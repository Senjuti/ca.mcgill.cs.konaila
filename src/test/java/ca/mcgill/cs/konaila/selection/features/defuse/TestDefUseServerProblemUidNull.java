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

public class TestDefUseServerProblemUidNull {

	static Connection c;
	static String url = DefUseServer.LOCAL_URL;
//	static String url = DefUseServer.SERVER_URL;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid1082() throws Exception {
		int cid = 1082;
		String var = "running";
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		
		int index = code.indexOf("running");
				
		List<KonailaVariableDef> defs = DefUseServer.analyzeDefUse(code, Strategy.ppa, 
				ParsingAttribute.JavaBlockStatements, url);
		KonailaVariableDef def = KonailaVariableDef.getDef(defs, var, index, index + var.length());
		
		Assert.assertNotNull(def);
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
