package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestCommentOutForParsing {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	// [35, 1038, 1047, 1083, 1209]

	@Test
	public void testCid35ERROR() throws Exception {
		int cid = 35;
		int lineNumber = 5;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, lineNumber, lineNumber, "LineComment");
		Assert.assertEquals("",code);
	}	
	
	@Test
	public void testCid1038ERROR() throws Exception {
		int cid = 1038;
		int lineNumber = 1;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, lineNumber, lineNumber, "LineComment");
		Assert.assertEquals("",code);
	}	
	
	@Test
	public void testCid1047ERROR() throws Exception {
		int cid = 1047;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 1, "LineComment");
		Assert.assertEquals("",code);
	}	
	@Test
	public void testCid1083ERROR() throws Exception {
		int cid = 1083;
		
//	     ICompilationUnit unit = ...;
		int lineNumber = 26;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, lineNumber, lineNumber, "LineComment");
		Assert.assertEquals("",code);
		
		// ellipses at line
		lineNumber = 24;
		code = DbAndroidUnit.selectCodeFromUnit(c, cid, lineNumber, lineNumber, "LineComment");
		Assert.assertEquals("",code);
	}	
	
	@Test
	public void testCid1209ERROR() throws Exception {
		int cid = 1209;
		int lineNumber = 1;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, lineNumber, lineNumber, "LineComment");
		Assert.assertEquals("",code);
	}	
	
	
	
	
	@AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}

}
