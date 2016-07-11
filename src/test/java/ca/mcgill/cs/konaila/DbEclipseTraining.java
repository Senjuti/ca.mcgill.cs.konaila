package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbEclipseTraining {
	
	public static String selectTrainingData(Connection conn) throws SQLException, IOException, Exception {

		String content = "cid\tuid\tmostTerms\tmostDiverseTerms\tdeclaredInCode\tjava\tdfPackage\tinSummary\n";
		
		int cid = -1;
		int uid = -1;
		int inSummary = -1;
		int mostTerms = -1;
		int mostDiverseTerms = -1;
		int declaredInCode = -1;
		int java = -1;
		int dfPackage = -1;
		
		PreparedStatement s = conn
				.prepareStatement("SELECT E.cid, G.uid, sum(E.inSummary), sum(E.mostTerms), sum(E.mostDiverseTerms), sum(E.declaredInCode), sum(E.java), sum(E.dfPackage) "
						+ " FROM goldStandard G, eclipseGoldStandardLineBased E "
						+ " WHERE E.cid=G.cid AND E.line=G.line "
						+ " GROUP BY E.cid, uid");
		
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			cid = r.getInt(1) - 1000;
			uid = r.getInt(2);
			inSummary = r.getInt(3);
			mostTerms = r.getInt(4);
			mostDiverseTerms = r.getInt(5);
			declaredInCode = r.getInt(6);
			java = r.getInt(7);
			dfPackage = r.getInt(8);	
			
			content += cid + "\t" + uid + "\t" + 
					feature(mostTerms) + "\t" + feature(mostDiverseTerms) + "\t" + 
					feature(declaredInCode) + "\t" + feature(java) + "\t" + dfPackage + "\t" + feature(inSummary) + "\n";
		}
		
		return content;
	}
	
	private static String feature(int value) {
		String result = value > 0 ? "TRUE" : "FALSE";
		return result;
	}
	
	
}
