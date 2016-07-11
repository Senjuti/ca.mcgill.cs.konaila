package ca.mcgill.cs.konaila.selection.optimization;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;



public class Test1Knapsack {

	@Test
	public void testOriginal() {
		
//		item	profit	weight	take
//		1	944	1971	false
//		2	480	306	true
//		3	133	1425	false
//		4	195	243	true
//		5	209	1440	false
//		6	609	851	true
		
		int N = 6;
		int W = 2000;
		boolean[] targets = new boolean[] {false, false, true, false, true, false, true};
		
        int[] profit = new int[] { -1 /*dummy*/, 
        		944, 480, 133, 195, 209, 609
        };
        int[] weight = new int[] { -1 /*dummy*/,
        		1971, 306, 1425, 243, 1440, 851
        };
       
        boolean[] take = KnapsackSolverOriginal.knapsack(N, W, profit, weight);
        for( int i = 0; i < targets.length; i++ ) {
        	Assert.assertEquals(targets[i], take[i]);
        }
	}
	
	@Test
	public void testNew() {
		
//		item	profit	weight	take
//		1	944	1971	false
//		2	480	306	true
//		3	133	1425	false
//		4	195	243	true
//		5	209	1440	false
//		6	609	851	true
		
		int W = 2000;
		
		List<KSItem> items = new ArrayList<KSItem>();
		items.add( new KSItem(944, 1971) );
		items.add( new KSItem(480, 306) );
		items.add( new KSItem(133, 1425) );
		items.add( new KSItem(195, 243) );
		items.add( new KSItem(209, 1440) );
		items.add( new KSItem(609, 851) );
		
		boolean[] target = new boolean[] {false, true, false, true, false, true}; 
       
        KnapsackSolver.knapsack(items, W);
        int i = 0;
        for( KSItem item : items ) {
        	Assert.assertEquals(target[i], item.getTake());
        	i+=1;
        }
	}
}
