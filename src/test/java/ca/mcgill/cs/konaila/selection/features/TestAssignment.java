package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestAssignment {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid7() throws Exception {
		int cid=7;
		String konailaProperty = "Assignment";		
		int propertyLineStart = 9;
		int propertyLineEnd = 9;

		Assert.assertEquals(konailaProperty, DbAndroidProperty.selectProperty(
				c, cid, propertyLineStart, propertyLineEnd));	
	}
	
	@Test
	public void testCid7NotEquality() throws Exception {
		int cid=7;
		String konailaProperty = "";		
		int propertyLineStart = 4;
		int propertyLineEnd = 4;

		Assert.assertEquals(0, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}
	
	@Test
	public void testCid32ToFix() throws Exception {
		int cid=32;
		String konailaProperty = "Assignment";		

		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(c, cid, 2, 2, konailaProperty));	
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(c, cid, 3, 3, konailaProperty));	
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(c, cid, 4, 4, konailaProperty));	
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(c, cid, 5, 7, konailaProperty));	
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(c, cid, 8, 9, konailaProperty));	
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(c, cid, 11, 11, konailaProperty));	
	}
	
	@Test
	public void testCid28DivideAssignmentOperator() throws Exception {
		int cid=28;
		String konailaProperty = "Assignment";		
		int propertyLineStart = 22;
		int propertyLineEnd = 22;

		Assert.assertEquals(konailaProperty, DbAndroidProperty.selectProperty(
				c, cid, propertyLineStart, propertyLineEnd));	
	}
	
	@Test
	public void testCid28CommentedOut() throws Exception {
		int cid=28;
		String konailaProperty = "Assignment";		
		int propertyLineStart = 44;
		int propertyLineEnd = 44;

		Assert.assertEquals(0, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}
	
	@Test
	public void testCid55IncrementOperatorInForLoop() throws Exception {
		int cid=55;
		String konailaProperty = "Assignment";		
		int propertyLineStart = 15;
		int propertyLineEnd = 15;

		Assert.assertEquals(2, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
