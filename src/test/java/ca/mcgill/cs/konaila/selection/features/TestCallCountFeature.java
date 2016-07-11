package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestCallCountFeature {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid5CallInIf() throws Exception {
		int cid=5;
		int lineNumber= 7;	
		Assert.assertEquals(1, DbAndroidProperty.getCallValue(c, cid, lineNumber));		
	}
	
	
	@Test
	public void testCid5CallOfCall() throws Exception {
		int cid=5;
		int lineNumber=	10;
		
		// J5, line=10: 
		// connectionPref.setSummary(sharedPreferences.getString(key, ""));
	
		Assert.assertEquals(2, DbAndroidProperty.getCallValue(c, cid, lineNumber));	
	}
	
	@Test
	public void testCid55CallChain1() throws Exception {
		int cid=55;
		int lineNumber=	5;
		
		// line 5:
//		mBuilder.setContentTitle("Picture Download")     uid=5 CallUnit
//	    .setContentText("Download in progress")          uid=4 CallUnit
//	    .setSmallIcon(R.drawable.ic_notification);       uid=3 SimpleStatement
		
		Assert.assertEquals(1, DbAndroidProperty.getCallValue(c, cid, lineNumber));		
	}
	
	@Test
	public void testCid55ToCallChain2() throws Exception {
		int cid=55;
		int lineNumber=6;
		
		Assert.assertEquals(1, DbAndroidProperty.getCallValue(c, cid, lineNumber));		
	}
	

	@Test
	public void testCid1248MultipleCallsMultipleLinesChars() throws Exception {
//		code fragment too short
//		int cid=1248;
//		int lineNumber=2;
//		
//		Assert.assertEquals(2, DbAndroidProperty.getCallValue(c, cid, lineNumber));	
		
	}
	
	public void testCid1228CallUnitWithTwoCalls() throws Exception {		
		// J1228; lines 23-24
//		                        System.out.println("changed: "
//		                                           + delta.getResource().getRawLocation());		
		int cid=1228;
		int lineNumber=23;

		Assert.assertEquals(2, DbAndroidProperty.getCallValue(c, cid, lineNumber));	
	}
	
	public void testCid41TwoCalls() throws Exception {		
		// J41: lines 11
//		getPreferenceScreen().getSharedPreferences()
		
		int cid=41;
		int lineNumber=11;

		Assert.assertEquals(2, DbAndroidProperty.getCallValue(c, cid, lineNumber));	
	}
	
	public void testCid15() throws Exception {
//		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		int cid=15;
		int lineNumber=10;
		Assert.assertEquals(2, DbAndroidProperty.getCallValue(c, cid, lineNumber));	
	}
	
	public void testCid22() throws Exception {
		int cid = 22;
		// LayoutInflater inflater = getActivity().getLayoutInflater();
		int lineNumber = 5;
		Assert.assertEquals(2, DbAndroidProperty.getCallValue(c, cid, lineNumber));
		
		// builder.setView(inflater.inflate(R.layout.dialog_signin, null))
		lineNumber = 9;
		Assert.assertEquals(2, DbAndroidProperty.getCallValue(c, cid, lineNumber));
		
		// LoginDialogFragment.this.getDialog().cancel();
		lineNumber = 19;
		Assert.assertEquals(2, DbAndroidProperty.getCallValue(c, cid, lineNumber));
	}
	
	public void testCid9() throws Exception {
		int cid=9;
		int lineNumber = 15;
		// super.onRestore(data, appVersionCode, newState);

		Assert.assertEquals(1, DbAndroidProperty.getCallValue(c, cid, lineNumber));
	}
	
	public void testCid13() throws Exception {
//		if (item.isChecked())
		int cid = 13;
		int lineNumber = 6;

		Assert.assertEquals(1, DbAndroidProperty.getCallValue(c, cid, lineNumber));
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
