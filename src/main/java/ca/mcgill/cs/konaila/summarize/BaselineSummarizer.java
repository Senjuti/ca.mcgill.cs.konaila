package ca.mcgill.cs.konaila.summarize;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.chopper.SelectionUnit.HoleElement;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseRecomputeBaseline;
import ca.mcgill.cs.konaila.database.DatabaseSummaries;
import ca.mcgill.cs.konaila.presentation.formatting.FormattingServer;
import ca.mcgill.cs.konaila.presentation.formatting.How;
import ca.mcgill.cs.konaila.selection.ReconstructSummary;

public class BaselineSummarizer {

	static final int MIN_I = 257;
	
	public static void main(String[] args) throws Exception {
		Database.setDatabaseString(Main.DB);
//		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();

		conn.setAutoCommit(false);

//		recomputeBaselines(conn);
		DatabaseRecomputeBaseline.cleanUpKonailaTags(conn);
		
		conn.commit();		
		Database.getInstance().closeConnection();
	}
	
	
	public static void recomputeBaselines(Connection conn)
			throws IOException, SQLException{
		List<Integer> sortedCids = DatabaseSummaries.getCids(conn);
		Collections.sort(sortedCids);
		
		int i = 0; 
		for( Integer cid : sortedCids ) {
			
			if( i >= MIN_I) {
				System.out.println("===========Summarizing " + i + ", cid=" + cid + "=======");
				for( Size size : Size.values()) {
					 String summary = generateSummaryTwoDimensional(conn, cid, size);
					 DatabaseRecomputeBaseline.populate(conn, cid, summary, size);
				}
				conn.commit();
			}
			i+=1;
		}		
	}
	
	public static String generateSummaryTwoDimensional(Connection c, 
			int cid, Size size)  
					 throws SQLException, IOException {
		return generateSummaryTwoDimensional(c, cid, size.getLineLength(), size.getWidth());
	}
	
	public static String generateSummaryTwoDimensional(Connection c, 
			int cid, int numberOfTargetLines, int width) 
			 throws SQLException, IOException {		
		
//		SummarizationMethod summarizationNoDataflow = SummarizationMethod.ConfigurationBased;
		
		String summary = "";
		Map<Integer,Pair<Float,Integer>> uidToCharactersPerLine =
				LineLengthUtilOnAllSelectionUnits.getUidToCharactersPerLine(c, cid, width);
		
		Collection<Integer> selectedUids = selectUids(c, cid, //summarizationNoDataflow,
				width, numberOfTargetLines, uidToCharactersPerLine);
				
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
			
//			summary = MainSummariesFromPredictionTable.applyPresentation(intermediateSummary);
			
//			summary = FormattingServer.format(summary, How.Compact, width,
//					FormattingServer.URL);
			summary = FormattingServer.doNotWrapSingleClosingBracketInItsOwnLine(summary);		
		}
		
		summary =summary.trim();
		summary = summary + 
				StringUtils.repeat("\n", numberOfTargetLines - LineLengthUtilOnGoodSelectionUnits.getNumberOfLines(summary));

		return summary;	
	}
	
	private static Collection<Integer> selectUids(Connection c, int cid, 
//			SummarizationMethod summarizationMethod, 
			int width,
			int numberOfTargetLines, Map<Integer,Pair<Float,Integer>> uidToCharactersPerLine)			
					throws SQLException, IOException {	
		 			
		List<Entry<Integer,Pair<Float,Integer>>> sortedCharactersPerLines = 
				new ArrayList<Entry<Integer,Pair<Float,Integer>>>(uidToCharactersPerLine.entrySet());
		Collections.sort(sortedCharactersPerLines,
				new Comparator<Entry<Integer,Pair<Float,Integer>>> () {
			  @Override
	            public int compare(Entry<Integer,Pair<Float,Integer>> e1, 
	            		Entry<Integer,Pair<Float,Integer>> e2) {
	                return (int)(e2.getValue().getLeft() - e1.getValue().getLeft());
	            }
		});
					
		Collection<Integer> selectedUids = new ArrayList<Integer>();
		int currentLength = 0;
		for( Entry<Integer,Pair<Float,Integer>> e : sortedCharactersPerLines) {
			int uid = e.getKey();
			int length = e.getValue().getRight();
						
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
