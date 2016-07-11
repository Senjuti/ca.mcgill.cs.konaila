package ca.mcgill.cs.konaila.chopper;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class SelectionUnit {

	protected Element firstElement;
	protected Element lastElement;
	protected int internalId;
	protected CommonTokenStream tokens;
	protected String antlrNodeType;
	protected String konailaNodeType;
	protected ParserRuleContext node;
		
	public SelectionUnit(ParserRuleContext ctx, CommonTokenStream tokens, 
			Token start, Token end, int internalId, String konailaNodeType) {
		this(ctx,tokens,internalId,konailaNodeType);
		addFirstElement(start,end);
	}

	public SelectionUnit(ParserRuleContext ctx, CommonTokenStream tokens, 
			Token start, Token end, int cid) {
		this(ctx,tokens,cid);
		addFirstElement(start,end);
	}
	
	public SelectionUnit(Token comment, String antlrNodeType, CommonTokenStream tokens, int internalId) {
		this.tokens = tokens;
		this.internalId = internalId;
		this.antlrNodeType = antlrNodeType;
		this.konailaNodeType = antlrNodeType;
		this.node = null;
		addComment(comment);
	}
	
	public SelectionUnit(Token ellipsis, CommonTokenStream tokens, int internalId) {
		this.tokens = tokens;
		this.internalId = internalId;
		this.antlrNodeType = "";
		this.konailaNodeType = "";
		this.node = null;
		addFirstElement(ellipsis,ellipsis);
	}
	
	protected SelectionUnit(ParserRuleContext ctx, CommonTokenStream tokens, int internalId, String konailaNodeType) {
		this.tokens = tokens;
		this.internalId = internalId;
		this.antlrNodeType = ctx.getClass().getSimpleName();
		this.konailaNodeType = konailaNodeType;
		this.node = ctx;
	}
	
	protected SelectionUnit(ParserRuleContext ctx, CommonTokenStream tokens, int internalId) {
		this.tokens = tokens;
		this.internalId = internalId;
		this.antlrNodeType = ctx.getClass().getSimpleName();
		this.konailaNodeType = antlrNodeType.substring(0, antlrNodeType.length() - "Context".length());
		this.node = ctx;
	}
	
	public int getStartLine() {
		return getFirstElement().getStartLine();
	}
	
	public int getEndLine() {
		return getLastElement().getEndLine();		
	}
	
	public int getStartCharPositionInLine() {
		return getFirstElement().getStartCharPositionInLine();
	}

	public int getStartChar() {
		return getFirstElement().getStartChar();
	}
	
	public int getEndCharPositionInLine() {
		return getLastElement().getEndCharPositionInLine();
	}

	public int getEndChar() {
		return getLastElement().getEndChar();
	}	
	
	public String getAntlrNodeType() {
		return antlrNodeType;
	}

	public String getKonailaNodeType() {
		return konailaNodeType;
	}
	
	public String getCodeDisplayWhole() {
		if( lastElement == null ) {
			return firstElement.getCode();
		} else {
			return firstElement.getCode() + HoleElement.HOLE + lastElement.getCode();
		}
	}
	
	public List<Element> getElementsWithoutHoles() {
		List<Element> elements = new ArrayList<Element>();
		elements.add(firstElement);
		if( lastElement != null ) {
			elements.add(lastElement);
		}
		return elements;
	}
		
	@Override
	public String toString() {
		return getCodeDisplayWhole();
	}
	
	public int getInternalId() {
		return this.internalId;
	}
	
	public Element getFirstElement() {
		return firstElement;
	}
	
	public Element getLastElement() {
		return lastElement == null ? firstElement : lastElement;  
	}

	public void addFirstElement(Token start, Token end) {
		firstElement = newElement(start, end);
//		System.out.println("main unit: " + firstElement.getCode());
	}

	public void addLastElement(Token start, Token end) {
		lastElement = newElement(start, end);	
//		System.out.println("end unit: " + lastElement.getCode());
	}
	
	public void addComment(Token comment) {
		int startLine = comment.getLine();
		int startCharPositionInLine = comment.getCharPositionInLine();		
		int startChar = comment.getStartIndex();
		int startToken = comment.getTokenIndex();
		
		
		String[] commentLines = comment.getText().split("\r\n|\r|\n");
		int endLine = startLine +  commentLines.length - 1;
		int endCharPositionInLine = commentLines[commentLines.length-1].length();	
		int endChar = comment.getStopIndex() + 1;
		int endToken = comment.getTokenIndex();
		
		String code = comment.getText(); 
		firstElement = new Element(
				startLine,startCharPositionInLine,startChar,startToken,
				endLine,endCharPositionInLine,endChar,endToken, code);
	}

	protected Element newElement(Token start, Token end) {			
		int startLine = start.getLine();
		int startCharPositionInLine = start.getCharPositionInLine();		
		int startChar = start.getStartIndex();
		int startToken = start.getTokenIndex();
		
		int endLine = end.getLine();
		int endCharPositionInLine = end.getCharPositionInLine() + end.getText().length();
		int endChar =  end.getStopIndex() + 1;
		int endToken = end.getTokenIndex();

		String code = tokens.getText(start,end); 

		return new Element(
				startLine,startCharPositionInLine,startChar,startToken,
				endLine,endCharPositionInLine,endChar,endToken, code);
	}
	

	public static class Element {
		protected int startLine;
		protected int startCharPositionInLine;
		protected int startChar;
		protected int startToken;
		protected int endLine;
		protected int endCharPositionInLine;
		protected int endChar;
		protected int endToken;
		protected String code;
		
		public Element(int startLine, int startCharPositionInLine,
				int startChar, int startToken,
				int endLine, int endCharPositionInLine,
				int endChar, int endToken,
				 String code) {
			super();
			this.startLine = startLine;
			this.startCharPositionInLine = startCharPositionInLine;
			this.startChar = startChar;
			this.startToken = startToken;
			this.endLine = endLine;
			this.endCharPositionInLine = endCharPositionInLine;
			this.endChar = endChar;
			this.endToken = endToken;
			this.code = code;
		}
		
		public String getCode() {
			return code;
		}

		public int getStartLine() {
			return startLine;
		}

		public int getStartCharPositionInLine() {
			return startCharPositionInLine;
		}

		public int getStartChar() {
			return startChar;
		}
		
		public int getStartToken() { 
			return startToken;
		}

		public int getEndLine() {
			return endLine;
		}

		public int getEndCharPositionInLine() {
			return endCharPositionInLine;
		}

		public int getEndChar() {
			return endChar;
		}	
		
		public int getEndToken() { 
			return endToken;
		}
		
		@Override
		public String toString() {
			return getCode();
		}
	}
	
	public static class HoleElement extends Element {

		public static final String HOLE = " ... ";
		private String holeOriginalString;

		public HoleElement(int startLine, int startCharPositionInLine,
				int startChar, int startToken,
				int endLine, int endCharPositionInLine,
				int endChar, int endToken, String holeOriginalString) {
			super(startLine, startCharPositionInLine, startChar, startToken,
					endLine, endCharPositionInLine, endChar, endToken, HOLE);
			this.holeOriginalString = holeOriginalString;
		}

		@Override
		public String toString() {
			return HOLE;
		}
		
		public String getOriginalString() {
			return holeOriginalString;
		}
		
	}
}


