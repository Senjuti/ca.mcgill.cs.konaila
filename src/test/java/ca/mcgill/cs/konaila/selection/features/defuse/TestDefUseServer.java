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
import ca.mcgill.cs.konaila.database.DatabaseFeaturesDataflow;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestDefUseServer {

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
	public void testCid5PPA() throws Exception {		

		int cid = 5;
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		
		System.out.println(code);
		
		List<KonailaVariableDef> vars = DefUseServer.analyzeDefUse(code, Strategy.ppa,
				ParsingAttribute.JavaCompilationUnit, url);
		
		List<String> targets = Arrays.asList(new String[]{"sharedPreferences","key","KEY_PREF_SYNC_CONN","connectionPref"});
		
		Assert.assertEquals(targets.size(), vars.size());
		
		for( KonailaVariableDef var : vars ) {
			System.out.println(var.getName());
			Assert.assertTrue(targets.contains(var.getName()));
			Assert.assertTrue(var.getUses().size() >= 1);
		}
	}
	
	@Test
	public void testCid5PPAServer() throws Exception {		

		int cid = 5;
		String targetName = "sharedPreferences";
		
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);	
		List<KonailaVariableDef> vars = DefUseServer.analyzeDefUse(code, Strategy.ppa,
				ParsingAttribute.JavaCompilationUnit, url);	
		
		KonailaVariableDef result = getVariable(targetName, vars);

		int index = code.indexOf(targetName + ",");		

		Assert.assertEquals(281, index);
		Assert.assertEquals(index, result.getCharStart());
		Assert.assertEquals(index + targetName.length(), result.getCharEnd());
		Assert.assertEquals(targetName, code.substring(result.getCharStart(), result.getCharEnd()));
		Assert.assertEquals(result.getName(), code.substring(result.getCharStart(), result.getCharEnd()));
	}	
	
	@Test
	public void testCid5PPAVariableDatabase() throws Exception {		

		int cid = 5;
		String targetName = "sharedPreferences";
		
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);

		int index = code.indexOf(targetName + ",");			

		Assert.assertEquals(281, index);
		Assert.assertEquals(index, 
				DatabaseFeaturesDataflow.getDefCharStart(c, cid, "sharedPreferences"));
		Assert.assertEquals(index + targetName.length(), 
				DatabaseFeaturesDataflow.getDefCharEnd(c, cid, "sharedPreferences"));	
		}
	
	@Test
	public void testCid5Eclipse() throws Exception {		

		int cid = 5;
		String code = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		
		System.out.println(code);
		
		List<KonailaVariableDef> vars = DefUseServer.analyzeDefUse(code, Strategy.eclipse, 
				ParsingAttribute.JavaCompilationUnit, url);
		
		List<String> targets = Arrays.asList(new String[]{"KEY_PREF_SYNC_CONN","key"});
		
		Assert.assertEquals(targets.size(), vars.size());
		
		for( KonailaVariableDef var : vars ) {
			Assert.assertTrue(targets.contains(var.getName()));
			Assert.assertTrue(var.getUses().size() >= 1);
		}		
	}
	
	private static KonailaVariableDef getVariable(String target, 
			List<KonailaVariableDef> vars) {		
		for( KonailaVariableDef var : vars ) {
			if( var.getName().equals(target)) {			
				return var;
			}
		}
		return null;
	}
	
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
