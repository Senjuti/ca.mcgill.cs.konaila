package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestMemberDeclarationField {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid1SingleLine() throws Exception {
		int cid = 1;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 1, 1);
		Assert.assertEquals("MemberDeclarationFieldContext", nodeType);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 1);
		Assert.assertEquals("private static final String DEBUG_TAG = \"MyActivity\";", code);
	}
	
	@Test
	public void testCid1SingleLineNoModifiers() throws Exception {
		int cid = 16;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 1, 1);
		Assert.assertEquals("MemberDeclarationFieldContext", nodeType);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 1);
		Assert.assertEquals("LinearLayout mLinearLayout;", code);
	}
	
	@Test
	public void testCid42WithInitializer() throws Exception {
		int cid = 42;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 24, 24);
		Assert.assertEquals("MemberDeclarationFieldContext", nodeType);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 24, 24);
		Assert.assertEquals("final Messenger mMessenger = new Messenger(new IncomingHandler());", code);
	}
	
	@Test
	public void testCid1MultiLine() throws Exception {
		int cid = 1;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 2, 6);
		Assert.assertEquals("MemberDeclarationFieldContext", nodeType);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 2, 6);
		Assert.assertTrue(
				code.startsWith("public static final String[]") &&
				code.endsWith("};"));		
	}

	@Test
	public void testCid5() throws Exception {
		int cid = 5;		
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 3, 3, "MemberDeclarationFieldContext");
		Assert.assertEquals("public static final String KEY_PREF_SYNC_CONN = \"pref_syncConnectionType\";", code);
	}
	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
