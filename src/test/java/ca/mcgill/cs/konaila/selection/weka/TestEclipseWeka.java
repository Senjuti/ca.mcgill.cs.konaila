package ca.mcgill.cs.konaila.selection.weka;

import java.sql.Connection;

import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestEclipseWeka {
	
	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testEvaluation() throws Exception {
//		Weka.dataPrep(c);
//		trainModel();
		Weka.evaluateModelCodeFragmentBased(c); 
	}
	
	@Test
	public void testPrediction() throws Exception {
//		Weka.dataPrep(c);
//		trainModel();
		Weka.predict(c); 
	}


}
