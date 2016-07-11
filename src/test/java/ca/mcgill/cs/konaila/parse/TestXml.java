package ca.mcgill.cs.konaila.parse;


import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class TestXml {
	
	final static InputSource xmlFragment = new InputSource(
			new StringReader("<tr>        <td>            <form:select path=\"publication\" cssStyle=\"width : 350px;\" >                <option  value=\"\" ></option>                <c:forEach var=\"item\" items=\"${publicationList}\">                    <c:if test=\"${item.id == publication.id}\">                        <option value=\"${item.id}\" selected=\"selected\" >${item.title}</option>                    </c:if>                </c:forEach>            </form:select>        </td>    </tr>"));

	final static InputSource mixedXml = new InputSource(new StringReader(
			"@NamedQuery(name = \"BonusCounter.findBonus\",                query = \"select counter from Counter counter where counter.fKey.fCode = :qr and bonus.fKey.fBusinessID = :officeID and bonus.fKey.fProductID = :productID\")          })   <persistence-unit name=\"my-pu\"> <description>My Persistence Unit</description> <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider> <properties>     <property name=\"javax.persistence.jdbc.url\" value=\"jdbc:postgresql://localhost:5432/code\"/>     <property name=\"javax.persistence.jdbc.driver\" value=\"org.postgresql.Driver\"/>     <property name=\"javax.persistence.jdbc.user\" value=\"ib\"/>     <property name=\"javax.persistence.jdbc.password\" value=\"\"/>     <property name=\"hibernate.dialect\" value=\"org.hibernate.dialect.PostgreSQL9Dialect\"/>     <property name = \"hibernate.show_sql\" value = \"true\" /> </properties>"));
	
	final static InputSource javaWithMistakes = new InputSource(new StringReader("public class MyAppModule extends AbstractModule {    @Singleton    @Provides    public SessionFactory provideSessionFactory(MyAppConfiguration configuration) {        HibernateBundle<MyAppConfiguration> hibernate = new HibernateBundle<ExampleConfiguration>(MyModel.class) {            @Override            public DataSourceFactory getDataSourceFactory(MyAppConfiguration configuration) {                return configuration.getDataSourceFactory();            }        }        return hibernate.getSessionFactory();    }}"));
	
	final static InputSource xmlTrickyExample = new InputSource(
			new StringReader("    <?xml version=\"1.0\" encoding='utf-8'?>    <!DOCTYPE hibernate-configuration PUBLIC \"-//Hibernate/Hibernate Configuration          DTD//EN\" \"http://www.hibernate.org/dtd/hibernate-configuration-3.0.dtd\">    <hibernate-configuration>    <session-factory>       <property name=\"connection.url\">jdbc:mysql://localhost/noob</property>       <property name=\"connection.driver_class\">com.mysql.jdbc.Driver</property>       <property name=\"dialect\">org.hibernate.dialect.MySQLInnoDBDialect</property>       <property name=\"connection.username\">root</property>       <property name=\"connection.password\"></property>       <!-- DB schema will be updated if needed -->       <property name=\"hbm2ddl.auto\">create-drop</property>       <property name=\"show_sql\">false</property>       <property name=\"format_sql\">false</property>   </session-factory>   </hibernate-configuration>"));
	
	@Test
	public void xmlFragment() throws Exception {	
		Assert.assertNull(XmlCheck.xmlCheck(xmlFragment));
	}	

	@Test
	public void xmlTrickyExamplent() throws Exception {	

		// shouldn't be any space before the first tag
		Assert.assertNotNull(XmlCheck.xmlCheck(xmlTrickyExample));
	}	
	
	@Test
	public void mixedXml() throws Exception {
		SAXParseException e = XmlCheck.xmlCheck(mixedXml);		
		Assert.assertNotNull(e);
		
		int columnNumber = e.getColumnNumber();
		int lineNumber = e.getLineNumber();		
		Assert.assertEquals(1, columnNumber);
		Assert.assertEquals(1, lineNumber);			   
	}

	@Test
	public void javaWithMistakes() throws Exception {
		SAXParseException e = XmlCheck.xmlCheck(mixedXml);		
		Assert.assertNotNull(e);
		
		int columnNumber = e.getColumnNumber();
		int lineNumber = e.getLineNumber();		
		Assert.assertEquals(1, columnNumber);
		Assert.assertEquals(1, lineNumber);			   
	}
	
	public static void sax(InputSource in) throws Exception {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser saxParser = factory.newSAXParser();
	 
		DefaultHandler handler = new DefaultHandler();	 
	    saxParser.parse(in, handler);
	}
	
	
	
	public static void dom(InputSource in) throws Exception {	    	 
    	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
    	Document doc = dBuilder.parse(in);
     
    	//optional, but recommended
    	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
    	doc.getDocumentElement().normalize();
	}
}
