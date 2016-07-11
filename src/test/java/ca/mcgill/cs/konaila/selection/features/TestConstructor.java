package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestConstructor {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid12ConstructorSimple() throws Exception {
		int cid=12;	
		int propertyLineStart = 5;
		int propertyLineEnd = 5;

		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, "ConstructorObject"));	
	}
	
	@Test
	public void testCid12ConstructorPrimitiveArray() throws Exception {
		int cid=12;	
		int propertyLineStart = 6;
		int propertyLineEnd = 6;		
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, "ConstructorArray"));	
	}
	
	@Test
	public void testCid12ConstructorEmbedded() throws Exception {
		int cid=12;	
		int propertyLineStart = 14;
		int propertyLineEnd = 14;		
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, "ConstructorObject"));	
	}

	@Test
	public void testCid17AnonymousClassCreationNotConstructor() throws Exception {
		int cid=17;	
		int propertyLineStart = 1;
		int propertyLineEnd = 11;

		Assert.assertEquals(0, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, "ConstructorObject"));	
	}

	@Test
	public void testCid29ConstructorEmbeddedMultiLine() throws Exception {
		int cid=29;	
		int propertyLineStart = 4;
		int propertyLineEnd = 4;		
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, "ConstructorObject"));	
	}
	
	@Test
	public void testCid32ConstructorEmbeddedMultiLine() throws Exception {
		int cid=32;	
		int propertyLineStart = 8;
		int propertyLineEnd = 9;		
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, "ConstructorArray"));	
	}
	
	@Test
	public void testCid32ConstructorEmbeddedTwoNews() throws Exception {
		// cannot parse this code fragment due to missing comma
//		int cid=18;	
//		int propertyLineStart = 5;
//		int propertyLineEnd = 5;		
//		Assert.assertEquals(2, DbAndroidProperty.selectNumberOfProperties(
//				c, cid, propertyLineStart, propertyLineEnd, "ConstructorObject"));	
	}
	
		
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
