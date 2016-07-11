package ca.mcgill.cs.konaila;
/*
[The "BSD license"]
Copyright (c) 2013 Terence Parr
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:

1. Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.
3. The name of the author may not be used to endorse or promote products
derived from this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.chopper.Chopper;
import ca.mcgill.cs.konaila.chopper.ParsingStats;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseChopperStats;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.parse.CodeFragmentPrerequisite;
import ca.mcgill.cs.konaila.selection.features.PropertyExtractor;

class Driver {
	
	public final static Driver INSTANCE = new Driver();
	static boolean fileInput = false;
	static boolean useDatabase = false;
	static boolean rebuildDatabase = false;
	static boolean populateAndroid=false;
	static boolean populateEclipse=false;
	static boolean populateStackOverflow = false;
	static boolean analyzeAndroid=false;
	static boolean analyzeEclipse=false;
	static boolean analyzeStackOverflow=false;
	static boolean summarize=false;
	static boolean parseOnlyStackOverflow=false;
	static boolean consolidate=false;
	
	public void doAll(String[] args) {
		List<String> inputFiles = new ArrayList<String>();
		long start = System.currentTimeMillis();
		try {
			if (args.length > 0 ) {
				// for each directory/file specified on the command line
				for(int i=0; i< args.length;i++) {	
					if ( args[i].equals("-fileInput") ) fileInput = true;
					else if ( args[i].equals("-useDatabase")) useDatabase = true;
					else if ( args[i].equals("-rebuildDatabase")) rebuildDatabase = true;
					else if ( args[i].equals("-populateAndroid")) populateAndroid = true;
					else if ( args[i].equals("-populateEclipse")) populateEclipse = true;
					else if ( args[i].equals("-populateStackOverflow")) populateStackOverflow = true;
					else if ( args[i].equals("-analyzeAndroid")) analyzeAndroid = true;
					else if ( args[i].equals("-analyzeEclipse")) analyzeEclipse = true;
					else if ( args[i].equals("-analyzeStackOverflow")) analyzeStackOverflow = true;
					else if ( args[i].equals("-summarize")) summarize = true;
					else if ( args[i].equals("-parseOnlyStackOverflow")) parseOnlyStackOverflow = true;
					else if ( args[i].equals("-consolidate")) consolidate = true;

					if ( args[i].charAt(0)!='-' ) { // input file name
						inputFiles.add(args[i]);
					}
				}
				
				if( rebuildDatabase ) 
					Database.getInstance().rebuild();				
				if( useDatabase && populateAndroid ) 
					Database.getInstance().populateAndroid();
				if( useDatabase && populateEclipse ) 
					Database.getInstance().populateEclipse();
				if( useDatabase && populateStackOverflow ) 
					Database.getInstance().populateStackOverflow();
				
				if( fileInput ) {
					doFiles(inputFiles);
				} else if( useDatabase ){
					if( analyzeAndroid ) 
						doCodeFragmentsFromDatabase("android-guide");
					if( analyzeEclipse ) 
						doCodeFragmentsFromDatabase("eclipse-faq");
					if( analyzeStackOverflow ) 
						doCodeFragmentsFromDatabase("stackoverflow");
					if( parseOnlyStackOverflow)
						doLightParsingCodeFragmentsFromDatabase("stackoverflow");
				}
				
				if( useDatabase &&
						(analyzeAndroid || analyzeEclipse || analyzeStackOverflow
								|| summarize || consolidate)) {
					
					if( consolidate ) {
						Database.getInstance().consolidateFeatures();
					}
					
					if( analyzeEclipse) {
//						Database.getInstance().createTrainingData();
//						Database.getInstance().populatePredictions();
					}

					if( summarize ) {
						Database.getInstance().populateCodeFragmentCategorization();
						Database.getInstance().populateConfigurationBasedPredictions();
						Database.getInstance().summarize();   
					}
				}
					
			}
			else {
				System.err.println("Usage: java Main <directory or file name>");
			}
		}
		catch(Exception e) {
			System.err.println("exception: "+e);
			e.printStackTrace(System.err); // so we can get stack trace
		}
		long stop = System.currentTimeMillis();
		System.gc();
	}

	public void doLightParsingCodeFragmentsFromDatabase(String source) throws Exception {
		Map<Integer,String> cidToCode = Database.getInstance().getCodeFragments(source);
		
		if( useDatabase)
			Database.getInstance().getConnection().setAutoCommit(false);					
		int i = 0;
		
		for (Entry<Integer,String> e : cidToCode.entrySet()) {

			int cid = e.getKey();
			String code = e.getValue();
						
			if( CodeFragmentPrerequisite.isLongEnough(code)) {				
								
				Chopper.parseFile(cid, source, code, useDatabase, 0);
				
				if( i % 200 == 0 ) {
					Database.getInstance().getConnection().commit();
				}
			} else {
				if( useDatabase)
					Database.getInstance().writeTooShort(cid);
				ParsingStats.writeToFile(cid, "original fragment too short");
			}
			i+=1;
		}
		
		if( useDatabase)
			Database.getInstance().getConnection().commit();
	}
	
	public void doChoppingCodeFragmentsFromDatabase(String source) throws Exception {
		List<Integer> sortedCids = Database.getInstance().getStudyCids();
		
		Database.getInstance().getConnection().setAutoCommit(false);					
		int i = 0;
		
		for (Integer cid : sortedCids ) {

			String code = Database.getInstance().getCodeFragment(cid);
			String query = Database.getInstance().getQuery(cid);
						
			if( CodeFragmentPrerequisite.isLongEnough(code)) {				
								
				Chopper.chopFile(cid, source, code, useDatabase);
				PropertyExtractor.addFeatures(cid, source, code, query, useDatabase, 0);
				
				if( i % 200 == 0 ) {
					Database.getInstance().getConnection().commit();
				}
			} else {
				Database.getInstance().writeTooShort(cid);
			}
			i+=1;
		}

		DatabaseChopperStats.populateStats(Database.getInstance().getConnection(), source);
		
		Database.getInstance().getConnection().commit();
	}
	
	public void doCodeFragmentsFromDatabase(String source) throws Exception {
		Map<Integer,String> cidToCode = Database.getInstance().getCodeFragments(source);
		Map<Integer,String> cidToQuery = Database.getInstance().getQueries(source);
		
		Database.getInstance().getConnection().setAutoCommit(false);					
		int i = 0;
		
		for (Entry<Integer,String> e : cidToCode.entrySet()) {

			int cid = e.getKey();
			String code = e.getValue();
			String query = cidToQuery.get(cid);
						
			if( CodeFragmentPrerequisite.isLongEnough(code)) {				
								
				Chopper.parseAndChopFile(cid, source, code, useDatabase, 0);
				PropertyExtractor.addFeatures(cid, source, code, query, useDatabase, 0);
				
				if( i % 200 == 0 ) {
					Database.getInstance().getConnection().commit();
				}
			} else {
				Database.getInstance().writeTooShort(cid);
			}
			i+=1;
		}

		DatabaseChopperStats.populateStats(Database.getInstance().getConnection(), source);
		
		Database.getInstance().getConnection().commit();
	}
	
	
	public void doFiles(List<String> inputFiles) throws Exception {
		
		List<String> javaFiles = new ArrayList<String>();
		for (String fileName : inputFiles) {
			List<String> files = getFilenames(new File(fileName));
			javaFiles.addAll(files);
		}		
		
		long parserStart = System.currentTimeMillis();
		// lexerTime = 0;
		
		for (String f : javaFiles) {	

			String filename = f.substring(f.lastIndexOf('/')+"/".length());
			int internalId = Integer.parseInt(filename.substring("J".length(), filename.length()-".java".length()));
			
			File file = new File(f);
			String code = FileUtils.readFileToString(file).trim();			

			if( CodeFragmentPrerequisite.isLongEnough(code)) {
				Chopper.parseAndChopFile(internalId, "android-guide", code, useDatabase, 0);
				PropertyExtractor.addFeatures(internalId, "android-guide", code, "", useDatabase, 0); // empty query
			}
		}
		long parserStop = System.currentTimeMillis();
		System.out.println("Total lexer+parser time " + (parserStop - parserStart) + "ms.");
	}
	
	
	private List<String> getFilenames(File f) throws Exception {
		List<String> files = new ArrayList<String>();
		getFilenames_(f, files);
		return files;
	}

	private void getFilenames_(File f, List<String> files) throws Exception {
		// If this is a directory, walk each file/dir in that directory
		if (f.isDirectory()) {
			String flist[] = f.list();
			for(int i=0; i < flist.length; i++) {
				getFilenames_(new File(f, flist[i]), files);
			}
		}

		// otherwise, if this is a java file, parse it!
		else if ( ((f.getName().length()>5) &&
				f.getName().substring(f.getName().length()-5).equals(".java")) ) {
			files.add(f.getAbsolutePath());
		}
	}
	
	String cleanEmptyLines(String text) {
		String result = "";
		String[] lines = text.split("\n");
		for( String line : lines ) {
			if( !line.trim().equals("")) { 
				result += line + "\n";
			}
		}
		return result;
	}
	

}