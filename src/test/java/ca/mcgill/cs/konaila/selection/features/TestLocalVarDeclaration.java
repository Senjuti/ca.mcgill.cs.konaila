package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestLocalVarDeclaration {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid12Simple() throws Exception {
		int cid=12;
		String konailaProperty = "LocalVariableDeclarationPrimitive";		
		int propertyLineStart = 3;
		int propertyLineEnd = 3;
		
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}
	
	@Test
	public void testCid12ByteArray() throws Exception {
		int cid=12;
		String konailaProperty = "LocalVariableDeclarationPrimitive";		
		int propertyLineStart = 6;
		int propertyLineEnd = 6;
		
		Assert.assertEquals(1, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}

	@Test
	public void testCid32StringArrayIsNotPrimitive() throws Exception {
		int cid=32;
		String konailaProperty = "LocalVariableDeclarationPrimitive";		
		int propertyLineStart = 8;
		int propertyLineEnd = 9;
		
		Assert.assertEquals(0, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}
	
	@Test
	public void testCid32Not() throws Exception {
		int cid=32;
		String konailaProperty = "LocalVariableDeclarationPrimitive";		
		int propertyLineStart = 2;
		int propertyLineEnd = 2;
		
		Assert.assertEquals(0, DbAndroidProperty.selectNumberOfProperties(
				c, cid, propertyLineStart, propertyLineEnd, konailaProperty));	
	}
	
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
