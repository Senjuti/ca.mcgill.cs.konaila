package ca.mcgill.cs.konaila.sanity;

import java.sql.Connection;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;
import ca.mcgill.cs.konaila.database.DatabaseFeatures;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestParsingAttributes {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testParsingAttributes() throws Exception {
		Collection<Integer> cids = DatabaseGetCodeFragments.getCids(c);
		
		Collection<Integer> cidParsingAttributes = DatabaseChopperStats.getCids(c);
		
		Assert.assertEquals(cids.size(), cidParsingAttributes.size());
	}
	
	@Test
	public void testParsingAttributesForJava() throws Exception {
		
		Collection<Integer> cidsJavaParsingAttributes = DatabaseChopperStats.getJavaCids(c);
		Collection<Integer> cidsWithSelectionUnits = DatabaseChopperStats.getCidsWithSelectionUnits(c);
		Assert.assertEquals(cidsJavaParsingAttributes.size(), cidsWithSelectionUnits.size());
	}

	@Test
	public void testJavaFilesShouldHaveAstProperties() throws Exception {
		
		Collection<Integer> cidsJavaParsingAttributes = DatabaseChopperStats.getJavaCids(c);
		Collection<Integer> cidsWithAstProperties = DatabaseFeatures.getNumberOfCidsWithAstProperties(c);
		// [35, 1038, 1047, 1083, 1209]
		cidsJavaParsingAttributes.removeAll(cidsWithAstProperties);
		
		Assert.assertEquals(0, cidsJavaParsingAttributes.size());
	}
	
	@Test
	public void testJavaFilesShouldHaveFeatures() throws Exception {
		
		Collection<Integer> cidsJavaParsingAttributes = DatabaseChopperStats.getJavaCids(c);
		int value = DatabaseFeatures.getNumberOfCidsWithFeatures(c);
		Assert.assertEquals(cidsJavaParsingAttributes.size(), value);
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
