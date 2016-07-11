package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbEclipseUnit {
	
	public static String selectCodeFromUnit(Connection conn, int cid, int lineStart, int lineEnd) throws SQLException, IOException, Exception {
		String code = "";
		PreparedStatement s = conn
				.prepareStatement("SELECT * FROM selectionUnits as S"
						+ " WHERE S.cid=? and S.unitLineStart=? and S.unitLineEnd=?");
		
		s.setInt(1, cid);
		s.setInt(2, lineStart);
		s.setInt(3, lineEnd);
		ResultSet r = s.executeQuery();
		
		if(r.next()) {
			code = r.getString("codeDisplayWhole");
		}
		return code;
	}
	
}
