package ca.mcgill.cs.konaila.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import ca.mcgill.cs.konaila.Main;

public class DatabaseConsolidateEmptySummaries {

	private static final File CONSLIDATE_FILE = new File("./database/study/conslidate-summaries.csv");
	
	static {		
		try {
			FileUtils.writeStringToFile(CONSLIDATE_FILE, 
					"cid\tuser\ttaskNumber\tcidOrder\tsize\thibernateOrSpring\n", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		Database.setDatabaseString(Main.DB);
//		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();

		conn.setAutoCommit(false);

		fixStudyRandomizedCodeFragmentTable(conn);
//		printTaskAssignments(conn);
		consolidate(conn);
		
		conn.commit();		
		Database.getInstance().closeConnection();
	}
	
	public static void consolidate(Connection conn) throws IOException, SQLException{
	
		Set<Integer> cids = getEmptySummaries(conn);
		List<Integer> sortedCids = new ArrayList<>();
		Collections.sort(sortedCids);

		PreparedStatement d = conn.prepareStatement(
				"DELETE FROM studyTaskAssignments" +
				" WHERE cid=? ");  // delete on to the replaced
		
		PreparedStatement u = conn.prepareStatement(
				"UPDATE studyTaskAssignments" +
				" SET cid=?, cidOrder=?" +
				" WHERE cid=? "); // enter it to the update
		
		for( int cid : cids ) {
			String hibernateOrSpring = DatabaseStudy.getHibernateOrSpring(conn, cid);
        	Pair<Integer,Integer> cidToBeReplacedAndOrder = DatabaseStudy
        			.getNextCid(conn, hibernateOrSpring);
        	int cidToBeReplaced = cidToBeReplacedAndOrder.getLeft();
        	int cidOrderToBeReplaced = cidToBeReplacedAndOrder.getRight();
        	
        	d.setInt(1, cidToBeReplaced);
        	d.executeUpdate();
        	conn.commit();
        	
        	u.setInt(1, cidToBeReplaced);
        	u.setInt(2, cidOrderToBeReplaced);
        	u.setInt(3, cid);
        	u.executeUpdate();        	
		}
		u.close();
	}
	
	private static void printTaskAssignments(Connection conn)
			throws IOException, SQLException{
		
		Set<Integer> cids = getEmptySummaries(conn);
		
        PreparedStatement s = conn.prepareStatement(
						"SELECT user, taskNumber, cidOrder, size, hibernateOrSpring "
						+ " FROM studyTaskAssignments"
						+ " WHERE cid = ? ");       
        
        for( int cid : cids ) {
        	s.setInt(1, cid);
	        ResultSet r = s.executeQuery();
	        while( r.next()){
	        	String user = r.getString(1);
	        	int taskNumber = r.getInt(2);
	        	int cidOrder = r.getInt(3);
	        	String size = r.getString(4);
	        	String hibernateOrSpring = r.getString(5);
	        		        	
	        	String line = cid+ "\t"+ user +"\t"+taskNumber+"\t"+cidOrder+"\t"
	        			+size+"\t"+hibernateOrSpring+"\n";
	        	FileUtils.writeStringToFile(CONSLIDATE_FILE, line, true);
	        }
	        r.close();
        }
		s.close();
	}
	

	
	
	public static void fixStudyRandomizedCodeFragmentTable(Connection conn)
			throws IOException, SQLException{
			
		Set<Integer> cids = getEmptySummaries(conn);
		
        PreparedStatement u = conn.prepareStatement(
        		"UPDATE studyRandomizedCodeFragments "
        		+ " SET cidOrder=cidOrder-10000 "
        		+ " WHERE cid=?");
        
        for( int cid : cids) {
        	u.setInt(1, cid);
        	u.executeUpdate();
        }
        u.close();
	}
	
	private static Set<Integer> getEmptySummaries(Connection conn)
			throws IOException, SQLException{
		
		Set<Integer> cids = new HashSet<Integer>();
        PreparedStatement s = conn.prepareStatement(
						"SELECT cid, how, size, summary "
						+ " FROM summaries ");
        
        ResultSet r = s.executeQuery();
        while( r.next()){
        	int cid = r.getInt(1);
        	String how = r.getString(2);
        	String size = r.getString(3);
        	String summary = r.getString(4);
        	
        	if( summary.trim().length() == 0 ) {
        		cids.add(cid);
        	}        	
        }
		
        return cids;
	}
}
