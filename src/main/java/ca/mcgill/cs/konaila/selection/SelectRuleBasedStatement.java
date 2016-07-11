package ca.mcgill.cs.konaila.selection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import ca.mcgill.cs.konaila.selection.weka.DatabaseWekaTrainingNonSignatures;
import ca.mcgill.cs.konaila.selection.weka.DatabaseWekaTrainingNonSignatures.CidUid;

public class SelectRuleBasedStatement {

	public static void populateScores(Connection c) throws SQLException, IOException { 
		
		Set<CidUid> cidUids = DatabaseWekaTrainingNonSignatures.getEclipseCidsUids(c);

		for( CidUid cidUid : cidUids ) { // each instance
			int cid = cidUid.getCid();
			int uid = cidUid.getUid();

			Set<String> features = DatabaseWekaTrainingNonSignatures.getFeatures(c,cid,uid);
			
			int score = 0;
				
			// features
			for( String f : features ) {
				if( f.equals("Comment")
						|| f.equals("CatchWrapper")
						|| f.equals("ElseWrapper")
						|| f.equals("FieldDeclaration")
						|| f.equals("FinallyWrapper")
						|| f.equals("ImportDeclaration")
						|| f.equals("PackageDeclaration")
						|| f.equals("SynchronizedWrapper")
						|| f.equals("TryWrapper") 
						|| f.equals("WhileWrapper") ) {

					score += -1;
				} else if( f.equals("CallCount")) {
					int intValue = DatabaseWekaTrainingNonSignatures.getIntValue(c, cid, uid, f);
					score += 2*intValue;
				} else if( f.equals("Constructor")) {
					score += 2;
				} else if( f.equals("LocalVariableDeclarationStatement")) {
					String stringValue = DatabaseWekaTrainingNonSignatures.getStringValueSimple(c, cid, uid, f);
					if( stringValue.equals("Primitive")) {
						score += 0;
					} else if( stringValue.equals("Object")) {
						score += 1;
					}
				} else if( f.equals("IfWrapper")
						|| f.equals("SwitchLabel")
						|| f.equals("SwitchWrapper")) {
					score += 1;
				}
			}
			
			DatabaseConfigurationBasedPredictions.populatePredictions(c, cid, uid, score);
		}
	}
}
