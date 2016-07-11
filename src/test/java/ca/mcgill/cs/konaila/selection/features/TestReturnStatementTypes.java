package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestReturnStatementTypes {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid31() throws Exception {
		// J31 - /blockStatements/J31.java

//		String nodeType = DbUnit.selectNodeType(c, cid, 1, 1);
//		Assert.assertEquals("MemberDeclarationFieldContext", nodeType);
		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
