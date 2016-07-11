package fix;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.MainEclipseTrainingBasedSummaries;
import ca.mcgill.cs.konaila.database.Database;

public class TestGenerate {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void test1175() throws Exception {
		String summary = MainEclipseTrainingBasedSummaries.generateSummary(c, 1175, (float)0.2);		
		Assert.assertEquals("mm.add(menu1);", summary);
	}
	// 11056: ErrorDialog.openError(window.getShell(), ..., ...,info);
	
	@Test
	public void test1102() throws Exception {
		String summary = MainEclipseTrainingBasedSummaries.generateSummary(c, 1102, (float)0.2);		
		Assert.assertTrue(summary.indexOf("public Object run(...)  {") != -1);
	}
	
	@Test
	public void test1015() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
