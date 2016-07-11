package ca.mcgill.cs.konaila.selection.optimization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;

public class PopulateKnapsackItemsForContext {

	public static void main(String[] args) throws Exception {

		Database.getInstance().initConnection(Main.DB);		
		Connection conn = Database.getInstance().getConnection();
		
		populateItems(conn);
		
		Database.getInstance().closeConnection();
	}
	
	public static void populateItems(Connection conn) throws SQLException, IOException {
		populateContext(conn);	
		populateKnapsackItems(conn);
	}
	
	
	private static void populateContext(Connection conn) throws SQLException, IOException {
		DatabaseKnapsackContext.populateContextForStructuralRelationships(conn);
		DatabaseKnapsackContext.populateContextForSuperThisReturnThrow(conn);	
		DatabaseKnapsackContext.populateContextForDataFlow(conn);	
	}
	
	private static void populateKnapsackItems(Connection conn) throws SQLException, IOException {
		
		List<Integer> cids = DatabasePredictions.getPredictionCids(conn);

		for( Integer cid : cids ) {

			Map<Integer, Set<Integer>> tidsToFids = DatabaseKnapsackContext.
				getTidsToFids(conn, cid, 
						Arrays.asList(new String[] {
								"Structure", 
								"This", "Super", "Throw",
								"DataFlow"}));
			
			int kidCurrent = 1;
			for( Entry<Integer,Set<Integer>> e : tidsToFids.entrySet()) {
				int tid = e.getKey();
				Set<Integer> fids = e.getValue();
				Set<Set<Integer>> fidSets = 
						PowerSet.generatePowerSet(fids);
				fidSets.remove(Collections.EMPTY_SET);
				kidCurrent = DatabaseKnapsackContext.populatePowersets(
						conn, cid, tid, 
						fidSets, kidCurrent);
			}
			
			DatabaseKnapsackContext.populateRestOfKnapsackItems(conn, 
					cid, tidsToFids);
		}
	}
	
	
}
