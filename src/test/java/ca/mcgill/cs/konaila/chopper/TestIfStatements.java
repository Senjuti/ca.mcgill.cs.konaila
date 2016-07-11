package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestIfStatements {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid52JustIf() throws Exception {
		int cid = 52;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 11, 11);
		Assert.assertEquals("IfStatementContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 11, 11);
		Assert.assertEquals("if (null != mDownloaderClientStub) {", codeStart);

		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 13, 13);
		Assert.assertEquals("}", codeEnd);
	}
	
	@Test
	public void testCid13IfSingleLine() throws Exception {
		int cid = 13;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 6, 6);
		Assert.assertEquals("IfStatementContext", nodeType);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 6, 6);
		Assert.assertEquals("if (item.isChecked())", code);
	}
	
	@Test
	public void testCid13ElseSingleLine() throws Exception {
		int cid = 13;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 7, 7);
		Assert.assertEquals("ElseBodyContext", nodeType);
		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 7, 7);
		Assert.assertEquals("else", code);
	}
	
	
	@Test
	public void testCid4IfMultiLine() throws Exception {
		int cid = 4;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 5, 5);
		Assert.assertEquals("IfStatementContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 5, 5);
		Assert.assertEquals("if (mIsLargeLayout) {", codeStart);
		
		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 8, 8);
		Assert.assertEquals("}", codeEnd);
	}
	
	@Test
	public void testCid4ElseMultiLine() throws Exception {
		int cid = 4;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 8, 8);
		Assert.assertEquals("ElseBodyContext", nodeType);
				
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 8, 8);
		Assert.assertEquals("else {", codeStart);
		
		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 17, 17);
		Assert.assertEquals("}", codeEnd);
	}
	

	@Test
	public void testCid25IfElseNestedIf() throws Exception {
		int cid = 25;				
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 41, 41, "IfStatementContext");
		Assert.assertEquals("if (isNewer && !isLessAccurate) {", codeStart);
		
		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 43, 43);
		Assert.assertEquals("}", codeEnd);
	}
	
	@Test
	public void testCid25IfElseNestedElse() throws Exception {
		int cid = 25;				
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 41, 41, "ElseBodyContext");
		Assert.assertEquals("else", codeStart);
	}
	
	
	@Test
	public void testCid14If() throws Exception {
		int cid = 14;

		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 8, 8);
		Assert.assertEquals("IfStatementContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 8, 8);
		Assert.assertEquals("if (checked)", codeStart);
	}
	
	@Test
	public void testCid14IfCharacters() throws Exception {
		int cid = 14;
		int uid = 5;
		
		String code = DbAndroidUnit.selectCodeFragment(c, cid);
		Pair<Integer,Integer> unitCharStartEnd = DbAndroidUnit.selectUnitCharStartEnd(c, cid, uid);
		
		String unit = code.substring(unitCharStartEnd.getLeft(), unitCharStartEnd.getRight());		
		Assert.assertEquals("if (checked)", unit);
	}
	
	@Test
	public void testCid14Else() throws Exception {
		int cid = 14;

		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 10, 10);
		Assert.assertEquals("ElseBodyContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 10, 10);
		Assert.assertEquals("else", codeStart);
	}
	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
