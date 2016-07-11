package ca.mcgill.cs.konaila.presentation.practices;

import javax.lang.model.type.PrimitiveType;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;

import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.FormalParameterContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.FormalParameterListContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodSignatureContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TypeContext;


public class EliminatingParameterInMethodSignature extends PresentationPractice<MethodSignatureContext>  {	

	static final int formalParametersThreshold = 5;
	
	public EliminatingParameterInMethodSignature(BufferedTokenStream tokens, TokenStreamRewriter rewriter) {
		super(tokens, rewriter);		
	}
	
	@Override
	public void apply(MethodSignatureContext ctx) {

		FormalParameterListContext params = ctx.formalParameters().formalParameterList();
		
		if( params != null 
					&& params.getText().length() > formalParametersThreshold ) {
						
//			cleanWhiteSpaces(params);
			for( int i = 0; i < params.getChildCount(); i++) {
				
				ParseTree expr = params.getChild(i);
					
				if( expr instanceof FormalParameterContext ) {
					ParseTree exprChild = expr.getChild(0);  

					if( exprChild instanceof TypeContext ) {
						if( exprChild.getChild(0) instanceof PrimitiveType 
								|| exprChild.getChild(0).getText().equals("Object")) {
							// 1102: Object
							rewriter.replace(
									((FormalParameterContext) expr).getStart(), 
									((FormalParameterContext) expr).getStop(), 
									"...");
							
							System.out.println("--> EliminatingParameterInMethodSignature: " + expr.getText() );
						}
					} 
				}			
				
			}
		
		
			if (ctx.exceptionDeclaration() != null ) {
				// 1102
				rewriter.replace(ctx.exceptionDeclaration().start, ctx.exceptionDeclaration().stop, "");
			}
		}
		
	}

}
