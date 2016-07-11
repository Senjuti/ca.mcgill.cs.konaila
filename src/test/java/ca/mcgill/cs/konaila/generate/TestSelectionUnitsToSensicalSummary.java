package ca.mcgill.cs.konaila.generate;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.selection.ReconstructSummary;

public class TestSelectionUnitsToSensicalSummary {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid24SomeEllipses() throws Exception {
		int cid = 24;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{6,7});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
		String[] lines = intermediateSummary.split("\n");
		Assert.assertEquals(2, lines.length);
		
		String target = "Cursor cursor =getContentResolver().query( ... ,  ... , null, null, null);";
		Assert.assertEquals(target.replace(" ", ""),
				intermediateSummary.replace(" ","").replace("\n", ""));		
	}
	
	@Test
	public void testCid41Full() throws Exception {
		int cid = 41;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{3,4});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
		String[] lines = intermediateSummary.split("\n");
		Assert.assertEquals(2, lines.length);
				
		String target = "getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);";
		Assert.assertEquals(target, intermediateSummary.replace("\n",""));
	
	}
	
	@Test
	public void testCid41Uid3() throws Exception {
		int cid = 41;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{3});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
		String[] lines = intermediateSummary.split("\n");
		Assert.assertEquals(2, lines.length);
					
		String target = "....registerOnSharedPreferenceChangeListener(this);";
		Assert.assertEquals(target, intermediateSummary.replace(" ","").replace("\n",""));	
	}
	
	@Test
	public void testCid41SelectCallButNotStatementERROR() throws Exception {
		int cid = 41;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{4});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
		String[] lines = intermediateSummary.split("\n");
		Assert.assertEquals(2, lines.length);
					
		String target = "getPreferenceScreen().getSharedPreferences() ... ;";
		
		System.out.println("-->" + target);
		System.out.println("-->" + intermediateSummary.replace("\n",""));
		
		Assert.assertEquals(target,  intermediateSummary.replace("\n",""));	
		
	}
	
	@Test
	public void testCid55FullStatement() throws Exception {
		int cid = 55;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{3,4,5});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
		String[] lines = intermediateSummary.split("\n");
		Assert.assertEquals(3, lines.length);
				
		String target = "mBuilder.setContentTitle(\"Picture Download\").setContentText(\"Download in progress\").setSmallIcon(R.drawable.ic_notification);";
		Assert.assertEquals(target.replace(" ",""),  intermediateSummary.replace("\n","").replace(" " , ""));	
	}
	
	@Test
	public void testCid55SkipCall() throws Exception {
		int cid = 55;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{3,4});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
		
		String target = "....setContentText(\"Download in progress\")"
				+ ".setSmallIcon(R.drawable.ic_notification);";
		Assert.assertEquals(target.replace(" ", ""),
				intermediateSummary.replace(" ","").replace("\n",""));	
	}
	
	@Test
	public void testCid55SelectCallButNotStatement() throws Exception {
		int cid = 55;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{4,5});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
				
		String target = "mBuilder.setContentTitle(\"Picture Download\")"
				+ ".setContentText(\"Download in progress\") ... ;";
		
		System.out.println("-->" + target);
		System.out.println("-->" + intermediateSummary.replace("\n",""));
		
		Assert.assertEquals(target,  intermediateSummary.replace("\n",""));		
	}
	
	@Test
	public void testCid55VerifyEllipses() throws Exception {
		int cid = 55;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{2});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
		String[] lines = intermediateSummary.split("\n");
		Assert.assertEquals(1, lines.length);
				
		String target = "mBuilder = new NotificationCompat.Builder(this);";
		Assert.assertEquals(target,  intermediateSummary);	
	}
	
	
	@Test
	public void testCid4SelectStatementButNotCall() throws Exception {
		int cid = 4;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{9});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
				
		String target = "... .addToBackStack(null).commit();";
		
		System.out.println("-->" + target);
		System.out.println("-->" + intermediateSummary.replace("\n",""));
		
		Assert.assertEquals(target,  intermediateSummary.replace("\n",""));		
	}
	
	@Test
	public void testCid4CallButNotStatement() throws Exception {
		int cid = 4;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{10});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
				
		String target = "transaction.add(android.R.id.content, newFragment) ... ;";
		
		System.out.println("-->" + target);
		System.out.println("-->" + intermediateSummary.replace("\n",""));
		
		Assert.assertEquals(target,  intermediateSummary.replace("\n",""));
		
	}
	
	
	@Test
	public void testCid1CallButNotStatement() throws Exception {
		int cid = 1;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{20});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
				
		String target = "... builder.build() ... ;";
		
		System.out.println("-->" + target);
		System.out.println("-->" + intermediateSummary.replace("\n",""));
		
		Assert.assertEquals(target,  intermediateSummary.replace("\n",""));
		
	}
	
	
	@Test
	public void testCid1StatementButNotCall() throws Exception {
		int cid = 1;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{19});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
				
		String target = "cur = cr.query( ... ,   INSTANCE_PROJECTION,   selection,   selectionArgs,   null);";
		
		System.out.println("-->" + target);
		System.out.println("-->" + intermediateSummary.replace("\n",""));
		
		Assert.assertEquals(target.replace(" ", ""), 
				intermediateSummary.replace("\n","").replace(" ",""));
		
	}
	
	
	@Test
	public void testCid1FullStatement() throws Exception {
		int cid = 1;
		List<Integer> selectedUids = Arrays.asList(new Integer[]{19,20});			
		
		String intermediateSummary = ReconstructSummary.reconstructCodeBetter(c, cid, selectedUids);
				
		String target = "cur = cr.query(builder.build(),   INSTANCE_PROJECTION,   selection,   selectionArgs,   null);";
		
		System.out.println("-->" + target);
		System.out.println("-->" + intermediateSummary.replace("\n",""));
		
		Assert.assertEquals(target.replace(" ", ""),  
				intermediateSummary.replace("\n","").replace(" ", ""));
		
	}
	
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}