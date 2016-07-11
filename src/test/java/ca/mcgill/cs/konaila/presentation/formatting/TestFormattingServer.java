package ca.mcgill.cs.konaila.presentation.formatting;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestFormattingServer {

	static Connection c;
//	static String url = FormattingServer.LOCAL_URL;
	static String url = FormattingServer.SERVER_URL;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid5WithEllipses() throws Exception {		

		int cid = 5;
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);
				
		String compact = FormattingServer.format(code, How.Compact, url);
		
		Assert.assertEquals(code, compact); // nothing was done
	}
	
	@Test
	public void testCid5Ellipses() throws Exception {

		int cid = 5;
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);		
		code = code.replace("...", "");

		String compact = FormattingServer.format(code, How.Compact, url);
		String eclipse = FormattingServer.format(code, How.Eclipse, url);
		String vertical = FormattingServer.format(code, How.Vertical, url);
		
		int compactLength = compact.length();
		int eclipseLength = eclipse.length();
		int verticalLength = vertical.length();
		
		Assert.assertTrue(eclipseLength < compactLength);
		Assert.assertTrue(compactLength < verticalLength);

		Assert.assertTrue(eclipse.replace("\t", "").length() < eclipse.length());
	}

	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
