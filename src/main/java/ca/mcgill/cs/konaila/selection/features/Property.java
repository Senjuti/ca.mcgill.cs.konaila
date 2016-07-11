package ca.mcgill.cs.konaila.selection.features;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import ca.mcgill.cs.konaila.chopper.SelectionUnit.Element;

public class Property {

	private Element element;
	private int cid;
	private CommonTokenStream tokens;
	private String antlrProperty;
	private String konailaProperty;
	private int enclosingUid;
	
	public Property(ParserRuleContext ctx, CommonTokenStream tokens, int cid) {
		this.tokens = tokens;
		this.cid = cid;
		this.antlrProperty = ctx.getClass().getSimpleName();
		this.konailaProperty = antlrProperty;
		setProperty(ctx.getStart(), ctx.getStop());
	}	

	public Property(ParserRuleContext ctx, String konailaProperty, CommonTokenStream tokens, int cid) {
		this.tokens = tokens;
		this.cid = cid;
		this.antlrProperty = ctx.getClass().getSimpleName();
		this.konailaProperty = konailaProperty;
		setProperty(ctx.getStart(), ctx.getStop());
	}
	
	public Property(Token token, String konailaProperty, CommonTokenStream tokens, int cid) {
		this.tokens = tokens;
		this.cid = cid;
		this.antlrProperty = "";
		this.konailaProperty = konailaProperty;
		setProperty(token, token);
	}
	
	public void setEnclosingUid(int enclosingUid) {
		this.enclosingUid = enclosingUid;
	}
	
	public int getEnclosingUid() { 
		return enclosingUid;
	}
	
	public String getAntlrProperty() {
		return antlrProperty;
	}

	public String getKonailaProperty() {
		return konailaProperty;
	}
	
	public int getCid() {
		return this.cid;
	}
	
	public Element getElement() {
		return element;
	}
	
	public void setProperty(Token start, Token end) {
		element = newProperty(start, end);
//		System.out.println("property: " + element.getCode());
	}
	
	private Element newProperty(Token start, Token end) {			
		int startLine = start.getLine();
		int startCharPositionInLine = start.getCharPositionInLine();		
		int startChar = start.getStartIndex();
		int startToken = start.getTokenIndex();
		
		int endLine = end.getLine();
		int endCharPositionInLine = end.getCharPositionInLine() + end.getText().length() ;	
		int endChar =  end.getStopIndex() + 1;
		int endToken = end.getTokenIndex();

		String code = tokens.getText(start,end); 
		return new Element(
				startLine,startCharPositionInLine,startChar,startToken,
				endLine,endCharPositionInLine,endChar,endToken, code);
	}
	
}


