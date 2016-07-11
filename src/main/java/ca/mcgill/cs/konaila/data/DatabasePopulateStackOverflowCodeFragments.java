package ca.mcgill.cs.konaila.data;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class DatabasePopulateStackOverflowCodeFragments {
	
	private static int minCidForThisBatch = 100000;

	public static void main(String[] args) throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		Connection conn = Database.getInstance().getConnection();
		
//		populate(conn, new File(GetCodeFragmentsFromSternaData.SPRING_DIR2), "spring");
		cleanDuplicates(conn);
		
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
	
	public static void populate(Connection conn) throws SQLException, IOException {	
		for( String hibernateDir : GetCodeFragmentsFromSternaData.HIBERNATE_DIRS) {
			populate(conn, new File(hibernateDir), "hibernate");
		}
		
		for( String springDir : GetCodeFragmentsFromSternaData.SPRING_DIRS) {
			populate(conn, new File(springDir), "spring");
		}
	}
	
	public static void populate(Connection conn, File file, String hibernateOrSpring)
			throws SQLException, IOException {		
		if( file.isDirectory() ) {
			File[] children = file.listFiles();
			for( File child : children ) {
				populate(conn, child, hibernateOrSpring);
			}
		} if( file.isFile() ) {
			System.out.println("Processing: " + file);
			Collection<StackOverflowCodeFragment> codeFragments = 
					GetCodeFragmentsFromSternaData.getStackOverflowCodeFragments(file);
			addCodeFragments(conn, codeFragments, hibernateOrSpring);			
		}
	}
	
	public static void addCodeFragments(Connection conn, 
	    Collection<StackOverflowCodeFragment> codeFragments,
	    String hibernateOrSpring) throws SQLException, IOException {
		
		PreparedStatement codeFragmentsStat = conn.prepareStatement(
				"INSERT INTO codeFragments VALUES(?,?,?,?,?,?);");
		
//	cid INTEGER, hibernateOrSpring, lastActivityDate INTEGER, score INTEGER,
//	answerPostId INTEGER, 
//	questionId INTEGER, questionCreationDate INTEGER, questionScore INTEGER
		
		PreparedStatement metadataStat = conn.prepareStatement(
				"INSERT INTO stackOverflowMetaData VALUES(?,?,?,?,?,?,?,?,?,?);");
		
		int currentCid = Math.max(minCidForThisBatch, 
				DatabaseGetCodeFragments.getMaxCid(conn));
		
		for ( StackOverflowCodeFragment c : codeFragments ) {
												
			currentCid += 1;
			
			loadCodeFragment(currentCid, -1, "stackoverflow", 
					c.getThreadUrl(),  c.getText(), "", "", c.getQuestion(),
					codeFragmentsStat);
			
			loadMetadata(currentCid, c.getPostId(), 
					hibernateOrSpring, c.getLastActivityDate(),
					c.getScore(), c.getPostId(), c.getQuestionId(), 
					c.getQuestionCreationDate(), c.getQuestionScore(),
					c.getSternaFilename(), metadataStat);
		}		
		codeFragmentsStat.close();
		metadataStat.close();

		minCidForThisBatch = currentCid;
		
		conn.commit();
	}
	
	private static void loadCodeFragment(int cid, int internalCid, String source, String url, 
			String code, String structure, String problem, String query,
			PreparedStatement codeFragmentsStat)
			throws SQLException, IOException {

		codeFragmentsStat.setInt(1, cid);
		codeFragmentsStat.setInt(2, internalCid);
		codeFragmentsStat.setString(3, source);
		codeFragmentsStat.setString(4, url);
		codeFragmentsStat.setString(5, code);
		codeFragmentsStat.setString(6, query);
		codeFragmentsStat.executeUpdate();
	}
	
//	cid INTEGER, hibernateOrSpring, lastActivityDate INTEGER, score INTEGER,
//	answerPostId INTEGER, questionId INTEGER, questionCreationDate INTEGER
	 private static void loadMetadata(int cid, int postId, 
		String hibernateOrSpring, 
	     int lastActivityDate, int score, int answerPostId,
	     int questionId, int creationDate, int questionScore,
	     String sternaFilename, PreparedStatement s)
	      throws SQLException, IOException {

	    s.setInt(1, cid);
	    s.setInt(2, postId);
	    s.setString(3, hibernateOrSpring);
	    s.setInt(4, lastActivityDate);
	    s.setInt(5, score);
	    s.setInt(6, answerPostId);
	    s.setInt(7, questionId);
	    s.setInt(8, creationDate);
	    s.setInt(9, questionScore);
	    s.setString(10, sternaFilename);
	    s.executeUpdate();
	 }
	 
	 

	 private static void cleanDuplicates(Connection conn)
			 throws SQLException, IOException {
		 List<Integer> duplicates = getDuplicates(conn);
		 
		 PreparedStatement m = conn.prepareStatement(
				 "DELETE FROM stackOverflowMetaData"
				 + " WHERE cid=?"
				 );
		 PreparedStatement c = conn.prepareStatement(
				 "DELETE FROM codeFragments "
				 + " WHERE cid=?");

		 PreparedStatement a = conn.prepareStatement(
				 "DELETE FROM codeFragmentParsingAttributes "
				 + " WHERE cid=?");
		 
		 System.out.println("Deleting " + duplicates.size() + " duplicates");
		 
		 for( Integer duplicateCid : duplicates ) {
			 m.setInt(1, duplicateCid);
			 m.executeUpdate();
			 
			 c.setInt(1, duplicateCid);
			 c.executeUpdate();
			 
			 a.setInt(1, duplicateCid);
			 a.executeUpdate();
		 }
		 m.close();
		 c.close();
	 }
	 
	 private static List<Integer> getDuplicates(Connection conn)
			 throws SQLException, IOException {
		 List<Integer> duplicates = new ArrayList<Integer>();
		 PreparedStatement s = conn.prepareStatement(
				 "SELECT MIN(M.cid) "
				+ " FROM stackOverflowMetaData M, codeFragments C "
				+ " WHERE M.cid=C.cid "
				+ " GROUP BY postId, sternaFile, code "
				+ " HAVING count(*) >1");
		 ResultSet r = s.executeQuery();
		 while( r.next() ) {
			 duplicates.add(r.getInt(1));
		 }
		 r.close();
		 s.close();
		 return duplicates;
	 }
	
}
