package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestThrows {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid9() throws Exception {
		int cid=9;
		String antlrProperty = "ExceptionDeclarationContext";		
		int propertyLineStart = 3;
		int propertyLineEnd = 3;

		Assert.assertEquals(antlrProperty, DbAndroidProperty.antlrPropertyExists(
				c, cid, propertyLineStart, propertyLineEnd, antlrProperty));	
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
