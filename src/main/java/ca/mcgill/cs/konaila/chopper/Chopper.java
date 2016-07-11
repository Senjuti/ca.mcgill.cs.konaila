package ca.mcgill.cs.konaila.chopper;

import java.util.ArrayList;
import java.util.Collection;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ca.mcgill.cs.konaila.MyDiagnosticErrorListener;
import ca.mcgill.cs.konaila.antlr.JavaFragmentLexer;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.parse.JavaRoot;

public class Chopper {
	
	public static Collection<SelectionUnit> parseAndChopFile(int cid, String source, String code, 
			boolean useDatabase, int numberOfTrialsPrior) {

		Collection<SelectionUnit> units = new ArrayList<SelectionUnit>();
		
		try {			
			// Create a scanner that reads from the input stream passed to us
			Lexer lexer = new JavaFragmentLexer(new ANTLRInputStream(code));
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			// Create a parser that reads from the scanner
			JavaFragmentParser parser = new JavaFragmentParser(tokens);
			
			parser.setErrorHandler(new ErrorStrategyForChopper(cid, source, code, useDatabase, numberOfTrialsPrior));
			parser.addErrorListener(new MyDiagnosticErrorListener());

			ParserRuleContext tree = parser.javaFragment();			
		
			if( tree.getChild(0) != null && tree.getChild(0).getChild(0) != null ) {
				String justification = getRootNameType(tree);
				if( useDatabase) 
					Database.getInstance().writeParsingOK(cid, justification);
				ParsingStats.writeToFile(cid, justification);
			} else {
				if( useDatabase)
					Database.getInstance().writeNoTree(cid);
				ParsingStats.writeToFile(cid, "cannot parse");
			}
			ParseTreeWalker walker = new ParseTreeWalker(); 	

			// Chopping pass
			ChopperAstListener chop = new ChopperAstListener(tokens, parser, cid, source); 					
			walker.walk(chop, tree);
			units.addAll(chop.getUnits());
			
			// get comments
			units.addAll( 
					ExtractComments.extractComments(
							new CommonTokenStream(new JavaFragmentLexer(new ANTLRInputStream(code))),
							cid));

			if( useDatabase ) {
				Database.getInstance().addUnits(units);
				Database.getInstance().addEllipses(chop.getEllipses());
			}
		}
		catch (Exception e) {
			System.err.println("parser exception: "+e);
			e.printStackTrace(); // so we can get stack trace
		}
		return units;
	}
	
	public static void parseFile(int cid, String source, String code, 
			boolean useDatabase, int numberOfTrialsPrior) {
		try {			
			// Create a scanner that reads from the input stream passed to us
			Lexer lexer = new JavaFragmentLexer(new ANTLRInputStream(code));
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			// Create a parser that reads from the scanner
			JavaFragmentParser parser = new JavaFragmentParser(tokens);
			
			parser.setErrorHandler(new ErrorStrategyForChopper(cid, source, code, useDatabase, numberOfTrialsPrior));
			parser.addErrorListener(new MyDiagnosticErrorListener());

			ParserRuleContext tree = parser.javaFragment();			
		
			if( tree.getChild(0) != null && tree.getChild(0).getChild(0) != null ) {
				String justification = getRootNameType(tree);
				if( useDatabase) 
					Database.getInstance().writeParsingOK(cid, justification);
				ParsingStats.writeToFile(cid, justification);
			} else {
				if( useDatabase)
					Database.getInstance().writeNoTree(cid);
				ParsingStats.writeToFile(cid, "cannot parse");
			}			
		}
		catch (Exception e) {
			System.err.println("parser exception: "+e);
			e.printStackTrace(); // so we can get stack trace
		}
	}
	
	public static void chopFile(int cid, String source, String code, 
			boolean useDatabase) {
		try {
			Collection<SelectionUnit> units = new ArrayList<SelectionUnit>();
			
			// Create a scanner that reads from the input stream passed to us
			Lexer lexer = new JavaFragmentLexer(new ANTLRInputStream(code));
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			// Create a parser that reads from the scanner
			JavaFragmentParser parser = new JavaFragmentParser(tokens);
			
			ParserRuleContext tree = parser.javaFragment();			
		
			ParseTreeWalker walker = new ParseTreeWalker(); 	

			// Chopping pass
			ChopperAstListener chop = new ChopperAstListener(tokens, parser, cid, source); 					
			walker.walk(chop, tree);
			units.addAll(chop.getUnits());
			
			// get comments
			units.addAll( 
					ExtractComments.extractComments(
							new CommonTokenStream(new JavaFragmentLexer(new ANTLRInputStream(code))),
							cid));

			if( useDatabase ) {
				Database.getInstance().addUnits(units);
				Database.getInstance().addEllipses(chop.getEllipses());
			}
		}
		catch (Exception e) {
			System.err.println("parser exception: "+e);
			e.printStackTrace(); // so we can get stack trace
		}
	}
	
	static String getRootNameType(ParserRuleContext tree) {
		ParseTree root = tree.getChild(0);
		String name = root.getClass().getName();
		String type = name.substring( name.indexOf("JavaFragmentParser$") + "JavaFragmentParser$".length(), name.length() - "Context" .length());
		return JavaRoot.valueOf("Java" + type).name();	
	}
}
