package ca.mcgill.cs.konaila.presentation.practices;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.TokenStreamRewriter;

import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CallContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExprDotIdentifierContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimaryIdentifierContext;
import ca.mcgill.cs.konaila.presentation.SplitCamelCase;


public class ShortenIdentifiersPractice extends PresentationPractice<PrimaryIdentifierContext>  {	

	static final int formalParametersThreshold = 1;
	
	public ShortenIdentifiersPractice(BufferedTokenStream tokens, TokenStreamRewriter rewriter) {
		super(tokens, rewriter);		
	}
	
	@Override
	public void apply(PrimaryIdentifierContext ctx) {
		String identifier = tokens.getText(ctx);
//		System.out.println("-> " + identifier);
		ParserRuleContext parent = ctx.getParent();
		if( parent != null ) {
			ParserRuleContext parentParent = parent.getParent(); // info.add() -
																// ExprDotIdentifierContext		
			if( parentParent instanceof ExprDotIdentifierContext) { // getting rid of cases: Type.field
				if( parentParent != null ) {					
					ParserRuleContext parentParentParent = parentParent.getParent();
					if( parentParentParent instanceof CallContext ) {
						// 1146: event
//						System.out.println(".... " + tokens.getText(ctx));
						
						if( Character.isLowerCase(identifier.charAt(0)) ) { // variables
							
							if( SplitCamelCase.splitCamelCaseString(identifier).size() >= 2 ) {
								String shortened = ShortenIdentifiersToInitials.shorten(identifier);
								rewriter.replace(ctx.getStart(), ctx.getStop(), shortened);
								System.out.println("--> ShortenIdentifiersPractice: " + tokens.getText(ctx));
							}
						}
					}
				}
			}
		}
	}
}
