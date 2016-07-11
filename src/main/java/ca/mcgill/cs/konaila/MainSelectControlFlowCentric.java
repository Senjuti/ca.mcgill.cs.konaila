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
import ca.mcgill.cs.konaila.selection.categorization.DatabaseCodeFragmentCategory;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseControlFlowSelectionFeatures;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseControlFlowSelectionFeatures.ControlFlowSelectionUnit;

public class MainSelectControlFlowCentric {
	
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
				String categoryDisplayStr = topCategories.iterator().next();
				Category category = Category.valueOf(categoryDisplayStr);
				if( category == Category.ControlFlowCentric) {

					System.out.println("--------code fragment " + cid + "-----------");
					
					String code = DatabaseGetCodeFragments.getCodeFragment(conn, cid);
					System.out.println(code);
					
					generateSummary(conn, cid);
				}
//			}
		}		
	}
	
	public static String generateSummary(Connection c, int cid) 
			 throws SQLException, IOException {
				
		Map<Integer,ControlFlowSelectionUnit> cfs = DatabaseControlFlowSelectionFeatures.selectControlFlowSelectionUnits(c,cid);
		DatabaseControlFlowSelectionFeatures.populateQueryTerms(c, cid, cfs);
		DatabaseControlFlowSelectionFeatures.populateCallCounts(c, cid, cfs);
		DatabaseControlFlowSelectionFeatures.populateLiterals(c, cid, cfs);
		
		
		Collection<Integer> selectedUids = new ArrayList<Integer>();
		for( Map.Entry<Integer, ControlFlowSelectionUnit> e : cfs.entrySet()) {	
			ControlFlowSelectionUnit cf = e.getValue();
			int score = 0;
			score += cf.getCallCount() >= 2 ? 2 : cf.getCallCount();
			score += cf.isConstructor() ? 1 : 0;
			score += cf.hasQueryTerms() ? 2 : 0;
			score += cf.hasContainCharacterLiteral()
					|| cf.hasContainCharacterLiteral()
					|| cf.hasContainFloatLiteral()
					|| cf.hasContainIntegerLiteral() ? 2 : 0;
			score += cf.hasNullLiteral() ? -2 : 0;
			cf.setScore(score);
		}
		
		List<ControlFlowSelectionUnit> sorted = new ArrayList<ControlFlowSelectionUnit>(cfs.values());
		Collections.sort(sorted, new Comparator<ControlFlowSelectionUnit>() {
			@Override
			public int compare(ControlFlowSelectionUnit o1, ControlFlowSelectionUnit o2) {
				return o2.getScore()-o1.getScore();
			}		
		});
		

		System.out.println("-------score------");
		int topThreshold = 2;
		for( int i = 0; i < topThreshold; i++ ) {
			if( !sorted.isEmpty()) {
				ControlFlowSelectionUnit top = sorted.remove(0);
				selectedUids.add(top.getUid());
				System.out.println("uid=" + top.getUid() + ": score=" + top.getScore());				
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