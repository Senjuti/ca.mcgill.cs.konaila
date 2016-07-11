package ca.mcgill.cs.konaila.summarize;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum HowToSummarize {
	Knapsack,
	Greedy,
	Baseline;
	
	public static List<HowToSummarize> chooseOrder() {
		List<HowToSummarize> list = Arrays.asList(HowToSummarize.values());
		Collections.shuffle(list);
		return list;
	}
	
}
