package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestCallProperty {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid5MultipleModifiers() throws Exception {
		int cid=5;
		String property = "Call%";		
		Assert.assertEquals(4, DbAndroidProperty.selectNumberOfProperties(c, cid, property));		
	}
	
	@Test
	public void testCid5CallInIf() throws Exception {
		int cid=5;
		String konailaProperty = "Call%";		
		int unitLineStart = 7;
		int unitLineEnd = 11;

		Assert.assertEquals("IfWrapper", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, konailaProperty));	
	}
	
	
	@Test
	public void testCid5CallInLocalVar() throws Exception {
		int cid=5;
		String konailaProperty = "Call%";
		int unitLineStart = 8;
		int unitLineEnd = 8;		
		
		Assert.assertEquals("LocalVariableDeclarationStatement", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, konailaProperty));	
	}
	
	@Test
	public void testCid55CallChainWhereCallIsAUnit() throws Exception {
		int cid=55;
		String konailaProperty = "Call%";		
		int unitLineStart = 5;
		int unitLineEnd = 5;
				
		Assert.assertEquals("CallUnit", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, konailaProperty));	
	}
	
	@Test
	public void testCid55ToFixCallChain() throws Exception {
		int cid=55;
		String antlrProperty = "Call%";		
		int unitLineStart = 5;
		int unitLineEnd = 7;
		
//		mBuilder.setContentTitle("Picture Download")
//	    .setContentText("Download in progress")
//	    .setSmallIcon(R.drawable.ic_notification);
		
		Assert.assertEquals("SimpleStatement", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, antlrProperty));	
	}
	
	@Test
	public void testCid55SimpleStatementWithCalls() throws Exception {
		int cid=55;
		String antlrProperty = "Call%";		
		int unitLineStart = 5;
		int unitLineEnd = 7;
		
//		mBuilder.setContentTitle("Picture Download")
//	    .setContentText("Download in progress")
//	    .setSmallIcon(R.drawable.ic_notification);
		
		Assert.assertEquals("SimpleStatement", 
				DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, antlrProperty));	
	}
	
	@Test
	public void testCid55CallLine5() throws Exception {
		int cid=55;
		String antlrProperty = "CallName";		
		int unitLineStart = 5;
		int unitLineEnd = 5;
		
//		mBuilder.setContentTitle("Picture Download")
//	    .setContentText("Download in progress")
//	    .setSmallIcon(R.drawable.ic_notification);
		
		Assert.assertEquals("CallUnit", 
				DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, antlrProperty));	
	}
	
	@Test
	public void testCid55CallLine6() throws Exception {
		int cid=55;
		String antlrProperty = "CallName";		
		int unitLineStart = 5;
		int unitLineEnd = 6;
		
//		mBuilder.setContentTitle("Picture Download")
//	    .setContentText("Download in progress")
//	    .setSmallIcon(R.drawable.ic_notification);
		
		Assert.assertEquals("CallUnit", 
				DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, antlrProperty));	
	}
	
	@Test
	public void testCid55MultiLineStatementContainingOneCall() throws Exception {
		int cid=55;		
		int unitLineStart = 2;
		int unitLineEnd = 3;

		Assert.assertEquals("SimpleStatement", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, "Call%"));
		Assert.assertEquals("", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, "StatementExpressionContext"));
	}
	
	@Test
	public void testCid55StatementContainingOneCall() throws Exception {
		int cid=55;		
		int unitLineStart = 19;
		int unitLineEnd = 19;
		
//        mBuilder.setProgress(100, incr, false);

		Assert.assertEquals("SimpleStatement", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, "Call%"));
		Assert.assertEquals("", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, "StatementExpressionContext"));
	}

	@Test
	public void testCid55SingLine() throws Exception {
		int cid=55;		
		int unitLineStart = 32;
		int unitLineEnd = 32;

		Assert.assertEquals("CallUnit", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, "Call%"));
	}

	@Test
	public void testCid1248MultipleCallsMultipleLinesChars() throws Exception {
		int cid=1248;
		
		String code = DbAndroidUnit.selectCodeFragment(c, cid);
		
//	    line 2: PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, 
//      line 3:          "org.eclipse.faq.examples.books_view");

		String call1 = "PlatformUI.getWorkbench()";
		String call2 = "PlatformUI.getWorkbench().getHelpSystem()";
		String lastPartOfCall3 = "\"org.eclipse.faq.examples.books_view\")";
		
		int charStart = code.indexOf(call1);
		int charEnd1=charStart+call1.length();
		int charEnd2=charStart+call2.length();
		int charEnd3=code.indexOf(lastPartOfCall3) + lastPartOfCall3.length();
	
		int lineCharStart = code.split("\n")[1].indexOf(call1);
		int lineCharEnd1 = lineCharStart + call1.length();
		int lineCharEnd2 = lineCharStart + call2.length();
		int lineCharEnd3 = code.split("\n")[2].indexOf(lastPartOfCall3) + lastPartOfCall3.length();
		
		Assert.assertTrue( DbAndroidProperty.verifyCharStartEnd(c, cid, lineCharStart, lineCharEnd1, lineCharStart, charEnd1));
		Assert.assertTrue( DbAndroidProperty.verifyCharStartEnd(c, cid, lineCharStart, lineCharEnd2, lineCharStart, charEnd2));
		Assert.assertTrue( DbAndroidProperty.verifyCharStartEnd(c, cid, lineCharStart, lineCharEnd3, lineCharStart, charEnd3));
	}

	public void testCid1248MultipleCallsMultipleLines() throws Exception {		
		int cid = 1249;
		int unitLineStart = 2;
		int unitLineEnd = 3;
		
		Assert.assertEquals("SimpleStatement", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, "Call%"));		
	}
			
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
