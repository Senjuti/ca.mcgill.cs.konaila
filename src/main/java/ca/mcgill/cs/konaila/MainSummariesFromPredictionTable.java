package ca.mcgill.cs.konaila;

import java.io.File;
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
import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.antlr.JavaFragmentLexer;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CallContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExprCastContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodSignatureContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimaryIdentifierContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ReturnStatementContext;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.presentation.Presentation;
import ca.mcgill.cs.konaila.presentation.Tabs;
import ca.mcgill.cs.konaila.presentation.practices.EliminateReturnPractice;
import ca.mcgill.cs.konaila.presentation.practices.EliminatingCast;
import ca.mcgill.cs.konaila.presentation.practices.EliminatingParameterInMethodCall;
import ca.mcgill.cs.konaila.presentation.practices.EliminatingParameterInMethodSignature;
import ca.mcgill.cs.konaila.presentation.practices.ShortenIdentifiersPractice;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.selection.DatabasePredictions.SelectedElement;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class MainSummariesFromPredictionTable {

	public static final String DB = "jdbc:sqlite:database/codeFragments.sqlite";
	public static float summarizationRate = (float)0.2;
	
	public static void main(String[] args) throws Exception {
		Database.getInstance().initConnection(MainSummariesFromPredictionTable.DB);
		
		if( args.length == 0 ) {
			generateSummaries();
		} else {
			generateSummary(Integer.parseInt(args[0]));
		}
		
		Database.getInstance().closeConnection();
	}

	public static void generateSummaries() throws SQLException, IOException {
		Connection c = Database.getInstance().getConnection();
		List<Integer> cids = DatabasePredictions.getPredictionCids(c);
		
		String htmlTemplate = FileUtils.readFileToString(new File("./html/ui-summarization-rate.html"));
		
		for( Integer cid : cids ) {
			String query = DatabaseGetCodeFragments.getQuery(c, cid);
			String codeFragment = DatabaseGetCodeFragments.getCodeFragment(c, cid);
			System.out.println("\n\n\n--------------" + cid + "-------------");
			System.out.println(codeFragment);
			
			String summary = generateSummary(cid);
			
			String htmlString = htmlTemplate.replace("***QUERY***", query)
					.replace("***public class Original {}***", codeFragment)
					.replace("***public class Summary {}***", summary);
			FileUtils.write(new File("./html/" + cid + ".html"), htmlString);
			
		}
	}
	
	public static String generateSummary(int cid) throws SQLException, IOException {

		Connection c = Database.getInstance().getConnection();
		
		String intermediate = generateSummary(c, cid, summarizationRate);
		String summary = applyPresentation(intermediate);

		System.out.println("---------------summary after presentation-------------");
		System.out.println(summary);
		
		System.out.println();
		
		return summary;
	}
	
	public static String generateSummary(Connection c, int cid, float summarizationRate) 
			 throws SQLException, IOException {
		float probabilityThreshold = 
				DatabasePredictions.selectTopProbability(c, cid, summarizationRate,
						SummarizationMethod.ConfigurationBased);
		
		System.out.println("score threshold for summarization rate " + summarizationRate + ": " + probabilityThreshold);
		
		Map<Integer,Float> uidToDbPredictions =
				DatabasePredictions.selectEqualOrAboveThreshold(c, cid, probabilityThreshold);
			
		List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, 
				uidToDbPredictions.keySet());
		
		String intermediateSummary = "";
		System.out.println("--------------------after selection ------------");
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
		

		passPresentation.addPractice(ReturnStatementContext.class, new EliminateReturnPractice(tokens, rewriter));
//		passPresentation.addPractice(CallContext.class, new EliminatingParameterInMethodCall(tokens, rewriter));
//		passPresentation.addPractice(MethodSignatureContext.class, new EliminatingParameterInMethodSignature(tokens, rewriter));
//		passPresentation.addPractice(ExprCastContext.class, new EliminatingCast(tokens, rewriter));
//		passPresentation.addPractice(PrimaryIdentifierContext.class, new ShortenIdentifiersPractice(tokens, rewriter));
		
		walker.walk(passPresentation,  tree);
		String passPresentationText = passPresentation.getText();
		
		return passPresentationText.trim();
		
	}

}
