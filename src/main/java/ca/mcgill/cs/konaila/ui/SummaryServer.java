package ca.mcgill.cs.konaila.ui;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

public class SummaryServer {
		
	public static void main(String[] args) throws Exception {		

		
		Server server = new Server(8844);
		Context root = new Context(server,"/",Context.SESSIONS);
		root.addServlet(new ServletHolder(new  SummarySerlvet()), "/*");	 
		server.start();
	}	
}
