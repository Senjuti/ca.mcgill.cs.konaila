package ca.mcgill.cs.konaila.selection.optimization;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/*************************************************************************
 *  Compilation:  javac Knapsack.java
 *  Execution:    java Knapsack N W
 *
 *  Generates an instance of the 0/1 knapsack problem with N items
 *  and maximum weight W and solves it in time and space proportional
 *  to N * W using dynamic programming.
 *
 *  For testing, the inputs are generated at random with weights between 0
 *  and W, and profits between 0 and 1000.
 *
 *  %  java Knapsack 6 2000 
 *  item    profit  weight  take
 *  1       874     580     true
 *  2       620     1616    false
 *  3       345     1906    false
 *  4       369     1942    false
 *  5       360     50      true
 *  6       470     294     true
 *
 *************************************************************************/

public class KnapsackSolver {
	
    public static void main(String[] args) {
        int N = Integer.parseInt(args[0]);   // number of items
        int W = Integer.parseInt(args[1]);   // maximum weight of knapsack
        
        List<KSItem> items = generate(N, W);
        knapsack(items, W);        
    } 

    public static List<KSItem> generate(int N, int W) {
        List<KSItem> items = new ArrayList<KSItem>(N);

        // generate random instance, items 1..N
        for (int n = 1; n <= N; n++) {
            int profit  = (int) (Math.random() * 1000);
            int weight = (int) (Math.random() * W);
            
            items.add(new KSItem(profit, weight));
        }
        return items;
    }
    
    public static void knapsack( List<KSItem> items, int W) {

    	int N = items.size();

        // opt[n][w] = max profit of packing items 1..n with weight limit w
        // sol[n][w] = does opt solution to pack items 1..n with weight limit w include item n?
        int[][] opt = new int[N+1][W+1];
        boolean[][] sol = new boolean[N+1][W+1];

        Iterator<KSItem> itemIterator = items.iterator();
        for (int n = 1; n <= N; n++) {
        	
        	KSItem item = itemIterator.next();
        	int weight = item.getWeight();
        	int profit = item.getProfit();
        	
            for (int w = 1; w <= W; w++) {

                // don't take item n
                int option1 = opt[n-1][w];

                // take item n
                int option2 = Integer.MIN_VALUE;
                if (weight <= w) option2 = profit + opt[n-1][w-weight];

                // select better of two options
                opt[n][w] = Math.max(option1, option2);
                sol[n][w] = (option2 > option1);
            }
        }

        // determine which items to take
        ListIterator<KSItem> itemsRev = items.listIterator(items.size());
        boolean[] take = new boolean[N+1]; take[0] = false;
        for (int n = N, w = W; n > 0 && itemsRev.hasPrevious(); n--) {
        	KSItem item = itemsRev.previous();
        	
            if (sol[n][w]) { 
            	item.setTake(true); 
            	w = w - item.getWeight();
            } else { 
            	item.setTake(false);
            }
        }

        // print results
        System.out.println("profit" + "\t" + "weight" + "\t" + "take");
        for ( KSItem item : items ) {
            System.out.println(item.getProfit() + "\t" + item.getWeight()
            		+ "\t" + item.getTake());
        }
    }
}

class KSItem {
	private int profit;
	private int weight;
	private boolean take;
	
	public KSItem( int profit, int weight) {
		this.profit = profit;
		this.weight = weight;
	}
	
	public int getProfit() {
		return profit;
	}
	public int getWeight() {
		return weight;
	}
	public void setTake(boolean take) {
		this.take = take;
	}
	public boolean getTake() {
		return take;
	}
	
	
}
