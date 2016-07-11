package ca.mcgill.cs.konaila.eclipse;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbEclipseUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestChopperEclipseCid1228 {
	
	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testMethodSignatureMultiLine() throws Exception {		
		int cid = 1228;
		Assert.assertTrue(DbEclipseUnit.selectCodeFromUnit(c, cid, 16, 30).startsWith("private void incrementalBuild(IResourceDelta delta,"));			
	}
	
	@Test
	public void testMethodSignatureMultiLine2() throws Exception {		
		int cid = 1228;
		Assert.assertTrue(DbEclipseUnit.selectCodeFromUnit(c, cid, 2, 15).startsWith("protected IProject[] build(int kind, Map args,"));		
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
