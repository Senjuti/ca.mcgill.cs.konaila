package ca.mcgill.cs.konaila.eclipse;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbEclipseUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestChopperEclipseCid1077 {
	
	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testClass() throws Exception {		
		int cid = 1077;
		Assert.assertEquals("public class ExamplesPlugin extends AbstractUIPlugin { ... }", 
				DbEclipseUnit.selectCodeFromUnit(c, cid, 1, 12));		
	}

	@Test
	public void testField() throws Exception {		
		int cid = 1077;
		Assert.assertEquals("public static final String IMAGE_ID = \"sample.image\";", 
				DbEclipseUnit.selectCodeFromUnit(c, cid, 3, 3));		
	}
	
	@Test
	public void testMethod() throws Exception {		
		int cid = 1077;
		Assert.assertEquals("protected void initializeImageRegistry(ImageRegistry registry) { ... }", 
				DbEclipseUnit.selectCodeFromUnit(c, cid, 5, 11));		
	}

	@Test
	public void testStatement() throws Exception {		
		int cid = 1077;
		Assert.assertEquals("registry.put(IMAGE_ID, desc);", 
				DbEclipseUnit.selectCodeFromUnit(c, cid, 10, 10));		
	}

	@Test
	public void testCallOfCall() throws Exception {		
		int cid = 1077;
		Assert.assertEquals("registry.put(IMAGE_ID, desc);", 
				DbEclipseUnit.selectCodeFromUnit(c, cid, 10, 10));		
	}
	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
