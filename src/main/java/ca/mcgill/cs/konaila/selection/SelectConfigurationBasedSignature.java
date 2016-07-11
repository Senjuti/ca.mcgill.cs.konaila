package ca.mcgill.cs.konaila.selection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ca.mcgill.cs.konaila.Source;
import ca.mcgill.cs.konaila.database.DatabaseFeatures;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection.MethodSignature;
import ca.mcgill.cs.konaila.selection.features.IncludeOrNot;

public class SelectConfigurationBasedSignature {
	
	public static void populateScores(Connection conn, Source source) throws SQLException, IOException {	

		List<Integer> cids = DatabaseFeatures.getCidsSorted(conn, source.getDbSourceString());
		
		for( Integer cid : cids ) {
			Map<Integer,MethodSignature> methodSignatureUids 
				= DatabaseSignatureSelection.selectImportantMethodSignatures(conn, cid);
					
			for( Entry<Integer,MethodSignature>  e : methodSignatureUids.entrySet()) {
				MethodSignature method = e.getValue();
				int uid = method.getUid();
				int methodScore = 0; 
				IncludeOrNot include = method.getMethodConfiguration();
				if( include == IncludeOrNot.Include) {
					methodScore = 10;
				} else if( include == IncludeOrNot.IncludeProbably ) {
					methodScore = 5;
				} else if( include == IncludeOrNot.ExcludeProbably) {
					methodScore =1;
				} else if( include == IncludeOrNot.Exclude) {
					methodScore = -1;                                                                                                                                                                                                                                                   
				}
	
				DatabaseConfigurationBasedPredictions.populatePredictions(conn, cid, uid, methodScore);
				
				if( method.hasEnclosingClassWithInheritance() && methodScore >= 5 ) {
					int typeUid = method.getEnclosingUid();
					DatabaseConfigurationBasedPredictions.
						populatePredictions(conn, cid, typeUid, methodScore);					
				}
			}	
			
			// clean class signatures if there's no methods			
		}		
	}
}
