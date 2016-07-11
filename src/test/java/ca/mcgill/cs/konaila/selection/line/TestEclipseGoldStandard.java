package ca.mcgill.cs.konaila.selection.line;

import java.sql.Connection;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbEclipseGoldStandard;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestEclipseGoldStandard {
	
	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void test1059() throws Exception {		
		int cid = 1059;
		int line = 2;
		Assert.assertEquals(1, DbEclipseGoldStandard.selectNumberOfMatchesWithLineBasedGoldStandard(c, cid, line));
	}
	
	@Test
	public void testNumberOfLines15() throws Exception {		
		Map<Integer,Integer> map = DbEclipseGoldStandard.selectEclipseCidsNumberOfLines(c);
		int maxLines = map.get(1015);
		Assert.assertEquals(14, maxLines);
	}
	
	@Test
	public void testNumberMaxLines72() throws Exception {		
		Map<Integer,Integer> map = DbEclipseGoldStandard.selectEclipseCidsNumberOfLines(c);
		int maxLines = map.get(1072);
		Assert.assertEquals(18, maxLines);
	}
	
	@Test
	public void test() throws Exception {		
		Map<Integer,Integer> map = DbEclipseGoldStandard.selectEclipseCidsNumberOfLines(c);
		
		Set<String> oneLineMultipleUnits = new HashSet<String>();
		oneLineMultipleUnits.add("1221,5"); // comment
		oneLineMultipleUnits.add("1101,1"); // comment
		oneLineMultipleUnits.add("1219,7"); // } finally {
		oneLineMultipleUnits.add("1228,6");// } else {	
		oneLineMultipleUnits.add("1228,10");// } else {		
		oneLineMultipleUnits.add("1228,23"); // multi-line call
		oneLineMultipleUnits.add("1228,24"); // comment 
		oneLineMultipleUnits.add("1228,27"); // } catch (
		oneLineMultipleUnits.add("1231,3"); //TODO		 if (vm == null) vm = JavaRuntime.getDefaultVMInstall();
		oneLineMultipleUnits.add("1226,7"); // } catch (
		oneLineMultipleUnits.add("1226,13"); // FIXME if (!status.isOK()) {
		oneLineMultipleUnits.add("1238,2"); // comment 		
		oneLineMultipleUnits.add("1238,9");		// } else {
		oneLineMultipleUnits.add("1235,14"); // TODO if (fragment == null) continue;
		oneLineMultipleUnits.add("1244,10"); // multie line call
		oneLineMultipleUnits.add("1246,10"); // } catch (
		oneLineMultipleUnits.add("1134,4");  //TODO try { Thread.sleep(1000); } catch (Exception e) { }
		oneLineMultipleUnits.add("1134,5");  // Display.getDefault().asyncExec(new Runnable() {
		oneLineMultipleUnits.add("1142,6"); //	} finally {
		oneLineMultipleUnits.add("1039,7"); // } else {
		oneLineMultipleUnits.add("1165,6"); // } finally {
		oneLineMultipleUnits.add("1049,4"); //	} else {
		oneLineMultipleUnits.add("1175,1"); //	comment
		oneLineMultipleUnits.add("1047,1"); //	comment
		oneLineMultipleUnits.add("1047,6"); //	comment
		oneLineMultipleUnits.add("1047,9"); //	} finally {
		oneLineMultipleUnits.add("1059,1"); // getContainer().run(true, true, new IRunnableWithProgress() {
		oneLineMultipleUnits.add("1062,7");//	} catch (
		oneLineMultipleUnits.add("1199,7"); // TODO if (!project.exists()) project.create(null);
		oneLineMultipleUnits.add("1199,8"); // TODO if (!project.isOpen()) project.open(null);
		oneLineMultipleUnits.add("1063,1"); //	comment
		oneLineMultipleUnits.add("1083,34"); // multi-line call
		oneLineMultipleUnits.add("1082,32"); // comment
		oneLineMultipleUnits.add("1082,42"); // block comment
		oneLineMultipleUnits.add("1209,1"); // comment
		oneLineMultipleUnits.add("1209,2"); // comment
		oneLineMultipleUnits.add("1209,3"); // comment
		
		
		int count = 0;
		for( Entry<Integer,Integer> e : map.entrySet()) {
			int cid = e.getKey();
			int maxLineNumber = e.getValue();
			for( int i = 1; i < maxLineNumber+1; i++ ) {
				int matches = DbEclipseGoldStandard.selectNumberOfMatchesWithLineBasedGoldStandard(c, cid, i); 
				System.out.println(count + ": " + cid + ", " + i + ", matches=" + matches);
				
				if( ! oneLineMultipleUnits.contains(cid + "," + i) ) {
					Assert.assertTrue(matches <= 1);
				}
			}
			count+=1;
		}
		int maxLines = map.get(1072);
		Assert.assertEquals(18, maxLines);
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
