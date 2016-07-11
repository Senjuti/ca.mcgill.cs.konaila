package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestComment {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testCid21SingleLineComment() throws Exception {
		int cid = 21;		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 4, 4, "LineComment");
		Assert.assertEquals("// This activity implements OnMenuItemClickListener", code);
	}

	
	@Test
	public void testCid12JavaDoc() throws Exception {
		int cid = 12;		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 9, 12, "JavaDocComment");
		Assert.assertTrue(code.contains("Create a client socket with the host,"));
		
		code = DbAndroidUnit.selectCodeFromUnit(c, cid, 35, 38, "JavaDocComment");
		Assert.assertTrue(code.contains("Clean up any open sockets when done"));
	}

	@Test
	public void testCid31FirstLine() throws Exception {
		int cid = 31;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 1, "LineComment");
		Assert.assertEquals("// Responds to the user selecting \"paste\"", code);
		
		code = DbAndroidUnit.selectCodeFromUnit(c, cid, 22, 22, "LineComment");
		Assert.assertEquals("// calls a routine to resolve the URI and get data from it. This routine is not", code);
		
		code = DbAndroidUnit.selectCodeFromUnit(c, cid, 23, 23, "LineComment");
		Assert.assertEquals("// presented here.", code);
	}
	
	@Test
	public void testCid23OneLinerJavaDoc() throws Exception {
		int cid = 23;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 1, "JavaDocComment");
		Assert.assertEquals("/** Check if this device has a camera */", code);
	}
	
	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}

}
