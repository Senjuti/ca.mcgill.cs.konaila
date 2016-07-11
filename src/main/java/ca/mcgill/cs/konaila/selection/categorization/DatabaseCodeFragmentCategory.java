package ca.mcgill.cs.konaila.selection.categorization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.parse.JavaRoot;

public class DatabaseCodeFragmentCategory {
			
	
	public static void main(String[] args) throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		Connection c = Database.getInstance().getConnection();
		
		int cid = 1083;
		classifyCodeFragment(c,cid);
		
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");		
	}
	
	
	public static void classifyCodeFragments(Connection conn) throws SQLException, IOException {

		PreparedStatement s = conn.prepareStatement(
						"INSERT INTO codeFragmentCategory values(?,?,?)");	
		conn.setAutoCommit(false);
				
		Collection<Integer> cids = DatabaseGetCodeFragments.getJavaCodeFragmentsCidsLongEnough(conn);
					
		for( int cid : cids ) {
			
			Map<Category,CategoryCertainty> categories = classifyCodeFragment(conn, cid);
						
			DatabaseCodeFragmentCategory.populate(conn, s, cid, categories);
		}

		s.close();
		conn.commit();
	}

	public static Map<Category,CategoryCertainty> classifyCodeFragment(Connection conn, int cid) throws SQLException, IOException {
		Map<Category,CategoryCertainty> categories = Category.getMap();
		
		// Structure vs SingleApiCallBack
		if (DatabaseCodeFragmentCategoryFeatures.getNumberOfStructuralUnits(conn, cid) > 0 ) {
			int numberOfCallBacks = DatabaseCodeFragmentCategoryFeatures.getNumberOfCallBacks(conn, cid);
			int numberOfStructureWithQueryTerms = DatabaseCodeFragmentCategoryFeatures.getNumberOfStructureWithQueryTerms(conn, cid);
			if( numberOfCallBacks > 3 ) { 
				categories.put(Category.Structure, CategoryCertainty.Yes);
			} else if( numberOfCallBacks > 1) {
				if( numberOfStructureWithQueryTerms > 0 ) {
					categories.put(Category.Structure, CategoryCertainty.Yes);
				} else {
					categories.put(Category.Structure, CategoryCertainty.ProbablyYes);
				}
			} else if( numberOfCallBacks == 1 ) {
//				categories.put(Category.SingleApiCallBack, CategoryCertainty.Yes );
				categories.put(Category.Structure, CategoryCertainty.Yes );
			} else if( numberOfCallBacks == 0 ) {
//				categories.put(Category.SingleApiCallBack, CategoryCertainty.No);
				categories.put(Category.Structure, CategoryCertainty.No);
			}
		}
		
		// ClassDefinedAndInstantiated
		if( DatabaseCodeFragmentCategoryFeatures.hasAnonymousClass(conn, cid)) {
			categories.put(Category.ClassDefinedAndInstantiated, CategoryCertainty.Yes);						
		} else if( DatabaseCodeFragmentCategoryFeatures.getProductionRule(conn, cid) != JavaRoot.JavaCompilationUnit 
				&& DatabaseCodeFragmentCategoryFeatures.hasSubclassOfApi(conn, cid)){
			categories.put(Category.ClassDefinedAndInstantiated, CategoryCertainty.ProbablyYes);
		} else {
			categories.put(Category.ClassDefinedAndInstantiated,  CategoryCertainty.ProbablyNo);
		}
		
		// ControlFlowCentric
		int numberControlFlowCounts = DatabaseCodeFragmentCategoryFeatures.getControlFlowCounts(conn, cid);
		float percentageWithinForWhile = DatabaseCodeFragmentCategoryFeatures.getPercentageWithinForWhile(conn, cid);
		float percentageWithSignatureOrCalls = DatabaseCodeFragmentCategoryFeatures.getPercentageWithSignaturesOrCalls(conn, cid);
		if( (numberControlFlowCounts > 3 && percentageWithinForWhile > 0.5)
				|| percentageWithinForWhile > 0.75
				|| numberControlFlowCounts > 8 ) {
			categories.put(Category.ControlFlowCentric, CategoryCertainty.Yes);								
		} else if( numberControlFlowCounts < 1 && percentageWithinForWhile == 0) {
			categories.put(Category.ControlFlowCentric, CategoryCertainty.No);
		} else if( numberControlFlowCounts >= 3 || percentageWithinForWhile > 0.2 ){
			if( percentageWithSignatureOrCalls < 0.5 ) {
				categories.put(Category.ControlFlowCentric, CategoryCertainty.Yes);
			} else {
				categories.put(Category.ControlFlowCentric, CategoryCertainty.ProbablyYes);
			}
		} else { 
			categories.put(Category.ControlFlowCentric, CategoryCertainty.ProbablyNo);
		}
		
		// ApiCalls
		float percentageOfApiCalls = DatabaseCodeFragmentCategoryFeatures.getPercentageOfUnitsWithCalls(conn, cid);
		int numberOfUnitsWithCallsAndQueryTerms = DatabaseCodeFragmentCategoryFeatures.getNumberOfUnitsWithCallsAndQueryTerms(conn, cid);
		if( percentageOfApiCalls > 0.8) {
			categories.put(Category.ApiCalls,  CategoryCertainty.Yes); 						
		} else if( percentageOfApiCalls > 0.2 ) {
			if( numberOfUnitsWithCallsAndQueryTerms > 1 ) {
				categories.put(Category.ApiCalls, CategoryCertainty.Yes);
			} else {
				categories.put(Category.ApiCalls, CategoryCertainty.ProbablyYes);
			}
		} else {
			categories.put(Category.ApiCalls, CategoryCertainty.No);
		}
		return categories;
	}
	
	public static void populate(Connection conn, PreparedStatement s,  
			int cid, Map<Category,CategoryCertainty> categories) throws SQLException, IOException {
	
        	        	
		for( Entry<Category,CategoryCertainty> c : categories.entrySet() ){
	    	s.setInt(1, cid);
	    	s.setString(2, c.getKey().name());
	    	s.setInt(3, c.getValue().ordinal());
			s.executeUpdate();   		
		}
 
		
	}
	
	public static int selectMaxCertainty(Connection conn, int cid) throws SQLException, IOException {
		int max = -1;
		PreparedStatement s = conn.prepareStatement("SELECT max(certainty) FROM codeFragmentCategory"
				+ " WHERE cid=?");
		s.setInt(1, cid);	
		
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			max = r.getInt(1);
		}
		
		s.close();
		return max;
	}
	
	public static List<String> selectTopCategories(Connection conn, int certainty, int cid) throws SQLException, IOException {
		List<String> categories = new ArrayList<String>();
		PreparedStatement s = conn.prepareStatement("SELECT category FROM codeFragmentCategory"
				+ " WHERE certainty>=? AND cid=?");
		s.setInt(1, certainty);	
		s.setInt(2,  cid);
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			String category = r.getString(1);
			categories.add(category);
		}
		
		s.close();
		return categories;
	}
	
	public static List<Integer> selectCids(Connection conn) throws SQLException, IOException {
		List<Integer> cids = new ArrayList<Integer>();
		PreparedStatement s = conn.prepareStatement("SELECT DISTINCT cid FROM codeFragmentCategory");	
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			int cid = r.getInt(1);
			cids.add(cid);
		}
		
		s.close();
		return cids;		
	}

}