package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.presentation.Tabs;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.DatabasePredictions.SelectedElement;
import ca.mcgill.cs.konaila.selection.categorization.Category;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseApiCallsSelectionFeatures;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseApiCallsSelectionFeatures.ApiCallSelectionUnit;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseControlFlowSelectionFeatures.ControlFlowSelectionUnit;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseCodeFragmentCategory;

public class MainSelectApiCalls {
	
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
			List<String> topCategories = DatabaseCodeFragmentCategory.selectTopCategories(conn, max, cid);
			
//			if( topCategories.size() == 1 ) {
			for( String categoryDisplayStr : topCategories ) {
				Category category = Category.valueOf(categoryDisplayStr);
				if( category == Category.ApiCalls) {

					System.out.println("--------code fragment " + cid + "-----------");
					
					String code = DatabaseGetCodeFragments.getCodeFragment(conn, cid);
					System.out.println(code);
					
					generateSummary(conn, cid);
				}
//			}
			}
		}		
	}
	
	public static String generateSummary(Connection c, int cid) 
			 throws SQLException, IOException {
				
		Map<Integer,ApiCallSelectionUnit> apiCalls = DatabaseApiCallsSelectionFeatures.selectApiSelectionUnits(c, cid);
		DatabaseApiCallsSelectionFeatures.populateQueryTerms(c, cid, apiCalls);
		
		
		Collection<Integer> selectedUids = new ArrayList<Integer>();
		for( Map.Entry<Integer, ApiCallSelectionUnit> e : apiCalls.entrySet()) {			
			int uid = e.getKey();
			ApiCallSelectionUnit api = e.getValue();
			int score = 0;
			score += api.getCallCount() >= 3 ? 3 : api.getCallCount();
			score += api.isConstructor() ? 1 : 0;
			score += api.hasQueryTerms() ? 3 : 0;			
			api.setScore(score);
		}
		
		List<ApiCallSelectionUnit> sorted = new ArrayList<ApiCallSelectionUnit>(apiCalls.values());
		Collections.sort(sorted, new Comparator<ApiCallSelectionUnit>() {
			@Override
			public int compare(ApiCallSelectionUnit o1, ApiCallSelectionUnit o2) {
				return o2.getScore()-o1.getScore();
			}		
		});
		

		System.out.println("-------score------");
		int topThreshold = 2;
		for( int i = 0; i < topThreshold; i++ ) {
			if( !sorted.isEmpty()) {
				ApiCallSelectionUnit top = sorted.remove(0);
				selectedUids.add(top.getUid());

				System.out.println("uid=" + top.getUid() + ": score=" + top.getScore());		
			}		
		}

		
		List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, selectedUids);
		
		String intermediateSummary = "";
		System.out.println("--------------" + cid + "------------");
		for( SelectedElement e : elements ) {
			String line = Tabs.getTab(e.getIndentationLevel()) + e.getCode();
			intermediateSummary += line + "\n";
			System.out.println(e.getUid() + ": " + line);	
		}		
		
		return intermediateSummary;
	}

	
	

}