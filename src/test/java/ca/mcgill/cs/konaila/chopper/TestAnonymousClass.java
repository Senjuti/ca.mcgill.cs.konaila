package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestAnonymousClass {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testCid2CallWithAnonymousClass() throws Exception {
		int cid = 2;
		
		Assert.assertEquals("menuItem.setOnActionExpandListener(new OnActionExpandListener() {",
				DbAndroidUnit.selectCodeFromUnit(c, cid, 7, 7, "StatementContext"));
		Assert.assertEquals("});", DbAndroidUnit.selectCodeFromSecondElement(c, cid, 19, 19));

		Assert.assertEquals("menuItem.setOnActionExpandListener(new OnActionExpandListener() { ... });",
				DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, 7, 7, "StatementContext"));
	}
	
	@Test
	public void testCid55AnonymousClassNewLine() throws Exception {
		int cid = 55;
		
		Assert.assertEquals("new Thread(", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, 9, 9));
		Assert.assertEquals("new Thread( ... ).start();", 
				DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, 9, 9, "StatementContext"));
		
		int uid = DbAndroidUnit.selectUid(c, cid, 10, 10, "ConstructorContext");
		int start = 4;
		Assert.assertEquals(start, DbAndroidUnit.selectLineCharStartOfSecondElement(c, cid, uid));
		Assert.assertEquals(start + "}".length(), DbAndroidUnit.selectLineCharEndOfSecondElement(c, cid, uid));
		Assert.assertEquals("new Runnable() { ... }", 
				DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, 10, 10, "ConstructorContext"));
		
		
		
	}
	
	@Test
	public void testCid37NoExtraAnonymousClassCreationUnitOnSameLine() throws Exception {
		int cid = 37;
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 1);
		Assert.assertEquals("BroadcastReceiver mUsbReceiver = new BroadcastReceiver() {", code);
		
		int uid = DbAndroidUnit.selectUid(c, cid, 1, 1, "LocalVariableDeclarationStatementContext");
		Assert.assertEquals(0, DbAndroidUnit.selectLineCharStartOfSecondElement(c, cid, uid));
		Assert.assertEquals("};".length(), DbAndroidUnit.selectLineCharEndOfSecondElement(c, cid, uid));
	}
	

	@Test
	public void testCid22ChainsWithinAnonymousClassCreationSetNegativeButton() throws Exception {
		int cid = 22;
		
//		J22.java - lines 17-21
//	    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//	        public void onClick(DialogInterface dialog, int id) {
//	            LoginDialogFragment.this.getDialog().cancel();
//	        }
//	    });  
		
		Assert.assertTrue(DbAndroidUnit.selectCodeFromUnit(c,cid,18,18,"MemberDeclarationMethodContext")
				.contains("onClick"));		
		Assert.assertEquals(".setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, 17, 17, "StatementContext"));
		Assert.assertEquals(" ... .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { ... });", 
				DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, 17, 17, "StatementContext"));	

	}	
	

	
	@Test
	public void testCid22ChainsWithinAnonymousClassCreationSsetPositiveButton() throws Exception {
		int cid = 22;
//		J22.java - lines 11-16
//		  .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {
//	    	@Override
//	      		public void onClick(DialogInterface dialog, int id) {
//	          	// sign in the user ...
//	      	}
//		  })		

		Assert.assertTrue(DbAndroidUnit.selectCodeFromUnit(c,cid,18,18,"MemberDeclarationMethodContext")
				.contains("onClick"));
		Assert.assertEquals(".setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() {", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, 11, 11, "CallContext"));
		Assert.assertEquals(" ... .setPositiveButton(R.string.signin, new DialogInterface.OnClickListener() { ... })", 
				DbAndroidUnit.selectCodeDisplayWholeFromUnit(c, cid, 11, 11, "CallContext"));
		

	}	
	
	
	@AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}

}
