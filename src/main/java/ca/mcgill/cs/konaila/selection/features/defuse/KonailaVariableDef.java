package ca.mcgill.cs.konaila.selection.features.defuse;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class KonailaVariableDef {
    private KonailaVariableUse def;
    private Set<KonailaVariableUse> uses = new HashSet<KonailaVariableUse>();
    
    public KonailaVariableDef( String name, String nodeType, String type, String parent,
    		int charStart, int charEnd ) {
        this.def=new KonailaVariableUse(name,nodeType,type,parent,charStart,charEnd);
    }
    
	public static String toJson(Collection<KonailaVariableDef> defs) {
		Gson gson = new GsonBuilder()
			.setPrettyPrinting()
			.disableHtmlEscaping()
			.create(); 
		String json = gson.toJson(defs);
		
		return json;
	}
	
	public static List<KonailaVariableDef> fromJson(String json) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		
		Type listType = new TypeToken<ArrayList<KonailaVariableDef>>() {}.getType();
		try {
			List<KonailaVariableDef> defs = gson.fromJson(json, listType);	    
			return defs;
		} catch( JsonSyntaxException e) {
			e.printStackTrace();
		}
		return Collections.EMPTY_LIST;
	}
    
	public static KonailaVariableDef getDef(Collection<KonailaVariableDef> defs, String name,
			int charStart, int charEnd) {
		for( KonailaVariableDef def : defs) {
			if( def.getName().equals(name) && 
					def.getCharStart() == charStart &&
					def.getCharEnd() == charEnd ) {
				return def;
			}
		}
		return null;
	}
	
    public String getName() {
        return def.getName();
    }
    
    public Set<KonailaVariableUse> getUses() {
        return uses;
    }    
    
    public void setDef(KonailaVariableUse def) {
    	this.def = def;
    }
    
    public void setUses(KonailaVariableUse use) {
        if( !use.equals(def)) {
            this.uses.add(use);
        }
    }

    public String getType()  {
        return def.getType();
    }

    public String getNodeType() {
        return def.getNodeType();
    }
    
    public int getCharStart() {
    	return def.getCharStart();
    }
    
    public int getCharEnd() {
    	return def.getCharEnd();
    }
    
    public String getParent() {
    	return def.getParent();
    }
    
    @Override
    public String toString() {
    	return "Def: " + def + "\n" + "Uses: " + uses;
    }
    
    @Override
    public int hashCode() {
    	String hash = def.getName() + "/" + getCharStart() + "/" + getCharEnd();
    	return hash.hashCode();
    }
}