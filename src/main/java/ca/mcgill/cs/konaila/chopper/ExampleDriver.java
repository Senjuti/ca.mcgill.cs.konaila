package ca.mcgill.cs.konaila.chopper;
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
import java.util.Collection;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.DiagnosticErrorListener;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.atn.LexerATNSimulator;
import org.antlr.v4.runtime.atn.PredictionMode;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.antlr.JavaFragmentLexer;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.selection.features.PropertyExtractingAstListener;
import ca.mcgill.cs.konaila.selection.features.Property;

/* This more or less duplicates the functionality of grun (TestRig) but it
 * has a few specific options for benchmarking like -x2 and -threaded.
 * It also allows directory names as commandline arguments. The simplest test is
 * for the current directory:

~/antlr/code/grammars-v4/java $ java Test .
/Users/parrt/antlr/code/grammars-v4/java/./JavaBaseListener.java
/Users/parrt/antlr/code/grammars-v4/java/./JavaFragmentLexer.java
/Users/parrt/antlr/code/grammars-v4/java/./JavaListener.java
/Users/parrt/antlr/code/grammars-v4/java/./JavaFragmentParser.java
/Users/parrt/antlr/code/grammars-v4/java/./Test.java
Total lexer+parser time 1867ms.

 */
class ExampleDriver {
	
	public final static boolean useDatabase = true;
	
	public final static ExampleDriver INSTANCE = new ExampleDriver();
		
	// public static long lexerTime = 0;
	public static boolean profile = false;
	public static boolean notree = false;
	public static boolean gui = false;
	public static boolean printTree = false;
	public static boolean SLL = false;
	public static boolean diag = false;
	public static boolean bail = false;
	public static boolean x2 = false;
	public static boolean threaded = false;
	public static boolean quiet = false;
	// public static long parserStart;
	// public static long parserStop;
	public static Worker[] workers = new Worker[3];
	static int windex = 0;

	public static CyclicBarrier barrier;

	public static volatile boolean firstPassDone = false;
	
	public static String output = "";
	public static String tableItems = "";

	public static class Worker implements Runnable {
		public long parserStart;
		public long parserStop;
		List<String> files;
		public Worker(List<String> files) {
			this.files = files;
		}
		@Override
		public void run() {
			parserStart = System.currentTimeMillis();
			for (String f : files) {
				INSTANCE.parseFile(f);
			}
			parserStop = System.currentTimeMillis();
			try {
				barrier.await();
			}
			catch (InterruptedException ex) {
				return;
			}
			catch (BrokenBarrierException ex) {
				return;
			}
		}
	}


	public void doAll(String[] args) {
		List<String> inputFiles = new ArrayList<String>();
		long start = System.currentTimeMillis();
		try {
			if (args.length > 0 ) {
				// for each directory/file specified on the command line
				for(int i=0; i< args.length;i++) {				
					if ( args[i].equals("-notree") ) notree = true;
					else if ( args[i].equals("-gui") ) gui = true;
					else if ( args[i].equals("-ptree") ) printTree = true;
					else if ( args[i].equals("-SLL") ) SLL = true;
					else if ( args[i].equals("-bail") ) bail = true;
					else if ( args[i].equals("-diag") ) diag = true;
					else if ( args[i].equals("-2x") ) x2 = true;
					else if ( args[i].equals("-threaded") ) threaded = true;
					else if ( args[i].equals("-quiet") ) quiet = true;
					if ( args[i].charAt(0)!='-' ) { // input file name
						inputFiles.add(args[i]);
					}
				}
				List<String> javaFiles = new ArrayList<String>();
				for (String fileName : inputFiles) {
					List<String> files = getFilenames(new File(fileName));
					javaFiles.addAll(files);
				}
				doFiles(javaFiles);
				
				Database.getInstance().consolidateFeatures();

				if ( x2 ) {
					System.gc();
					System.out.println("waiting for 1st pass");
					if ( threaded ) while ( !firstPassDone ) { } // spin
					System.out.println("2nd pass");
					doFiles(javaFiles);
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

	public void doFiles(List<String> files) throws Exception {
		long parserStart = System.currentTimeMillis();
		// lexerTime = 0;
		if ( threaded ) {
			barrier = new CyclicBarrier(3,new Runnable() {
				public void run() {
					report(); firstPassDone = true;
				}
			});
			int chunkSize = files.size() / 3; // 10/3 = 3
			int p1 = chunkSize; // 0..3
			int p2 = 2 * chunkSize; // 4..6, then 7..10
			workers[0] = new Worker(files.subList(0,p1+1));
			workers[1] = new Worker(files.subList(p1+1,p2+1));
			workers[2] = new Worker(files.subList(p2+1,files.size()));
			new Thread(workers[0], "worker-"+windex++).start();
			new Thread(workers[1], "worker-"+windex++).start();
			new Thread(workers[2], "worker-"+windex++).start();
		}
		else {

			output = FileUtils.readFileToString(new File("./database/table.html"));
			tableItems = FileUtils.readFileToString(new File("./database/table-item.html"));
			
			for (String f : files) {				
				parseFile(f);
				addFeatures(f);
			}
			long parserStop = System.currentTimeMillis();
			System.out.println("Total lexer+parser time " + (parserStop - parserStart) + "ms.");
			
//			FileUtils.write(new File("./output.html"),output);
		}
	}

	private void report() {
		long time = 0;
		if ( workers!=null ) {
			// compute max as it's overlapped time
			for (Worker w : workers) {
				long wtime = w.parserStop - w.parserStart;
				time = Math.max(time,wtime);
				System.out.println("worker time " + wtime + "ms.");
			}
		}
		System.out.println("Total lexer+parser time " + time + "ms.");

		System.out.println("finished parsing OK");
		System.out.println(LexerATNSimulator.match_calls+" lexer match calls");
	}

	public List<String> getFilenames(File f) throws Exception {
		List<String> files = new ArrayList<String>();
		getFilenames_(f, files);
		return files;
	}

	public void getFilenames_(File f, List<String> files) throws Exception {
		// If this is a directory, walk each file/dir in that directory
		if (f.isDirectory()) {
			String flist[] = f.list();
			for(int i=0; i < flist.length; i++) {
				getFilenames_(new File(f, flist[i]), files);
			}
		}

		// otherwise, if this is a java file, parse it!
		else if ( ((f.getName().length()>5) &&
				f.getName().substring(f.getName().length()-5).equals(".java")) )
		{
			files.add(f.getAbsolutePath());
		}
	}

	// This method decides what action to take based on the type of
	// file we are looking at
	// public static void doFile_(File f) throws Exception {
	// // If this is a directory, walk each file/dir in that directory
	// if (f.isDirectory()) {
	// String files[] = f.list();
	// for(int i=0; i < files.length; i++) {
	// doFile_(new File(f, files[i]));
	// }
	// }
	//
	// // otherwise, if this is a java file, parse it!
	// else if ( ((f.getName().length()>5) &&
	// f.getName().substring(f.getName().length()-5).equals(".java")) )
	// {
	// System.err.println(f.getAbsolutePath());
	// parseFile(f.getAbsolutePath());
	// }
	// }

	
	
	public void parseFile(String f) {
		try {
			
			File file = new File(f);
			String text = FileUtils.readFileToString(file).trim();
			
//			System.out.println("----------------original--------------");
//			System.out.println(text);
//			System.out.println("---------------------------------------");		
			
			if ( !quiet ) System.out.println(f);

			Collection<SelectionUnit> units = new ArrayList<SelectionUnit>();
			
			// Create a scanner that reads from the input stream passed to us
			Lexer lexer = new JavaFragmentLexer(new ANTLRInputStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			// Create a parser that reads from the scanner
			JavaFragmentParser parser = new JavaFragmentParser(tokens);
			if ( diag ) parser.addErrorListener(new DiagnosticErrorListener());
			if ( bail ) parser.setErrorHandler(new BailErrorStrategy());
			if ( SLL ) parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
			
			ParserRuleContext tree = parser.javaFragment();					
			ParseTreeWalker walker = new ParseTreeWalker(); 	
			
			// Chopping pass
			String filename = f.substring(f.lastIndexOf('/')+"/".length());
			int cid = Integer.parseInt(filename.substring("J".length(), filename.length()-".java".length()));
			ChopperAstListener chop = new ChopperAstListener(tokens, parser, cid, "android-faq"); 					
			walker.walk(chop, tree);
			units.addAll(chop.getUnits());
			
			// get comments
			units.addAll( ExtractComments.extractComments(
					new CommonTokenStream(new JavaFragmentLexer(new ANTLRInputStream(text))),
					cid));

			if( useDatabase ) {
				Database.getInstance().addUnits(units);
			}
						
						
			if ( notree ) parser.setBuildParseTree(false);
			if ( gui ) tree.inspect(parser);
			if ( printTree ) System.out.println("  " + tree.toStringTree(parser));

		}
		catch (Exception e) {
			System.err.println("parser exception: "+e);
			e.printStackTrace(); // so we can get stack trace
		}
	}
	
	public void addFeatures(String f) {
		try {
			
			File file = new File(f);
			String text = FileUtils.readFileToString(file).trim();
			
			// Create a scanner that reads from the input stream passed to us
			Lexer lexer = new JavaFragmentLexer(new ANTLRInputStream(text));
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			// Create a parser that reads from the scanner
			JavaFragmentParser parser = new JavaFragmentParser(tokens);
			
			ParserRuleContext tree = parser.javaFragment();					
			ParseTreeWalker walker = new ParseTreeWalker();
			
			// Chopping pass
			String filename = f.substring(f.lastIndexOf('/')+"/".length());
			int cid = Integer.parseInt(filename.substring("J".length(), filename.length()-".java".length()));
			PropertyExtractingAstListener features = new PropertyExtractingAstListener(tokens, cid, ""); // empty query! 					
			walker.walk(features, tree);
			
			Collection<Property> properties = features.getProperties();
			
			if( useDatabase ) {
				Database.getInstance().addProperties(properties);
			}
		}
		catch (Exception e) {
			System.err.println("parser exception: "+e);
			e.printStackTrace(); // so we can get stack trace
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