package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestMemberDeclarationMethod {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testCid5OneLine() throws Exception {
		int cid = 5;

		String startUnit = DbAndroidUnit.selectCodeFromUnit(c, cid, 6, 6);
		Assert.assertTrue(startUnit.startsWith("public void onSharedPreferenceChanged"));
		
		String endUnit = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 12, 12);
		Assert.assertEquals("}", endUnit);		
	}
	

	@Test
	public void testCid46TwoLines() throws Exception {
		int cid = 46;

		String startUnit = DbAndroidUnit.selectCodeFromUnit(c, cid, 14, 15);
		Assert.assertTrue(startUnit.startsWith("@Override") && startUnit.endsWith("{"));
				
		
		String endUnit = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 18, 18);
		Assert.assertEquals("}", endUnit);		
	}
	
	@Test
	public void testCid9ThreeLines() throws Exception {
		int cid = 9;

		String startUnit = DbAndroidUnit.selectCodeFromUnit(c, cid, 10, 12);
		Assert.assertTrue(startUnit.startsWith("@Override") && startUnit.endsWith("throws IOException {"));
				
		
		String endUnit = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 17, 17);
		Assert.assertEquals("}", endUnit);		
	}
	
	@Test
	public void testCid8Constructor() throws Exception {
		int cid = 8;
		String startUnit = DbAndroidUnit.selectCodeFromUnit(c, cid, 2, 2);
		Assert.assertTrue(startUnit.startsWith("public NumberPickerPreference"));
		
		String endUnit = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 10, 10);
		Assert.assertEquals("}", endUnit);		
	}

	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
