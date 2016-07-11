package ca.mcgill.cs.konaila.selection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConfigurationBasedPredictions {
		
	public static void populatePredictions(Connection conn, int cid, int uid, int score) throws SQLException, IOException {
	
		PreparedStatement s = conn
				.prepareStatement(
						"INSERT INTO predictions VALUES (?,?,?,-1) ");
		
		s.setInt(1, cid);
		s.setInt(2, uid);
		s.setDouble(3, (double)score);
		s.executeUpdate();

		s.close();
	}

}
