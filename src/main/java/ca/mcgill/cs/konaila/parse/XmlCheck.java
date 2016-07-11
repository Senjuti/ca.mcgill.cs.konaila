package ca.mcgill.cs.konaila.parse;

import java.io.File;
import java.io.FileNotFoundException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

public class XmlCheck {

	public static SAXParseException xmlCheck(InputSource in) {	    	 
    	try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(in);
    
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			return null;
		} catch (SAXParseException e) {
			return e;
		} catch (FileNotFoundException e) {
			return null;
		} catch (Exception e ) {
			throw new RuntimeException(e);
		}
	}
	
	public static SAXParseException xmlCheck(File file) {	    	 
    	try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(file);
    
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
			return null;
		} catch (SAXParseException e) {
			return e;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
