package ca.mcgill.cs.konaila.data;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathException;
import javax.xml.xpath.XPathFactory;

import net.htmlparser.jericho.CharacterReference;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class GetCodeFragmentsFromSternaData {

	static final String[] HIBERNATE_DIRS = new String[]{
		"stackoverflow/stackoverflow _hibernate_20150416_from_Apr_to_Sep1_2014/",
		"stackoverflow/stackoverflow _hibernate_20150416_from_Dec31_to_Mar31_2015/",
		"stackoverflow/stackoverflow _hibernate_20150416_from_Sep1_to_Dec31_2014/",
		"stackoverflow/stackoverflow _hibernate_20150423_creation_date_from_Sep_1_2008_to_Apr_1_2014/"
	};
	static final String[] SPRING_DIRS = new String[]{
		"stackoverflow/stackoverflow _spring_20150416_from_Apr1_to_Mar31_2015/",
		"stackoverflow/stackoverflow _spring_20150417_creation_date_from_Sep_1_2008_to_Sep1_1_2010/",
		"stackoverflow/stackoverflow _spring_20150417_creation_date_from_Sep_1_2010_to_Apr_1_2014/"
	};
	static final String dir = HIBERNATE_DIRS[0];
	static final String fil = "./stackoverflow/stackoverflow_hibernate_20140901/stackoverflow_hibernate_20140901[1-100].xml";
	

	public static Collection<StackOverflowCodeFragment> getStackOverflowCodeFragments(File file) throws IOException {

		String property = "//Post[@Type='Answer']/Snippet/CodeTerm[@Class='' and @Inferred='False']";
		Collection<StackOverflowCodeFragment> codeFragments = new ArrayList<StackOverflowCodeFragment>();
		try { 
			getStackOverflowCodeFragments(property, file, codeFragments);
			return codeFragments;
		} catch	(XPathException e ) {
			throw new RuntimeException(e);
		} catch (SAXException e ) {
			throw new RuntimeException(e);
		} catch( ParserConfigurationException e) {
			throw new RuntimeException(e);
		}
	}

	public static void getStackOverflowCodeFragments(String xpath, File file, 
			Collection<StackOverflowCodeFragment> stackOverflowCodeFragments) throws 
			ParserConfigurationException, SAXException, IOException, XPathException {

		if( file.isDirectory() ) {
			File[] children = file.listFiles();
			for( File child : children ) {
				getStackOverflowCodeFragments(xpath, child, stackOverflowCodeFragments);
			}
		} if( file.isFile() ) {

			DocumentBuilderFactory builderFactory =
					DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;

			builder = builderFactory.newDocumentBuilder();

			//		    Document document = builder.parse(
			//		            new FileInputStream("employees.xml"));

			String xml = FileUtils.readFileToString(file);
			Document xmlDocument = builder.parse(new ByteArrayInputStream(xml.getBytes()));

			XPath xPath =  XPathFactory.newInstance().newXPath();

			String expression = xpath;

			//read a string value
			String email = xPath.compile(expression).evaluate(xmlDocument);

			//read an xml node using xpath
			Node node = (Node) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODE);

			//read a nodelist using xpath
			NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.NODESET);

			for( int i = 0; i < nodeList.getLength(); i++) {
				Node n = nodeList.item(i);
				String codeFragment = CharacterReference.decode(n.getTextContent());				

				String codeTermClass = ((Attr)n.getAttributes().getNamedItem("Class")).getValue();

				boolean codeTermInferred = Boolean.parseBoolean(
						((Attr)n.getAttributes().getNamedItem("Inferred")).getValue());

				Node postNode = n.getParentNode().getParentNode();			
				Attr attr = (Attr)postNode.getAttributes().getNamedItem("Id");
				int postId = Integer.parseInt(attr.getValue());
				String postType = ((Attr)postNode.getAttributes().getNamedItem("Id")).getValue();

				Node originNode = postNode.getParentNode().getChildNodes().item(1); // assumes this is the "Origin" node	
				String threadUrl = originNode.getTextContent();

//				int creationDate = findCreationDate(n);
				int score = findScore(n);
				int lastActivityDate = findLastActivityDate(n);
				QuestionInfo questionInfo = findQuestionInfo(postNode,n);

				StackOverflowCodeFragment s = new StackOverflowCodeFragment(codeFragment, 
						postId, postType, questionInfo.getQuestion(), 
						threadUrl, codeTermClass, codeTermInferred, 
						lastActivityDate, score,
						questionInfo.getPostId(), questionInfo.getCreationDate(),
						questionInfo.getScore(), file.getName());
				stackOverflowCodeFragments.add(s);				
			}
		}
	}

	private static int findCreationDate(Node questionPost) {
		return findIntegerPropertyForPostNode(questionPost, "creation_date");
	}
	
	private static int findQuestionScore(Node questionPost) {
		return findIntegerPropertyForPostNode(questionPost, "score");
	}

	private static int findLastActivityDate(Node n) {
		return findIntegerPropertyForCodeTermNode(n, "last_activity_date");
	}

	private static int findScore(Node n) {
		return findIntegerPropertyForCodeTermNode(n, "score");
	}

	private static int findIntegerPropertyForCodeTermNode(Node n, String property) {
		return findIntegerPropertyForPostNode(n.getParentNode().getParentNode(), property);
	}
	
	private static int findIntegerPropertyForPostNode(Node postNode, String property) {
		NodeList siblingList = postNode.getChildNodes();
		int value = -1;
		for( int i = 0; i < siblingList.getLength(); i++ ) {
			Node s = siblingList.item(i);

			if( s.getNodeName().equals("Property") 
					&& s.getChildNodes().item(1).getNodeName().equals("Key")
					&& s.getChildNodes().item(1).getTextContent() != null 
					&& s.getChildNodes().item(1).getTextContent().equals(property)) {
				if( s.getChildNodes().item(3).getNodeName().equals("Value") 
					&& s.getChildNodes().item(3).getTextContent() != null ) { 
						String valueStr = s.getChildNodes().item(3).getTextContent();
						value = Integer.parseInt(valueStr);
						break;
				}

			}
		}
		return value;
	}
	
	private static QuestionInfo findQuestionInfo(Node postNode, Node n) {
		String type = ((Attr)postNode.getAttributes().getNamedItem("Type")).getValue();
		if( type.equals("Question")) {
			return getQuestionInfo(postNode,n);
		} else {
			Node threadNode = postNode.getParentNode();
			NodeList siblingList = threadNode.getChildNodes();
			int length = siblingList.getLength();
			Node questionNode = null;
			for( int i = 0; i < length; i++) {
				Node sibling = siblingList.item(i);
				if( sibling.getNodeName().equals("Post") ) {
					type = ((Attr)sibling.getAttributes().getNamedItem("Type")).getValue();
					if( type.equals("Question")) {
						questionNode = sibling;
						return getQuestionInfo(questionNode,n);
					}
				}
			}
		}
		return null;
	}

	private static QuestionInfo getQuestionInfo(Node questionNode, Node n) {
		Node titleNode = questionNode.getChildNodes().item(1); // assume it is "Title" node
		Node fragmentNode = titleNode.getChildNodes().item(1); // assume it is the "Fragment" node
		String question = fragmentNode.getTextContent();

		int creationDate = findCreationDate(questionNode);
		int questionScore = findQuestionScore(questionNode); 

		Attr attr = (Attr)questionNode.getAttributes().getNamedItem("Id");
		int postId = Integer.parseInt(attr.getValue());		
		
		return new QuestionInfo(question, postId, creationDate, questionScore);
	}

	static class QuestionInfo {
		private String question;
		private int postId;
		private int createionDate;
		private int score;
		public QuestionInfo(String question, int postId, int createionDate,
				int score) {
			super();
			this.question = question;
			this.postId = postId;
			this.createionDate = createionDate;
			this.score = score;
		}
		public String getQuestion() {
			return question;
		}
		public int getPostId() {
			return postId;
		}
		public int getCreationDate() {
			return createionDate;
		}
		public int getScore() {
			return score;
		}
	}
}
