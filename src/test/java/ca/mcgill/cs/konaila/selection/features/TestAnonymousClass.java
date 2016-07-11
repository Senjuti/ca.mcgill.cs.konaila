package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestAnonymousClass {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid1228() throws Exception {
		int cid=1228;
		String konailaProperty = "AnonymousClassCreation";		
		int propertyLineStart = 20;
		int propertyLineEnd = 26;

		List<String> features = DbAndroidProperty.selectProperties(c, cid, propertyLineStart, propertyLineEnd);
		Assert.assertTrue(features.contains(konailaProperty));	
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
