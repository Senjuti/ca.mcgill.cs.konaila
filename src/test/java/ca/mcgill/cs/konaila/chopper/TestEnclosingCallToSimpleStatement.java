package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseEnclosingRelationships;
import ca.mcgill.cs.konaila.database.DatabaseEnclosingRelationships.CallUnit;

public class TestEnclosingCallToSimpleStatement {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid55() throws Exception {
		int cid = 55;		
				
		int uid = 4;		
		Assert.assertEquals(3,  DatabaseEnclosingRelationships.getEnclosingUid(c, cid, uid));
		
		uid = 5;		
		Assert.assertEquals(3,  DatabaseEnclosingRelationships.getEnclosingUid(c, cid, uid));
		
		uid = 3;		
		Assert.assertEquals(-1,  DatabaseEnclosingRelationships.getEnclosingUid(c, cid, uid));
	}

	@Test
	public void testCid4() throws Exception {
		int cid = 4;		
				
		int uid = 10;		
		int callUnitUid = DatabaseEnclosingRelationships.selectEnclosingThroughCorrespondingHole(c,
				new CallUnit(cid,uid,788,838));
		Assert.assertEquals(9, callUnitUid);
		Assert.assertEquals(9,  DatabaseEnclosingRelationships.getEnclosingUid(c, cid, uid));		
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
