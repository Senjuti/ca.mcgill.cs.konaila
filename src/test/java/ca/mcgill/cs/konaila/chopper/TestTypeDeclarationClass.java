package ca.mcgill.cs.konaila.chopper;

import java.io.File;
import java.sql.Connection;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestTypeDeclarationClass {

	static Connection c;
	
	@BeforeClass
	public static void setUp() throws Exception{
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	@Test
	public void testCid8() throws Exception {
		int cid = 8;

		String startUnit = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 1);
		Assert.assertEquals("public class NumberPickerPreference extends DialogPreference {", startUnit);
		
		String endUnit = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 12, 12);
		Assert.assertEquals("}", endUnit);		
	}
	
	@Test
	public void testCid8TestUid() throws Exception {
		int cid = 8;

		int uid = DbAndroidUnit.selectUid(c, cid, 1, 1, "TypeDeclarationClassContext");
		Assert.assertEquals(1, uid);
				
		String code = DbAndroidUnit.selectCodeOfSecondElement(c, cid, uid);
		Assert.assertEquals("}", code);		
	}

	@Test
	public void testCid8TestCharStartEnd() throws Exception {
		int cid = 8;

		int charStart = DbAndroidUnit.selectCharStart(c, cid, 1, 1, "TypeDeclarationClassContext");
		int charEnd = DbAndroidUnit.selectCharEnd(c, cid, 1, 1, "TypeDeclarationClassContext"); 
		int lineCharStart = DbAndroidUnit.selectLineCharStart(c, cid, 1, 1);
		int lineCharEnd = DbAndroidUnit.selectLineCharEnd(c, cid, 1, 1);
		
		Assert.assertEquals(0, charStart);
		Assert.assertEquals("public class NumberPickerPreference extends DialogPreference {".length(), charEnd);
		Assert.assertEquals(charStart, lineCharStart);
		Assert.assertEquals(charEnd, lineCharEnd);
			
		int uid = DbAndroidUnit.selectUid(c, cid, 1, 1, "TypeDeclarationClassContext");
		int charStartSecond = DbAndroidUnit.selectCharStartOfSecondElement(c, cid, uid);
		int charEndSecond = DbAndroidUnit.selectCharEndOfSecondElement(c, cid, uid); 	
		
		String content = FileUtils.readFileToString(new File("./database/android-guide/compilationUnit/ellipses/J08.java"));
		content = content.trim();
		int contentLength = content.length();
		
		Assert.assertEquals(contentLength, charEndSecond);
		Assert.assertEquals(contentLength  - "}".length(), charStartSecond);
		Assert.assertEquals(399, charStartSecond);
		
		System.out.println();
	}
	
	@Test
	public void testCid5TwoLinedSignature() throws Exception {
		int cid = 5;

		String startUnit = DbAndroidUnit.selectCodeFromUnit(c, cid, 1, 2);
		Assert.assertTrue(startUnit.startsWith("public class SettingsActivity"));
		
		String endUnit = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 13, 13);
		Assert.assertEquals("}", endUnit);		
	}
	
	@Test
	public void testCid42InnerClass() throws Exception {
		int cid = 42;

		String startUnit = DbAndroidUnit.selectCodeFromUnit(c, cid, 8, 8);
		Assert.assertTrue(startUnit.startsWith("class IncomingHandler"));
		
		String endUnit = DbAndroidUnit.selectCodeFromSecondElement(c, cid, 19, 19);
		Assert.assertEquals("}", endUnit);		
	}

	 @AfterClass
	    public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
