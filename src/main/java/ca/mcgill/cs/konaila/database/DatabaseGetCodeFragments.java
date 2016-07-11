package ca.mcgill.cs.konaila.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ca.mcgill.cs.konaila.parse.CodeFragmentPrerequisite;

public class DatabaseGetCodeFragments {
	
	public static Map<Integer,String> getCodeFragments(Connection conn, String source) throws SQLException, IOException {		
		Map<Integer,String> cidToCode = new HashMap<Integer,String>();
		
        PreparedStatement s = conn
                .prepareStatement(
                                  "SELECT cid, code "
                                  + " FROM codeFragments "
                                  + " WHERE source like ?");			
		s.setString(1, source);   
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			int cid = r.getInt(1);
			String code = r.getString(2);
			cidToCode.put(cid,code);
		}
		r.close();
		s.close();
		return cidToCode;		
	}
	
	public static String getQuery(Connection conn, int cid) throws SQLException, IOException {		
		String query = "";
		
        PreparedStatement s = conn
                .prepareStatement("SELECT query "
                                  + " FROM codeFragments "
                                  + " WHERE cid=?");			
		s.setInt(1, cid);   
		ResultSet r = s.executeQuery();			
		
		if( r.next() ) {
			query = r.getString(1);
		}
		
		r.close();
		s.close();
		return query;
	}

	public static Map<Integer,String> getQueries(Connection conn, String source) throws SQLException, IOException {		
		String query = "";
		int cid = -1;
		Map<Integer,String> result = new HashMap<Integer,String>();
		
        PreparedStatement s = conn.prepareStatement("SELECT cid, query "
                                  + " FROM codeFragments WHERE source like ?");			
        s.setString(1, source);
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			cid = r.getInt(1);
			query = r.getString(2);
			result.put(cid,query);
		}
		return result;
	}
	
	public static String getCodeFragment(Connection conn, int cid) throws SQLException, IOException {		
		String code = "";
		
        PreparedStatement s = conn
                .prepareStatement("SELECT code "
                                  + " FROM codeFragments "
                                  + " WHERE cid=?");			
		s.setInt(1, cid);   
		ResultSet r = s.executeQuery();			
		
		if( r.next() ) {
			code = r.getString(1);
		}
		
		r.close();
		s.close();
		return code;
	}
	
	public static Map<Integer,String> getJavaCodeFragments(Connection conn, String source) throws SQLException, IOException {		
		Map<Integer,String> cidToCode = new HashMap<Integer,String>();
		
        PreparedStatement s = conn
                .prepareStatement("SELECT C.cid, C.code, A.comment "
                		+ " FROM codeFragments C, codeFragmentParsingAttributes A "
                		+ " WHERE source like ? "
                		+ " AND C.cid=A.cid AND A.comment LIKE 'java%'");			
		s.setString(1, source);   
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			int cid = r.getInt(1);
			String code = r.getString(2);
			cidToCode.put(cid,code);
		}
		r.close();
		s.close();
		return cidToCode;		
	}

	public static Map<Integer,String> getJavaCodeFragmentsCidsLongEnoughMap(Connection conn)
			throws SQLException, IOException {		
		Map<Integer,String> cids = new HashMap<Integer,String>();
		
        PreparedStatement s = conn
                .prepareStatement("SELECT C.cid, C.code, A.comment "
                		+ " FROM codeFragments C, codeFragmentParsingAttributes A  "
                		+ " WHERE C.cid=A.cid AND A.comment LIKE 'Java%' "
                		+ " AND C.cid IN (SELECT DISTINCT cid FROM selectionUnits S)");			
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			String code = r.getString(2);
			if( CodeFragmentPrerequisite.isLongEnough(code)) {
				int cid = r.getInt(1);			
				cids.put(cid,code);
			}
		}
		r.close();
		s.close();
		return cids;		
	}
	
	public static List<Integer> getJavaCodeFragmentsCidsLongEnough(Connection conn) throws SQLException, IOException {		
		List<Integer> cids = new ArrayList<Integer>();
		
        PreparedStatement s = conn
                .prepareStatement("SELECT C.cid, C.code, A.comment "
                		+ " FROM codeFragments C, codeFragmentParsingAttributes A "
                		+ " WHERE C.cid=A.cid AND A.comment LIKE 'Java%' "
                		+ " AND C.cid IN (SELECT DISTINCT cid FROM selectionUnits S)");			
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			String code = r.getString(2);
			if( CodeFragmentPrerequisite.isLongEnough(code)) {
				int cid = r.getInt(1);			
				cids.add(cid);
			}
		}
		r.close();
		s.close();
		return cids;		
	}
	
	public static List<Integer> getJavaCodeFragmentsCidsLongEnough(Connection conn,
			String source) throws SQLException, IOException {		
		List<Integer> cids = new ArrayList<Integer>();
		
        PreparedStatement s = conn
                .prepareStatement("SELECT C.cid, C.code, A.comment "
                		+ " FROM codeFragments C, codeFragmentParsingAttributes A "
                		+ " WHERE C.cid==A.cid AND A.comment LIKE 'Java%' AND C.source=?"
                		+ " ' AND C.cid IN (SELECT DISTINCT cid FROM selectionUnits S)");
        s.setString(1, source);
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			String code = r.getString(2);
			if( CodeFragmentPrerequisite.isLongEnough(code)) {
				int cid = r.getInt(1);			
				cids.add(cid);
			}
		}
		r.close();
		s.close();
		return cids;		
	}

	public static List<Integer> getCids(Connection conn) throws SQLException, IOException {		
		List<Integer> cids = new ArrayList<Integer>();
        PreparedStatement s = conn.prepareStatement("SELECT DISTINCT cid "
                + " FROM codeFragments ");

		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			cids.add(r.getInt(1));
		}
		return cids;
	}
	
	public static int getNumberOfCids(Connection conn, String source) throws SQLException, IOException {		
        PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT cid) "
                + " FROM codeFragments "
                + " WHERE source LIKE ? ");
        
        s.setString(1, source);
        
        int number = -1;
		ResultSet r = s.executeQuery();	
		if( r.next() ) {
			number = r.getInt(1);
		}
		return number;
	}
	
	public static List<Integer> getCids(Connection conn, String source) throws SQLException, IOException {		
        PreparedStatement s = conn.prepareStatement("SELECT DISTINCT cid "
                + " FROM codeFragments "
                + " WHERE source LIKE ? ");
        
        s.setString(1, source);
        
        List<Integer> cids = new ArrayList<Integer>();
		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			cids.add(r.getInt(1));
		}
		return cids;
	}
	
	public static String getSource(Connection conn, int cid) throws SQLException, IOException {		
		String source = "";
		
        PreparedStatement s = conn
                .prepareStatement(
                                  "SELECT source "
                                  + " FROM codeFragments "
                                  + " WHERE cid like ?");			
		s.setInt(1, cid);   
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			source = r.getString(1);
		}
		r.close();
		s.close();
		return source;		
	}
	
	public static void updateCode(Connection conn, int cid, String newCode)
			throws SQLException, IOException {	
		
        PreparedStatement s = conn
                .prepareStatement(
                                  "UPDATE codeFragments "
                                  + " SET code = ? "
                                  + " WHERE cid = ?");
		s.setString(1, newCode); 
		s.setInt(2, cid);   
		s.executeUpdate();
		s.close();
	}
	
	public static int getMaxCid(Connection conn) throws SQLException, IOException {	
		
        PreparedStatement s = conn
                .prepareStatement(
                                  "SELECT max(cid) "
                                  + " FROM codeFragments ");
        int max = -1;
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			max = r.getInt(1);
		}
		r.close();
		s.close();
		return max;
	}
}
