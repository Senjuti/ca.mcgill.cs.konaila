package ca.mcgill.cs.konaila.presentation;

import java.sql.Connection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbEclipseUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.MainSummariesFromPredictionTable;
import ca.mcgill.cs.konaila.database.Database;

public class TestTab {
	
	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}



	@Test
	public void testCid1235() throws Exception {		
		int cid = 1235;
		
		// this summary should have no tabs
//		IJavaElement fes[] = fragment.getChildren();
//		IWorkspace workspace = ResourcesPlugin.getWorkspace();
//		String className = fes[j].getElementName();
//		IJavaModel javaModel = JavaCore.create(workspace.getRoot());
//		IJavaProject projects[] = jm.getJavaProjects();
//		String projectName = projects[n].getElementName();
//		IPackageFragmentRoot[] roots = project.getAllPackageFragmentRoots();
//		IJavaElement[] elements = root.getChildren();
//		PackageFragment fragment = (...) element.getAdapter(PackageFragment.class);
		
		String summary = MainSummariesFromPredictionTable.generateSummary(c, cid, (float)0.2);
		String[] lines = summary.split("\n");
		for( String line : lines ) {
			Assert.assertFalse(line.startsWith(" ")); 
		}
	}
	
	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
