package ca.mcgill.cs.konaila.selection.categorization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import ca.mcgill.cs.konaila.selection.features.IncludeOrNot;

public class DatabaseControlFlowSelectionFeatures {
	

	public static Map<Integer,ControlFlowSelectionUnit> selectControlFlowSelectionUnits(Connection conn,
			int cid) throws SQLException, IOException {
		Map<Integer,ControlFlowSelectionUnit> uidToCf = new HashMap<Integer,ControlFlowSelectionUnit>();
		PreparedStatement s = conn.prepareStatement("SELECT DISTINCT uid, intValue, feature " 
				+ " FROM features"
				+ " WHERE (feature='ForWrapper' OR feature='IfWrapper' OR feature='WhileWrapper' OR feature='SwitchWrapper' )"
				+ " AND cid=? " );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int uid = r.getInt(1);
			String feature = r.getString(3);
			
			ControlFlowSelectionUnit api = new ControlFlowSelectionUnit(cid, uid, feature);			
			uidToCf.put(uid,api);
		}
		
		s.close();		
		return uidToCf;
	}
	
	public static void populateQueryTerms(Connection conn, 
			int cid, Map<Integer,ControlFlowSelectionUnit> uidToApi) throws SQLException, IOException {
		
		PreparedStatement s = conn.prepareStatement(
				"SELECT DISTINCT Fc.uid, Fc.unit, Fc.intValue, Fq.feature, Fq.intValue "
				+ " FROM features Fq, features Fc  "
				+ " WHERE Fq.uid=Fc.uid AND Fq.cid=Fc.cid AND "
				+ " (Fq.feature LIKE '%Query%') "
				+ " AND (Fc.feature='ForWrapper'  OR Fc.feature='IfWrapper' OR Fc.feature='WhileWrapper' OR Fc.feature='SwitchWrapper') "
				+ " AND Fq.cid=?" );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int uid = r.getInt(1);
			ControlFlowSelectionUnit cf = uidToApi.get(uid);
			cf.setHasQueryTerms(true);
		}
		
		s.close();
	}
	
	public static void populateCallCounts(Connection conn, 
			int cid, Map<Integer,ControlFlowSelectionUnit> uidToCf) throws SQLException, IOException {
		
		PreparedStatement s = conn.prepareStatement(
				"SELECT DISTINCT Fc.uid, Fq.intValue, Fq.feature"
				+ " FROM features Fq, features Fc  "
				+ " WHERE Fq.uid=Fc.uid AND Fq.cid=Fc.cid AND "
				+ " (Fq.feature LIKE 'CallCounts' OR Fq.feature LIKE 'Constructor') "
				+ " AND (Fc.feature='ForWrapper'  OR Fc.feature='IfWrapper' OR Fc.feature='WhileWrapper' OR Fc.feature='SwitchWrapper') "
				+ " AND Fq.cid=?" );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int uid = r.getInt(1);
			int intValue = r.getInt(2);
			String feature = r.getString(3);
			ControlFlowSelectionUnit cf = uidToCf.get(uid);
			if( feature.equals("CallCount")) {
				cf.setCallCount(intValue);
			} else if( feature.equals("Constructor")) {
				cf.setConstructor(true);
			} else {throw new RuntimeException();}
		}
		
		s.close();
	}
	
	public static void populateLiterals(Connection conn, 
			int cid, Map<Integer,ControlFlowSelectionUnit> uidToCf) throws SQLException, IOException {
		
		PreparedStatement s = conn.prepareStatement(
				"SELECT DISTINCT Fc.uid, Fq.intValue, Fq.feature"
				+ " FROM features Fq, features Fc  "
				+ " WHERE Fq.uid=Fc.uid AND Fq.cid=Fc.cid AND "
				+ " (Fq.feature LIKE 'BooleanLiteral' OR Fq.feature LIKE 'CharacterLiteral' OR Fq.feature LIKE 'FloatLiteral' OR Fq.feature LIKE 'IntegerLiteral' OR Fq.feature LIKE 'NullLiteral' OR Fq.feature LIKE 'StringLiteral') "
				+ " AND (Fc.feature='ForWrapper' OR Fc.feature='IfWrapper' OR Fc.feature='WhileWrapper' OR Fc.feature='SwitchWrapper') "
				+ " AND Fq.cid=?" );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int uid = r.getInt(1);
			int intValue = r.getInt(2);
			String feature = r.getString(3);
			ControlFlowSelectionUnit cf = uidToCf.get(uid);
			cf.setLiteral(feature);
		}
		
		s.close();
	}
	

	public static class ControlFlowSelectionUnit extends ScoringSelectionUnitImpl {

		 String feature;
		 boolean hasQueryTerms = false;
		 int callCount = 0;
		 boolean isConstructor = false;
		 
		 boolean containBooleanLiteral = false;
		 boolean containCharacterLiteral = false;
		 boolean containFloatLiteral = false;
		 boolean containIntegerLiteral = false;
		 boolean containNullLiteral = false;
		 boolean containStringLiteral = false;
		 
//		 IncludeOrNot includeOrNot;
		 int score;
		 
		 static Category category = Category.ControlFlowCentric;
		 
		public ControlFlowSelectionUnit(int cid, int uid, String feature) {
			super();
			this.cid = cid;
			this.uid = uid;
			this.feature = feature;
		}

		public Category getCategory () { return category; }

		public boolean hasQueryTerms() {
			return hasQueryTerms;
		}
		public void setHasQueryTerms(boolean hasQueryTerms) {
			this.hasQueryTerms = hasQueryTerms;
		}
//		public IncludeOrNot getIncludeOrNot() {
//			return includeOrNot;
//		}
//		public void setIncludeOrNot(IncludeOrNot includeOrNot) {
//			this.includeOrNot = includeOrNot;
//		}
		public int getScore() {
			return score;
		}
		public void setScore(int score) {
			this.score = score;
		}
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
		public boolean hasContainBooleanLiteral() {
			return containBooleanLiteral;
		}
		public boolean hasContainCharacterLiteral() {
			return containCharacterLiteral;
		}
		public boolean hasContainFloatLiteral() {
			return containFloatLiteral;
		}
		public boolean hasContainIntegerLiteral() {
			return containIntegerLiteral;
		}
		public boolean hasNullLiteral() {
			return containNullLiteral;
		}
		public boolean hasStringLiteral() {
			return containStringLiteral;
		}
		public void setLiteral(String feature) {
			switch(feature ) {			
			case "BooleanLiteral" :
				this.containBooleanLiteral = true;
				break;
			case "CharacterLiteral" :
				this.containCharacterLiteral = true;
				break;
			case "FloatLiteral" :
				this.containFloatLiteral = true;
				break;
			case "IntegerLiteral" :
				this.containIntegerLiteral = true;
				break;
			case "NullLiteral" :
				this.containNullLiteral = true;
				break;
			case "StringLiteral" :
				this.containStringLiteral = true;
				break;
			}
		}
	}
}
