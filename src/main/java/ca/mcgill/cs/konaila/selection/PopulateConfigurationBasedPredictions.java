package ca.mcgill.cs.konaila.selection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseFeaturesDataflow;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.selection.categorization.Category;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseAnonymousClassCreation;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseAnonymousClassCreation.AnonymousClassCreationSelectionUnit;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseApiCallsSelectionFeatures;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseApiCallsSelectionFeatures.ApiCallSelectionUnit;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseCodeFragmentCategory;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseControlFlowSelectionFeatures;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseControlFlowSelectionFeatures.ControlFlowSelectionUnit;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseNoCategoryFeatures;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseNoCategoryFeatures.NoCategorySelectionUnit;
import ca.mcgill.cs.konaila.selection.categorization.ScoringSelectionUnit;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection.ClassSignature;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection.MethodSignature;
import ca.mcgill.cs.konaila.selection.features.IncludeOrNot;

public class PopulateConfigurationBasedPredictions {

	public static void main(String[] args) throws Exception {

		Database.getInstance().initConnection(Main.DB);		
		Connection conn = Database.getInstance().getConnection();
		
		populateScores(conn);
		
		Database.getInstance().closeConnection();
	}
	
	public static void populateScores(Connection conn) throws SQLException, IOException {

		conn.setAutoCommit(false);		
		
		List<Integer> cids = DatabaseGetCodeFragments.getJavaCodeFragmentsCidsLongEnough(conn);		
		
		for( Integer cid : cids ) {
			populateIndividualScores(conn, cid);
		}
		DatabasePredictionsComponents.populateConsolidatedScores(conn);
		conn.commit();

		for( Integer cid : cids ) {
			Map<Integer,Float> boosts = DatabaseFeaturesDataflow.getDataFlowBoost(conn, cid);
			DatabaseFeaturesDataflow.addDataFlowBoost(conn, cid, boosts);
		}
		conn.commit();

		DatabasePredictionsComponents.populateConsolidatedScoresDataFlow(conn);
		
	}
	
	public static void populateIndividualScores(Connection conn, int cid) throws SQLException, IOException {
		
//		int max = DatabaseCodeFragmentCategory.selectMaxCertainty(conn,cid);
		int max = IncludeOrNot.IncludeProbably.ordinal();
		List<String> topCategories = DatabaseCodeFragmentCategory.selectTopCategories(conn, max, cid);
				
		populateCandidateSelectionUnits(conn, cid, topCategories);
		
	}
	
	public static void populateCandidateSelectionUnits(Connection conn, int cid, 
			List<String> topCategories) throws SQLException, IOException {
		
		List<ScoringSelectionUnit> candidates = new ArrayList<ScoringSelectionUnit>();
		if( topCategories.size() == 0 ) {
			candidates = selectCandidatesWhenNoCategory(conn, cid);
		} else {
			for( String categoryDisplayStr : topCategories ) {
				Category category = Category.valueOf(categoryDisplayStr);
				if( category == Category.ControlFlowCentric) {					
					candidates.addAll(selectControlFlowUnitsCandidates(conn, cid));					
				}
				if( category == Category.ApiCalls ) {
					candidates.addAll(selectApiCallsCandidates(conn, cid));
				}
	//			if( category == Category.SingleApiCallBack  ) {
	//				candidates.addAll(selectSingleApiCallBack(conn, cid));
	//			}
				if( category == Category.ClassDefinedAndInstantiated ) {
					candidates.addAll(selectAnonymousClass(conn, cid));
				}
				if( category == Category.Structure) {
					candidates.addAll(selectStructure(conn,cid));
				}
			}
		}
		
		DatabasePredictionsComponents.populateCandidates(conn, cid, candidates);
	}
	
	
	public static List<ScoringSelectionUnit> selectAnonymousClass(Connection c, int cid) 
			 throws SQLException, IOException {
				
		List<ScoringSelectionUnit> results = new ArrayList<ScoringSelectionUnit>();
		
		Map<Integer,AnonymousClassCreationSelectionUnit> anon = DatabaseAnonymousClassCreation.selectAnonymoucClassCreationUnits(c, cid);
		Map<Integer,MethodSignature> methodsInside = DatabaseAnonymousClassCreation.selectMethods(c, cid, anon);		
		
		for( Entry<Integer,AnonymousClassCreationSelectionUnit> e : anon.entrySet() ) {
			AnonymousClassCreationSelectionUnit a = e.getValue();
			int score = 0;
			score += 10;
			score += a.hasQueryTerms() ? 3 : 0;
			a.setScore(score);
		}
		
		for( Entry<Integer,MethodSignature> e : methodsInside.entrySet() ) {
			MethodSignature m = e.getValue();
			int score = 0;
			score += m.getMethodConfiguration() == IncludeOrNot.Exclude ? -2 : 0;
			score += m.getMethodConfiguration() == IncludeOrNot.IncludeProbably ? 0 : 0;
			score += m.getMethodConfiguration() == IncludeOrNot.Include ? 10 : 0;			
			score += m.hasQueryTerms() ? 3 : 0;
			m.setScore(score);
		}
		
		results.addAll(methodsInside.values());
		results.addAll(anon.values()) ;
				
		return results;
	}
	
	public static List<ScoringSelectionUnit> selectSingleApiCallBack(Connection c, int cid) 
			 throws SQLException, IOException {
		Map<Integer,MethodSignature> methodSignatures = DatabaseSignatureSelection.selectCallBackMethodSignatures(c, cid);
		if( methodSignatures.size() != 1 ) {
			throw new RuntimeException();
		}
		for( Map.Entry<Integer, MethodSignature> e : methodSignatures.entrySet()) {	
			MethodSignature m = e.getValue();
			m.setScore(10);
		}
		return new ArrayList<ScoringSelectionUnit>(methodSignatures.values());
	}
	
	public static List<ScoringSelectionUnit> selectStructure(Connection c, int cid)  
			 throws SQLException, IOException {
		
		List<ScoringSelectionUnit> result = new ArrayList<ScoringSelectionUnit>();
		
		Map<Integer,MethodSignature> methodSignatures = DatabaseSignatureSelection.
				selectImportantMethodSignatures(c, cid);
		Map<Integer,ClassSignature> classSignatures = DatabaseSignatureSelection.
				selectImportantClassSignatures(c, cid);

//		if( methodSignatures.size() == 1 && classSignatures.size() == 0) {
//			throw new RuntimeException();
//		}
		
		DatabaseSignatureSelection.populateQueryTerms(c, cid, methodSignatures, classSignatures);
		
		for( Map.Entry<Integer, MethodSignature> e : methodSignatures.entrySet()) {	
			MethodSignature m = e.getValue();
			int score = 0;
			score += m.getMethodConfiguration() == IncludeOrNot.Exclude ? -2 : 0;
			score += m.getMethodConfiguration() == IncludeOrNot.IncludeProbably ? 3 : 0;
			score += m.getMethodConfiguration() == IncludeOrNot.Include ? 5 : 0;			
			score += m.hasQueryTerms() ? 3 : 0;
			m.setScore(score);
		}
		
		for( Map.Entry<Integer, ClassSignature> e : classSignatures.entrySet()) {	
			ClassSignature s = e.getValue();
			int score = 0;
			score += s.hasExtends() || s.hasImplements() ? 10 : 0;
			score += s.hasQueryTerms() ? 3 : 0;
			s.setScore(score);
		}
		result.addAll(methodSignatures.values());
		result.addAll(classSignatures.values());
		return result;
		
	}
	
	public static List<ScoringSelectionUnit> selectControlFlowUnitsCandidates(Connection c, int cid) 
			 throws SQLException, IOException {
				
		Map<Integer,ControlFlowSelectionUnit> cfUidsToInfo = DatabaseControlFlowSelectionFeatures.selectControlFlowSelectionUnits(c,cid);
		DatabaseControlFlowSelectionFeatures.populateQueryTerms(c, cid, cfUidsToInfo);
		DatabaseControlFlowSelectionFeatures.populateCallCounts(c, cid, cfUidsToInfo);
		DatabaseControlFlowSelectionFeatures.populateLiterals(c, cid, cfUidsToInfo);		
				
		for( Map.Entry<Integer, ControlFlowSelectionUnit> e : cfUidsToInfo.entrySet()) {	
			ControlFlowSelectionUnit cf = e.getValue();
			int score = 0;
			score += cf.getCallCount() >= 2 ? 2 : cf.getCallCount();
			score += cf.isConstructor() ? 1 : 0;
			score += cf.hasQueryTerms() ? 2 : 0;
			score += cf.hasContainBooleanLiteral()
					|| cf.hasContainCharacterLiteral()
					|| cf.hasContainFloatLiteral()
					|| cf.hasContainIntegerLiteral() ? 2 : 0;
			score += cf.hasNullLiteral() ? -2 : 0;
			cf.setScore(score);
		}
		return new ArrayList<ScoringSelectionUnit>(cfUidsToInfo.values());
	}
	
	public static List<ScoringSelectionUnit> selectApiCallsCandidates(Connection c, int cid) 
			 throws SQLException, IOException {
				
		Map<Integer,ApiCallSelectionUnit> apiCalls = DatabaseApiCallsSelectionFeatures.selectApiSelectionUnits(c, cid);
		DatabaseApiCallsSelectionFeatures.populateQueryTerms(c, cid, apiCalls);
		
		for( Map.Entry<Integer, ApiCallSelectionUnit> e : apiCalls.entrySet()) {		
			ApiCallSelectionUnit api = e.getValue();
			int score = 0;
			score += api.getCallCount() >= 3 ? 3 : api.getCallCount();
			score += api.isConstructor() ? 3 : 0;
			score += api.hasQueryTerms() ? 3 : 0;			
			api.setScore(score);
		}		
		return new ArrayList<ScoringSelectionUnit>(apiCalls.values());
	}
	

	public static List<ScoringSelectionUnit> selectCandidatesWhenNoCategory(Connection c, int cid) 
			 throws SQLException, IOException {
				
		Map<Integer,NoCategorySelectionUnit> candidates = DatabaseNoCategoryFeatures.getTopFeaturesIgnoringCategory(c, cid);
		return new ArrayList<ScoringSelectionUnit>(candidates.values());
	}
	
	public static void sortUnits(Connection conn, int cid, List<ScoringSelectionUnit> units) {
		Collections.sort(units, new Comparator<ScoringSelectionUnit>() {
			@Override
			public int compare(ScoringSelectionUnit o1, ScoringSelectionUnit o2) {
				return o2.getScore()-o1.getScore();
			}		
		});			
	}
	
}
