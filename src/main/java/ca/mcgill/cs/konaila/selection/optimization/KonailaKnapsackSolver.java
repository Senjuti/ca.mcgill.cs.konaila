package ca.mcgill.cs.konaila.selection.optimization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class KonailaKnapsackSolver {
	
	public static  Set<Integer> selectOptimalUnitsGivenLines(int cid,
			Map<Integer,Integer> uidToLines, 
			Map<Integer, Float> uidToValues, int lines) {
		
		List<KSItem> items = new ArrayList<KSItem>();
		Set<Integer> uids = uidToLines.keySet();
		for( Integer uid : uids ) {
			Integer weight = uidToLines.get(uid);
			Float value = uidToValues.get(uid);
			int valueInteger = Math.round(value);
			
			items.add(new KonailaSelectionUnit(valueInteger, weight, uid));
		}
		
		KnapsackSolver.knapsack(items, lines);
		
		Set<Integer> result = new HashSet<Integer>();
		for( KSItem item : items  ) {
			KonailaSelectionUnit unit = (KonailaSelectionUnit)item;
			if( item.getTake() ) {
				result.add(unit.getUid());
			}
		}
		return result;
	} 
	
	private static class KonailaSelectionUnit extends KSItem { 
		int uid;
		KonailaSelectionUnit( int value, int length, int uid) {
			super(value,length);
			this.uid = uid;
		}
		
		int getUid() {return uid; }
	}
}



