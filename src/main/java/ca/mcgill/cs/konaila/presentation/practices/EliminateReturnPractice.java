package ca.mcgill.cs.konaila.presentation.practices;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExpressionContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ReturnStatementContext;


public class EliminateReturnPractice extends PresentationPractice<ReturnStatementContext>  {	
	
	public EliminateReturnPractice(BufferedTokenStream tokens, TokenStreamRewriter rewriter) {
		super(tokens, rewriter);		
	}
	
	@Override
	public void apply(ReturnStatementContext ctx) {
		ExpressionContext expr = ctx.getChild(ExpressionContext.class, 0);
		String exprString = tokens.getText(expr);		
		String shortened = exprString + ";";		
		rewriter.replace(ctx.getStart(), ctx.getStop(), shortened);
		
	}
}
