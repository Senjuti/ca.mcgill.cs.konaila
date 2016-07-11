package ca.mcgill.cs.konaila.presentation.practices;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;
import org.antlr.v4.runtime.tree.ParseTree;

import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CallContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExpressionContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExpressionListContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimaryLiteralContext;


public class EliminatingParameterInMethodCall extends PresentationPractice<CallContext>  {	

	static final int argumentThreshold = 20;
	
	public EliminatingParameterInMethodCall(BufferedTokenStream tokens, TokenStreamRewriter rewriter) {
		super(tokens, rewriter);		
	}
	
	@Override
	public void apply(CallContext ctx) {

		ExpressionListContext exprList = ctx.expressionList();
		
		if( exprList != null
				&& exprList.getText().length() > argumentThreshold ) {	
			
//			cleanWhiteSpaces(ctx.expressionList());
			for( int i = 0; i < exprList.getChildCount(); i++) {
				ParseTree expr = exprList.getChild(i);
				
				if( expr instanceof ExpressionContext ) {
					ParseTree exprChild = expr.getChild(0);  

					if( exprChild instanceof PrimaryLiteralContext ) {
						// 1015: false, 0.2f, 1056: "Time", null, 1219: 10
						rewriter.replace(((PrimaryLiteralContext) exprChild).getStart(), 
								((PrimaryLiteralContext) exprChild).getStop(), 
								"...");
						
						System.out.println("--> EliminatingParameterInMethodCall: " + expr.getText() );
					} 
				}
			}
			
		}
		
	}

}
