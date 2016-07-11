package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;

import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseFeatures;
import ca.mcgill.cs.konaila.presentation.Tabs;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.DatabasePredictions.SelectedElement;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection.MethodSignature;
import ca.mcgill.cs.konaila.selection.features.IncludeOrNot;

public class MainCongifurationBasedSummaries {

	public static final String DB = "jdbc:sqlite:database/codeFragments.sqlite";
		
	public static void main(String[] args) throws Exception {
		Database.getInstance().initConnection(MainCongifurationBasedSummaries.DB);
		
		generateSummaries(Database.getInstance().getConnection(), Source.Eclipse);
		
		Database.getInstance().closeConnection();
	}
	
	public static void generateSummaries(Connection c, Source source) 
			 throws SQLException, IOException {
		
		List<Integer> cids = DatabaseFeatures.getCidsSorted(c, source.getTemplateStub());
		for( Integer cid : cids ) {
			generateSummary(c, cid);
		}
	}
	
	public static String  generateSummary(Connection c, int cid) 
			 throws SQLException, IOException {
		
		List<Integer> uids = DatabaseSignatureSelection.getAllUids(c, cid);		
		List<Integer> uidToExclude = deselectMethodAndTypeSignatures(c, cid);
		Collection<Integer> selectedUids = CollectionUtils.subtract(uids, uidToExclude);
				
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
	
	public static List<Integer> deselectMethodAndTypeSignatures(Connection conn, int cid) throws SQLException, IOException {
		
		List<Integer> uidToExclude = new ArrayList<Integer>();
		Map<Integer,MethodSignature> methodSignatureUids  = DatabaseSignatureSelection.selectImportantMethodSignatures(conn, cid);
				
		for( Entry<Integer,MethodSignature>  e : methodSignatureUids.entrySet()) {
			MethodSignature method = e.getValue();
		
			if( method.getMethodConfiguration().ordinal() <= IncludeOrNot.IncludeProbably.ordinal()) {
				uidToExclude.add(method.getUid());
			}
		}	
		
		// clean class signatures if there's no methods
		
		return uidToExclude;
	}

}
