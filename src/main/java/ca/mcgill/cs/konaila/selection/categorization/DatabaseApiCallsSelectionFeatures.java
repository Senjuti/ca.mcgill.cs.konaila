package ca.mcgill.cs.konaila.selection.categorization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ca.mcgill.cs.konaila.selection.features.IncludeOrNot;

public class DatabaseApiCallsSelectionFeatures {
	

	public static Map<Integer,ApiCallSelectionUnit> selectApiSelectionUnits(Connection conn,
			int cid) throws SQLException, IOException {
		Map<Integer,ApiCallSelectionUnit> uidToApi = new HashMap<Integer,ApiCallSelectionUnit>();
		PreparedStatement s = conn.prepareStatement("SELECT DISTINCT uid, intValue, feature " 
				+ " FROM features WHERE (feature='CallCount' OR feature='Constructor') AND cid=? " );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int uid = r.getInt(1);
			int callCount = r.getInt(2);
			String feature = r.getString(3);
			
			ApiCallSelectionUnit api =uidToApi.containsKey(uid) ? uidToApi.get(uid) : new ApiCallSelectionUnit(cid, uid, feature, callCount);			
			uidToApi.put(uid,api);
		}
		
		s.close();		
		return uidToApi;
	}
	
	public static void populateQueryTerms(Connection conn, 
			int cid, Map<Integer,ApiCallSelectionUnit> uidToApi) throws SQLException, IOException {
		
		PreparedStatement s = conn.prepareStatement(
				"SELECT DISTINCT Fc.uid, Fc.unit, Fc.intValue, Fq.feature, Fq.intValue "
				+ " FROM features Fq, features Fc  "
				+ " WHERE Fq.uid=Fc.uid AND Fq.cid=Fc.cid AND "
				+ " (Fq.feature LIKE '%Query%' AND (Fc.feature='CallCount' OR Fc.feature='Constructor')) "
				+ " AND Fq.cid=?" );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int uid = r.getInt(1);
			ApiCallSelectionUnit api = uidToApi.get(uid);
			api.setHasQueryTerms(true);
		}
		
		s.close();
	}
	

	public static class ApiCallSelectionUnit extends ScoringSelectionUnitImpl {

		 int callCount = 0;
		 boolean isConstructor = false;
		 boolean hasQueryTerms = false;
		 static Category category = Category.ApiCalls;
		 
		public ApiCallSelectionUnit(int cid, int uid, String feature, int callCount) {
			super();
			this.cid = cid;
			this.uid = uid;
			
			if( feature.equals("CallCount")) {
				this.callCount = callCount;
			} else if (feature.equals("Constructor")) {
				this.isConstructor = true;
			} else {
				new RuntimeException();
			}
		}
		public Category getCategory () { return category; }

		public int getCallCount() {
			return callCount;
		}
		public void setCallCount(int callCount) {
			this.callCount = callCount;
		}
		public boolean isConstructor() {
			return isConstructor;
		}
		public void setConstructor(boolean isConstructor) {
			this.isConstructor = isConstructor;
		}
		public boolean hasQueryTerms() {
			return hasQueryTerms;
		}
		public void setHasQueryTerms(boolean hasQueryTerms) {
			this.hasQueryTerms = hasQueryTerms;
		}	 
	}

}
