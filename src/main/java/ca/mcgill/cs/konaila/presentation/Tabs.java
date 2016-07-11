package ca.mcgill.cs.konaila.presentation;

import java.util.HashMap;
import java.util.Map;

public class Tabs {
	
	static Map<Integer,String> tabs = new HashMap<Integer,String>();
	static final String tab = "  ";
	
	static { 
		String t = "";
		tabs.put(0, t); t+=tab;
		tabs.put(1, t); t+=tab;
		tabs.put(2, t); t+=tab;
		tabs.put(3, t); t+=tab;
		tabs.put(4, t); t+=tab;
		tabs.put(5, t); t+=tab;
	}
	
	public static String getTab(int targetNumberOfTabs) {
		String result = null;
		if( targetNumberOfTabs <= 5 ) {
			result = tabs.get(targetNumberOfTabs);
		} else {
			String t = tabs.get(5);
			for( int counter=5; counter < targetNumberOfTabs; counter++ ) {
				t+=tab;
			}
			result = t;
		}
		return result;
	}
}
