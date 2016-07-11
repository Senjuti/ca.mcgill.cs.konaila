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

public class TestTryCatchFinally {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid12Try() throws Exception {
		int cid = 12;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 8, 8);
		Assert.assertEquals("TryCatchFinallyContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 8, 8);
		Assert.assertEquals("try {", codeStart);
		
		int lineCharStart = DbAndroidUnit.selectLineCharStart(c, cid, 8, 8);
		Assert.assertEquals(0, lineCharStart);

		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 29, 29);
		Assert.assertEquals("}", codeEnd);
	}
	
	@Test
	public void testCid12Catch() throws Exception {
		int cid = 12;
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 29, 29);
		Assert.assertEquals("CatchClauseContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 29, 29);
		Assert.assertEquals("catch (FileNotFoundException e) {", codeStart);

		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 31, 31);
		Assert.assertEquals("}", codeEnd);
	}
	
	@Test
	public void testCid12Finally() throws Exception {
		int cid = 12;

		String code = DbAndroidUnit.selectCodeFragment(c, cid);		
		String[] lines = code.split("\n");
		
		String nodeType = DbAndroidUnit.selectAntlrNodeType(c, cid, 39, 39);
		Assert.assertEquals("FinallyBlockContext", nodeType);
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 39, 39);
		Assert.assertEquals("finally {", codeStart);

		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 49, 49);
		Assert.assertEquals("}", codeEnd);
	}
	
	
	@Test
	public void testCid1228Try() throws Exception {
		int cid = 1228;
		int uid = 14;
		String antlrNodeType = "TryCatchFinallyContext";
		String code = DbAndroidUnit.selectCodeFragment(c, cid);		
		int startFromIndexOf = code.indexOf("try {");
		
		Pair<Integer,Integer> charStartEnd = DbAndroidUnit.selectUnitCharStartEnd(c, cid, uid);
		int start = charStartEnd.getLeft();
		int end = charStartEnd.getRight();
				
		String tryBlock = code.substring(start, end+1);
		Assert.assertEquals(startFromIndexOf, start);
		Assert.assertTrue(tryBlock.trim().endsWith("}"));
			
	}
	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
