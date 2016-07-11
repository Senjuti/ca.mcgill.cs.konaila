package ca.mcgill.cs.konaila.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSummaries {
	
	public static void populateSummary(Connection conn, int cid, String summary, 
			String how, String size) throws SQLException, IOException {		
		
		PreparedStatement s = conn
				.prepareStatement("INSERT INTO summaries values(?,?,?,?);");
		
		s.setInt(1, cid);
		s.setString(2, summary);
		s.setString(3, how);
		s.setString(4, size);
		s.executeUpdate();	
		
		s.close();	
	}

	
	public static String getSummary(Connection conn, int cid, String how) 
			throws SQLException, IOException {		
		String summary = "";
		
        PreparedStatement s = conn
                .prepareStatement("SELECT summary, how "
                                  + " FROM summaries "
                                  + " WHERE cid=? AND how LIKE ?");			
		s.setInt(1, cid);
		s.setString(2, how);
		ResultSet r = s.executeQuery();			
		
		if( r.next() ) {
			summary = r.getString(1);
		}
		r.close();
		s.close();
		return summary;
	}
	
	public static void compareSummaries() {
	
//	SELECT Baseline.summary==Knapsack.summary, Baseline.summary==Greedy.summary, Knapsack.summary==Greedy.summary
//			FROM summaries Baseline, summaries Greedy, summaries Knapsack
//			WHERE Baseline.cid=Greedy.cid AND Greedy.cid=Knapsack.cid AND Baseline.cid=Knapsack.cid
//			AND Baseline.how LIKE 'Baseline' AND Greedy.how LIKE 'Greedy' AND Knapsack.how LIKE 'Knapsack'
	}
	
	public static List<Integer> getCids(Connection conn) 
			throws SQLException, IOException {	
		List<Integer> cids = new ArrayList<>();
        PreparedStatement s = conn.prepareStatement("SELECT DISTINCT cid "
                                  + " FROM summaries ");			

		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			int cid = r.getInt(1);
			cids.add(cid);
		}
		
		r.close();
		s.close();
		return cids;
		
	}
}
