package ca.mcgill.cs.konaila.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.selection.DatabasePredictionsComponents;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseCodeFragmentCategory;
import ca.mcgill.cs.konaila.selection.features.defuse.DefUseServer;
import ca.mcgill.cs.konaila.selection.features.defuse.KonailaVariableDef;
import ca.mcgill.cs.konaila.selection.features.defuse.KonailaVariableUse;
import ca.mcgill.cs.konaila.selection.features.defuse.ParsingAttribute;
import ca.mcgill.cs.konaila.selection.features.defuse.Strategy;

public class DatabaseFeaturesDataflow {
	
	static List<Integer> ignore = Arrays.asList(new Integer[]{});
	static int startCid = 1;
	
	public static void main(String[] args) throws Exception {

		Database.getInstance().initConnection(Main.DB);
		Connection conn = Database.getInstance().getConnection();
			
		conn.setAutoCommit(false);
				
		populate(Database.getInstance().getConnection(), 0);
		conn.commit();

		System.out.println("*** generating features *** ");
		
		List<Integer> cids = DatabaseCodeFragmentCategory.selectCids(conn);
		for( Integer cid : cids ) {
			System.out.println(cid);
			Map<Integer,Float> boosts = DatabaseFeaturesDataflow.getDataFlowBoost(conn, cid);
			DatabaseFeaturesDataflow.addDataFlowBoost(conn, cid, boosts);
		}
		conn.commit();

		DatabasePredictionsComponents.populateConsolidatedScoresDataFlow(conn);		

		conn.commit();

		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
	
	public static void populate(Connection conn, int server) throws SQLException, IOException {
		
		List<Integer> cidToCodeFragment
			= DatabaseGetCodeFragments.getJavaCodeFragmentsCidsLongEnough(conn);

		List<Integer> sortedCids = new ArrayList<Integer>(cidToCodeFragment);
		Collections.sort(sortedCids);
		
		for( Integer cid : sortedCids ) {
			
			if( cid < startCid) {
				continue;
			}
			
			System.out.println("*** cid = " + cid + "***");
			
			if( ignore.contains(cid)) {
				System.out.println("ignored");
				continue;
			}
			
			populate(conn,cid, server);
				
		}		
	}
	
	public static void populate(Connection conn, int cid, int server) 
			throws SQLException, IOException {

		String code = DatabaseGetCodeFragments.getCodeFragment(conn, cid);
		
		String parsingAttributeString = DatabaseChopperStats.getJustification(conn, cid);
		ParsingAttribute parsingAttribute = ParsingAttribute.valueOf(parsingAttributeString);
		
		if( parsingAttribute == ParsingAttribute.JavaCompilationUnit 
			|| parsingAttribute == ParsingAttribute.JavaClassBodyMemberDeclaration
			|| parsingAttribute == ParsingAttribute.JavaBlockStatements
			) { 
							
			List<KonailaVariableDef> defs = DefUseServer.analyzeDefUse(code, Strategy.ppa, 
				parsingAttribute, DefUseServer.urlForServer(server));			
		
			int variableId = 1;
			for( KonailaVariableDef def : defs ) {
				populate(conn, cid, def, variableId);
				variableId += 1;
			}
		}
	}
	
	public static void populate(Connection conn, int cid, 
			KonailaVariableDef def, int variableId) throws SQLException, IOException {

//		DROP TABLE IF EXISTS dataFlowVariables;
//		CREATE TABLE dataFlowVariables (cid INTEGER, uid INTEGER, internalId INTEGER, variableName, charStart INTEGER, charEnd INTEGER, defOrUse, FOREIGN KEY(uid) REFERENCES selectionUnits(uid), FOREIGN KEY(cid) REFERENCES codeFragments(cid));
		conn.setAutoCommit(false);
		
		PreparedStatement dfv = conn
				.prepareStatement("INSERT INTO dataFlowVariables VALUES (?,?,?,?,?,?,?,?,?)");
		
		String variableName = def.getName();
		int charStart = def.getCharStart();
		int charEnd = def.getCharEnd();
		String defOrUse = "def";
		String type = def.getType();
		String nodeType = def.getNodeType();
		populate(dfv, cid, type, variableName, charStart, charEnd, defOrUse, nodeType, variableId);
		
		for( KonailaVariableUse use : def.getUses() ) {
			populate(dfv, cid, type, use.getName(),
					use.getCharStart(), use.getCharEnd(), "use", 
					use.getNodeType(), variableId);	
		}		
		
		dfv.close();
		conn.commit();
		
		updateUids(conn);
		conn.commit();
	}
	
	private static void populate(PreparedStatement s, int cid, String type,
			String variableName, int charStart, int charEnd, String defOrUse, 
			String nodeType, int variableId)
					throws SQLException, IOException {	
		
		s.setInt(1,cid);
		s.setInt(2, -1); // will match uid later
		s.setString(3, type);
		s.setString(4,  variableName);
		s.setInt(5, charStart);
		s.setInt(6, charEnd);
		s.setString(7, defOrUse);
		s.setString(8,  nodeType);
		s.setInt(9, variableId);
		
		s.executeUpdate();		
	}
	
	private static void updateUids(Connection conn)
			throws SQLException, IOException {
		PreparedStatement s = conn.prepareStatement(
				"UPDATE dataFlowVariables SET uid= "
				+ " (SELECT U.uid"
				+ " FROM elements as E, selectionUnits as U "
				+ " WHERE E.cid=dataFlowVariables.cid AND E.charStart<=dataFlowVariables.charStart AND E.charEnd>=dataFlowVariables.charEnd"
				+ " AND U.cid=E.cid AND U.uid=E.uid)");
		s.executeUpdate();
		s.close();
	}
	
	
	public static Map<Integer,Float> getDataFlowBoost(Connection conn, int cid)
			throws SQLException, IOException {		
		
		Map<Integer, Float> map = new HashMap<Integer,Float>();
		
		PreparedStatement s = conn
				.prepareStatement("SELECT D.variableId, max(P.probabilityInSummary) "
						+ " FROM predictions P, dataFlowVariables D "
						+ " WHERE P.cid=D.cid AND P.uid=D.uid "
						+ " AND D.cid=? "
						+ " GROUP BY D.variableId ");
		s.setInt(1, cid);	
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int uid = r.getInt(1);
			float boost = r.getFloat(2);
			map.put(uid, boost);
		}
		
		s.close();
		return map;
	}
	
	public static Map<Integer,Float> addDataFlowBoost(Connection conn, int cid, 
			Map<Integer,Float> variableIdToBoost)
			throws SQLException, IOException {		
		
		Map<Integer, Float> map = new HashMap<Integer,Float>();
		
		PreparedStatement s = conn
				.prepareStatement("INSERT INTO predictionComponents (cid,uid,score,component) "
						+ "SELECT D.cid, D.uid, ?, 'DataFlowBoost'"
						+ " FROM dataFlowVariables D "
						+ " WHERE  D.cid=? AND D.variableId=?");
		
		for( Entry<Integer,Float> e : variableIdToBoost.entrySet() ) {
			int variableId = e.getKey();
			float boost = e.getValue();
			
			s.setFloat(1, boost);
			s.setInt(2, cid);	
			s.setInt(3, variableId);
			
			s.executeUpdate();
		}
		
		s.close();
		return map;
	}
	
	public static int getDefCharStart(Connection c,
			int cid, String variableName) 
					throws SQLException, IOException {	
		
		int charStart = -1;
		
		PreparedStatement s = c
				.prepareStatement("SELECT charStart, charEnd "
						+ " FROM dataFlowVariables "
						+ " WHERE cid=? AND variableName LIKE ? "
						+ " AND defOrUse LIKE ?");
		s.setInt(1, cid);
		s.setString(2, variableName);
		s.setString(3, "def");
		
		ResultSet r = s.executeQuery();
		
		if( r.next() ) {
			charStart = r.getInt(1);
		}
			
		r.close();
		s.close();
		return charStart;
		
	}
	
	public static int getDefCharEnd(Connection c,
			int cid, String variableName) 
					throws SQLException, IOException {	
		int charEnd = -1;
		
		PreparedStatement s = c
				.prepareStatement("SELECT charStart, charEnd "
						+ " FROM dataFlowVariables "
						+ " WHERE cid=? AND variableName LIKE ? "
						+ " AND defOrUse LIKE ?");
		s.setInt(1, cid);
		s.setString(2, variableName);
		s.setString(3, "def");
		
		ResultSet r = s.executeQuery();
		
		if( r.next() ) {
			charEnd = r.getInt(2);
		}
			
		r.close();
		s.close();
		return charEnd;
	}
	
	public static int getNumberOfUses(Connection c,
			int cid, String variableName, int defCharStart, int defCharEnd) 
					throws SQLException, IOException {	
		int count = -1;
		PreparedStatement s = c
				.prepareStatement("SELECT count(*) "
						+ " FROM dataFlowVariables D, dataFlowVariables U "
						+ " WHERE  D.cid=? AND D.cid=U.cid AND D.variableId=U.variableId "
						+ " AND D.defOrUse LIKE 'def' AND U.defOrUse LIKE 'use'"
						+ " AND D.charStart=? AND D.charEnd=? AND D.variableName=?");
		s.setInt(1, cid);
		s.setInt(2, defCharStart);
		s.setInt(3, defCharEnd);
		s.setString(4, variableName);
		
		ResultSet r = s.executeQuery();
		
		if( r.next() ) {
			count=r.getInt(1);
		}
			
		r.close();
		s.close();
		return count;
		
	}
}
