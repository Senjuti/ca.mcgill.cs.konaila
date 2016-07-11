package ca.mcgill.cs.konaila.selection.features;

import java.sql.Connection;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.selection.features.DatabaseSignatureSelection.MethodSignature;

public class TestClassInheritanceInFeatureTable {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}

	@Test
	public void testCid1077PublicMethod() throws Exception {
		int cid=1077;

		Map<Integer,MethodSignature> uidToMethods= DatabaseSignatureSelection.selectMethodSignaturesWithnEnclosingClass(c, cid);		
		Assert.assertEquals(1, uidToMethods.size());
		
		Entry<Integer,MethodSignature> e = uidToMethods.entrySet().iterator().next();
		MethodSignature method = e.getValue();
		Assert.assertEquals(false, method.isPublic());
		Assert.assertEquals(true, method.isProtected());
		Assert.assertEquals(false, method.isStatic());
		Assert.assertEquals(true, method.enclosingTypeHasExtends());
		Assert.assertEquals(false, method.enclosingTypeHasImplements());
	}

	
	@Test
	public void testCid1213PublicMethod() throws Exception {
		// code fragment too short
//		int cid=1213;
//
//		Map<Integer,MethodSignature> uidToMethods= DatabaseSignatureSelection.selectMethodSignaturesWithnEnclosingClass(c, cid);
//		Assert.assertEquals(1, uidToMethods.size());	
//		
//		Entry<Integer,MethodSignature> e = uidToMethods.entrySet().iterator().next();
//		MethodSignature method = e.getValue();
//		Assert.assertEquals(true, method.isPublic());
//		Assert.assertEquals(false, method.isProtected());
//		Assert.assertEquals(false, method.isStatic());
//		Assert.assertEquals(true, method.enclosingTypeHasExtends());
//		Assert.assertEquals(true, method.enclosingTypeHasImplements());
	}
	
	@Test
	public void testCid1102ThreePublicMethods() throws Exception {
		int cid=1102;

		Map<Integer,MethodSignature> uidToMethods= DatabaseSignatureSelection.selectMethodSignaturesWithnEnclosingClass(c, cid);
		Assert.assertEquals(3, uidToMethods.size());
		
		
		for( Entry<Integer,MethodSignature> e : uidToMethods.entrySet()) {
			MethodSignature method = e.getValue();
			Assert.assertEquals(true, method.isPublic());
			Assert.assertEquals(false, method.isProtected());
			Assert.assertEquals(false, method.isStatic());
			Assert.assertEquals(true, method.enclosingTypeHasExtends());
			Assert.assertEquals(true, method.enclosingTypeHasImplements());			
		}
	}
	
	@Test
	public void testCid1228() throws Exception {
		int cid=1228;

		Map<Integer,MethodSignature> uidToMethods= DatabaseSignatureSelection.selectMethodSignaturesWithnEnclosingClass(c, cid);
		Assert.assertEquals(4, uidToMethods.size());
				
		for( Entry<Integer,MethodSignature> e : uidToMethods.entrySet()) {
			MethodSignature method = e.getValue();
			
			if( method.getCodeDisplayWhole().contains("protected IProject[] build(")) {
				Assert.assertEquals(false, method.isPublic());
				Assert.assertEquals(true, method.isProtected());
				Assert.assertEquals(false, method.isStatic());
				Assert.assertEquals(true, method.enclosingTypeHasExtends());
				Assert.assertEquals(false, method.enclosingTypeHasImplements());		
			} else if( method.getCodeDisplayWhole().contains("private void incrementalBuild(IResourceDelta delta,")) {
				Assert.assertEquals(false, method.isPublic());
				Assert.assertEquals(false, method.isProtected());
				Assert.assertEquals(false, method.isStatic());
				Assert.assertEquals(true, method.enclosingTypeHasExtends());
				Assert.assertEquals(false, method.enclosingTypeHasImplements());				
			} else if( method.getCodeDisplayWhole().contains("public boolean visit(IResourceDelta delta) { ... }")) {
				Assert.assertEquals(true, method.isPublic());
				Assert.assertEquals(false, method.isProtected());
				Assert.assertEquals(false, method.isStatic());
				Assert.assertEquals(true, method.enclosingTypeIsAnonymousClass());
				Assert.assertEquals(false, method.enclosingTypeHasImplements());				
			} else if( method.getCodeDisplayWhole().contains("private void fullBuild(IProgressMonitor monitor) { ... }")) {
				Assert.assertEquals(false, method.isPublic());
				Assert.assertEquals(false, method.isProtected());
				Assert.assertEquals(false, method.isStatic());
				Assert.assertEquals(true, method.enclosingTypeHasExtends());
				Assert.assertEquals(false, method.enclosingTypeHasImplements());				
			}
		}		
	}
	
	@Test
	public void testCid1215StaticMethod() throws Exception {
//		int cid=1215;
//
//		Map<Integer,MethodSignature> uidToMethods= DatabaseSignatureSelection.selectMethodSignatures(c, cid);
//		Assert.assertEquals(1, uidToMethods.size());	
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
