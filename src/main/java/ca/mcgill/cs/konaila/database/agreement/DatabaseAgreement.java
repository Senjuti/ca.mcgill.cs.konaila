package ca.mcgill.cs.konaila.database.agreement;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;

public class DatabaseAgreement {
	
	public static final String AGREEMENT_INPUT = "./database/study/agreement.csv";
	public static final String AGREEMENT_USER = "P18.Yam.Chhetri";
	
	public static void main(String[] args) throws Exception {
		Database.setDatabaseString(Main.DB);
//		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();
		conn.setAutoCommit(false);
		
		populateAgreementAssignments(conn);
		
		conn.commit();
		Database.getInstance().closeConnection();
	}

	public static void populateAgreementAssignments(Connection conn)
			throws SQLException, IOException {		

		List<String> lines = FileUtils.readLines(new File(AGREEMENT_INPUT));
		lines.remove(0);  // remove header
		int newTaskNumber = 1; 
		for( String line : lines ) {
//			System.out.println("processing " + line);
			String[] fields = line.split("\t");
			
			String user = fields[1];
			int taskNumber= Integer.parseInt(fields[2]);
		
//			copyTaskAssignment(conn, user, taskNumber, AGREEMENT_USER, newTaskNumber);
			copyRating(conn, user, taskNumber, AGREEMENT_USER, newTaskNumber);
			newTaskNumber += 1;
		}
	}
	
	private static void copyTaskAssignment(Connection conn, String copyFromUser, 
			int copyFromTaskNumber, String copyToUser, int copyToTaskNumber) throws SQLException, IOException {
		
		PreparedStatement insert = conn
				.prepareStatement(
						"INSERT INTO studyTaskAssignments (user,taskNumber, cid,"
						+ "      cidOrder, size, hibernateOrSpring, comment)  " 
						+ " SELECT ?, ?, cid, cidOrder, size, "
						+ "		hibernateOrSpring, 'agreement - ' || ? || ' Task ' || ?"
						+ " FROM studyTaskAssignments "
						+ " WHERE user LIKE ? AND taskNumber=? ");
		insert.setString(1, copyToUser);
		insert.setInt(2, copyToTaskNumber);
		insert.setString(3, copyFromUser);
		insert.setInt(4, copyFromTaskNumber);
		insert.setString(5, copyFromUser);
		insert.setInt(6, copyFromTaskNumber);
		insert.executeUpdate();
		
		System.out.println("inserting  " + copyToTaskNumber);
		
		insert.close();
		conn.commit();
	}
	
	private static void copyRating(Connection conn, String copyFromUser, 
			int copyFromTaskNumber, String copyToUser, int copyToTaskNumber) throws SQLException, IOException {
		
		PreparedStatement insert = conn
				.prepareStatement(
						"INSERT INTO studyRatings (user,taskNumber, summary1,"
						+ "      summary2, summary3, summaryAnswer1, "
						+ "      summaryAnswer2, summaryAnswer3)  " 
						+ " SELECT ?, ?, summary1, summary2, summary3, "
						+ "		'not rated yet', 'not rated yet', 'not rated yet'"
						+ " FROM studyRatings "
						+ " WHERE user LIKE ? AND taskNumber=? ");
		insert.setString(1, copyToUser);
		insert.setInt(2, copyToTaskNumber);
		insert.setString(3, copyFromUser);
		insert.setInt(4, copyFromTaskNumber);
		insert.executeUpdate();
		
		System.out.println("inserting  " + copyToTaskNumber);
		
		insert.close();
		conn.commit();
	}
	
}
