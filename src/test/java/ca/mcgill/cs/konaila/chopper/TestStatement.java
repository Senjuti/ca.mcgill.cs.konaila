package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestStatement {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid9LineLineBlock() throws Exception {		
		int cid = 9;		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 6, 6, "StatementContext");
		Assert.assertEquals("super.onBackup(oldState, data, newState);", code);
	}

	@Test
	public void testCid3() throws Exception {		
		int cid = 3;		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 7, 7, "StatementContext");
		Assert.assertEquals("Toast.makeText(this, \"landscape\", Toast.LENGTH_SHORT).show();", code);
	}
	
	@Test
	public void testCid42InsideInnerClass() throws Exception {
		int cid = 42;		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 13, 13, "StatementContext");
		Assert.assertEquals("Toast.makeText(getApplicationContext(), \"hello!\", Toast.LENGTH_SHORT).show();", code);		
	}

	@Test
	public void testCid8InConstructorAndClassDeclaration() throws Exception {
		int cid = 8;		
		String code = DbAndroidUnit.selectCodeFromUnit(c, cid, 9, 9, "StatementContext");
		Assert.assertEquals("setDialogIcon(null);", code);		
	}
		
	@AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
