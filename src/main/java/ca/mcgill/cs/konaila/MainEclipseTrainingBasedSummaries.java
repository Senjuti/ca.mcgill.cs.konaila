package ca.mcgill.cs.konaila;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import ca.mcgill.cs.konaila.antlr.JavaFragmentLexer;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CallContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExprCastContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodSignatureContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimaryIdentifierContext;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.presentation.Presentation;
import ca.mcgill.cs.konaila.presentation.Tabs;
import ca.mcgill.cs.konaila.presentation.practices.EliminatingCast;
import ca.mcgill.cs.konaila.presentation.practices.EliminatingParameterInMethodCall;
import ca.mcgill.cs.konaila.presentation.practices.EliminatingParameterInMethodSignature;
import ca.mcgill.cs.konaila.presentation.practices.ShortenIdentifiersPractice;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.DatabasePredictions.SelectedElement;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class MainEclipseTrainingBasedSummaries {

	public static final String DB = "jdbc:sqlite:database/codeFragments.sqlite";
		
	public static void main(String[] args) throws Exception {
		Database.getInstance().initConnection(MainEclipseTrainingBasedSummaries.DB);
		
		generateSummaries();
		
		Database.getInstance().closeConnection();
	}

	public static void generateSummaries() throws SQLException, IOException {
		Connection c = Database.getInstance().getConnection();
		List<Integer> cids = DatabasePredictions.getPredictionCids(c);
		
		float summarizationRate = (float)0.2;
		
		for( Integer cid : cids) {
			String summary = generateSummary(c, cid, summarizationRate);			
			
			System.out.println("---------------summary-------------");
			System.out.println(summary);
			System.out.println("----------------------------------------");
			
			System.out.println();
		}
	}
	
	public static String generateSummary(Connection c, int cid, float summarizationRate) 
			 throws SQLException, IOException {
		float probabilityThreshold = 
				DatabasePredictions.selectTopProbability(c, cid, summarizationRate,
						SummarizationMethod.ConfigurationBased);
		
		Map<Integer,Float> uidToDbPredictions =
				DatabasePredictions.selectEqualOrAboveThreshold(c, cid, probabilityThreshold);
			
		List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, uidToDbPredictions.keySet());
		
		String intermediateSummary = "";
		System.out.println("--------------" + cid + "------------");
		for( SelectedElement e : elements ) {
			String line = Tabs.getTab(e.getIndentationLevel()) + e.getCode();
			intermediateSummary += line + "\n";
			System.out.println(e.getUid() + ": " + line);	
		}		
		
		return applyPresentation(intermediateSummary);	
	}
	
	public static String applyPresentation(String passSelectionText) {
		
		Lexer lexer = new JavaFragmentLexer(new ANTLRInputStream(passSelectionText));
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		JavaFragmentParser parser = new JavaFragmentParser(tokens);
		ParserRuleContext tree = parser.javaFragment();	
		ParseTreeWalker walker = new ParseTreeWalker();
		
		// Presentation pass			
		TokenStreamRewriter rewriter = new TokenStreamRewriter(tokens);
		Presentation passPresentation = new Presentation(tokens, rewriter);
		
		passPresentation.addPractice(CallContext.class, new EliminatingParameterInMethodCall(tokens, rewriter));
		passPresentation.addPractice(MethodSignatureContext.class, new EliminatingParameterInMethodSignature(tokens, rewriter));
		passPresentation.addPractice(ExprCastContext.class, new EliminatingCast(tokens, rewriter));
		passPresentation.addPractice(PrimaryIdentifierContext.class, new ShortenIdentifiersPractice(tokens, rewriter));

		walker.walk(passPresentation,  tree);
		String passPresentationText = passPresentation.getText();
		
		return passPresentationText.trim();
		
	}
}
