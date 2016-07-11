package ca.mcgill.cs.konaila.database;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class DatabaseEclipsePopulatePredictions {
	
	public static void populatePredictions(Connection conn) throws SQLException, IOException {
		
		List<PredictionLineBasedRow> predictionRows = new ArrayList<PredictionLineBasedRow>();
		
		List<String> lines = FileUtils.readLines(new File("./training/predictions-icse2015-one-gold2-svm.csv"));
		lines.remove(0);
		for( String line : lines ) {
			String[] fields = line.split("\t");
			int cid = Integer.parseInt(fields[1]) + 1000;
			int lid = Integer.parseInt(fields[2]);  
			float probability = Float.parseFloat(fields[4]); 
			
			PredictionLineBasedRow r = new PredictionLineBasedRow(cid, lid, probability);
			predictionRows.add(r);
		}		
		
		populatePredictions(conn, predictionRows, "small");		
	}
	
	
	private static void populatePredictions(Connection conn, List<PredictionLineBasedRow> rows, 
			String featureSet) throws SQLException, IOException {
		
		PreparedStatement s = conn
				.prepareStatement("INSERT into eclipsePredictionsLineBased values(?,?,?,?);");
		
		
		for( PredictionLineBasedRow r : rows ) {
			System.out.println("cid " + r.cid + ", uid " + r.lid);
			s.setInt(1, r.cid);
			s.setInt(2, r.lid);
			s.setFloat(3, r.probabilityInSummary);
			s.setString(4, featureSet);
			
			s.executeUpdate();
		}
		
		s.close();
	}
	
	public static class PredictionLineBasedRow {
		int cid;
		int lid;
		float probabilityInSummary;
		
		public PredictionLineBasedRow(int cid, int lid, float probabilityInSummary) {
			super();
			this.cid = cid;
			this.lid = lid;
			this.probabilityInSummary = probabilityInSummary;
		} 		
	}
	
}
