package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DbGenerate {
	
	public static Map<String,Float> selectTopUnits(Connection conn, int cid) throws SQLException, IOException, Exception {
		
		Map<String,Float> map = new HashMap<String,Float>();
		
		PreparedStatement s = conn
				.prepareStatement("SELECT codeDisplayWhole, probabilityInSummary "
						+ " FROM predictions as P, selectionUnits as S "
						+ " WHERE P.cid=S.cid AND P.uid=S.uid AND P.cid=?");
		
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			String code = r.getString("codeDisplayWhole");
			float probability = r.getFloat("probabilityInSummary");
			map.put(code, probability);
		}
		
		return map;
	}
	
}
