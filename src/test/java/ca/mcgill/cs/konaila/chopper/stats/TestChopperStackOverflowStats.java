package ca.mcgill.cs.konaila.chopper.stats;

import java.sql.Connection;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestChopperStackOverflowStats {

	static Connection c;
	static final String STACKOVERFLOW = "stackoverflow";
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void test() throws Exception {
		List<Integer> problematicCids = DatabaseChopperStats.getCodeFragmentsWithProblemWithGeneratingSelectionUnits(c, STACKOVERFLOW);
		for( Integer cid : problematicCids ) {
			String codeFragment = DatabaseGetCodeFragments.getCodeFragment(c, cid);
			if( codeFragment.trim().startsWith("<") && codeFragment.trim().endsWith(">")) {
//				System.out.println("---xml---");
			} else if( codeFragment.length() - codeFragment.replace("\n", "").length() <=4 ) {
//				System.out.println("---too short---");			
			} else {
				System.out.println("---don't know---");
				System.out.println(codeFragment);	
			}

		}

		String codeChanged = "public class MyAppModule extends AbstractModule {\n"
		+ "@Singleton\n"
		+ "@Provides\n"
		+ "public SessionFactory provideSessionFactory(MyAppConfiguration configuration) {\n"
		+ "HibernateBundle<MyAppConfiguration> hibernate = new HibernateBundle<ExampleConfiguration>(MyModel.class) {\n"
		+ "@Override\n"
		+ "public DataSourceFactory getDataSourceFactory(MyAppConfiguration configuration) {\n"
		+ "return configuration.getDataSourceFactory();\n"
		+ "}\n"
		+ "};\n"
		+ "return hibernate.getSessionFactory();\n"
		+ "}\n"
		+ "}\n";
		System.out.println();				
	}
	
	@AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
	

	
}
