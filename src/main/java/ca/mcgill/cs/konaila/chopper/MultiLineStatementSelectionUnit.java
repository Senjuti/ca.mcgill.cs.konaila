package ca.mcgill.cs.konaila.chopper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

public class MultiLineStatementSelectionUnit extends SelectionUnit {

	protected List<Hole> holes = new ArrayList<Hole>();
	protected List<Element> elementsWithHoles = new ArrayList<Element>();
	protected Element unit;

	public MultiLineStatementSelectionUnit(ParserRuleContext ctx, CommonTokenStream tokens, 
			int cid) {
		super(ctx,tokens,cid);
		this.unit = newElement(ctx.getStart(), ctx.getStop());
	}
	
	public MultiLineStatementSelectionUnit(ParserRuleContext ctx, CommonTokenStream tokens, 
			int cid, String konailaNodeType) {
		super(ctx,tokens,cid, konailaNodeType);
		this.unit = newElement(ctx.getStart(), ctx.getStop());
	}
	
	public ParserRuleContext getNode() {
		return node;
	}
	
	public void addHole(ParserRuleContext hole) {		
		addHole(hole.getStart(), hole.getStop());
	}
	
	public void addHole(Token start, Token stop) {
		
		boolean doNotAdd = false;
		Iterator<Hole> it = holes.iterator();
		while( it.hasNext() ) {
			Hole h = it.next();
			if( subsume( h.getStart(), h.getStop(), start, stop) ) { 
				// an existing hole subsume the current one, don't add 
				// J55: lines 5-7
				doNotAdd=true;
				break;
			} else if( subsume( start, stop, h.getStart(), h.getStop())) {
				// the current one subsume an existing one
				// J55: lines 5-7
				it.remove();
			} 
		}
		
		if( !doNotAdd ) {
			holes.add(new Hole(start, stop, tokens));
		}
		
		Collections.sort(holes, new Comparator<Hole>(){
			@Override
			public int compare(Hole o1, Hole o2) {
				return o1.getStart().getStartIndex() - o2.getStart().getStartIndex();
			}			
		});
		
		finishElements();
	}
	
	private boolean subsume(Token outerStart, Token outerStop, Token innerStart, Token innerStop) {
		return outerStart.getStartIndex() <= innerStart.getStartIndex() && 
			outerStop.getStopIndex() >= innerStop.getStopIndex();
	}
	
	public void finishElements() {
		
		elementsWithHoles.clear();
		
		Iterator<Hole> it = holes.iterator();
		Hole hole = null;
		Token start = node.getStart();
		while( it.hasNext() ) {
			hole = it.next();
			
			if( start == hole.getStart() ) {
				elementsWithHoles.add( newHole(hole) );
			} else {
				Token stop = AntlrUtil.getPrevToken(hole.getStart(), tokens);
				elementsWithHoles.add(newElement(start, stop));
				elementsWithHoles.add( newHole(hole) );
			}
			start = AntlrUtil.getNextToken(hole.getStop(), tokens);
		}
		
		if( hole == node.getStop() ) {
			elementsWithHoles.add( newHole(hole) );
		} else {
			elementsWithHoles.add( newElement( start, node.getStop()) );
		}
	}
	
	public boolean isHoleSameLineAsAnElement(ParserRuleContext hole) {
		Token holeStart = hole.getStart();
		Token holeStop = hole.getStop();
		for( Element e : elementsWithHoles ) {
			if( e.getStartLine() == holeStart.getLine() 
					&& e.getEndLine() == holeStop.getLine() ) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String getCodeDisplayWhole() {		
		String result = "";
		for( Element e : elementsWithHoles ) {
			result += e.getCode();
		}	
		return result;
	}
	
	@Override
	public String toString() {
		if( elementsWithHoles.size() != 0 ) {
			return getCodeDisplayWhole();
		} else {
			return konailaNodeType;
		}
	}
	
	@Override
	public Element getFirstElement() {
//		for( Element e : elementsWithHoles ) {
//			if( e.getCode().equals(HOLE) ) {
//				continue;
//			}
//			return e;
//		}
		if( elementsWithHoles.size() >= 1 ) {
			return elementsWithHoles.get(0);
		}
		return null;
	}
	
	@Override
	public Element getLastElement() {
//		ListIterator<Element> it = elementsWithHoles.listIterator(elementsWithHoles.size());
//		while ( it.hasPrevious() ) {
//			Element e = it.previous();
//			if( e.getCode().equals(HOLE)) {
//				continue;
//			}
//			return e;
//		}
		if( elementsWithHoles.size() >= 1 ) {
			return elementsWithHoles.get(elementsWithHoles.size()-1);
		}
		return null;
	}
	
	@Override
	public List<Element> getElementsWithoutHoles() {
		List<Element> result = new ArrayList<Element>();
		for( Element e : elementsWithHoles) {
			if( e instanceof HoleElement ) { //.getCode().equals(HOLE) ) {
				continue;
			}	
			result.add(e);
		}
		return result;
	}
	
	public List<HoleElement> getHoles() {
		List<HoleElement> result = new ArrayList<HoleElement>();
		for( Element e : elementsWithHoles) {
			if( e instanceof HoleElement ) { 
				result.add((HoleElement)e);
			}	
		}
		return result;
	}
	
	protected Element newHole(Hole hole) {
		Token start = hole.getStart();
		Token end = hole.getStop();
		int startLine = start.getLine();
		int startCharPositionInLine = start.getCharPositionInLine();		
		int startChar = start.getStartIndex();
		int startToken = start.getTokenIndex();
		
		int endLine = end.getLine();
		int endCharPositionInLine = end.getCharPositionInLine() + end.getText().length() - 1;	
		int endChar =  end.getStopIndex() + 1;
		int endToken = end.getTokenIndex();
		
		String text = hole.toString();
		
		return new HoleElement(
				startLine,startCharPositionInLine,startChar,startToken,
				endLine,endCharPositionInLine,endChar,endToken, hole.toString());
	}
	
	public static class Hole {
		Token start;
		Token stop;
		CommonTokenStream tokens;
		public Hole(Token start, Token stop, CommonTokenStream tokens) {
			this.start = start;
			this.stop = stop;
			this.tokens=tokens;
		}
		public Token getStart() {
			return start;
		}
		public Token getStop() {
			return stop;
		}
		@Override
		public String toString() {
//			Token stop = AntlrUtil.getPrevToken(start, tokens);
			String text = tokens.getText(start, stop);
			return text;
		}		
	}	 


}


