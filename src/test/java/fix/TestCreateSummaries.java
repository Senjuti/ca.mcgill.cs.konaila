package fix;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.DatabasePredictions.SelectedElement;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class TestCreateSummaries {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testAboveThresohld() throws Exception {
		int cid = 1015;
		Map<Integer,Float> uidToProbability = DatabasePredictions.selectEqualOrAboveThreshold(
				c, cid, (float)0.7);
		Assert.assertEquals(5, uidToProbability.size());		
	}
	
	@Test
	public void testTop() throws Exception {
		int cid = 1015;

		SummarizationMethod summarizationMethod = SummarizationMethod.ConfigurationBased;
		
		Assert.assertEquals(8, DatabasePredictions.selectTop(c, cid, (float)1, summarizationMethod).size());
		
		Assert.assertEquals(0, DatabasePredictions.selectTop(c, cid, (float)0.1, summarizationMethod).size());
		Assert.assertEquals(1, DatabasePredictions.selectTop(c, cid, (float)0.2, summarizationMethod).size());
		Assert.assertEquals(2, DatabasePredictions.selectTop(c, cid, (float)0.3, summarizationMethod).size());
		Assert.assertEquals(3, DatabasePredictions.selectTop(c, cid, (float)0.4, summarizationMethod).size());	
	}
	
	@Test
	public void testTopAtThreshold() throws Exception {
		float summarizationRate = (float)0.2;
		int cid = 1015;
		float probabilityThreshold = 
				DatabasePredictions.selectTopProbability(c, cid, summarizationRate, 
						SummarizationMethod.ConfigurationBased);
		
		Map<Integer,Float> uidToDbPredictions =
				DatabasePredictions.selectEqualOrAboveThreshold(c, cid, probabilityThreshold);
//		Assert.assertEquals(5, uidToDbPredictions.size());
			
		List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, uidToDbPredictions.keySet());
		for( SelectedElement e : elements ) {
			System.out.println(e.getCode());	
		}		
	}
	
	@Test
	public void testTopAtThresholdAll() throws Exception {
		
		List<Integer> cids = DatabasePredictions.getPredictionCids(c);
		
		for( Integer cid : cids) {
			float summarizationRate = (float)0.2;
			float probabilityThreshold = 
					DatabasePredictions.selectTopProbability(c, cid, summarizationRate,
							SummarizationMethod.ConfigurationBased);
			
			Map<Integer,Float> uidToDbPredictions =
					DatabasePredictions.selectEqualOrAboveThreshold(c, cid, probabilityThreshold);
//		Assert.assertEquals(5, uidToDbPredictions.size());
				
			List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, uidToDbPredictions.keySet());
			
			System.out.println("--------------" + cid + "------------");
			for( SelectedElement e : elements ) {
				System.out.println(e.getCode());	
			}		
		}
	}
	
	
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
