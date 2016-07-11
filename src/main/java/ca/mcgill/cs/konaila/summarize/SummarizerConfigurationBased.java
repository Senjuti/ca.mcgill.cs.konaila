package ca.mcgill.cs.konaila.summarize;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ca.mcgill.cs.konaila.MainSummariesFromPredictionTable;
import ca.mcgill.cs.konaila.presentation.Tabs;
import ca.mcgill.cs.konaila.presentation.formatting.FormattingServer;
import ca.mcgill.cs.konaila.presentation.formatting.How;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.ReconstructSummary;
import ca.mcgill.cs.konaila.selection.DatabasePredictions.SelectedElement;
import ca.mcgill.cs.konaila.selection.categorization.ScoringSelectionUnit;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class SummarizerConfigurationBased {

	public static String generateSummary(Connection c, int cid, int summarySize, 
			SummarizationMethod summarizationMethod ) 
			 throws SQLException, IOException {

		float probabilityThreshold = DatabasePredictions.selectTopProbability(
				c, cid, summarySize, summarizationMethod);
		
		System.out.println("--> score threshold for summary size " + summarySize  + ": " + probabilityThreshold);
		
		return generateSummary(c, cid, probabilityThreshold, summarySize, 
				summarizationMethod);
	}

	
	public static String generateSummary(Connection c, int cid, 
			float summarizationRate, SummarizationMethod summarizationMethod) 
			 throws SQLException, IOException {
		
		float probabilityThreshold = DatabasePredictions.selectTopProbability(
				c, cid, summarizationRate, summarizationMethod);
		
		int numberOfSelectionUnits = DatabasePredictions.selectNumberOfSelectionUnits(c, cid);
		int numberOfTargetSummarySelectionUnits = new Double(Math.floor(summarizationRate * numberOfSelectionUnits)).intValue();
		
		System.out.println("--> score threshold for summarization rate " + summarizationRate + ": " + probabilityThreshold);
		System.out.println("--> number of selection units: " + numberOfTargetSummarySelectionUnits				
				+ "/" + numberOfSelectionUnits);
		
		if( numberOfTargetSummarySelectionUnits == 0 ) {
			numberOfTargetSummarySelectionUnits = 1;
		}
		
		return generateSummary(c, cid, probabilityThreshold, 
				numberOfTargetSummarySelectionUnits, summarizationMethod);
	}
	

	private static String generateSummary(Connection c, int cid, float probabilityThreshold, 
			int numberOfTargetSummarySelectionUnits, 
			SummarizationMethod summarizationMethod) throws SQLException, IOException {	

		return generateSummaryDumpOptimization(c,cid,probabilityThreshold,numberOfTargetSummarySelectionUnits,summarizationMethod);
	}

	
	private static String generateSummaryDumpOptimization(Connection c, int cid, float probabilityThreshold, 
			int numberOfTargetSummarySelectionUnits, 
			SummarizationMethod summarizationMethod) throws SQLException, IOException {	
		
		Collection<Integer> selectedUids = new ArrayList<Integer>();
		Collection<Integer> above = DatabasePredictions.selectAboveThreshold(c, cid, 
				probabilityThreshold, summarizationMethod).keySet();
		selectedUids.addAll(above);
		selectedUids.addAll(DatabasePredictions.selectTopUidsEqualThresholdClosestToUids(
				c,cid, probabilityThreshold, selectedUids, 
				numberOfTargetSummarySelectionUnits - selectedUids.size(),
				summarizationMethod));
		
		String summary = "";
		if( selectedUids.size() > 0 ) {
			
//			List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, selectedUids);			
//			String intermediateSummary = ReconstructSummary.reconstructCode(elements);

			String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
			
			intermediateSummary = FormattingServer.format(
					intermediateSummary, How.Eclipse, -1, FormattingServer.URL);
	
			summary = MainSummariesFromPredictionTable.applyPresentation(intermediateSummary);
		} 
		
		return summary;		
	}
	


	
	public static List<Integer> selectTopUids(Connection conn, int cid, 
			List<ScoringSelectionUnit> sortedUnits, int topThreshold) throws SQLException, IOException {
		
		List<Integer> selectedUids = new ArrayList<Integer>(); 
		
		for( int i = 0; i < topThreshold; i++ ) {
			if( !sortedUnits.isEmpty()) {
				ScoringSelectionUnit top = sortedUnits.remove(0);
				selectedUids.add(top.getUid());			
			}		
		}
		
		if( selectedUids.size() == 0 ) {
			throw new RuntimeException("Code fragment " + cid + " with no selected uid.");
		}
		
		return selectedUids;
	}
	

	public static String generateSummary(Connection c, int cid,
			List<Integer> selectedUids) throws SQLException, IOException {
		
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
