package ca.mcgill.cs.konaila.chopper;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;


public class ParsingStats {

	static final String STATS_FILE =  "./stats/chopper/parsing-stats.csv";
	static final String STATS_FILE2 =  "./stats/chopper/parsing-stats-short-ok-noTree.csv";
	static final String DELIM = "\t";
	
	static {
		try { 
			FileUtils.write(new File(STATS_FILE), 
					"file\tcode\tcid\tindex\toffending.token\tstate\tcontext.state\tjustification\n", false);
			FileUtils.write(new File(STATS_FILE2), 
					"file\tjustification\n", false);

		} catch(Exception e) {
			
		}
	}
	
	public static void write(String file, String code, int cid, 
			int index, String offendingToken, 
			int state, String contextState, String justification,
			boolean useDatabase) {
		String dbJustification = null;
		try {

			
			dbJustification = DatabaseChopperStats.getJustification(Database.getInstance().getConnection(), cid);
			if( dbJustification != null ) {
				if( dbJustification.equals(justification) ) {
					// justification already inputted
				} else {
					throw new RuntimeException("Confliciting values for parsing justification");
				}
			} else {
				if( useDatabase)
					DatabaseChopperStats.writeToDb(Database.getInstance().getConnection(), 
						cid, justification);
			
			}
		} catch (IOException e) {
			e.printStackTrace();
			new RuntimeException(e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public static void writeToFile(String file, String code, int cid, 
			int index, String offendingToken, 
			int state, String contextState, String justification) {
		try {
			String line = file + DELIM + code.split("\n")[0] + DELIM + cid + 
					DELIM + index + DELIM + offendingToken + DELIM
					+ state + DELIM + contextState + DELIM + justification + "\n";
			FileUtils.write(new File(STATS_FILE), line, true);
		} catch (IOException e) {
			e.printStackTrace();
			new RuntimeException(e);
		}
	}
	
	public static void writeToFile(int cid, String justification) {
		try {
			String line = cid + DELIM + justification + "\n";
			FileUtils.write(new File(STATS_FILE2), line, true);
		} catch (IOException e) {
			e.printStackTrace();
			new RuntimeException(e);
		}
	}
}
