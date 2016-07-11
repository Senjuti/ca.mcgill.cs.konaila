package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbEclipseGoldStandard {
	
	public static int selectGoldStandardSanity(Connection conn, int cid, int line) throws SQLException, IOException, Exception {
		int code = -1;
		PreparedStatement s = conn
				.prepareStatement("SELECT * FROM eclipseGoldStandardLineBased WHERE cid=? AND line=?");
		
		s.setInt(1, cid);
		s.setInt(2, line);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			code = r.getInt("inSummary");
		}
		return code;
	}
	
	public static int selectNumberOfMatchesWithLineBasedGoldStandard(Connection conn, int cid, int line) throws SQLException, IOException, Exception {
		int count = 0;

		PreparedStatement s = conn
				.prepareStatement("SELECT count(*) "
				+ " FROM eclipseGoldStandardLineBased as E, selectionUnits as U, elements as Elt "
				+ " WHERE E.cid=U.cid AND U.cid=Elt.cid AND U.uid=Elt.uid "
				+ " AND E.cid=? AND E.line=? "
				+ " AND (Elt.lineStart <= E.line AND Elt.lineEnd >= E.line)"
				+ " GROUP BY U.cid, U.uid");
		
		s.setInt(1, cid);
		s.setInt(2, line);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			count+=1;
		}
		
		return count;
	}
	
	public static Map<Integer,Integer> selectEclipseCidsNumberOfLines(Connection conn) throws SQLException, IOException, Exception {
		Map<Integer,Integer> map = new HashMap<Integer,Integer>();
		
		PreparedStatement s = conn
			.prepareStatement("SELECT cid, MAX(line) FROM eclipseGoldStandardLineBased  GROUP BY cid");

		ResultSet r = s.executeQuery();

		while(r.next()) {
			int cid = r.getInt(1);
			int maxLines = r.getInt(2);
			map.put(cid, maxLines);
		}
		
		return map;
	}
}
