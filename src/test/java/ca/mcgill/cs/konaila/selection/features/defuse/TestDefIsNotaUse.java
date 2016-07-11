package ca.mcgill.cs.konaila.selection.features.defuse;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;
import ca.mcgill.cs.konaila.database.DatabaseFeaturesDataflow;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestDefIsNotaUse {

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
	public void testCid1083() throws Exception {		

		int cid = 1083;
		int targetNumberOfUses = 5; // not including def
		String varName = "buffer";
		int charStart=55, charEnd=61;
		
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		String parsingAttributeString = DatabaseChopperStats.getJustification(c, cid);
		ParsingAttribute parsingAttribute = ParsingAttribute.valueOf(parsingAttributeString);		
	
		List<KonailaVariableDef> vars = DefUseServer.analyzeDefUse(code, Strategy.ppa,
				parsingAttribute, url);
		
		KonailaVariableDef var = KonailaVariableDef.getDef(vars, varName, charStart, charEnd);
		Assert.assertEquals(targetNumberOfUses, var.getUses().size());
				
		Assert.assertEquals(targetNumberOfUses, 
				DatabaseFeaturesDataflow.getNumberOfUses(c, cid, varName, 
						charStart, charEnd));
	}
	
	
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
