package ca.mcgill.cs.konaila.database;

import java.io.IOException;
import java.sql.SQLException;

import ca.mcgill.cs.konaila.Main;


public class DatabaseRebuild  {	

	private static final String dir = "./database/";
	private static final String sqlFile = dir+"init.sql";

	
	public static void main(String[] args) throws Exception {
		Database db = Database.getInstance();
		db.initConnection(Main.DB);
		db.conn.setAutoCommit(false);
		db.initDB(sqlFile);	
		db.conn.commit();		
		db.closeConnection();
	}
	
	public static void rebuild() throws SQLException, IOException {
		Database.getInstance().initDB(sqlFile);	
	}	

}
