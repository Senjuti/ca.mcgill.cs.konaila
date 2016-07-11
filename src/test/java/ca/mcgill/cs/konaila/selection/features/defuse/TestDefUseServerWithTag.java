package ca.mcgill.cs.konaila.selection.features.defuse;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestDefUseServerWithTag {

	static Connection c;
	static String url = DefUseServer.URL;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}	

	@Test
	public void testCid1047PPA() throws Exception {		

		int cid = 1047;
		String var = "buffer";
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		
		int index = code.indexOf(var + " =");
		
		List<KonailaVariableDef> defs = DefUseServer.analyzeDefUse(code, Strategy.ppa, 
				ParsingAttribute.JavaBlockStatements, url);
		KonailaVariableDef def = KonailaVariableDef.getDef(defs, var, index, index + var.length());
		
		Assert.assertNotNull(def);
		System.out.println();
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
