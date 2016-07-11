package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.presentation.Tabs;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.DatabasePredictions.SelectedElement;
import ca.mcgill.cs.konaila.selection.categorization.Category;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseCodeFragmentCategory;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection.MethodSignature;

public class MainSelectCallBack {
	
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

			if( topCategories.size() == 1 ) {
				String categoryDisplayStr = topCategories.iterator().next();
				Category category = Category.valueOf(categoryDisplayStr);
				if( category == Category.Structure 
//						|| category == Category.SingleApiCallBack
						) {

					System.out.println("--------code fragment " + cid + "-----------");
					
					String code = DatabaseGetCodeFragments.getCodeFragment(conn, cid);
					System.out.println(code);
					
					generateSummary(conn, cid);
				}
			}
		}		
	}
	
	public static String generateSummary(Connection c, int cid) 
			 throws SQLException, IOException {
				
		Map<Integer,MethodSignature> methodSignatures = DatabaseSignatureSelection.selectCallBackMethodSignatures(c, cid);
		Collection<Integer> candidateUids = new ArrayList<Integer>();
		candidateUids.addAll(methodSignatures.keySet());
		candidateUids.addAll(enclosingClassUids(methodSignatures.values()));
				
		List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, candidateUids);
		
		String intermediateSummary = "";
		System.out.println("--------------" + cid + "------------");
		for( SelectedElement e : elements ) {
			String line = Tabs.getTab(e.getIndentationLevel()) + e.getCode();
			intermediateSummary += line + "\n";
			System.out.println(e.getUid() + ": " + line);	
		}		
		
		return intermediateSummary;
	}

	private static Collection<Integer> enclosingClassUids(Collection<MethodSignature> methods) {
		Set<Integer> result = new HashSet<Integer>();
		for( MethodSignature m : methods ) {
			result.add(m.getEnclosingUid());
		}
		return result;
	}	

}