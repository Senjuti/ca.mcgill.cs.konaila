package ca.mcgill.cs.konaila.selection.weka;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import ca.mcgill.cs.konaila.selection.weka.DatabaseWekaTrainingNonSignatures.CidUid;

public class Weka {

	public static void dataPrep(Connection c) throws Exception { 

		ArrayList<Attribute> wekaAttributes = new ArrayList<Attribute>();
		
		// boolean attributes
		ArrayList<String> booleanFeatureNames = DatabaseWekaTrainingNonSignatures.getFeatureNames(c, "Boolean");
		List<String> booleans = Arrays.asList(new String[]{"false","true"});
		for( String f : booleanFeatureNames ) {
			wekaAttributes.add(new Attribute(f, booleans));			
		}
		
		// integer attributes
		ArrayList<String> integerFeatureNames = DatabaseWekaTrainingNonSignatures.getFeatureNames(c, "Integer");
		for( String f : integerFeatureNames ) {
			Attribute integerAttribute = new Attribute(f);
			wekaAttributes.add(integerAttribute); 			
		}		
		
		// categorical attribute
		ArrayList<String> categoricalFeatureNames = DatabaseWekaTrainingNonSignatures.getFeatureNames(c, "Categorical");
		for( String f : categoricalFeatureNames ) {			
			ArrayList<String> featureValues = DatabaseWekaTrainingNonSignatures.getFeatureValues(c,f);
			Attribute categoricalAttribute = new Attribute(f, featureValues);
			wekaAttributes.add(categoricalAttribute); 			
		}			
		
		// class attribute
		wekaAttributes.add(new Attribute("class", booleans));
		
		
		Map<String,Integer> attributeToIndex = getAttributeIndexMap(wekaAttributes);
		
		// instances
//		Instances data = new Instances("Summarization Training Data", wekaAttributes, 0);
		Set<CidUid> cidUids = DatabaseWekaTrainingNonSignatures.getEclipseCidsUids(c);

		List<Integer> randomizedCids = DatabaseWekaTrainingNonSignatures.getEclipseRandomizedCid(c);
		double percent = 70.0; 
		int trainSize = (int) Math.round(randomizedCids.size() * percent / 100); 
		
		Instances train = new Instances("Summarization Training Data", wekaAttributes, 0); 
		Instances test = new Instances("Summarization Test Data", wekaAttributes, 0);
		Instances data = null;
		Set<Integer> trainCids = new HashSet<Integer>();
		Set<Integer> testCids = new HashSet<Integer>();
		Set<Integer> cids = new HashSet<Integer>();
		
		int goldStandardNotFound = 0;
		for( CidUid cidUid : cidUids ) { // each instance
			int cid = cidUid.getCid();
			int uid = cidUid.getUid();

			data = randomizedCids.indexOf(new Integer(cid)) < trainSize ?
					train : test;
			cids = randomizedCids.indexOf(new Integer(cid)) < trainSize ?
					trainCids : testCids;
			
			double[] attributeValues = new double[wekaAttributes.size()];
			Arrays.fill(attributeValues, 0);
			
			// class
			System.out.println(cid + "," + uid);
			int inSummary = DatabaseWekaTrainingNonSignatures.getInSummary(c,cid,uid);
			if( inSummary == -1 ) {
				goldStandardNotFound+=1;
			} else {
				
				attributeValues[attributeValues.length - 1] = inSummary;
				
				// features
				Set<String> features = DatabaseWekaTrainingNonSignatures.getFeatures(c,cid,uid);
				for( String feature : features ) {

					int index = attributeToIndex.get(feature);
					
					// boolean feature
					if( booleanFeatureNames.contains(feature)) {
						attributeValues[index]=1; // feature is present, hence "true"
					} 
					
					// numeric feature					
					else if( integerFeatureNames.contains(feature)) {
						int intValue = DatabaseWekaTrainingNonSignatures.getIntValue(c, cid, uid, feature);
						attributeValues[index]=intValue; // feature is present, hence "true"
					} 
					
					// categorical feature
					else if( categoricalFeatureNames.contains(feature )) {
						int stringValue = DatabaseWekaTrainingNonSignatures.getStringValue(c, cid, uid, feature);
						attributeValues[index]=stringValue;
					}
				}

				// add instance to the Weka dataset
				data.add(new DenseInstance(1.0, attributeValues));
				cids.add(cid);
			}			
		}
		
		System.out.println("instances: " + data.size());
		System.out.println("# of instances from DB with features: " + cidUids.size());
		System.out.println("# of instances from DB without matching gold standard: " + goldStandardNotFound);
		
		FileUtils.write(new File("./eclipse-training.arff"), train.toString());
		FileUtils.write(new File("./eclipse-test.arff"), test.toString());
		FileUtils.write(new File("./eclipse-training-cids.txt"), trainCids.toString());
		FileUtils.write(new File("./eclipse-test-cids.txt"), testCids.toString());
	}
	
	static ArrayList<Attribute> setAttributes(Connection c) throws SQLException, IOException {
		ArrayList<Attribute> wekaAttributes = new ArrayList<Attribute>();
		
		// boolean attributes
		ArrayList<String> booleanFeatureNames = DatabaseWekaTrainingNonSignatures.getFeatureNames(c, "Boolean");
		List<String> booleans = Arrays.asList(new String[]{"false","true"});
		for( String f : booleanFeatureNames ) {
			wekaAttributes.add(new Attribute(f, booleans));			
		}
		
		// integer attributes
		ArrayList<String> integerFeatureNames = DatabaseWekaTrainingNonSignatures.getFeatureNames(c, "Integer");
		for( String f : integerFeatureNames ) {
			Attribute integerAttribute = new Attribute(f);
			wekaAttributes.add(integerAttribute); 			
		}		
		
		// categorical attribute
		ArrayList<String> categoricalFeatureNames = DatabaseWekaTrainingNonSignatures.getFeatureNames(c, "Categorical");
		for( String f : categoricalFeatureNames ) {			
			ArrayList<String> featureValues = DatabaseWekaTrainingNonSignatures.getFeatureValues(c,f);
			Attribute categoricalAttribute = new Attribute(f, featureValues);
			wekaAttributes.add(categoricalAttribute); 			
		}			
		
		// class attribute
		wekaAttributes.add(new Attribute("class", booleans));
		
		return wekaAttributes;
	}
	
	
	static Map<String, Integer> getAttributeIndexMap(ArrayList<Attribute> wekaAttributes) throws SQLException, IOException {		
		Map<String,Integer> map = new HashMap<String,Integer>();		
		int count = 0;
		for( Attribute attribute : wekaAttributes ) {
			map.put(attribute.name(), count);			
			count+=1;
		}
		return map;
	}
	
	public static void trainModel() throws Exception {
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File("./eclipse.arff"));
		Instances data = loader.getDataSet(); 
		int classIndex = data.numAttributes() - 1;
		data.setClassIndex(classIndex);
		Classifier model = new J48();
		model.buildClassifier(data);
	}
	
	
	public static void evaluateModelCodeFragmentBased(Connection c) throws Exception {
		
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File("./eclipse-training.arff"));
		Instances train  = loader.getDataSet(); 
		int classIndex = train.numAttributes() - 1;
		
		loader.setFile(new File("./eclipse-test.arff"));
		Instances test  = loader.getDataSet(); 

		train.setClassIndex(classIndex); 
		test.setClassIndex(classIndex);   //do eval
		
		Classifier model = new J48();
		model.buildClassifier(train);
		
		Evaluation eval = new Evaluation(train); //trainset 
		eval.evaluateModel(model, test); //testset 		
		
		
		System.out.println(eval.toSummaryString()); 
		System.out.println(eval.weightedFMeasure()); 
		System.out.println(eval.weightedPrecision()); 
		System.out.println(eval.weightedRecall()); 
		
		// - See more at: http://zitnik.si/wordpress/2011/09/25/quick-intro-to-weka/#sthash.yad9oXMh.dpuf

	}
	
	public static void predict(Connection c) throws Exception {
		
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File("./eclipse-training.arff"));
		Instances train  = loader.getDataSet(); 
		int classIndex = train.numAttributes() - 1;
		
		loader.setFile(new File("./eclipse-test.arff"));
		Instances test  = loader.getDataSet(); 

		train.setClassIndex(classIndex); 
		test.setClassIndex(classIndex);   //do eval
		
		Classifier model = new J48();
		model.buildClassifier(train);
		
		for( Instance t :  test ) {
			System.out.println(t);
			double prediction = model.classifyInstance(t);
			System.out.println(prediction);
		}
		


	}
	
	public static void evaluateModelLineBased() throws Exception {
		
		ArffLoader loader = new ArffLoader();
		loader.setFile(new File("./eclipse.arff"));
		Instances data = loader.getDataSet(); 
		int classIndex = data.numAttributes() - 1;
		
		
		double percent = 70.0; 
		int trainSize = (int) Math.round(data.size() * percent / 100); 
		int testSize = data.numInstances() - trainSize; 
		Instances train = new Instances(data, 0, trainSize);
		Instances test = new Instances(data, trainSize, testSize);		
		
		train.setClassIndex(classIndex); 
		test.setClassIndex(classIndex);   //do eval
		
		Classifier model = new J48();
		model.buildClassifier(train);
		
		Evaluation eval = new Evaluation(train); //trainset 
		eval.evaluateModel(model, test); //testset 
		
		System.out.println(eval.toSummaryString()); 
		System.out.println(eval.weightedFMeasure()); 
		System.out.println(eval.weightedPrecision()); 
		System.out.println(eval.weightedRecall()); 
		
		// - See more at: http://zitnik.si/wordpress/2011/09/25/quick-intro-to-weka/#sthash.yad9oXMh.dpuf

	}
}
