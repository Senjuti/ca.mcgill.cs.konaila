package ca.mcgill.cs.konaila.selection.optimization;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;



public class Test2Knapsack {

	@Test
	public void testOriginal() {
		
//		item	profit	weight	take
//		1	123	2499	false
//		2	3	955	false
//		3	889	1746	true
//		4	595	807	true
//		5	641	3497	false
//		6	893	1227	true
		
		int N = 7;
		int W = 4000;
		boolean[] targets = new boolean[] {false, /*dummy*/ 
				false, false, true, true, false, true, true};
		
        int[] profit = new int[] { -1 /*dummy*/, 
        		123,3,889,595,641,893,1000
        };
        int[] weight = new int[] { -1 /*dummy*/,
        		2499,955,1746,807,3497,1227,10
        };
       
        boolean[] take = KnapsackSolverOriginal.knapsack(N, W, profit, weight);
        for( int i = 0; i < targets.length; i++ ) {
        	Assert.assertEquals(targets[i], take[i]);
        }
	}
	
	@Test
	public void testNew() {
		
//		item	profit	weight	take
//		1	123	2499	false
//		2	3	955	false
//		3	889	1746	true
//		4	595	807	true
//		5	641	3497	false
//		6	893	1227	true
		
		int W = 4000;
		
		List<KSItem> items = new ArrayList<KSItem>();
		items.add( new KSItem(123, 2499) );
		items.add( new KSItem(3, 955) );
		items.add( new KSItem(899, 1746) );
		items.add( new KSItem(595, 807) );
		items.add( new KSItem(641, 3497) );
		items.add( new KSItem(893, 1227) );
		items.add( new KSItem(1000, 10) );
		
		boolean[] target = new boolean[] {false, false, true, true, false, true, true}; 
       
        KnapsackSolver.knapsack(items, W);
        int i = 0;
        for( KSItem item : items ) {
        	Assert.assertEquals(target[i], item.getTake());
        	i+=1;
        }
	}
}
