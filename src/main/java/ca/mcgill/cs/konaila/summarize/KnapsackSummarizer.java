package ca.mcgill.cs.konaila.summarize;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import ca.mcgill.cs.konaila.MainSummariesFromPredictionTable;
import ca.mcgill.cs.konaila.chopper.SelectionUnit.HoleElement;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.presentation.formatting.FormattingServer;
import ca.mcgill.cs.konaila.presentation.formatting.How;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.ReconstructSummary;
import ca.mcgill.cs.konaila.selection.optimization.DatabaseKnapsackContext;
import ca.mcgill.cs.konaila.selection.optimization.KonailaKnapsackSolver;
import ca.mcgill.cs.konaila.selection.optimization.KonailaKnapsackSolverWithContext;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class KnapsackSummarizer {

	public static String generateSummaryFixedWidth(Connection c, int cid, 
			float summarizationRate, SummarizationMethod summarizationMethod,
			int width) 
			 throws SQLException, IOException {
		
		int totalLines = LineLengthUtilOnGoodSelectionUnits.getNumberOfLines(DatabaseGetCodeFragments.getCodeFragment(c, cid));
		int numberOfTargetLines = new Double(Math.floor(summarizationRate * totalLines)).intValue();
		
			
		Map<Integer,Integer> uidToLines = DatabaseChopperStats.getUidsToLineLengths(c, cid);
		Map<Integer,Float> uidToValues = DatabasePredictions.getValues(c, cid, summarizationMethod);
		
		Collection<Integer> selectedUids = KonailaKnapsackSolver.selectOptimalUnitsGivenLines(
				cid, uidToLines, uidToValues, numberOfTargetLines);
				
		String summary = "";
		if( selectedUids.size() > 0 ) {
			
//			List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, selectedUids);			
//			String intermediateSummary = ReconstructSummary.reconstructCode(elements);

			String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);	

			intermediateSummary = FormattingServer.format(intermediateSummary, 
					How.Eclipse, width, FormattingServer.URL);
			
			summary = MainSummariesFromPredictionTable.applyPresentation(intermediateSummary);			
		} 
		
		return summary.trim();		
	}
	
	
	public static String generateSummaryTwoDimensional(Connection c, int cid, 
			float summarizationRate, SummarizationMethod summarizationMethod,
			int width) 
			 throws SQLException, IOException {
		
		int totalLines = LineLengthUtilOnGoodSelectionUnits.getNumberOfLines(DatabaseGetCodeFragments.getCodeFragment(c, cid));
		int numberOfTargetLines = new Double(Math.floor(summarizationRate * totalLines)).intValue();		
			
		Map<Integer,Integer> uidToLines = LineLengthUtilOnGoodSelectionUnits.
				getNumberOfLinesPerUnitAfterFormatter(
						c, cid, summarizationMethod, width);
		Map<Integer,Float> uidToValues = DatabasePredictions.getValues(c, cid, summarizationMethod);
		
		Collection<Integer> selectedUids = KonailaKnapsackSolver.selectOptimalUnitsGivenLines(
				cid, uidToLines, uidToValues, numberOfTargetLines);
		
		String summary = "";
		if( selectedUids.size() > 0 ) {
			
//			List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, selectedUids);			
//			String intermediateSummary = ReconstructSummary.reconstructCode(c, cid, selectedUids);

			String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
			summary = intermediateSummary;
			summary = summary.replace("," + HoleElement.HOLE, "");
			summary = summary.replace(HoleElement.HOLE + ",", "");
			summary = summary.replace(HoleElement.HOLE, "");			
			summary = FormattingServer.format(summary, How.Compact, 
					width, FormattingServer.URL);
			
//			summary = MainSummariesFromPredictionTable.applyPresentation(intermediateSummary);
//			summary = FormattingServer.format(summary, How.Eclipse, FormattingServer.SERVER_URL);
		} 
		
		return summary.trim();		
	}
	
	public static String generateSummaryTwoDimensionalWithContext(Connection c, int cid, 
			float summarizationRate, SummarizationMethod summarizationMethod,
			int width) 
			 throws SQLException, IOException {
		int totalLines = LineLengthUtilOnGoodSelectionUnits.getNumberOfLines(
				DatabaseGetCodeFragments.getCodeFragment(c, cid));
		int numberOfTargetLines = new Double(Math.floor(summarizationRate * totalLines)).intValue();		
		return generateSummaryTwoDimensionalWithContext(c, cid, 
				numberOfTargetLines, summarizationMethod, width);
	}

	public static String generateSummaryTwoDimensionalWithContext(Connection c, int cid, 
			Size size, SummarizationMethod summarizationMethod) 
			 throws SQLException, IOException {	
		return generateSummaryTwoDimensionalWithContext(c, cid, size.getLineLength(),
				summarizationMethod, size.getWidth());
	}
	
	public static String generateSummaryTwoDimensionalWithContext(Connection c, int cid, 
			int numberOfTargetLines, 
			SummarizationMethod summarizationMethod,
			int width) 
			 throws SQLException, IOException {		
			
		String summary = "";
		Map<Integer,Integer> uidToLines = LineLengthUtilOnGoodSelectionUnits.
				getNumberOfLinesPerUnitAfterFormatter(
						c, cid, summarizationMethod, width);
		Map<Integer,Float> uidToValues = DatabasePredictions.getValues(c, cid, summarizationMethod);
		Map<Integer,Set<Integer>> kidToUids = DatabaseKnapsackContext.getKidToUids(c, cid);
		
		Collection<Integer> selectedUids = KonailaKnapsackSolverWithContext.
				selectOptimalUnitsGivenLines(cid, 
						uidToLines, uidToValues, kidToUids,
						numberOfTargetLines);
		
		if( selectedUids.size() > 0 ) {
			
//			List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, selectedUids);			
//			String intermediateSummary = ReconstructSummary.reconstructCode(c, cid, selectedUids);

			String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
			summary = intermediateSummary;
			summary = summary.replace("," + HoleElement.HOLE, "");
			summary = summary.replace(HoleElement.HOLE + ",", "");
			summary = summary.replace(HoleElement.HOLE, "");		
			
			summary = FormattingServer.format(summary, How.Compact, width, 
					FormattingServer.URL);
			
			summary = MainSummariesFromPredictionTable.applyPresentation(intermediateSummary);
			summary = FormattingServer.format(summary, How.Compact, width,
					FormattingServer.URL);
			summary = FormattingServer.doNotWrapSingleClosingBracketInItsOwnLine(summary);
			
		} else {
			summary = GreedySummarizer.generateSummaryTwoDimensional(c, cid, numberOfTargetLines, width);
		}
		
		summary = summary.trim();
		summary = summary + 
				StringUtils.repeat("\n", numberOfTargetLines - LineLengthUtilOnGoodSelectionUnits.getNumberOfLines(summary));

		return summary;
	}
	
}
