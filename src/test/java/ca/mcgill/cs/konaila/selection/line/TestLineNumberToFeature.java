package ca.mcgill.cs.konaila.selection.line;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.DbAndroidProperty.Feature;
import ca.mcgill.cs.konaila.DbFeatures;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestLineNumberToFeature {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testCid6TwoOneLinerTypeSignatureUnit() throws Exception {
		int cid=6;
		int uid=1;
		
		List<String> features = DbFeatures.selectFeatures(c, cid, uid);		
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
		int uid=3;
//		 ... .setSmallIcon(R.drawable.ic_notification);
		
		List<String> features = DbFeatures.selectFeatures(c, cid, uid);		
		Assert.assertEquals(3, features.size());		
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
