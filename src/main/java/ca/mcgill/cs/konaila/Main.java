package ca.mcgill.cs.konaila;

import ca.mcgill.cs.konaila.database.Database;

public class Main {

	public static final String DB = "jdbc:sqlite:src/main/resources/database/codeFragments.sqlite";
//	public static final String DB = "jdbc:sqlite:/dev/shm/codeFragments.sqlite";
		
	public static void main(String[] args) throws Exception {
		Database.setDatabaseString(Main.DB);
		Database.getInstance().getConnection().setAutoCommit(false);
		
		Driver.INSTANCE.doAll(args);
		
		Database.getInstance().getConnection().commit();	
		Database.getInstance().closeConnection();
	}

}
