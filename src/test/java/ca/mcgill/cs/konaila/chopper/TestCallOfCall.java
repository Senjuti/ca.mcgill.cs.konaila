package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestCallOfCall {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	

	@Test
	public void testCid11() throws Exception {
		int cid = 11;
		String enclosingStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 11, 14);
		Assert.assertTrue(enclosingStart.startsWith("menu.addIntentOptions("));
		
		String enclosingEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 15, 19);
		Assert.assertTrue(enclosingEnd.endsWith(");"));
				
		String call = DbAndroidUnit.selectCodeFromUnit(c, cid, 15, 15);
		Assert.assertEquals("this.getComponentName()", call);		
	}
	
	@Test
	public void testCid11CharOuterCall() throws Exception {
		int cid = 11;
		
		String code = DbAndroidUnit.selectCodeFragment(c, cid);

		// outer: firstElementEnd: 11:14
		String lastPartOfFirstElement = "0,";
		int outerFirstElementEndExpected = code.split("\n")[13].indexOf(lastPartOfFirstElement)
				+ lastPartOfFirstElement.length();
		int uidOuter = DbAndroidUnit.selectUid(c, cid, 11, 14, "StatementContext");
		int outerFirstElementEnd = DbAndroidUnit.selectLineCharEnd(c, cid, 11, 14);
		Assert.assertEquals(outerFirstElementEndExpected, outerFirstElementEnd);
		
	
		// outer: secondElementStart 15:32
		String lastPartOfSecondElement = "null);";
		int outerSecondElementEndExpected = code.split("\n")[18].indexOf(lastPartOfSecondElement)
				+ lastPartOfSecondElement.length();
		int outerSecondElementEnd = DbAndroidUnit.selectLineCharEndOfSecondElement(c, cid, uidOuter);
		Assert.assertEquals(outerSecondElementEndExpected, outerSecondElementEnd);
		
		String firstPartOfSecondElement = ",   // The current activity name";
		int outerSecondElementStartExpected = code.split("\n")[14].indexOf(firstPartOfSecondElement);
		int outerSecondElementStart = DbAndroidUnit.selectLineCharStartOfSecondElement(c, cid, uidOuter);
		Assert.assertEquals(outerSecondElementStartExpected, outerSecondElementStart);
	}
		
	@Test
	public void testCid11CharInnerCall() throws Exception {
		int cid = 11;
		String call = "this.getComponentName()";
		String code = DbAndroidUnit.selectCodeFragment(c, cid);
		int charStart = code.indexOf(call);
		int charEnd = charStart + call.length();
		int lineCharStart = code.split("\n")[14].indexOf(call);
		int lineCharEnd = lineCharStart + call.length();
		
		Assert.assertTrue( DbAndroidProperty.verifyCharStartEnd(c, cid, lineCharStart,lineCharEnd, charStart, charEnd) );	
	}
	
	@Test
	public void testCid1() throws Exception {
		int cid = 1;
		String enclosingStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 37, 37, "StatementContext");
		Assert.assertEquals("cur =  cr.query(", enclosingStart);
		
		String enclosingEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 37, 41);
		Assert.assertTrue(enclosingEnd.endsWith(");"));
				
		String call = DbAndroidUnit.selectCodeFromUnit(c, cid, 37, 37, "CallContext");
		Assert.assertEquals("builder.build()", call);	
	}
	
	@Test
	public void testCid29CallOnFirstLineOfStatement() throws Exception {
		int cid = 29;
		
		Assert.assertEquals("getApplicationContext()", DbAndroidUnit.selectCodeFromUnit(c, cid, 10, 10, "CallContext"));
		Assert.assertEquals("notification.setLatestEventInfo(", DbAndroidUnit.selectCodeFromUnit(c, cid, 10, 10, "StatementContext"));
		Assert.assertTrue(DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, 10, 10, "StatementContext")
				.startsWith("notification.setLatestEventInfo( ... "));

	}
	
	
	// TOOD test call of call of call
	// TODO call with multiple parameters that are calls
	
	// TODO chains classBody/J04.java
//    transaction.add(android.R.id.content, newFragment)
//    .addToBackStack(null).commit();
	
	@Test
	public void testCid22CallOfCallInComplexStatement() throws Exception {
		int cid = 22;
		int lineNumber = 9;
//		J22.java - line 9
//          builder.setView(inflater.inflate(R.layout.dialog_signin, null)

		Assert.assertEquals("builder.setView(inflater.inflate(R.layout.dialog_signin, null))", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, lineNumber, lineNumber, "CallContext"));	
	}
	

	@Test
	public void testCid25TwoCallsAsParameters() throws Exception {
		int cid = 25;
		int lineNumber = 35;
//		J25.java - line 35
//	    boolean isFromSameProvider = isSameProvider(location.getProvider(),
//	            currentBestLocation.getProvider());

		Assert.assertEquals("boolean isFromSameProvider = isSameProvider(", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, lineNumber, lineNumber, "LocalVariableDeclarationStatementContext"));
		Assert.assertEquals("boolean isFromSameProvider = isSameProvider( ... , ... );", 
				DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, lineNumber, lineNumber, "LocalVariableDeclarationStatementContext"));
		Assert.assertEquals("location.getProvider()", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, lineNumber, lineNumber, "CallContext"));
		Assert.assertEquals("currentBestLocation.getProvider()", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, lineNumber+1, lineNumber+1, "CallContext"));	
	}
	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}

}
