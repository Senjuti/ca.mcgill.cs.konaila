package ca.mcgill.cs.konaila.selection.features;

import java.util.Collection;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ca.mcgill.cs.konaila.MyDiagnosticErrorListener;
import ca.mcgill.cs.konaila.antlr.JavaFragmentLexer;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser;
import ca.mcgill.cs.konaila.database.Database;

public class PropertyExtractor {
	
	public static void addFeatures(int cid, String source, String code, String query, 
			boolean useDatabase, int numberOfTrialsPrior) {
		try {
						
			// Create a scanner that reads from the input stream passed to us
			Lexer lexer = new JavaFragmentLexer(new ANTLRInputStream(code));
			CommonTokenStream tokens = new CommonTokenStream(lexer);

			// Create a parser that reads from the scanner
			JavaFragmentParser parser = new JavaFragmentParser(tokens);
			
			parser.setErrorHandler(new PropertyExtractorErrorStrategy(cid, source, code, query, useDatabase, numberOfTrialsPrior));
			parser.addErrorListener(new MyDiagnosticErrorListener());
			
			ParserRuleContext tree = parser.javaFragment();					
			ParseTreeWalker walker = new ParseTreeWalker(); 	
			
			// Chopping pass
			PropertyExtractingAstListener features = new PropertyExtractingAstListener(tokens, cid, query); 					
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

}
