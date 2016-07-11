package ca.mcgill.cs.konaila.database.results;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.parse.CodeFragmentPrerequisite;
import ca.mcgill.cs.konaila.summarize.HowToSummarize;
import ca.mcgill.cs.konaila.summarize.Size;
import ca.mcgill.cs.konaila.ui.survey.Answer;

public class DatabaseStudyResults {
	
	public static final String PAIRED_RESULTS = "./results/paired-ratings.csv";
	
	static {
		try {
			FileUtils.writeStringToFile(new File(PAIRED_RESULTS), "user\ttask.number\t" 
					+ HowToSummarize.values()[0] + "\t" 
					+ HowToSummarize.values()[1] + "\t"
					+ HowToSummarize.values()[2] + "\t"
					+ "Number of non-empty lines" + 
					"\n", false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
		
	
	public static void main(String[] args) throws Exception {
		Database.setDatabaseString(Main.DB);
//		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();
		
		getPairedResultsToFile(conn);
		
		Database.getInstance().closeConnection();
	}

	public static void getPairedResultsToFile(Connection conn)
			throws SQLException, IOException {		

		Answer[] answers = new Answer[3];

		PreparedStatement s = conn.prepareStatement(
				"SELECT user, taskNumber, summary1, summary2, summary3,"
				+ "	summaryAnswer1, summaryAnswer2, summaryAnswer3 "
				+ " FROM studyRatings WHERE summaryAnswer1 NOT LIKE 'not rated yet'");
		
		PreparedStatement i = conn.prepareStatement(
				"INSERT INTO results VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			String user = r.getString(1);
			int taskNumber = r.getInt(2);
							
			String how1 = r.getString(3);
			String how2 = r.getString(4);
			String how3 = r.getString(5);
			String answer1 = r.getString(6);
			String answer2 = r.getString(7);
			String answer3 = r.getString(8);			
			
			answers[HowToSummarize.valueOf(how1).ordinal()] = Answer.valueOf(answer1);
			answers[HowToSummarize.valueOf(how2).ordinal()] = Answer.valueOf(answer2);
			answers[HowToSummarize.valueOf(how3).ordinal()] = Answer.valueOf(answer3);
			
			int value1 = (4-answers[0].ordinal());
			int value2 = (4-answers[1].ordinal());
			int value3 = (4-answers[2].ordinal());
			
			Task task = new Task(user, taskNumber, value1, value2, value3);
			
			populateTask(conn, user, taskNumber, task);
			populateSummaries(conn, task.getCid(), task.getSize(), task);
			populateCodeFragmentInfo(conn, task.getCid(), task);
			
			addResults(conn, i, task.getUser(), task.getTaskNumber(),
					task.getKnapsackRating(), task.getGreedyRating(),
					task.getBaselineRating(), task);
		}

		r.close();
		s.close();
		i.close();
	}
	
	private static void addResults(Connection conn, PreparedStatement i,
			String user, int taskNumber, int value1, int value2, int value3, Task task) 
			throws SQLException, IOException {
		i.setString(1, user);
		i.setInt(2, taskNumber);
		i.setInt(3, value1);
		i.setInt(4, value2);
		i.setInt(5, value3);
		i.setString(6, task.getSize().name());
		i.setString(7, task.getHibernateOrSpring());
		i.setString(8, task.getKnapsackSummary());
		i.setString(9,  task.getGreedySummary());
		i.setString(10, task.getBaselineSummary());
		i.setInt(11, task.getNumberOfLines());
		i.setInt(12, task.getNumberOfNonEmptyLines());
		i.setInt(13, task.getCid());
		i.executeUpdate();	
		
		String line = user + "\t" + taskNumber + "\t" + 
				value1 + "\t" + value2 + "\t" + value3 + 
				+ task.getNumberOfNonEmptyLines() + "\n";
		FileUtils.write(new File(PAIRED_RESULTS), line, true);
	}

	private static void populateTask(Connection conn, 
			String user, int taskNumber, Task task)
			throws SQLException, IOException {	
		PreparedStatement tasks = conn.prepareStatement(
				"SELECT cid, size, hibernateOrSpring "
				+ " FROM studyTaskAssignments "
				+ " WHERE user=? AND taskNumber=?");
		tasks.setString(1, user);
		tasks.setInt(2, taskNumber);
		ResultSet r = tasks.executeQuery();
		if(r.next()) {
			int cid = r.getInt(1);
			String size = r.getString(2);
			String hibernateOrSpring = r.getString(3);
			
			Size s = Size.valueOf(size);
			
			task.setTaskDetails(cid, s, hibernateOrSpring);
		}		
		r.close();tasks.close();
	}
	
	private static void populateSummaries(Connection conn, int cid, Size size,
			Task task) throws SQLException, IOException {	
		PreparedStatement s = conn.prepareStatement(
				"SELECT how, summary "
				+ " FROM summaries "
				+ " WHERE cid=? AND size = ?");
		s.setInt(1, cid);
		s.setString(2, task.getSize().name());
		ResultSet r = s.executeQuery();
		while(r.next()) {
			HowToSummarize how = HowToSummarize.valueOf(r.getString(1));
			String summary = r.getString(2);
		
			if( how == HowToSummarize.Knapsack) {
				task.setKnapsack(summary);
			} else if ( how == HowToSummarize.Greedy) {
				task.setGreedy(summary);
			} else if( how == HowToSummarize.Baseline) {
				task.setBaseline(summary);
			}
			
		}		
		r.close();
		s.close();
	}
	
	private static void populateCodeFragmentInfo(Connection conn, int cid, 
			Task task) throws SQLException, IOException {	

		PreparedStatement s = conn.prepareStatement(
				"SELECT code "
				+ " FROM codeFragments  "
				+ " WHERE cid=? ");
		s.setInt(1, cid);
		ResultSet r = s.executeQuery();
		if(r.next()) {
			String code = r.getString(1);
			task.setCodeInfo(code, 
					CodeFragmentPrerequisite.getLength(code),
					CodeFragmentPrerequisite.getLengthNonEmptyLines(code));
		}		
		r.close();
		s.close();
	}
	
	
	static class Task {
		String user;
		int taskNumber;
		int cid;
		Size size;
		String hibernateOrSpring;
		int knapsackRating;
		int greedyRating;
		int baselineRating;
		String knapsackSummary;
		String greedySummary;
		String baselineSummary;
		int summaryLength;
		String code;
		int numberOfLines;
		int numberOfNonEmptyLines;
		public Task(String user, int taskNumber, int knapsackRating, int greedyRating,
				int baselineRating) {
			super();
			this.user = user;
			this.taskNumber = taskNumber;
			this.knapsackRating = knapsackRating;
			this.greedyRating = greedyRating;
			this.baselineRating = baselineRating;
		}
		public void setTaskDetails(int cid, Size size, String hibernateOrSpring) {
			this.cid = cid;
			this.size = size;
			this.hibernateOrSpring = hibernateOrSpring;			
		}
		public void setKnapsack(String knapsackSummary) {
			this.knapsackSummary = knapsackSummary;		
		}
		public void setGreedy(String greedySummary) {
			this.greedySummary = greedySummary;			
		}
		public void setBaseline(String baselineSummary) {
			this.baselineSummary = baselineSummary;			
		}
		public void setCodeInfo(String code, int numberOfLines,
				int numberOfNonEmptyLines) {
			this.code = code;
			this.numberOfLines = numberOfLines;
			this.numberOfNonEmptyLines = numberOfNonEmptyLines;
		}
		public String getCode() {
			return code;
		}
		public int getNumberOfLines() {
			return numberOfLines;
		}
		public int getNumberOfNonEmptyLines() {
			return numberOfNonEmptyLines;
		}
		public String getUser() {
			return user;
		}
		public int getTaskNumber() {
			return taskNumber;
		}
		public int getCid() {
			return cid;
		}
		public Size getSize() {
			return size;
		}
		public String getHibernateOrSpring() {
			return hibernateOrSpring;
		}
		public int getKnapsackRating() {
			return knapsackRating;
		}
		public int getGreedyRating() {
			return greedyRating;
		}
		public int getBaselineRating() {
			return baselineRating;
		}
		public String getKnapsackSummary() {
			return knapsackSummary;
		}
		public String getGreedySummary() {
			return greedySummary;
		}
		public String getBaselineSummary() {
			return baselineSummary;
		}		
	}
	
}
