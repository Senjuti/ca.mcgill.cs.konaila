package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestSynchronzied {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	
	@Test
	public void testCid9() throws Exception {

		int cid = 9;
		
		String codeStart = DbAndroidUnit.selectCodeFromUnit(c, cid, 5, 5, "SynchronizedStatementContext");
		Assert.assertEquals("synchronized (MyActivity.sDataLock) {", codeStart);
		
		String codeEnd = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 7, 7);
		Assert.assertEquals("}", codeEnd);
	}
	


	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
