package ca.mcgill.cs.konaila.selection.features;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ca.mcgill.cs.konaila.selection.categorization.Category;
import ca.mcgill.cs.konaila.selection.categorization.ScoringSelectionUnitImpl;

public class DatabaseSignatureSelection {

	public static Map<Integer,MethodSignature> selectMethodSignaturesWithnEnclosingClass(Connection conn, int cid) throws SQLException, IOException {
		Map<Integer,MethodSignature> methods = selectImportantMethodSignatures(conn, cid);
		Map<Integer, MethodSignature> result = new HashMap<Integer,MethodSignature>();

		for( Entry<Integer,MethodSignature> e : methods.entrySet()) {
			MethodSignature method = e.getValue();
			if( method.hasEnclosingClass() ) {
				result.put(e.getKey(), method);
			}
		}		
		return result;		
	}

	public static Map<Integer,MethodSignature> selectImportantMethodSignatures(
			Connection conn, int cid)
			throws SQLException, IOException {

		Map<Integer,MethodSignature> uidToMethod = new HashMap<Integer,MethodSignature>();

		PreparedStatement s = conn
				.prepareStatement(
						"	SELECT S.uid, S.konailaNodeType, S.codeDisplayWhole, F.feature, S.enclosingUnitUid"
								+ " FROM selectionUnits as S, features as F"
								+ " WHERE S.cid=?  AND S.cid=F.cid AND S.uid=F.uid  "
								+ " AND F.feature like 'Modifier%'"
								+ " AND (S.konailaNodeType='MethodSignature' "
								+ "OR S.konailaNodeType='ConstructorSignature')");

		s.setInt(1, cid);
		ResultSet r = s.executeQuery();

		while(r.next()) {
			int uid = r.getInt(1);
			String methodModifier = r.getString(4);
			String codeDisplayWhole = r.getString(3);
			int enclosingTypeUid = r.getInt(5);

			if( !uidToMethod.containsKey(uid)) {
				MethodSignature method = new MethodSignature(cid,uid,enclosingTypeUid,codeDisplayWhole);
				uidToMethod.put(uid,method);
			} 

			if( methodModifier.equals("ModifierPublic")) {
				uidToMethod.get(uid).setPublic(true);
			} else if( methodModifier.equals("ModifierProtected")) {
				uidToMethod.get(uid).setProtected(true);
			} else if (methodModifier.equals("ModifierStatic")) {
				uidToMethod.get(uid).setStatic(true);
			} else if( methodModifier.equals("Modifier@Override")) {
				uidToMethod.get(uid).setOverrideTag(true);
			}
		}
		
		populateEnclosingUnits(conn, cid, uidToMethod);

		return uidToMethod;
	}
	
	public static Map<Integer,ClassSignature> selectImportantClassSignatures(
			Connection conn, int cid)
			throws SQLException, IOException {

		Map<Integer,ClassSignature> uidToClass = new HashMap<Integer,ClassSignature>();

		PreparedStatement s = conn
				.prepareStatement(
						"		SELECT S.uid, S.konailaNodeType, S.codeDisplayWhole, F.feature"
								+ "	FROM selectionUnits as S, features as F"
								+ "	WHERE S.cid=? AND S.cid=F.cid AND S.uid=F.uid"
								+ " AND (F.feature='Extends' "
								+ "      OR F.feature='Implements')"
								+ " AND (S.konailaNodeType='TypeSignature'"
								+ " OR 'InterfaceSignature')");
		

		s.setInt(1, cid);
		ResultSet r = s.executeQuery();

		while(r.next()) {
			int uid = r.getInt(1);
			String classProperty = r.getString(4);
			String codeDisplayWhole = r.getString(3);

			if( !uidToClass.containsKey(uid)) {
				ClassSignature classUnit = new ClassSignature(cid,uid,codeDisplayWhole);
				uidToClass.put(uid,classUnit);
			} 

			if( classProperty.equals("Extends")) {
				uidToClass.get(uid).setExtends(true);
			} else if( classProperty.equals("Implements") ) {
				uidToClass.get(uid).setImplements(true);
			} else if( classProperty.equals("ModifierPublic") ) {
				uidToClass.get(uid).setIsPublic(true);
			}
		}

		return uidToClass;
	}
	
	public static Map<Integer,MethodSignature> selectCallBackMethodSignatures(Connection conn, int cid)
			throws SQLException, IOException {
		Map<Integer,MethodSignature> methodSignatureUids = DatabaseSignatureSelection.selectImportantMethodSignatures(conn, cid);
		
		Map<Integer,MethodSignature> result = new HashMap<Integer,MethodSignature>();
		
		for( Entry<Integer,MethodSignature>  e : methodSignatureUids.entrySet()) {
			MethodSignature method = e.getValue();
		
			if( method.getMethodConfiguration().ordinal() >= IncludeOrNot.IncludeProbably.ordinal()) {
				result.put(method.getUid(), method);
			}
		}
		return result;
	}
	
	public static void populateQueryTerms(Connection conn, int cid,
			Map<Integer,MethodSignature> importantMethodSignatures, 
			Map<Integer,ClassSignature> importantClassSignatures )
			throws SQLException, IOException {
		
		PreparedStatement s = conn.prepareStatement(
				"SELECT DISTINCT Fc.uid, Fc.unit, Fc.intValue, Fq.feature, Fq.intValue "
				+ " FROM features Fq, features Fc "
				+ " WHERE Fq.uid=Fc.uid AND Fq.cid=Fc.cid AND "
				+ " (Fq.feature LIKE '%Query%') "
				+ " AND (Fc.feature='MethodSignature'  "
				+ "   OR Fc.feature='ConstructureSignature' "
				+ "   OR Fc.feature='TypeSignature' "
				+ "   OR Fc.feature='InterfaceSignature') "
				+ " AND Fq.cid=?" );
		s.setInt(1, cid);
				
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int uid = r.getInt(1);
			
			MethodSignature methodSignature = importantMethodSignatures.get(uid);
			ClassSignature classSignature = importantClassSignatures.get(uid);
			if( methodSignature != null) {
				methodSignature.setHasQueryTerms(true);
			} else if( classSignature != null ) {
				classSignature.setHasQueryTerms(true);
			}
		}
		
		s.close();		
		
	}
	

	private static void populateEnclosingUnits(Connection conn, int cid,
			Map<Integer,MethodSignature> uidToMethods)  throws SQLException, IOException {

		PreparedStatement s = conn
				.prepareStatement(
						"		SELECT S.uid, S.konailaNodeType, S.codeDisplayWhole, F.feature"
								+ "	FROM selectionUnits as S, features as F"
								+ "	WHERE S.cid=?  AND S.uid=? AND S.cid=F.cid AND S.uid=F.uid"
								+ " AND (F.feature='Extends' "
								+ "      OR F.feature='Implements'"
								+ "      OR F.feature='AnonymousClassCreation')");


		for( Entry<Integer,MethodSignature> e : uidToMethods.entrySet()) {
			int uid = e.getKey();
			MethodSignature method = e.getValue();
			int enclosingUid = method.getEnclosingUid();			

			if( enclosingUid != -1 ) {
				s.setInt(1, cid); 
				s.setInt(2, enclosingUid);			
				ResultSet r = s.executeQuery();

				while(r.next()) {
					String classProperty = r.getString(4);

					method.setEnclosingUid(enclosingUid);

					if( classProperty.equals("Extends")) {
						method.setEnclosingTypeHasExtends(true);
					} else if( classProperty.equals("Implements") ) {
						method.setEnclosingTypeHasImplements(true);
					} else if( classProperty.equals("AnonymousClassCreation") ) {
						method.setEnclosingTypeIsAnonymousClass(true);
					}				 		 
				}		
			}
		}		
	}

	public static List<Integer> getMethodSignatures(Connection conn, int cid) throws SQLException, IOException {
		return getSelectionUnits(conn, cid, "MethodSignature");
	}

	public static List<Integer> getSelectionUnits(Connection conn, int cid, String konailaNodeType) throws SQLException, IOException {		
		List<Integer> uids = new ArrayList<Integer>();		
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT uid "
								+ " FROM selectionUnits as S "
								+ "WHERE S.cid=? AND S.konailaNodeType=?");		
		int i = 1;
		s.setInt(i, cid); i+=1;
		s.setString(i, konailaNodeType); i+=1;
		ResultSet r = s.executeQuery();

		while(r.next()) {
			int uid = r.getInt(1);
			uids.add(uid);
		}

		return uids;
	}

	public static List<Integer> getAllUids(Connection conn, int cid) throws SQLException, IOException {		
		List<Integer> uids = new ArrayList<Integer>();		
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT DISTINCT uid "
								+ " FROM selectionUnits as S "
								+ "WHERE S.cid=?");

		s.setInt(1, cid);
		ResultSet r = s.executeQuery();

		while(r.next()) {
			int uid = r.getInt(1);
			uids.add(uid);
		}

		return uids;
	}
	
	public static class Signature extends ScoringSelectionUnitImpl {

		protected String codeDisplayWhole;
		protected boolean hasQueryTerms;
		
//		static Category category = Category.SingleApiCallBack;
		static Category category = Category.Structure;
		
		public Signature(int cid, int uid, String codeDisplayWhole) {
			super(cid, uid);
		}		

		public Category getCategory () { return category; }
		
		public void setHasQueryTerms(boolean b) {
			this.hasQueryTerms = b; 
		}
		
		public boolean hasQueryTerms() {
			return hasQueryTerms;
		}
	}
	
	public static class ClassSignature extends Signature {
		
		boolean hasExtends = false;
		boolean hasImplements = false;
		boolean isPublic = false;

//		 static Category category = Category.SingleApiCallBack;
		static Category category = Category.Structure;
		 
		public ClassSignature(int cid, int uid, String codeDisplayWhole) {
			super(cid, uid, codeDisplayWhole);
		}
		
		@Override
		public Category getCategory() {
			return category;
		}
		
		public boolean hasExtends() {
			return hasExtends;
		}
		
		public boolean hasImplements() {
			return hasImplements;
		}
		
		public boolean isPublic() {
			return isPublic;
		}
		
		public void setExtends(boolean b) {
			hasExtends = b;
		}
		
		public void setImplements(boolean b) {
			hasImplements = b;
		}
		
		public void setIsPublic(boolean b) {
			isPublic = b;
		}
	}

	public static class MethodSignature extends Signature  {
		private int enclosingTypeUid = -1;
		private boolean enclosingTypeHasExtends = false;
		private boolean enclosingTypeHasImplements = false;
		private boolean enclosingTypeIsAnonymousClass = false;
		private boolean isStatic = false;
		private boolean isPublic = false;
		private boolean isProtected = false;
		private boolean hasOverrideTag = false;
//		private String codeDisplayWhole;
		
//		 static Category category = Category.SingleApiCallBack;

		public enum EnclosingTypeFeature {
			HasEnclosingClassWithInheritance,
			HasEnclosingClassNoInheritance,
			HasNoEnclosingClass,
			HasEnclosingAnonymousClass
		}

		public enum MethodFeature {
			Override,
			Static,
			NotOverrideMemberPublicOrProtected,
			NotOverrideMemberNotPublicOrProtected
		}

		public MethodSignature(int cid, int uid, int enclosingTypeUid, String codeDisplayWhole) {
			super(cid,uid, codeDisplayWhole);
			this.enclosingTypeUid = enclosingTypeUid;
			this.codeDisplayWhole = codeDisplayWhole;
		}

//		public Category getCategory () { return category; }

		public EnclosingTypeFeature getEnclosingTypeFeature() {
			if( hasEnclosingClassWithInheritance() ) {
				return EnclosingTypeFeature.HasEnclosingClassWithInheritance;
			} else if( hasEnclosingClass() && !hasEnclosingClassWithInheritance() ) {
				return EnclosingTypeFeature.HasEnclosingClassNoInheritance;
			} else if( !hasEnclosingClass() ) {
				return EnclosingTypeFeature.HasNoEnclosingClass;
			} else {
				throw new RuntimeException();
			}
		}			

		public IncludeOrNot getMethodConfiguration() {
			if( getMethodFeature().equals(MethodFeature.Override)) {
				// INCLUDE STRONG
				return IncludeOrNot.Include;
			} else if( getMethodFeature().equals(MethodFeature.Static)
					|| getMethodFeature().equals(MethodFeature.NotOverrideMemberNotPublicOrProtected)) {
				// EXCLUDE STRONG
				return IncludeOrNot.Exclude; 
			} else if( getMethodFeature().equals(MethodFeature.NotOverrideMemberPublicOrProtected)) {
				
				if( getEnclosingTypeFeature().equals(EnclosingTypeFeature.HasEnclosingClassWithInheritance)) {
					// INCLUDE STRONG
					return IncludeOrNot.Include;
				} else if( getEnclosingTypeFeature().equals(EnclosingTypeFeature.HasEnclosingClassNoInheritance)) {
					// EXCLUDE PROBABLY
					return IncludeOrNot.ExcludeProbably;			
				} else if( getEnclosingTypeFeature().equals(EnclosingTypeFeature.HasNoEnclosingClass)) {
					// INCLUDE PROBABLY	
					return IncludeOrNot.IncludeProbably;
				}
			}
			throw new RuntimeException();
		}
		
		public MethodFeature getMethodFeature() {
			if( hasOverrideTag()) {
				return MethodFeature.Override;
			} else if( isStatic() ) {
				return MethodFeature.Static;
			} else {
				if( isPublicOrProtected()) {
					return MethodFeature.NotOverrideMemberPublicOrProtected;
				} else {
					return MethodFeature.NotOverrideMemberNotPublicOrProtected;
				}
			}
		}

		public boolean hasEnclosingClass() {
			return enclosingTypeUid!=-1;
		}

		boolean isStatic () {
			return isStatic;
		}

		public boolean hasEnclosingClassWithInheritance() {
			return enclosingTypeHasExtends || enclosingTypeHasImplements
					|| enclosingTypeIsAnonymousClass;
		}

		boolean isPublicOrProtected() {
			return isPublic || isProtected;
		}

		boolean isPublic() {
			return isPublic;
		}

		boolean isProtected() {
			return isProtected;
		}

		boolean enclosingTypeHasImplements() {
			return enclosingTypeHasImplements;
		}

		boolean enclosingTypeHasExtends() {
			return enclosingTypeHasExtends;
		}
		
		boolean enclosingTypeIsAnonymousClass() {
			return enclosingTypeIsAnonymousClass;
		}

		public boolean hasOverrideTag() {
			return hasOverrideTag;
		}
		
		public String getCodeDisplayWhole() {
			return codeDisplayWhole;
		}

		public int getEnclosingUid() {
			return enclosingTypeUid;
		}
		
//		public ClassSignature getClassSignature() {
//			return enclosingType;
//		}

		public void setEnclosingTypeHasExtends(boolean enclosingClassHasExtends) {
			this.enclosingTypeHasExtends = enclosingClassHasExtends;
		}

		public void setEnclosingTypeHasImplements(boolean enclosingClassHasImplements) {
			this.enclosingTypeHasImplements = enclosingClassHasImplements;
		}

		public void setEnclosingTypeIsAnonymousClass(boolean enclosingTypeIsAnonymousClass) {
			this.enclosingTypeIsAnonymousClass = enclosingTypeIsAnonymousClass;
		}
		
		public void setStatic(boolean isStatic) {
			this.isStatic = isStatic;
		}

		public void setPublic(boolean isPublic) {
			this.isPublic = isPublic;
		}

		public void setProtected(boolean isProtected) {
			this.isProtected = isProtected;
		}

		public void setEnclosingUid(int uid) {
			this.enclosingTypeUid = uid;
		}
		
		public void setOverrideTag(boolean hasOverrideTag) {
			this.hasOverrideTag = hasOverrideTag;
		}

//		public void setEnclosingType(ClassSignature s) {
//			this.enclosingType = s;
//		}
		
		@Override
		public String toString() {
			return codeDisplayWhole;
		}
	}	
}
