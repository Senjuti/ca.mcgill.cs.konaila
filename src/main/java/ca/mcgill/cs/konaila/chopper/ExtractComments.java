package ca.mcgill.cs.konaila.chopper;

import java.util.ArrayList;
import java.util.Collection;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class ExtractComments {
	
	private static final int BLOCK_COMMENTS = 2;	
	private static final int LINE_COMMENTS = 3;
	
	
	public static Collection<SelectionUnit> extractComments(CommonTokenStream tokenStream, int internalId) {

		Collection<SelectionUnit> result = new ArrayList<SelectionUnit>(); 
		
		tokenStream.fill();
		Collection<Token> tokens = tokenStream.getTokens();
		
		for(Token t : tokens) {	
			// J12, J31 first and multi-line, J23 one-liner JavaDoc
			if( t.getChannel() == BLOCK_COMMENTS ) {
				if( t.getText().startsWith("/**") ) {
					SelectionUnit unit = new SelectionUnit(t, "JavaDocComment", tokenStream, internalId);
					result.add(unit);
				} else {
					SelectionUnit unit = new SelectionUnit(t, "BasicBlockComment", tokenStream, internalId);
					result.add(unit);					
				}
			} else if ( t.getChannel() == LINE_COMMENTS ) { 
				SelectionUnit unit = new SelectionUnit(t, "LineComment", tokenStream, internalId);
				result.add(unit);
			}
		}
		return result;		
	}
}
