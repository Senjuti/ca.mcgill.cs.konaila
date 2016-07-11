package ca.mcgill.cs.konaila.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;

public class DatabaseFeatures {
	
	private static final String featureFileName = "database/features.csv";
	
	public static void populateMatchedProperties(Connection conn) throws SQLException, IOException {

		conn.setAutoCommit(false);
		
		PreparedStatement s = conn
				.prepareStatement(
						"INSERT INTO features (cid,uid,unit,feature,intValue,stringValue,propertyOrType,lineStart) " + 
						" SELECT U.cid, U.uid, U.codeDisplayWhole, A.konailaProperty, 1, '', 'property', A.lineStart "
						+ " FROM elements as E, astProperties as A, selectionUnits as U "
						+ " WHERE E.cid=A.cid AND E.charStart<=A.charStart AND E.charEnd>=A.charEnd "
						+ " AND U.cid=E.cid AND U.uid=E.uid "
						+ " AND A.konailaProperty NOT LIKE 'AnonymousClassCreation'");
		s.executeUpdate();
		
		s = conn
				.prepareStatement(
						"INSERT INTO features (cid,uid,unit,feature,intValue,stringValue,propertyOrType,lineStart) " + 
						" SELECT U.cid, U.uid, U.codeDisplayWhole, A.konailaProperty, 1, '', 'property', A.lineStart "
						+ " FROM astProperties as A, selectionUnits as U "
						+ " WHERE U.cid=A.cid AND U.unitCharStart<=A.charStart AND U.unitCharEnd>=A.charEnd"
						+ " AND A.konailaProperty LIKE 'AnonymousClassCreation'");	
		s.executeUpdate();
		s.close();
		
		conn.commit();
	}

	public static void populateNodeType(Connection conn) throws SQLException, IOException {
		
		conn.setAutoCommit(false);

		PreparedStatement s = conn
				.prepareStatement(
						"INSERT INTO features  (cid,uid,unit,feature,intValue,stringValue,propertyOrType,lineStart) " + 
						" SELECT U.cid, U.uid, U.codeDisplayWhole, U.konailaNodeType, 1, '', 'type', E.lineStart "
						+ " FROM selectionUnits as U, elements as E "
						+ " WHERE U.uid=E.uid AND U.cid=E.cid AND E.elementOrderInUnit=1 ");	
		s.executeUpdate();
		s.close();

		conn.commit();
	}
	
	public static void consolidateCallFeatures(Connection conn) throws SQLException, IOException {
		
		conn.setAutoCommit(false);		
		PreparedStatement s = conn
				.prepareStatement(
						"INSERT INTO features (cid,uid,unit,feature,intValue,stringValue,propertyOrType,lineStart) " +				
						" SELECT F.cid, F.uid, F.unit, 'CallCount', count(*), '', 'property', F.lineStart"
						+ " FROM features as F "
						+ " WHERE  "
						+ "    (feature  LIKE 'CallContext' OR feature LIKE 'CallName') "
						+ " GROUP BY F.cid, F.uid ");
		
		s.executeUpdate();
		s.close();		
		conn.commit();
		
	}
	
	public static void consolidateLocalVariableDeclarationFeatures(Connection conn) throws SQLException, IOException {		
		conn.setAutoCommit(false);		
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT F.cid, F.uid, F.feature "
						+ " FROM features as F WHERE  feature  LIKE 'LocalVariableDeclarationPrimitive'");
		
		PreparedStatement u = conn
				.prepareStatement(
						"UPDATE features "
						+ " SET cid=cid, uid=uid, unit=unit, feature=feature, intValue=-1, stringValue='Primitive', "
						+ "	    propertyOrType=propertyOrType, lineStart=lineStart "
						+ " WHERE cid=? AND uid=? AND feature='LocalVariableDeclarationStatement' ");
		
		ResultSet r = s.executeQuery();
		while(r.next()) {
			int cid = r.getInt(1);
			int uid = r.getInt(2);
			
			u.setInt(1, cid);
			u.setInt(2, uid);
			u.executeUpdate();
		}		

		
		u = conn
				.prepareStatement(
						"UPDATE features "
						+ " SET cid=cid, uid=uid, unit=unit, feature=feature, intValue=-1, stringValue='Object', "
						+ "	    propertyOrType=propertyOrType, lineStart=lineStart "
						+ " WHERE feature='LocalVariableDeclarationStatement' AND stringValue=''");
		u.executeUpdate();
		
		u.close();
		s.close();		
		conn.commit();		
	}
	
	public static void consolidateConstructorFeatures(Connection conn) throws SQLException, IOException {		
		conn.setAutoCommit(false);		
		
		PreparedStatement u = conn
				.prepareStatement(
						"INSERT INTO features (cid,uid,unit,feature,intValue,stringValue,propertyOrType,lineStart) "
						+ "SELECT F.cid, F.uid, F.unit, 'Constructor', 1, ?, F.propertyOrType, F.lineStart "
						+ "FROM features as F "
						+ " WHERE F.feature=? ");		

//		u.setString(1,  "Array");
//		u.setString(2, "ConstructorArray");
//		u.executeUpdate();
		
		u.setString(1,  "Object");
		u.setString(2, "ConstructorObject");
		u.executeUpdate();		
		u.close();
		
//		u = conn.prepareStatement("INSERT INTO features (cid,uid,unit,feature,intValue,stringValue,propertyOrType,lineStart) "
//						+ "SELECT F.cid, F.uid, F.unit, 'Constructor', 1, '', F.propertyOrType, F.lineStart "
//						+ "FROM features as F "
//						+ " WHERE F.feature='ConstructorUnit' ");		

//		u.setString(1,  "Array");
//		u.setString(2, "ConstructorArray");
//		u.executeUpdate();
		
//		u.executeUpdate();		
		u.close();
		conn.commit();		
	}
	
	
	
	public static void consolidateComments(Connection conn) throws SQLException, IOException {		
		conn.setAutoCommit(false);		
		PreparedStatement s = conn
				.prepareStatement(
						"INSERT INTO features (cid,uid,unit,feature,intValue,stringValue,propertyOrType,lineStart) " +				
						" SELECT F.cid, F.uid, F.unit, 'Comment', 1, ?, 'type', F.lineStart"
						+ " FROM features as F "
						+ " WHERE  feature  LIKE ? ");
		
		String[] commentTypes = new String[] {"LineComment", "JavaDocComment", "BasicBlockComment"};
		for( String commentType : commentTypes ) {
			s.setString(1, commentType);
			s.setString(2, commentType);
			s.executeUpdate();
		}
		
		s.close();		
		conn.commit();	
	}
	
	public static void cleanup(Connection conn) throws SQLException, IOException {		
		conn.setAutoCommit(false);		
		PreparedStatement s = conn
				.prepareStatement(
						"DELETE from features "
						+ " WHERE feature='CallUnit' "
						+ " OR feature = 'CallContext' "
						+ " OR feature = 'CallName' "
						+ " OR feature = 'LocalVariableDeclarationPrimitive' "
						+ " OR feature = 'ConstructorUnit' "
						+ " OR feature = 'ConstructorObject' "
						+ " OR  feature = 'LineComment' "
						+ " OR feature='BasicBlockComment' "
						+ " OR feature='JavaDocComment'");
		
		
		s.executeUpdate();

		s.close();		
		conn.commit();		
	}
	
	public static void populateFeatureInfo(Connection conn) throws SQLException, IOException {		
		

		conn.setAutoCommit(false);
		
		List<String> lines = FileUtils.readLines(new File(featureFileName));
		lines.remove(0);
		
		PreparedStatement s = conn
				.prepareStatement(
						"INSERT INTO featureType VALUES (?,?,?,?)" ); 
						
		
		for( String line : lines ) {
			String[] fields = line.split("\t");
			String feature = fields[0].replace("\"", "");
			String featureType = fields[1].replace("\"", "");
			String featureValueType = fields[2].replace("\"", "");
			Integer individualConfigurationScore = Integer.parseInt(fields[3]);
			
			s.setString(1, feature);
			s.setString(2, featureType);
			s.setString(3, featureValueType);
			s.setInt(4, individualConfigurationScore);
			s.executeUpdate();
		}
		
		s.close();
		conn.commit();	
	}
	
	public static Set<Integer> getNumberOfCidsWithAstProperties(Connection conn) throws SQLException, IOException {		
		Set<Integer> result = new HashSet<Integer>();

		PreparedStatement s = conn
				.prepareStatement(
						"		SELECT DISTINCT cid FROM astProperties");
		

		ResultSet r = s.executeQuery();	
		while( r.next()) {
			int value = r.getInt(1);
			result.add(value);
		}
		return result;
	}
	
	
	public static int getNumberOfCidsWithFeatures(Connection conn) throws SQLException, IOException {		
		int result = -1;

		PreparedStatement s = conn
				.prepareStatement(
						"		SELECT count(DISTINCT cid) FROM features");
		

		ResultSet r = s.executeQuery();	
		if( r.next()) {
			result = r.getInt(1);
		}
		return result;
	}
	
	public static List<Integer> getCidsSorted(Connection conn, String source)  throws SQLException, IOException {
		List<Integer> cids = new ArrayList<Integer>();

		PreparedStatement s = conn
				.prepareStatement(
						"		SELECT DISTINCT C.cid"
								+ " FROM features as F, codeFragments as C"
								+ " WHERE F.cid=C.cid AND C.source LIKE ? "
								+ " ORDER BY C.cid ASC");
		
		s.setString(1, source);

		ResultSet r = s.executeQuery();
		while ( r.next() ) {
			int cid= r.getInt(1);
			cids.add(cid);
		}		
		return cids;
	}
	
	public static int getNextCid(Connection conn, int cid)  throws SQLException, IOException {
		String source = DatabaseGetCodeFragments.getSource(conn, cid);
		List<Integer> cids = getCidsSorted(conn, source);
		int index = cids.indexOf(cid);
		if( (index != -1) &&index + 1 < cids.size()) {
			return cids.get(index + 1);
		} else {
			return -1;
		}
	}
	
	public static List<String> getFeatures(Connection conn)  throws SQLException, IOException {
		List<String> features = new ArrayList<String>();

		PreparedStatement s = conn
				.prepareStatement(
						"		SELECT DISTINCT feature"
								+ " FROM features as F"
								+ " ORDER BY feature ASC");
		
		ResultSet r = s.executeQuery();
		while ( r.next() ) {
			features.add(r.getString(1));
		}		
		r.close();
		s.close();
		return features;
	}
	
	public static List<String> getFeatureTypes(Connection conn)  throws SQLException, IOException {
		List<String> features = new ArrayList<String>();

		PreparedStatement s = conn
				.prepareStatement(
						"		SELECT DISTINCT feature"
								+ " FROM featureType as F"
								+ " ORDER BY feature ASC");
		
		ResultSet r = s.executeQuery();
		while ( r.next() ) {
			features.add(r.getString(1));
		}

		r.close();
		s.close();
		return features;
	}
}
