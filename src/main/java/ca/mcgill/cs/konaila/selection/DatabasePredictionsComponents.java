package ca.mcgill.cs.konaila.selection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import ca.mcgill.cs.konaila.selection.categorization.ScoringSelectionUnit;

public class DatabasePredictionsComponents {
	
	public enum FeatureSet {
		ConfigurationBased,
		ConfigurationBasedWithDataFlow
	}

	public static void populateCandidates(Connection conn, int cid, List<ScoringSelectionUnit> candidates)
			throws SQLException, IOException {
		populateIndividualScores(conn, cid, candidates);
		
	}
	
	public static void populateIndividualScores(Connection conn, int cid, List<ScoringSelectionUnit> candidates)
			throws SQLException, IOException {
		conn.setAutoCommit(false);
		PreparedStatement s = conn
				.prepareStatement(
						"INSERT INTO predictionComponents VALUES (?,?,?,?) ");
		
		for( ScoringSelectionUnit c : candidates) {			
			s.setInt(1, cid);
			s.setInt(2, c.getUid());
			s.setInt(3, c.getScore());
			s.setString(4, c.getCategory().name());
			s.executeUpdate();
		}

		s.close();
		
		conn.commit();		
	}
	
	public static void populateConsolidatedScores(Connection conn) throws IOException, SQLException {
		conn.setAutoCommit(false);
		PreparedStatement s = conn
				.prepareStatement(
				"INSERT INTO predictions (cid,uid,probabilityInSummary,featureSet) " + 
						" SELECT cid, uid, sum(score), '" + FeatureSet.ConfigurationBased.name() + "' "
						+ " FROM predictionComponents "
						+ " GROUP BY cid, uid");
		s.executeUpdate();

		s.close();
		conn.commit();		
	}
	
	public static void updateConsolidatedScores(Connection conn) throws IOException, SQLException {
		conn.setAutoCommit(false);
		
		PreparedStatement s = conn
				.prepareStatement(
				"UPDATE predictions SET probabilityInSummary="
				+ "( SELECT sum(score) " 
				+ " FROM predictionComponents C "
				+ " WHERE  C.cid = predictions.cid AND C.uid = predictions.uid"
				+ " GROUP BY C.cid, C.uid)");
		s.executeUpdate();

		s.close();
		conn.commit();		
	}
	
	public static void populateConsolidatedScoresDataFlow(Connection conn) throws IOException, SQLException {
		conn.setAutoCommit(false);
		PreparedStatement s = conn
				.prepareStatement(
				"INSERT INTO predictions (cid,uid,probabilityInSummary,featureSet) " + 
						" SELECT cid, uid, sum(score), '" + FeatureSet.ConfigurationBasedWithDataFlow.name() + "' "
						+ " FROM predictionComponents "
						+ " GROUP BY cid, uid");
		s.executeUpdate();

		s.close();
		conn.commit();		
	}
}
