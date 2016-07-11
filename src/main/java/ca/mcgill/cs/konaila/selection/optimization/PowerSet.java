package ca.mcgill.cs.konaila.selection.optimization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PowerSet {

	public static void main(String[] args) {
		Set<Integer> originalSet = new HashSet<Integer>();
		originalSet.add(1);
		originalSet.add(2);
		originalSet.add(3);
		Set<Set<Integer>> result = generatePowerSet(originalSet);
		result.remove(Collections.EMPTY_SET);
		System.out.println(result);
	}

	public static Set<Set<Integer>> generatePowerSet(Set<Integer> originalSet) {
		List<Integer> list = new ArrayList<Integer>(originalSet);
		int n = list.size();

		Set<Set<Integer>> powerSet = new HashSet<Set<Integer>>();

		for( long i = 0; i < (1 << n); i++) {
		    Set<Integer> element = new HashSet<Integer>();
		    for( int j = 0; j < n; j++ )
		        if( (i >> j) % 2 == 1 ) element.add(list.get(j));
		    powerSet.add(element); 
		}

		return powerSet;
	}
}
