package ca.mcgill.cs.konaila.ui.survey;

//Import required java libraries
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
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
import ca.mcgill.cs.konaila.database.Database;
import ca.mcgill.cs.konaila.database.DatabaseGetCodeFragments;
import ca.mcgill.cs.konaila.database.DatabaseStudy;
import ca.mcgill.cs.konaila.database.DatabaseStudy.StudyTask;
import ca.mcgill.cs.konaila.ui.ParameterException;

public class SurveySerlvet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static Map<String,String> faqToCodeWithFontTags; 
	public static final String dir = "./html/";
	private static final String uiRatingPageFilename = dir + "experiment-questions.html";
	private static final String uiFrontFilename = dir + "experiment-front.html";

	static {
		Database.setDatabaseString(Main.DB);
	}

	public synchronized void doPost(HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws ServletException, IOException {
		String path = aRequest.getPathInfo();
		String url = aRequest.getRequestURL().toString();
		int colon = url.lastIndexOf(":") + 1;
		int end = url.indexOf("/", colon);
		String hostAndPort = url.substring("http://".length(), end);
		long time = System.currentTimeMillis();
		try {
			Connection c = Database.getInstance().getConnection();

			if( path.endsWith("SurveyQuestion") ) {

				// Parameters
				String user = getStringParameter(aRequest, "user");
				if( DatabaseStudy.checkUserExists(c, user) ) {
					
					int taskNumber = getIntParameter(aRequest, "taskNumber");
					
					if( !path.startsWith("/FirstSurveyQuestion")) {
						String summary1Param = getStringParameter(aRequest, "Summary 1");
						String summary2Param = getStringParameter(aRequest, "Summary 2");
						String summary3Param = getStringParameter(aRequest, "Summary 3");
						
						Answer summary1Answer = Answer.valueOf(summary1Param);
						Answer summary2Answer = Answer.valueOf(summary2Param);
						Answer summary3Answer = Answer.valueOf(summary3Param);
						
						int lastTaskNumber = taskNumber;
						saveRatings(c, user, lastTaskNumber, summary1Answer, summary2Answer, summary3Answer);
					}
	
					int nextTaskNumber = taskNumber + 1;
					if( nextTaskNumber <= 52 ) {					
						StudyTask task = DatabaseStudy.getTask(c, user, nextTaskNumber);
						if( task == null ) {
							 writeErrorMessage(aResponse, "Problem loading tasks for "
							 		+ " user '" + user + "' (user not found in database)");
						} else {
							String htmlString = createRatingPage(c, hostAndPort, task, user);
		
							aResponse.setContentType("text/html");
							aResponse.setStatus(HttpServletResponse.SC_OK);
							ServletOutputStream out = aResponse.getOutputStream();
							out.print(htmlString);
							out.close();        
						}
					} else {
						String htmlString = FileUtils.readFileToString(new File("./html/experiment-end.html"));
						
						aResponse.setContentType("text/html");
						aResponse.setStatus(HttpServletResponse.SC_OK);
						ServletOutputStream out = aResponse.getOutputStream();
						out.print(htmlString);
						out.close();  
					}
				}				
			} 
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch( ParameterException e) {
			writeErrorMessage(aResponse, e.getMessage());
		} 
		long diff = (System.currentTimeMillis() - time)/1000;
		System.out.println("page loaded in " + diff + " s" );
	}


	public synchronized void doGet(HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws ServletException, IOException {
		String path = aRequest.getPathInfo();
		String url = aRequest.getRequestURL().toString();
		int colon = url.lastIndexOf(":") + 1;
		int end = url.indexOf("/", colon);
		long time = System.currentTimeMillis();
		try {
			if( path.startsWith("/SummarizationStudy") ) {

				// Parameters
				String user = getStringParameter(aRequest, "user");
				int taskNumber = aRequest.getParameter("taskNumber") == null 
						? 0 : // incremented to 1 when displaying the next page
						getIntParameter(aRequest, "taskNumber"); // next one is +1

				String html = FileUtils.readFileToString(new File(uiFrontFilename))
						.replace("***user***", user)
						.replace("***taskNumber***", Integer.toString(taskNumber));			

				aResponse.setContentType("text/html");
				aResponse.setStatus(HttpServletResponse.SC_OK);
				ServletOutputStream out = aResponse.getOutputStream();
				out.print(html);
				out.close();        
			}    
		} catch( ParameterException e) {
			writeErrorMessage(aResponse, e.getMessage());
		} 
		long diff = (System.currentTimeMillis() - time)/1000;
		System.out.println("page loaded in " + diff + " s" );
	}
	
	private static void saveRatings(Connection conn, String user, int taskNumber,
			Answer summary1, Answer summary2, Answer summary3) throws IOException, SQLException{
		DatabaseStudy.saveRatings(conn, user, taskNumber,
				summary1.name(), summary2.name(), summary3.name());
		
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

	private static String createRatingPage(Connection c, String hostAndPort, 
			StudyTask task, String user) 
					throws SQLException, IOException {

		int cid = task.getCid();
		int taskNumber = task.getTaskNumber();
		String summary1HtmlEncoded = CharacterReference.encode(task.getSummary1());
		String summary2HtmlEncoded = CharacterReference.encode(task.getSummary2());
		String summary3HtmlEncoded = CharacterReference.encode(task.getSummary3());
		int nextTaskNumber = taskNumber+1;

		String source = DatabaseGetCodeFragments.getSource(c, cid);
		String query = DatabaseGetCodeFragments.getQuery(c, cid);
//		query += " (" + Source.getReadableSource(source) + ")";
		String codeFragment = DatabaseGetCodeFragments.getCodeFragment(c, cid);
		codeFragment = codeFragment.replace(AbstractErrorStrategy.COMMENT_TOKEN_ADDED,"");
		String codeFragmentHtmlEncoded = CharacterReference.encode(codeFragment);	
		System.out.println("\n\n\n--------------" + cid + "-------------");
		System.out.println(codeFragment);
		
		String htmlTemplate = FileUtils.readFileToString(new File(uiRatingPageFilename));

		String htmlString = htmlTemplate.replace("***QUERY***", query)
				.replace("***public class Original {}***", codeFragmentHtmlEncoded)
				.replace("***public class Summary1 {}***", summary1HtmlEncoded)
				.replace("***public class Summary2 {}***", summary2HtmlEncoded)				
				.replace("***public class Summary3 {}***", summary3HtmlEncoded)
				.replace("***CID***", Integer.toString(cid))
				.replace("***taskNumber***", Integer.toString(taskNumber))
				.replace("***user***", user);
//				.replace("***size***", size.name());

		return htmlString;
	}

	private static void writeErrorMessage(HttpServletResponse aResponse, 
			String message) throws IOException {
		ServletOutputStream out = aResponse.getOutputStream();
		aResponse.setContentType("text/plain");
		aResponse.setStatus(HttpServletResponse.SC_OK);
		out.print(message);
		out.close();
	}

}

