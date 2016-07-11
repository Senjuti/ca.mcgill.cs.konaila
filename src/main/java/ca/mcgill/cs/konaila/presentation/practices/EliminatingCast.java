package ca.mcgill.cs.konaila.presentation.practices;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.TokenStreamRewriter;

import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExprCastContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TypeContext;


public class EliminatingCast extends PresentationPractice<ExprCastContext>  {	

	static final int argumentThreshold = 20;
	
	public EliminatingCast(BufferedTokenStream tokens, TokenStreamRewriter rewriter) {
		super(tokens, rewriter);		
	}
	
	@Override
	public void apply(ExprCastContext ctx) {
		// 1083: CompilationUnit , 1220
		
		TypeContext type = ctx.type();

		String typeInString = type.getText();
		String codeBefore = rewriter.getText();
		
		rewriter.replace(type.getStart(), 
				type.getStop(), "...");	

		String codeAfter =  rewriter.getText();
		
		System.out.println("--> EliminatingCast ");
		
	}

}
