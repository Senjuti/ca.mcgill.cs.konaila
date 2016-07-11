package ca.mcgill.cs.konaila.eclipse;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestEclipseMultiLineCall {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testCid228() throws Exception {
		int cid=1228;
		String konailaProperty = "Call%";		
		int unitLineStart = 20;
		int unitLineEnd = 26;

		
		String codeFragment = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		
		String[] lines = codeFragment.split("\n");
		String line26= lines[25];
		String line20= lines[19];
		int size26 = line26.length();
		int size20 = line20.length();
		String inAstPropertiesTable = codeFragment.substring(680, 1025);
		String inSelectionUnits = codeFragment.substring(680, 721);
		
		Assert.assertEquals("SimpleStatement", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, konailaProperty));	
	}
	
	
	@Test
	public void testCid59() throws Exception {
		int cid=1059;
		String konailaProperty = "Call%";		
		int unitLineStart = 1;
		int unitLineEnd = 13;

		Assert.assertEquals("SimpleStatement", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, konailaProperty));	
	}
	

	@Test
	public void testCid221NotProcess() throws Exception {
		int cid=1221;
		String konailaProperty = "CallContext";		
		int unitLineStart = 1;
		int unitLineEnd = 5;

		Assert.assertEquals("", DbAndroidProperty.selectNodeTypeOfMatchedProperty(
				c, cid, unitLineStart,unitLineEnd, konailaProperty));	
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}

}
