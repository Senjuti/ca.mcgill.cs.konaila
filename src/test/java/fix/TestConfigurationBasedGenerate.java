package fix;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.MainCongifurationBasedSummaries;
import ca.mcgill.cs.konaila.database.Database;

public class TestConfigurationBasedGenerate {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void test1062() throws Exception {
		int cid = 1062;
		String summary = MainCongifurationBasedSummaries.generateSummary(c, cid);		
		Assert.assertTrue(summary.startsWith("try {"));
	}	

	@Test
	public void test1213() throws Exception {
		int cid = 1213;
		
		String summary = MainCongifurationBasedSummaries.generateSummary(c, cid);		
		Assert.assertTrue(summary.startsWith("class Handler"));
	}
	
	@Test
	public void test1165() throws Exception {
		int cid = 1165;
		String summary = MainCongifurationBasedSummaries.generateSummary(c, cid);		
		Assert.assertTrue(summary.trim().startsWith("try {"));
	}	
	
	@Test
	public void test1072() throws Exception {
		int cid = 1072;
		String summary = MainCongifurationBasedSummaries.generateSummary(c, cid);		
		Assert.assertTrue(summary.startsWith("class CompareItem"));
	}
	
	// 1239
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
