package ca.mcgill.cs.konaila.selection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DatabaseJoinEclipseLinesWithSelectionUnits  {
	
	public static final String goldStandardTableName = "goldStandard";
	public static final String goldStandardTempTableName = "goldStandardLinesMappedToOneUnit";
	public static final String goldStandardLineBasedTableName = "eclipseGoldStandardLineBased";
	public static final String predictionLineBasedTableName = "eclipsePredictionsLineBased";
	

	public static void joinPredictions(Connection conn) throws SQLException, IOException {
		
//		create table eclipsePredictionsLineBased (cid INTEGER, line INTEGER, probabilityInSummary REAL, featureSet, FOREIGN KEY(cid,uid) REFERENCES selectionUnits(cid,uid));
		String sql = "INSERT INTO predictions values(?,?,?,?);";
		
		PreparedStatement s = conn.prepareStatement(sql);
		
		Map<Integer,Integer> cidToNumberOfLines = selectEclipseCidsNumberOfLines(conn);
		for( Entry<Integer,Integer> e : cidToNumberOfLines.entrySet()) {
			int cid = e.getKey();
			int maxLineNumber = e.getValue();
			
			for( int lineNumber = 1; lineNumber < maxLineNumber+1; lineNumber++ ) {
				int numberOfMatchedSelectionUnits = selectNumberOfMatchesWithLineBasedGoldStandard(
						conn, cid, lineNumber, predictionLineBasedTableName);
				
				// unambiguious cases
				if( numberOfMatchedSelectionUnits == 1) { 
					Map<Integer,Integer> uidGroundTruths = selectMatchedUidPredictions(conn, cid, lineNumber);
					for( Entry<Integer, Integer> uidGroundTruth: uidGroundTruths.entrySet()) {
						int uid = uidGroundTruth.getKey();
						int inSummary = uidGroundTruth.getValue();
						
						System.out.println("inserting prediction data " + cid + "," + uid + "," + inSummary );
						
						s.setInt(1, cid);
						s.setInt(2, uid);
						s.setInt(3, inSummary);
						s.setInt(4, lineNumber);
						s.executeUpdate();
					}				
				}
			}
		}

		s.close();
	}
	
	public static void joinGoldStandard(Connection conn) throws SQLException, IOException {		
		mapGoldStandardLinesToOneUnit(conn);
		makeUnitsUnique(conn);
	}
	
	public static void mapGoldStandardLinesToOneUnit(Connection conn) throws SQLException, IOException {
		
		String sql = "insert into *** values(?,?,?,?);";
		sql = sql.replace("***", goldStandardTempTableName);
		
		PreparedStatement s = conn.prepareStatement(sql);
		
		Map<Integer,Integer> cidToNumberOfLines = selectEclipseCidsNumberOfLines(conn);
		for( Entry<Integer,Integer> e : cidToNumberOfLines.entrySet()) {
			int cid = e.getKey();
			int maxLineNumber = e.getValue();
			
			for( int lineNumber = 1; lineNumber < maxLineNumber+1; lineNumber++ ) {
				int numberOfMatchedSelectionUnits = selectNumberOfMatchesWithLineBasedGoldStandard(
						conn, cid, lineNumber, goldStandardLineBasedTableName);
				
				// unambiguious cases: each line maps to one uid (but one unit may map to many lines)
				if( numberOfMatchedSelectionUnits == 1) { 
					Map<Integer,Integer> uidGroundTruths = selectMatchedUidGoldStandard(
							conn, cid, lineNumber);
					for( Entry<Integer, Integer> uidGroundTruth: uidGroundTruths.entrySet()) {
						int uid = uidGroundTruth.getKey();
						int inSummary = uidGroundTruth.getValue();
						
						System.out.println("inserting training data " + cid + "," + uid + "," + inSummary );
						
						s.setInt(1, cid);
						s.setInt(2, uid);
						s.setInt(3, inSummary);
						s.setInt(4, lineNumber);
						s.executeUpdate();
					}				
				}
			}
		}
		s.close();
	}
	
	public static void makeUnitsUnique(Connection conn) throws SQLException, IOException {
		String insertString = "insert into *** values(?,?,?);";
		insertString = insertString.replace("***", goldStandardTableName);		
		PreparedStatement insert = conn.prepareStatement(insertString);
		
		String queryString ="SELECT sum(inSummary), cid, uid "
				+ " FROM *** "
				+ " GROUP BY cid, uid"; 
		queryString = queryString.replace("***", goldStandardTempTableName);
		PreparedStatement q = conn.prepareStatement(queryString);	
		
		ResultSet result = q.executeQuery();				
		
		while( result.next()) {
			int cid = result.getInt("cid");
			int uid = result.getInt("uid");
			int sumOfInSummary = result.getInt(1);
			
			int inSummary = sumOfInSummary>=1 ? 1 : 0;
			insert.setInt(1, cid);
			insert.setInt(2, uid);
			insert.setInt(3, inSummary);
			insert.executeUpdate();
			
		}
		insert.close();
	}
	
	
	
	private static Map<Integer,Integer> selectMatchedUidGoldStandard(Connection conn, int cid, 
			int line) 
			throws SQLException, IOException {

		String query = "SELECT U.cid, U.uid, ### "
				+ " FROM *** as E, selectionUnits as U, elements as Elt "
				+ " WHERE E.cid=U.cid AND U.cid=Elt.cid AND U.uid=Elt.uid "
				+ " AND E.cid=? AND E.line=? "
				+ " AND (Elt.lineStart <= E.line AND Elt.lineEnd >= E.line)"
				+ " GROUP BY U.cid, U.uid";
		query = query.replace("***", goldStandardLineBasedTableName);
		query = query.replace("###", "E.inSummary");
		
		PreparedStatement s = conn.prepareStatement(query);
		

		Map<Integer,Integer> uidToGroundTruth = new HashMap<Integer,Integer>();
		s.setInt(1, cid);
		s.setInt(2, line);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int uid = r.getInt(2);
			int inSummary = r.getInt(3);
			uidToGroundTruth.put(uid,inSummary);
		}
		
		return uidToGroundTruth;
	}
	
	private static Map<Integer,Integer> selectMatchedUidPredictions(Connection conn, int cid, 
			int line) 
			throws SQLException, IOException {

		String query = "SELECT U.cid, U.uid, ### "
				+ " FROM *** as E, selectionUnits as U, elements as Elt "
				+ " WHERE E.cid=U.cid AND U.cid=Elt.cid AND U.uid=Elt.uid "
				+ " AND E.cid=? AND E.line=? "
				+ " AND (Elt.lineStart <= E.line AND Elt.lineEnd >= E.line)"
				+ " GROUP BY U.cid, U.uid";
		query = query.replace("***", predictionLineBasedTableName);
		query = query.replace("###", "E.probabilityInSummary");
		
		PreparedStatement s = conn.prepareStatement(query);
		

		Map<Integer,Integer> uidToGroundTruth = new HashMap<Integer,Integer>();
		s.setInt(1, cid);
		s.setInt(2, line);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int uid = r.getInt(2);
			int inSummary = r.getInt(3);
			uidToGroundTruth.put(uid,inSummary);
		}
		
		return uidToGroundTruth;
	}
		
	private static int selectNumberOfMatchesWithLineBasedGoldStandard(Connection conn, 
			int cid, int line, String lineBasedTable ) 
			throws SQLException, IOException {
		int count = 0;
		String query = "SELECT count(*) "
				+ " FROM *** as E, selectionUnits as U, elements as Elt "
				+ " WHERE E.cid=U.cid AND U.cid=Elt.cid AND U.uid=Elt.uid "
				+ " AND E.cid=? AND E.line=? "
				+ " AND (Elt.lineStart <= E.line AND Elt.lineEnd >= E.line)"
				+ " GROUP BY U.cid, U.uid";
		query = query.replace("***", lineBasedTable);
		
		PreparedStatement s = conn.prepareStatement(query);

		s.setInt(1, cid);
		s.setInt(2, line);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			count+=1;
		}
		
		return count;
	}
	
	public static Map<Integer,Integer> selectEclipseCidsNumberOfLines(Connection conn) throws SQLException, IOException {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		PreparedStatement s = conn
			.prepareStatement("SELECT cid, MAX(line) FROM eclipseGoldStandardLineBased  GROUP BY cid");

		ResultSet r = s.executeQuery();

		while(r.next()) {
			int cid = r.getInt(1);
			int maxLines = r.getInt(2);
			map.put(cid, maxLines);
		}
		
		return map;
	}
}
