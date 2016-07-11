package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbFeatures {
	
	public static List<String> selectFeatures(Connection conn, int cid, int uid) throws SQLException, IOException {

		List<String> features = new ArrayList<String>();
		
		PreparedStatement s = conn.prepareStatement(
						" SELECT * FROM features WHERE cid=? AND uid=?");
		s.setInt(1, cid);
		s.setInt(2, uid);
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			features.add( r.getString(4) );
		}
	
		return features;
	}
	
}
