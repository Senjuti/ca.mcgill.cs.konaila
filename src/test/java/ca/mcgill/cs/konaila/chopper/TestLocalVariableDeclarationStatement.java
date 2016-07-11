package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestLocalVariableDeclarationStatement {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid25SingleLine() throws Exception {
		int cid = 25;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 14, 14);
		Assert.assertEquals("LocalVariableDeclarationStatementContext", nodeType);	
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 14, 14);
		Assert.assertEquals("long timeDelta = location.getTime() - currentBestLocation.getTime();", code);		
	}
	
	@Test
	public void testCid37SingleLine() throws Exception {
		int cid = 37;
		
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 3, 3);
		Assert.assertEquals("LocalVariableDeclarationStatementContext", nodeType);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 3, 3);
		Assert.assertEquals("String action = intent.getAction();", code);
	}
	

	
	@Test
	public void testCid37LocalVariableDeclarationContainsAnonymousClass() throws Exception {
		int cid = 37;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 1, 1);
		Assert.assertEquals("LocalVariableDeclarationStatementContext", nodeType);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 1);
		Assert.assertTrue(code.startsWith("BroadcastReceiver mUsbReceiver ="));	
	}
	
	@Test
	public void testCid5WithinClass() throws Exception {
		int cid = 5;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 8, 8);
		Assert.assertEquals("LocalVariableDeclarationStatementContext", nodeType);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 8, 8);
		Assert.assertEquals("Preference connectionPref = findPreference(key);", code);		
	}
	
	
	@Test
	public void testCid1WithinClass() throws Exception {
		int cid = 1;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 45, 45, "LocalVariableDeclarationStatementContext");
		Assert.assertEquals("long eventID = 0;", code);			
	}

	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
