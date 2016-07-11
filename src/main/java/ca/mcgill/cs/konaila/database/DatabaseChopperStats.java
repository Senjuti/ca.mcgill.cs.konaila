package ca.mcgill.cs.konaila.database;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.chopper.SelectionUnit.HoleElement;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class DatabaseChopperStats {
	
	public static Map<String,Integer> getTypeCounts(Connection conn, String source) throws SQLException, IOException {		
		Map<String,Integer> typeToCount = new HashMap<String,Integer>();
		
        PreparedStatement s = conn
                .prepareStatement("SELECT S.konailaNodeType, count(*) "
                		+ " FROM selectionUnits S, codeFragments C "
                		+ " WHERE S.cid=C.cid AND C.source LIKE ? "
                		+ " GROUP BY S.konailaNodeType ");		   
        
        s.setString(1, source);
        
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			String type = r.getString(1);
			Integer count = r.getInt(2);
			typeToCount.put(type,count);
		}
		r.close();
		s.close();
		
		return typeToCount;		
	}

	public static void getSelectionUnitLength(Connection conn, String source, File output) throws SQLException, IOException {		
	
        PreparedStatement s = conn
                .prepareStatement("SELECT S.cid, S.uid, sum(length(E.code)), length(S.codeDisplayWhole), "
                		+ " GROUP_CONCAT(E.code), S.unitLineStart, S.unitLineEnd, S.konailaNodeType "
                		+ " FROM elements E, codeFragments C, selectionUnits S "
                		+ " WHERE S.cid=C.cid AND S.uid=E.uid AND S.cid=E.cid "
                		+ " AND C.source LIKE ? "
                		+ " GROUP BY S.cid, S.uid");		   
        
        s.setString(1, source);
        
		ResultSet r = s.executeQuery();			
		
		PrintWriter out = new PrintWriter(output);
		out.println("cid\tuid\tlength\tnewLines\tunitLineStart\tunitLineEnd\tkonailaNodeType");
		
		while( r.next() ) {
			int cid = r.getInt(1);
			int uid = r.getInt(2);
			String code = r.getString(5);
			int length = r.getInt(3);
			int newLines = length - code.replace("\n", "").length();
			int unitLineStart = r.getInt(6);
			int unitLineEnd = r.getInt(7);
			String type = r.getString(8);
			out.println(cid + "\t" + uid + "\t" + length + "\t" + newLines + "\t" 
					+ unitLineStart + "\t" + unitLineEnd + "\t" + type);
		}
		r.close();
		s.close();
		out.close();
	}
	
	public static void getLineLength(Connection conn, String source, File output) throws SQLException, IOException {		
		
        PreparedStatement s = conn
                .prepareStatement("SELECT C.code, C.cid "
                		+ " FROM codeFragments C "
                		+ " WHERE C.source LIKE ? ");		   
        
        s.setString(1, source);
        
		ResultSet r = s.executeQuery();			
		
		PrintWriter out = new PrintWriter(output);
		out.println("cid\tline\tlength");
		
		while( r.next() ) {
			int cid = r.getInt(2);
			String code = r.getString(1);
						
			String[] lines = code.split("\n");
			for( int i = 0; i < lines.length; i++ ) {
				int length = lines[i].length();
				int lineNumber = i+1;
				out.println(cid + "\t" + lineNumber + "\t" + length );
			}
		}
		r.close();
		s.close();
		out.close();
	}
	
	public static int getNumberOfCids(Connection conn, String source) throws SQLException, IOException {		
		int number = -1;
        PreparedStatement s = conn.prepareStatement(
        		"SELECT count(DISTINCT S.cid) "
        		+ " FROM selectionUnits S, codeFragments C "
        		+ " WHERE S.cid=C.cid"
        		+ " AND C.source LIKE ?");

        s.setString(1, source);
        
		ResultSet r = s.executeQuery();	
		if( r.next() ) {
			number = r.getInt(1);
		}
		r.close();
		return number;
	}

	public static List<Integer> getCids(Connection conn, String source) throws SQLException, IOException {		
		List<Integer> cids = new ArrayList<Integer>();
        PreparedStatement s = conn.prepareStatement(
        		"SELECT DISTINCT S.cid "
        		+ " FROM selectionUnits S, codeFragments C "
        		+ " WHERE S.cid=C.cid"
        		+ " AND C.source LIKE ?");

        s.setString(1, source);
        
		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			cids.add(r.getInt(1));
		}
		r.close();
		return cids;
	}
	
	public static List<Integer> getCodeFragmentsWithProblemWithGeneratingSelectionUnits(Connection conn, String source) throws SQLException, IOException {		
		List<Integer> cids = new ArrayList<Integer>();
        PreparedStatement s = conn.prepareStatement(
        		"SELECT DISTINCT * "
        		+ " FROM codeFragments C "
        		+ " WHERE NOT EXISTS (SELECT cid FROM selectionUnits S WHERE C.cid=S.cid)");
        
		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			cids.add(r.getInt(1));
		}
		r.close();
		return cids;
	}

//	public static void createTable(Connection conn) throws SQLException, IOException {
//		
//		 PreparedStatement d = conn.prepareStatement(
//					"DROP TABLE IF EXISTS selectionUnitStats ");
//		 d.executeUpdate();
//		 
//		 PreparedStatement s = conn.prepareStatement(
//					"CREATE TABLE selectionUnitStats (cid INTEGER, uid INTEGER, "
//					+ " codeDisplayWhole, konailaNodeType, enclosingUnitType, "
//					+ " unitLineStart INTEGER, unitLineEnd INTEGER, "
//					+ " choppedInLines INTEGER, choppedInChars INTEGER, "
//					+ " unchoppedInLines INTEGER, unchoppedInChars INTEGER, "
//					+ " FOREIGN KEY(cid,uid) REFERENCES selectionUnits(cid,uid))");
//		 s.executeUpdate();
//
//	}	
	
	public static void populateStats(Connection conn, String source) throws SQLException, IOException {
		statsFirstPass(conn, source);
		statsSecondPass(conn, source);
		statsThirdPass(conn, source);
	}
	
	public static void statsFirstPass(Connection conn, String source) throws SQLException, IOException {

		conn.setAutoCommit(false);	
		
        PreparedStatement s = conn.prepareStatement(
			"INSERT INTO selectionUnitStats (cid,uid,codeDisplayWhole,konailaNodeType,"
			+ "enclosingUnitType,unitLineStart,unitLineEnd,"
			+ "choppedInLines,choppedInChars,unchoppedInLines,unchoppedInChars) "
				+ " SELECT S.cid, S.uid, "
				+ " S.codeDisplayWhole, S.konailaNodeType, '', S.unitLineStart, S.unitLineEnd, "
				+ " 0 as choppedInLines, " 
				+ " sum(length(E.code)) as choppedInChars,"
				+ " S.unitLineEnd - S.unitLineStart + 1 as unchoppedInLines,"
				+ " S.unitCharEnd - S.unitCharStart as unchoppedInChars "
				+ "		FROM elements E, codeFragments C, selectionUnits S "
				+ "		WHERE S.cid=C.cid AND S.uid=E.uid AND S.cid=E.cid "
				+ "     AND C.source=? "
				+ "		GROUP BY S.cid, S.uid ");
        s.setString(1,source);
        s.executeUpdate();
		s.close();

		conn.commit();	
	}
	
	public static void statsSecondPass(Connection conn, String source) throws SQLException, IOException {
		
		PreparedStatement s = conn
				.prepareStatement("SELECT S.cid, S.uid,  E.elementOrderInUnit, "
						+ " E.lineStart, E.lineEnd, "
						+ " E.code, S.codeDisplayWhole, S.konailaNodeType "
						+ " FROM elements E, codeFragments C, selectionUnits S "
						+ " WHERE S.cid=C.cid AND S.uid=E.uid AND S.cid=E.cid "
						+ " AND C.source=?");
		s.setString(1, source);
		
        PreparedStatement u = conn.prepareStatement(
						"UPDATE selectionUnitStats "
						+ " SET choppedInLines=? "
						+ " WHERE cid=? AND uid=?");
        
        Map<String,Set<Integer>> cidUidToLines = new HashMap<String,Set<Integer>>();
        
		ResultSet r = s.executeQuery();
		while(r.next()) {
			int cid = r.getInt(1);
			int uid = r.getInt(2);
			int lineStart = r.getInt(4);
			int lineEnd = r.getInt(5);
			String key = cid +"," + uid;
			Set<Integer> lines = cidUidToLines.get(key);
			if( lines == null ) {
				lines = new HashSet<Integer>();
				cidUidToLines.put(key,lines);
			}
			
			for( int i = lineStart; i <= lineEnd; i++) {
				lines.add(i);
			}		
		}		

		conn.setAutoCommit(false);	
		
		for( Entry<String,Set<Integer>> e : cidUidToLines.entrySet()) {
			int cid = Integer.parseInt(e.getKey().split(",")[0]);
			int uid = Integer.parseInt(e.getKey().split(",")[1]);
			Set<Integer> lines = e.getValue();
					
			u.setInt(1, lines.size());
			u.setInt(2, cid);
			u.setInt(3, uid);
			u.executeUpdate();
		}
		
		u.close();
		s.close();
		r.close();
		conn.commit();	
	}
	
	public static void statsThirdPass(Connection conn, String source) throws SQLException, IOException {	
				
		PreparedStatement s = conn
				.prepareStatement("SELECT S.cid, S.uid, S2.konailaNodeType "
						+ "	FROM codeFragments C, selectionUnits S, selectionUnits S2 "
						+ "	WHERE S.cid=C.cid AND S.cid=S2.cid AND S.enclosingUnitUid=S2.uid "
						+ "	AND S.enclosingUnitUid!=-1 "
						+ " AND C.source=?");
		s.setString(1, source);
		
		ResultSet r = s.executeQuery();
		
        PreparedStatement u = conn.prepareStatement(
						"UPDATE selectionUnitStats "
						+ " SET enclosingUnitType=? "
						+ " WHERE cid=? AND uid=?");
        
		conn.setAutoCommit(false);	
        
        while( r.next() ) {
        	int cid = r.getInt(1);
        	int uid = r.getInt(2);
        	String enclosingNodeType = r.getString(3);
        	
//        	System.out.println("cid="+cid + ",uid="+uid + ",type="+enclosingNodeType);

        	u.setString(1, enclosingNodeType);
        	u.setInt(2, cid);
        	u.setInt(3, uid);
        	u.executeUpdate();        	
        }

        u.close();
		r.close();
		conn.commit();	

	}

	public static void writeToDb(Connection conn, int cid, String justification) throws SQLException, IOException {

		conn.setAutoCommit(false);	
		
		PreparedStatement s = conn
				.prepareStatement("INSERT INTO codeFragmentParsingAttributes VALUES (?,?)");
		s.setInt(1, cid);
		s.setString(2,  justification);
		s.executeUpdate();
		s.close();

		conn.commit();
		
		FileUtils.write(new File("./database/codeFragmentParsingAttributes"), cid + "\t" + justification, true);
	}
	
	public static String getJustification(Connection conn, int cid) throws SQLException, IOException {
		String justification = null;
		PreparedStatement s = conn
				.prepareStatement("SELECT comment FROM codeFragmentParsingAttributes A "
						+ " WHERE A.cid=? ");
		s.setInt(1, cid);
		s.executeQuery();
		
		ResultSet r = s.executeQuery();	
		if( r.next() ) {
			justification = r.getString(1);
		}
		r.close();
		s.close();
		
		return justification;
	}

	
	public static List<Integer> getCids(Connection conn) throws SQLException, IOException {		
		List<Integer> cids = new ArrayList<Integer>();
        PreparedStatement s = conn.prepareStatement("SELECT DISTINCT cid "
                + " FROM codeFragmentParsingAttributes ");

		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			cids.add(r.getInt(1));
		}
		r.close();
		s.close();
		return cids;
	}
	
	
	public static List<Integer> getJavaCids(Connection conn) throws SQLException, IOException {		
		List<Integer> cids = new ArrayList<Integer>();
        PreparedStatement s = conn.prepareStatement("SELECT DISTINCT cid "
                + " FROM codeFragmentParsingAttributes "
                + " WHERE comment LIKE 'Java%'");

		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			cids.add(r.getInt(1));
		}
		r.close();
		s.close();
		return cids;
	}

	public static List<Integer> getCidsWithSelectionUnits(Connection conn) throws SQLException, IOException {		
		List<Integer> cids = new ArrayList<Integer>();
        PreparedStatement s = conn.prepareStatement("SELECT DISTINCT cid "
                + " FROM selectionUnits ");

		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			cids.add(r.getInt(1));
		}
		r.close();
		s.close();
		return cids;
	}

	public static List<String> getKonailaNodeTypes(Connection conn) throws SQLException, IOException {		
		List<String> types = new ArrayList<String>();
        PreparedStatement s = conn.prepareStatement("SELECT DISTINCT konailaNodeType "
                + " FROM selectionUnits ");

		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			types.add(r.getString(1));
		}

		s.close();
		return types;
	}
	
	public static Map<Integer, Integer> getUidsToLineLengths(Connection conn, int cid) throws SQLException, IOException {		
		Map<Integer,Integer> uidsToLineLengths = new HashMap<Integer,Integer>();
        PreparedStatement s = conn.prepareStatement(
        			"SELECT uid, choppedInLines "
                + " FROM selectionUnitStats"
                + " WHERE cid = ? ");

        s.setInt(1, cid);
        
		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			int uid = r.getInt(1);
			int lines = r.getInt(2);
			uidsToLineLengths.put(uid, lines);
		}
		r.close();
		s.close();
		return uidsToLineLengths;
	}
	
	public static Map<Integer, Integer> getUidsToCharPerLengths(Connection conn, 
			int cid) throws SQLException, IOException {		
		Map<Integer,Integer> uidsToLineLengths = new HashMap<Integer,Integer>();
        PreparedStatement s = conn.prepareStatement(
        			"SELECT uid,  "
                + " FROM selectionUnits"
                + " WHERE cid = ? ");

        s.setInt(1, cid);
        
		ResultSet r = s.executeQuery();	
		while( r.next() ) {
			int uid = r.getInt(1);
			int lines = r.getInt(2);
			uidsToLineLengths.put(uid, lines);
		}
		r.close();
		s.close();
		return uidsToLineLengths;
	}
	
	
	public static int getTotalNumberOfLines(Connection conn, int cid) throws SQLException, IOException {		
		int totalLines = -1;
        PreparedStatement s = conn.prepareStatement(
        			"SELECT max(unitLineEnd) "
                + " FROM selectionUnits"
                + " WHERE cid = ? ");

        s.setInt(1, cid);
                
		ResultSet r = s.executeQuery();	
		if( r.next() ) {
			totalLines = r.getInt(1);
		}
		r.close();
		s.close();
		return totalLines;
	}

	public static Map<Integer,String> selectSelectionUnitCode(Connection conn, 
			int cid) throws SQLException, IOException {		
		Map<Integer,String> uidToCode = new HashMap<Integer,String>();
		
        PreparedStatement s = conn
                .prepareStatement("SELECT S.uid, S.codeDisplayWhole "
                                  + " FROM selectionUnits S "
                                  + " WHERE S.cid = ?");        
        s.setInt(1, cid);
        
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			int uid = r.getInt(1);
			String codeDisplayWhole = r.getString(2);
			
			String code = codeDisplayWhole.replace(HoleElement.HOLE, "");
			
			uidToCode.put(uid,code);
		}
		r.close();
		s.close();
		return uidToCode;		
	}
	
}
