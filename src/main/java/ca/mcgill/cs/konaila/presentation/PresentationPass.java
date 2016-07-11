package ca.mcgill.cs.konaila.presentation;

import java.util.List;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStreamRewriter;

import ca.mcgill.cs.konaila.antlr.JavaFragmentBaseListener;
import ca.mcgill.cs.konaila.antlr.JavaFragmentLexer;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.AnnotationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CallContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ElseBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.IfBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodSignatureContext;

public class PresentationPass extends JavaFragmentBaseListener {
	
	boolean printBody = true;
	int formalParametersThreshold = 50;
	int argumentThreshold = 50;
	
	private BufferedTokenStream tokens;
	private TokenStreamRewriter rewriter;
	
	public PresentationPass(BufferedTokenStream tokens, TokenStreamRewriter rewriter) {
		this.tokens = tokens;
		this.rewriter = rewriter;
	}
	
	public String getText() {
		return rewriter.getText();
	}
	
	public TokenStreamRewriter getRewriter() {
		return rewriter;
	}
	
	@Override
	public void enterMethodSignature(MethodSignatureContext ctx) {
		if( ctx.formalParameters() != null 
				&& ctx.formalParameters().getText().length() > formalParametersThreshold ) { 
			rewriter.replace(ctx.formalParameters().start, ctx.formalParameters().stop, "(...)");
		} 
		if (ctx.exceptionDeclaration() != null ) {
			rewriter.replace(ctx.exceptionDeclaration().start, ctx.exceptionDeclaration().stop, "");
		}
	}
	
	@Override
	public void enterAnnotation(AnnotationContext ctx) {
		cleanWhiteSpaces(ctx);
		rewriter.delete(ctx.start, ctx.stop);
	}

	@Override
	public void enterMethodBody(MethodBodyContext ctx) {
		if( !printBody ) {
			rewriter.replace(ctx.start, ctx.stop, "{...}");
		}
	}

	@Override
	public void enterCall(CallContext ctx) {
		if( ctx.expressionList() != null
				&& ctx.expressionList().getText().length() > argumentThreshold ) {
			cleanWhiteSpaces(ctx.expressionList());
			rewriter.replace(ctx.expressionList().start, ctx.expressionList().stop, "...");
		}
	}
	
	@Override
	public void exitIfBody(IfBodyContext ctx) {
		rewriter.replace(ctx.start, ctx.stop, "...");		
	}	

	@Override
	public void exitElseBody(ElseBodyContext ctx) {
		rewriter.replace(ctx.start, ctx.stop, "...");		
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
