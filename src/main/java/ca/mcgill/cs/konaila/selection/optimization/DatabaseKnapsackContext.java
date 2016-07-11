package ca.mcgill.cs.konaila.selection.optimization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DatabaseKnapsackContext {
	
	public static void populateContextForDataFlow(Connection conn)
			throws SQLException, IOException {
		
		PreparedStatement dataFlow = conn.prepareStatement(
				" INSERT INTO context(cid,fromUid,toUid,type) "
				+ " SELECT D.cid, U.uid, D.uid, 'DataFlow'"
				+ " FROM dataFlowVariables D, dataFlowVariables U "
				+ " WHERE D.cid=U.cid AND D.variableId=U.variableId "
				+ " AND D.defOrUse LIKE 'def' AND U.defOrUse LIKE 'use'"
				+ " AND D.type IS NOT NULL AND U.type IS NOT NULL" /* type IS NULL when variables defined in for loop */
				);		
		dataFlow.executeUpdate();
		dataFlow.close();
		
		PreparedStatement cleanup = conn.prepareStatement(
				"DELETE from Context "
				+ " WHERE (fromUid=toUid)");
		cleanup.executeUpdate();
		cleanup.close();
	}

	public static void populateContextForStructuralRelationships(Connection conn)
			throws SQLException, IOException {
		PreparedStatement enclosingRelationships = conn.prepareStatement(
				" INSERT INTO context(cid,fromUid,toUid,type) "
				+ " SELECT S.cid, S.uid, S.enclosingUnitUid, 'Structure' "
				+ " FROM selectionUnits S, predictionComponents P "
				+ " WHERE S.cid=P.cid AND S.uid=P.uid "
				+ " AND P.component LIKE 'Structure' "
				+ " AND S.enclosingUnitUid!=-1");		
		enclosingRelationships.executeUpdate();		
		enclosingRelationships.close();
	}

	public static void populateContextForSuperThisReturnThrow(Connection conn)
			throws SQLException, IOException {
//		String insert = " INSERT INTO context(cid,fromUid,toUid,type) "
//				+ " SELECT S.cid, S.uid, S.enclosingUnitUid, 'SuperThisReturnThrow' "
//				+ " FROM selectionUnits S, predictionComponents P "
//				+ " WHERE S.cid=P.cid AND S.uid=P.uid "
//				+ " AND (codeDisplayWhole LIKE 'super%' "
//				+ " OR codeDisplayWhole LIKE 'this%' "
//				+ " OR codeDisplayWhole LIKE 'return%' "
//				+ " OR codeDisplayWhole LIKE 'throw%')"
//				+ " AND S.enclosingUnitUid!=-1";
		String insert = " INSERT INTO context(cid,fromUid,toUid,type) "
		+ " SELECT DISTINCT S.cid, S.uid, S.enclosingUnitUid, ? "
		+ " FROM selectionUnits S, predictionComponents P "
		+ " WHERE S.cid=P.cid AND S.uid=P.uid "
		+ " AND codeDisplayWhole LIKE '***' "
		+ " AND S.enclosingUnitUid!=-1";
		PreparedStatement superThisReturn = conn.prepareStatement(insert.replace("***", "this%"));
		superThisReturn.setString(1, "This");	
		superThisReturn.executeUpdate();		
		superThisReturn = conn.prepareStatement(insert.replace("***", "return%"));
		superThisReturn.setString(1, "Return");		
		superThisReturn.executeUpdate();		
		superThisReturn = conn.prepareStatement(insert.replace("***", "super%"));
		superThisReturn.setString(1, "Super");	
		superThisReturn.executeUpdate();
		superThisReturn = conn.prepareStatement(insert.replace("***", "throw%"));
		superThisReturn.setString(1, "Throw");	
		superThisReturn.executeUpdate();		
		
		superThisReturn.close();
		
		populateContextForSuperThisReturnThrowWhenImmediateEnclosingNotMethod(conn);	

	}
	
	private static void populateContextForSuperThisReturnThrowWhenImmediateEnclosingNotMethod(Connection conn)
			throws SQLException, IOException {	
	    PreparedStatement u = conn.prepareStatement(
						"UPDATE context "
						+ " SET toUid=? "
						+ " WHERE cid=? AND fromUid=? "
						+ " AND (type LIKE 'Super'"
						+ "      OR type LIKE 'This'"
						+ "      OR type LIKE 'Return'"
						+ "      OR type LIKE 'Throw') ");
	    PreparedStatement s = conn.prepareStatement(
	    		"SELECT Second.cid, Second.uid, C.fromUid, FirstLevel.konailaNodeType, Second.konailaNodeType "
	    		+ " FROM context C, selectionUnits FirstLevel, selectionUnits Second "
	    		+ " WHERE (type LIKE 'Super'"
						+ "      OR type LIKE 'This'"
						+ "      OR type LIKE 'Return'"
						+ "      OR type LIKE 'Throw')"
	    		+ " AND C.cid=FirstLevel.cid AND C.toUid=FirstLevel.uid "
	    		+ " AND (FirstLevel.konailaNodeType NOT LIKE 'MethodSignature' AND FirstLevel.konailaNodeType NOT LIKE 'ConstructorSignature') "
	    		+ " AND Second.cid=FirstLevel.cid AND Second.uid=FirstLevel.enclosingUnitUid ");
	    
	    PreparedStatement d = conn.prepareStatement(
	    		"DELETE from Context "
				+ " WHERE cid=? AND fromUid=? "
				+ " AND (type LIKE 'Super'"
						+ "      OR type LIKE 'This'"
						+ "      OR type LIKE 'Return'"
						+ "      OR type LIKE 'Throw')");

	    ResultSet r = null;
	    while( true ) {
		    r = s.executeQuery();
		    boolean next = r.next();
		    if( next) {
		    	while( next ) {
			    	int cid = r.getInt(1);
			    	int newToUid = r.getInt(2);
			    	int fromUid = r.getInt(3);
			    	if( newToUid == -1) {
			    		d.setInt(1, cid);
			    		d.setInt(2, fromUid);
			    		d.executeUpdate();
			    	} else {
				    	u.setInt(1, newToUid);
				    	u.setInt(2, cid);
				    	u.setInt(3,fromUid);
				    	u.executeUpdate();
				    	next = r.next();
			    	}
			    }
		    } else {
		    	break;
		    }
	    }

	    PreparedStatement d2 = conn.prepareStatement(
	    		"DELETE from Context "
				+ " WHERE cid=? AND toUid=? ");
	    	    
	    d2.close();
	    d.close();
	    r.close();
	    u.close();
	    s.close();
	}
	
	public static Map<Integer,Set<Integer>> getTidsToFids(Connection conn, 
			int cid, Collection<String> types)
			throws SQLException, IOException {
		Map<Integer,Set<Integer>> tidsToFids = new HashMap<Integer,Set<Integer>>();
		PreparedStatement s = conn.prepareStatement(
				" SELECT DISTINCT fromUid, toUid "
				+ " FROM context C "
				+ " WHERE C.cid=? " + getTypeSqlString(types));
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while(r.next()) {
			int fid = r.getInt(1);
			int tid = r.getInt(2);
			
			Set<Integer> fids = tidsToFids.get(tid);
			if( fids == null ) {
				fids = new HashSet<Integer>();
				tidsToFids.put(tid,fids);
			}
			fids.add(fid);
		}		
		
		r.close();
		return tidsToFids;
	}
	
	private static String getTypeSqlString(Collection<String> types) {
		if( types.size() == 0 ) {
			return "";
		} else if ( types.size() == 1) {
			return "AND type LIKE '" + types.iterator().next() + "'"; 
		} else {
			String str = " AND (";
			for( String type : types) {
				str += "type LIKE '" + type + "' OR "; 
			}
			str = str.substring(0, str.length() - " OR ".length());
			str += ")";		
			return str;
		}
	}
	
	
	public static void populateRestOfKnapsackItems(Connection conn,
			int cid, Map<Integer,Set<Integer>> unitsNeedContext) 
					throws SQLException, IOException {
		Set<Integer> unitsAndUnitsNeedContext = new HashSet<Integer>();
		unitsAndUnitsNeedContext.addAll(unitsNeedContext.keySet());
		for( Set<Integer> set : unitsNeedContext.values()) {
			unitsAndUnitsNeedContext.addAll(set);
		}
		
		String sql = "SELECT uid "
				+ " FROM predictions "
				+ " WHERE cid=? "
				+ " AND featureSet LIKE '%DataFlow%' "
				+ " ****";
		sql = sql.replace("****", 
				unitsAndUnitsNeedContext.size() == 0 ? 
						"" : 
						" AND " + generateNotCondition(unitsAndUnitsNeedContext));

		PreparedStatement s = conn.prepareStatement(sql);
		s.setInt(1, cid);
		
		
		conn.setAutoCommit(false);
		PreparedStatement insert = conn.prepareStatement(
				"INSERT INTO knapsackItems VALUES (?,?,?)");


		System.out.println("inserting knapsack cid " + cid);
		
		int kid = getMaxKid(conn, cid);
		ResultSet r = s.executeQuery();
		while(r.next()) {
			
			kid +=1;
			int uid = r.getInt(1);

			
			insert.setInt(1, cid);
			insert.setInt(2, kid);
			insert.setInt(3, uid);
			insert.executeUpdate();			
		}
		
		conn.commit();
		s.close();
		insert.close();		
	}
	
	private static String generateNotCondition(Set<Integer> uids) {
		String condition = "";
		for( Integer uid : uids ) {
			condition += " AND " + "uid!=" + uid ;
		}
		condition = condition.substring(" AND ".length());
		condition = "(" + condition + ")";
		return condition;
	}
	
	private static int getMaxKid(Connection conn, int cid) 
		throws SQLException, IOException {
		int maxKid = -1;
		PreparedStatement s = conn.prepareStatement("SELECT max(kid) FROM knapsackItems "
				+ " WHERE cid=?");
		s.setInt(1,cid);
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			maxKid = r.getInt(1);
		}
		s.close();
		return maxKid;
	}
	
	public static int populatePowersets(Connection conn, int cid, 
			int tid, Set<Set<Integer>> fidSets, int kidInitial) 
	throws IOException, SQLException  {
		
		conn.setAutoCommit(false);
		
		int kidCurrent = kidInitial;
		PreparedStatement s = conn.prepareStatement(
				" INSERT INTO knapsackItems VALUES (?,?,?) ");
		for( Set<Integer> set : fidSets ) {
			s.setInt(1, cid);
			s.setInt(2, kidCurrent);
			s.setInt(3, tid);
			s.executeUpdate();

			for( Integer fid : set) {
				s.setInt(1, cid);
				s.setInt(2, kidCurrent);
				s.setInt(3, fid);
				s.executeUpdate();
			}
			kidCurrent += 1;
		}	
		conn.commit();
		s.close();
		return kidCurrent;
	}
	
	public static Map<Integer,Set<Integer>> getKidToUids(Connection conn, int cid)
			throws SQLException, IOException {
		Map<Integer,Set<Integer>> kidToUids = new HashMap<Integer,Set<Integer>>();
		PreparedStatement s = conn.prepareStatement(
				" SELECT DISTINCT kid, uid "
				+ " FROM knapsackItems "
				+ " WHERE cid=?");
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while(r.next()) {
			int kid = r.getInt(1);
			int uid = r.getInt(2);
			
			Set<Integer> uids = kidToUids.get(kid);
			if( uids == null ) {
				uids = new HashSet<Integer>();
				kidToUids.put(kid,uids);
			}
			uids.add(uid);
		}		
		
		r.close();
		return kidToUids;
	}
	
	public static int getEnclosingMethodsForReturns(Connection conn, int cid,
			int returnSelectionUnitId)
			throws SQLException, IOException {
		int enclosingMethodId = -1;
		PreparedStatement s = conn.prepareStatement(
				" SELECT toUid "
				+ " FROM context "
				+ " WHERE cid=? AND fromUid=? "
				+ " AND type LIKE 'Return'");
		s.setInt(1, cid);
		s.setInt(2, returnSelectionUnitId);
		
		ResultSet r = s.executeQuery();
		if(r.next()) {
			enclosingMethodId = r.getInt(1);
		}		
		
		r.close();
		return enclosingMethodId;
	}
	

}