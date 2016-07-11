package ca.mcgill.cs.konaila.database.eclipse;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.database.Database;

public class DatabasePopulateEclipseGoldStandard {  

    private static final String dir = DatabasePopulateEclipse.dir;
    private static final String eclipseDir = dir+"/eclipse-faq";
    public static final String DB = "jdbc:sqlite:database/codeFragments.sqlite";
	public static final int cidCounterStart = DatabasePopulateEclipse.cidCounterStart;
	public static final String goldStandardFile = dir + "eclipse-query-call-frequency-features-gold-standard.csv";

        
    public static void main(String[] args) throws Exception {		        
		Database db= Database.getInstance();
		db.initConnection(DB);
		populate(db.getConnection());
		db.closeConnection(); 
    }


    public static void populate(Connection conn) throws SQLException, IOException {
		List<Row> rowObjects = getGoldStandard();            

        int maxCid = cidCounterStart;

		conn.setAutoCommit(false);
		populate(conn, rowObjects, maxCid);
		conn.commit();
    }
        
    public static void populate(Connection conn, List<Row> rowObjects, int maxCid) 
    		throws SQLException, IOException {

        PreparedStatement s = conn
            .prepareStatement("insert into eclipseGoldStandardLineBased values(?,?,?,?,?,?,?,?,?);");
        
        try {       
        	List<Row> rows = getGoldStandard();
        	
	        for( Row r : rows ) {	                        
	            System.out.println("Processing " + r.cid );                   
	
	            int cid = r.cid + cidCounterStart;
	                        
	            s.setInt(1, cid);
	            s.setInt(2, r.line);
	            s.setString(3, r.familiarity);
	            s.setInt(4, r.dfPackage);
	            s.setBoolean(5, r.inSummary);
	            s.setBoolean(6, r.mostTerms);
	            s.setBoolean(7, r.mostDiverseTerms);
	            s.setBoolean(8, r.declaredInCodeSnippet);
	            s.setBoolean(9, r.java);
	                        
	            s.executeUpdate();                               
	        }         
    	} finally {
    		s.close();
    	}
    }
        
    private static List<Row> getGoldStandard() throws IOException {
        List<Row> rowObjects = new ArrayList<Row>();
        String[] rows = FileUtils.readFileToString(new File(goldStandardFile)).split("\n");
        
        for(int i=1; // skip first lien
        		i < rows.length; i++ ) {
        	String row = rows[i];
        	String[] entries = row.split("\t");

        	Row r = new Row(Integer.parseInt(entries[1].replace("\"","")), 
        			Integer.parseInt(entries[2].replace("\"","")),
        			Boolean.parseBoolean(entries[3].replace("\"","")), // mostTerms
        			Boolean.parseBoolean(entries[4].replace("\"","")), // mostDiverseTerms
        			Boolean.parseBoolean(entries[5].replace("\"","")), // declaredInCodeSnippet
        			Boolean.parseBoolean(entries[6].replace("\"","")), // java        			
        			entries[7].replace("\"",""), // familiarity
        			Integer.parseInt(entries[8]), // dfPackage
        			Boolean.parseBoolean(entries[9].replace("\"","")) // inSummary
        			);
        	rowObjects.add(r);
        }
        return rowObjects;
    }
    
    public static class Row {
    	int cid;
    	int line;
    	String familiarity;
    	int dfPackage;
    	boolean inSummary;
    	boolean mostTerms;
    	boolean mostDiverseTerms;
    	boolean declaredInCodeSnippet;
    	boolean java;
    	
		public Row(int cid, int line, 
				boolean mostTerms, boolean mostDiverseTerms,
				boolean declaredInCodeSnippet, boolean java,
				String familiarity, int dfPackage, boolean inSummary) {
			super();
			this.cid = cid;
			this.line = line;
			this.familiarity = familiarity;
			this.dfPackage = dfPackage;
			this.inSummary = inSummary;
			this.mostTerms = mostTerms;
			this.mostDiverseTerms = mostDiverseTerms;
			this.declaredInCodeSnippet = declaredInCodeSnippet;
			this.java = java;
		}   	
    }
}
