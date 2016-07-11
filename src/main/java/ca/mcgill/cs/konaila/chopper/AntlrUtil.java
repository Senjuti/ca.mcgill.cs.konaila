package ca.mcgill.cs.konaila.chopper;

import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ArrayCreatorRestContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.BlockStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CallContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ClassBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ClassCreatorRestContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ConstructorContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CreatedNameContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CreatorContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.JavaFragmentContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.LocalVariableDeclarationStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MemberDeclarationFieldContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimitiveTypeContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.StatementContext;

public class AntlrUtil {

	public static Token getNextToken(Token t, CommonTokenStream tokens) {
		List<Token> hiddenTokensToRight = tokens.getHiddenTokensToRight(t.getTokenIndex());
		if( t.getTokenIndex()==tokens.getTokens().size() ) {
			System.out.println();
		}
		return tokens.get(	t.getTokenIndex() 
				+ (t.getTokenIndex()==tokens.getTokens().size() ? 0 : 1 ) 
				+ (hiddenTokensToRight==null?0:hiddenTokensToRight.size()));
	}
	
	public static Token getPrevToken(Token t, CommonTokenStream tokens) {
		List<Token> hiddenTokensToLeft = tokens.getHiddenTokensToLeft(t.getTokenIndex());
		return tokens.get(t.getTokenIndex() 
				- (t.getTokenIndex()==0 ? 0 : 1) 
				- (hiddenTokensToLeft==null?0:hiddenTokensToLeft.size()));
	}
	
	//////////////////////////////////////////////////

	public static boolean isOnOneLine(ParserRuleContext ctx) {
		return ctx.getStart().getLine() == ctx.getStop().getLine();
	}
	
	public static boolean isOnSameLinesAs(ParserRuleContext enclosing, ParserRuleContext ctx) {
		int enclosingStart = enclosing.getStart().getLine();
		int enclosingStop = enclosing.getStop().getLine();
		int innerStart = ctx.getStart().getLine();
		int innerStop = ctx.getStop().getLine();
		return enclosingStart == innerStart && enclosingStop == innerStop;
//				|| enclosingStart == innerStart && enclosingStart == innerStop
//				|| enclosingStop == innerStart && enclosingStop == innerStop;
	}
		
	/////////////////////////////////////////////////////////////////
	
//	public static ParserRuleContext getParentUnit(ParserRuleContext ctx) { 
//		return recurseUpClosestUnit(ctx);
//	}		
//	
//	private static ParserRuleContext recurseUpClosestUnit(ParserRuleContext current) {
//		ParserRuleContext parent = current.getParent();
//		if( parent instanceof PackageDeclarationContext
//				|| parent instanceof ImportDeclarationContext
//				|| parent instanceof TypeDeclarationClassContext
//				|| parent instanceof ClassBodyStaticBlockContext
//				|| parent instanceof MemberDeclarationClassContext
//				|| parent instanceof TypeDeclarationEnumContext
//				|| parent instanceof TypeDeclarationInterfaceContext
//				|| parent instanceof TypeDeclarationAnnotationContext
//				|| parent instanceof TypeDeclarationEmptyContext
//				|| parent instanceof MemberDeclarationMethodContext
//				|| parent instanceof MemberDeclarationFieldContext
//				|| parent instanceof MemberDeclarationConstructorContext				
//				|| parent instanceof IfStatementContext				
//				|| parent instanceof ForStatementContext
//				|| parent instanceof WhileStatementContext				
//				|| parent instanceof DoStatementContext
//				|| parent instanceof TryCatchFinallyContext
//				|| parent instanceof TryResourceContext
//				|| parent instanceof SwitchStatementContext
//				|| parent instanceof SynchronizedStatementContext
//				|| parent instanceof SwitchLabelContext
//				|| parent instanceof CatchClauseContext
//				|| parent instanceof FinallyBlockContext
//			) {
//			return parent;			
//		} else if( parent instanceof StatementContext
//			|| parent instanceof LocalVariableDeclarationStatementContext ) {
//			return parent;
//		} else if( parent instanceof ExprNewContext 
//				&& containAnonymousClassCreation((ExprNewContext)parent) != null) {
//			return parent;
//		} else if( parent instanceof JavaFragmentContext ) {
//			// kitchen sink
//			return null;
//		} else {
//			return recurseUpClosestUnit(parent);
//		}
//	}
	
	public static <T> T getParentUnit( ParserRuleContext current, 
			ParseTreeProperty<T> map) {
		T unit = recurseUpClosestUnit(current, map);				
		return unit;
	}
	
	private static <T> T recurseUpClosestUnit(ParserRuleContext current,
			ParseTreeProperty<T> map) {
		
		String text = current.getText();		
		
		ParserRuleContext parent = current.getParent();
		
		String parentText = parent.getText();
		
		T parentSelectionUnit = map.get(parent);
		if( parentSelectionUnit != null ) {
			return parentSelectionUnit;
		} else if( parent instanceof JavaFragmentContext // kitchen sink
				|| parent instanceof StatementContext
				|| parent instanceof LocalVariableDeclarationStatementContext ) {
			return null;			
		} else {
			return recurseUpClosestUnit(parent, map);
		}
	}
	
	public static ParserRuleContext getParent( ParserRuleContext current, 
			ParseTreeProperty<MultiLineStatementSelectionUnit> map) {
		return recurseUpClosest(current, map);
	}
	
	private static ParserRuleContext recurseUpClosest(ParserRuleContext current,
			ParseTreeProperty<MultiLineStatementSelectionUnit> map) {
		ParserRuleContext parent = current.getParent();
		MultiLineStatementSelectionUnit parentSelectionUnit = map.get(parent);
		if( parentSelectionUnit != null ) {
			return parent;
		} else if( parent instanceof JavaFragmentContext ) {
			// kitchen sink
			return null;			
		} else {
			return recurseUpClosest(parent, map);
		}
	}
	
	/////////////////////////////
	
	public static CallContext getParentCall(ParserRuleContext current) {
		return recurseUpParentCall(current);
	}
	
	private static CallContext recurseUpParentCall(ParserRuleContext current) {
		ParserRuleContext parent = current.getParent();
		if( parent instanceof CallContext) {
			return (CallContext)parent;			
		} else if( parent instanceof BlockStatementContext 
				|| parent instanceof MemberDeclarationFieldContext) {
			// stop, reached statement which is a unit
			return null;
		} else {
			// keep going
			return recurseUpParentCall(parent);
		}
	}
	
	public static ClassBodyContext containAnonymousClassCreation(ConstructorContext ctx) {
		CreatorContext creator = ctx.getChild(CreatorContext.class,0);
		if( creator!= null ) {
			ClassCreatorRestContext classCreator = creator.getChild(ClassCreatorRestContext.class, 0);
			if( classCreator != null ) {
				ClassBodyContext body = classCreator.getChild(ClassBodyContext.class, 0);
				String text = ctx.getText();
				return body;
			}
		}
		return null;
	}
	
	public static boolean isNewArray(ConstructorContext ctx) {
		CreatorContext creator = ctx.getChild(CreatorContext.class,0);
		if( creator!= null ) {
			ArrayCreatorRestContext array = creator.getChild(ArrayCreatorRestContext.class, 0);
			if( array != null ) {
				return true;
			} else {			
				CreatedNameContext classCreator = creator.getChild(CreatedNameContext.class, 0);
				if( classCreator != null ) {
					PrimitiveTypeContext body = classCreator.getChild(PrimitiveTypeContext.class, 0);
					if( body != null ) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	////////////////////////////
	
	public static MultiLineStatementSelectionUnit getParentStatementUnit( ParserRuleContext current, 
			ParseTreeProperty<MultiLineStatementSelectionUnit> map) {
		return recurseUpClosestStatementUnit(current, map);
	}
	
	private static MultiLineStatementSelectionUnit recurseUpClosestStatementUnit(ParserRuleContext current,
			ParseTreeProperty<MultiLineStatementSelectionUnit> map) {
		ParserRuleContext parent = current.getParent();
		
		String text = current.getText();
		String parentText = parent.getText();
		
		MultiLineStatementSelectionUnit parentSelectionUnit = map.get(parent);
		if( (parent instanceof StatementContext
				|| 	parent instanceof LocalVariableDeclarationStatementContext)
			&& parentSelectionUnit != null ) {
			return parentSelectionUnit;
		} else if( parent instanceof JavaFragmentContext ) { // kitchen sink
			return null;			
		} else {
			return recurseUpClosestStatementUnit(parent, map);
		}
	}
	
	////////////////////////////////////////////////////
	
	public static int getNumberOfChildrenCalls(ParserRuleContext current) {
		return recurseDownNumberOfCalls(current);
	}
	
	public static int recurseDownNumberOfCalls(ParserRuleContext current) {
		int numberOfCalls = 0;
		for( int i=0; i < current.getChildCount(); i++) {
			ParseTree child = current.getChild(i);
			if( child instanceof CallContext ) {
				numberOfCalls += 1 + recurseDownNumberOfCalls((ParserRuleContext)child);
			} else if( child instanceof ParserRuleContext ) {
				numberOfCalls += recurseDownNumberOfCalls((ParserRuleContext)child);
			}
		}
		return numberOfCalls;
	}
	
}
