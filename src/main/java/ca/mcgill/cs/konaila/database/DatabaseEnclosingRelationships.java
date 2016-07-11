package ca.mcgill.cs.konaila.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseEnclosingRelationships {
	
	public static class EnclosingUnits {
		int cid;
		int uid;
		int betweenStart;
		int betweenEnd;
		String codeDisplayWhole;
		EnclosingUnits( int cid, int uid, int betweenStart, int betweenEnd, String codeDisplayWhole) {
			this.cid=cid;
			this.uid=uid;
			this.betweenStart=betweenStart;
			this.betweenEnd = betweenEnd;
			this.codeDisplayWhole = codeDisplayWhole;
		}
		public int getCid() { return cid; }
		public int getUid() { return uid; }
	}
	
	public static class CallUnit {
		int cid;
		int uid;
		int charStart;
		int charEnd;
		public CallUnit( int cid, int uid, int charStart, int charEnd) {
			this.cid=cid;
			this.uid=uid;
			this.charStart=charStart;
			this.charEnd=charEnd;
		}
		public int getCid() { return cid; }
		public int getUid() { return uid; }
		public int getCharStart() { return charStart; }
		public int getCharEnd() { return charEnd; }
		@Override
		public String toString() {
			return "(" + charStart + "," + charEnd + ")";
		}
	}
	
	public static void populateEnclosingRelationships(Connection conn)  throws SQLException, IOException {

		conn.setAutoCommit(false);
		
		PreparedStatement s = conn
				.prepareStatement("UPDATE selectionUnits "
						+ " SET enclosingUnitUid=? "
						+ " WHERE cid=? AND uid=?");
	
		List<EnclosingUnits> enclosingUnits = selectCidUidsOfEclosingUnits(conn);
		for( EnclosingUnits unit : enclosingUnits) {
			List<Integer> unitsEnclosed = selectUidsEnclosed(conn, unit.cid, unit.betweenStart, unit.betweenEnd);
			for( Integer uid : unitsEnclosed) {
				int enclosingUnitUid = unit.uid;
				int cid = unit.cid;
				
				s.setInt(1, enclosingUnitUid);
				s.setInt(2, cid);
				s.setInt(3, uid);
				s.executeUpdate();	
			}
		}
		s.close();
		
		conn.commit();
	}

	public static void populateEnclosingRelationshipsForCallUnits(Connection conn)  throws SQLException, IOException {
	
		PreparedStatement s = conn
				.prepareStatement("UPDATE selectionUnits "
						+ " SET enclosingUnitUid=? "
						+ " WHERE cid=? AND uid=?");
		
		List<CallUnit> unitsEnclosed = selectCidUidsOfCallUnits(conn);
		for( CallUnit unit : unitsEnclosed) {	
			int cid = unit.cid;
			int uid = unit.uid;
			
			int enclosingUnitUid = selectEnclosingThroughCorrespondingHole(conn, unit);
			
			s.setInt(1, enclosingUnitUid);
			s.setInt(2, cid);
			s.setInt(3, uid);
			s.executeUpdate();			
		}
		
		s.close();
	}
	
//	public static void populateEnclosingRelationshipsForCallUnits(Connection conn)  throws SQLException, IOException {
//		
//		PreparedStatement s = conn
//				.prepareStatement("UPDATE selectionUnits "
//						+ " SET enclosingUnitUid=? "
//						+ " WHERE cid=? AND uid=?");
//		
//		List<CallUnit> unitsEnclosed = selectCidUidsOfCallUnits(conn);
//		for( CallUnit unit : unitsEnclosed) {
//			int enclosingUnitUid = selectEnclosingSimpleStatement(conn, unit.cid, 
//					unit.getCharStart(), unit.getCharEnd());
//
//			int cid = unit.cid;
//			int uid = unit.uid;
//			s.setInt(1, enclosingUnitUid);
//			s.setInt(2, cid);
//			s.setInt(3, uid);
//			s.executeUpdate();			
//		}
//		
//		s.close();
//	}
	
	public static List<Integer> selectUidsEnclosed(Connection conn, int cid, int betweenStart, int betweenEnd) throws SQLException, IOException {
		List<Integer> uids= new ArrayList<Integer>();
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT F.uid FROM elements as F, selectionUnits as U  "
						+ " WHERE F.cid=U.cid AND U.uid=F.uid AND F.cid=? "
						+ " AND charStart >= ? AND charEnd <= ? "
						+ " AND F.elementOrderInUnit = 1 "
						+ " ORDER BY charStart asc");
							
		s.setInt(1, cid);
		s.setInt(2, betweenStart);
		s.setInt(3, betweenEnd);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int uid = r.getInt(1);
			uids.add(uid);
		}
		
		r.close();
		s.close(); 
		return uids;
	}

	
//	public static int selectEnclosingSimpleStatement(Connection conn, int cid, 
//			int charStart, int charEnd) throws SQLException, IOException {
//		int enclosingSimpleStatementUid = -1;
//		int uid = -1;
//		PreparedStatement s = conn
//				.prepareStatement(
//						"SELECT E.cid, E.uid, E.charEnd, E.charStart, U.codeDisplayWhole, U.konailaNodeType, U.uid, U.konailaNodeType "
//						+ " FROM elements E, selectionUnits U "
//						+ " WHERE E.cid=U.cid AND E.uid=U.uid AND U.konailaNodeType LIKE 'SimpleStatement' "
//						+ " AND E.cid=? AND U.unitCharStart<=? AND U.unitCharEnd>=?");
//							
//		s.setInt(1, cid);
//		s.setInt(2, charStart);
//		s.setInt(3, charEnd);
//		ResultSet r = s.executeQuery();
//		
//		if(r.next()) {
//			uid = r.getInt(2);
//			enclosingSimpleStatementUid = uid;
//		}
//		
//		if( r.next() ) {
//			int uid2 = r.getInt(2);
//			int enclosingSimpleStatementUid2 = uid2;
//		}
//		r.close();
//		s.close(); 
//		return enclosingSimpleStatementUid;
//	}
	
	public static List<EnclosingUnits> selectCidUidsOfEclosingUnits(Connection conn) throws SQLException, IOException {
		List<EnclosingUnits> cidUids= new ArrayList<EnclosingUnits>();
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT F.cid, F.uid, F.charEnd, S.charStart, U.codeDisplayWhole "
						+ " FROM elements as F, elements as S, selectionUnits as U "
						+ " WHERE F.cid=S.cid AND F.cid=U.cid "
						+ " AND F.uid=S.uid AND F.uid=U.uid "
						+ " AND F.elementOrderInUnit = 1 "
						+ " AND S.elementOrderInUnit = 2");
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int cid = r.getInt(1);
			int uid = r.getInt(2);
			int betweenStart = r.getInt(3);
			int betweenEnd = r.getInt(4);
			String codeDisplayWhole = r.getString(5);
			cidUids.add(new EnclosingUnits(cid,uid,betweenStart,betweenEnd, codeDisplayWhole));
		}
		
		r.close();
		s.close();
		return cidUids;
	}
	
	public static List<CallUnit> selectCidUidsOfCallUnits(Connection conn) throws SQLException, IOException {
		List<CallUnit> cidUids= new ArrayList<CallUnit>();
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT E.cid, E.uid, E.charEnd, E.charStart, U.codeDisplayWhole, U.konailaNodeType " 
						+ " FROM elements as E, selectionUnits as U "
						+ " WHERE E.cid=U.cid AND E.uid=U.uid AND U.konailaNodeType LIKE 'CallUnit'");
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int cid = r.getInt(1);
			int uid = r.getInt(2);
			int charEnd = r.getInt(3);
			int charStart = r.getInt(4);
			cidUids.add(new CallUnit(cid,uid,charStart,charEnd));
		}
		
		r.close();
		s.close();
		return cidUids;
	}
	
	public static int selectEnclosingThroughCorrespondingHole(Connection conn, CallUnit callUnit) throws SQLException, IOException {
		int enclosingUid = -1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT H.enclosingUid "
						+ " FROM holes H, elements E, selectionUnits S "
						+ " WHERE E.uid=? AND E.cid=?"
						+ " AND E.cid=H.cid AND S.cid=E.cid "
						+ " AND H.charStart<=E.charStart AND H.charEnd>=E.charEnd"
						+ " AND S.uid=H.enclosingUid AND S.konailaNodeType != 'CallUnit' "
						+ " ORDER BY H.charStart DESC ");
		s.setInt(1, callUnit.getUid());
		s.setInt(2,  callUnit.getCid());
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			enclosingUid = r.getInt(1);
		}
				
		r.close();
		s.close();
		
		return enclosingUid;
	}
	
	public static List<Integer> selectCidsOfEclosingUnits(Connection conn, String source) throws SQLException, IOException {
		List<Integer> uids= new ArrayList<Integer>();
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT F.cid FROM elements as F, elements as S, selectionUnits as U, codeFragments as C"
						+ " WHERE F.cid=S.cid AND F.cid=U.cid AND F.uid=S.uid AND F.uid=U.uid AND C.cid=F.cid "
						+ " AND F.elementOrderInUnit = 1"
						+ " AND S.elementOrderInUnit = 2"
						+ " AND source like ?");
		
		s.setString(1, source);
		
		ResultSet r = s.executeQuery();		
		
		while(r.next()) {
			int uid = r.getInt(1);
			uids.add(uid);
		}
		
		r.close();
		s.close();
		return uids;
	}
	
	public static int getEnclosingUid(Connection conn, int cid, int uid) throws SQLException, IOException {
		
		int enclosingUid = -1;
		String query =  "SELECT uid, enclosingUnitUid FROM selectionUnits "
				 + " WHERE cid=? AND uid=?";
		
		PreparedStatement s = conn.prepareStatement(query);
		s.setInt(1, cid);
		s.setInt(2,  uid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			enclosingUid = r.getInt("enclosingUnitUid");
		}
		
		s.close();
		return enclosingUid;		
	}	
	
	public static void getEnclosingMethod(Connection conn, int cid) 
			throws SQLException, IOException {

//		SELECT S.cid, S.uid, S.enclosingUnitUid, S.codeDisplayWhole, E.konailaNodeType
//		FROM selectionUnits S, selectionUnits E 
//		WHERE S.cid=E.cid AND S.enclosingUnitUid=E.uid AND S.codeDisplayWhole LIKE 'return%'
		
	}
}
