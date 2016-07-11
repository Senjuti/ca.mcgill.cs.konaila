package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestSwitch {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid14TwoCases() throws Exception {
		int cid = 14;

		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 6, 6);
		Assert.assertEquals("SwitchStatementContext", nodeType);		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 6, 6);
		Assert.assertEquals("switch(view.getId()) {", codeStart);		
		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 20, 20);
		Assert.assertEquals("}", codeEnd);
	}
	
	@Test
	public void testCid13Switch() throws Exception {
		int cid = 13;

		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 3, 3);
		Assert.assertEquals("SwitchStatementContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 3, 3);
		Assert.assertEquals("switch (item.getItemId()) {", codeStart);
		
		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 11, 11);
		Assert.assertEquals("}", codeEnd);
	}
	
	@Test
	public void testCid13EmptyCaseBody() throws Exception {
		int cid = 13;
		
		String codeEmpty = DbAndroidUnit.selectCodeFromUnit(c, cid, 4, 4, "SwitchLabelContext");
		Assert.assertEquals("case R.id.vibrate:", codeEmpty);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 5, 5, "SwitchLabelContext");
		Assert.assertEquals("case R.id.dont_vibrate:", code);
	}
	
	@Test
	public void testCid13Default() throws Exception {
		int cid = 13;
		
		String codeEmpty = DbAndroidUnit.selectCodeFromUnit(c, cid, 9, 9, "SwitchLabelContext");
		Assert.assertEquals("default:", codeEmpty);
	}

	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
