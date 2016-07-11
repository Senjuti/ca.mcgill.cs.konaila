package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestSanity {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void test() throws Exception {
		PreparedStatement s = c
				.prepareStatement("SELECT lineStart, lineEnd, cid FROM elements as F WHERE F.elementOrderInUnit=1");
		
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int lineStart = r.getInt(1);
			int lineEnd = r.getInt(2);
			int cid = r.getInt(3);
			Assert.assertTrue(lineStart <= lineEnd);
		}
		
	}
	
	@AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}

}
