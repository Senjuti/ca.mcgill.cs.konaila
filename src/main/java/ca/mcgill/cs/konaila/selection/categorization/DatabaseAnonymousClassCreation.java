package ca.mcgill.cs.konaila.selection.categorization;

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

import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection.MethodSignature;

public class DatabaseAnonymousClassCreation {
	

	public static Map<Integer,AnonymousClassCreationSelectionUnit> selectAnonymoucClassCreationUnits(Connection conn,
			int cid) throws SQLException, IOException {
		Map<Integer,AnonymousClassCreationSelectionUnit> uidToCf = new HashMap<Integer,AnonymousClassCreationSelectionUnit>();
		PreparedStatement s = conn.prepareStatement("SELECT DISTINCT uid, intValue, feature " 
				+ " FROM features"
				+ " WHERE feature='AnonymousClassCreation'"
				+ " AND cid=? " );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int uid = r.getInt(1);
			
			AnonymousClassCreationSelectionUnit api = new AnonymousClassCreationSelectionUnit(cid, uid);			
			uidToCf.put(uid,api);
		}
		
		s.close();		
		return uidToCf;
	}
	
	public static Map<Integer,MethodSignature> selectMethods(Connection conn, 
			int cid, Map<Integer,AnonymousClassCreationSelectionUnit> uidToAnon) throws SQLException, IOException {
		
		Map<Integer,MethodSignature> uidToMethod = new HashMap<Integer,MethodSignature>();

		PreparedStatement s = conn
				.prepareStatement(
						"	SELECT S.uid, S.konailaNodeType, S.codeDisplayWhole, F.feature, S.enclosingUnitUid"
								+ " FROM selectionUnits as S, features as F"
								+ " WHERE S.cid=?  AND S.cid=F.cid AND S.uid=F.uid  "
								+ " AND F.feature like 'Modifier%'"
								+ " AND (S.konailaNodeType='MethodSignature')"
								+ " AND S.enclosingUnitUid=?");

		s.setInt(1, cid);		

		for( Entry<Integer,AnonymousClassCreationSelectionUnit> e : uidToAnon.entrySet()) {		

			AnonymousClassCreationSelectionUnit anon = e.getValue();
			int outerUid = anon.getUid();
			s.setInt(2, outerUid);
			
			ResultSet r = s.executeQuery();
	
			while(r.next()) {
				int uid = r.getInt(1);
				String methodModifier = r.getString(4);
				String codeDisplayWhole = r.getString(3);
				int enclosingTypeUid = r.getInt(5);
	
				if( !uidToMethod.containsKey(uid)) {
					MethodSignature method = new MethodSignature(cid,uid,enclosingTypeUid,codeDisplayWhole);
					uidToMethod.put(uid,method);
					method.setEnclosingUid(outerUid);
					method.setEnclosingTypeIsAnonymousClass(true);
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
		}
		return uidToMethod;
	}
	
	public static void populateQueryTerms(Connection conn, int cid,
			Map<Integer,MethodSignature> methodSignatures, 
			Map<Integer,AnonymousClassCreationSelectionUnit> anonymousClasses )
			throws SQLException, IOException {
		
		PreparedStatement s = conn.prepareStatement(
				"SELECT DISTINCT Fc.uid, Fc.unit, Fc.intValue, Fq.feature, Fq.intValue "
				+ " FROM features Fq, features Fc "
				+ " WHERE Fq.uid=Fc.uid AND Fq.cid=Fc.cid AND "
				+ " (Fq.feature LIKE '%Query%') "
				+ " AND (Fc.feature='MethodSignature' "
				+ "   OR Fc.feature='AnonymousClassCreation') "
				+ " AND Fq.cid=?" );
		s.setInt(1, cid);
				
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int uid = r.getInt(1);
			
			MethodSignature methodSignature = methodSignatures.get(uid);
			AnonymousClassCreationSelectionUnit anonymousClass = 
					anonymousClasses.get(uid);
			if( methodSignature != null) {
				methodSignature.setHasQueryTerms(true);
			} else if( anonymousClass != null ) {
				anonymousClass.setHasQueryTerms(true);
			} else {
				throw new RuntimeException();
			}
		}
		
		s.close();		
		
	}

	public static class AnonymousClassCreationSelectionUnit extends ScoringSelectionUnitImpl {

		 boolean hasQueryTerms = false;
		 int callCount = 0;
		 boolean isConstructor = false;
		 List<Integer> methodDeclarations = new ArrayList<Integer>();
//		 IncludeOrNot includeOrNot;
		 int score;
		 
		 static Category category = Category.ClassDefinedAndInstantiated;
		 
		public AnonymousClassCreationSelectionUnit(int cid, int uid) {
			super(cid,uid);
		}
		public Category getCategory() {
			return category;
		}
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
		public void addMethodDeclaration(Integer methodUid) {
			methodDeclarations.add(methodUid);
		}
		public List<Integer> getMethodDeclaration() {
			return methodDeclarations;
		}
	}
}
