package ca.mcgill.cs.konaila.selection;

import java.io.File;
import java.sql.Connection;

import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.cs.konaila.DbEclipseTraining;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class TestTrainingData {

	static Connection c;

	@BeforeClass
	public static void setUp() throws Exception {
		Database.getInstance().initConnection(Main.DB);
		System.out.println("initialized DB connection");
		c = Database.getInstance().getConnection();
	}
	
	
	public void test() throws Exception {
		String content = DbEclipseTraining.selectTrainingData(c);
		FileUtils.write(new File("./training/eclipse.csv"), content);
	}
	
	@AfterClass
	public static void tearDown() throws Exception {
		Database.getInstance().closeConnection();
		System.out.println("closed DB connection");
	}
}
