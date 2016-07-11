package ca.mcgill.cs.konaila.generate;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseSummaries;

public class TestNonEmptySummaries {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid44() throws Exception {
		int cid = 44;
		String summary = DatabaseSummaries.getSummary(c, cid, "summary size=3");
		
		Assert.assertNotEquals("", summary);
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
