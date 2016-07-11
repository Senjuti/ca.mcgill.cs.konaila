package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbAndroidProperty {
	
	public static int selectNumberOfPropertiesWithMatchedUids(Connection conn, int cid) 
			throws SQLException, IOException {
		int count=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT count(*) FROM astProperties as A, elements as F "
						+ " WHERE A.cid=F.cid AND F.charStart<=A.charStart AND F.charEnd>=A.charEnd "
						+ " AND A.cid=?"
						+ " AND F.elementOrderInUnit = 1");		
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			count = r.getInt(1);
		}
		return count;
	}
	
	
	public static int selectNumberOfPropertiesWithMatchedUids(Connection conn, int cid, 
			String property, String source) throws SQLException, IOException {
		int uid=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT count(*) FROM astProperties as A, elements as F, codeFragments as C "
						+ " WHERE A.cid=F.cid AND C.cid=A.cid "
						+ "AND F.charStart<=A.charStart AND F.charEnd>=A.charEnd "
						+ " AND A.cid=? AND A.konailaProperty=? "
						+ " AND F.elementOrderInUnit = 1");		
		s.setInt(1, cid);
		s.setString(2, property);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			uid = r.getInt(1);
		}
		return uid;
	}
	
	public static int selectUidOfMatchedProperty(Connection conn, int cid, int charStart, int charEnd) 
			throws SQLException, IOException  {
		int uid=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT F.uid FROM astProperties as A, elements as F "
						+ " WHERE A.cid=F.cid AND F.charStart<=A.charStart AND F.charEnd>=A.charEnd AND A.cid=?"
						+ " AND A.charStart=? AND A.charEnd = ?"
						+ " AND F.elementOrderInUnit = 1");		
		s.setInt(1, cid);
		s.setInt(2, charStart);
		s.setInt(3, charEnd);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			uid = r.getInt(1);
		}
		return uid;
	}

	
	public static String selectPropertyTypeOfMatchedProperty(Connection conn, int cid, int charStart, int charEnd) throws SQLException, IOException, Exception {
		String property="";
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT konailaProperty FROM astProperties as A, elements as F "
						 + " WHERE A.cid=F.cid  "
						 + " AND F.charStart<=A.charStart AND F.charEnd>=A.charEnd"
						 + " AND A.cid=? AND A.charStart=? AND A.charEnd=?");		
		s.setInt(1, cid);
		s.setInt(2, charStart);
		s.setInt(3, charEnd);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			property = r.getString(1);
		}
		return property;
	}
	
	public static String selectProperty(Connection conn, int cid, 
			int propertyLineStart, int propertyLineEnd) throws SQLException, IOException, Exception {
		String property="";
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT konailaProperty FROM astProperties as A "
						 + " WHERE A.cid=? AND A.lineStart=? AND A.lineEnd=?");		
		s.setInt(1, cid);
		s.setInt(2, propertyLineStart);
		s.setInt(3, propertyLineEnd);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			property = r.getString(1);
		}
		return property;
	}

	public static String antlrPropertyExists(Connection conn, int cid, 
			int propertyLineStart, int propertyLineEnd, String antlrPropertyExists) throws SQLException, IOException, Exception {
		String property="";
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT konailaProperty FROM astProperties as A "
						 + " WHERE A.cid=? AND A.lineStart=? AND A.lineEnd=? AND A.antlrProperty LIKE 'ExceptionDeclarationContext'");		
		s.setInt(1, cid);
		s.setInt(2, propertyLineStart);
		s.setInt(3, propertyLineEnd);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			property = r.getString(1);
		}
		return property;
	}
	
	public static List<String> selectProperties(Connection conn, int cid, 
			int propertyLineStart, int propertyLineEnd) throws SQLException, IOException, Exception {
		
		List<String> properties = new ArrayList<String>();
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT konailaProperty FROM astProperties as A "
						 + " WHERE A.cid=? AND A.lineStart=? AND A.lineEnd=?");		
		s.setInt(1, cid);
		s.setInt(2, propertyLineStart);
		s.setInt(3, propertyLineEnd);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			properties.add(r.getString(1));
		}
		return properties;
	}

	public static int selectNumberOfProperties(Connection conn, int cid, 
			int propertyLineStart, int propertyLineEnd, String konailaProperty) 
					throws SQLException, IOException, Exception {
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT konailaProperty FROM astProperties as A "
						 + " WHERE A.cid=? AND A.lineStart=? AND A.lineEnd=?"
						 + " AND A.konailaProperty=?");		
		s.setInt(1, cid);
		s.setInt(2, propertyLineStart);
		s.setInt(3, propertyLineEnd);
		s.setString(4,  konailaProperty);
		ResultSet r = s.executeQuery();
		
		int count = 0;
		while(r.next()) {
			count += 1;
		}
		return count;
	}
	
	public static String selectNodeTypeOfMatchedProperty(Connection conn, int cid, 
			int propertyCharStart, int propertyCharEnd) throws SQLException, IOException, Exception {
		String nodeType="";
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT konailaNodeType FROM astProperties as A, elements as F, selectionUnits as U "
						 + " WHERE A.cid=F.cid AND F.uid=U.uid AND F.cid=U.cid "
						 + " AND F.charStart<=A.charStart AND F.charEnd>=A.charEnd"
						 + " AND A.cid=? AND A.charStart=? AND A.charEnd=?");		
		s.setInt(1, cid);
		s.setInt(2, propertyCharStart);
		s.setInt(3, propertyCharEnd);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			nodeType = r.getString(1);
		}
		return nodeType;
	}
	
	public static String selectNodeTypeOfMatchedProperty(Connection conn, int cid, 
			int unitLineStart, int unitLineEnd, String konailaProperty) throws SQLException, IOException, Exception {
		String nodeType="";
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT U.konailaNodeType, A.antlrProperty, U.uid, U.codeDisplayWhole, A.charStart, A.charEnd "
						+ " FROM astProperties as A, selectionUnits as U "
						+ " WHERE U.unitCharStart<=A.charStart AND U.unitCharEnd>=A.charEnd "
						+ " AND U.cid=A.cid AND A.cid=? AND U.unitLineStart=? AND U.unitLineEnd=? "
						+ " AND A.konailaProperty LIKE ?");	
		
		s.setInt(1, cid);
		s.setInt(2, unitLineStart);
		s.setInt(3, unitLineEnd);
		s.setString(4,  konailaProperty);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			nodeType = r.getString(1);
		}
		return nodeType;
	}
	
	public static int selectNumberOfProperties(Connection conn, int cid) throws SQLException, IOException, Exception {
		int count=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT count(*) FROM astProperties as A "
						+ " WHERE A.cid=?");		
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			count = r.getInt(1);
		}
		return count;
	}

	public static int selectNumberOfProperties(Connection conn, int cid, String property) throws SQLException, IOException, Exception {
		int count=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT count(*) FROM astProperties as A  "
						+ " WHERE A.cid=? AND A.konailaProperty LIKE ? ");		
		s.setInt(1, cid);
		s.setString(2, property);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			count = r.getInt(1);
		}
		return count;
	}
	
	public static int selectPropertyCharStart(Connection conn, int cid, 
			int propertyLineStart, int propertyLineEnd, String antlrProperty) throws SQLException, IOException, Exception {
		int index=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT charStart FROM astProperties as A "
						+ " WHERE A.cid=? AND A.konailaProperty = ?"
						+ " AND A.lineStart=? AND A.lineEnd=?");		
		s.setInt(1, cid);
		s.setString(2, antlrProperty);
		s.setInt(3,  propertyLineStart);
		s.setInt(4, propertyLineEnd);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			index = r.getInt(1);
		}
		return index;
	}
	
	public static int selectPropertyCharEnd(Connection conn, int cid, 
			int propertyLineStart, int propertyLineEnd, String antlrProperty) throws SQLException, IOException, Exception {
		int index=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT charEnd FROM astProperties as A "
						+ " WHERE A.cid=? AND A.konailaProperty = ?"
						+ " AND A.lineStart=? AND A.lineEnd=?");		
		s.setInt(1, cid);
		s.setString(2, antlrProperty);
		s.setInt(3,  propertyLineStart);
		s.setInt(4, propertyLineEnd);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			index = r.getInt(1);
		}
		return index;
	}
	
	public static boolean verifyCharStartEnd(Connection conn, int cid, 
			int lineCharStart, int lineCharEnd, int charStart, int charEnd) throws SQLException, IOException, Exception {

		PreparedStatement s = conn
				.prepareStatement(
						"SELECT count(*) FROM astProperties as A "
						+ " WHERE A.cid=? AND A.lineCharStart=? AND A.lineCharEnd=? AND A.charStart=? AND A.charEnd=?");		
		s.setInt(1, cid);
		s.setInt(2, lineCharStart);
		s.setInt(3,  lineCharEnd);
		s.setInt(4, charStart);
		s.setInt(5, charEnd);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			return true; 
		}
		return false;
	}
	
	public static int selectPropertyUid(Connection conn, int cid, String property) throws SQLException, IOException {
		int uid=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT uid FROM astProperties as A "
						+ " WHERE A.cid=? AND A.antlrProperty = ?");		
		s.setInt(1, cid);
		s.setString(2, property);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			uid = r.getInt(1);
		}
		return uid;
	}
	
	public static int getPropertyTypeCounts(Connection conn) throws SQLException, IOException {
		int count = -1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT count(*) "
								+ " FROM features as F "
								+ " WHERE propertyOrType='type'");
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			 count = r.getInt(1);
		}
		
		return count;			
	}
	

	public static int getUniquePropertyTypeUidCid(Connection conn) throws SQLException, IOException {
		int count = -1;
		PreparedStatement s = conn
			.prepareStatement(
					"SELECT DISTINCT uid, cid, count(*) "
							+ " FROM features as F "
							+ " WHERE propertyOrType='type'");
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			 count = r.getInt(3);
		}		
		
		return count;
	}
	
	public static void printErrors(Connection conn) throws SQLException, IOException {

		PreparedStatement s = conn
				.prepareStatement("SELECT count(*), cid, uid FROM features as F "
						+ " WHERE propertyOrType='type'");		
		
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int count = r.getInt(1);
			if( count != 1 ) {
				int cid = r.getInt("cid");
				int uid = r.getInt("uid");
				System.out.println("cid=" + cid + ", uid=" + uid + ", count=" + count);
			}
		}	
	}
	
	public static List<Feature> selectProperties(Connection conn, int cid, int lineNumber) throws SQLException, IOException {
		String feature;
		String propertyOrType;
		int uid;
		
		List<Feature> result = new ArrayList<Feature>();
		PreparedStatement s = conn
				.prepareStatement("SELECT F.feature, F.propertyOrType, F.uid, E.lineStart, E.lineEnd, F.unit "
						+ " FROM features as F, elements as E "
						+ " WHERE F.cid=E.cid AND F.uid=E.uid AND (E.lineStart<=? AND E.lineEnd>=?)"
						+ " AND F.cid=?");
		
		s.setInt(1, lineNumber);
		s.setInt(2, lineNumber);
		s.setInt(3, cid);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			feature = r.getString(1);
			propertyOrType = r.getString(2);
			uid = r.getInt(3);
			result.add(new Feature(cid,uid,feature,propertyOrType,lineNumber));			
		}
		return result;

	}

	
	public static int getCallValue(Connection conn, int cid, int lineNumber) throws SQLException, IOException {
		
		int count = -1;
		PreparedStatement s = conn
				.prepareStatement("SELECT F.intValue "
						+ " FROM features as F "
						+ " WHERE F.lineStart=? AND F.cid=? AND F.feature LIKE 'CallCount'");
		
		s.setInt(1, lineNumber);
		s.setInt(2, cid);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			count = r.getInt(1);
		}

		return count;

	}

	
	public static class Feature {
		int cid;
		int uid;
		String feature;
		String propertyOrType;
		int lineNumber;
		public Feature(int cid, int uid, String feature, String propertyOrType, int lineNumber) {
			super();
			this.cid = cid;
			this.uid = uid;
			this.feature = feature;
			this.propertyOrType = propertyOrType;
			this.lineNumber = lineNumber;
		}
		
		@Override
		public String toString() {
			return feature + "-line:" + lineNumber;
		}
		
	}
	
}
