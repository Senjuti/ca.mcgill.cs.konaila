package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestCharIndex {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void test() throws Exception {
		int cid = 55;
		int charStart = 197;
		int charEnd = 236;
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		
		String call = code.substring(charStart, charEnd);
		Assert.assertEquals(".setContentText(\"Download in progress\")", call);
	}
	
	
	@AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}

}
