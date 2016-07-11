package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.BeforeClass;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;

public class MainChopperStats {

	static Connection c;
	static final String ANDROID_GUIDE = "android-guide";
	static final String ECLIPSE_FAQ = "eclipse-faq";
	
	@BeforeClass
	public static void main(String[] args) throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
		
//		DatabaseChopperStats.getLineLength(c, "stackoverflow", 
//				new File("stats/chopper/stackoverflow-line-stats.csv"));
//		DatabaseChopperStats.createTable(c);
		DatabaseChopperStats.populateStats(c, ANDROID_GUIDE);
		
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}	
}
