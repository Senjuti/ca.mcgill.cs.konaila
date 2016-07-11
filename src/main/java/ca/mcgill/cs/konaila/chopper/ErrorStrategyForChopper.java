package ca.mcgill.cs.konaila.chopper;

import java.io.IOException;
import java.sql.SQLException;

import ca.mcgill.cs.konaila.AbstractErrorStrategy;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;

public class ErrorStrategyForChopper extends AbstractErrorStrategy {

	public ErrorStrategyForChopper(int cid, String source, String code, boolean useDatabase, int numberOfPriorTrials) {
		super(cid,source,code,useDatabase,numberOfPriorTrials);
	}
	
	@Override
	public void reparse(String newCode) {
		try { 
			DatabaseGetCodeFragments.updateCode(Database.getInstance().getConnection(),
				cid, newCode);
		} catch ( IOException e ) {
			e.printStackTrace();
			throw new RuntimeException();
		} catch ( SQLException e ) {
			e.printStackTrace();
			throw new RuntimeException();
		}
		
		Chopper.parseAndChopFile(cid, source, newCode, useDatabase, numberOfPriorTrials + 1);

	}

}
