package ca.mcgill.cs.konaila.selection.line;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbEclipseGoldStandard;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestEclipseGoldStandardSanityCheck {
	
	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void test1059() throws Exception {		
		int cid = 1059;
		Assert.assertEquals(1, DbEclipseGoldStandard.selectGoldStandardSanity(c, cid, 2));
		Assert.assertEquals(0, DbEclipseGoldStandard.selectGoldStandardSanity(c, cid, 3));
		Assert.assertEquals(0, DbEclipseGoldStandard.selectGoldStandardSanity(c, cid, 13));
	}
	
	@Test
	public void testFirstLine() throws Exception {		
		int cid = 1015;
		Assert.assertEquals(0, DbEclipseGoldStandard.selectGoldStandardSanity(c, cid, 1));
		Assert.assertEquals(1, DbEclipseGoldStandard.selectGoldStandardSanity(c, cid, 2));
	}
	
	@Test
	public void testLastLine() throws Exception {		
		int cid = 1250;
		Assert.assertEquals(1, DbEclipseGoldStandard.selectGoldStandardSanity(c, cid, 18));
		Assert.assertEquals(0, DbEclipseGoldStandard.selectGoldStandardSanity(c, cid, 17));
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
