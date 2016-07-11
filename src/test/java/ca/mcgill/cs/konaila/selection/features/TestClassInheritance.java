package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestClassInheritance {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid51Extends() throws Exception {
		int cid=51;
		String konailaProperty = "Extends";		
		int propertyLineStart = 4;
		int propertyLineEnd = 4;

		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}

	@Test
	public void testCid42InnerClass() throws Exception {
		int cid=42;
		String konailaProperty = "Extends";		
		int propertyLineStart = 8;
		int propertyLineEnd = 8;

//		String code = DbAndroidUnit.selectCodeFragment(c, cid);		
//		int innerClass = code.indexOf("class IncomingHandler extends Handler {");

		
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}
		
	@Test
	public void testCid39ExtendsImplements() throws Exception {
		int cid=39;
		String konailaProperty = "Implements"; 
		int propertyLineStart = 1;
		int propertyLineEnd = 1;

		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(		
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}
		
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
