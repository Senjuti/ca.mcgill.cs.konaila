package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public class DbAndroidUnit {
	
	public static String selectCodeFromUnit(Connection conn, int cid, int lineStart, int lineEnd) throws SQLException, IOException, Exception {
		String code = "";
		PreparedStatement s = conn
				.prepareStatement("SELECT * FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid "
						+ " AND S.cid=? AND F.lineStart=? AND F.lineEnd=?"
						+ " AND F.elementOrderInUnit=1");
		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);;
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			code = r.getString("code");
		}
		return code;
	}
	
	public static String selectCodeFromUnit(Connection conn, int cid, int lineStart, int lineEnd, String antlrNodeType) throws SQLException, IOException, Exception {
		String code = "";
		PreparedStatement s = conn
				.prepareStatement("SELECT * FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid "
						+ " AND S.cid=? AND F.lineStart=? AND F. lineEnd=? AND S.antlrNodeType like ?"
						+ " AND F.elementOrderInUnit=1");		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);
		s.setString(4, antlrNodeType);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			code = r.getString("code");
		}
		return code;
	}
	public static String selectCodeDisplayWholeFromUnit(Connection conn, int cid, int lineStart, int lineEnd, String antlrNodeType) throws SQLException, IOException, Exception {
		String code = "";
		PreparedStatement s = conn
				.prepareStatement("SELECT * FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid "
						+ " AND S.cid=? AND F.lineStart=? AND F.lineEnd=? AND S.antlrNodeType like ?"
						+ " AND F.elementOrderInUnit=1");		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);
		s.setString(4, antlrNodeType);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			code = r.getString("codeDisplayWhole");
		}
		return code;
	}
	
	
	public static String selectCodeFromSecondElement(Connection conn, int cid, int lineStart, int lineEnd) throws SQLException, IOException, Exception {
		String code = "";
		PreparedStatement s = conn
				.prepareStatement("SELECT *  FROM selectionUnits as S, elements as Sec "
						+ " WHERE S.cid=Sec.cid AND S.uid=Sec.uid "
						+ " AND S.cid=? AND Sec.lineStart=? AND Sec.lineEnd=?"
						+ " AND Sec.elementOrderInUnit=2");
		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);;
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			code = r.getString("code");
		}
		return code;
	}
		
	public static String selectAntlrNodeType(Connection conn, int cid, int lineStart, int lineEnd) throws SQLException, IOException, Exception {
		String nodetype = "";
		PreparedStatement s = conn
				.prepareStatement("SELECT * FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid "
						+ " AND S.cid=? AND F.lineStart=? AND F.lineEnd=?"
						+ " AND F.elementOrderInUnit=1");
		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);;
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			nodetype = r.getString("antlrNodeType");
		}
		return nodetype;
	}
	
	public static String selectAntlrNodeType(Connection conn, int cid, int uid) throws SQLException, IOException, Exception {
		String code = "";
		PreparedStatement s = conn
				.prepareStatement("SELECT * FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid "
						+ " AND S.cid=? AND F.uid=?"
						+ " AND F.elementOrderInUnit=1");
		
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			code = r.getString("antlrNodeType");
		}
		return code;
	}

	public static int selectLineCharStart(Connection conn, int cid, int lineStart, int lineEnd) throws SQLException, IOException, Exception {
		int lineCharStart=-1;
		PreparedStatement s = conn
				.prepareStatement("SELECT * FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid "
						+ " AND S.cid=? AND F.lineStart=? AND F.lineEnd=?"
						+ " AND F.elementOrderInUnit=1");
		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);;
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			lineCharStart = r.getInt("lineCharStart");
		}
		return lineCharStart;
	}
	
	public static int selectLineCharEnd(Connection conn, int cid, int lineStart, int lineEnd) throws SQLException, IOException, Exception {
		int lineCharEnd=-1;
		PreparedStatement s = conn
				.prepareStatement("SELECT * FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid "
						+ " AND S.cid=? AND F.lineStart=? AND F.lineEnd=?"
						+ " AND F.elementOrderInUnit=1");
		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);;
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			lineCharEnd = r.getInt("lineCharEnd");
		}
		return lineCharEnd;
	}
	
	public static int selectUid(Connection conn, int cid, int lineStart, int lineEnd, String antlrNodeType) throws SQLException, IOException, Exception {
		int uid=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT S.uid FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid AND S.cid=? "
						+ " AND F.lineStart=? AND F.lineEnd=? "
						+ " AND S.antlrNodeType=?"
						+ " AND F.elementOrderInUnit=1");

		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);
		s.setString(4, antlrNodeType);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			uid = r.getInt(1);
		}
		return uid;
	}

	public static String selectCodeOfSecondElement(Connection conn, int cid, int uid) throws SQLException, IOException, Exception {
		String code="";
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT code FROM elements as S WHERE cid=? AND uid=?"
								+ " AND S.elementOrderInUnit=2");				

		
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			code = r.getString("code");
		}
		return code;
	}
	
	public static int selectCharStartOfSecondElement(Connection conn, int cid, int uid) throws SQLException, IOException, Exception {
		int charStart=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT charStart FROM elements as S WHERE cid=? AND uid=?"
								+ " AND S.elementOrderInUnit=2");				

		
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			charStart = r.getInt("charStart");
		}
		return charStart;
	}
	
	public static int selectLineCharStartOfSecondElement(Connection conn, int cid, int uid) throws SQLException, IOException, Exception {
		int charStart=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT lineCharStart FROM elements as S WHERE cid=? AND uid=?"
								+ " AND S.elementOrderInUnit=2");				

		
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			charStart = r.getInt("lineCharStart");
		}
		return charStart;
	}
	
	public static int selectCharEndOfSecondElement(Connection conn, int cid, int uid) throws SQLException, IOException, Exception {
		int charEnd=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT charEnd FROM elements as S WHERE cid=? AND uid=?"
								+ " AND S.elementOrderInUnit=2");				

		
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			charEnd = r.getInt("charEnd");
		}
		return charEnd;
	}
	
	public static int selectLineCharEndOfSecondElement(Connection conn, int cid, int uid) throws SQLException, IOException, Exception {
		int charEnd=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT lineCharEnd FROM elements as S WHERE cid=? AND uid=?"
								+ " AND S.elementOrderInUnit=2");				

		
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			charEnd = r.getInt("lineCharEnd");
		}
		return charEnd;
	}
	
	public static int selectCharStart(Connection conn, int cid, int lineStart, int lineEnd, String antlrNodeType) throws SQLException, IOException, Exception {
		int charStart=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT charStart FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid AND S.cid=? AND F.lineStart=? AND F.lineEnd=? "
						+ " AND S.antlrNodeType=?"
						+ " AND F.elementOrderInUnit=1");
		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);
		s.setString(4, antlrNodeType);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			charStart = r.getInt(1);
		}
		return charStart;
	}
	
	public static int selectCharEnd(Connection conn, int cid, int lineStart, int lineEnd, String antlrNodeType) throws SQLException, IOException, Exception {
		int charEnd=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT charEnd FROM selectionUnits as S, elements as F "
						+ " WHERE S.cid=F.cid AND S.uid=F.uid AND S.cid=? AND F.lineStart=? AND F.lineEnd=? "
						+ " AND S.antlrNodeType=?"
						+ " AND F.elementOrderInUnit=1");
		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3,  lineEnd);
		s.setString(4, antlrNodeType);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			charEnd = r.getInt(1);
		}
		return charEnd;
	}
	

	public static int selectEnclosingUid(Connection conn, int cid, int uid) throws SQLException, IOException, Exception {
		int enlcosingUnitUid=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT enclosingUnitUid FROM selectionUnits as S WHERE S.cid=? AND S.uid=?");
		
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			enlcosingUnitUid = r.getInt(1);
		}
		return enlcosingUnitUid;
	}

	public static int selectNumberNodeTypes(Connection conn, int cid) throws SQLException, IOException, Exception {
		int count=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT count(*) FROM selectionUnit as U "
						+ " WHERE U.cid=?");		
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			count = r.getInt(1);
		}
		return count;
	}
	
	public static int selectNumberNodeTypes(Connection conn, int cid, int lineStart, int lineEnd, String antlrNodeType) throws SQLException, IOException, Exception {
		int count=-1;
		PreparedStatement s = conn
			.prepareStatement("SELECT  count(*) "
					+ " FROM selectionUnits as S, elements as F"
				+ " WHERE S.cid=F.cid AND S.uid=F.uid "
				+ " AND S.cid=? AND F.lineStart=? AND F.lineEnd=? AND S.antlrNodeType=?"
				+ " AND F.elementOrderInUnit=1");
		s.setInt(1, cid);
		s.setInt(2,  lineStart);
		s.setInt(3,  lineEnd);
		s.setString(4,  antlrNodeType);
		
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			count = r.getInt(1);
		}
		return count;
	}

	public static String selectCodeFragment(Connection conn, int cid) throws SQLException, IOException, Exception {
		String code="";
		PreparedStatement s = conn
			.prepareStatement("SELECT code FROM codeFragments WHERE cid=?");
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			code = r.getString(1);
		}
		return code;
	}
	
	public static Pair<Integer, Integer> selectUnitCharStartEnd(Connection conn, int cid, int uid) throws SQLException, IOException, Exception {
		ImmutablePair<Integer,Integer> pair = null;
		PreparedStatement s = conn
			.prepareStatement("SELECT unitCharStart, unitCharEnd "
					+ " FROM selectionUnits as S, codeFragments as C "
					+ " WHERE C.cid=? AND C.cid=S.cid AND uid=?");
		s.setInt(1, cid);
		s.setInt(2, uid);
		
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			int unitCharStart = r.getInt(1);
			int unitCharEnd = r.getInt(2);
			 pair = new ImmutablePair<Integer,Integer>(unitCharStart, unitCharEnd);
		}
		return pair;
	}
	
}
