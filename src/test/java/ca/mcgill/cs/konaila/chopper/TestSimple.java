package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestSimple {
	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testPackage() throws Exception{
		int cid = 40;
		String p = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 1);
		Assert.assertEquals("package com.example.android.rs.hellocompute;", p);	
	}
	
	@Test
	public void testImportStatements() throws Exception{
		int cid = 40;
		int[] lines = new int[] {3,4,5,6,7,8,9};
		for( int line : lines ) {
			String i = DbAndroidUnit.selectCodeFromUnit(c, cid, line, line);
			Assert.assertTrue(i.startsWith("import ") && i.endsWith(";"));
		}
	}
	

		
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}

}
