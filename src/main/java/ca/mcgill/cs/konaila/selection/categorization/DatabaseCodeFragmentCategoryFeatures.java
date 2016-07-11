package ca.mcgill.cs.konaila.selection.categorization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.parse.JavaRoot;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection;
import ca.mcgill.cs.konaila.selection.features.IncludeOrNot;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection.MethodSignature;

public class DatabaseCodeFragmentCategoryFeatures {
		
	public static void populate(Connection conn) throws SQLException, IOException {

		conn.setAutoCommit(false);
		
		PreparedStatement s = conn.prepareStatement(
						"INSERT INTO codeFragmentCategoryFeatures values(?,?,?,?,?)");
				
        List<Integer> cids = DatabaseGetCodeFragments.getJavaCodeFragmentsCidsLongEnough(conn);
        for( Integer cid : cids ) {
        	        	
        	s.setInt(1, cid);
        	s.setString(2, "productionRule");
        	s.setInt(3, -1);
        	s.setFloat(4, -1);        	
        	s.setString(5, selectProductionRuleValue(conn, cid));
    		s.executeUpdate();
    		
        	s.setInt(1, cid);
        	s.setString(2, "numberOfStructuralUnits");
        	s.setInt(3, selectNumberOfSignatures(conn, cid));
        	s.setFloat(4, -1);        	
        	s.setString(5, "");
    		s.executeUpdate();
   		
    		int calls = selectNumberOfSelectionUnitsWithCalls(conn, cid);
    		int totalMinusComments = selectNumberOfSelectionUnits(conn, cid) 
    				- selectNumberOfCommentsSelectionUnit(conn, cid);
    		float percentageCalls = (float)calls/(float)totalMinusComments; 
        	s.setInt(1, cid);
        	s.setString(2, "percentageOfUnitsWithCalls");
        	s.setInt(3, -1);
        	s.setFloat(4, percentageCalls);
        	s.setString(5, "");
    		s.executeUpdate();	
    		
    		boolean hasAnonymousClass = selectHasAnonymousClassInstantiation(conn,cid);
        	s.setInt(1, cid);
        	s.setString(2,  "hasAnonymousClass");
        	s.setInt(3, hasAnonymousClass ? 1 : 0);
        	s.setFloat(4, -1);
        	s.setString(5, "");
    		s.executeUpdate();	    		
    		
    		boolean hasSubclassOfApi = selectHasSubclassOfApi(conn, cid);
        	s.setInt(1, cid);
        	s.setString(2, "hasSubclassClassOfApi");
        	s.setInt(3, hasSubclassOfApi ? 1 : 0);
        	s.setFloat(4, -1);
        	s.setString(5, "");
    		s.executeUpdate();	       		
    		
    		int controlFlowCounts = selectControlFlowCounts(conn,cid);
        	s.setInt(1, cid);
        	s.setString(2, "controlFlowCounts");
        	s.setInt(3, controlFlowCounts);
        	s.setFloat(4, -1);
        	s.setString(5, "");
    		s.executeUpdate();	
    		
    		float percentageWithinControlFlow = selectSizeOfControlFlow(conn,cid);
        	s.setInt(1, cid);
        	s.setString(2, "percentageWithinForWhile");
        	s.setInt(3, -1);
        	s.setFloat(4, percentageWithinControlFlow);
        	s.setString(5, "");
    		s.executeUpdate();
    		
    		int numberOfCallBacks = selectCallBacks(conn,cid);
        	s.setInt(1, cid);
        	s.setString(2, "numberOfCallBacks");
        	s.setInt(3, numberOfCallBacks);
        	s.setFloat(4, -1);
        	s.setString(5, "");
    		s.executeUpdate();
    		
    		int numberCallWithQueryTerms = selectNumberOfSelectionUnitsWithCallsAndQueryTerms(conn, cid);
//    		float percentageQueryCalls = (float)numberCallWithQueryTerms/(float)total; 
        	s.setInt(1, cid);
        	s.setString(2, "numberOfUnitsWithCallsAndQueryTerms");
        	s.setInt(3, numberCallWithQueryTerms);
        	s.setFloat(4, -1);
        	s.setString(5, "");
    		s.executeUpdate();
    		
    		int numberOfStructureWithQueryTerms = selectNumberOfSelectionUnitsWithStructureAndQueryTerms(conn, cid);
//    		float percentageQueryCalls = (float)numberCallWithQueryTerms/(float)total; 
        	s.setInt(1, cid);
        	s.setString(2, "numberOfStructureWithQueryTerms");
        	s.setInt(3, numberOfStructureWithQueryTerms);
        	s.setFloat(4, -1);
        	s.setString(5, "");
    		s.executeUpdate();
    		
    		int numberOfLiterals = selectNumberOfLiterals(conn, cid);
    		float percentageLiterals = (float)numberOfLiterals/(float)totalMinusComments; 
        	s.setInt(1, cid);
        	s.setString(2, "percentageOfLiterals");
        	s.setInt(3, -1);
        	s.setFloat(4, percentageLiterals);
        	s.setString(5, "");
    		s.executeUpdate();
    		
    		int numberOfSignatures = selectNumberOfSignatures(conn, cid);
    		float percentageSignaturesOrCalls = (float)(numberOfSignatures + calls) / 
    				(float)totalMinusComments; 
        	s.setInt(1, cid);
        	s.setString(2, "percentageOfSignaturesOrCalls");
        	s.setInt(3, -1);
        	s.setFloat(4, percentageSignaturesOrCalls);
        	s.setString(5, "");
    		s.executeUpdate();
        }

		conn.commit();
		s.close();
		
	}

	private static int selectNumberOfSignatures(Connection conn, int cid) throws SQLException, IOException {
		int count = -1;
		PreparedStatement s = conn.prepareStatement(
			"SELECT count(*) FROM features S WHERE feature LIKE '%Signature' "
			+ " AND cid=? ");
		
		s.setInt(1,cid);
		ResultSet r = s.executeQuery();
		
		if( r.next() ) {
			count = r.getInt(1);
		}
		
		s.close();
		return count;
	}
	
	private static String selectProductionRuleValue(Connection conn, int cid) throws SQLException, IOException {
		String productionRule = "";
		PreparedStatement s = conn.prepareStatement(
			"SELECT comment FROM codeFragmentParsingAttributes "
			+ " WHERE cid=? ");
		
		s.setInt(1,cid);
		ResultSet r = s.executeQuery();
		
		if( r.next() ) {
			productionRule = r.getString("comment");
		}
		
		s.close();
		return productionRule;
	}
	
	private static int selectNumberOfSelectionUnitsWithCalls(Connection conn, int cid) throws SQLException, IOException {
		int selectionUnitsWithCalls = -1;
		PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT uid) " 
				+ " FROM features WHERE (feature='CallCount' OR feature='Constructor') AND cid=? " );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			selectionUnitsWithCalls = r.getInt(1);
		}
		
		s.close();
		return selectionUnitsWithCalls;
	}
	
	private static int selectNumberOfSelectionUnits(Connection conn, int cid) throws SQLException, IOException {
		int selectionUnits = -1;
		PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT uid) " 
				+ " FROM selectionUnits WHERE cid=? " );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			selectionUnits = r.getInt(1);
		}
		
		s.close();
		return selectionUnits;
	}
	
	private static int selectNumberOfCommentsSelectionUnit(Connection conn, int cid) throws SQLException, IOException {
		int selectionUnits = -1;
		PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT uid) " 
				+ " FROM features WHERE cid=? AND feature LIKE 'Comment'" );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			selectionUnits = r.getInt(1);
		}
		
		s.close();
		return selectionUnits;
	}
	
	private static boolean selectHasAnonymousClassInstantiation(Connection conn, int cid) throws SQLException, IOException {
		int numberOfClasses = -1;
		PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT F1.uid) "
				+ " FROM features F1, features F2 "
				+ " WHERE F1.cid=F2.cid AND F1.uid=F2.uid "
				+ " AND F1.cid=? "
				+ " AND "
//				+ " ((F1.feature='TypeSignature' AND (F2.feature='Extends' OR F2.feature='Implements'))"
//				+ " OR "
				+ "(F1.feature='AnonymousClassCreation' OR F2.feature='AnonymousClassCreation')"
//				+ ")"
				);
		s.setInt(1, cid);		
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			numberOfClasses = r.getInt(1);
		}
		
		s.close();
		return numberOfClasses > 0;
	}
	
	private static boolean selectHasSubclassOfApi(Connection conn, int cid) throws SQLException, IOException {
		int numberOfClasses = -1;
		PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT F1.uid) "
				+ " FROM features F1, features F2 "
				+ " WHERE F1.cid=F2.cid AND F1.uid=F2.uid "
				+ " AND F1.cid=? "
				+ " AND F1.feature='TypeSignature' AND (F2.feature='Extends' OR F2.feature='Implements')"
				);
		s.setInt(1, cid);		
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			numberOfClasses = r.getInt(1);
		}
		
		s.close();
		return numberOfClasses > 0;
	}

	private static int selectControlFlowCounts(Connection conn, int cid) throws SQLException, IOException {
		int numberControlFlows = -1;
		PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT uid) "
				+ " FROM features F1 "
				+ " WHERE cid=? AND "
				+ " (feature='ForWrapper' OR feature='IfWrapper' OR feature='WhileWrapper' OR feature='SwitchWrapper')");
		s.setInt(1, cid);		
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			numberControlFlows = r.getInt(1);
		}
		
		s.close();
		return numberControlFlows;
	}
	
	private static float selectSizeOfControlFlow(Connection conn, int cid) throws SQLException, IOException {

		int controlFlowStart = -1;
		int controlFlowEnd = -1;
		float percentageWithinControlFlow = 0;
		
		PreparedStatement s = conn.prepareStatement("SELECT min(unitLineStart), max(unitLineEnd) "
				+ " FROM features F1, selectionUnits S "
				+ " WHERE F1.cid=? AND (feature='ForWrapper' OR feature='WhileWrapper') "
				+ " AND F1.cid=S.cid AND F1.uid=S.uid " );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			controlFlowStart = r.getInt(1);
			controlFlowEnd = r.getInt(2);
		}		
		s.close();
		
		if( controlFlowStart != 0 && controlFlowEnd != 0 ) {

			int codeStart = -1;
			int codeEnd = -1;
			PreparedStatement s2 = conn.prepareStatement("SELECT min(unitLineStart), max(unitLineEnd) "
					+ " FROM selectionUnits S "
					+ " WHERE S.cid=?" );
			s2.setInt(1,cid);
			
			ResultSet r2 = s2.executeQuery();
			if( r2.next() ) {
				codeStart = r2.getInt(1);
				codeEnd = r2.getInt(2);
			}
			s2.close();
			
			percentageWithinControlFlow =
					((float)(controlFlowEnd-controlFlowStart+1)) / ((float)(codeEnd-codeStart+1));
		}
		return percentageWithinControlFlow;		
	}
	
	private static int selectCallBacks(Connection conn, int cid) throws SQLException, IOException {
		Map<Integer,MethodSignature> uidToMethods = DatabaseSignatureSelection.
				selectImportantMethodSignatures(conn, cid);
		int numberOfCallBacks = 0;
		for( Entry<Integer,MethodSignature> e : uidToMethods.entrySet()) {
			int uid = e.getKey();
			MethodSignature signature = e.getValue();
			if( signature.getMethodConfiguration().ordinal() >= IncludeOrNot.IncludeProbably.ordinal() ) {
				numberOfCallBacks +=1;
			}
		}
		return numberOfCallBacks;
	}
	
	private static int selectNumberOfSelectionUnitsWithCallsAndQueryTerms(Connection conn, int cid) throws SQLException, IOException {
		int selectionUnitsWithCalls = -1;
		PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT Fc.uid) "
				+ " FROM features Fq, features Fc "
				+ " WHERE Fq.uid=Fc.uid AND Fq.cid=Fc.cid AND "
				+ " (Fq.feature LIKE '%Query%' AND (Fc.feature='CallCount' OR Fc.feature='Constructor')) "
				+ " AND Fq.cid=?" );
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			selectionUnitsWithCalls = r.getInt(1);
		}
		
		s.close();
		return selectionUnitsWithCalls;
	}
	
	
	private static int selectNumberOfSelectionUnitsWithStructureAndQueryTerms(Connection conn, int cid) throws SQLException, IOException {
		int selectionUnitsWithCalls = -1;
		PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT Fc.uid) "
				+ " FROM features Fq, features Fc "
				+ " WHERE Fq.uid=Fc.uid AND Fq.cid=Fc.cid AND Fq.cid=? AND " 
				+ " (Fq.feature LIKE '%Query%' "
				+ "   AND (Fc.feature='TypeSignature' OR Fc.feature='InterfaceSignature' OR "
				+ "        Fc.feature='MethodSignature' OR Fc.feature='ConstructorSignature'))");
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			selectionUnitsWithCalls = r.getInt(1);
		}
		
		s.close();
		return selectionUnitsWithCalls;
	}

	private static int selectNumberOfLiterals(Connection conn, int cid) throws SQLException, IOException {
		int selectionUnitsWithCalls = -1;
		PreparedStatement s = conn.prepareStatement("SELECT count(DISTINCT uid) "
				+ " FROM features "
				+ " WHERE cid=? AND "
				+ " (feature LIKE 'BooleanLiteral' OR feature LIKE 'CharacterLiteral' "
				+ " OR feature LIKE 'FloatLiteral' OR feature LIKE 'IntegerLiteral' "
				+ " OR feature LIKE 'StringLiteral')");
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			selectionUnitsWithCalls = r.getInt(1);
		}
		
		s.close();
		return selectionUnitsWithCalls;
	}

	
	public static JavaRoot getProductionRule(Connection conn, int cid) throws SQLException, IOException {
		String value = "";
		PreparedStatement s = conn.prepareStatement("SELECT stringValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='productionRule'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getString(1);
		}		
		s.close();
		
		return JavaRoot.valueOf(value);
	}
	
	public static int getNumberOfStructuralUnits(Connection conn, int cid) throws SQLException, IOException {
		int value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT intValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='numberOfStructuralUnits'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getInt(1);
		}		
		s.close();
		return value;		
	}
	
	public static float getPercentageOfUnitsWithCalls(Connection conn, int cid) throws SQLException, IOException {
		float value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT floatValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='percentageOfUnitsWithCalls'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getFloat(1);
		}		
		s.close();
		
		return value;
	}
	
	public static boolean hasAnonymousClass(Connection conn, int cid) throws SQLException, IOException {
		int value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT intValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='hasAnonymousClass'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getInt(1);
		}		
		s.close();
		
		return value == 1;
	}

	
	public static boolean hasSubclassOfApi(Connection conn, int cid) throws SQLException, IOException {
		int value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT intValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='hasSubclassOfApi'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getInt(1);
		}		
		s.close();
		
		return value == 1;
	}
	
	public static int getControlFlowCounts(Connection conn, int cid) throws SQLException, IOException {
		int value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT intValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='controlFlowCounts'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getInt(1);
		}		
		s.close();
		
		return value;
	}

	public static float getPercentageWithinForWhile(Connection conn, int cid) throws SQLException, IOException {
		float value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT floatValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='percentageWithinForWhile'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getFloat(1);
		}		
		s.close();
		
		return value;
	}
	
	public static int getNumberOfCallBacks(Connection conn, int cid) throws SQLException, IOException {
		int value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT intValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='numberOfCallBacks'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getInt(1);
		}		
		s.close();
		
		return value;
	}
	

	public static int getNumberOfUnitsWithCallsAndQueryTerms(Connection conn, int cid) throws SQLException, IOException {
		int value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT intValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='numberOfUnitsWithCallsAndQueryTerms'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getInt(1);
		}		
		s.close();
		
		return value;
	}
	
	public static int getNumberOfStructureWithQueryTerms(Connection conn, int cid) throws SQLException, IOException {
		int value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT intValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='numberOfStructureWithQueryTerms'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getInt(1);
		}		
		s.close();
		
		return value;
	}


	public static float getPercentageWithSignaturesOrCalls(Connection conn, int cid) throws SQLException, IOException {
		float value = -1;
		PreparedStatement s = conn.prepareStatement("SELECT floatValue "
				+ " FROM codeFragmentCategoryFeatures "
				+ " WHERE cid=? AND feature='percentageOfSignaturesOrCalls'" );
		s.setInt(1,cid);
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			value = r.getFloat(1);
		}		
		s.close();
		
		return value;
	}
	
}
