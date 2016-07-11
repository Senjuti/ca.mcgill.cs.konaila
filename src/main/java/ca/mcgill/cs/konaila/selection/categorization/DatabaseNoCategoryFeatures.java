package ca.mcgill.cs.konaila.selection.categorization;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class DatabaseNoCategoryFeatures {
	
	public static Map<Integer,NoCategorySelectionUnit> getTopFeaturesIgnoringCategory(Connection conn, int cid)  throws SQLException, IOException {
		Map<Integer,NoCategorySelectionUnit> map = new HashMap<Integer,NoCategorySelectionUnit>();

		PreparedStatement s = conn
				.prepareStatement(
						"SELECT uid, sum(individualConfigurationScore), F.feature "
						+ " FROM features F, featureType T "
						+ " WHERE F.cid=? AND F.feature=T.feature "
						+ " GROUP BY F.cid, F.uid "
						+ " ORDER BY sum(individualConfigurationScore) DESC");
		
		s.setInt(1, cid);
		
		ResultSet r = s.executeQuery();
		while ( r.next() ) {
			int uid = r.getInt(1);
			int score = r.getInt(2);
			String feature = r.getString(3);
			if( score > 0) { 
				map.put(uid, new NoCategorySelectionUnit(cid,uid,feature, score));
			}
		}

		r.close();
		s.close();
		return map;
	}

	public static class NoCategorySelectionUnit extends ScoringSelectionUnitImpl {
		
		private String individualFeature = "";
		static Category category = Category.NoCategories;
		 
		public NoCategorySelectionUnit(int cid, int uid, String feature, int score) {
			super(cid,uid);
			this.individualFeature = feature;
			setScore(score);
		}

		public Category getCategory () { return category; }
		
		public String getFeature() { return individualFeature; }
	}
}
