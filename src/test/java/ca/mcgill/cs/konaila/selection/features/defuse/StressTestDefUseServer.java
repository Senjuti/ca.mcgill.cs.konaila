package ca.mcgill.cs.konaila.selection.features.defuse;

import java.sql.Connection;
import java.util.Arrays;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class StressTestDefUseServer {

	static Connection c;
//	static String url = DefUseServer.LOCAL_URL;
	static String url = DefUseServer.SERVER_URL;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void stressTest() throws Exception {		

		int cid = 5;
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		
		int i = 0;
		while ( i < 100) {
			System.out.println(i);
			
			List<KonailaVariableDef> vars = DefUseServer.analyzeDefUse(code, Strategy.ppa,
					ParsingAttribute.JavaCompilationUnit, url);
			
			List<String> targets = Arrays.asList(new String[]{"sharedPreferences","key","KEY_PREF_SYNC_CONN","connectionPref"});
			
			Assert.assertEquals(targets.size(), vars.size());
			
			for( KonailaVariableDef var : vars ) {
				Assert.assertTrue(targets.contains(var.getName()));
				Assert.assertTrue(var.getUses().size() >= 1);
			}
			i+=1;
			
			System.out.println(KonailaVariableDef.toJson(vars));
		}
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
