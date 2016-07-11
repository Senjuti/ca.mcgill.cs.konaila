package ca.mcgill.cs.konaila.ui.survey;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

public class SurveyServer {
		
	public static void main(String[] args) throws Exception {				
		Server server = new Server(8848);
		Context root = new Context(server,"/",Context.SESSIONS);
		root.addServlet(new ServletHolder(new  SurveySerlvet()), "/*");	 
		server.start();
	}	
}
