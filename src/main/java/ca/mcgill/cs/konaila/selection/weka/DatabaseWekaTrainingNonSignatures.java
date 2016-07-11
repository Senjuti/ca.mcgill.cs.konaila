package ca.mcgill.cs.konaila.selection.weka;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DatabaseWekaTrainingNonSignatures {
	
	private static boolean justStatements = true; 
	
	public static Set<Instance> getMachineLearningInstances(Connection conn, int cid, int uid) throws SQLException, IOException {
			
		Set<Instance> features = new HashSet<Instance>();
		PreparedStatement s = conn
				.prepareStatement("SELECT G.inSummary "
								+ " FROM eclipseGoldStandardLinesMappedToOneUnit as G "
								+ " WHERE G.cid=? AND G.uid=?");
		s.setInt(1,cid);
		s.setInt(2,cid);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			String featureString = r.getString("feature");
			int inSummary = r.getInt("G.inSummary");
			features.add(new Instance(featureString, inSummary, cid, uid));
		}
		
		return features;
	}
	
	
	public static Set<CidUid> getEclipseCidsUids(Connection conn) throws SQLException, IOException  {
		
		Set<CidUid> cidUids = new HashSet<CidUid>();
		PreparedStatement s = conn
				.prepareStatement("SELECT DISTINCT F.cid, F.uid "
							+ " FROM codeFragments as C, features as F, featureType as T " 
							+ " WHERE "
//							+ " C.source='eclipse-faq' AND "
							+ " C.cid=F.cid AND F.feature=T.feature "
							+ (justStatements ? 
									" AND T.featureType='NotSignature'"
								: "")
							+ " GROUP BY F.cid, F.uid ");		
		ResultSet r = s.executeQuery();	
		while(r.next()) {
			int cid = r.getInt(1);
			int uid = r.getInt(2);
			cidUids.add(new CidUid(cid,uid));
		}
		
		s.close();
		return cidUids;
	}

	@Deprecated
	public static Set<String> getFeatures(Connection conn, int cid, int uid) throws SQLException, IOException {
		
		Set<String> features = new HashSet<String>();
		PreparedStatement s = conn
				.prepareStatement("SELECT F.feature "
								+ " FROM features as F, featureType as T"
						        + " WHERE F.cid=? AND F.uid=? AND F.feature=T.feature "
						        + (justStatements ? 
						        		" AND T.featureType LIKE 'NotSignature'" : "")
						        );
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();		
	
		while(r.next()) {
			String feature = r.getString("feature");
			features.add(feature);
		}
		
		s.close();
		return features;
	}
	
	public static int getInSummary(Connection conn, int cid, int uid) throws SQLException, IOException {
		
		int inSummary = -1;
		PreparedStatement s = conn
				.prepareStatement("SELECT inSummary "
								+ " FROM goldStandard "
						        + " WHERE cid=? AND uid=?");
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();		
	
		if(r.next()) {
			return r.getInt("inSummary");
		}
		
		s.close();
		return inSummary;
	}
	
	
	public static int getIntValue(Connection conn, int cid, int uid, String feature) throws SQLException, IOException {
		
		int intValue = -1;
		PreparedStatement s = conn
				.prepareStatement("SELECT intValue "
								+ " FROM features "
						        + " WHERE cid=? AND uid=? AND feature=?");
		s.setInt(1, cid);
		s.setInt(2, uid);
		s.setString(3, feature);
		ResultSet r = s.executeQuery();		
	
		if(r.next()) {
			intValue = r.getInt("intValue");
		}
		
		s.close();
		return intValue;
	}
	
	public static int getStringValue(Connection conn, int cid, int uid, String feature) throws SQLException, IOException {
		
		ArrayList<String> featureValues = getFeatureValues(conn,feature);

		
		int stringIndex = -1;
		String stringValue = "";
		PreparedStatement s = conn
				.prepareStatement("SELECT stringValue "
								+ " FROM features "
						        + " WHERE cid=? AND uid=? AND feature=?");
		s.setInt(1, cid);
		s.setInt(2, uid);
		s.setString(3, feature);
		ResultSet r = s.executeQuery();		
	
		if(r.next()) {
			stringValue = r.getString("stringValue");
			stringIndex = featureValues.indexOf(stringValue);
		}
		
		s.close();
		return stringIndex;
	}
	
	public static String getStringValueSimple(Connection conn, int cid, int uid, String feature) throws SQLException, IOException {
		
		String stringValue = "";
		PreparedStatement s = conn
				.prepareStatement("SELECT stringValue "
								+ " FROM features "
						        + " WHERE cid=? AND uid=? AND feature=?");
		s.setInt(1, cid);
		s.setInt(2, uid);
		s.setString(3, feature);
		ResultSet r = s.executeQuery();		
	
		if(r.next()) {
			stringValue = r.getString("stringValue");			
		}		
		
		s.close();
		return stringValue;
	}
	
//	public static ArrayList<String> getAllFeatureNames(Connection conn) throws SQLException, IOException, Exception {		
//		
//		ArrayList<String> features = new ArrayList<String>();
//		PreparedStatement s = conn
//				.prepareStatement("	SELECT DISTINCT feature "
//									+ " FROM features as F "
//									+ (justStatements ?
//											" WHERE feature NOT  LIKE 'Modifier%' AND feature NOT LIKE '%Signature'" : "")
//									+ " ORDER BY feature ASC");
//		
//		ResultSet r = s.executeQuery();
//		
//		while(r.next()) {
//			String feature = r.getString("feature");
//			features.add(feature);
//		}
//		
//		return features;		
//	}
	
	public static ArrayList<String> getFeatureNames(Connection conn, String featureValueType) throws SQLException, IOException {		
		
		ArrayList<String> features = new ArrayList<String>();
		PreparedStatement s = conn
				.prepareStatement("	SELECT DISTINCT feature "
									+ " FROM featureType as F "
									+ " WHERE featureValueType=? "
									+ (justStatements ?
											" AND featureType LIKE 'NotSignature'"
											: "")
									+ " ORDER BY feature ASC");

		s.setString(1, featureValueType);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			String feature = r.getString("feature");
			features.add(feature);
		}
		
		s.close();
		return features;		
	}
	
	
	public static ArrayList<String> getFeatureValues(Connection conn, String feature) throws SQLException, IOException {		
		
		ArrayList<String> featureValues = new ArrayList<String>();
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT DISTINCT stringValue "
								+ " FROM features WHERE feature = ? "
								+ " ORDER BY stringValue ASC");
		
		s.setString(1, feature);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			String stringValue = r.getString("stringValue");
			featureValues.add(stringValue);
		}
		
		s.close();
		return featureValues;		
	}
	
	public static List<Integer> getEclipseRandomizedCid(Connection conn) throws SQLException, IOException {		
		
		List<Integer> cids = new ArrayList<Integer>();
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT cid FROM codeFragments WHERE source='eclipse-faq' ORDER BY RANDOM();");
		
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int cid = r.getInt("cid");
			cids.add(cid);
		}
		
		s.close();
		return cids;	
		
	}
	
	
	public static class CidUid {
		int cid;
		int uid;
		public CidUid(int cid, int uid) {
			super();
			this.cid = cid;
			this.uid = uid;
		}
		public int getCid() {
			return cid;
		}
		public int getUid() {
			return uid;
		}
		
	}

	public static class Instance {
		String feature;
		int inSummary;
		int cid;
		int uid;
		public Instance(String feature, int inSummary, int cid, int uid) {
			super();
			this.feature = feature;
			this.inSummary = inSummary;
			this.cid = cid;
			this.uid = uid;
		}
		public String getFeature() {
			return feature;
		}
		public int getInSummary() {
			return inSummary;
		}
		public int getCid() {
			return cid;
		}
		public int getUid() {
			return uid;
		}		
	}
	
}
