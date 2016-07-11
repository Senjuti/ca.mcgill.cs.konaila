package ca.mcgill.cs.konaila.presentation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;

import ca.mcgill.cs.konaila.antlr.JavaFragmentBaseListener;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CallContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExprCastContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodSignatureContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimaryIdentifierContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ReturnStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.StatementExpressionContext;
import ca.mcgill.cs.konaila.presentation.practices.PresentationPractice;

public class Presentation extends JavaFragmentBaseListener {

	private BufferedTokenStream tokens;
	private TokenStreamRewriter rewriter;
	private Map<Class, List<PresentationPractice>> presentationPractices = new HashMap<Class, List<PresentationPractice>>();
	private Stack<MethodDeclarationContext> methods = new Stack<MethodDeclarationContext>();

	public Presentation(BufferedTokenStream tokens, TokenStreamRewriter rewriter) {
		this.tokens = tokens;
		this.rewriter = rewriter;
	}

	public String getText() {
//		TokenStream tokens = rewriter.getTokenStream();
//		String text = tokens.getText();
		return rewriter.getText();
	}

	public TokenStreamRewriter getRewriter() {
		return rewriter;
	}

	public void addPractice(Class c, PresentationPractice practice) {
		List<PresentationPractice> practices = presentationPractices.get(c);
		if (practices == null) {
			practices = new ArrayList<PresentationPractice>();
			presentationPractices.put(c, practices);
		}
		practices.add(practice);
	}

	@Override
	public void enterCall(CallContext ctx) {
		apply(ctx);
	}

	@Override
	public void enterMethodSignature(MethodSignatureContext ctx) {
		apply(ctx);
	}
	
	@Override
	public void enterMethodDeclaration(MethodDeclarationContext ctx) {
		methods.push(ctx);
	}
	
	@Override
	public void exitMethodDeclaration(MethodDeclarationContext ctx) {
		if( methods.isEmpty()) {
			throw new RuntimeException("Unmatched method declarationin AST");
		}
		
		MethodDeclarationContext popped = methods.pop();
		if( !popped.equals(ctx)) {
			throw new RuntimeException("Unmatched method declaration in AST");
		}
	}

	@Override
	public void enterExprCast(ExprCastContext ctx) {
		apply(ctx);
	}

	@Override
	public void enterStatementExpression(StatementExpressionContext ctx) {
		apply(ctx);
	}
	
	@Override
	public void enterPrimaryIdentifier(PrimaryIdentifierContext ctx) {		
		apply(ctx);
	}
	
	@Override
	public void enterReturnStatement(ReturnStatementContext ctx) {
		if( methods.isEmpty() ) {
			// Apply presentation practice when the return statement
			// with enclosing method
			apply(ctx);
		} else {
			// Do nothing when the return statement with no enclosing method
		}
	}

	public void apply(ParserRuleContext ctx) {
		List<PresentationPractice> practices = presentationPractices.get(ctx
				.getClass());
		if (practices != null) {
			for (PresentationPractice p : practices) {
				p.apply(ctx);
			}
		}
	}

}
