package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbAndroidFeatureSet {
	
	public static int selectNumberOfFeatureTypes(Connection conn) 
			throws SQLException, IOException {
		int count=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT count(*) FROM featureType");
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			count = r.getInt(1);
		}
		return count;
	}
	
	public static int selectNumberOfMatchedFeatureTypes(Connection conn) 
			throws SQLException, IOException {
		int count=-1;
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT count(DISTINCT F.feature) "
						+ " FROM featureType as T, features as F "
						+ " WHERE T.feature=F.feature");
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			count = r.getInt(1);
		}
		return count;
	}
	
	
}
