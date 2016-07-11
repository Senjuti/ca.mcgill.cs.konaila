package ca.mcgill.cs.konaila.selection.features;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;
import org.apache.commons.lang3.StringUtils;

import ca.mcgill.cs.konaila.antlr.JavaFragmentBaseListener;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.AnnotationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.AnnotationNameContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.AssertStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.AssignmentExpressionContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.BooleanContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.BreakStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CallContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CharacterContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ClassBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ClassDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ClassOrInterfaceModifierContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ConstantDeclaratorContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ConstructorContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ContinueStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ElementValuePairContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExceptionDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExprDotIdentifierContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExprPrimaryContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ExpressionContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.FloatContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.IntegerContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.LocalVariableDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.NamedStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.NullContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimaryIdentifierContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimaryLiteralContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimarySuperContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PrimitiveTypeContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ResourceContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ReturnStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.StatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.StatementExpressionContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.StringContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ThrowStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TypeContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.VariableDeclaratorContext;
import ca.mcgill.cs.konaila.chopper.AntlrUtil;

public class PropertyExtractingAstListener extends JavaFragmentBaseListener {

	private CommonTokenStream tokens;
	private int cid;
	private String query;
	
	private Collection<Property> properties = new ArrayList<Property>();
	
	public PropertyExtractingAstListener(CommonTokenStream tokens, int cid, String query) {
		this.tokens = tokens;
		this.cid = cid;
		this.query = query;
	}
	
	public Collection<Property> getProperties() {
		return properties;
	}
	
	private Property newProperty(ParserRuleContext ctx) {	
		Property property = new Property(ctx, tokens, cid);
		properties.add(property);
		return property;
	}

	private Property newProperty(ParserRuleContext ctx, String konailaProperty) {	
		Property property = new Property(ctx, konailaProperty, tokens, cid);
		properties.add(property);
		return property;
	}

	private Property newProperty(Token token, String konailaProperty) {	
		Property property = new Property(token, konailaProperty, tokens, cid);
		properties.add(property);
		return property;
	}
	
	///////////////////////////  query ///////////////////////////////
	
//	@Override
//	public void enterPrimaryIdentifier(PrimaryIdentifierContext ctx) {
//		String identifier = ctx.Identifier().getText();
//		List<String> codeWords = QueryUtil.getCodeWords(identifier);
//		for( String codeWord : codeWords ) {
//			boolean queryContainMethodName = QueryUtil.queryContainTerm(query, codeWord);
//			if (queryContainMethodName) {
//				newProperty(ctx, "IdentifierMatchedQueryTerm");
//			}			
//		}		
//	}
	
	@Override
	public void visitTerminal(TerminalNode ctx) {
		String text = ctx.getText();
		if( StringUtils.isAlpha(text) ) {
			List<String> codeWords = QueryUtil.getCodeWords(text);
			for( String codeWord : codeWords ) {
				boolean queryContainMethodName = QueryUtil.queryContainTerm(query, codeWord);
				if (queryContainMethodName) {
					newProperty(ctx.getSymbol(), "IdentifierMatchedQueryTerm");
				}			
			}
		}
		if( StringUtils.isAllUpperCase(text) ) {
			newProperty(ctx.getSymbol(), "IdentifierLiteral");
		}
	}
	
	/////////////////////// group 1 method call //////////////////////////
	
	@Override
	public void enterCall(CallContext ctx) {
		if( ctx.getChild(0) instanceof ExprDotIdentifierContext ) { 
// J55 lines 5-7:
//			mBuilder.setContentTitle("Picture Download")
//			    .setContentText("Download in progress")
//			    .setSmallIcon(R.drawable.ic_notification);
			ExprDotIdentifierContext exprDotIdentifier = ctx.getChild(ExprDotIdentifierContext.class, 0);
			TerminalNodeImpl callName = exprDotIdentifier.getChild(TerminalNodeImpl.class, 0 );
			newProperty(callName.getSymbol(), "CallName");
			return;
		} else if( ctx.getChild(0) instanceof ExprPrimaryContext) {
	
			
			ExprPrimaryContext exprPrimaryIdentifier = ctx.getChild(ExprPrimaryContext.class, 0);
			if( exprPrimaryIdentifier.getChild(0) instanceof PrimaryIdentifierContext ) {
//				J55
//				getSystem	
				PrimaryIdentifierContext primaryIdentifier = exprPrimaryIdentifier.getChild(PrimaryIdentifierContext.class, 0);
				TerminalNodeImpl callName = primaryIdentifier.getChild(TerminalNodeImpl.class, 0 );
				newProperty(callName.getSymbol(), "CallName");
				return;
			} else {
				// J8: super(context,attrs)
				// J51: super()
				newProperty(exprPrimaryIdentifier, "CallName");
				return;
			}
		} 
		

		// J28: new float[4]()
		String text = "J" + cid+ ": " + ctx.getText();
		newProperty(ctx);
		
	}
	
	// constructor call handled in anonymous class group
	
	/////////////////////  group 2  method declaration ////////////////////////////
		
	
	///////////////// group 3 field declarations ////////////////////////////////

	// method modifiers in enterClassOrInterfaceModifiers 
	
		
	///////////////// group 4 control flow constructs /////////////////////////
	
	
	
	///////////////// group 5 return statements //////////////////////////////
	
	@Override
	public void enterReturnStatement(ReturnStatementContext ctx) {		
		ExpressionContext expr = ctx.getChild(ExpressionContext.class, 0);
//		if( isReturnStatementNull(ctx) ) {
//			// J31 - /blockStatements/J31.java
//			newProperty(ctx, "ReturnVoid");
//		} else if( isReturnStatementWithLiteral(ctx)){
//			// J07
//			newProperty(ctx, "ReturnLiteral");
//		} else if( isReturnStatementWithIdentifier(ctx) ) {
//			// J54- /blockStatements/J54.java
//			newProperty(ctx, "ReturnIdentifier");
//		} 
				
		newProperty(ctx, "Return");
		
	}
	
	/////////////////// group 6 local variable declarations ////////////////////
	
	@Override
	public void enterLocalVariableDeclaration(LocalVariableDeclarationContext ctx) {
		if( isLocalVariablePrimitiveType(ctx) ) {
			newProperty(ctx, "LocalVariableDeclarationPrimitive");
		} else  {
			// local variable declaration that is not of a primitive type
		}	
	}
	
	///////////////  group 7 type and signatures /////////////////
	
	@Override
	public void enterClassOrInterfaceModifier(ClassOrInterfaceModifierContext ctx) {
		AnnotationContext a = ctx.getChild(AnnotationContext.class, 0);
		
		if( a == null ) {
			// J08 - class
			newProperty(ctx, "Modifier" + StringUtils.capitalize(ctx.getText()));
		} else {
			AnnotationNameContext annotation = a.getChild(AnnotationNameContext.class,0);
			String annotationText = annotation.getText();
			if( annotationText.equals("Override") ) {
				newProperty(ctx, "ModifierAnnotationOverride");
			} else if( annotationText.equals("Deprecated") ||
					annotationText.equals("SuppressWarnings") ||
					annotationText.equals("SafeVarargs") ||
					annotationText.equals("FunctionalInterface") ||
					annotationText.equals("Retention") ||
					annotationText.equals("Documented") ||
					annotationText.equals("Target") ||
					annotationText.equals("Inherited") ||
					annotationText.equals("Repeatable")) {
				newProperty(ctx, "ModifierAnnotationOtherPredefined");
			} else {
				newProperty(ctx, "ModifierAnnotationDefined");
			}
		}
	}

	@Override
	public void enterClassDeclaration(ClassDeclarationContext ctx) {
		// J39 (both extends and implements)
		int count = ctx.getChildCount();
		for( int i = 0; i < count; i++ ) {

			TerminalNodeImpl token = ctx.getChild(TerminalNodeImpl.class, i);
			if( token != null ) {			
				if( token.getText().equals("extends") ) {
					newProperty(token.getSymbol(), "Extends");
				} else if( token.getText().equals("implements")) {
					newProperty(token.getSymbol(), "Implements");
				}
			}
		}	
	}
	
	/////////////////// group 8 anonymous class ////////////////
	
	@Override
	public void enterConstructor(ConstructorContext ctx) {		
		if( isAnonymousClassCreation(ctx)) {
			newProperty(ctx, "AnonymousClassCreation");
		} else if( AntlrUtil.isNewArray(ctx)){
			newProperty(ctx, "ConstructorArray");
		} else {
			// J55
//			new NotificationCompat.Builder(this)
			String text = ctx.getText();
			newProperty(ctx, "ConstructorObject");
		}
	}

	/////////////////// group 9 comments /////////////////////////
	
//	see ExtractComment	
	
	
	/////////////////// group 10 exception handling //////////////////
	
	@Override
	public void enterExceptionDeclaration(ExceptionDeclarationContext ctx) {
		newProperty(ctx);
	}
	
	//////////////////// group 11 assignment ///////////////////////
	
	@Override
	public void enterAssignmentExpression(AssignmentExpressionContext ctx) {
		newProperty(ctx, "Assignment");
	}
	
	@Override
	public void enterConstantDeclarator(ConstantDeclaratorContext ctx) {
		newProperty(ctx);
	}
	
	@Override
	public void enterVariableDeclarator(VariableDeclaratorContext ctx) {
		newProperty(ctx, "Assignment");
	}
	
	@Override
	public void enterElementValuePair(ElementValuePairContext ctx) {
		newProperty(ctx);
	}
	
	@Override
	public void enterResource(ResourceContext ctx) {
		newProperty(ctx);
	}
	
	//////////////////////// primitive values ////////////////////////////////
	
	@Override
	public void enterNull(NullContext ctx) {
		if( cid == 31 ) { // cid=1235
			System.out.println();
		}		
		newProperty(ctx, "NullLiteral");
	}
	
	@Override
	public void enterInteger(IntegerContext ctx) {
		newProperty(ctx, "IntegerLiteral");
	}

	@Override
	public void enterFloat(FloatContext ctx) {
		newProperty(ctx, "FloatLiteral");
	}

	@Override
	public void enterBoolean(BooleanContext ctx) {
		newProperty(ctx, "BooleanLiteral");
	}
	
	@Override
	public void enterCharacter(CharacterContext ctx) {
		newProperty(ctx, "CharacterLiteral");
	}
	
	@Override
	public void enterString(StringContext ctx) {
		if( cid == 1236 ) {
			System.out.println();
		}
		newProperty(ctx, "StringLiteral");
	}
	
	/////////////////////////////////////////////////////////
		
	private boolean isSimpleStatement(StatementContext ctx) {
		ParserRuleContext child = ctx.getChild(ParserRuleContext.class, 0);
		return child !=null && 
				(child instanceof AssertStatementContext 
				|| child instanceof ReturnStatementContext
				|| child instanceof ThrowStatementContext
				|| child instanceof BreakStatementContext
				|| child instanceof ContinueStatementContext
				|| child instanceof StatementExpressionContext
				|| child instanceof NamedStatementContext);
	}
	
	private static boolean isReturnStatementNull(ReturnStatementContext ctx) {
		ExpressionContext expr = ctx.getChild(ExpressionContext.class, 0);
		return expr == null;
	}
	
	private static boolean isReturnStatementWithLiteral(ReturnStatementContext ctx) {
		ExpressionContext expr = ctx.getChild(ExpressionContext.class, 0);
		ParseTree child = expr.getChild(0);
		return child instanceof PrimaryLiteralContext;
	}
	
	private static boolean isReturnStatementWithIdentifier(ReturnStatementContext ctx) {
		ExpressionContext expr = ctx.getChild(ExpressionContext.class, 0);
		ParseTree child = expr.getChild(0);
		return child instanceof PrimaryIdentifierContext;
	}
	
	private static boolean isLocalVariablePrimitiveType(LocalVariableDeclarationContext ctx) {
		TypeContext type = ctx.getChild(TypeContext.class, 0);
		return type.getChild(PrimitiveTypeContext.class, 0) != null;
	}

	public static boolean isAnonymousClassCreation(ConstructorContext ctx) {
		ClassBodyContext body = AntlrUtil.containAnonymousClassCreation(ctx);
		return body != null;
	}
		
	

	
}
