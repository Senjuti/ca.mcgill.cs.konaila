package ca.mcgill.cs.konaila.selection;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class DatabasePrintInfoForManualClustering  {	

	private static final String DB = Main.DB;
	
	
	public static void main(String[] args) throws Exception {
		Database db= Database.getInstance();
		db.initConnection(DB);
		
		print(db.getConnection());	
		
		db.closeConnection();
	}

	
	public static void print(Connection conn) throws SQLException, IOException {

		String context = "cid\tcode (first line)\tquery\ttags\n";
		
		PreparedStatement s = conn
				.prepareStatement(
						"SELECT cid, query, code FROM codeFragments WHERE source = 'eclipse-faq' ORDER BY cid ASC");		
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int cid = r.getInt(1);
			String code = r.getString("code");
			String query = r.getString("query");
			
			String codeFirstLine = code.split("\n")[0];
			
			context += Integer.toString(cid) + "\t" + codeFirstLine +"\t" + query + "\n";
		}
		
		FileUtils.write(new File("./manual-clustering.csv"), context);
	}
}
