package ca.mcgill.cs.konaila.selection.line;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.DbAndroidProperty.Feature;
import ca.mcgill.cs.konaila.database.Database;

public class TestLineNumberToProperties {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testCid6TwoOneLinersUnit() throws Exception {
		int cid=6;
		int lineNumber=1;
		List<Feature> features = DbAndroidProperty.selectProperties(c, cid, lineNumber);		
		Assert.assertEquals(10, features.size());
	}
	
	@Test
	public void testCid9MultiLinerUnit() throws Exception {
		int cid=9;
		int lineNumber=1;

		// int uid = 1;
		List<Feature> features = DbAndroidProperty.selectProperties(c, cid, lineNumber);		
		Assert.assertEquals(6, features.size());
	}
	
	@Test
	public void testCid55MultiUnitStatement() throws Exception {
		int cid=55;
		
		int lineNumberStart=5;
		int lineNumberEnd=5;		
		List<String> features = DbAndroidProperty.selectProperties(c, cid, lineNumberStart, lineNumberEnd);		
		Assert.assertEquals(2, features.size());
		
		lineNumberStart=6;
		lineNumberEnd=6;		
		features = DbAndroidProperty.selectProperties(c, cid, lineNumberStart, lineNumberEnd);		
		Assert.assertEquals(2, features.size());
		
		lineNumberStart=7;
		lineNumberEnd=7;		
		features = DbAndroidProperty.selectProperties(c, cid, lineNumberStart, lineNumberEnd);		
		Assert.assertEquals(2, features.size());
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
