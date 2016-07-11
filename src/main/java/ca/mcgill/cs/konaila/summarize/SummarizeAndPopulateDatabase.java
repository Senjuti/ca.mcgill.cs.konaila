package ca.mcgill.cs.konaila.summarize;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collection;

import ca.mcgill.cs.konaila.MainCodeFragmentClassification;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.database.DatabaseSummaries;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class SummarizeAndPopulateDatabase {
	
	static final int startFrom = 100000;
	
	public static void main(String[] args) throws Exception {
		
		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();
					
		SummarizeAndPopulateDatabase.summarizeMultipleSettingsInDatabase(conn);
		
		Database.getInstance().closeConnection();
	}
	

	public static void summarizeMultipleSettingsInDatabase(Connection conn) throws SQLException, IOException {
		
		conn.setAutoCommit(false);
		
		Collection<Integer> cids = DatabaseGetCodeFragments.getJavaCodeFragmentsCidsLongEnough(conn);
		for( Integer cid : cids ) {
			if( cid> startFrom) {
				summarizeMultipleSettingsInDatabase(conn, cid);
				conn.commit();
			}
		}
		
		conn.commit();
	}
	
	public static void summarizeMultipleSettingsInDatabase(Connection conn, int cid) throws SQLException, IOException {

//		printCodeFragment(conn, cid);		
	
		System.out.println("==========Summaries for " + cid + "-----------\n");
		
		for( HowToSummarize how : HowToSummarize.values()) {
			System.out.println("--------------" + how.name() + "----------------\n");	
			
			for( Size size : Size.values()) {
				
				String summary = "";
				
				if( how == HowToSummarize.Knapsack ) {
					summary = KnapsackSummarizer.generateSummaryTwoDimensionalWithContext(conn, cid, size, SummarizationMethod.ConfigurationBasedWithDataflow);
				} else if( how == HowToSummarize.Greedy ) {
					summary = GreedySummarizer.generateSummaryTwoDimensional(conn, cid, size); 
				} else if( how == HowToSummarize.Baseline) {
					summary = BaselineSummarizer.generateSummaryTwoDimensional(conn, cid, size);
				}
					
				DatabaseSummaries.populateSummary(conn, cid, summary, 
					how.name(), size.name());
			}
		}	
	}
	
	public static void summarizeMultipleSettings(Connection conn, int cid) throws SQLException, IOException {
		String summary = "";
//		printCodeFragment(conn, cid);		
	
		System.out.println("Summaries for " + cid);
		
		summary = KnapsackSummarizer.generateSummaryTwoDimensionalWithContext(
				conn, cid, 3, SummarizationMethod.ConfigurationBasedWithDataflow, 50);
		System.out.println("--------------Knapsack----------------\n");
		System.out.println(summary);
		System.out.println("\n");

		summary = BaselineSummarizer.generateSummaryTwoDimensional(conn, cid, 3, 50);
		System.out.println("--------------Baseline----------------");
		System.out.println(summary);
		System.out.println("\n");

		summary = GreedySummarizer.generateSummaryTwoDimensional(conn, cid, 3, 50);
		System.out.println("--------------Greedy----------------");
		System.out.println(summary);
		System.out.println("\n");
	}
}
