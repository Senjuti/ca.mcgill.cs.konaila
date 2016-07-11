package ca.mcgill.cs.konaila.chopper;

import java.util.ArrayList;
import java.util.Collection;

import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.TerminalNodeImpl;

import ca.mcgill.cs.konaila.antlr.JavaFragmentBaseListener;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.BlockContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CallContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.CatchClauseContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ClassBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ClassBodyStaticBlockContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ClassDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ConstructorBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ConstructorContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ConstructorDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.DoStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.EllipsisTokenContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ElseBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.FinallyBlockContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ForControlContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ForStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.GenericMethodDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.IfBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.IfStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ImportDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.InterfaceBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.InterfaceDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.LocalVariableDeclarationStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MemberDeclarationClassContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MemberDeclarationConstructorContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MemberDeclarationFieldContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MemberDeclarationMethodContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodBodyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.MethodSignatureContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.PackageDeclarationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.ParExpressionContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.StatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.SwitchLabelContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.SwitchStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.SynchronizedStatementContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TryCatchFinallyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TryResourceContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TypeDeclarationAnnotationContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TypeDeclarationClassContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TypeDeclarationEmptyContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TypeDeclarationEnumContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.TypeDeclarationInterfaceContext;
import ca.mcgill.cs.konaila.antlr.JavaFragmentParser.WhileStatementContext;

public class ChopperAstListener extends JavaFragmentBaseListener {
	
	private CommonTokenStream tokens;
	private JavaFragmentParser parser;
	private int internalId;
	private String source;
	
	private ParseTreeProperty<MultiLineStatementSelectionUnit> complexNodeToUnit = 
			new ParseTreeProperty<MultiLineStatementSelectionUnit>();
	private ParseTreeProperty<SelectionUnit> simpleNodeToUnit = 
			new ParseTreeProperty<SelectionUnit>();
		
	private Collection<SelectionUnit> units = new ArrayList<SelectionUnit>();
	private Collection<SelectionUnit> ellipses = new ArrayList<SelectionUnit>();
	
	public ChopperAstListener(CommonTokenStream tokens, JavaFragmentParser parser, int cid, String source) {
		this.tokens = tokens;
		this.internalId = cid;
		this.parser = parser;
		this.source = source;
	}
	
	public Collection<SelectionUnit> getUnits() {
		return units;
	}

	public Collection<SelectionUnit> getEllipses() {
		return ellipses;
	}
	
	private SelectionUnit newUnit(ParserRuleContext ctx, Token start, Token end) {		
		SelectionUnit unit = new SelectionUnit(ctx, tokens, start, end, internalId);
		units.add(unit);
		simpleNodeToUnit.put(ctx,unit);
		return unit;
	}

	private SelectionUnit newUnit(ParserRuleContext ctx, Token start, Token end, String konailaNodeType) {
		SelectionUnit unit = new SelectionUnit(ctx, tokens, start, end, internalId, konailaNodeType);
		units.add(unit);
		simpleNodeToUnit.put(ctx,unit);
		return unit;
	}

	private SelectionUnit newDefaultUnit(ParserRuleContext ctx) {	
		SelectionUnit unit = new SelectionUnit(ctx, tokens, ctx.getStart(), ctx.getStop(), internalId);
		units.add(unit);
		simpleNodeToUnit.put(ctx,unit);
		return unit;
	}
	
	private SelectionUnit newDefaultUnit(ParserRuleContext ctx, String konailaNodeType) {	
		SelectionUnit unit = new SelectionUnit(ctx, tokens, ctx.getStart(), ctx.getStop(), internalId, konailaNodeType);
		units.add(unit);
		simpleNodeToUnit.put(ctx,unit);
		return unit;
	}

	private SelectionUnit newEllipsis(Token ellipsis) {
		SelectionUnit e = new SelectionUnit(ellipsis, tokens, internalId);
		ellipses.add(e);
		return e;
	}
	
	private MultiLineStatementSelectionUnit newMultiLineUnit(ParserRuleContext ctx) {
		MultiLineStatementSelectionUnit n = new MultiLineStatementSelectionUnit(ctx, tokens, internalId);
		complexNodeToUnit.put(ctx, n);
		units.add(n);
		return n;
	}

	private MultiLineStatementSelectionUnit newMultiLineUnit(ParserRuleContext ctx, String konailaNodeType) {
		MultiLineStatementSelectionUnit n = new MultiLineStatementSelectionUnit(ctx, tokens, internalId, konailaNodeType);
		complexNodeToUnit.put(ctx, n);
		units.add(n);
		return n;
	}
		
	private void finishUnit(ParserRuleContext ctx) {
		MultiLineStatementSelectionUnit node = complexNodeToUnit.get(ctx);
		if( node != null ) {
			node.finishElements();
		}
	}
	
	
	///////////////////////////// headers ///////////////////////////
	
	@Override
	public void enterPackageDeclaration(PackageDeclarationContext ctx) {
		// J40
		newDefaultUnit(ctx);	
	}

	@Override
	public void enterImportDeclaration(ImportDeclarationContext ctx) {
		// J40
		newDefaultUnit(ctx);
	}
	

	////////////////////////// typeDeclaration ///////////////////////////	
	
	@Override
	public void enterTypeDeclarationClass(TypeDeclarationClassContext ctx) {
		// J08
		ClassDeclarationContext decl = ctx.getChild(ClassDeclarationContext.class, 0);
		ClassBodyContext body = decl.getChild(ClassBodyContext.class, 0);
			
		// add class signature
		SelectionUnit unit = newUnit(ctx, ctx.getStart(), body.getStart(), "TypeSignature");
		unit.addLastElement(body.getStop(), body.getStop());
	}
	
	
	@Override
	public void enterClassBodyStaticBlock(ClassBodyStaticBlockContext ctx) {
		//TODO handle static block
	}
	
	@Override
	public void enterMemberDeclarationClass(MemberDeclarationClassContext ctx) {
		// J42
		ClassDeclarationContext decl  =ctx.getChild(ClassDeclarationContext.class, 0);
		ClassBodyContext body = decl.getChild(ClassBodyContext.class, 0);
		
		// add class body
		SelectionUnit unit = newUnit(ctx, ctx.getStart(), body.getStart(), "TypeSignature");
		unit.addLastElement(body.getStop(), body.getStop());
	}

	@Override
	public void enterTypeDeclarationEnum(TypeDeclarationEnumContext ctx) {		
		// TODO handle Enums
	}
	
	@Override
	public void enterTypeDeclarationInterface(TypeDeclarationInterfaceContext ctx) {
		// TODO need to test TypeDeclarationInterface
		InterfaceDeclarationContext decl = ctx.getChild(InterfaceDeclarationContext.class, 0);

		InterfaceBodyContext body = decl.getChild(InterfaceBodyContext.class, 0);
			
		SelectionUnit unit = newUnit(ctx, ctx.getStart(), body.getStart(), "InterfaceSignature");
		unit.addLastElement(body.getStop(), body.getStop());		
	}
	
	@Override
	public void enterTypeDeclarationAnnotation(TypeDeclarationAnnotationContext ctx) {		
		// TODO annotationTypeDeclaration
	}
	
	@Override
	public void enterTypeDeclarationEmpty(TypeDeclarationEmptyContext ctx) {		
		// ignore empty statement
	}
		
	
	/////////////////////////////  method declaration /////////////////////
	
	@Override
	public void enterMemberDeclarationMethod(MemberDeclarationMethodContext ctx) {
		MethodDeclarationContext decl = ctx.getChild(MethodDeclarationContext.class, 0);
		if( decl != null ) {
			MethodSignatureContext signature = decl.getChild(MethodSignatureContext.class,0);
			MethodBodyContext body = decl.getChild(MethodBodyContext.class,0);
				
			// add the signature
			if( body != null ) {
				SelectionUnit unit = newUnit(ctx, ctx.getStart(), body.getStart(), "MethodSignature");
				unit.addLastElement(body.getStop(), body.getStop());
			} else {
				newDefaultUnit(ctx, "MethodSignature");
			}
		}
	}

	@Override
	public void enterGenericMethodDeclaration(GenericMethodDeclarationContext ctx) {
		MethodDeclarationContext decl = ctx.getChild(MethodDeclarationContext.class, 0);
		if( decl != null ) {
			MethodSignatureContext signature = decl.getChild(MethodSignatureContext.class,0);
			MethodBodyContext body = decl.getChild(MethodBodyContext.class,0);
				
			// add the signature
			if( body != null ) {
				SelectionUnit unit = newUnit(ctx, ctx.getStart(), body.getStart(), "MethodSignature");
				unit.addLastElement(body.getStop(), body.getStop());
			} else {
				newDefaultUnit(ctx, "MethodSignature");
			}
		}
	}
	
	@Override
	public void enterMemberDeclarationField(MemberDeclarationFieldContext ctx) {
		newDefaultUnit(ctx, "FieldDeclaration");
	}

	@Override
	public void enterMemberDeclarationConstructor(MemberDeclarationConstructorContext ctx) {
		ConstructorDeclarationContext decl = ctx.getChild(ConstructorDeclarationContext.class, 0);
		if( decl != null ) {
			ConstructorBodyContext body = decl.getChild(ConstructorBodyContext.class,0);
				
			// add the signature
			SelectionUnit unit = newUnit(ctx, ctx.getStart(), body.getStart(), "ConstructorSignature");
			unit.addLastElement(body.getStop(), body.getStop());	
		}
	}
	
	////////////////////////////// statement /////////////////////////////

	// TODO /blockStatements/J37.java - StatementContext should be Expression
	
	@Override
	public void enterStatement(StatementContext ctx) {			
		if( AntlrUtil.isOnOneLine(ctx) ) {			
			newDefaultUnit(ctx, "SimpleStatement");
			return;
		} else { // multi line
			if( !isStatementWithABlock(ctx) ) {
				newMultiLineUnit(ctx, "SimpleStatement");
				return;
			}		
		}
	}
	
	@Override
	public void exitStatement(StatementContext ctx) {
		finishUnit(ctx);
	}
	
	@Override
	public void enterLocalVariableDeclarationStatement(LocalVariableDeclarationStatementContext ctx) {
		if( AntlrUtil.isOnOneLine(ctx) ) {			
			newDefaultUnit(ctx);
		} else { // multi line
			newMultiLineUnit(ctx);
		}
	}
	
	@Override
	public void exitLocalVariableDeclarationStatement(LocalVariableDeclarationStatementContext ctx) {
		finishUnit(ctx);
	}
	
	private boolean isStatementWithABlock(StatementContext ctx) {
		ParserRuleContext s = ctx.getChild(ParserRuleContext.class,0);
		return s != null && 
				(s instanceof IfStatementContext
				|| s instanceof ForStatementContext
				|| s instanceof WhileStatementContext
				|| s instanceof DoStatementContext 
				|| s instanceof TryCatchFinallyContext 
				|| s instanceof TryResourceContext
				|| s instanceof SwitchStatementContext 
				|| s instanceof SynchronizedStatementContext
				|| s instanceof BlockContext);
	}

	
	////////////////////////// statements that contains a block ///////////////////	
	
	@Override
	public void enterIfStatement(IfStatementContext ctx) {
		IfBodyContext ifBody = ctx.getChild(IfBodyContext.class, 0);
		if( ifBody == null) {
			// J14
			ParExpressionContext expression = ctx.getChild(ParExpressionContext.class,0);
			newUnit(ctx, ctx.getStart(), expression.getStop(), "IfWrapper");	
		} else {
			StatementContext s = ifBody.getChild(StatementContext.class, 0);
			BlockContext block = s.getChild(BlockContext.class, 0);
			if( block == null ) {
				// J13
				ParExpressionContext expression = ctx.getChild(ParExpressionContext.class, 0);
				newUnit(ctx, ctx.getStart(), expression.getStop(), "IfWrapper");			
			} else {
				// J52
				SelectionUnit unit = newUnit(ctx, ctx.getStart(), block.getStart(), "IfWrapper");
				unit.addLastElement(block.getStop(), block.getStop());
			}
		}
		
		ElseBodyContext elseBody = ctx.getChild(ElseBodyContext.class, 0 );
		if( elseBody != null ) {
			StatementContext s = elseBody.getChild(StatementContext.class, 0);
			BlockContext block = s.getChild(BlockContext.class, 0);
			Token elseKeyword = ctx.getChild(TerminalNodeImpl.class, 1).getSymbol();
			if( block == null ) {
				// J13
				newUnit(elseBody, elseKeyword, elseKeyword, "ElseWrapper");			
			} else {
				// J4
				SelectionUnit unit = newUnit(elseBody, elseKeyword, block.getStart(), "ElseWrapper");
				unit.addLastElement(block.getStop(), block.getStop());
			}
		}
	} 

	@Override
	public void enterForStatement(ForStatementContext ctx) {
		// J55, J47
		ForControlContext expression = ctx.getChild(ForControlContext.class, 0 );
		Token closeBracket = ctx.getChild(TerminalNodeImpl.class, 2).getSymbol();
		StatementContext forBody = ctx.getChild(StatementContext.class,0);
		BlockContext block = forBody.getChild(BlockContext.class,0);
		if( block != null ) {
			SelectionUnit unit = newUnit(ctx, ctx.getStart(), block.getStart(), "ForWrapper");
			unit.addLastElement(block.getStop(), block.getStop());
		} else {
			newUnit(ctx, ctx.getStart(), closeBracket, "ForWrapper");
		} 
	}
	
	@Override
	public void enterWhileStatement(WhileStatementContext ctx) {
		// J01
		ParExpressionContext expression = ctx.getChild(ParExpressionContext.class, 0 );
		StatementContext whileBody = ctx.getChild(StatementContext.class,0);
		BlockContext block = whileBody.getChild(BlockContext.class,0);
		if( block != null ) {
			// add expression
			SelectionUnit unit = newUnit(ctx, ctx.getStart(), block.getStart(), "WhileWrapper");
			unit.addLastElement(block.getStop(), block.getStop());
		} else {
			newUnit(ctx, ctx.getStart(), AntlrUtil.getPrevToken(whileBody.getStart(), tokens), "WhileWrapper");
		} 
	}
	
	@Override
	public void enterDoStatement(DoStatementContext ctx) {
		// TODO test do statements
		ParExpressionContext expression = ctx.getChild(ParExpressionContext.class, 0);
		StatementContext doBody = ctx.getChild(StatementContext.class, 0);
		Token whileKeyword = ctx.getChild(TerminalNodeImpl.class, 1).getSymbol();
		BlockContext block = doBody.getChild(BlockContext.class, 0);
		if( block != null ) {
			SelectionUnit unit = newUnit(ctx, ctx.getStart(), block.getStart(), "DoWrapper");
			unit.addLastElement(block.getStop(), ctx.getStop());
		} else {
			SelectionUnit unit = newUnit(ctx, ctx.getStart(), ctx.getStart(), "DoWrapper");
			unit.addLastElement(whileKeyword, ctx.getStop());
		} 
	}
	
	@Override
	public void enterTryCatchFinally(TryCatchFinallyContext ctx) {
		// J12, J55
		Token openParen = ctx.getChild(TerminalNodeImpl.class, 1).getSymbol();
		Token closeParen = ctx.getChild(TerminalNodeImpl.class, 2).getSymbol();
		SelectionUnit unit = newUnit(ctx, ctx.getStart(), openParen, "TryWrapper");
		unit.addLastElement(closeParen, closeParen);
		// handle catch and finally in other methods 
	} 
	
	@Override
	public void enterTryResource(TryResourceContext ctx) {
		// TODO handle TryResource
	}
	
	@Override
	public void enterSwitchStatement(SwitchStatementContext ctx) {
		// J21 - HERE
		Token openParen = ctx.getChild(TerminalNodeImpl.class, 1).getSymbol();
		Token closeParen = ctx.getChild(TerminalNodeImpl.class, 2).getSymbol();
		SelectionUnit unit = newUnit(ctx, ctx.getStart(), openParen, "SwitchWrapper");
		unit.addLastElement(closeParen, closeParen);			
		// handle "case" blocks in SwitchLabelContext
	}

	@Override
	public void enterSynchronizedStatement(SynchronizedStatementContext ctx) {
		// J09
		Token openParen = ctx.getChild(TerminalNodeImpl.class, 1).getSymbol();
		Token closeParen = ctx.getChild(TerminalNodeImpl.class, 2).getSymbol();
		SelectionUnit unit = newUnit(ctx, ctx.getStart(), openParen, "SynchronizedWrapper");
		unit.addLastElement(closeParen, closeParen);
	} 	

	
	@Override
	public void enterSwitchLabel(SwitchLabelContext ctx) {
		// J13
		newUnit(ctx, ctx.getStart(), ctx.getStop());
	}
	
	@Override
	public void enterCatchClause(CatchClauseContext ctx) {
		// J55
		Token openParen = ctx.getChild(TerminalNodeImpl.class, 4).getSymbol();
		Token closeParen = ctx.getChild(TerminalNodeImpl.class, 5).getSymbol();
		SelectionUnit unit = newUnit(ctx, ctx.getStart(), openParen, "CatchWrapper");
		unit.addLastElement(closeParen, closeParen);
	}
	
	@Override
	public void enterFinallyBlock(FinallyBlockContext ctx) {
		// J12
		Token openParen = ctx.getChild(TerminalNodeImpl.class, 1).getSymbol();
		Token closeParen = ctx.getChild(TerminalNodeImpl.class, 2).getSymbol();
		SelectionUnit unit = newUnit(ctx, ctx.getStart(), openParen, "FinallyWrapper");
		unit.addLastElement(closeParen, closeParen);		
	}

	//////////////////////////// anonymous class ///////////////////////

	@Override
	public void enterConstructor(ConstructorContext ctx) {		
		
		// J22, J02, J55, J37
		ClassBodyContext body = AntlrUtil.containAnonymousClassCreation(ctx);
		if(  body != null ) {
			// anonymous class call
			System.out.println("-->" + body.getText());
			System.out.println();

			MultiLineStatementSelectionUnit parentUnit = AntlrUtil.getParentUnit(ctx, complexNodeToUnit);
			if( parentUnit != null ) {

				ParserRuleContext parent = parentUnit.getNode();				
				
				Token openParen = body.getChild(TerminalNodeImpl.class, 0).getSymbol();
				Token closeParen = body.getChild(TerminalNodeImpl.class, 1).getSymbol();
				
				String text = tokens.getText(ctx);
				String parentText = tokens.getText(parent);
				
				if( AntlrUtil.isOnSameLinesAs(parent, ctx)) {
					parentUnit.addHole(AntlrUtil.getNextToken(openParen, tokens),
							AntlrUtil.getPrevToken(closeParen, tokens));
				} else if( parentUnit.isHoleSameLineAsAnElement(ctx) ) {
					// J22's anonymous class should go here.
					parentUnit.addHole(AntlrUtil.getNextToken(openParen, tokens),
							AntlrUtil.getPrevToken(closeParen, tokens));
				} else {
					SelectionUnit unit = newUnit(ctx, ctx.getStart(), openParen, "AnonymousClassCreation");
					unit.addLastElement(closeParen, closeParen);		
					
					parentUnit.addHole(ctx);
				}
			}
		} else {
			// normal constructor
			dealWithCallOrConstructor(ctx);
		}
	}

	@Override
	public void exitConstructor(ConstructorContext ctx) {	
		finishUnit(ctx);
	}
	
	//////////////////////////// call of call ///////////////////////////
	
	@Override
	public void enterCall(CallContext ctx) {
		dealWithCallOrConstructor(ctx);
	}
		
	public void dealWithCallOrConstructor(ParserRuleContext ctx) {
		
		MultiLineStatementSelectionUnit parent = AntlrUtil.getParentUnit(ctx, complexNodeToUnit);
		MultiLineStatementSelectionUnit parentStatement = AntlrUtil.getParentStatementUnit(ctx, complexNodeToUnit);
				
		boolean candidate = !AntlrUtil.isOnOneLine(ctx);	
		if( parent != null ) {					
			if( parentStatement != null ) {
				if( AntlrUtil.getNumberOfChildrenCalls(parentStatement.getNode()) >= 2 ) {
					candidate = true;
					// J55: add call "setSmallIcon" 
//					mBuilder.setContentTitle("Picture Download")
//				    .setContentText("Download in progress")
//				    .setSmallIcon(R.drawable.ic_notification);
				} else {
					candidate = false;
					// J55: eliminate case where the unit has 2 lines but 1 call
//					mNotifyManager =
//					        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
				}
			} else if( parent.getAntlrNodeType().equals("CallContext") ) {
				candidate = true;
				// J55: add call "setContentText" or "setContentTitle"
//				mBuilder.setContentTitle("Picture Download")
//			    .setContentText("Download in progress")
//			    .setSmallIcon(R.drawable.ic_notification);
			}
		}
		
		if( candidate ) {
			
			SelectionUnit parentSimpleUnit = AntlrUtil.getParentUnit(ctx, simpleNodeToUnit);
			
			if( parent != null && AntlrUtil.isOnSameLinesAs(parent.getNode(), ctx) ) {
				// same line as parent, don't need to create (J55: call "setSmallIcon")
				// (J1059: getContainer().run(true, true, new IRunnableWithProgress() {...});
				System.out.println();
			} 		
			else if ( parentSimpleUnit != null) {
				// tricky case: J22 line 9 "builder.setView(inflater.inflate(R.layout.dialog_signin, null))" 
				// in the context of the whole statement (line9-22)
				System.out.println();
			} else {
				if( AntlrUtil.isOnOneLine(ctx) ) {
					// on one line - create simple unit (J55: call "setContentTitle")

					newDefaultUnit(ctx,
							ctx.getClass().getSimpleName().substring(0, 
									ctx.getClass().getSimpleName().length() - "Context".length())
									+ "Unit");
				} else {
					// multi line - create multi line unit (J55: call "setContentText")
					newMultiLineUnit(ctx, 
							ctx.getClass().getSimpleName().substring(0, 
									ctx.getClass().getSimpleName().length() - "Context".length())
									+ "Unit");				
				}
				parent.addHole(ctx);
			}			
		}		
	}
	
	@Override
	public void exitCall(CallContext ctx) {
		finishUnit(ctx);		
	}
	
	////////////////////// ellipsis ////////////////////////
	
	@Override 
	public void enterEllipsisToken(EllipsisTokenContext ctx) {
		TerminalNode ellipsis = ctx.getChild(TerminalNode.class, 0);
		newEllipsis(ellipsis.getSymbol());
	}

}
