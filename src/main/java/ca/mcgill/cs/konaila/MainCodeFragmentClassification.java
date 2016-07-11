package ca.mcgill.cs.konaila;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.selection.categorization.DatabaseCodeFragmentCategory;

public class MainCodeFragmentClassification {

	public static final String DB = Main.DB;
	public static final String dir = "code-fragment-categorization/";
	public static final String file = dir + "code-fragments-categorization-labels.csv";
	
	public static void main(String[] args) throws Exception {
		Database.getInstance().initConnection(MainCodeFragmentClassification.DB);
		
		Connection conn = Database.getInstance().getConnection();
		writeToOutput(conn);
		
		Database.getInstance().closeConnection();
	}

	public static void writeToOutput(Connection conn) throws SQLException, IOException {

		String output = "cid\tlabel\tcorrect?\tcertainty\tcategory\tcategory\tcategory\n";
		
		List<String> lines = FileUtils.readLines(new File(file));
		lines.remove(0);
		
		for( String line : lines ) {
			String[] tokens = line.split("\t");
			int cid = Integer.parseInt(tokens[0]);
			String label = tokens[1].replace("\"", ""); 
			
			System.out.println("code fragment " + cid);
			
			int max = DatabaseCodeFragmentCategory.selectMaxCertainty(conn,cid);
			List<String> topCategories = DatabaseCodeFragmentCategory.selectTopCategories(conn, max, cid);

			output += cid + "\t" + label + "\t" + (topCategories.contains(label)?1:0) + "\t" + max + "\t";
			for( String c : topCategories ) {
				output += c + "\t";
			}
			output += "\n";

		}
		
		FileUtils.write(new File(dir + "output.csv"), output);
	}	
}
