package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.presentation.Tabs;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.DatabasePredictions.SelectedElement;
import ca.mcgill.cs.konaila.selection.categorization.Category;
import ca.mcgill.cs.konaila.selection.categorization.CategoryCertainty;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseAnonymousClassCreation;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseAnonymousClassCreation.AnonymousClassCreationSelectionUnit;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseCodeFragmentCategory;

public class MainSelectClassInstantiated {
	
	public static final String DB = Main.DB;
	public static final String dir = "code-fragment-categorization/";
	
	public static void main(String[] args) throws Exception {
		
		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();
		summarize(conn);
		
		Database.getInstance().closeConnection();
	}

	public static void summarize(Connection conn) throws SQLException, IOException {

		List<Integer> cids = DatabaseCodeFragmentCategory.selectCids(conn);
		
		for( Integer cid : cids ) {						
			
			int max = DatabaseCodeFragmentCategory.selectMaxCertainty(conn,cid);
			if( max > CategoryCertainty.ProbablyNo.ordinal()) {
				List<String> topCategories = DatabaseCodeFragmentCategory.selectTopCategories(conn, max, cid);
	
//				if( topCategories.size() == 1 ) {
					String categoryDisplayStr = topCategories.iterator().next();
					Category category = Category.valueOf(categoryDisplayStr);
					if( category == Category.ClassDefinedAndInstantiated) {
	
						System.out.println("--------code fragment " + cid + "-----------");
						
						String code = DatabaseGetCodeFragments.getCodeFragment(conn, cid);
						System.out.println(code);
						
						generateSummary(conn, cid);
					}
//				}
			}
		}		
	}
	
	public static String generateSummary(Connection c, int cid) 
			 throws SQLException, IOException {
				
		Map<Integer,AnonymousClassCreationSelectionUnit> anon = DatabaseAnonymousClassCreation.selectAnonymoucClassCreationUnits(c, cid);
		DatabaseAnonymousClassCreation.selectMethods(c, cid, anon);		
		
		Collection<Integer> selectedUids = new ArrayList<Integer>();
		for( Map.Entry<Integer, AnonymousClassCreationSelectionUnit> e : anon.entrySet()) {	
			AnonymousClassCreationSelectionUnit a = e.getValue();
			selectedUids.add(a.getUid());
			for( Integer method : a.getMethodDeclaration()) {
				selectedUids.add(method);				
			}
		}
				
		List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, selectedUids);
		
		String intermediateSummary = "";
		System.out.println("-------------- summary " + cid + "------------");
		for( SelectedElement e : elements ) {
			String line = Tabs.getTab(e.getIndentationLevel()) + e.getCode();
			intermediateSummary += line + "\n";
			System.out.println(e.getUid() + ": " + line);	
		}		
		
		return intermediateSummary;
	}
}