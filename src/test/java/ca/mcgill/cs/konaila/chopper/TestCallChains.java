package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestCallChains {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	// J55.java - line 5-7
//	mBuilder.setContentTitle("Picture Download")
//    .setContentText("Download in progress")
//    .setSmallIcon(R.drawable.ic_notification);
	
//	Statement 
//		StatementExpr 
//			Call -> ExprDotId ( ExprList )  
//				    ExprDotId:Call.setSmallIcon---------------------------------------------- ----------line 5-7
//						      Call:ExprDotId( ExprList )
//							       ExprDotId:Call.setContentText ---------------------------------------line 5-6
//								             Call:ExprDotId( ExprList )
//									              ExprDotId:ExprPrimary.setContentTitle
//										                    ExprPrimary:PrimaryId 
//											                            PrimaryId:mBuilder--------------line 5
								
	
	@Test
	public void testCid55SameLineAsParent() throws Exception {
		// same line as parent, don't need to create (J55: call "setSmallIcon") 
		int cid = 55;
		int uid = 3;
		
		Assert.assertEquals("StatementContext", DbAndroidUnit.selectAntlrNodeType(c, cid, uid));
		Assert.assertEquals(".setSmallIcon(R.drawable.ic_notification);", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, 7, 7, "StatementContext"));
		Assert.assertEquals(" ... .setSmallIcon(R.drawable.ic_notification);",
				DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, 7, 7, "StatementContext"));
	}

	@Test
	public void testCid55OnOneLine() throws Exception {
		// on one line - create simple unit (J55: call "setContentTitle")
		int cid = 55;
		int uid = 5;
		
		Assert.assertEquals("CallContext", DbAndroidUnit.selectAntlrNodeType(c, cid, uid));
		Assert.assertEquals("mBuilder.setContentTitle(\"Picture Download\")", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, 5, 5, "CallContext"));
		Assert.assertEquals("mBuilder.setContentTitle(\"Picture Download\")", 
				DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, 5, 5, "CallContext"));
	}	
	
	@Test
	public void testCid55MultiLineButNotSameAsParentStatement() throws Exception {
		// multi line - create multi line unit (J55: call "setContentText")
		int cid = 55;
		int uid = 4;
		
		Assert.assertEquals("CallContext", DbAndroidUnit.selectAntlrNodeType(c, cid, uid));
		Assert.assertEquals(".setContentText(\"Download in progress\")", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, 6, 6, "CallContext"));
		Assert.assertEquals(" ... .setContentText(\"Download in progress\")", 
				DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, 6, 6, "CallContext"));
	}
	
	@Test
	public void testCid55MultiLineButOneCall() throws Exception {
		// it's a simple unit when the statement has 2 lines but 1 call
//		mNotifyManager =
//		        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		int cid = 55;
		int uid = 1;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 2, 3, "StatementContext");
		
		Assert.assertEquals("StatementContext", DbAndroidUnit.selectAntlrNodeType(c, cid, uid));
		Assert.assertTrue(code.startsWith("mNotifyManager ="));
		Assert.assertTrue(code.endsWith("(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);"));
	}
	
	@Test
	public void testCid22CallChainOneLineDoNotCreateUnit() throws Exception {
		int cid = 22;
//		J22.java - line 5
//	    LayoutInflater inflater = getActivity().getLayoutInflater();

		Assert.assertEquals("LayoutInflater inflater = getActivity().getLayoutInflater();", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, 5, 5, "LocalVariableDeclarationStatementContext"));		
		Assert.assertEquals("", DbAndroidUnit.selectCodeFromUnit(c, cid, 5, 5, "CallContext"));
	}
	
	@Test
	public void testCid22CallChainsInAnonymousClass() throws Exception {
		int cid = 22;
//		J22.java - line 19
//        LoginDialogFragment.this.getDialog().cancel();

		Assert.assertEquals("LoginDialogFragment.this.getDialog().cancel();", DbAndroidUnit.selectCodeFromUnit(c, cid, 19, 19, "StatementContext"));		

	}
	
	@Test
	public void testCid22CallChainsComplexStatement() throws Exception {
		int cid = 22;
//		J22.java - line 9
//	    builder.setView(inflater.inflate(R.layout.dialog_signin, null))

		Assert.assertEquals(1, DbAndroidUnit.selectNumberNodeTypes(c, cid, 9, 9, "CallContext")); 
	}
	
	public void testCid41() throws Exception {
		int cid = 41;
//    getPreferenceScreen().getSharedPreferences()
//    .unregisterOnSharedPreferenceChangeListener(this);
		
		Assert.fail("fix");
		
	}
	
	@AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
