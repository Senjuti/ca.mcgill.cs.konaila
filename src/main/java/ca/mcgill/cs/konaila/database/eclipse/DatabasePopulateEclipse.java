package ca.mcgill.cs.konaila.database.eclipse;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

import ca.mcgill.cs.konaila.database.Database;

public class DatabasePopulateEclipse {  

    public static final String dir = "./database/";
    public static final String eclipseDir = dir +"/eclipse-faq";
    public static final String eclipseQueries = dir + "/eclipse-queries.csv"; 
    public static final String DB = "jdbc:sqlite:database/codeFragments.sqlite";
	public static final int cidCounterStart = 1000;
	public static final String source = "eclipse-faq";
	public static final String parsingAttributeFile = dir + "/parsing-attributes.csv";

        
    public static void main(String[] args) throws Exception {		        
		Database db= Database.getInstance();
		db.initConnection(DB);
		populate(db.getConnection());
		db.closeConnection(); 
    }


    public static void populate(Connection conn) throws SQLException, IOException {
//      Map<Integer, String> sidToCode = DatabaseEclipseFromOldDatabase.getCodeFragments();
		Map<Integer, String> sidToCode = getCodeFragments();
		Map<Integer, String> sidToQueries = getQueries();         

        int maxCid = cidCounterStart;

		conn.setAutoCommit(false);
		populateCodeFragments(conn, sidToCode, sidToQueries, maxCid);
//		populateParsingAttributes(conn);    
		conn.commit();
    }
        
    public static void populateCodeFragments(Connection conn, Map<Integer, String> sidToCode, Map<Integer,String> sidToQuery, int maxCid) 
    		throws SQLException, IOException {

        PreparedStatement codeFragmentsStat = conn
            .prepareStatement("insert into codeFragments values(?,?,?,?,?,?);");
        
        try {	                
	        for ( Entry<Integer,String> e : sidToCode.entrySet() ) {
	            int sid = e.getKey();
	            String code = e.getValue();
	                        
	            System.out.println("Processing " + sid );                   
	
	            int cid = sid + cidCounterStart;
	            int internalCid = sid;
	            String source = "eclipse-faq";
	            String url = "";
	            String query = sidToQuery.get(sid);
	                        
	            codeFragmentsStat.setInt(1, cid);
	            codeFragmentsStat.setInt(2, internalCid);
	            codeFragmentsStat.setString(3, source);
	            codeFragmentsStat.setString(4, url);
	            codeFragmentsStat.setString(5, code);
	            codeFragmentsStat.setString(6, query);
	                        
	            codeFragmentsStat.executeUpdate();                               
	        }         
    	} finally {
    		codeFragmentsStat.close();
    	}
    }
        
    private static Map<Integer,String> getCodeFragments() throws IOException {
        Map<Integer,String> cidToCode = new HashMap<Integer,String>();

        Collection<File> files = FileUtils.listFiles(new File(eclipseDir), TrueFileFilter.INSTANCE,FalseFileFilter.INSTANCE);
        for( File file : files) {
            String filename = file.getName();
            int eclipseId = Integer.parseInt(filename.substring("E".length(), filename.length()-".java".length()));
            String code = FileUtils.readFileToString(file);
                        
            cidToCode.put(eclipseId, code);
        }
        return cidToCode;
    }    
    
    private static Map<Integer,String> getQueries() throws IOException {
        Map<Integer,String> cidToQuery = new HashMap<Integer,String>();

        List<String> queryLines = FileUtils.readLines(new File(eclipseQueries));
        queryLines.remove(0); // header
                
        for( String queryLine : queryLines ) {
        	String[] tokens = queryLine.split("\t");
        	int cid = Integer.parseInt(tokens[0]);
        	String query = tokens[1].replace("\"", "");
        	cidToQuery.put(cid,query);
        }
        return cidToQuery;
    }
    
    public static void populateParsingAttributes(Connection conn) 
    		throws SQLException, IOException {
        PreparedStatement s = conn
            .prepareStatement("INSERT INTO codeFragmentParsingAttributes values(?,?);");
        List<String> lines = FileUtils.readLines(new File(parsingAttributeFile));
        lines.remove(0);
        for( String line : lines ) {
        	String[] tokens = line.split("\t");
        	int cid = Integer.parseInt(tokens[0]);
        	String attribute = tokens[1].replace("\"","");
        	if( cid > 1000 ) { // Eclipse cids are above 1000
        		s.setInt(1,cid);
//        		s.setInt(2, cid-1000);
//        		s.setString(3,"structure");
        		s.setString(2,attribute);
        		s.executeUpdate();
        	}       	
        }
        s.close();
    }
}
