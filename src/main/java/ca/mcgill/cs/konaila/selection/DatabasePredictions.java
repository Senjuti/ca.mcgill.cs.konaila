package ca.mcgill.cs.konaila.selection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import ca.mcgill.cs.konaila.chopper.SelectionUnit.HoleElement;
import ca.mcgill.cs.konaila.database.DatabaseEnclosingRelationships;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class DatabasePredictions {
	
	public static Map<Integer,Float> selectEqualOrAboveThreshold(Connection conn, int cid, float threshold) throws SQLException, IOException {
		return selectAroundThreshold(conn, cid, threshold, ">=", SummarizationMethod.ConfigurationBased);
	}

	public static Map<Integer,Float> selectEqualThreshold(Connection conn, int cid, float threshold) throws SQLException, IOException {
		return selectAroundThreshold(conn, cid, threshold, "=", SummarizationMethod.ConfigurationBased);
	}
	
	public static List<Integer> selectTopUidsEqualThresholdClosestToUids(
			Connection conn, int cid, float threshold, Collection<Integer> closestToUids, 
			int top, SummarizationMethod summarizationMethod) throws SQLException, IOException {
		
		String equation = "";
		if( closestToUids.size() == 0 ) {
			equation = "0";
		} else {
			boolean first = true;
			for( int closestToUid : closestToUids ) {
				equation += ((!first) ? "+" : "") + 
						"abs(S.uid-" + closestToUid + ")";
				first=false;
			}
		}
		
		List<Integer> sortedUids = new ArrayList<Integer>();
		
		String query = "SELECT S.uid, probabilityInSummary, codeDisplayWhole, *** as C"
				+ " FROM predictions as P, selectionUnits as S "
				+ " WHERE P.cid=S.cid AND P.uid=S.uid "
				+ " AND P.cid=? AND P.probabilityInSummary = ? "
				+ " AND P.featureSet LIKE ? "
				+ " ORDER BY C ASC"
				+ " LIMIT ?";
		
		query = query.replace("***",equation);
		PreparedStatement s = conn.prepareStatement(query);
		
		int i=1;
//		s.setString(i, equation); i+=1;
		s.setInt(i, cid); i+=1;
		s.setFloat(i, threshold); i+=1;
		s.setString(i, summarizationMethod.name()); i+=1;
		s.setInt(i, top);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int uid = r.getInt("uid");
			sortedUids.add(uid); 
		}

		s.close();
		return sortedUids;
	}
	
	public static Map<Integer,Float> selectAboveThreshold(Connection conn, 
			int cid, float threshold, 
			SummarizationMethod summarizationMethod) throws SQLException, IOException {
		return selectAroundThreshold(conn, cid, threshold, ">", summarizationMethod);
	}

	private static Map<Integer,Float> selectAroundThreshold(Connection conn, int cid, 
			float threshold, String comparison, 
			SummarizationMethod summarizationMethod) throws SQLException, IOException {
		
		Map<Integer,Float> map = new HashMap<Integer,Float>();
		
		String query = "SELECT S.uid, probabilityInSummary, codeDisplayWhole"
				+ " FROM predictions as P, selectionUnits as S "
				+ " WHERE P.cid=S.cid AND P.uid=S.uid "
				+ " AND P.cid=? AND P.probabilityInSummary *** ? "
				+ " AND P.featureSet LIKE ? "
				+ " ORDER BY probabilityInSummary DESC";
		query = query.replace("***", comparison);
		
		PreparedStatement s = conn.prepareStatement(query);
		
		s.setInt(1, cid);
		s.setFloat(2, threshold);
		s.setString(3, summarizationMethod.name());
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int code = r.getInt("uid");
			float probability = r.getFloat("probabilityInSummary");
			map.put(code, probability);
		}

		s.close();
		return map;
	}
	
	public static Map<Integer,Float> selectTop(Connection conn, int cid, 
			float summarizationRate,
			SummarizationMethod summarizationMethod) throws SQLException, IOException {
		
		Map<Integer,Float> map = new HashMap<Integer,Float>();
		
		PreparedStatement s = conn
				.prepareStatement(
						 "SELECT S.uid, probabilityInSummary, codeDisplayWhole "
						+ " FROM predictions as P, selectionUnits as S "
						+ " WHERE P.cid=S.cid AND P.uid=S.uid "
						+ " AND P.cid=? "
						+ " AND P.featureSet LIKE ? "
						+ " ORDER BY P.probabilityInSummary DESC "
						+ " LIMIT MAX(1, CAST(((SELECT COUNT(*) FROM selectionUnits WHERE cid=?) * ?) AS int))");
		
		int i = 1;
		s.setInt(i, cid); i+=1;
		s.setString(i, summarizationMethod.name()); i+=1;
		s.setInt(i, cid); i+=1;
		s.setFloat(i, summarizationRate); i+=1;
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int code = r.getInt("uid");
			float probability = r.getFloat("probabilityInSummary");
			map.put(code, probability);
		}

		s.close();
		return map;
	}
	
	public static float selectTopProbability(Connection conn, int cid, 
			float summarizationRate, SummarizationMethod summarizationMethod) throws SQLException, IOException {
				
		PreparedStatement s = conn
				.prepareStatement(
						 "SELECT S.uid, probabilityInSummary, codeDisplayWhole "
						+ " FROM predictions AS P, selectionUnits AS S "
						+ " WHERE P.cid=S.cid AND P.uid=S.uid "
						+ " AND P.cid=? AND P.featureSet LIKE ? "
						+ " ORDER BY P.probabilityInSummary DESC "
						+ " LIMIT MAX(1, CAST(((SELECT COUNT(*) FROM selectionUnits WHERE cid=?) * ?) AS int))");
		
		float probability = -1000;
		int i = 1;
		s.setInt(i, cid); i+=1;
		s.setString(i,  summarizationMethod.name()); i+=1;
		s.setInt(i, cid); i+=1;
		s.setFloat(i, summarizationRate); i+=1;
		ResultSet r = s.executeQuery();
		
		while(r.next()) { // get last probability
			 probability = r.getFloat("probabilityInSummary");
		}
		
		s.close();
		return probability;
	}
	
	
	
	public static float selectTopProbability(Connection conn, int cid, 
			int summarySize,
			SummarizationMethod summarizationMethod) throws SQLException, IOException {
		
		PreparedStatement s = conn.prepareStatement(
						 "SELECT S.uid, probabilityInSummary, codeDisplayWhole "
						+ " FROM predictions as P, selectionUnits as S "
						+ " WHERE P.cid=S.cid AND P.uid=S.uid "
						+ " AND P.cid=? "
						+ " AND P.featureSet LIKE ? "
						+ " ORDER BY P.probabilityInSummary DESC "
						+ " LIMIT ?");
		
		float probability = -1;
		int i = 1;
		s.setInt(i, cid); i+=1;
		s.setString(i,  summarizationMethod.name()); i+=1;
		s.setInt(i, summarySize); i+=1;
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			 probability = r.getFloat("probabilityInSummary");
		}

		s.close();
		return probability;
	}
	
	public static List<SelectedElement> getCodeForSelectedElements(Connection conn, int cid, 
			Collection<Integer> uids) throws SQLException, IOException {
		
		List<SelectedElement> elements = new ArrayList<SelectedElement>();
		Set<Integer> elementUids = new HashSet<Integer>();
		
		String query =  "SELECT charStart, charEnd, code, E.uid, U.enclosingUnitUid, codeDisplayWhole, lineStart, lineEnd "
				 + " FROM elements as E, selectionUnits as U "
				 + " WHERE E.cid=U.cid AND E.uid=U.uid AND E.cid=? " + getSqlString(uids) 
				 + " ORDER BY charStart ASC";
		
		
		PreparedStatement s = conn.prepareStatement(query);
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();		
		
		while(r.next()) {
			int charStart = r.getInt("charStart");
			int enclosingUnitUid = r.getInt("enclosingUnitUid");
			int uid = r.getInt("uid");
			SelectedElement e = new SelectedElement(charStart,
					r.getInt("charEnd"),
					r.getInt("lineStart"),
					r.getInt("lineEnd"),
					r.getString("code"),
					r.getString("codeDisplayWhole"),
					uid,
					enclosingUnitUid);			 
			elements.add(e);
			elementUids.add(uid);
		}
		
		for( SelectedElement e : elements ) {
			
			int currEnclosingUid = e.getEnclosingUnitUid();
			while ( currEnclosingUid != -1 ) {
				if( uids.contains(currEnclosingUid) ) {
					e.addEnclosingUnit(currEnclosingUid);
				}
				currEnclosingUid = DatabaseEnclosingRelationships.getEnclosingUid(conn, cid, currEnclosingUid); 
			}
		}

		s.close();
		return elements;
	}
	
	public static Map<Integer,SelectedElement> getCodeForSelectedElementsMap(
			Connection conn, int cid, 
			Collection<Integer> uids) throws SQLException, IOException {
		
		List<SelectedElement> element = getCodeForSelectedElements(conn, cid, uids);
		Map<Integer,SelectedElement> map = new HashMap<Integer,SelectedElement>();
		for( SelectedElement e : element ) {
			map.put(e.getCharStart(), e);
		}

		populateHoleForSelectedElements(conn, cid, uids, map);
		populateEllipsesForCallUnitsWithNoEnclosingUnits(conn, cid, uids, map);
		
		return map;
	}
	
	public static Map<Integer,SelectedElement> populateHoleForSelectedElements(Connection conn, 
			int cid, Collection<Integer> enclosingUids, 
			Map<Integer,SelectedElement> selectedElements) throws SQLException, IOException {
				
		String query =  "SELECT charStart, charEnd, code, E.uid, U.enclosingUnitUid, codeDisplayWhole, lineStart, lineEnd "
				 + " FROM holes as E, selectionUnits as U "
				 + " WHERE E.cid=U.cid AND E.enclosingUid=U.uid AND E.cid=? " 
				 + getSqlString(enclosingUids, "E.enclosingUid") 
				 + " ORDER BY charStart ASC";
		
		PreparedStatement s = conn.prepareStatement(query);
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();		
		
		while(r.next()) {
			int charStart = r.getInt("charStart");
			int enclosingUnitUid = r.getInt("enclosingUnitUid");
			int uid = r.getInt("uid");
			SelectedElement e = new SelectedElement(charStart,
					r.getInt("charEnd"),
					r.getInt("lineStart"),
					r.getInt("lineEnd"),
					HoleElement.HOLE,
					r.getString("codeDisplayWhole"),
					uid,
					enclosingUnitUid);		
			if( !selectedElements.containsKey(charStart)) {
				selectedElements.put(charStart,e);
			}
		}
		
		s.close();
		return selectedElements;
	}
	
	public static Map<Integer,SelectedElement> populateEllipsesForCallUnitsWithNoEnclosingUnits(Connection conn, 
			int cid, Collection<Integer> selectedUids, 
			Map<Integer,SelectedElement> selectedElements) throws SQLException, IOException {
						
		Set<Integer> enclosingOfSelectedCallUnits = getEnclosingSimpleStatementsForCallUnits(conn, cid, selectedUids);
				
		if( enclosingOfSelectedCallUnits.size() == 0 ) {
			return selectedElements;
		}
		
		String query =  "SELECT U.uid, charStart, charEnd, lineStart, lineEnd, "
				+ "              konailaNodeType, enclosingUnitUid, code, codeDisplayWhole "
				+ " FROM selectionUnits U, elements E "
				+ " WHERE U.cid=? " 
				+ getSqlString(enclosingOfSelectedCallUnits, "E.uid") 
				+ " AND U.cid=E.cid AND U.uid=E.uid ";
		
		PreparedStatement s = conn.prepareStatement(query);
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();		
		
		while(r.next()) {
			int charStart = r.getInt("charStart");
			int charEnd = r.getInt("charEnd");
			int lineStart = r.getInt("lineStart");
			int lineEnd = r.getInt("lineEnd");
			int enclosingUnitUid = r.getInt("enclosingUnitUid");
			int uid = r.getInt(1); 
			String konailaNodeType = r.getString("konailaNodeType");
			String elementCode = r.getString("code");
			String replacedCode = "";
			
			if( konailaNodeType.equals("SimpleStatement")) {
				replacedCode = HoleElement.HOLE;
				if( elementCode.trim().endsWith(";") ) {
					replacedCode += " ;";
				}
				
				SelectedElement e = new SelectedElement(charStart, charEnd,
						lineStart, lineEnd,
						replacedCode,
						r.getString("codeDisplayWhole"),
						uid,
						enclosingUnitUid);		
				if( !selectedElements.containsKey(charStart)) {
					selectedElements.put(charStart,e);
				}
			}		
		}
		
		s.close();
		return selectedElements;
	}
	
	public static Set<Integer> getEnclosingSimpleStatementsForCallUnits(Connection conn, int cid, 
			Collection<Integer> selectedUids) throws SQLException, IOException {
		
		Set<Integer> enclosingUids = new HashSet<Integer>();
		String query =  "SELECT U.enclosingUnitUid "
				+ " FROM selectionUnits U, selectionUnits Enclosing"
				 + " WHERE U.cid=? " + getSqlString(selectedUids, "U.uid")
				 + " AND U.konailaNodeType LIKE 'CallUnit' "
				 + " AND U.enclosingUnitUid = Enclosing.uid AND Enclosing.konailaNodeType LIKE 'SimpleStatement'";
		
		PreparedStatement s = conn.prepareStatement(query);
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int enclosingUid = r.getInt(1);
			enclosingUids.add(enclosingUid);
		}
		
		r.close();
		s.close();
		
		return enclosingUids;		
	}
	
	public static String getCode(Connection conn, int cid, int uid) throws SQLException, IOException {
				
		String query =  "SELECT codeDisplayWhole "
				+ " FROM selectionUnits "
				 + " WHERE cid=? AND uid=?";
		
		
		PreparedStatement s = conn.prepareStatement(query);
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();
		
		String code = "";
		
		if(r.next()) {
			code = r.getString(1);
		}
		return code;
	}
	
	private static String getSqlString(Collection<Integer> uids) {
		return getSqlString(uids, "E.uid");
	}
	
	private static String getSqlString(Collection<Integer> uids, String field) {
		String query = "";
		if( uids.size() >0 ) {
			Iterator<Integer> uidIt = uids.iterator();
			if( uidIt.hasNext() ) {
				query += "AND (" + field + "=" + uidIt.next();
			}
			while( uidIt.hasNext() ) {
				query += " OR " + field + "=" + uidIt.next();
			}
			query+=") ";
		}
		return query;
	}
	
	public static List<Integer> getPredictionCids(Connection conn) 
			throws SQLException, IOException {
		List<Integer> cids = new ArrayList<Integer>();
		PreparedStatement s = conn.prepareStatement(
				"SELECT DISTINCT cid FROM predictions ORDER BY cid ASC");
		ResultSet r = s.executeQuery();
		
		while( r.next()) {
			int cid = r.getInt("cid");
			cids.add(cid);
		}

		s.close();
		return cids;
	}
	
	public static List<Integer> getPredictionCids(Connection conn, String source) 
			throws SQLException, IOException {
		List<Integer> cids = new ArrayList<Integer>();
		PreparedStatement s = conn.prepareStatement(
				"SELECT DISTINCT P.cid "
				+ " FROM predictions P, codeFragments C "
				+ " WHERE P.cid=C.cid AND C.source LIKE ? "
				+ " ORDER BY P.cid ASC");
		
		s.setString(1, source);
		
		ResultSet r = s.executeQuery();
		
		while( r.next()) {
			int cid = r.getInt("cid");
			cids.add(cid);
		}

		s.close();
		return cids;
	}

	
	public static Map<Integer, Float> getValues(Connection conn, int cid, 
			SummarizationMethod summarizationMethod) 
			throws SQLException, IOException {
		Map<Integer, Float> uidToValue = new HashMap<Integer, Float>();
		PreparedStatement s = conn.prepareStatement(
				"SELECT uid, probabilityInSummary "
				+ " FROM predictions "
				+ " WHERE cid = ? AND featureSet LIKE ?");
		s.setInt(1, cid);
		s.setString(2,  summarizationMethod.name());
		
		ResultSet r = s.executeQuery();
		
		while( r.next()) {
			int uid = r.getInt(1);
			float value = r.getFloat(2);
			uidToValue.put(uid, value);
		}

		s.close();
		return uidToValue;
	}
	
	public static List<Pair<Integer, Float>> getSortedUidToValues(Connection conn, int cid, 
			SummarizationMethod summarizationMethod) 
			throws SQLException, IOException {
		List<Pair<Integer,Float>> result = new ArrayList<Pair<Integer,Float>>();
		PreparedStatement s = conn.prepareStatement(
				"SELECT uid, probabilityInSummary "
				+ " FROM predictions "
				+ " WHERE cid = ? AND featureSet LIKE ?"
				+ " ORDER BY probabilityInSummary DESC ");
		s.setInt(1, cid);
		s.setString(2,  summarizationMethod.name());
		
		ResultSet r = s.executeQuery();
		
		while( r.next()) {
			int uid = r.getInt(1);
			float value = r.getFloat(2);
			ImmutablePair<Integer,Float> uidToValue = new ImmutablePair<Integer, Float>(uid,value);

			result.add(uidToValue);
		}

		s.close();
		return result;
	}

	public static int selectNumberOfSelectionUnits(Connection conn, int cid) throws SQLException, IOException {
				
		PreparedStatement s = conn
				.prepareStatement(
						 "SELECT count(uid) FROM selectionUnits WHERE cid = ?");
		int count = -1;
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			 count = r.getInt(1);
		}
		
		s.close();
		return count;
	}
	
	public static Map<Integer,String> selectSelectionUnitCode(Connection conn, 
			int cid, SummarizationMethod summarizationMethod) throws SQLException, IOException {		
		Map<Integer,String> uidToCode = new HashMap<Integer,String>();
		
        PreparedStatement s = conn
                .prepareStatement(
                                  "SELECT S.uid, S.codeDisplayWhole "
                                  + " FROM selectionUnits S, predictions P "
                                  + " WHERE S.cid=? AND S.uid=P.uid AND S.cid=P.cid"
                                  + " AND P.featureSet LIKE ?");        
        
		s.setInt(1, cid);   
		s.setString(2,  summarizationMethod.name());
		ResultSet r = s.executeQuery();			
		
		while( r.next() ) {
			int uid = r.getInt(1);
			String codeDisplayWhole = r.getString(2);
			
			String code = codeDisplayWhole.replace(HoleElement.HOLE, "");
			
			uidToCode.put(uid,code);
		}
		r.close();
		s.close();
		return uidToCode;		
	}

	public static String selectSelectionUnitCode(Connection conn, 
			int cid, int uid, SummarizationMethod summarizationMethod) throws SQLException, IOException {		
		String code = "";
		
        PreparedStatement s = conn.prepareStatement(
                                  "SELECT S.codeDisplayWhole "
                                  + " FROM selectionUnits S, predictions P "
                                  + " WHERE S.cid=? AND S.uid=P.uid AND S.cid=P.cid"
                                  + " AND P.featureSet LIKE ?"
                                  + " AND S.uid=?");        
        
		s.setInt(1, cid);   
		s.setString(2,  summarizationMethod.name());
		s.setInt(3,  uid);
		ResultSet r = s.executeQuery();			
		
		if( r.next() ) {
			String codeDisplayWhole = r.getString(1);
			
			code = codeDisplayWhole.replace(HoleElement.HOLE, "");
		}
		r.close();
		s.close();
		return code;		
	}
	
	public static class SelectedElement {
		int charStart;
		int charEnd;
		int lineStart;
		int lineEnd;
		String code;
		String displayWhole;
		int uid;
		List<Integer> enclosingUnitUids = new ArrayList<Integer>();
		int enclosingUnitUid;
		
		public SelectedElement(int charStart, int charEnd, int lineStart, int lineEnd,
				String code, String displayWhole,
				int uid, int enclosingUid) {
			super();
			this.charStart = charStart;
			this.charEnd = charEnd;
			this.lineStart = lineStart;
			this.lineEnd = lineEnd;
			this.code = code;
			this.displayWhole = displayWhole;
			this.uid = uid;
			this.enclosingUnitUid = enclosingUid;
//			this.enclosingUnitUids.add(enclosingUid);
		}
		public int getCharStart() {
			return charStart;
		}
		public int getCharEnd() {
			return charEnd;
		}
		public String getCode() {
			return code;
		}		
		public String getDisplayWhole() {
			return displayWhole;
		}
		public int getUid() {
			return uid;
		}
		public int getEnclosingUnitUid() {
			return enclosingUnitUid;
		}
		public int getLineStart() {
			return lineStart;
		}
		public int getLineEnd() {
			return lineEnd;
		}
		public void addEnclosingUnit(int enclosingUid) {
			enclosingUnitUids.add(enclosingUid);
		}
		public int getIndentationLevel() {
			return enclosingUnitUids.size();
		}
		@Override
		public String toString() {
			return uid + ": \"" + code + "\""; 
		}
		@Override
		public boolean equals(Object o) {
			if( o instanceof SelectedElement ) {
				SelectedElement e = (SelectedElement)o;
				return e.getDisplayWhole().equals(this.getDisplayWhole());
			}
			return false;
		}
	}
	

	public static class SelectedHole extends SelectedElement {
	
		public SelectedHole(int charStart, int charEnd, int lineStart, int lineEnd,
				String code, String displayWhole,
				int uid, int enclosingUid) {
			super(charStart, charEnd, lineStart, lineEnd, code, displayWhole, uid, enclosingUid);
		}		
	}
}