package ca.mcgill.cs.konaila.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.summarize.HowToSummarize;
import ca.mcgill.cs.konaila.summarize.Size;

public class DatabasePopulateStudy {
	
	static final File participantListFile = new File("./database/study/participants.csv"); 
	
	enum HibernateOrSpringOrBoth {
		hibernate, spring, both;
	}
	
	public static void main(String[] args) throws Exception {
		Database.setDatabaseString(Main.DB);
//		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();

		conn.setAutoCommit(false);
		
//		populateCodeFragmentOrdering(conn);
			
//		populateTasks(conn);

//		populateTaskAssignments(conn, participantListFile);		
//		copyTaskAssignments(conn, "P01.Paul.Holden", "P02.Theresa.Deering", "pilot");
//		copyTaskAssignments(conn, "P14.Mathieu.Nassif", "P03.Marc.Klocke", 
//				"copying from P03.Marc.Klocke"); // Spring
//		copyTaskAssignments(conn, "P13.Alex", "P02.Theresa.Deering",
//				"copying from P02.Theresa.Deering"); // Both
//		copyTaskAssignments(conn, "P16.Harry.Tran", "P11.Henri.Tremblay",
//				"copying from P11.Henri.Tremblay"); // Both
//		copyTaskAssignments(conn, "P17.Suzy.Wu", "P05.Chen.Bi",
//				"copying from P05.Chen.Bi"); // Both		
//		copyTaskAssignments(conn, "P16.Harry.Tran", "P12.Niloofar.Khoshsiyar",
//		"copying from P12.Niloofar.Khoshsiyar"); // Both
//		copyTaskAssignments(conn, "P12.Niloofar.Khoshsiyar", "P07.Alec.Harmon.Blumenfeld",
//		"copying from P07.Alec.Harmon.Blumenfeld"); // Hibernate
//		unassign(conn, "P12.Niloofar.Khoshsiyar");
//		unassign(conn, "P15.Ying.Zhai");
		unassign(conn, "P19.Devon.Ochman");
		
//		cleanP9(conn);
		
		
		// ---------------- ALWAYS CHECK!!!!!! ---------------- 
/*SELECT user, count(*)
FROM studyTaskAssignments
GROUP BY user*/

/*SELECT count(DISTINCT cidOrder)
FROM studyTaskAssignments
WHERE user NOT LIKE 'NOT ASSIGNED'
GROUP BY hibernateOrSpring, size*/
		
		conn.commit();		
		Database.getInstance().closeConnection();
	}
	
	public static void populateCodeFragmentOrdering(Connection conn)
			throws SQLException, IOException {

		List<Integer> eligibleQuestionIdsRandomized = getThreadsForStudyRandomized(conn);
		PreparedStatement s = conn.prepareStatement(
				"SELECT M.cid, max(score) "
				+ " FROM stackOverflowMetaData M, codeFragmentParsingAttributes A"
				+ " WHERE M.questionId=? AND M.cid=A.cid AND A.comment LIKE 'Java%' "
				+ " ORDER BY M.cid");
		
		PreparedStatement insert = conn.prepareStatement(
				"INSERT INTO studyRandomizedCodeFragments VALUES(?,?,?)");
	
		int order=1;
		for( Integer questionId : eligibleQuestionIdsRandomized) {
			System.out.println("------------" + order + "-------------");
			s.setInt(1, questionId);			
			int cidWithTopScoreAndLowerCidNumber = -1;
			ResultSet r = s.executeQuery();
			if(r.next()) {
				cidWithTopScoreAndLowerCidNumber = r.getInt(1);
			}
			r.close();
			
			insert.setInt(1, questionId);
			insert.setInt(2, order); // cidOrder
			insert.setInt(3, cidWithTopScoreAndLowerCidNumber);
			insert.executeUpdate();
			order += 1;
		}
		insert.close();
		s.close();
	}

	public static List<Integer> getThreadsForStudyRandomized(Connection conn)
		throws SQLException, IOException {
		List<Integer> questionIds = new ArrayList<Integer>();
		PreparedStatement s = conn.prepareStatement(
				" SELECT questionId "
				+ " FROM codeFragmentParsingAttributes A, stackOverflowMetaData M "
				+ " WHERE A.cid=M.cid AND A.comment LIKE 'Java%' "
				+ " GROUP BY questionId "
				+ " HAVING max(score)>0"
				+ " ORDER BY RANDOM()");
		
		ResultSet r = s.executeQuery();
		while( r.next() ) {
			questionIds.add(r.getInt(1));
		}
		
		s.close();	
		r.close();
		return questionIds;
	}

	public static void populateTaskAssignments(Connection conn, File participantListFile) 
			throws SQLException, IOException {
				
		Map<String,HibernateOrSpringOrBoth> userNameToWhich = 
				getUserToHibernateOrSpringOrBoth(participantListFile);
		
		List<String> sortedUsers = new ArrayList<>(userNameToWhich.keySet());
		Collections.sort(sortedUsers);
		
		for( String userName : sortedUsers ) {
			HibernateOrSpringOrBoth which = userNameToWhich.get(userName);			
			populateTaskAssignments(conn, userName, which, "");
		}
	}
	
	public static void copyTaskAssignments(Connection conn, 
			String copiedTo, String copiedFrom, String comment) 
			throws SQLException, IOException {

		System.out.println(copiedTo + "<-" + copiedFrom);
		PreparedStatement insert = conn
				.prepareStatement(
			"INSERT INTO studyTaskAssignments (user,taskNumber, cid,"
					+ "      cidOrder, size, hibernateOrSpring, comment)  " 
					+ " SELECT ?, taskNumber, cid, "
					+ "      cidOrder, size, hibernateOrSpring, ? "
					+ " FROM studyTaskAssignments "
					+ " WHERE user LIKE ? ");
		insert.setString(1, copiedTo);
		insert.setString(2, comment);
		insert.setString(3,  copiedFrom);
		insert.executeUpdate();		
		insert.close();
		
		conn.commit();	
		
		populateRatings(conn, copiedTo);
		
		conn.commit();	
	}
	
	
	private static void populateTasks(Connection conn) 
			throws SQLException, IOException {

		PreparedStatement insert = conn
				.prepareStatement(
						"INSERT INTO studyTaskAssignments (user,taskNumber, cid,"
						+ "      cidOrder, size, hibernateOrSpring, comment)  " 
						+ " SELECT 'NOT ASSIGNED', -1, M.cid, "
						+ "      R.cidOrder, ?, ?, 'NO COMMENT' "
						+ " FROM studyRandomizedCodeFragments R, stackOverflowMetaData M "
						+ " WHERE R.cid=M.cid AND M.hibernateOrSpring LIKE ? "
						+ " ORDER BY R.cidOrder ");
		
		Size[] sizes = Size.values();
		String[] hibernateOrSpring = new String[] {"hibernate","spring"};
		for (Size size : sizes) {
			for( String which : hibernateOrSpring) {
				int index=1;
				insert.setString(index, size.name());index+=1;
				insert.setString(index, which);index+=1;
				insert.setString(index, which);index+=1;
				insert.executeUpdate();
			}		
		}
		insert.close();
		conn.commit();
	}
	
	public static void populateTaskAssignments(Connection conn, String user, 
			HibernateOrSpringOrBoth which, String comment) 
			throws SQLException, IOException {
		
		int currentTaskNumber = 1;
		Size[] sizes = Size.values();
		for( Size size : sizes ) {
			
			if( which == HibernateOrSpringOrBoth.both) {
				int numberOfTaskPartitions = 13;
				
				populateTaskAssignments(conn, user, size, HibernateOrSpringOrBoth.hibernate.name(),
						currentTaskNumber, numberOfTaskPartitions, comment);
				currentTaskNumber += numberOfTaskPartitions;
				
				populateTaskAssignments(conn, user, size, HibernateOrSpringOrBoth.spring.name(),
						currentTaskNumber, numberOfTaskPartitions, comment);
				currentTaskNumber += numberOfTaskPartitions;
	
			} else if(which == HibernateOrSpringOrBoth.hibernate 
					|| which == HibernateOrSpringOrBoth.spring ) {
				int numberOfTaskPartitions = 26;
				
				populateTaskAssignments(conn, user, size, which.name(),
						currentTaskNumber, numberOfTaskPartitions, comment);
				currentTaskNumber += numberOfTaskPartitions;
			}
		}
		
		populateRatings(conn, user);	
	}
	
	public static void populateTaskAssignments(Connection conn, String user, 
			Size size, String hibernateOrSpring,
			int currentTaskNumber, int numberOfTasks, String comment)
			throws SQLException, IOException {
		
		System.out.println(user + " " + size.name() + ", " + hibernateOrSpring + ": " 
		+ currentTaskNumber + "-" + (currentTaskNumber+numberOfTasks-1) );
		
		List<Integer> nextCids = getNextTaskCids(conn, hibernateOrSpring, 
				size, numberOfTasks, user);
		
		int t = currentTaskNumber;
		PreparedStatement update = conn.prepareStatement(
				"UPDATE studyTaskAssignments "
				+ " SET taskNumber=?, user=?"
				+ " WHERE cid=? AND hibernateOrSpring LIKE ? AND size LIKE ?");
		
		for( Integer cid : nextCids ) {
			int index=1;

			update.setInt(index, t);index+=1;
			update.setString(index, user);index+=1;
			update.setInt(index, cid);index+=1;
			update.setString(index, hibernateOrSpring);index+=1;
			update.setString(index, size.name());index+=1;
			update.executeUpdate();
			t+=1;
		}
		update.close();
		
		conn.commit();
	}
		
	private static Map<String,HibernateOrSpringOrBoth> getUserToHibernateOrSpringOrBoth(
			File participantListFile) throws IOException {
		Map<String,HibernateOrSpringOrBoth> result = new HashMap<>();
		
		List<String> lines = FileUtils.readLines(participantListFile);
		lines.remove(0); // remove header
		
		for( String line : lines ) {
			String[] fields =  line.trim().split("\t");
			System.out.println(line);
			if( fields.length >= 3) {
				String participantNumber = fields[0];
				participantNumber = participantNumber.length() == 1 ? "0"+participantNumber : participantNumber;
				String name = fields[1];
				String userName = "P" + participantNumber + "." + name.replace(" ",".");
				HibernateOrSpringOrBoth which = HibernateOrSpringOrBoth.valueOf(fields[2].toLowerCase());
				
				result.put(userName, which);
			}
		}
		return result;
	}
	
	private static int getLargestTaskNumber(Connection conn, String user)
			throws SQLException, IOException {
		
		int largestTaskNumber = -1;
		PreparedStatement s = conn.prepareStatement(
				" SELECT max(A.taskNumber) "
				+ " FROM studyTaskAssignments A "
				+ " WHERE user LIKE ? " );
		s.setString(1, user);
		
		ResultSet r = s.executeQuery();
		if(r.next()) {
			largestTaskNumber = r.getInt(1);
		}
		
		r.close();
		s.close();
		return largestTaskNumber;
	}
	
	private static List<Integer> getTaskNumbers(Connection conn, String user)
			throws SQLException, IOException {
		
		List<Integer> taskNumbers = new ArrayList<Integer>();
		PreparedStatement s = conn.prepareStatement(
				" SELECT A.taskNumber "
				+ " FROM studyTaskAssignments A "
				+ " WHERE user LIKE ? " );
		s.setString(1, user);
		
		ResultSet r = s.executeQuery();
		while(r.next()) {
			taskNumbers.add(r.getInt(1));
		}
		
		r.close();
		s.close();
		return taskNumbers;
	}
		
	
	
	
	public static List<Integer> getSortedCidsForStudy(Connection conn) throws SQLException, IOException {
		List<Integer> hibernateCids = 
				DatabasePopulateStudy.getSortedCids(conn, "hibernate", 250);

		List<Integer> springCids = 
				DatabasePopulateStudy.getSortedCids(conn, "spring", 250);
		
		List<Integer> together = new ArrayList<Integer>(500);
		together.addAll(hibernateCids);
		together.addAll(springCids);
		return together;
	}
	
	public static void printCidForCurrentStudy(Connection conn) throws SQLException, IOException {
		List<Integer> studyCid = getSortedCidsForStudy(conn);
		String content = "";
		for( Integer cid : studyCid ) {
			content += cid + "\n";
		}
		FileUtils.write(new File("./database/current-study-cids.csv"), content);
	}
	
	public static List<Integer> getSortedCids(Connection conn, 
			String hibernateOrSpring, int topHowMany)
			throws SQLException, IOException {
		
		List<Integer> cids = new ArrayList<Integer>(topHowMany);
		
		PreparedStatement s = conn.prepareStatement(
				" SELECT R.cid, M.hibernateOrSpring"
				+ " FROM studyRandomizedCodeFragments R, stackOverflowMetaData M"
				+ " WHERE R.cid=M.cid AND M.hibernateOrSpring LIKE ? "
				+ " ORDER BY R.cidOrder ASC "
				+ " LIMIT ?");
		
		s.setString(1, hibernateOrSpring);
		s.setInt(2, topHowMany);
		
		ResultSet r = s.executeQuery();
		while(r.next()) {
			cids.add(r.getInt(1));
		}
		
		r.close();
		s.close();
		return cids;
	}
	
	
	private static int getSmallerestCidOrder(Connection conn, String hibernateOrSpring)
			throws SQLException, IOException {
		
		int minCid = 1;
		
		PreparedStatement s = conn.prepareStatement(
				" SELECT min(cidOrder) "
				+ " FROM studyTaskAssignments A, studyRandomizedCodeFragments R, stackOverflowMetaData M "
				+ " WHERE A.cid=R.cid AND R.cid=M.cid "
				+ " AND M.hibernateOrSpring LIKE ?" );
		
		s.setString(1, hibernateOrSpring);
		
		ResultSet r = s.executeQuery();
		if(r.next()) {
			minCid = r.getInt(1);
		}
		
		r.close();
		s.close();
		return minCid;
	}	
	
	public static List<Integer> getNextTaskCids(Connection conn, 
			String hibernateOrSpring, Size size, int numberOfTasks, String user) 
					throws SQLException, IOException {
		List<Integer> cids = new ArrayList<Integer>();
		PreparedStatement s = null;
		if( size == Size.Small) {
			s = conn.prepareStatement(
				"SELECT A.cid "
				+ " FROM studyTaskAssignments A "
				+ " WHERE A.hibernateOrSpring LIKE ? AND A.size LIKE 'Small' "
				+ "       AND user LIKE 'NOT ASSIGNED' "
				+ " ORDER BY A.cidOrder "
				+ " LIMIT ? ");

			s.setString(1, hibernateOrSpring);
			s.setInt(2, numberOfTasks);
		} else if( size == Size.Big) {
			s = conn.prepareStatement(
					"SELECT A.cid "
						+ " FROM studyTaskAssignments A "
						+ " WHERE A.hibernateOrSpring LIKE ? AND A.size LIKE 'Big' "
						+ "       AND user LIKE 'NOT ASSIGNED' "
						+ "       AND A.cid NOT IN (SELECT cid "
						+ "							FROM studyTaskAssignments"
						+ "							WHERE size LIKE 'Small' AND user LIKE ?)"
						
						+ " ORDER BY A.cidOrder "
						+ " LIMIT ? ");

			s.setString(1, hibernateOrSpring);
			s.setString(2, user);
			s.setInt(3, numberOfTasks);
		}
		
		ResultSet r = s.executeQuery();
		
		while(r.next()) {
			int cid = r.getInt(1);
			cids.add(cid);
		}
		
		r.close();
		s.close();
		return cids;		
	}
	
//	private static List<Integer> getNextCidsToSummarize(Connection conn, 
//			String hibernateOrSpring, int minimum, int topHowMany)
//			throws SQLException, IOException {
//		
//		List<Integer> cids = new ArrayList<Integer>(topHowMany);
//		
//		PreparedStatement s = conn.prepareStatement(
//				" SELECT R.cid, M.hibernateOrSpring"
//				+ " FROM studyRandomizedCodeFragments R, stackOverflowMetaData M"
//				+ " WHERE R.cid=M.cid AND M.hibernateOrSpring LIKE ? "
//				+ " ORDER BY R.cidOrder ASC "
//				+ " LIMIT ?");
//		
//		s.setString(1, hibernateOrSpring);
//		s.setInt(2, topHowMany + minimum -1);
//		
//		ResultSet r = s.executeQuery();
//		int i=minimum;
//		while(r.next()) {
//			if( i >= minimum) {
//				cids.add(r.getInt(1));
//			}
//			i+=1;
//		}
//		
//		r.close();
//		s.close();
//		return cids;
//	}
	
	private static List<Integer> getCidsByUser(Connection conn, String user)
			throws SQLException, IOException {
		
		List<Integer> cids = new ArrayList<Integer>();
		
		PreparedStatement s = conn.prepareStatement(
				" SELECT C.cid"
				+ " FROM studyTaskAssignments A, studyRandomizedCodeFragments C "
				+ " WHERE user LIKE ? AND A.cid=C.cid "
				+ " ORDER BY cidOrder " );
		s.setString(1, user);
		
		ResultSet r = s.executeQuery();
		while(r.next()) {
			cids.add(r.getInt(1));
		}
		
		r.close();
		s.close();
		return cids;
	}
	
	private static void populateRatings(Connection conn, String user) 
			throws SQLException, IOException {		
		
		PreparedStatement insert = conn
				.prepareStatement(
						"INSERT INTO studyRatings VALUES (?,?,?,?,?,?,?,?) ");			
		
		List<Integer> taskNumbers = getTaskNumbers(conn, user);
		
		for( Integer taskNumber : taskNumbers ) {

			System.out.println("Ratings table: " + user + ", " + taskNumber.toString());
			
			List<HowToSummarize> how = HowToSummarize.chooseOrder();
			
			int index=1;
			insert.setString(index, user); index+=1;
			insert.setInt(index, taskNumber); index+=1;
			insert.setString(index, how.get(0).name()); index+=1;
			insert.setString(index, how.get(1).name()); index+=1;
			insert.setString(index, how.get(2).name()); index+=1;
			insert.setString(index, "not rated yet"); index+=1;
			insert.setString(index, "not rated yet"); index+=1;
			insert.setString(index, "not rated yet"); index+=1;
			insert.executeUpdate();
		}

		insert.close();
	}

	private static void cleanP9(Connection conn) 
			throws SQLException, IOException {		
		
		PreparedStatement delete = conn
				.prepareStatement(
						"DELETE FROM studyTaskAssignments "
						+ " WHERE cid=? AND user='P09.Yinuo.Li'");	
		
		List<String> cidsToKeep = FileUtils.readLines(new File("./database/study/fix-p9/keep.csv"));
		List<String> cidsAll = FileUtils.readLines(new File("./database/study/fix-p9/keep+extra.csv"));

		for( String cidFromAll : cidsAll ) {
			if( !cidsToKeep.contains(cidFromAll)) {
				int cid = Integer.parseInt(cidFromAll);
				delete.setInt(1, cid);
				delete.executeUpdate();
			}
		}
		
		delete.close();
	}
	
	private static void unassign(Connection conn, String user) 
			throws SQLException, IOException {		
		
		PreparedStatement update = conn.prepareStatement(
				"UPDATE studyTaskAssignments "
				+ " SET user='NOT ASSIGNED'"
				+ " WHERE user LIKE ? ");
		
		update.setString(1, user);
		update.executeUpdate();
		
		update.close();
	}
			
	
}
