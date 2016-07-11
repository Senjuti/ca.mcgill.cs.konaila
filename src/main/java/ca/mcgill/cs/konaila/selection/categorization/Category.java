package ca.mcgill.cs.konaila.selection.categorization;

import java.util.HashMap;
import java.util.Map;

public enum Category {
	
//	SingleApiCallBack,
	Structure,
	ClassDefinedAndInstantiated,
	ControlFlowCentric,
	ApiCalls,
	NoCategories;
		
	public static Map<Category, CategoryCertainty> getMap() {
		Map<Category,CategoryCertainty> categories = new HashMap<Category,CategoryCertainty>();	
		for( Category c : Category.values() ) {
			categories.put(c, CategoryCertainty.No);
		}
		return categories;
	}
}