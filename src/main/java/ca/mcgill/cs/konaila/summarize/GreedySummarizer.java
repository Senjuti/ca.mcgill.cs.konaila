package ca.mcgill.cs.konaila.summarize;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import ca.mcgill.cs.konaila.MainSummariesFromPredictionTable;
import ca.mcgill.cs.konaila.chopper.SelectionUnit.HoleElement;
import ca.mcgill.cs.konaila.presentation.formatting.FormattingServer;
import ca.mcgill.cs.konaila.presentation.formatting.How;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.ReconstructSummary;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class GreedySummarizer {

	public static String generateSummaryTwoDimensional(Connection c, 
			int cid, Size size)  
					 throws SQLException, IOException {
		return generateSummaryTwoDimensional(c, cid, size.getLineLength(), size.getWidth());
	}
	
	public static String generateSummaryTwoDimensional(Connection c, 
			int cid, int numberOfTargetLines, int width) 
			 throws SQLException, IOException {		
		
		SummarizationMethod summarizationNoDataflow = SummarizationMethod.ConfigurationBased;
		
		String summary = "";
		List<Pair<Integer,Float>> uidToValues = DatabasePredictions.getSortedUidToValues(c, cid, summarizationNoDataflow);

		Collection<Integer> selectedUids = selectUids(c, cid, summarizationNoDataflow,
				width, numberOfTargetLines, uidToValues);
						
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
		}		
		
		summary =summary.trim();
		summary = summary + 
				StringUtils.repeat("\n", numberOfTargetLines - LineLengthUtilOnGoodSelectionUnits.getNumberOfLines(summary));

		return summary;
	}	

	private static Collection<Integer> selectUids(Connection c, int cid, 
			SummarizationMethod summarizationMethod, int width,
			int numberOfTargetLines, List<Pair<Integer,Float>> uidToValues)			
					throws SQLException, IOException {	
		Collection<Integer> selectedUids = new ArrayList<Integer>();
		int currentLength = 0;
		for( Pair<Integer,Float> e : uidToValues) {
			int uid = e.getLeft();
						
			int length = LineLengthUtilOnGoodSelectionUnits.getNumberOfLinesAfterFormatter(c, cid, uid,
					summarizationMethod, width);
			
			if( currentLength + length <= numberOfTargetLines ) {
				selectedUids.add(uid);
				currentLength += length;
			} else {
				continue;
			}			
		}
		return selectedUids;
	}
	

}
