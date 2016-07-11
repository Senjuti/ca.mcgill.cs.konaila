package ca.mcgill.cs.konaila.selection.optimization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class KonailaKnapsackSolverWithContext {
	
	public static  Set<Integer> selectOptimalUnitsGivenLines(int cid,
			Map<Integer,Integer> uidToLines, 
			Map<Integer, Float> uidToValues, 
			Map<Integer, Set<Integer>> kidToUids,
			int lines) {
		
		List<KSItem> items = new ArrayList<KSItem>();
		for( Entry<Integer,Set<Integer>> e : kidToUids.entrySet() ) {
			int kid = e.getKey();
			Set<Integer> uids = e.getValue();
			int weight = 0;
			int value = 0;
			for( Integer uid : uids ) {
				Integer w = uidToLines.get(uid);
				Float v = uidToValues.get(uid);
				 
				weight  += w==null ? 1 : w;				
				value += v==null? 1 : Math.round(v);				
			}
			int valueInteger = Math.round(value);
			items.add(new KonailaSelectionUnitWithContext(
					valueInteger, weight, uids));
		}
		KnapsackSolver.knapsack(items, lines);
		
		Set<Integer> result = new HashSet<Integer>();
		for( KSItem item : items  ) {
			KonailaSelectionUnitWithContext unit = (KonailaSelectionUnitWithContext)item;
			if( item.getTake() ) {
				result.addAll(unit.getUids());
			}
		}
		return result;
	} 
	
	private static class KonailaSelectionUnitWithContext extends KSItem { 
		Set<Integer> uids;
		KonailaSelectionUnitWithContext( int value, int length, Set<Integer> uids) {
			super(value,length);
			this.uids = uids;
		}
		
		Set<Integer> getUids() {return uids; }
	}
}



