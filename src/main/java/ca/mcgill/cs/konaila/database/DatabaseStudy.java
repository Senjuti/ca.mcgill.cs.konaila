package ca.mcgill.cs.konaila.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import ca.mcgill.cs.konaila.MainCodeFragmentClassification;
import ca.mcgill.cs.konaila.summarize.Size;

public class DatabaseStudy {
	
	static final File sqlLogs = new File("./database/logs/ratings.log");
	static final SimpleDateFormat f = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss.SSS z");
	
	public static void main(String[] args) throws Exception {
		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();
		
		Database.getInstance().closeConnection();
	}
	

	
	public static StudyTask getTask(Connection conn, String user, int i)
			throws SQLException, IOException {
		
		StudyTask task = null;
		
		PreparedStatement s = conn.prepareStatement(
				" SELECT T.taskNumber, T.cid, S1.summary, S2.summary, S3.summary, T.size "
				+ " FROM studyTaskAssignments T, summaries S1, summaries S2, summaries S3, studyRatings R"
				+ " WHERE T.cid=S1.cid AND T.cid=S2.cid AND T.cid=S3.cid "
				+ "     AND S1.cid=S2.cid AND S1.cid=S3.cid AND S2.cid=S3.cid "
				+ "     AND T.user LIKE ? "
				+ "     AND T.taskNumber = ? "
				+ "     AND R.summary1=S1.how AND R.summary2=S2.how AND R.summary3=S3.how"
				+ "     AND S1.size=T.size AND S2.size=T.size AND S3.size=T.size"
				+ "     AND R.taskNumber=T.taskNumber");
		s.setString(1, user);
		s.setInt(2,  i);
		
		ResultSet r = s.executeQuery();
		if(r.next()) {
			int taskNumber = r.getInt(1);
			int cid = r.getInt(2);
			String summary1 = r.getString(3);
			String summary2 = r.getString(4);
			String summary3 = r.getString(5);
			String sizeString = r.getString(6);
			Size size = Size.valueOf(sizeString);
			
			task = new StudyTask(cid, taskNumber, size, summary1, summary2,
					summary3);
		}
		
		r.close();
		s.close();
		return task;
	}
	
	public static boolean checkUserExists(Connection conn, String user)
			throws SQLException, IOException {
		
		boolean userExists = false;
		
		PreparedStatement s = conn.prepareStatement(
				" SELECT DISTINCT user "
				+ " FROM studyTaskAssignments T"
				+ " WHERE T.user LIKE ? ");
		s.setString(1, user);
		ResultSet r = s.executeQuery();
		
		if( r.next() ) {
			userExists=true;
		}
		
		r.close();
		s.close();
		return userExists;
	}
	
	
	public static void saveRatings(Connection conn, String user, int i, 
			String summaryAnswer1, String summaryAnswer2, String summaryAnswer3)
			throws SQLException, IOException {
		
		String sql = "UPDATE studyRatings "
			+ " SET summaryAnswer1=?, summaryAnswer2=?, summaryAnswer3=? "
			+ " WHERE taskNumber=? AND user=?";				
        PreparedStatement u = conn.prepareStatement(sql);
        
        u.setString(1,summaryAnswer1);
        u.setString(2,summaryAnswer2);
        u.setString(3,summaryAnswer3);
        u.setInt(4, i);
        u.setString(5, user);

        u.executeUpdate();
        
        u.close();
        
        sql = sql.replace("summaryAnswer1=?","summaryAnswer1='"+summaryAnswer1+"'")
        		.replace("summaryAnswer2=?","summaryAnswer2='"+summaryAnswer2+"'")
        		.replace("summaryAnswer3=?","summaryAnswer3='"+summaryAnswer3+"'")
        		.replace("taskNumber=?","taskNumber="+i)
        		.replace("user=?","user='"+user+"'");
        
        String log = f.format(System.currentTimeMillis()) + "\t" + sql + "\n";
        FileUtils.write(sqlLogs, log, true);
	}

	public static String getHibernateOrSpring(Connection conn, int cid)
			throws IOException, SQLException{
		
		String hibernateOrSpring = "";
        PreparedStatement s = conn.prepareStatement(
						"SELECT hibernateOrSpring "
						+ " FROM stackOverflowMetaData"
						+ " WHERE cid = ? ");       
        
        s.setInt(1,cid);
        ResultSet r = s.executeQuery();
        if( r.next()){
        	hibernateOrSpring = r.getString(1);
        }
        r.close();        
		s.close();
		return hibernateOrSpring;
	}
	
	public static Pair<Integer,Integer> getNextCid(Connection conn,
			String hibernateOrSpring) 
					throws SQLException, IOException {
		int cid  = -1;
		int cidOrder = -1;
		PreparedStatement s = conn.prepareStatement(
				"SELECT A.cid, A.cidOrder "
				+ " FROM studyTaskAssignments A "
				+ " WHERE A.hibernateOrSpring LIKE ? "
				+ "       AND user LIKE 'NOT ASSIGNED' "
				+ " ORDER BY A.cidOrder "
				+ " LIMIT 1 ");

		s.setString(1, hibernateOrSpring);
		ResultSet r = s.executeQuery();
		if( r.next() ) {
			cid=r.getInt(1);
			cidOrder=r.getInt(2);
		}
		r.close();s.close();
		
		return new ImmutablePair<Integer,Integer>(cid,cidOrder);
	}
	
	public static class StudyTask {
		int cid;
		int taskNumber;
		String summary1;
		String summary2;
		String summary3;
		Size size;
		public StudyTask(int cid, int taskNumber, Size size,
				String summary1, String summary2, String summary3) {
			super();
			this.cid = cid;
			this.taskNumber = taskNumber;
			this.size = size;
			this.summary1 = summary1;
			this.summary2 = summary2;
			this.summary3 = summary3;
		}
		public int getCid() {
			return cid;
		}
		public int getTaskNumber() {
			return taskNumber;
		}
		public String getSummary1() {
			return summary1;
		}
		public String getSummary2() {
			return summary2;
		}
		public String getSummary3() {
			return summary3;
		}	
	}

}
