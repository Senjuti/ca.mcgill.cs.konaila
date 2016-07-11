package ca.mcgill.cs.konaila.eclipse;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbEclipseUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestChopperEclipseMultiLineCall {
	
	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testClassCid1228() throws Exception {		
		int cid = 1228;
		
		String codeFragment = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		String[] lines = codeFragment.split("\n");
		String line20 = lines[19];
		String line26 = lines[25];
		Assert.assertEquals("delta.accept(new IResourceDeltaVisitor() { ... });", 
				DbEclipseUnit.selectCodeFromUnit(c, cid, 20, 26));		
	}
	
	@Test
	public void testClassCid1227() throws Exception {
//		no longer applicable because the code fragment is too short
//		int cid = 1227;
//		Assert.assertTrue(DbEclipseUnit.selectCodeFromUnit(c, cid, 3, 7).startsWith("ps.busyCursorWhile(new IRunnableWithProgress() {"));		
	}

	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
