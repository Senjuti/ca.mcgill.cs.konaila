package ca.mcgill.cs.konaila.selection;

import ca.mcgill.cs.konaila.database.Database;

public class MainPopulatePredictions {

	public static final String DB = "jdbc:sqlite:database/codeFragments.sqlite";
		
	public static void main(String[] args) throws Exception {
		Database.getInstance().initConnection(MainPopulatePredictions.DB);
		Database.getInstance().getConnection().setAutoCommit(false);
		
		Database.getInstance().populateEclipsePredictions();
		
		Database.getInstance().getConnection().commit();	
		Database.getInstance().closeConnection();
	}
}
