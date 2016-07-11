package ca.mcgill.cs.konaila.chopper;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbAndroidUnit;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseEnclosingRelationships;
import ca.mcgill.cs.konaila.database.DatabaseEnclosingRelationships.EnclosingUnits;

public class TestEnclosingFeatures {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid8Enclosing() throws Exception {
		int cid = 8;

		int charStart = DbAndroidUnit.selectCharStart(c, cid, 1, 1,
				"TypeDeclarationClassContext");
		int charEnd = DbAndroidUnit.selectCharEnd(c, cid, 1, 1,
				"TypeDeclarationClassContext");
		int uid = DbAndroidUnit.selectUid(c, cid, 1, 1, "TypeDeclarationClassContext");
		int charStartSecond = DbAndroidUnit
				.selectCharStartOfSecondElement(c, cid, uid);
		int charEndSecond = DbAndroidUnit.selectCharEndOfSecondElement(c, cid, uid);

		List<Integer> uids = DatabaseEnclosingRelationships.selectUidsEnclosed(
				c, cid, charEnd, charStartSecond);
		Assert.assertEquals("MemberDeclarationConstructorContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(0)));
		Assert.assertEquals("StatementContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(1)));
		Assert.assertEquals("StatementContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(2)));
		Assert.assertEquals("StatementContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(3)));
		Assert.assertEquals("StatementContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(4)));
		Assert.assertEquals("StatementContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(5)));
	}

	@Test
	public void testCid8Relationship() throws Exception {
		int cid = 8;
		int uid = 1;
		List<EnclosingUnits> enclosingUnits = DatabaseEnclosingRelationships
				.selectCidUidsOfEclosingUnits(c);
		boolean found = false;
		for( EnclosingUnits unit : enclosingUnits) {
			if( unit.getCid()==cid && unit.getUid()==uid) {
				if( found ) {
					Assert.fail("more than one unit");
				} else {
					found = true;
				}
			}
		}				
		
		if( !found ) 
			Assert.fail("no unit found");
	}
	

	@Test
	public void testCid8EnclosingUnitUid() throws Exception {
		int cid = 8;
		
		int enclosingUid = DbAndroidUnit.selectEnclosingUid(c,cid,2);
		Assert.assertEquals(1, enclosingUid);
		
		enclosingUid = DbAndroidUnit.selectEnclosingUid(c,cid,1);
		Assert.assertEquals(-1, enclosingUid);
		
		enclosingUid = DbAndroidUnit.selectEnclosingUid(c,cid,3);
		Assert.assertEquals(2, enclosingUid);
	}
	
	@Test
	public void testCid42OuterClass() throws Exception {
		int cid = 42;

		int charStart = DbAndroidUnit.selectCharStart(c, cid, 1, 1,
				"TypeDeclarationClassContext");
		int charEnd = DbAndroidUnit.selectCharEnd(c, cid, 1, 1,
				"TypeDeclarationClassContext");
		int uid = DbAndroidUnit.selectUid(c, cid, 1, 1, "TypeDeclarationClassContext");
		int charStartSecond = DbAndroidUnit
				.selectCharStartOfSecondElement(c, cid, uid);
		int charEndSecond = DbAndroidUnit.selectCharEndOfSecondElement(c, cid, uid);

		List<Integer> uids = DatabaseEnclosingRelationships.selectUidsEnclosed(
				c, cid, charEnd, charStartSecond);
		Assert.assertEquals("JavaDocComment",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(0)));
		Assert.assertEquals("MemberDeclarationFieldContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(1)));
		Assert.assertEquals("JavaDocComment",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(2)));
		Assert.assertEquals("MemberDeclarationClassContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(3)));
	}

	@Test
	public void testCid42InnerClass() throws Exception {
		int cid = 42;

		int charStart = DbAndroidUnit.selectCharStart(c, cid, 8, 8,
				"MemberDeclarationClassContext");
		int charEnd = DbAndroidUnit.selectCharEnd(c, cid, 8, 8,
				"MemberDeclarationClassContext");
		int uid = DbAndroidUnit.selectUid(c, cid, 8, 8,
				"MemberDeclarationClassContext");
		int charStartSecond = DbAndroidUnit
				.selectCharStartOfSecondElement(c, cid, uid);
		int charEndSecond = DbAndroidUnit.selectCharEndOfSecondElement(c, cid, uid);

		List<Integer> uids = DatabaseEnclosingRelationships.selectUidsEnclosed(
				c, cid, charEnd, charStartSecond);
		Assert.assertEquals("MemberDeclarationMethodContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(0)));
		Assert.assertEquals("SwitchStatementContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(1)));
		Assert.assertEquals("SwitchLabelContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(2)));
		Assert.assertEquals("StatementContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(3)));
		Assert.assertEquals(7, uids.size());
	}

	@Test
	public void testTotal() throws Exception {
		List<Integer> uids = DatabaseEnclosingRelationships
				.selectCidsOfEclosingUnits(c, "android-guide");
//		Assert.assertEquals(183, uids.size());

		List<Integer> cidsWithNoEnclUnits = Arrays.asList(new Integer[] { 18, 32, 44 });
		Collection intersection = CollectionUtils.intersection(uids,
				cidsWithNoEnclUnits);
		Assert.assertEquals(0, intersection.size());
	}
	
	@Test
	public void testCid29CallOnFirstLineOfStatement() throws Exception {
		int cid = 29;
		int uid = DbAndroidUnit.selectUid(c, cid, 10, 10, "CallContext");
		
//		notification.setLatestEventInfo(getApplicationContext(), "MusicPlayerSample",
//		        "Playing: " + songName, pi);
		
		Assert.assertEquals("getApplicationContext()", 
				DbAndroidUnit.selectCodeFromUnit(c, cid, 10, 10, "CallContext"));
		Assert.assertEquals(DbAndroidUnit.selectEnclosingUid(c, cid, uid), 
				DbAndroidUnit.selectUid(c, cid, 10, 10, "StatementContext"));
	}
	
	@Test
	public void testCid1EnclosingMethodCalls() throws Exception {
		int cid = 1;

		int charStart = DbAndroidUnit.selectCharStart(c, cid, 37, 37,
				"StatementContext");
		int charEnd = DbAndroidUnit.selectCharEnd(c, cid, 37, 37,
				"StatementContext");
		int uid = DbAndroidUnit.selectUid(c, cid, 37, 37,
				"StatementContext");
		int charStartSecond = DbAndroidUnit
				.selectCharStartOfSecondElement(c, cid, uid);
		int charEndSecond = DbAndroidUnit.selectCharEndOfSecondElement(c, cid, uid);
		
		List<Integer> uids = DatabaseEnclosingRelationships.selectUidsEnclosed(
				c, cid, charEnd, charStartSecond);
		Assert.assertEquals("CallContext",
				DbAndroidUnit.selectAntlrNodeType(c, cid, uids.get(0)));		
	}

	@Test
	public void testCid11EnclosingMethodCalls() throws Exception {
		int cid = 11;
		
		int innerUid = DbAndroidUnit.selectUid(c, cid, 15, 15, "CallContext");		
		int outerUid = DbAndroidUnit.selectEnclosingUid(c,cid,innerUid);
		Assert.assertEquals(outerUid, DbAndroidUnit.selectUid(c, cid, 11, 14, "StatementContext"));
	}
	
	@Test
	public void testCid55() throws Exception {
		int cid = 55;
		int[] uids = new int[]{16,15,10,8,7,6};
		
		for( int i=0; i<uids.length; i++ ) {
			System.out.println(i);
			int enclosingUidTarget = i < uids.length-1 ? uids[i+1] : -1;
			int enclosingUid = DatabaseEnclosingRelationships.getEnclosingUid(c, cid, uids[i]);
			Assert.assertEquals(enclosingUidTarget, enclosingUid);
		}
	}

	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
