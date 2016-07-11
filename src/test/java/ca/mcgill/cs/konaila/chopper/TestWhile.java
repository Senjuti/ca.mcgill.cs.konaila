package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestWhile {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid1MultiLine() throws Exception {
		int cid = 1;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 43, 43);
		Assert.assertEquals("WhileStatementContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 43, 43);
		Assert.assertEquals("while (cur.moveToNext()) {", codeStart);

		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 59, 59);
		Assert.assertEquals("}", codeEnd);
	}
	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
