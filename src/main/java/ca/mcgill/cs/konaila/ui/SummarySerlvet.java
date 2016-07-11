package ca.mcgill.cs.konaila.ui;

//Import required java libraries
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.htmlparser.jericho.CharacterReference;

import org.apache.commons.io.FileUtils;

import ca.mcgill.cs.konaila.AbstractErrorStrategy;
import ca.mcgill.cs.konaila.Main;
import ca.mcgill.cs.konaila.Source;
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseFeatures;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.database.DatabaseSummaries;
import ca.mcgill.cs.konaila.presentation.formatting.FormattingServer;
import ca.mcgill.cs.konaila.presentation.formatting.How;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.summarize.HowToSummarize;
import ca.mcgill.cs.konaila.summarize.KnapsackSummarizer;
import ca.mcgill.cs.konaila.summarize.SummarizerConfigurationBased;

public class SummarySerlvet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Map<String,String> faqToCodeWithFontTags; 
	public static final String dir = "./src/main/resources/html/";
	private static final String uiSummarizationRateFilename = dir + "ui-summarization-rate.html";
	private static final String uiSummaryOrderingFilename = dir + "ui-summary-ordering.html";
	private static final String indexFilename = dir + "index.html";
	private static final int DEFAULT_SUMMARIZATION_RATE = 20;
	private static final int DEFAULT_SUMMARIZATION_RATE_CONFIG_BASED = 25;
	private static final int DEFAULT_LINE_WIDTH = 50;
	
	public enum ExperimentInterface { 
		SummarizationRate, SummaryOrdering;
	}
	
	public enum Front {
		FirstThreeLines, Whole, Summary, ThreeSummaries;
	}
	
	public enum Next { 
		Show, No;
	}
	
	public enum SummarizationMethod { 
		ConfigurationBased,
		ConfigurationBasedWithDataflow;
	}

	public enum OptimizationMethod { 
		Simple, 
		Knapsack,
		KnapsackOneDimensional,
		KnapsackContext;
	}
	
	private static final String frontPageRow = 
			"<tr><td>QUERY [<a href=\"URL\">Summary</a>]</td><td><pre class=\"prettyprint\">CODE</pre></td></tr>";

	private static final String frontPageRow3Things = 
			"<tr><td>QUERY [<a href=\"URL\">Summary</a>]</td><td><pre class=\"prettyprint\">SUMMARY1</pre></td><td><pre class=\"prettyprint\">SUMMARY2</pre></td><td><pre class=\"prettyprint\">SUMMARY3</pre></td></tr>";

	
	private static final String urlTemplate = 
			"http://***HOST_AND_PORT***/Load***EXPERIMENT-INTERFACE***?" 
					+ "cid=***CID***" 
					+ "&summarization-rate=***SUMMARIZATION_RATE***" 
					+ "&line-width=***LINE-WIDTH***" 
					+ "&next-cid=***NEXT_CID***"
					+ "&summarization-method=***SUMMARIZATION-METHOD***";
	
	public void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws ServletException, IOException {
		String path = aRequest.getPathInfo();
		int cid;
		int summarizationRate = DEFAULT_SUMMARIZATION_RATE;
		int lineWidth = DEFAULT_LINE_WIDTH;
		String url = aRequest.getRequestURL().toString();
		int colon = url.lastIndexOf(":") + 1;
		int end = url.indexOf("/", colon);
		String hostAndPort = url.substring("http://".length(), end);
		long time = System.currentTimeMillis();
		try {
			Database.getInstance().initConnection(Main.DB);
			Connection c = Database.getInstance().getConnection();

			if( path.startsWith("/DevelopmentSetServer") ) { 
				
				String sourcesParam =  aRequest.getParameter("source") == null ? "Eclipse,Android" : getStringParameter(aRequest, "source") ;
				String summarizationParam = aRequest.getParameter("interface") == null ? ExperimentInterface.SummarizationRate.name() : getStringParameter(aRequest, "interface");
				String displayParam = aRequest.getParameter("front") == null ? Front.FirstThreeLines.name() : getStringParameter(aRequest, "front");
				String showNext = aRequest.getParameter("next") == null ? Next.No.name() : getStringParameter(aRequest, "next") ;
				String summarizationMethodParam = aRequest.getParameter("summarization-method") == null ? 
						SummarizationMethod.ConfigurationBasedWithDataflow.name() : getStringParameter(aRequest, "summarization-method") ;
				String opMethodParam = aRequest.getParameter("optimization-method") == null ? 
								OptimizationMethod.KnapsackContext.name() : getStringParameter(aRequest, "optimization-method") ;

								
				String[] sources = sourcesParam.split(",");
				String[] summarization = summarizationParam.split(",");
				Front display = Front.valueOf(displayParam);
				Next next = Next.valueOf(showNext);
				SummarizationMethod summarizationMethod = SummarizationMethod.valueOf(summarizationMethodParam);
				OptimizationMethod opMethod = OptimizationMethod.valueOf(opMethodParam);
				
				String htmlTemplate = FileUtils.readFileToString(new File(indexFilename));
				String htmlString = htmlTemplate;
				
				for( int i = 0; i < sources.length; i++ ) {
					Source source = Source.valueOf(sources[i]);
					String methodString = summarization.length == 1 ?
							summarization[0] : summarization[i];
					ExperimentInterface method = ExperimentInterface.valueOf(methodString);
													
					String content = display == Front.ThreeSummaries ? 
							createFrontPage3Summaries(c, hostAndPort,
									display, source,  method, next, summarizationMethod)
						: createFrontPage( c, hostAndPort, display, source,
								method, next, summarizationMethod);
							
					htmlString = htmlString
							.replace("<!--" + source.getTemplateStub() + "-->", content)
							.replace("<!--" + source.getTitle() + "-->", source.getTitle());
				}
				
				aResponse.setContentType("text/html");
				aResponse.setStatus(HttpServletResponse.SC_OK);
				ServletOutputStream out = aResponse.getOutputStream();
				out.print(htmlString);
				out.close();				
			} else if( path.startsWith("/Load") ) {

				cid = getIntParameter(aRequest, "cid");
				summarizationRate = getIntParameter(aRequest, "summarization-rate");
				lineWidth = getIntParameter(aRequest, "line-width");
				int nextCid = aRequest.getParameter("next-cid") == null ? -1 : getIntParameter(aRequest, "next-cid");	
				ExperimentInterface experimentInterface = 
						ExperimentInterface.valueOf(path.substring("/Load".length(), path.length()));
				SummarizationMethod summarizationMethod = 
						aRequest.getParameter("summarization-method") == null ?
					SummarizationMethod.ConfigurationBasedWithDataflow : SummarizationMethod.valueOf(getStringParameter(aRequest, "summarization-method"));
				OptimizationMethod optimizationMethod =
						aRequest.getParameter("optimization-method") == null ?
								OptimizationMethod.KnapsackContext : OptimizationMethod.valueOf(getStringParameter(aRequest, "optimization-method"));
			
				if( path.startsWith("/LoadSummaryOrdering") ) {
					
					String htmlString = createSummaryOrderingPage(c, cid, summarizationRate, 
							nextCid, hostAndPort, summarizationMethod,
							optimizationMethod);
					
					aResponse.setContentType("text/html");
					aResponse.setStatus(HttpServletResponse.SC_OK);
					ServletOutputStream out = aResponse.getOutputStream();
					out.print(htmlString);
					out.close();
				} else if( path.startsWith("/LoadSummarizationRate") ) {				
				
					String htmlString = createSummarySummarizationRatePage(
							c, cid, summarizationRate, experimentInterface,
							nextCid, hostAndPort, summarizationMethod, 
							optimizationMethod, lineWidth);
					
					aResponse.setContentType("text/html");
					aResponse.setStatus(HttpServletResponse.SC_OK);
					ServletOutputStream out = aResponse.getOutputStream();
					out.print(htmlString);
					out.close();				
				} 
			} else if( path.startsWith("/ExperimentQuestions") ) {				
				
				String htmlString = FileUtils.readFileToString(new File(dir
						+ "/experiment-questions.html"));
				
				aResponse.setContentType("text/html");
				aResponse.setStatus(HttpServletResponse.SC_OK);
				ServletOutputStream out = aResponse.getOutputStream();
				out.print(htmlString);
				out.close();				
			} 
			
			Database.getInstance().getConnection().close();			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( ParameterException e) {
			ServletOutputStream out = aResponse.getOutputStream();
			aResponse.setContentType("text/plain");
			aResponse.setStatus(HttpServletResponse.SC_OK);
			out.print(e.getMessage());
			out.close();
		}
		long diff = (System.currentTimeMillis() - time)/1000;
		System.out.println("page loaded in " + diff + " s" );
	}
	
	private static int getIntParameter(HttpServletRequest aRequest, String name) throws ParameterException{
		try {
			return Integer.parseInt(getStringParameter(aRequest, name));		
		} catch( NumberFormatException e) {
			e.printStackTrace();
			throw new ParameterException("REST parameter " + name + " expecting an integer value");			
		}
	}
	
	private static String getStringParameter(HttpServletRequest aRequest, String name) throws ParameterException{
		String value = aRequest.getParameter(name);
		if( value == null ) {
			throw new ParameterException("Expecting the parameter '" + name + "'");
		} else {
			return value;
		}
	}

	private static String createSummarySummarizationRatePage(Connection c, int cid,  
			int summarizationRate, ExperimentInterface experimentInterface, 
			int nextCid, String hostAndPort, SummarizationMethod summarizationMethod,
			OptimizationMethod optimizationMethod, int lineWidth) 
					throws SQLException, IOException {
		String source = DatabaseGetCodeFragments.getSource(c, cid);
		String query = DatabaseGetCodeFragments.getQuery(c, cid);
		query += " (" + Source.getReadableSource(source) + ")";
		String codeFragment = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		codeFragment = codeFragment.replace(AbstractErrorStrategy.COMMENT_TOKEN_ADDED,"");
		String codeFragmentHtmlEncoded = CharacterReference.encode(codeFragment);	
		System.out.println("\n\n\n--------------" + cid + "-------------");
		System.out.println(codeFragment);
		
		float summarizationPercentage = (float)summarizationRate / (float)100;
		String summary = generateSummary(c, cid, summarizationPercentage, 
				experimentInterface, summarizationMethod, optimizationMethod, lineWidth);
		int nextNextCid = DatabaseFeatures.getNextCid(c, nextCid);
		
		String nextCidLink = urlTemplate.replace("***HOST_AND_PORT***", hostAndPort)
				.replace("***EXPERIMENT-INTERFACE***", ExperimentInterface.SummaryOrdering.name())
				.replace("***CID***", Integer.toString(nextCid))
				.replace("***SUMMARIZATION-RATE***", Integer.toString(summarizationRate)
				.replace("***LINE-WIDTH***", Integer.toString(lineWidth)));
		nextCidLink = nextNextCid == -1 ? 
				nextCidLink.replace("&next-cid=***NEXT_CID***", "")
				: nextCidLink.replace("***NEXT_CID***",  Integer.toString(nextNextCid));
		
		String htmlTemplate = FileUtils.readFileToString(new File(uiSummarizationRateFilename));
		
		String htmlString = htmlTemplate.replace("***QUERY***", query)
				.replace("***public class Original {}***", codeFragmentHtmlEncoded)
				.replace("***public class Summary {}***", summary)
				.replace("***CID***", Integer.toString(cid))
				.replace("***SUMMARIZATION-RATE***", Integer.toString(summarizationRate))
				.replace("***LINE-WIDTH***", Integer.toString(lineWidth))
				.replace("***LOAD***", "Load" + experimentInterface.name());
		htmlString = nextCid == -1 ?
				htmlString.replace("<p><a href=\"***NEXT LINK***\" >Next code fragment (Query: ***NEXT QUERY***)</a>","") :
					htmlString.replace("***NEXT QUERY***", DatabaseGetCodeFragments.getQuery(c, nextCid))
					.replace("***NEXT LINK***", nextCidLink);
		return htmlString;
	}
	
	private static String createSummaryOrderingPage(Connection c, int cid,  
			int summarizationRate, int nextCid, String hostAndPort,
			SummarizationMethod summarizationMethod,
			OptimizationMethod optimizationMethod) throws SQLException, IOException {

		String source = DatabaseGetCodeFragments.getSource(c, cid);
		String query = DatabaseGetCodeFragments.getQuery(c, cid);
		query += " (" + Source.getReadableSource(source) + ")";
		String codeFragment = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		String codeFragmentHtmlEncoded = CharacterReference.encode(codeFragment);	
		System.out.println("\n\n\n--------------" + cid + "-------------");
		System.out.println(codeFragment);
		
		float summarizationPercentage = (float)summarizationRate / (float)100;
		String summary = generateSummary(c, cid, summarizationPercentage, 
				ExperimentInterface.SummaryOrdering, summarizationMethod,
				optimizationMethod, DEFAULT_LINE_WIDTH);
		int nextNextCid = DatabaseFeatures.getNextCid(c, nextCid);
		
		String htmlTemplate = FileUtils.readFileToString(new File(uiSummaryOrderingFilename));
		
		String nextCidLink = urlTemplate.replace("***HOST_AND_PORT***", hostAndPort)
				.replace("***EXPERIMENT-INTERFACE***", ExperimentInterface.SummaryOrdering.name())
				.replace("***CID***", Integer.toString(nextCid))
				.replace("***SUMMARIZATION_RATE***", Integer.toString(summarizationRate));
		nextCidLink = nextNextCid == -1 ? 
				nextCidLink.replace("&next-cid=***NEXT_CID***", "")
				: nextCidLink.replace("***NEXT_CID***",  Integer.toString(nextNextCid));
		
		String htmlString = htmlTemplate.replace("***QUERY***", query)
				.replace("***public class Original {}***", codeFragmentHtmlEncoded)
				.replace("***public class Summary {}***", summary)
				.replace("***CID***", Integer.toString(cid))
				.replace("***SUMMARIZATION-RATE***", Integer.toString(summarizationRate));
//				.replace("***LOAD***", "Load" + summarizationMethod.name());
		htmlString = nextCid == -1 ?
				htmlString.replace("<p><a href=\"***NEXT LINK***\" >Next code fragment (Query: ***NEXT QUERY***)</a>","") :
					htmlString.replace("***NEXT QUERY***", DatabaseGetCodeFragments.getQuery(c, nextCid))
					.replace("***NEXT LINK***", nextCidLink);
		return htmlString;
	}
	
	private static String generateSummary(Connection c, int cid, float summarizationPercentage,
			ExperimentInterface experimentInterface,
			SummarizationMethod summarizationMethod, OptimizationMethod optimizationMethod,
			int lineWidth) throws SQLException, IOException {
		String summary = null;
		if( experimentInterface == ExperimentInterface.SummaryOrdering ) {
			if( summarizationPercentage == 1 ) {
				String codeFragment = DatabaseGetCodeFragments.getCodeFragment(c, cid);
				summary= codeFragment;
				
			} 
		} else if( experimentInterface == ExperimentInterface.SummarizationRate) {			
			if( summarizationPercentage == 0 ) {
				summary = "";
			} else if( ((int)(summarizationPercentage*100)) == 100) {
				summary = DatabaseGetCodeFragments.getCodeFragment(c, cid);
			}					
		}
				
		if( summary == null ) {
			if( optimizationMethod == OptimizationMethod.Simple) {
				summary = SummarizerConfigurationBased.generateSummary(c, cid,
					summarizationPercentage, summarizationMethod);
			} else if( optimizationMethod == OptimizationMethod.KnapsackOneDimensional) {
				summary = KnapsackSummarizer.
						generateSummaryFixedWidth(
								c, cid, summarizationPercentage, summarizationMethod, lineWidth);
			} else if( optimizationMethod == OptimizationMethod.Knapsack) {
				summary = KnapsackSummarizer.
						generateSummaryTwoDimensional(
								c, cid, summarizationPercentage, summarizationMethod, lineWidth);				
			} else if( optimizationMethod == OptimizationMethod.KnapsackContext) {
				summary = KnapsackSummarizer.
						generateSummaryTwoDimensionalWithContext(
								c, cid, summarizationPercentage, summarizationMethod, lineWidth);				
			} else {
				summary = "";						
			}
		}

		String summaryInHtml = CharacterReference.encode(summary);		
		return summaryInHtml;
	}

	private static String createFrontPage3Summaries(Connection c, String hostAndPort, 
			Front display, Source source, ExperimentInterface experimentInterface,
			Next next, SummarizationMethod summarizationMethod)
					throws SQLException, IOException {
		
		List<Integer> cids = DatabasePredictions.getPredictionCids(c, source.getDbSourceString());
				
		String content = "<tr><th>Query</th><th>Knapsack</th><th>Greedy</th><th>Baseline</th></tr>";
		int internalId = 1;
		
		String ruler = "1234567891         2         3         4         5";
		
		for(  int cid : cids ) {
			String query = DatabaseGetCodeFragments.getQuery(c, cid); 
			
			String summary1 = CharacterReference.encode(
					DatabaseSummaries.getSummary(c, cid, HowToSummarize.Knapsack.name()));

			String summary2 = CharacterReference.encode(
					DatabaseSummaries.getSummary(c, cid, HowToSummarize.Greedy.name()));

			String summary3 = CharacterReference.encode(
					DatabaseSummaries.getSummary(c, cid, HowToSummarize.Baseline.name()));
			
			summary1 = ruler + "\n" + summary1;
			summary2 = ruler + "\n" + summary2;
			summary3 = ruler + "\n" + summary3;

			int defaultSummarizationRate = 
					experimentInterface == ExperimentInterface.SummarizationRate ? 
							DEFAULT_SUMMARIZATION_RATE_CONFIG_BASED : DEFAULT_SUMMARIZATION_RATE;
			String url = urlTemplate.replace("***HOST_AND_PORT***", hostAndPort)
					.replace("***EXPERIMENT-INTERFACE***", experimentInterface.name())
					.replace("***CID***", Integer.toString(cid))
					.replace("***SUMMARIZATION_RATE***", Integer.toString(defaultSummarizationRate)).trim()
					.replace("***LINE-WIDTH***", Integer.toString(DEFAULT_LINE_WIDTH)).trim()
					.replace("***SUMMARIZATION-METHOD***", summarizationMethod.name());
			url = next == Next.No ? 
					url.replace("&next-cid=***NEXT_CID***", "") : 
				url.replace("***NEXT_CID***", Integer.toString(DatabaseFeatures.getNextCid(c, cid)));
			String row = frontPageRow3Things.
					replace("QUERY", "<p>" + internalId + ". " + query + " (cid=" + cid + ")").
					replace("URL", url).
					replace("SUMMARY1", summary1).
					replace("SUMMARY2", summary2).
					replace("SUMMARY3", summary3);
			
			content += row;			
			internalId += 1;
		}
		return content;
		
	}
	
	private static String createFrontPage(Connection c, String hostAndPort, 
			Front display, Source source, ExperimentInterface experimentInterface,
			Next next, SummarizationMethod summarizationMethod)
					throws SQLException, IOException {

//		List<Integer> cids = DatabaseGetCodeFragments.getJavaCodeFragmentsCidsLongEnough(c, source.getDbSourceString());
		
		List<Integer> cids = DatabasePredictions.getPredictionCids(c, source.getDbSourceString());
		
		String content = createFrontPageContent(c, cids, experimentInterface,
				 hostAndPort, display, next, summarizationMethod);
		return content;
	}
	
	private static String createFrontPageContent(Connection c, List<Integer> cids,
			ExperimentInterface experimentInterface, String hostAndPort, 
			Front display, Next next, SummarizationMethod summarizationMethod)
					throws SQLException, IOException{
	
		String content = "";
		int internalId = 1;
		
		String ruler = "1234567891         2         3         4         5";
		
		for(  int cid : cids ) {
			String query = DatabaseGetCodeFragments.getQuery(c, cid); 
			String codeFragmentWhole = DatabaseGetCodeFragments.getCodeFragment(c, cid);
			String[] lines = codeFragmentWhole.split("\n");
			
			String codeFragment = "";
			if( display == Front.FirstThreeLines ) { 
					codeFragment = lines[0] + "\n" + 
							(lines.length >= 2 ? lines[1] : "") +
							(lines.length > 2 ? "\n..." : "");
			} else if( display == Front.Whole) {
					codeFragment = FormattingServer.format(codeFragmentWhole, How.Compact, 
							ruler.length(),
							FormattingServer.URL);
			} else if( display == Front.Summary) {
				codeFragment = KnapsackSummarizer.
						generateSummaryTwoDimensionalWithContext(
								c, cid, 3, 
								SummarizationMethod.ConfigurationBasedWithDataflow,
								50);
			}
					
			codeFragment = ruler + "\n" + codeFragment;

			int defaultSummarizationRate = 
					experimentInterface == ExperimentInterface.SummarizationRate ? 
							DEFAULT_SUMMARIZATION_RATE_CONFIG_BASED : DEFAULT_SUMMARIZATION_RATE;
			String url = urlTemplate.replace("***HOST_AND_PORT***", hostAndPort)
					.replace("***EXPERIMENT-INTERFACE***", experimentInterface.name())
					.replace("***CID***", Integer.toString(cid))
					.replace("***SUMMARIZATION_RATE***", Integer.toString(defaultSummarizationRate)).trim()
					.replace("***LINE-WIDTH***", Integer.toString(DEFAULT_LINE_WIDTH)).trim()
					.replace("***SUMMARIZATION-METHOD***", summarizationMethod.name());
			url = next == Next.No ? 
					url.replace("&next-cid=***NEXT_CID***", "") : 
				url.replace("***NEXT_CID***", Integer.toString(DatabaseFeatures.getNextCid(c, cid)));
			String row = frontPageRow.
					replace("QUERY", "<p>" + internalId + ". " + query + " (cid=" + cid + ")").
					replace("URL", url).
					replace("CODE", codeFragment);
			
			content += row;			
			internalId += 1;
		}
		return content;
	}
}

