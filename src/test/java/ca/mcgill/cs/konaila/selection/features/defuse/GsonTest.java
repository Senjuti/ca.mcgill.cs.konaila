package ca.mcgill.cs.konaila.selection.features.defuse;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

public class GsonTest {
	
	static String targetCompact = "[{\"def\":{\"name\":\"a\",\"type\":\"int\",\"nodeType\":\"\",\"charStart\":1,\"charEnd\":3,\"parent\":\"int a=1;\"},\"uses\":[{\"name\":\"a\",\"type\":\"int\",\"nodeType\":\"\",\"charStart\":6,\"charEnd\":9,\"parent\":\"b=a+a;\"}]}]";
			
	@Test
	public void testToJson() {
		
		KonailaVariableUse use = new KonailaVariableUse("a","","int", "b=a+a;", 6,9);
		KonailaVariableDef def = new KonailaVariableDef("a", "", "int" , "int a=1;", 1,3);
		def.setUses(use);
		
		List<KonailaVariableDef> defs = new ArrayList<KonailaVariableDef>();
		defs.add(def);
		
		String json = KonailaVariableDef.toJson(defs);
		String jsonCompact = json.replace("\n", "").replace(" ", "");
		
		Assert.assertEquals(targetCompact.replace(" ", ""), jsonCompact);		
	}
	
	@Test 
	public void testFromJson() {
		List<KonailaVariableDef> defs = KonailaVariableDef.fromJson(targetCompact);
		
		KonailaVariableDef def = defs.get(0);
		
		Assert.assertEquals("a", def.getName());
		Assert.assertEquals("int", def.getType());
		Assert.assertEquals("int a=1;", def.getParent());
		
		Set<KonailaVariableUse> uses = def.getUses();
		Assert.assertEquals(1, uses.size());		
	}
	
	@Test
	public void testHttp() throws Exception {
		String code = "public class Foo {void foo(String[] args) {\nint a=1; a+=1;}}";
						
		List<KonailaVariableDef> defs = DefUseServer.analyzeDefUse(code, Strategy.eclipse);
		KonailaVariableDef def = null;
		for( KonailaVariableDef d : defs) {
			if( d.getName().equals("a") ) {
				def = d;
			}
		}
		String name = def.getName();
		String parent = def.getParent();
		
		Assert.assertEquals("a", name);
		Assert.assertEquals("a=1", parent);
	}
	
}
