package ca.mcgill.cs.konaila.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import ca.mcgill.cs.konaila.summarize.HowToSummarize;
import ca.mcgill.cs.konaila.summarize.Size;

public class DatabaseRecomputeBaseline {

	private static final HowToSummarize how = HowToSummarize.Baseline;

	public static void populate(Connection conn, int cid, String summary,
			Size size) throws IOException, SQLException{
				
		PreparedStatement s = conn
				.prepareStatement("INSERT INTO summariesBaseline values(?,?,?,?);");
		
		System.out.println("inserting " + cid + ", " +size);
		
		s.setInt(1, cid);
		s.setString(2, summary);
		s.setString(3, how.name());
		s.setString(4, size.name());
		s.executeUpdate();	
		
		s.close();		
	}
	
	public static void cleanUpKonailaTags(Connection conn) throws IOException, SQLException{
        PreparedStatement u = conn.prepareStatement(
						"UPDATE summariesBaseline "
						+ " SET summary=? "
						+ " WHERE cid=? AND how=? AND size=?");
        PreparedStatement s = conn.prepareStatement(
						"SELECT cid, how, size, summary "
						+ " FROM summariesBaseline "
						+ " WHERE summary LIKE '%ADDED BY KONAILA%' ");

        ResultSet r = s.executeQuery();
        while(r.next()){ 
        	int cid = r.getInt(1);
        	String how = r.getString(2);
        	String size = r.getString(3);
        	String summary = r.getString(4);
        	
        	String fixedSummary = summary.replace("// <ADDED BY KONAILA>", "");
        	
        	u.setString(1,fixedSummary);
        	u.setInt(2,cid);
        	u.setString(3,how);
        	u.setString(4,size);
        	u.executeUpdate();
        }
        
        conn.commit();
		r.close();
		s.close();
		u.close();
	}
}
