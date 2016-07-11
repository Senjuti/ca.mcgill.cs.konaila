package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestFor {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid55MultiLine() throws Exception {
		int cid = 55;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 15, 15);
		Assert.assertEquals("ForStatementContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 15, 15);
		Assert.assertEquals("for (incr = 0; incr <= 100; incr+=5) {", codeStart);

		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 30, 30);
		Assert.assertEquals("}", codeEnd);
	}
	
	@Test
	public void testCid55BlockWithSingleLine() throws Exception {
		int cid = 47;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 25, 25);
		Assert.assertEquals("ForStatementContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 25, 25);
		Assert.assertEquals("for (int i = 0; i < mCount; i++) {", codeStart);

		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 27, 27);
		Assert.assertEquals("}", codeEnd);
	}
	
	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
