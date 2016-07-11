package ca.mcgill.cs.konaila;

import java.sql.Connection;

import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.summarize.SummarizeAndPopulateDatabase;

public class MainSummarize {
	
	public static final String DB = Main.DB;
	public static final String dir = "code-fragment-categorization/";
	public static final int NUMBER_UNIT_SELECTED = 2;
	
	public static void main(String[] args) throws Exception {
		
		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();
			
		int cid = Integer.parseInt(args[0]);
		
		SummarizeAndPopulateDatabase.summarizeMultipleSettings(conn, cid);
		
		Database.getInstance().closeConnection();
	}
}