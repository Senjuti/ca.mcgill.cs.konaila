package ca.mcgill.cs.konaila.sanity;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;
import ca.mcgill.cs.konaila.database.DatabaseFeatures;

public class TestFeatures {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void test() throws Exception {
		Collection<String> features = DatabaseFeatures.getFeatures(c);		
		Collection<String> featureTypes = DatabaseFeatures.getFeatureTypes(c);
				
		Assert.assertEquals(features.size(), featureTypes.size());
		
		Collection<String> konailaNodeType = DatabaseChopperStats.getKonailaNodeTypes(c);
		Collection<String> selectionUnitMinusFeatures = new ArrayList<String>(konailaNodeType);
		selectionUnitMinusFeatures.removeAll(features);
		
		Collection<String> selectionUnitType = DatabaseChopperStats.getKonailaNodeTypes(c);
		Collection<String> selectionUnitTypeMinusFeatures = new ArrayList<String>(selectionUnitType);
		selectionUnitMinusFeatures.removeAll(features);
		
		Collection<String> featuresMinusSelectionUnitType = new ArrayList<String>(features);
		featuresMinusSelectionUnitType.removeAll(selectionUnitType);
		
		System.out.println();
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
