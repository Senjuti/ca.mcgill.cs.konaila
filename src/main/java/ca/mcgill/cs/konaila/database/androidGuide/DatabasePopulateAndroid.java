package ca.mcgill.cs.konaila.database.androidGuide;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import ca.mcgill.cs.konaila.database.Database;

public class DatabasePopulateAndroid {
	
	private static final String dir = "./database/";
	public static final String DB = "jdbc:sqlite:database/codeFragments.sqlite";
	public static final int cidCounterStart = 0;
	public static final String source = "android-guide";
	
	
	public static void main(String[] args) throws Exception {
		Database db = Database.getInstance();
		db.initConnection(DB);
		db.getConnection().setAutoCommit(false);
		populate(db.getConnection());	
		db.getConnection().commit();		
		db.closeConnection();
	}
	
	public Collection<AndroidCodeFragment> getAllCodeFragments(Connection conn) throws SQLException, IOException {
		
		String statement = "select cid, internalCid, source, url, code " 
				+ " from codeFragments ";
		Collection<AndroidCodeFragment> result = new ArrayList<AndroidCodeFragment>();

		PreparedStatement stat = conn.prepareStatement(statement);
		ResultSet rs = stat.executeQuery();
		
		while (rs.next()) {
			AndroidCodeFragment c = new AndroidCodeFragment();
			c.setContent( rs.getString("code") );
			c.setId( rs.getInt("cid") );
			result.add(c);
		}
		rs.close();
		stat.close();
		return result;
	}
	
	public static void populate(Connection conn) throws SQLException, IOException {
		Collection<AndroidCodeFragment> codeFragments = LoadAndroidGuideFromStudy.getAndroidGuide();
		
		conn.setAutoCommit(false);
		
		PreparedStatement codeFragmentsStat = conn
				.prepareStatement("insert into codeFragments values(?,?,?,?,?,?);");
		PreparedStatement codeFragmentsAttributeStat = conn
				.prepareStatement("insert into codeFragmentParsingAttributes values(?,?);");
	
		for ( AndroidCodeFragment c : codeFragments ) {
				
			System.out.println("Processing " + c.getInternalId());
							
			int cid = c.getInternalId() + cidCounterStart;
			loadCodeFragment(cid, c.getInternalId(), "android-guide", "",  c.getContent(),
					c.getParseStructure(), c.getParseProblem(), c.getQuery(),
					codeFragmentsStat, codeFragmentsAttributeStat);					
		}		
		codeFragmentsStat.close();
		codeFragmentsAttributeStat.close();		

		conn.commit();
	}
	
	private static void loadCodeFragment(int cid, int internalCid, String source, String url, String code,
			String structure, String problem, String query,
			PreparedStatement codeFragmentsStat, PreparedStatement codeFragmentAttributeStat)
			throws SQLException, IOException {

		codeFragmentsStat.setInt(1, cid);
		codeFragmentsStat.setInt(2, internalCid);
		codeFragmentsStat.setString(3, source);
		codeFragmentsStat.setString(4, url);
		codeFragmentsStat.setString(5, code);
		codeFragmentsStat.setString(6, query);
		codeFragmentsStat.executeUpdate();
		
//		codeFragmentAttributeStat.setInt(1, cid);
//		codeFragmentAttributeStat.setString(2, structure);
//		codeFragmentAttributeStat.executeUpdate();
		
//		codeFragmentAttributeStat.setInt(1, cid);
//		codeFragmentAttributeStat.setInt(2,  internalCid);
//		codeFragmentAttributeStat.setString(3, "problem");
//		codeFragmentAttributeStat.setString(4, problem);
//		codeFragmentAttributeStat.executeUpdate();
	}
	
}
