package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidProperty;
import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestModifierProperty {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid5MultipleModifiers() throws Exception {
		int cid=5;
		String property = "ModifierPublic";		
		Assert.assertEquals(3, DbAndroidProperty.selectNumberOfProperties(c, cid, property));		
	}
	
	@Test
	public void testCid5ModifierInClassSignature() throws Exception {
		int cid=5;
		String antlrProperty = "ModifierPublic";
		
		String code = DbAndroidUnit.selectCodeFragment(c, cid);
		int charStartExpected = code.indexOf("public");
		int charEndExpected = charStartExpected + "public".length();
			
		
		int charStart = DbAndroidProperty.selectPropertyCharStart(c, cid, 1,1, antlrProperty);
		int charEnd = DbAndroidProperty.selectPropertyCharEnd(c, cid, 1,1, antlrProperty);
		Assert.assertEquals(charStartExpected, charStart);
		Assert.assertEquals(charEndExpected, charEnd);
		Assert.assertEquals(antlrProperty, DbAndroidProperty.selectPropertyTypeOfMatchedProperty(c, cid, charStart,charEnd));
		Assert.assertEquals("TypeSignature", DbAndroidProperty.selectNodeTypeOfMatchedProperty(c, cid, charStart,charEnd));
	}
	
	@Test
	public void testCid5ModifierInField() throws Exception {
		int cid=5;
		String property = "ModifierPublic";		
		
		int charStart = DbAndroidProperty.selectPropertyCharStart(c, cid, 3,3, property);
		int charEnd = DbAndroidProperty.selectPropertyCharEnd(c, cid, 3,3, property);
		Assert.assertEquals(property, DbAndroidProperty.selectPropertyTypeOfMatchedProperty(c, cid, charStart,charEnd));
		Assert.assertEquals("FieldDeclaration", DbAndroidProperty.selectNodeTypeOfMatchedProperty(c, cid, charStart,charEnd));
	}

	@Test
	public void testCid5ModifierInMethod() throws Exception {
		int cid=5;
		String property = "ModifierPublic";		
		
		int charStart = DbAndroidProperty.selectPropertyCharStart(c, cid, 6,6, property);
		int charEnd = DbAndroidProperty.selectPropertyCharEnd(c, cid, 6,6, property);
		Assert.assertEquals(property, DbAndroidProperty.selectPropertyTypeOfMatchedProperty(c, cid, charStart,charEnd));
		Assert.assertEquals("MethodSignature", DbAndroidProperty.selectNodeTypeOfMatchedProperty(c, cid, charStart,charEnd));
	}
	
	@Test
	public void testUnmatched() throws Exception {
		int startCid=1;
		int endCid=54;
		String property = "ModifierPublic";

		Collection<Integer> excepts = Arrays.asList(new Integer[]{38});
		
		for( int i=startCid; i< endCid; i++) {
			if( !excepts.contains(i)) {
				System.out.println("cid " + i);
//				SELECT F.uid, F.code, F.charStart, F.charEnd, F.lineStart, F.lineEnd, U.nodeType  FROM astFeatures as A, firstElements as F , selectionUnits as U WHERE F.uid=U.uid AND F.cid=U.cid
//						AND A.cid=F.cid AND F.charStart<=A.charStart AND F.charEnd>=A.charEnd AND A.cid=2 AND  A.property like 'ModifierPublic';

				Assert.assertEquals(
						DbAndroidProperty.selectNumberOfProperties(c, i, property),
						DbAndroidProperty.selectNumberOfPropertiesWithMatchedUids(c,i, property,"android-guide" ));
			}
		}
	}
	
	@Test
	public void testCid1083() throws Exception {
		int cid=1083;
		String property = "ModifierPublic";		
		
		int charStart = DbAndroidProperty.selectPropertyCharStart(c, cid, 3,3, property);
		int charEnd = DbAndroidProperty.selectPropertyCharEnd(c, cid, 3,3, property);
		Assert.assertEquals(property, DbAndroidProperty.selectPropertyTypeOfMatchedProperty(c, cid, charStart,charEnd));
		Assert.assertEquals("MethodSignature", DbAndroidProperty.selectNodeTypeOfMatchedProperty(c, cid, charStart,charEnd));

		charStart = DbAndroidProperty.selectPropertyCharStart(c, cid, 16,16, property);
		Assert.assertEquals(-1, charStart);
	}
	

	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
