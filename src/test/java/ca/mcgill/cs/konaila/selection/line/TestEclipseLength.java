package ca.mcgill.cs.konaila.selection.line;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbEclipseGoldStandard;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestEclipseLength {
	
	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void test1015() throws Exception {		
		int cid = 1015;
		Integer max = 11;
//		Assert.assertEquals(max, DbEclipseGoldStandard.selectEclipseCidsNumberOfLines(c).get(cid));
	}

	@Test
	public void testAll() throws Exception {		
		Map<Integer,Integer> map = DbEclipseGoldStandard.selectEclipseCidsNumberOfLines(c);
		String file = "./database/eclipse-faq/E@.java";
		
		Set<Integer> excepts = new HashSet<Integer>();
		excepts.add(15);
		excepts.add(102);
		excepts.add(103);
		excepts.add(179);
		excepts.add(213);
		
		for( Entry<Integer,Integer> e : map.entrySet()) {
			int cid = e.getKey()-1000;
			int max = e.getValue();
			
			System.out.print(cid);
			
			if( !excepts.contains(cid) ) {
				try { 
					String[] lines = FileUtils.readFileToString(new File(file.replace("@", Integer.toString(cid)))).trim().split("\n");
					Assert.assertEquals(lines.length, max);
					System.out.println("...good");
				} catch( FileNotFoundException ex ) {
					System.out.println("...not found");
					continue;
				}
			} else {
				System.out.println("...bad");
			}
		}
		
	}


	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
