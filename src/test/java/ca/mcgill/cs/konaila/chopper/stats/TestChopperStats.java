package ca.mcgill.cs.konaila.chopper.stats;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class TestChopperStats {

	static Connection c;
	static final String ANDROID_GUIDE = "android-guide";
	static final String ECLIPSE_FAQ = "eclipse-faq";
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testTypeCounts() throws Exception {
		Map<String,Integer> typeToCounts = DatabaseChopperStats.getTypeCounts(c, ANDROID_GUIDE);
		Assert.assertTrue(typeToCounts.size() > 20);		
	}

	@Test
	public void testSanityCheckAndroid() throws Exception {
		int numberOfCodeFragments = DatabaseGetCodeFragments.getJavaCodeFragmentsCidsLongEnough(c, ANDROID_GUIDE).size();
		int numberOfCidsInSelectionUnits = DatabaseChopperStats.getNumberOfCids(c, ANDROID_GUIDE);
		
		Assert.assertEquals(numberOfCodeFragments, numberOfCidsInSelectionUnits);		
	}

	@Test
	public void testSanityCheckEclipse() throws Exception {
		List<Integer> codeFragmentsCids = DatabaseGetCodeFragments.getJavaCodeFragmentsCidsLongEnough(c, ECLIPSE_FAQ);
		List<Integer> selectionUnitsCids = DatabaseChopperStats.getCids(c, ECLIPSE_FAQ);
				
		Assert.assertEquals(codeFragmentsCids.size(), selectionUnitsCids.size());		
		
//		private void externalModify(IFile iFile) throws ... {
//		    java.io.File file = iFile.getLocation().toFile();
//		    FileOutputStream fOut = new FileOutputStream(file);
//		    fOut.write("Written by FileOutputStream".getBytes());
//		    iFile.refreshLocal(IResource.DEPTH_ZERO, null);
//		}
		Assert.assertEquals(false, codeFragmentsCids.contains(1037));
		
//		IWorkbenchPage page = ...;
//		//the active part
//		IWorkbenchPart active = page.getActivePart();
//		//adding a listener
//		IPartListener2 pl = new IPartListener2() {
//		        public void partActivated(IWorkbenchPartReference ref)
//		            System.out.println("Active: "+ref.getTitle());
//		    }
////		    ... other listener methods ...
//		        };
//		page.addPartListener(pl);
		Assert.assertEquals(false, codeFragmentsCids.contains(1078));
	}
	
	@AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
	

	
}
