package ca.mcgill.cs.konaila.presentation.practices;

import java.util.List;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

import ca.mcgill.cs.konaila.antlr.JavaFragmentLexer;

public class PresentationPractice<T extends ParserRuleContext>  {

	public static final String ELLIPSES = "...";
	
	protected BufferedTokenStream tokens;
	protected TokenStreamRewriter rewriter;
	
	public PresentationPractice(BufferedTokenStream tokens, TokenStreamRewriter rewriter) {
		this.tokens = tokens;
		this.rewriter = rewriter;
	}
	
	public void apply(T ctx) {
		
	}
	
	public void cleanWhiteSpaces(ParserRuleContext ctx) {
		cleanWhiteSpaces(tokens.getHiddenTokensToLeft(ctx.start.getTokenIndex(),
				JavaFragmentLexer.WHITESPACE));
		cleanWhiteSpaces(tokens.getHiddenTokensToLeft(ctx.stop.getTokenIndex(),
				JavaFragmentLexer.WHITESPACE));	
	}
	
	public void cleanWhiteSpaces(List<Token> whitespaces) {
		if( whitespaces != null ) {
			rewriter.delete(whitespaces.get(0), whitespaces.get(whitespaces.size()-1));
		}
	}

}
