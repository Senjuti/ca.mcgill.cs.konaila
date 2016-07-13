// Generated from JavaFragment.g4 by ANTLR 4.2.2

package ca.mcgill.cs.konaila.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class JavaFragmentParser extends Parser {
	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		ABSTRACT=1, ASSERT=2, BOOLEAN=3, BREAK=4, BYTE=5, CASE=6, CATCH=7, CHAR=8, 
		CLASS=9, CONST=10, CONTINUE=11, DEFAULT=12, DO=13, DOUBLE=14, ELSE=15, 
		ENUM=16, EXTENDS=17, FINAL=18, FINALLY=19, FLOAT=20, FOR=21, IF=22, GOTO=23, 
		IMPLEMENTS=24, IMPORT=25, INSTANCEOF=26, INT=27, INTERFACE=28, LONG=29, 
		NATIVE=30, NEW=31, PACKAGE=32, PRIVATE=33, PROTECTED=34, PUBLIC=35, RETURN=36, 
		SHORT=37, STATIC=38, STRICTFP=39, SUPER=40, SWITCH=41, SYNCHRONIZED=42, 
		THIS=43, THROW=44, THROWS=45, TRANSIENT=46, TRY=47, VOID=48, VOLATILE=49, 
		WHILE=50, IntegerLiteral=51, FloatingPointLiteral=52, BooleanLiteral=53, 
		CharacterLiteral=54, StringLiteral=55, NullLiteral=56, LPAREN=57, RPAREN=58, 
		LBRACE=59, RBRACE=60, LBRACK=61, RBRACK=62, SEMI=63, COMMA=64, DOT=65, 
		ASSIGN=66, GT=67, LT=68, BANG=69, TILDE=70, QUESTION=71, COLON=72, EQUAL=73, 
		LE=74, GE=75, NOTEQUAL=76, AND=77, OR=78, INC=79, DEC=80, ADD=81, SUB=82, 
		MUL=83, DIV=84, BITAND=85, BITOR=86, CARET=87, MOD=88, ADD_ASSIGN=89, 
		SUB_ASSIGN=90, MUL_ASSIGN=91, DIV_ASSIGN=92, AND_ASSIGN=93, OR_ASSIGN=94, 
		XOR_ASSIGN=95, MOD_ASSIGN=96, LSHIFT_ASSIGN=97, RSHIFT_ASSIGN=98, URSHIFT_ASSIGN=99, 
		Identifier=100, AT=101, ELLIPSIS4=102, ELLIPSIS3=103, ELLIPSIS2=104, WS=105, 
		BLOCK_COMMENT=106, LINE_COMMENT=107;
	public static final String[] tokenNames = {
		"<INVALID>", "'abstract'", "'assert'", "'boolean'", "'break'", "'byte'", 
		"'case'", "'catch'", "'char'", "'class'", "'const'", "'continue'", "'default'", 
		"'do'", "'double'", "'else'", "'enum'", "'extends'", "'final'", "'finally'", 
		"'float'", "'for'", "'if'", "'goto'", "'implements'", "'import'", "'instanceof'", 
		"'int'", "'interface'", "'long'", "'native'", "'new'", "'package'", "'private'", 
		"'protected'", "'public'", "'return'", "'short'", "'static'", "'strictfp'", 
		"'super'", "'switch'", "'synchronized'", "'this'", "'throw'", "'throws'", 
		"'transient'", "'try'", "'void'", "'volatile'", "'while'", "IntegerLiteral", 
		"FloatingPointLiteral", "BooleanLiteral", "CharacterLiteral", "StringLiteral", 
		"'null'", "'('", "')'", "'{'", "'}'", "'['", "']'", "';'", "','", "'.'", 
		"'='", "'>'", "'<'", "'!'", "'~'", "'?'", "':'", "'=='", "'<='", "'>='", 
		"'!='", "'&&'", "'||'", "'++'", "'--'", "'+'", "'-'", "'*'", "'/'", "'&'", 
		"'|'", "'^'", "'%'", "'+='", "'-='", "'*='", "'/='", "'&='", "'|='", "'^='", 
		"'%='", "'<<='", "'>>='", "'>>>='", "Identifier", "'@'", "'....'", "'...'", 
		"'..'", "WS", "BLOCK_COMMENT", "LINE_COMMENT"
	};
	public static final int
		RULE_javaFragment = 0, RULE_compilationUnit = 1, RULE_packageDeclaration = 2, 
		RULE_importDeclaration = 3, RULE_typeDeclaration = 4, RULE_modifier = 5, 
		RULE_classOrInterfaceModifier = 6, RULE_variableModifier = 7, RULE_classDeclaration = 8, 
		RULE_typeParameters = 9, RULE_typeParameter = 10, RULE_typeBound = 11, 
		RULE_enumDeclaration = 12, RULE_enumConstants = 13, RULE_enumConstant = 14, 
		RULE_enumBodyDeclarations = 15, RULE_interfaceDeclaration = 16, RULE_typeList = 17, 
		RULE_classBody = 18, RULE_interfaceBody = 19, RULE_classBodyDeclaration = 20, 
		RULE_memberDeclaration = 21, RULE_methodDeclaration = 22, RULE_methodSignature = 23, 
		RULE_exceptionDeclaration = 24, RULE_genericMethodDeclaration = 25, RULE_constructorDeclaration = 26, 
		RULE_genericConstructorDeclaration = 27, RULE_fieldDeclaration = 28, RULE_interfaceBodyDeclaration = 29, 
		RULE_interfaceMemberDeclaration = 30, RULE_constDeclaration = 31, RULE_constantDeclarator = 32, 
		RULE_interfaceMethodDeclaration = 33, RULE_genericInterfaceMethodDeclaration = 34, 
		RULE_variableDeclarators = 35, RULE_variableDeclarator = 36, RULE_variableDeclaratorId = 37, 
		RULE_variableInitializer = 38, RULE_arrayInitializer = 39, RULE_enumConstantName = 40, 
		RULE_type = 41, RULE_classOrInterfaceType = 42, RULE_primitiveType = 43, 
		RULE_typeArguments = 44, RULE_typeArgument = 45, RULE_qualifiedNameList = 46, 
		RULE_formalParameters = 47, RULE_formalParameterList = 48, RULE_formalParameter = 49, 
		RULE_lastFormalParameter = 50, RULE_methodBody = 51, RULE_constructorBody = 52, 
		RULE_qualifiedName = 53, RULE_literal = 54, RULE_annotation = 55, RULE_annotationName = 56, 
		RULE_elementValuePairs = 57, RULE_elementValuePair = 58, RULE_elementValue = 59, 
		RULE_elementValueArrayInitializer = 60, RULE_annotationTypeDeclaration = 61, 
		RULE_annotationTypeBody = 62, RULE_annotationTypeElementDeclaration = 63, 
		RULE_annotationTypeElementRest = 64, RULE_annotationMethodOrConstantRest = 65, 
		RULE_annotationMethodRest = 66, RULE_annotationConstantRest = 67, RULE_defaultValue = 68, 
		RULE_blockStatements = 69, RULE_blockStatement = 70, RULE_localVariableDeclarationStatement = 71, 
		RULE_localVariableDeclaration = 72, RULE_statement = 73, RULE_block = 74, 
		RULE_assertStatement = 75, RULE_ifStatement = 76, RULE_ifBody = 77, RULE_elseBody = 78, 
		RULE_forStatement = 79, RULE_whileStatement = 80, RULE_doStatement = 81, 
		RULE_tryCatchFinally = 82, RULE_tryResource = 83, RULE_switchStatement = 84, 
		RULE_synchronizedStatement = 85, RULE_returnStatement = 86, RULE_throwStatement = 87, 
		RULE_breakStatement = 88, RULE_continueStatement = 89, RULE_namedStatement = 90, 
		RULE_catchClause = 91, RULE_catchType = 92, RULE_finallyBlock = 93, RULE_resourceSpecification = 94, 
		RULE_resources = 95, RULE_resource = 96, RULE_switchBlockStatementGroup = 97, 
		RULE_switchLabel = 98, RULE_forControl = 99, RULE_forInit = 100, RULE_enhancedForControl = 101, 
		RULE_forUpdate = 102, RULE_parExpression = 103, RULE_expressionList = 104, 
		RULE_statementExpression = 105, RULE_constantExpression = 106, RULE_expression = 107, 
		RULE_primary = 108, RULE_superPrimary = 109, RULE_creator = 110, RULE_createdName = 111, 
		RULE_innerCreator = 112, RULE_arrayCreatorRest = 113, RULE_classCreatorRest = 114, 
		RULE_explicitGenericInvocation = 115, RULE_nonWildcardTypeArguments = 116, 
		RULE_typeArgumentsOrDiamond = 117, RULE_nonWildcardTypeArgumentsOrDiamond = 118, 
		RULE_superSuffix = 119, RULE_explicitGenericInvocationSuffix = 120, RULE_arguments = 121, 
		RULE_ellipsisToken = 122;
	public static final String[] ruleNames = {
		"javaFragment", "compilationUnit", "packageDeclaration", "importDeclaration", 
		"typeDeclaration", "modifier", "classOrInterfaceModifier", "variableModifier", 
		"classDeclaration", "typeParameters", "typeParameter", "typeBound", "enumDeclaration", 
		"enumConstants", "enumConstant", "enumBodyDeclarations", "interfaceDeclaration", 
		"typeList", "classBody", "interfaceBody", "classBodyDeclaration", "memberDeclaration", 
		"methodDeclaration", "methodSignature", "exceptionDeclaration", "genericMethodDeclaration", 
		"constructorDeclaration", "genericConstructorDeclaration", "fieldDeclaration", 
		"interfaceBodyDeclaration", "interfaceMemberDeclaration", "constDeclaration", 
		"constantDeclarator", "interfaceMethodDeclaration", "genericInterfaceMethodDeclaration", 
		"variableDeclarators", "variableDeclarator", "variableDeclaratorId", "variableInitializer", 
		"arrayInitializer", "enumConstantName", "type", "classOrInterfaceType", 
		"primitiveType", "typeArguments", "typeArgument", "qualifiedNameList", 
		"formalParameters", "formalParameterList", "formalParameter", "lastFormalParameter", 
		"methodBody", "constructorBody", "qualifiedName", "literal", "annotation", 
		"annotationName", "elementValuePairs", "elementValuePair", "elementValue", 
		"elementValueArrayInitializer", "annotationTypeDeclaration", "annotationTypeBody", 
		"annotationTypeElementDeclaration", "annotationTypeElementRest", "annotationMethodOrConstantRest", 
		"annotationMethodRest", "annotationConstantRest", "defaultValue", "blockStatements", 
		"blockStatement", "localVariableDeclarationStatement", "localVariableDeclaration", 
		"statement", "block", "assertStatement", "ifStatement", "ifBody", "elseBody", 
		"forStatement", "whileStatement", "doStatement", "tryCatchFinally", "tryResource", 
		"switchStatement", "synchronizedStatement", "returnStatement", "throwStatement", 
		"breakStatement", "continueStatement", "namedStatement", "catchClause", 
		"catchType", "finallyBlock", "resourceSpecification", "resources", "resource", 
		"switchBlockStatementGroup", "switchLabel", "forControl", "forInit", "enhancedForControl", 
		"forUpdate", "parExpression", "expressionList", "statementExpression", 
		"constantExpression", "expression", "primary", "superPrimary", "creator", 
		"createdName", "innerCreator", "arrayCreatorRest", "classCreatorRest", 
		"explicitGenericInvocation", "nonWildcardTypeArguments", "typeArgumentsOrDiamond", 
		"nonWildcardTypeArgumentsOrDiamond", "superSuffix", "explicitGenericInvocationSuffix", 
		"arguments", "ellipsisToken"
	};

	@Override
	public String getGrammarFileName() { return "JavaFragment.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JavaFragmentParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class JavaFragmentContext extends ParserRuleContext {
		public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
			return getRuleContext(SwitchBlockStatementGroupContext.class,i);
		}
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public CompilationUnitContext compilationUnit() {
			return getRuleContext(CompilationUnitContext.class,0);
		}
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
			return getRuleContexts(SwitchBlockStatementGroupContext.class);
		}
		public JavaFragmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_javaFragment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterJavaFragment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitJavaFragment(this);
		}
	}

	public final JavaFragmentContext javaFragment() throws RecognitionException {
		JavaFragmentContext _localctx = new JavaFragmentContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_javaFragment);
		int _la;
		try {
			int _alt;
			setState(268);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(246); compilationUnit();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(247); methodDeclaration();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(248); blockStatements();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(252);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << LBRACE) | (1L << SEMI))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (Identifier - 68)) | (1L << (AT - 68)) | (1L << (ELLIPSIS4 - 68)) | (1L << (ELLIPSIS3 - 68)) | (1L << (ELLIPSIS2 - 68)))) != 0)) {
					{
					{
					setState(249); classBodyDeclaration();
					}
					}
					setState(254);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(258);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(255); classBodyDeclaration();
						}
						} 
					}
					setState(260);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,1,_ctx);
				}
				setState(261); blockStatements();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==CASE || _la==DEFAULT) {
					{
					{
					setState(262); switchBlockStatementGroup();
					}
					}
					setState(267);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CompilationUnitContext extends ParserRuleContext {
		public List<ImportDeclarationContext> importDeclaration() {
			return getRuleContexts(ImportDeclarationContext.class);
		}
		public TerminalNode EOF() { return getToken(JavaFragmentParser.EOF, 0); }
		public PackageDeclarationContext packageDeclaration() {
			return getRuleContext(PackageDeclarationContext.class,0);
		}
		public ImportDeclarationContext importDeclaration(int i) {
			return getRuleContext(ImportDeclarationContext.class,i);
		}
		public List<TypeDeclarationContext> typeDeclaration() {
			return getRuleContexts(TypeDeclarationContext.class);
		}
		public TypeDeclarationContext typeDeclaration(int i) {
			return getRuleContext(TypeDeclarationContext.class,i);
		}
		public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compilationUnit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterCompilationUnit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitCompilationUnit(this);
		}
	}

	public final CompilationUnitContext compilationUnit() throws RecognitionException {
		CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_compilationUnit);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(271);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				{
				setState(270); packageDeclaration();
				}
				break;
			}
			setState(276);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==IMPORT) {
				{
				{
				setState(273); importDeclaration();
				}
				}
				setState(278);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(282);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << CLASS) | (1L << ENUM) | (1L << FINAL) | (1L << INTERFACE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SEMI))) != 0) || _la==AT) {
				{
				{
				setState(279); typeDeclaration();
				}
				}
				setState(284);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(285); match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PackageDeclarationContext extends ParserRuleContext {
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_packageDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPackageDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPackageDeclaration(this);
		}
	}

	public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
		PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_packageDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(287); annotation();
				}
				}
				setState(292);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(293); match(PACKAGE);
			setState(294); qualifiedName();
			setState(295); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ImportDeclarationContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_importDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterImportDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitImportDeclaration(this);
		}
	}

	public final ImportDeclarationContext importDeclaration() throws RecognitionException {
		ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_importDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(297); match(IMPORT);
			setState(299);
			_la = _input.LA(1);
			if (_la==STATIC) {
				{
				setState(298); match(STATIC);
				}
			}

			setState(301); qualifiedName();
			setState(304);
			_la = _input.LA(1);
			if (_la==DOT) {
				{
				setState(302); match(DOT);
				setState(303); match(MUL);
				}
			}

			setState(306); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeDeclarationContext extends ParserRuleContext {
		public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDeclaration; }
	 
		public TypeDeclarationContext() { }
		public void copyFrom(TypeDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class TypeDeclarationInterfaceContext extends TypeDeclarationContext {
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public TypeDeclarationInterfaceContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeDeclarationInterface(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeDeclarationInterface(this);
		}
	}
	public static class TypeDeclarationEmptyContext extends TypeDeclarationContext {
		public TypeDeclarationEmptyContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeDeclarationEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeDeclarationEmpty(this);
		}
	}
	public static class TypeDeclarationEnumContext extends TypeDeclarationContext {
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public TypeDeclarationEnumContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeDeclarationEnum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeDeclarationEnum(this);
		}
	}
	public static class TypeDeclarationAnnotationContext extends TypeDeclarationContext {
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public TypeDeclarationAnnotationContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeDeclarationAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeDeclarationAnnotation(this);
		}
	}
	public static class TypeDeclarationClassContext extends TypeDeclarationContext {
		public ClassOrInterfaceModifierContext classOrInterfaceModifier(int i) {
			return getRuleContext(ClassOrInterfaceModifierContext.class,i);
		}
		public List<ClassOrInterfaceModifierContext> classOrInterfaceModifier() {
			return getRuleContexts(ClassOrInterfaceModifierContext.class);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public TypeDeclarationClassContext(TypeDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeDeclarationClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeDeclarationClass(this);
		}
	}

	public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
		TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typeDeclaration);
		int _la;
		try {
			int _alt;
			setState(337);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				_localctx = new TypeDeclarationClassContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(311);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
					{
					{
					setState(308); classOrInterfaceModifier();
					}
					}
					setState(313);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(314); classDeclaration();
				}
				break;

			case 2:
				_localctx = new TypeDeclarationEnumContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
					{
					{
					setState(315); classOrInterfaceModifier();
					}
					}
					setState(320);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(321); enumDeclaration();
				}
				break;

			case 3:
				_localctx = new TypeDeclarationInterfaceContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(325);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0) || _la==AT) {
					{
					{
					setState(322); classOrInterfaceModifier();
					}
					}
					setState(327);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(328); interfaceDeclaration();
				}
				break;

			case 4:
				_localctx = new TypeDeclarationAnnotationContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(332);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(329); classOrInterfaceModifier();
						}
						} 
					}
					setState(334);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
				}
				setState(335); annotationTypeDeclaration();
				}
				break;

			case 5:
				_localctx = new TypeDeclarationEmptyContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(336); match(SEMI);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModifierContext extends ParserRuleContext {
		public ClassOrInterfaceModifierContext classOrInterfaceModifier() {
			return getRuleContext(ClassOrInterfaceModifierContext.class,0);
		}
		public ModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitModifier(this);
		}
	}

	public final ModifierContext modifier() throws RecognitionException {
		ModifierContext _localctx = new ModifierContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_modifier);
		int _la;
		try {
			setState(341);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case FINAL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case STATIC:
			case STRICTFP:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(339); classOrInterfaceModifier();
				}
				break;
			case NATIVE:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
				enterOuterAlt(_localctx, 2);
				{
				setState(340);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NATIVE) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassOrInterfaceModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ClassOrInterfaceModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterClassOrInterfaceModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitClassOrInterfaceModifier(this);
		}
	}

	public final ClassOrInterfaceModifierContext classOrInterfaceModifier() throws RecognitionException {
		ClassOrInterfaceModifierContext _localctx = new ClassOrInterfaceModifierContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_classOrInterfaceModifier);
		int _la;
		try {
			setState(345);
			switch (_input.LA(1)) {
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(343); annotation();
				}
				break;
			case ABSTRACT:
			case FINAL:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case STATIC:
			case STRICTFP:
				enterOuterAlt(_localctx, 2);
				{
				setState(344);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableModifierContext extends ParserRuleContext {
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public VariableModifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableModifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterVariableModifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitVariableModifier(this);
		}
	}

	public final VariableModifierContext variableModifier() throws RecognitionException {
		VariableModifierContext _localctx = new VariableModifierContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_variableModifier);
		try {
			setState(349);
			switch (_input.LA(1)) {
			case FINAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(347); match(FINAL);
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(348); annotation();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterClassDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitClassDeclaration(this);
		}
	}

	public final ClassDeclarationContext classDeclaration() throws RecognitionException {
		ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_classDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(351); match(CLASS);
			setState(352); match(Identifier);
			setState(354);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(353); typeParameters();
				}
			}

			setState(358);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(356); match(EXTENDS);
				setState(357); type();
				}
			}

			setState(362);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(360); match(IMPLEMENTS);
				setState(361); typeList();
				}
			}

			setState(364); classBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParametersContext extends ParserRuleContext {
		public List<TypeParameterContext> typeParameter() {
			return getRuleContexts(TypeParameterContext.class);
		}
		public TypeParameterContext typeParameter(int i) {
			return getRuleContext(TypeParameterContext.class,i);
		}
		public TypeParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeParameters(this);
		}
	}

	public final TypeParametersContext typeParameters() throws RecognitionException {
		TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366); match(LT);
			setState(367); typeParameter();
			setState(372);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(368); match(COMMA);
				setState(369); typeParameter();
				}
				}
				setState(374);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(375); match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeParameterContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public TypeBoundContext typeBound() {
			return getRuleContext(TypeBoundContext.class,0);
		}
		public TypeParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeParameter(this);
		}
	}

	public final TypeParameterContext typeParameter() throws RecognitionException {
		TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_typeParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(377); match(Identifier);
			setState(380);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(378); match(EXTENDS);
				setState(379); typeBound();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeBoundContext extends ParserRuleContext {
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeBoundContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeBound; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeBound(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeBound(this);
		}
	}

	public final TypeBoundContext typeBound() throws RecognitionException {
		TypeBoundContext _localctx = new TypeBoundContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_typeBound);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(382); type();
			setState(387);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITAND) {
				{
				{
				setState(383); match(BITAND);
				setState(384); type();
				}
				}
				setState(389);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumDeclarationContext extends ParserRuleContext {
		public TerminalNode ENUM() { return getToken(JavaFragmentParser.ENUM, 0); }
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public EnumConstantsContext enumConstants() {
			return getRuleContext(EnumConstantsContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public EnumBodyDeclarationsContext enumBodyDeclarations() {
			return getRuleContext(EnumBodyDeclarationsContext.class,0);
		}
		public EnumDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterEnumDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitEnumDeclaration(this);
		}
	}

	public final EnumDeclarationContext enumDeclaration() throws RecognitionException {
		EnumDeclarationContext _localctx = new EnumDeclarationContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_enumDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(390); match(ENUM);
			setState(391); match(Identifier);
			setState(394);
			_la = _input.LA(1);
			if (_la==IMPLEMENTS) {
				{
				setState(392); match(IMPLEMENTS);
				setState(393); typeList();
				}
			}

			setState(396); match(LBRACE);
			setState(398);
			_la = _input.LA(1);
			if (_la==Identifier || _la==AT) {
				{
				setState(397); enumConstants();
				}
			}

			setState(401);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(400); match(COMMA);
				}
			}

			setState(404);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(403); enumBodyDeclarations();
				}
			}

			setState(406); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumConstantsContext extends ParserRuleContext {
		public List<EnumConstantContext> enumConstant() {
			return getRuleContexts(EnumConstantContext.class);
		}
		public EnumConstantContext enumConstant(int i) {
			return getRuleContext(EnumConstantContext.class,i);
		}
		public EnumConstantsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstants; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterEnumConstants(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitEnumConstants(this);
		}
	}

	public final EnumConstantsContext enumConstants() throws RecognitionException {
		EnumConstantsContext _localctx = new EnumConstantsContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_enumConstants);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(408); enumConstant();
			setState(413);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(409); match(COMMA);
					setState(410); enumConstant();
					}
					} 
				}
				setState(415);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,28,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumConstantContext extends ParserRuleContext {
		public AnnotationContext annotation(int i) {
			return getRuleContext(AnnotationContext.class,i);
		}
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public List<AnnotationContext> annotation() {
			return getRuleContexts(AnnotationContext.class);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public EnumConstantContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstant; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterEnumConstant(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitEnumConstant(this);
		}
	}

	public final EnumConstantContext enumConstant() throws RecognitionException {
		EnumConstantContext _localctx = new EnumConstantContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_enumConstant);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(419);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==AT) {
				{
				{
				setState(416); annotation();
				}
				}
				setState(421);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(422); match(Identifier);
			setState(424);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(423); arguments();
				}
			}

			setState(427);
			_la = _input.LA(1);
			if (_la==LBRACE) {
				{
				setState(426); classBody();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumBodyDeclarationsContext extends ParserRuleContext {
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public EnumBodyDeclarationsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumBodyDeclarations; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterEnumBodyDeclarations(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitEnumBodyDeclarations(this);
		}
	}

	public final EnumBodyDeclarationsContext enumBodyDeclarations() throws RecognitionException {
		EnumBodyDeclarationsContext _localctx = new EnumBodyDeclarationsContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_enumBodyDeclarations);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(429); match(SEMI);
			setState(433);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << LBRACE) | (1L << SEMI))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (Identifier - 68)) | (1L << (AT - 68)) | (1L << (ELLIPSIS4 - 68)) | (1L << (ELLIPSIS3 - 68)) | (1L << (ELLIPSIS2 - 68)))) != 0)) {
				{
				{
				setState(430); classBodyDeclaration();
				}
				}
				setState(435);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public InterfaceBodyContext interfaceBody() {
			return getRuleContext(InterfaceBodyContext.class,0);
		}
		public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterInterfaceDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitInterfaceDeclaration(this);
		}
	}

	public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
		InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_interfaceDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(436); match(INTERFACE);
			setState(437); match(Identifier);
			setState(439);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(438); typeParameters();
				}
			}

			setState(443);
			_la = _input.LA(1);
			if (_la==EXTENDS) {
				{
				setState(441); match(EXTENDS);
				setState(442); typeList();
				}
			}

			setState(445); interfaceBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeListContext extends ParserRuleContext {
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeList(this);
		}
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(447); type();
			setState(452);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(448); match(COMMA);
				setState(449); type();
				}
				}
				setState(454);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyContext extends ParserRuleContext {
		public ClassBodyDeclarationContext classBodyDeclaration(int i) {
			return getRuleContext(ClassBodyDeclarationContext.class,i);
		}
		public List<ClassBodyDeclarationContext> classBodyDeclaration() {
			return getRuleContexts(ClassBodyDeclarationContext.class);
		}
		public ClassBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterClassBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitClassBody(this);
		}
	}

	public final ClassBodyContext classBody() throws RecognitionException {
		ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_classBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(455); match(LBRACE);
			setState(459);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << LBRACE) | (1L << SEMI))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (Identifier - 68)) | (1L << (AT - 68)) | (1L << (ELLIPSIS4 - 68)) | (1L << (ELLIPSIS3 - 68)) | (1L << (ELLIPSIS2 - 68)))) != 0)) {
				{
				{
				setState(456); classBodyDeclaration();
				}
				}
				setState(461);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(462); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceBodyContext extends ParserRuleContext {
		public InterfaceBodyDeclarationContext interfaceBodyDeclaration(int i) {
			return getRuleContext(InterfaceBodyDeclarationContext.class,i);
		}
		public List<InterfaceBodyDeclarationContext> interfaceBodyDeclaration() {
			return getRuleContexts(InterfaceBodyDeclarationContext.class);
		}
		public InterfaceBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterInterfaceBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitInterfaceBody(this);
		}
	}

	public final InterfaceBodyContext interfaceBody() throws RecognitionException {
		InterfaceBodyContext _localctx = new InterfaceBodyContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_interfaceBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(464); match(LBRACE);
			setState(468);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOID) | (1L << VOLATILE) | (1L << SEMI))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (Identifier - 68)) | (1L << (AT - 68)))) != 0)) {
				{
				{
				setState(465); interfaceBodyDeclaration();
				}
				}
				setState(470);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(471); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassBodyDeclarationContext extends ParserRuleContext {
		public ClassBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classBodyDeclaration; }
	 
		public ClassBodyDeclarationContext() { }
		public void copyFrom(ClassBodyDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ClassBodyMemberDeclarationContext extends ClassBodyDeclarationContext {
		public MemberDeclarationContext memberDeclaration() {
			return getRuleContext(MemberDeclarationContext.class,0);
		}
		public ClassBodyMemberDeclarationContext(ClassBodyDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterClassBodyMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitClassBodyMemberDeclaration(this);
		}
	}
	public static class ClassBodyStaticBlockContext extends ClassBodyDeclarationContext {
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public ClassBodyStaticBlockContext(ClassBodyDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterClassBodyStaticBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitClassBodyStaticBlock(this);
		}
	}
	public static class EllipsisContext extends ClassBodyDeclarationContext {
		public EllipsisTokenContext ellipsisToken() {
			return getRuleContext(EllipsisTokenContext.class,0);
		}
		public EllipsisContext(ClassBodyDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterEllipsis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitEllipsis(this);
		}
	}
	public static class ClassBodyEmptyContext extends ClassBodyDeclarationContext {
		public ClassBodyEmptyContext(ClassBodyDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterClassBodyEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitClassBodyEmpty(this);
		}
	}

	public final ClassBodyDeclarationContext classBodyDeclaration() throws RecognitionException {
		ClassBodyDeclarationContext _localctx = new ClassBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_classBodyDeclaration);
		int _la;
		try {
			setState(483);
			switch ( getInterpreter().adaptivePredict(_input,39,_ctx) ) {
			case 1:
				_localctx = new ClassBodyEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(473); match(SEMI);
				}
				break;

			case 2:
				_localctx = new ClassBodyStaticBlockContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(475);
				_la = _input.LA(1);
				if (_la==STATIC) {
					{
					setState(474); match(STATIC);
					}
				}

				setState(477); match(LBRACE);
				setState(478); blockStatements();
				setState(479); match(RBRACE);
				}
				break;

			case 3:
				_localctx = new ClassBodyMemberDeclarationContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(481); memberDeclaration();
				}
				break;

			case 4:
				_localctx = new EllipsisContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(482); ellipsisToken();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MemberDeclarationContext extends ParserRuleContext {
		public MemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_memberDeclaration; }
	 
		public MemberDeclarationContext() { }
		public void copyFrom(MemberDeclarationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class MemberDeclarationGenericConstructorContext extends MemberDeclarationContext {
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public GenericConstructorDeclarationContext genericConstructorDeclaration() {
			return getRuleContext(GenericConstructorDeclarationContext.class,0);
		}
		public MemberDeclarationGenericConstructorContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMemberDeclarationGenericConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMemberDeclarationGenericConstructor(this);
		}
	}
	public static class MemberDeclarationFieldContext extends MemberDeclarationContext {
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public FieldDeclarationContext fieldDeclaration() {
			return getRuleContext(FieldDeclarationContext.class,0);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public MemberDeclarationFieldContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMemberDeclarationField(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMemberDeclarationField(this);
		}
	}
	public static class MemberDeclarationMethodContext extends MemberDeclarationContext {
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public MemberDeclarationMethodContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMemberDeclarationMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMemberDeclarationMethod(this);
		}
	}
	public static class MemberDeclarationClassContext extends MemberDeclarationContext {
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public MemberDeclarationClassContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMemberDeclarationClass(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMemberDeclarationClass(this);
		}
	}
	public static class MemberDeclarationEnumContext extends MemberDeclarationContext {
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public MemberDeclarationEnumContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMemberDeclarationEnum(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMemberDeclarationEnum(this);
		}
	}
	public static class MemberDeclarationConstructorContext extends MemberDeclarationContext {
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public MemberDeclarationConstructorContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMemberDeclarationConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMemberDeclarationConstructor(this);
		}
	}
	public static class MemberDeclarationGenericMethodContext extends MemberDeclarationContext {
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public GenericMethodDeclarationContext genericMethodDeclaration() {
			return getRuleContext(GenericMethodDeclarationContext.class,0);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public MemberDeclarationGenericMethodContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMemberDeclarationGenericMethod(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMemberDeclarationGenericMethod(this);
		}
	}
	public static class MemberDeclarationInterfaceContext extends MemberDeclarationContext {
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public MemberDeclarationInterfaceContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMemberDeclarationInterface(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMemberDeclarationInterface(this);
		}
	}
	public static class MemberDelcarationAnnotationTypeContext extends MemberDeclarationContext {
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public MemberDelcarationAnnotationTypeContext(MemberDeclarationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMemberDelcarationAnnotationType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMemberDelcarationAnnotationType(this);
		}
	}

	public final MemberDeclarationContext memberDeclaration() throws RecognitionException {
		MemberDeclarationContext _localctx = new MemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_memberDeclaration);
		int _la;
		try {
			int _alt;
			setState(548);
			switch ( getInterpreter().adaptivePredict(_input,49,_ctx) ) {
			case 1:
				_localctx = new MemberDeclarationMethodContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(488);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || _la==AT) {
					{
					{
					setState(485); modifier();
					}
					}
					setState(490);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(491); methodDeclaration();
				}
				break;

			case 2:
				_localctx = new MemberDeclarationGenericMethodContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(495);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || _la==AT) {
					{
					{
					setState(492); modifier();
					}
					}
					setState(497);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(498); genericMethodDeclaration();
				}
				break;

			case 3:
				_localctx = new MemberDeclarationFieldContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(502);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || _la==AT) {
					{
					{
					setState(499); modifier();
					}
					}
					setState(504);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(505); fieldDeclaration();
				}
				break;

			case 4:
				_localctx = new MemberDeclarationConstructorContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(509);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || _la==AT) {
					{
					{
					setState(506); modifier();
					}
					}
					setState(511);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(512); constructorDeclaration();
				}
				break;

			case 5:
				_localctx = new MemberDeclarationGenericConstructorContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(516);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || _la==AT) {
					{
					{
					setState(513); modifier();
					}
					}
					setState(518);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(519); genericConstructorDeclaration();
				}
				break;

			case 6:
				_localctx = new MemberDeclarationInterfaceContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(523);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || _la==AT) {
					{
					{
					setState(520); modifier();
					}
					}
					setState(525);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(526); interfaceDeclaration();
				}
				break;

			case 7:
				_localctx = new MemberDelcarationAnnotationTypeContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(530);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(527); modifier();
						}
						} 
					}
					setState(532);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,46,_ctx);
				}
				setState(533); annotationTypeDeclaration();
				}
				break;

			case 8:
				_localctx = new MemberDeclarationClassContext(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(537);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || _la==AT) {
					{
					{
					setState(534); modifier();
					}
					}
					setState(539);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(540); classDeclaration();
				}
				break;

			case 9:
				_localctx = new MemberDeclarationEnumContext(_localctx);
				enterOuterAlt(_localctx, 9);
				{
				setState(544);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << FINAL) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE))) != 0) || _la==AT) {
					{
					{
					setState(541); modifier();
					}
					}
					setState(546);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(547); enumDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodDeclarationContext extends ParserRuleContext {
		public MethodBodyContext methodBody() {
			return getRuleContext(MethodBodyContext.class,0);
		}
		public MethodSignatureContext methodSignature() {
			return getRuleContext(MethodSignatureContext.class,0);
		}
		public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMethodDeclaration(this);
		}
	}

	public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
		MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_methodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(550); methodSignature();
			setState(553);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(551); methodBody();
				}
				break;
			case SEMI:
				{
				setState(552); match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodSignatureContext extends ParserRuleContext {
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExceptionDeclarationContext exceptionDeclaration() {
			return getRuleContext(ExceptionDeclarationContext.class,0);
		}
		public MethodSignatureContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodSignature; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMethodSignature(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMethodSignature(this);
		}
	}

	public final MethodSignatureContext methodSignature() throws RecognitionException {
		MethodSignatureContext _localctx = new MethodSignatureContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_methodSignature);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(557);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				{
				setState(555); type();
				}
				break;
			case VOID:
				{
				setState(556); match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(559); match(Identifier);
			setState(560); formalParameters();
			setState(565);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(561); match(LBRACK);
				setState(562); match(RBRACK);
				}
				}
				setState(567);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(569);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(568); exceptionDeclaration();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExceptionDeclarationContext extends ParserRuleContext {
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public ExceptionDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exceptionDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExceptionDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExceptionDeclaration(this);
		}
	}

	public final ExceptionDeclarationContext exceptionDeclaration() throws RecognitionException {
		ExceptionDeclarationContext _localctx = new ExceptionDeclarationContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_exceptionDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(571); match(THROWS);
			setState(572); qualifiedNameList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericMethodDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public MethodDeclarationContext methodDeclaration() {
			return getRuleContext(MethodDeclarationContext.class,0);
		}
		public GenericMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterGenericMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitGenericMethodDeclaration(this);
		}
	}

	public final GenericMethodDeclarationContext genericMethodDeclaration() throws RecognitionException {
		GenericMethodDeclarationContext _localctx = new GenericMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_genericMethodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(574); typeParameters();
			setState(575); methodDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorDeclarationContext extends ParserRuleContext {
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public ConstructorBodyContext constructorBody() {
			return getRuleContext(ConstructorBodyContext.class,0);
		}
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitConstructorDeclaration(this);
		}
	}

	public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
		ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_constructorDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(577); match(Identifier);
			setState(578); formalParameters();
			setState(581);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(579); match(THROWS);
				setState(580); qualifiedNameList();
				}
			}

			setState(583); constructorBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericConstructorDeclarationContext extends ParserRuleContext {
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public ConstructorDeclarationContext constructorDeclaration() {
			return getRuleContext(ConstructorDeclarationContext.class,0);
		}
		public GenericConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericConstructorDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterGenericConstructorDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitGenericConstructorDeclaration(this);
		}
	}

	public final GenericConstructorDeclarationContext genericConstructorDeclaration() throws RecognitionException {
		GenericConstructorDeclarationContext _localctx = new GenericConstructorDeclarationContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_genericConstructorDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(585); typeParameters();
			setState(586); constructorDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FieldDeclarationContext extends ParserRuleContext {
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fieldDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterFieldDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitFieldDeclaration(this);
		}
	}

	public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
		FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_fieldDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(588); type();
			setState(589); variableDeclarators();
			setState(590); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceBodyDeclarationContext extends ParserRuleContext {
		public InterfaceMemberDeclarationContext interfaceMemberDeclaration() {
			return getRuleContext(InterfaceMemberDeclarationContext.class,0);
		}
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public InterfaceBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceBodyDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterInterfaceBodyDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitInterfaceBodyDeclaration(this);
		}
	}

	public final InterfaceBodyDeclarationContext interfaceBodyDeclaration() throws RecognitionException {
		InterfaceBodyDeclarationContext _localctx = new InterfaceBodyDeclarationContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_interfaceBodyDeclaration);
		try {
			int _alt;
			setState(600);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOID:
			case VOLATILE:
			case LT:
			case Identifier:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(595);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(592); modifier();
						}
						} 
					}
					setState(597);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
				}
				setState(598); interfaceMemberDeclaration();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(599); match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceMemberDeclarationContext extends ParserRuleContext {
		public GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() {
			return getRuleContext(GenericInterfaceMethodDeclarationContext.class,0);
		}
		public InterfaceMethodDeclarationContext interfaceMethodDeclaration() {
			return getRuleContext(InterfaceMethodDeclarationContext.class,0);
		}
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public ConstDeclarationContext constDeclaration() {
			return getRuleContext(ConstDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public InterfaceMemberDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMemberDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterInterfaceMemberDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitInterfaceMemberDeclaration(this);
		}
	}

	public final InterfaceMemberDeclarationContext interfaceMemberDeclaration() throws RecognitionException {
		InterfaceMemberDeclarationContext _localctx = new InterfaceMemberDeclarationContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_interfaceMemberDeclaration);
		try {
			setState(609);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(602); constDeclaration();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(603); interfaceMethodDeclaration();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(604); genericInterfaceMethodDeclaration();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(605); interfaceDeclaration();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(606); annotationTypeDeclaration();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(607); classDeclaration();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(608); enumDeclaration();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstDeclarationContext extends ParserRuleContext {
		public List<ConstantDeclaratorContext> constantDeclarator() {
			return getRuleContexts(ConstantDeclaratorContext.class);
		}
		public ConstantDeclaratorContext constantDeclarator(int i) {
			return getRuleContext(ConstantDeclaratorContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ConstDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterConstDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitConstDeclaration(this);
		}
	}

	public final ConstDeclarationContext constDeclaration() throws RecognitionException {
		ConstDeclarationContext _localctx = new ConstDeclarationContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_constDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(611); type();
			setState(612); constantDeclarator();
			setState(617);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(613); match(COMMA);
				setState(614); constantDeclarator();
				}
				}
				setState(619);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(620); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantDeclaratorContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public ConstantDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterConstantDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitConstantDeclarator(this);
		}
	}

	public final ConstantDeclaratorContext constantDeclarator() throws RecognitionException {
		ConstantDeclaratorContext _localctx = new ConstantDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_constantDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(622); match(Identifier);
			setState(627);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(623); match(LBRACK);
				setState(624); match(RBRACK);
				}
				}
				setState(629);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(630); match(ASSIGN);
			setState(631); variableInitializer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InterfaceMethodDeclarationContext extends ParserRuleContext {
		public FormalParametersContext formalParameters() {
			return getRuleContext(FormalParametersContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public QualifiedNameListContext qualifiedNameList() {
			return getRuleContext(QualifiedNameListContext.class,0);
		}
		public InterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_interfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitInterfaceMethodDeclaration(this);
		}
	}

	public final InterfaceMethodDeclarationContext interfaceMethodDeclaration() throws RecognitionException {
		InterfaceMethodDeclarationContext _localctx = new InterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_interfaceMethodDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(635);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				{
				setState(633); type();
				}
				break;
			case VOID:
				{
				setState(634); match(VOID);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(637); match(Identifier);
			setState(638); formalParameters();
			setState(643);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(639); match(LBRACK);
				setState(640); match(RBRACK);
				}
				}
				setState(645);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(648);
			_la = _input.LA(1);
			if (_la==THROWS) {
				{
				setState(646); match(THROWS);
				setState(647); qualifiedNameList();
				}
			}

			setState(650); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class GenericInterfaceMethodDeclarationContext extends ParserRuleContext {
		public InterfaceMethodDeclarationContext interfaceMethodDeclaration() {
			return getRuleContext(InterfaceMethodDeclarationContext.class,0);
		}
		public TypeParametersContext typeParameters() {
			return getRuleContext(TypeParametersContext.class,0);
		}
		public GenericInterfaceMethodDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_genericInterfaceMethodDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterGenericInterfaceMethodDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitGenericInterfaceMethodDeclaration(this);
		}
	}

	public final GenericInterfaceMethodDeclarationContext genericInterfaceMethodDeclaration() throws RecognitionException {
		GenericInterfaceMethodDeclarationContext _localctx = new GenericInterfaceMethodDeclarationContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_genericInterfaceMethodDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(652); typeParameters();
			setState(653); interfaceMethodDeclaration();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorsContext extends ParserRuleContext {
		public VariableDeclaratorContext variableDeclarator(int i) {
			return getRuleContext(VariableDeclaratorContext.class,i);
		}
		public List<VariableDeclaratorContext> variableDeclarator() {
			return getRuleContexts(VariableDeclaratorContext.class);
		}
		public VariableDeclaratorsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarators; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterVariableDeclarators(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitVariableDeclarators(this);
		}
	}

	public final VariableDeclaratorsContext variableDeclarators() throws RecognitionException {
		VariableDeclaratorsContext _localctx = new VariableDeclaratorsContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_variableDeclarators);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(655); variableDeclarator();
			setState(660);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(656); match(COMMA);
				setState(657); variableDeclarator();
				}
				}
				setState(662);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorContext extends ParserRuleContext {
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public VariableInitializerContext variableInitializer() {
			return getRuleContext(VariableInitializerContext.class,0);
		}
		public VariableDeclaratorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclarator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterVariableDeclarator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitVariableDeclarator(this);
		}
	}

	public final VariableDeclaratorContext variableDeclarator() throws RecognitionException {
		VariableDeclaratorContext _localctx = new VariableDeclaratorContext(_ctx, getState());
		enterRule(_localctx, 72, RULE_variableDeclarator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(663); variableDeclaratorId();
			setState(666);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(664); match(ASSIGN);
				setState(665); variableInitializer();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableDeclaratorIdContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public VariableDeclaratorIdContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableDeclaratorId; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterVariableDeclaratorId(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitVariableDeclaratorId(this);
		}
	}

	public final VariableDeclaratorIdContext variableDeclaratorId() throws RecognitionException {
		VariableDeclaratorIdContext _localctx = new VariableDeclaratorIdContext(_ctx, getState());
		enterRule(_localctx, 74, RULE_variableDeclaratorId);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(668); match(Identifier);
			setState(673);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==LBRACK) {
				{
				{
				setState(669); match(LBRACK);
				setState(670); match(RBRACK);
				}
				}
				setState(675);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VariableInitializerContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public VariableInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_variableInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterVariableInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitVariableInitializer(this);
		}
	}

	public final VariableInitializerContext variableInitializer() throws RecognitionException {
		VariableInitializerContext _localctx = new VariableInitializerContext(_ctx, getState());
		enterRule(_localctx, 76, RULE_variableInitializer);
		try {
			setState(678);
			switch (_input.LA(1)) {
			case LBRACE:
				enterOuterAlt(_localctx, 1);
				{
				setState(676); arrayInitializer();
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case THIS:
			case VOID:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case NullLiteral:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(677); expression(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayInitializerContext extends ParserRuleContext {
		public VariableInitializerContext variableInitializer(int i) {
			return getRuleContext(VariableInitializerContext.class,i);
		}
		public List<VariableInitializerContext> variableInitializer() {
			return getRuleContexts(VariableInitializerContext.class);
		}
		public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitArrayInitializer(this);
		}
	}

	public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
		ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 78, RULE_arrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(680); match(LBRACE);
			setState(692);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)))) != 0)) {
				{
				setState(681); variableInitializer();
				setState(686);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(682); match(COMMA);
						setState(683); variableInitializer();
						}
						} 
					}
					setState(688);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,67,_ctx);
				}
				setState(690);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(689); match(COMMA);
					}
				}

				}
			}

			setState(694); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnumConstantNameContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public EnumConstantNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enumConstantName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterEnumConstantName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitEnumConstantName(this);
		}
	}

	public final EnumConstantNameContext enumConstantName() throws RecognitionException {
		EnumConstantNameContext _localctx = new EnumConstantNameContext(_ctx, getState());
		enterRule(_localctx, 80, RULE_enumConstantName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(696); match(Identifier);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 82, RULE_type);
		try {
			int _alt;
			setState(714);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(698); classOrInterfaceType();
				setState(703);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(699); match(LBRACK);
						setState(700); match(RBRACK);
						}
						} 
					}
					setState(705);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
				}
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(706); primitiveType();
				setState(711);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(707); match(LBRACK);
						setState(708); match(RBRACK);
						}
						} 
					}
					setState(713);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassOrInterfaceTypeContext extends ParserRuleContext {
		public TypeArgumentsContext typeArguments(int i) {
			return getRuleContext(TypeArgumentsContext.class,i);
		}
		public TerminalNode Identifier(int i) {
			return getToken(JavaFragmentParser.Identifier, i);
		}
		public List<TerminalNode> Identifier() { return getTokens(JavaFragmentParser.Identifier); }
		public List<TypeArgumentsContext> typeArguments() {
			return getRuleContexts(TypeArgumentsContext.class);
		}
		public ClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classOrInterfaceType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterClassOrInterfaceType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitClassOrInterfaceType(this);
		}
	}

	public final ClassOrInterfaceTypeContext classOrInterfaceType() throws RecognitionException {
		ClassOrInterfaceTypeContext _localctx = new ClassOrInterfaceTypeContext(_ctx, getState());
		enterRule(_localctx, 84, RULE_classOrInterfaceType);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(716); match(Identifier);
			setState(718);
			switch ( getInterpreter().adaptivePredict(_input,73,_ctx) ) {
			case 1:
				{
				setState(717); typeArguments();
				}
				break;
			}
			setState(727);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(720); match(DOT);
					setState(721); match(Identifier);
					setState(723);
					switch ( getInterpreter().adaptivePredict(_input,74,_ctx) ) {
					case 1:
						{
						setState(722); typeArguments();
						}
						break;
					}
					}
					} 
				}
				setState(729);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PrimitiveTypeContext extends ParserRuleContext {
		public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primitiveType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPrimitiveType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPrimitiveType(this);
		}
	}

	public final PrimitiveTypeContext primitiveType() throws RecognitionException {
		PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
		enterRule(_localctx, 86, RULE_primitiveType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(730);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentsContext extends ParserRuleContext {
		public List<TypeArgumentContext> typeArgument() {
			return getRuleContexts(TypeArgumentContext.class);
		}
		public TypeArgumentContext typeArgument(int i) {
			return getRuleContext(TypeArgumentContext.class,i);
		}
		public TypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeArguments(this);
		}
	}

	public final TypeArgumentsContext typeArguments() throws RecognitionException {
		TypeArgumentsContext _localctx = new TypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 88, RULE_typeArguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(732); match(LT);
			setState(733); typeArgument();
			setState(738);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(734); match(COMMA);
				setState(735); typeArgument();
				}
				}
				setState(740);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(741); match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeArgument(this);
		}
	}

	public final TypeArgumentContext typeArgument() throws RecognitionException {
		TypeArgumentContext _localctx = new TypeArgumentContext(_ctx, getState());
		enterRule(_localctx, 90, RULE_typeArgument);
		int _la;
		try {
			setState(749);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(743); type();
				}
				break;
			case QUESTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(744); match(QUESTION);
				setState(747);
				_la = _input.LA(1);
				if (_la==EXTENDS || _la==SUPER) {
					{
					setState(745);
					_la = _input.LA(1);
					if ( !(_la==EXTENDS || _la==SUPER) ) {
					_errHandler.recoverInline(this);
					}
					consume();
					setState(746); type();
					}
				}

				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedNameListContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public QualifiedNameListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedNameList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterQualifiedNameList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitQualifiedNameList(this);
		}
	}

	public final QualifiedNameListContext qualifiedNameList() throws RecognitionException {
		QualifiedNameListContext _localctx = new QualifiedNameListContext(_ctx, getState());
		enterRule(_localctx, 92, RULE_qualifiedNameList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(751); qualifiedName();
			setState(756);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(752); match(COMMA);
				setState(753); qualifiedName();
				}
				}
				setState(758);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParametersContext extends ParserRuleContext {
		public FormalParameterListContext formalParameterList() {
			return getRuleContext(FormalParameterListContext.class,0);
		}
		public FormalParametersContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameters; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterFormalParameters(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitFormalParameters(this);
		}
	}

	public final FormalParametersContext formalParameters() throws RecognitionException {
		FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
		enterRule(_localctx, 94, RULE_formalParameters);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(759); match(LPAREN);
			setState(761);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << SHORT))) != 0) || _la==Identifier || _la==AT) {
				{
				setState(760); formalParameterList();
				}
			}

			setState(763); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterListContext extends ParserRuleContext {
		public List<FormalParameterContext> formalParameter() {
			return getRuleContexts(FormalParameterContext.class);
		}
		public LastFormalParameterContext lastFormalParameter() {
			return getRuleContext(LastFormalParameterContext.class,0);
		}
		public FormalParameterContext formalParameter(int i) {
			return getRuleContext(FormalParameterContext.class,i);
		}
		public FormalParameterListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameterList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterFormalParameterList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitFormalParameterList(this);
		}
	}

	public final FormalParameterListContext formalParameterList() throws RecognitionException {
		FormalParameterListContext _localctx = new FormalParameterListContext(_ctx, getState());
		enterRule(_localctx, 96, RULE_formalParameterList);
		int _la;
		try {
			int _alt;
			setState(778);
			switch ( getInterpreter().adaptivePredict(_input,83,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(765); formalParameter();
				setState(770);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(766); match(COMMA);
						setState(767); formalParameter();
						}
						} 
					}
					setState(772);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,81,_ctx);
				}
				setState(775);
				_la = _input.LA(1);
				if (_la==COMMA) {
					{
					setState(773); match(COMMA);
					setState(774); lastFormalParameter();
					}
				}

				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(777); lastFormalParameter();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FormalParameterContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public FormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_formalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitFormalParameter(this);
		}
	}

	public final FormalParameterContext formalParameter() throws RecognitionException {
		FormalParameterContext _localctx = new FormalParameterContext(_ctx, getState());
		enterRule(_localctx, 98, RULE_formalParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(783);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(780); variableModifier();
				}
				}
				setState(785);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(786); type();
			setState(787); variableDeclaratorId();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LastFormalParameterContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public LastFormalParameterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_lastFormalParameter; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterLastFormalParameter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitLastFormalParameter(this);
		}
	}

	public final LastFormalParameterContext lastFormalParameter() throws RecognitionException {
		LastFormalParameterContext _localctx = new LastFormalParameterContext(_ctx, getState());
		enterRule(_localctx, 100, RULE_lastFormalParameter);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(792);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(789); variableModifier();
				}
				}
				setState(794);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(795); type();
			setState(796); match(ELLIPSIS3);
			setState(797); variableDeclaratorId();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MethodBodyContext extends ParserRuleContext {
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public MethodBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_methodBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterMethodBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitMethodBody(this);
		}
	}

	public final MethodBodyContext methodBody() throws RecognitionException {
		MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
		enterRule(_localctx, 102, RULE_methodBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(799); match(LBRACE);
			setState(800); blockStatements();
			setState(801); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstructorBodyContext extends ParserRuleContext {
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public ConstructorBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constructorBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterConstructorBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitConstructorBody(this);
		}
	}

	public final ConstructorBodyContext constructorBody() throws RecognitionException {
		ConstructorBodyContext _localctx = new ConstructorBodyContext(_ctx, getState());
		enterRule(_localctx, 104, RULE_constructorBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(803); match(LBRACE);
			setState(804); blockStatements();
			setState(805); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QualifiedNameContext extends ParserRuleContext {
		public TerminalNode Identifier(int i) {
			return getToken(JavaFragmentParser.Identifier, i);
		}
		public List<TerminalNode> Identifier() { return getTokens(JavaFragmentParser.Identifier); }
		public QualifiedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_qualifiedName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterQualifiedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitQualifiedName(this);
		}
	}

	public final QualifiedNameContext qualifiedName() throws RecognitionException {
		QualifiedNameContext _localctx = new QualifiedNameContext(_ctx, getState());
		enterRule(_localctx, 106, RULE_qualifiedName);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(807); match(Identifier);
			setState(812);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(808); match(DOT);
					setState(809); match(Identifier);
					}
					} 
				}
				setState(814);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,86,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LiteralContext extends ParserRuleContext {
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
	 
		public LiteralContext() { }
		public void copyFrom(LiteralContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class FloatContext extends LiteralContext {
		public TerminalNode FloatingPointLiteral() { return getToken(JavaFragmentParser.FloatingPointLiteral, 0); }
		public FloatContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterFloat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitFloat(this);
		}
	}
	public static class StringContext extends LiteralContext {
		public TerminalNode StringLiteral() { return getToken(JavaFragmentParser.StringLiteral, 0); }
		public StringContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitString(this);
		}
	}
	public static class BooleanContext extends LiteralContext {
		public TerminalNode BooleanLiteral() { return getToken(JavaFragmentParser.BooleanLiteral, 0); }
		public BooleanContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterBoolean(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitBoolean(this);
		}
	}
	public static class IntegerContext extends LiteralContext {
		public TerminalNode IntegerLiteral() { return getToken(JavaFragmentParser.IntegerLiteral, 0); }
		public IntegerContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitInteger(this);
		}
	}
	public static class NullContext extends LiteralContext {
		public TerminalNode NullLiteral() { return getToken(JavaFragmentParser.NullLiteral, 0); }
		public NullContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterNull(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitNull(this);
		}
	}
	public static class CharacterContext extends LiteralContext {
		public TerminalNode CharacterLiteral() { return getToken(JavaFragmentParser.CharacterLiteral, 0); }
		public CharacterContext(LiteralContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterCharacter(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitCharacter(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 108, RULE_literal);
		try {
			setState(821);
			switch (_input.LA(1)) {
			case IntegerLiteral:
				_localctx = new IntegerContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(815); match(IntegerLiteral);
				}
				break;
			case FloatingPointLiteral:
				_localctx = new FloatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(816); match(FloatingPointLiteral);
				}
				break;
			case CharacterLiteral:
				_localctx = new CharacterContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(817); match(CharacterLiteral);
				}
				break;
			case StringLiteral:
				_localctx = new StringContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(818); match(StringLiteral);
				}
				break;
			case BooleanLiteral:
				_localctx = new BooleanContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(819); match(BooleanLiteral);
				}
				break;
			case NullLiteral:
				_localctx = new NullContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(820); match(NullLiteral);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationContext extends ParserRuleContext {
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public ElementValuePairsContext elementValuePairs() {
			return getRuleContext(ElementValuePairsContext.class,0);
		}
		public AnnotationNameContext annotationName() {
			return getRuleContext(AnnotationNameContext.class,0);
		}
		public AnnotationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAnnotation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAnnotation(this);
		}
	}

	public final AnnotationContext annotation() throws RecognitionException {
		AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
		enterRule(_localctx, 110, RULE_annotation);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(823); match(AT);
			setState(824); annotationName();
			setState(831);
			_la = _input.LA(1);
			if (_la==LPAREN) {
				{
				setState(825); match(LPAREN);
				setState(828);
				switch ( getInterpreter().adaptivePredict(_input,88,_ctx) ) {
				case 1:
					{
					setState(826); elementValuePairs();
					}
					break;

				case 2:
					{
					setState(827); elementValue();
					}
					break;
				}
				setState(830); match(RPAREN);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationNameContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName() {
			return getRuleContext(QualifiedNameContext.class,0);
		}
		public AnnotationNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAnnotationName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAnnotationName(this);
		}
	}

	public final AnnotationNameContext annotationName() throws RecognitionException {
		AnnotationNameContext _localctx = new AnnotationNameContext(_ctx, getState());
		enterRule(_localctx, 112, RULE_annotationName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(833); qualifiedName();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValuePairsContext extends ParserRuleContext {
		public List<ElementValuePairContext> elementValuePair() {
			return getRuleContexts(ElementValuePairContext.class);
		}
		public ElementValuePairContext elementValuePair(int i) {
			return getRuleContext(ElementValuePairContext.class,i);
		}
		public ElementValuePairsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePairs; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterElementValuePairs(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitElementValuePairs(this);
		}
	}

	public final ElementValuePairsContext elementValuePairs() throws RecognitionException {
		ElementValuePairsContext _localctx = new ElementValuePairsContext(_ctx, getState());
		enterRule(_localctx, 114, RULE_elementValuePairs);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(835); elementValuePair();
			setState(840);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(836); match(COMMA);
				setState(837); elementValuePair();
				}
				}
				setState(842);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValuePairContext extends ParserRuleContext {
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public ElementValuePairContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValuePair; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterElementValuePair(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitElementValuePair(this);
		}
	}

	public final ElementValuePairContext elementValuePair() throws RecognitionException {
		ElementValuePairContext _localctx = new ElementValuePairContext(_ctx, getState());
		enterRule(_localctx, 116, RULE_elementValuePair);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(843); match(Identifier);
			setState(844); match(ASSIGN);
			setState(845); elementValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValueContext extends ParserRuleContext {
		public ElementValueArrayInitializerContext elementValueArrayInitializer() {
			return getRuleContext(ElementValueArrayInitializerContext.class,0);
		}
		public AnnotationContext annotation() {
			return getRuleContext(AnnotationContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ElementValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterElementValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitElementValue(this);
		}
	}

	public final ElementValueContext elementValue() throws RecognitionException {
		ElementValueContext _localctx = new ElementValueContext(_ctx, getState());
		enterRule(_localctx, 118, RULE_elementValue);
		try {
			setState(850);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case THIS:
			case VOID:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case NullLiteral:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(847); expression(0);
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 2);
				{
				setState(848); annotation();
				}
				break;
			case LBRACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(849); elementValueArrayInitializer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElementValueArrayInitializerContext extends ParserRuleContext {
		public ElementValueContext elementValue(int i) {
			return getRuleContext(ElementValueContext.class,i);
		}
		public List<ElementValueContext> elementValue() {
			return getRuleContexts(ElementValueContext.class);
		}
		public ElementValueArrayInitializerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elementValueArrayInitializer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterElementValueArrayInitializer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitElementValueArrayInitializer(this);
		}
	}

	public final ElementValueArrayInitializerContext elementValueArrayInitializer() throws RecognitionException {
		ElementValueArrayInitializerContext _localctx = new ElementValueArrayInitializerContext(_ctx, getState());
		enterRule(_localctx, 120, RULE_elementValueArrayInitializer);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(852); match(LBRACE);
			setState(861);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN) | (1L << LBRACE))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)) | (1L << (AT - 68)))) != 0)) {
				{
				setState(853); elementValue();
				setState(858);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(854); match(COMMA);
						setState(855); elementValue();
						}
						} 
					}
					setState(860);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,92,_ctx);
				}
				}
			}

			setState(864);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(863); match(COMMA);
				}
			}

			setState(866); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeDeclarationContext extends ParserRuleContext {
		public AnnotationTypeBodyContext annotationTypeBody() {
			return getRuleContext(AnnotationTypeBodyContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public AnnotationTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAnnotationTypeDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAnnotationTypeDeclaration(this);
		}
	}

	public final AnnotationTypeDeclarationContext annotationTypeDeclaration() throws RecognitionException {
		AnnotationTypeDeclarationContext _localctx = new AnnotationTypeDeclarationContext(_ctx, getState());
		enterRule(_localctx, 122, RULE_annotationTypeDeclaration);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(868); match(AT);
			setState(869); match(INTERFACE);
			setState(870); match(Identifier);
			setState(871); annotationTypeBody();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeBodyContext extends ParserRuleContext {
		public List<AnnotationTypeElementDeclarationContext> annotationTypeElementDeclaration() {
			return getRuleContexts(AnnotationTypeElementDeclarationContext.class);
		}
		public AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration(int i) {
			return getRuleContext(AnnotationTypeElementDeclarationContext.class,i);
		}
		public AnnotationTypeBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAnnotationTypeBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAnnotationTypeBody(this);
		}
	}

	public final AnnotationTypeBodyContext annotationTypeBody() throws RecognitionException {
		AnnotationTypeBodyContext _localctx = new AnnotationTypeBodyContext(_ctx, getState());
		enterRule(_localctx, 124, RULE_annotationTypeBody);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(873); match(LBRACE);
			setState(877);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NATIVE) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SYNCHRONIZED) | (1L << TRANSIENT) | (1L << VOLATILE) | (1L << SEMI))) != 0) || _la==Identifier || _la==AT) {
				{
				{
				setState(874); annotationTypeElementDeclaration();
				}
				}
				setState(879);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(880); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeElementDeclarationContext extends ParserRuleContext {
		public List<ModifierContext> modifier() {
			return getRuleContexts(ModifierContext.class);
		}
		public AnnotationTypeElementRestContext annotationTypeElementRest() {
			return getRuleContext(AnnotationTypeElementRestContext.class,0);
		}
		public ModifierContext modifier(int i) {
			return getRuleContext(ModifierContext.class,i);
		}
		public AnnotationTypeElementDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAnnotationTypeElementDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAnnotationTypeElementDeclaration(this);
		}
	}

	public final AnnotationTypeElementDeclarationContext annotationTypeElementDeclaration() throws RecognitionException {
		AnnotationTypeElementDeclarationContext _localctx = new AnnotationTypeElementDeclarationContext(_ctx, getState());
		enterRule(_localctx, 126, RULE_annotationTypeElementDeclaration);
		try {
			int _alt;
			setState(890);
			switch (_input.LA(1)) {
			case ABSTRACT:
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case CLASS:
			case DOUBLE:
			case ENUM:
			case FINAL:
			case FLOAT:
			case INT:
			case INTERFACE:
			case LONG:
			case NATIVE:
			case PRIVATE:
			case PROTECTED:
			case PUBLIC:
			case SHORT:
			case STATIC:
			case STRICTFP:
			case SYNCHRONIZED:
			case TRANSIENT:
			case VOLATILE:
			case Identifier:
			case AT:
				enterOuterAlt(_localctx, 1);
				{
				setState(885);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(882); modifier();
						}
						} 
					}
					setState(887);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
				}
				setState(888); annotationTypeElementRest();
				}
				break;
			case SEMI:
				enterOuterAlt(_localctx, 2);
				{
				setState(889); match(SEMI);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationTypeElementRestContext extends ParserRuleContext {
		public InterfaceDeclarationContext interfaceDeclaration() {
			return getRuleContext(InterfaceDeclarationContext.class,0);
		}
		public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
			return getRuleContext(AnnotationTypeDeclarationContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() {
			return getRuleContext(AnnotationMethodOrConstantRestContext.class,0);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public EnumDeclarationContext enumDeclaration() {
			return getRuleContext(EnumDeclarationContext.class,0);
		}
		public ClassDeclarationContext classDeclaration() {
			return getRuleContext(ClassDeclarationContext.class,0);
		}
		public AnnotationTypeElementRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationTypeElementRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAnnotationTypeElementRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAnnotationTypeElementRest(this);
		}
	}

	public final AnnotationTypeElementRestContext annotationTypeElementRest() throws RecognitionException {
		AnnotationTypeElementRestContext _localctx = new AnnotationTypeElementRestContext(_ctx, getState());
		enterRule(_localctx, 128, RULE_annotationTypeElementRest);
		try {
			setState(912);
			switch (_input.LA(1)) {
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(892); type();
				setState(893); annotationMethodOrConstantRest();
				setState(894); match(SEMI);
				}
				break;
			case CLASS:
				enterOuterAlt(_localctx, 2);
				{
				setState(896); classDeclaration();
				setState(898);
				switch ( getInterpreter().adaptivePredict(_input,98,_ctx) ) {
				case 1:
					{
					setState(897); match(SEMI);
					}
					break;
				}
				}
				break;
			case INTERFACE:
				enterOuterAlt(_localctx, 3);
				{
				setState(900); interfaceDeclaration();
				setState(902);
				switch ( getInterpreter().adaptivePredict(_input,99,_ctx) ) {
				case 1:
					{
					setState(901); match(SEMI);
					}
					break;
				}
				}
				break;
			case ENUM:
				enterOuterAlt(_localctx, 4);
				{
				setState(904); enumDeclaration();
				setState(906);
				switch ( getInterpreter().adaptivePredict(_input,100,_ctx) ) {
				case 1:
					{
					setState(905); match(SEMI);
					}
					break;
				}
				}
				break;
			case AT:
				enterOuterAlt(_localctx, 5);
				{
				setState(908); annotationTypeDeclaration();
				setState(910);
				switch ( getInterpreter().adaptivePredict(_input,101,_ctx) ) {
				case 1:
					{
					setState(909); match(SEMI);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationMethodOrConstantRestContext extends ParserRuleContext {
		public AnnotationConstantRestContext annotationConstantRest() {
			return getRuleContext(AnnotationConstantRestContext.class,0);
		}
		public AnnotationMethodRestContext annotationMethodRest() {
			return getRuleContext(AnnotationMethodRestContext.class,0);
		}
		public AnnotationMethodOrConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodOrConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAnnotationMethodOrConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAnnotationMethodOrConstantRest(this);
		}
	}

	public final AnnotationMethodOrConstantRestContext annotationMethodOrConstantRest() throws RecognitionException {
		AnnotationMethodOrConstantRestContext _localctx = new AnnotationMethodOrConstantRestContext(_ctx, getState());
		enterRule(_localctx, 130, RULE_annotationMethodOrConstantRest);
		try {
			setState(916);
			switch ( getInterpreter().adaptivePredict(_input,103,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(914); annotationMethodRest();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(915); annotationConstantRest();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationMethodRestContext extends ParserRuleContext {
		public DefaultValueContext defaultValue() {
			return getRuleContext(DefaultValueContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public AnnotationMethodRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationMethodRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAnnotationMethodRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAnnotationMethodRest(this);
		}
	}

	public final AnnotationMethodRestContext annotationMethodRest() throws RecognitionException {
		AnnotationMethodRestContext _localctx = new AnnotationMethodRestContext(_ctx, getState());
		enterRule(_localctx, 132, RULE_annotationMethodRest);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(918); match(Identifier);
			setState(919); match(LPAREN);
			setState(920); match(RPAREN);
			setState(922);
			_la = _input.LA(1);
			if (_la==DEFAULT) {
				{
				setState(921); defaultValue();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AnnotationConstantRestContext extends ParserRuleContext {
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public AnnotationConstantRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_annotationConstantRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAnnotationConstantRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAnnotationConstantRest(this);
		}
	}

	public final AnnotationConstantRestContext annotationConstantRest() throws RecognitionException {
		AnnotationConstantRestContext _localctx = new AnnotationConstantRestContext(_ctx, getState());
		enterRule(_localctx, 134, RULE_annotationConstantRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(924); variableDeclarators();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DefaultValueContext extends ParserRuleContext {
		public ElementValueContext elementValue() {
			return getRuleContext(ElementValueContext.class,0);
		}
		public DefaultValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defaultValue; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterDefaultValue(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitDefaultValue(this);
		}
	}

	public final DefaultValueContext defaultValue() throws RecognitionException {
		DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
		enterRule(_localctx, 136, RULE_defaultValue);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(926); match(DEFAULT);
			setState(927); elementValue();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatementsContext extends ParserRuleContext {
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public BlockStatementsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatements; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterBlockStatements(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitBlockStatements(this);
		}
	}

	public final BlockStatementsContext blockStatements() throws RecognitionException {
		BlockStatementsContext _localctx = new BlockStatementsContext(_ctx, getState());
		enterRule(_localctx, 138, RULE_blockStatements);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(932);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN) | (1L << LBRACE) | (1L << SEMI))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)) | (1L << (AT - 68)) | (1L << (ELLIPSIS4 - 68)) | (1L << (ELLIPSIS3 - 68)) | (1L << (ELLIPSIS2 - 68)))) != 0)) {
				{
				{
				setState(929); blockStatement();
				}
				}
				setState(934);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TypeDeclarationContext typeDeclaration() {
			return getRuleContext(TypeDeclarationContext.class,0);
		}
		public EllipsisTokenContext ellipsisToken() {
			return getRuleContext(EllipsisTokenContext.class,0);
		}
		public LocalVariableDeclarationStatementContext localVariableDeclarationStatement() {
			return getRuleContext(LocalVariableDeclarationStatementContext.class,0);
		}
		public BlockStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_blockStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterBlockStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitBlockStatement(this);
		}
	}

	public final BlockStatementContext blockStatement() throws RecognitionException {
		BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
		enterRule(_localctx, 140, RULE_blockStatement);
		try {
			setState(939);
			switch ( getInterpreter().adaptivePredict(_input,106,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(935); localVariableDeclarationStatement();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(936); statement();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(937); typeDeclaration();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(938); ellipsisToken();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalVariableDeclarationStatementContext extends ParserRuleContext {
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public LocalVariableDeclarationStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableDeclarationStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterLocalVariableDeclarationStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitLocalVariableDeclarationStatement(this);
		}
	}

	public final LocalVariableDeclarationStatementContext localVariableDeclarationStatement() throws RecognitionException {
		LocalVariableDeclarationStatementContext _localctx = new LocalVariableDeclarationStatementContext(_ctx, getState());
		enterRule(_localctx, 142, RULE_localVariableDeclarationStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(941); localVariableDeclaration();
			setState(942); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class LocalVariableDeclarationContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableDeclaratorsContext variableDeclarators() {
			return getRuleContext(VariableDeclaratorsContext.class,0);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public LocalVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVariableDeclaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterLocalVariableDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitLocalVariableDeclaration(this);
		}
	}

	public final LocalVariableDeclarationContext localVariableDeclaration() throws RecognitionException {
		LocalVariableDeclarationContext _localctx = new LocalVariableDeclarationContext(_ctx, getState());
		enterRule(_localctx, 144, RULE_localVariableDeclaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(947);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(944); variableModifier();
				}
				}
				setState(949);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(950); type();
			setState(951); variableDeclarators();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementContext extends ParserRuleContext {
		public BreakStatementContext breakStatement() {
			return getRuleContext(BreakStatementContext.class,0);
		}
		public TryResourceContext tryResource() {
			return getRuleContext(TryResourceContext.class,0);
		}
		public IfStatementContext ifStatement() {
			return getRuleContext(IfStatementContext.class,0);
		}
		public TryCatchFinallyContext tryCatchFinally() {
			return getRuleContext(TryCatchFinallyContext.class,0);
		}
		public ThrowStatementContext throwStatement() {
			return getRuleContext(ThrowStatementContext.class,0);
		}
		public ReturnStatementContext returnStatement() {
			return getRuleContext(ReturnStatementContext.class,0);
		}
		public WhileStatementContext whileStatement() {
			return getRuleContext(WhileStatementContext.class,0);
		}
		public AssertStatementContext assertStatement() {
			return getRuleContext(AssertStatementContext.class,0);
		}
		public SwitchStatementContext switchStatement() {
			return getRuleContext(SwitchStatementContext.class,0);
		}
		public StatementExpressionContext statementExpression() {
			return getRuleContext(StatementExpressionContext.class,0);
		}
		public ContinueStatementContext continueStatement() {
			return getRuleContext(ContinueStatementContext.class,0);
		}
		public NamedStatementContext namedStatement() {
			return getRuleContext(NamedStatementContext.class,0);
		}
		public SynchronizedStatementContext synchronizedStatement() {
			return getRuleContext(SynchronizedStatementContext.class,0);
		}
		public DoStatementContext doStatement() {
			return getRuleContext(DoStatementContext.class,0);
		}
		public BlockContext block() {
			return getRuleContext(BlockContext.class,0);
		}
		public ForStatementContext forStatement() {
			return getRuleContext(ForStatementContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 146, RULE_statement);
		try {
			setState(972);
			switch ( getInterpreter().adaptivePredict(_input,108,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(953); block();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(954); assertStatement();
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(955); ifStatement();
				}
				break;

			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(956); forStatement();
				}
				break;

			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(957); whileStatement();
				}
				break;

			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(958); doStatement();
				}
				break;

			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(959); tryCatchFinally();
				}
				break;

			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(960); tryResource();
				}
				break;

			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(961); switchStatement();
				}
				break;

			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(962); synchronizedStatement();
				}
				break;

			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(963); returnStatement();
				}
				break;

			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(964); throwStatement();
				}
				break;

			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(965); breakStatement();
				}
				break;

			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(966); continueStatement();
				}
				break;

			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(967); match(SEMI);
				}
				break;

			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(968); statementExpression();
				setState(969); match(SEMI);
				}
				break;

			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(971); namedStatement();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BlockContext extends ParserRuleContext {
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 148, RULE_block);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(974); match(LBRACE);
			setState(975); blockStatements();
			setState(976); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AssertStatementContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public TerminalNode ASSERT() { return getToken(JavaFragmentParser.ASSERT, 0); }
		public AssertStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assertStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAssertStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAssertStatement(this);
		}
	}

	public final AssertStatementContext assertStatement() throws RecognitionException {
		AssertStatementContext _localctx = new AssertStatementContext(_ctx, getState());
		enterRule(_localctx, 150, RULE_assertStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(978); match(ASSERT);
			setState(979); expression(0);
			setState(982);
			_la = _input.LA(1);
			if (_la==COLON) {
				{
				setState(980); match(COLON);
				setState(981); expression(0);
				}
			}

			setState(984); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfStatementContext extends ParserRuleContext {
		public IfBodyContext ifBody() {
			return getRuleContext(IfBodyContext.class,0);
		}
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public ElseBodyContext elseBody() {
			return getRuleContext(ElseBodyContext.class,0);
		}
		public IfStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterIfStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitIfStatement(this);
		}
	}

	public final IfStatementContext ifStatement() throws RecognitionException {
		IfStatementContext _localctx = new IfStatementContext(_ctx, getState());
		enterRule(_localctx, 152, RULE_ifStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(986); match(IF);
			setState(987); parExpression();
			setState(989);
			switch ( getInterpreter().adaptivePredict(_input,110,_ctx) ) {
			case 1:
				{
				setState(988); ifBody();
				}
				break;
			}
			setState(993);
			switch ( getInterpreter().adaptivePredict(_input,111,_ctx) ) {
			case 1:
				{
				setState(991); match(ELSE);
				{
				setState(992); elseBody();
				}
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IfBodyContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public IfBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ifBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterIfBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitIfBody(this);
		}
	}

	public final IfBodyContext ifBody() throws RecognitionException {
		IfBodyContext _localctx = new IfBodyContext(_ctx, getState());
		enterRule(_localctx, 154, RULE_ifBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(995); statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ElseBodyContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ElseBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elseBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterElseBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitElseBody(this);
		}
	}

	public final ElseBodyContext elseBody() throws RecognitionException {
		ElseBodyContext _localctx = new ElseBodyContext(_ctx, getState());
		enterRule(_localctx, 156, RULE_elseBody);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(997); statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ForControlContext forControl() {
			return getRuleContext(ForControlContext.class,0);
		}
		public ForStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterForStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitForStatement(this);
		}
	}

	public final ForStatementContext forStatement() throws RecognitionException {
		ForStatementContext _localctx = new ForStatementContext(_ctx, getState());
		enterRule(_localctx, 158, RULE_forStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(999); match(FOR);
			setState(1000); match(LPAREN);
			setState(1001); forControl();
			setState(1002); match(RPAREN);
			setState(1003); statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class WhileStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public WhileStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_whileStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterWhileStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitWhileStatement(this);
		}
	}

	public final WhileStatementContext whileStatement() throws RecognitionException {
		WhileStatementContext _localctx = new WhileStatementContext(_ctx, getState());
		enterRule(_localctx, 160, RULE_whileStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1005); match(WHILE);
			setState(1006); parExpression();
			setState(1007); statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DoStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public DoStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_doStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterDoStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitDoStatement(this);
		}
	}

	public final DoStatementContext doStatement() throws RecognitionException {
		DoStatementContext _localctx = new DoStatementContext(_ctx, getState());
		enterRule(_localctx, 162, RULE_doStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1009); match(DO);
			setState(1010); statement();
			setState(1011); match(WHILE);
			setState(1012); parExpression();
			setState(1013); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TryCatchFinallyContext extends ParserRuleContext {
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public CatchClauseContext catchClause(int i) {
			return getRuleContext(CatchClauseContext.class,i);
		}
		public List<CatchClauseContext> catchClause() {
			return getRuleContexts(CatchClauseContext.class);
		}
		public FinallyBlockContext finallyBlock() {
			return getRuleContext(FinallyBlockContext.class,0);
		}
		public TryCatchFinallyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryCatchFinally; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTryCatchFinally(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTryCatchFinally(this);
		}
	}

	public final TryCatchFinallyContext tryCatchFinally() throws RecognitionException {
		TryCatchFinallyContext _localctx = new TryCatchFinallyContext(_ctx, getState());
		enterRule(_localctx, 164, RULE_tryCatchFinally);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1015); match(TRY);
			setState(1016); match(LBRACE);
			setState(1017); blockStatements();
			setState(1018); match(RBRACE);
			setState(1028);
			switch (_input.LA(1)) {
			case CATCH:
				{
				setState(1020); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(1019); catchClause();
					}
					}
					setState(1022); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CATCH );
				setState(1025);
				_la = _input.LA(1);
				if (_la==FINALLY) {
					{
					setState(1024); finallyBlock();
					}
				}

				}
				break;
			case FINALLY:
				{
				setState(1027); finallyBlock();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TryResourceContext extends ParserRuleContext {
		public ResourceSpecificationContext resourceSpecification() {
			return getRuleContext(ResourceSpecificationContext.class,0);
		}
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public CatchClauseContext catchClause(int i) {
			return getRuleContext(CatchClauseContext.class,i);
		}
		public List<CatchClauseContext> catchClause() {
			return getRuleContexts(CatchClauseContext.class);
		}
		public FinallyBlockContext finallyBlock() {
			return getRuleContext(FinallyBlockContext.class,0);
		}
		public TryResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tryResource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTryResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTryResource(this);
		}
	}

	public final TryResourceContext tryResource() throws RecognitionException {
		TryResourceContext _localctx = new TryResourceContext(_ctx, getState());
		enterRule(_localctx, 166, RULE_tryResource);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1030); match(TRY);
			setState(1031); resourceSpecification();
			setState(1032); match(LBRACE);
			setState(1033); blockStatements();
			setState(1034); match(RBRACE);
			setState(1038);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CATCH) {
				{
				{
				setState(1035); catchClause();
				}
				}
				setState(1040);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1042);
			_la = _input.LA(1);
			if (_la==FINALLY) {
				{
				setState(1041); finallyBlock();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchStatementContext extends ParserRuleContext {
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
			return getRuleContext(SwitchBlockStatementGroupContext.class,i);
		}
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
			return getRuleContexts(SwitchBlockStatementGroupContext.class);
		}
		public SwitchStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterSwitchStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitSwitchStatement(this);
		}
	}

	public final SwitchStatementContext switchStatement() throws RecognitionException {
		SwitchStatementContext _localctx = new SwitchStatementContext(_ctx, getState());
		enterRule(_localctx, 168, RULE_switchStatement);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1044); match(SWITCH);
			setState(1045); parExpression();
			setState(1046); match(LBRACE);
			setState(1050);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1047); switchBlockStatementGroup();
					}
					} 
				}
				setState(1052);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,117,_ctx);
			}
			setState(1056);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==CASE || _la==DEFAULT) {
				{
				{
				setState(1053); switchLabel();
				}
				}
				setState(1058);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1059); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SynchronizedStatementContext extends ParserRuleContext {
		public ParExpressionContext parExpression() {
			return getRuleContext(ParExpressionContext.class,0);
		}
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public SynchronizedStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_synchronizedStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterSynchronizedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitSynchronizedStatement(this);
		}
	}

	public final SynchronizedStatementContext synchronizedStatement() throws RecognitionException {
		SynchronizedStatementContext _localctx = new SynchronizedStatementContext(_ctx, getState());
		enterRule(_localctx, 170, RULE_synchronizedStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1061); match(SYNCHRONIZED);
			setState(1062); parExpression();
			setState(1063); match(LBRACE);
			setState(1064); blockStatements();
			setState(1065); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ReturnStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ReturnStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_returnStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterReturnStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitReturnStatement(this);
		}
	}

	public final ReturnStatementContext returnStatement() throws RecognitionException {
		ReturnStatementContext _localctx = new ReturnStatementContext(_ctx, getState());
		enterRule(_localctx, 172, RULE_returnStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1067); match(RETURN);
			setState(1069);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)))) != 0)) {
				{
				setState(1068); expression(0);
				}
			}

			setState(1071); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ThrowStatementContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ThrowStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_throwStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterThrowStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitThrowStatement(this);
		}
	}

	public final ThrowStatementContext throwStatement() throws RecognitionException {
		ThrowStatementContext _localctx = new ThrowStatementContext(_ctx, getState());
		enterRule(_localctx, 174, RULE_throwStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1073); match(THROW);
			setState(1074); expression(0);
			setState(1075); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BreakStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public BreakStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_breakStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterBreakStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitBreakStatement(this);
		}
	}

	public final BreakStatementContext breakStatement() throws RecognitionException {
		BreakStatementContext _localctx = new BreakStatementContext(_ctx, getState());
		enterRule(_localctx, 176, RULE_breakStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1077); match(BREAK);
			setState(1079);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(1078); match(Identifier);
				}
			}

			setState(1081); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ContinueStatementContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public ContinueStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_continueStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterContinueStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitContinueStatement(this);
		}
	}

	public final ContinueStatementContext continueStatement() throws RecognitionException {
		ContinueStatementContext _localctx = new ContinueStatementContext(_ctx, getState());
		enterRule(_localctx, 178, RULE_continueStatement);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1083); match(CONTINUE);
			setState(1085);
			_la = _input.LA(1);
			if (_la==Identifier) {
				{
				setState(1084); match(Identifier);
				}
			}

			setState(1087); match(SEMI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NamedStatementContext extends ParserRuleContext {
		public StatementContext statement() {
			return getRuleContext(StatementContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public NamedStatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_namedStatement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterNamedStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitNamedStatement(this);
		}
	}

	public final NamedStatementContext namedStatement() throws RecognitionException {
		NamedStatementContext _localctx = new NamedStatementContext(_ctx, getState());
		enterRule(_localctx, 180, RULE_namedStatement);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1089); match(Identifier);
			setState(1090); match(COLON);
			setState(1091); statement();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchClauseContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public CatchTypeContext catchType() {
			return getRuleContext(CatchTypeContext.class,0);
		}
		public CatchClauseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchClause; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterCatchClause(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitCatchClause(this);
		}
	}

	public final CatchClauseContext catchClause() throws RecognitionException {
		CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
		enterRule(_localctx, 182, RULE_catchClause);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1093); match(CATCH);
			setState(1094); match(LPAREN);
			setState(1098);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(1095); variableModifier();
				}
				}
				setState(1100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1101); catchType();
			setState(1102); match(Identifier);
			setState(1103); match(RPAREN);
			setState(1104); match(LBRACE);
			setState(1105); blockStatements();
			setState(1106); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CatchTypeContext extends ParserRuleContext {
		public QualifiedNameContext qualifiedName(int i) {
			return getRuleContext(QualifiedNameContext.class,i);
		}
		public List<QualifiedNameContext> qualifiedName() {
			return getRuleContexts(QualifiedNameContext.class);
		}
		public CatchTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_catchType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterCatchType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitCatchType(this);
		}
	}

	public final CatchTypeContext catchType() throws RecognitionException {
		CatchTypeContext _localctx = new CatchTypeContext(_ctx, getState());
		enterRule(_localctx, 184, RULE_catchType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1108); qualifiedName();
			setState(1113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==BITOR) {
				{
				{
				setState(1109); match(BITOR);
				setState(1110); qualifiedName();
				}
				}
				setState(1115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FinallyBlockContext extends ParserRuleContext {
		public BlockStatementsContext blockStatements() {
			return getRuleContext(BlockStatementsContext.class,0);
		}
		public FinallyBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_finallyBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterFinallyBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitFinallyBlock(this);
		}
	}

	public final FinallyBlockContext finallyBlock() throws RecognitionException {
		FinallyBlockContext _localctx = new FinallyBlockContext(_ctx, getState());
		enterRule(_localctx, 186, RULE_finallyBlock);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1116); match(FINALLY);
			setState(1117); match(LBRACE);
			setState(1118); blockStatements();
			setState(1119); match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourceSpecificationContext extends ParserRuleContext {
		public ResourcesContext resources() {
			return getRuleContext(ResourcesContext.class,0);
		}
		public ResourceSpecificationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resourceSpecification; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterResourceSpecification(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitResourceSpecification(this);
		}
	}

	public final ResourceSpecificationContext resourceSpecification() throws RecognitionException {
		ResourceSpecificationContext _localctx = new ResourceSpecificationContext(_ctx, getState());
		enterRule(_localctx, 188, RULE_resourceSpecification);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1121); match(LPAREN);
			setState(1122); resources();
			setState(1124);
			_la = _input.LA(1);
			if (_la==SEMI) {
				{
				setState(1123); match(SEMI);
				}
			}

			setState(1126); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourcesContext extends ParserRuleContext {
		public List<ResourceContext> resource() {
			return getRuleContexts(ResourceContext.class);
		}
		public ResourceContext resource(int i) {
			return getRuleContext(ResourceContext.class,i);
		}
		public ResourcesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resources; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterResources(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitResources(this);
		}
	}

	public final ResourcesContext resources() throws RecognitionException {
		ResourcesContext _localctx = new ResourcesContext(_ctx, getState());
		enterRule(_localctx, 190, RULE_resources);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1128); resource();
			setState(1133);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(1129); match(SEMI);
					setState(1130); resource();
					}
					} 
				}
				setState(1135);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ResourceContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public VariableDeclaratorIdContext variableDeclaratorId() {
			return getRuleContext(VariableDeclaratorIdContext.class,0);
		}
		public ClassOrInterfaceTypeContext classOrInterfaceType() {
			return getRuleContext(ClassOrInterfaceTypeContext.class,0);
		}
		public ResourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_resource; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterResource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitResource(this);
		}
	}

	public final ResourceContext resource() throws RecognitionException {
		ResourceContext _localctx = new ResourceContext(_ctx, getState());
		enterRule(_localctx, 192, RULE_resource);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1139);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(1136); variableModifier();
				}
				}
				setState(1141);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1142); classOrInterfaceType();
			setState(1143); variableDeclaratorId();
			setState(1144); match(ASSIGN);
			setState(1145); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchBlockStatementGroupContext extends ParserRuleContext {
		public List<BlockStatementContext> blockStatement() {
			return getRuleContexts(BlockStatementContext.class);
		}
		public SwitchLabelContext switchLabel(int i) {
			return getRuleContext(SwitchLabelContext.class,i);
		}
		public List<SwitchLabelContext> switchLabel() {
			return getRuleContexts(SwitchLabelContext.class);
		}
		public BlockStatementContext blockStatement(int i) {
			return getRuleContext(BlockStatementContext.class,i);
		}
		public SwitchBlockStatementGroupContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchBlockStatementGroup; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterSwitchBlockStatementGroup(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitSwitchBlockStatementGroup(this);
		}
	}

	public final SwitchBlockStatementGroupContext switchBlockStatementGroup() throws RecognitionException {
		SwitchBlockStatementGroupContext _localctx = new SwitchBlockStatementGroupContext(_ctx, getState());
		enterRule(_localctx, 194, RULE_switchBlockStatementGroup);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1148); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1147); switchLabel();
				}
				}
				setState(1150); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CASE || _la==DEFAULT );
			setState(1153); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(1152); blockStatement();
				}
				}
				setState(1155); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << ABSTRACT) | (1L << ASSERT) | (1L << BOOLEAN) | (1L << BREAK) | (1L << BYTE) | (1L << CHAR) | (1L << CLASS) | (1L << CONTINUE) | (1L << DO) | (1L << DOUBLE) | (1L << ENUM) | (1L << FINAL) | (1L << FLOAT) | (1L << FOR) | (1L << IF) | (1L << INT) | (1L << INTERFACE) | (1L << LONG) | (1L << NEW) | (1L << PRIVATE) | (1L << PROTECTED) | (1L << PUBLIC) | (1L << RETURN) | (1L << SHORT) | (1L << STATIC) | (1L << STRICTFP) | (1L << SUPER) | (1L << SWITCH) | (1L << SYNCHRONIZED) | (1L << THIS) | (1L << THROW) | (1L << TRY) | (1L << VOID) | (1L << WHILE) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN) | (1L << LBRACE) | (1L << SEMI))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)) | (1L << (AT - 68)) | (1L << (ELLIPSIS4 - 68)) | (1L << (ELLIPSIS3 - 68)) | (1L << (ELLIPSIS2 - 68)))) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SwitchLabelContext extends ParserRuleContext {
		public ConstantExpressionContext constantExpression() {
			return getRuleContext(ConstantExpressionContext.class,0);
		}
		public EnumConstantNameContext enumConstantName() {
			return getRuleContext(EnumConstantNameContext.class,0);
		}
		public SwitchLabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_switchLabel; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterSwitchLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitSwitchLabel(this);
		}
	}

	public final SwitchLabelContext switchLabel() throws RecognitionException {
		SwitchLabelContext _localctx = new SwitchLabelContext(_ctx, getState());
		enterRule(_localctx, 196, RULE_switchLabel);
		try {
			setState(1167);
			switch ( getInterpreter().adaptivePredict(_input,129,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1157); match(CASE);
				setState(1158); constantExpression();
				setState(1159); match(COLON);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1161); match(CASE);
				setState(1162); enumConstantName();
				setState(1163); match(COLON);
				}
				break;

			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(1165); match(DEFAULT);
				setState(1166); match(COLON);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForControlContext extends ParserRuleContext {
		public ForInitContext forInit() {
			return getRuleContext(ForInitContext.class,0);
		}
		public EnhancedForControlContext enhancedForControl() {
			return getRuleContext(EnhancedForControlContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ForUpdateContext forUpdate() {
			return getRuleContext(ForUpdateContext.class,0);
		}
		public ForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitForControl(this);
		}
	}

	public final ForControlContext forControl() throws RecognitionException {
		ForControlContext _localctx = new ForControlContext(_ctx, getState());
		enterRule(_localctx, 198, RULE_forControl);
		int _la;
		try {
			setState(1181);
			switch ( getInterpreter().adaptivePredict(_input,133,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1169); enhancedForControl();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1171);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FINAL) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)) | (1L << (AT - 68)))) != 0)) {
					{
					setState(1170); forInit();
					}
				}

				setState(1173); match(SEMI);
				setState(1175);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)))) != 0)) {
					{
					setState(1174); expression(0);
					}
				}

				setState(1177); match(SEMI);
				setState(1179);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)))) != 0)) {
					{
					setState(1178); forUpdate();
					}
				}

				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForInitContext extends ParserRuleContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public LocalVariableDeclarationContext localVariableDeclaration() {
			return getRuleContext(LocalVariableDeclarationContext.class,0);
		}
		public ForInitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forInit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterForInit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitForInit(this);
		}
	}

	public final ForInitContext forInit() throws RecognitionException {
		ForInitContext _localctx = new ForInitContext(_ctx, getState());
		enterRule(_localctx, 200, RULE_forInit);
		try {
			setState(1185);
			switch ( getInterpreter().adaptivePredict(_input,134,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1183); localVariableDeclaration();
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1184); expressionList();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EnhancedForControlContext extends ParserRuleContext {
		public List<VariableModifierContext> variableModifier() {
			return getRuleContexts(VariableModifierContext.class);
		}
		public VariableModifierContext variableModifier(int i) {
			return getRuleContext(VariableModifierContext.class,i);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public EnhancedForControlContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enhancedForControl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterEnhancedForControl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitEnhancedForControl(this);
		}
	}

	public final EnhancedForControlContext enhancedForControl() throws RecognitionException {
		EnhancedForControlContext _localctx = new EnhancedForControlContext(_ctx, getState());
		enterRule(_localctx, 202, RULE_enhancedForControl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1190);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==FINAL || _la==AT) {
				{
				{
				setState(1187); variableModifier();
				}
				}
				setState(1192);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(1193); type();
			setState(1194); match(Identifier);
			setState(1195); match(COLON);
			setState(1196); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ForUpdateContext extends ParserRuleContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ForUpdateContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_forUpdate; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterForUpdate(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitForUpdate(this);
		}
	}

	public final ForUpdateContext forUpdate() throws RecognitionException {
		ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
		enterRule(_localctx, 204, RULE_forUpdate);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1198); expressionList();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ParExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterParExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitParExpression(this);
		}
	}

	public final ParExpressionContext parExpression() throws RecognitionException {
		ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
		enterRule(_localctx, 206, RULE_parExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1200); match(LPAREN);
			setState(1201); expression(0);
			setState(1202); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionListContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expressionList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpressionList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpressionList(this);
		}
	}

	public final ExpressionListContext expressionList() throws RecognitionException {
		ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
		enterRule(_localctx, 208, RULE_expressionList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1204); expression(0);
			setState(1209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(1205); match(COMMA);
				setState(1206); expression(0);
				}
				}
				setState(1211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StatementExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public StatementExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statementExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterStatementExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitStatementExpression(this);
		}
	}

	public final StatementExpressionContext statementExpression() throws RecognitionException {
		StatementExpressionContext _localctx = new StatementExpressionContext(_ctx, getState());
		enterRule(_localctx, 210, RULE_statementExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1212); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConstantExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_constantExpression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterConstantExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitConstantExpression(this);
		}
	}

	public final ConstantExpressionContext constantExpression() throws RecognitionException {
		ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
		enterRule(_localctx, 212, RULE_constantExpression);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1214); expression(0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
	 
		public ExpressionContext() { }
		public void copyFrom(ExpressionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AssignmentExpressionContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public AssignmentExpressionContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterAssignmentExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitAssignmentExpression(this);
		}
	}
	public static class ExprDotNewContext extends ExpressionContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public InnerCreatorContext innerCreator() {
			return getRuleContext(InnerCreatorContext.class,0);
		}
		public ExprDotNewContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExprDotNew(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExprDotNew(this);
		}
	}
	public static class ExprDotExplicitGenericInvocContext extends ExpressionContext {
		public ExplicitGenericInvocationContext explicitGenericInvocation() {
			return getRuleContext(ExplicitGenericInvocationContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprDotExplicitGenericInvocContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExprDotExplicitGenericInvoc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExprDotExplicitGenericInvoc(this);
		}
	}
	public static class Expr4Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr4Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr4(this);
		}
	}
	public static class Expr16Context extends ExpressionContext {
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expr16Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr16(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr16(this);
		}
	}
	public static class Expr3Context extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expr3Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr3(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr3(this);
		}
	}
	public static class ExprArrayContext extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExprArrayContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExprArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExprArray(this);
		}
	}
	public static class Expr2Context extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expr2Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr2(this);
		}
	}
	public static class Expr14Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr14Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr14(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr14(this);
		}
	}
	public static class Expr1Context extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expr1Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr1(this);
		}
	}
	public static class Expr15Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr15Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr15(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr15(this);
		}
	}
	public static class Expr12Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr12Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr12(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr12(this);
		}
	}
	public static class Expr13Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr13Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr13(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr13(this);
		}
	}
	public static class Expr10Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr10Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr10(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr10(this);
		}
	}
	public static class Expr11Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr11Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr11(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr11(this);
		}
	}
	public static class ExprDotIdentifierContext extends ExpressionContext {
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprDotIdentifierContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExprDotIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExprDotIdentifier(this);
		}
	}
	public static class ExprPrimaryContext extends ExpressionContext {
		public PrimaryContext primary() {
			return getRuleContext(PrimaryContext.class,0);
		}
		public ExprPrimaryContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExprPrimary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExprPrimary(this);
		}
	}
	public static class CallContext extends ExpressionContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public CallContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterCall(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitCall(this);
		}
	}
	public static class Expr9Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr9Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr9(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr9(this);
		}
	}
	public static class Expr5Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr5Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr5(this);
		}
	}
	public static class Expr6Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr6Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr6(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr6(this);
		}
	}
	public static class Expr7Context extends ExpressionContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public Expr7Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr7(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr7(this);
		}
	}
	public static class Expr8Context extends ExpressionContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Expr8Context(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExpr8(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExpr8(this);
		}
	}
	public static class ExprCastContext extends ExpressionContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprCastContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExprCast(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExprCast(this);
		}
	}
	public static class ConstructorContext extends ExpressionContext {
		public CreatorContext creator() {
			return getRuleContext(CreatorContext.class,0);
		}
		public ConstructorContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterConstructor(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitConstructor(this);
		}
	}
	public static class ExprDotthisContext extends ExpressionContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public ExprDotthisContext(ExpressionContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExprDotthis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExprDotthis(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		return expression(0);
	}

	private ExpressionContext expression(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState);
		ExpressionContext _prevctx = _localctx;
		int _startState = 214;
		enterRecursionRule(_localctx, 214, RULE_expression, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1229);
			switch ( getInterpreter().adaptivePredict(_input,137,_ctx) ) {
			case 1:
				{
				_localctx = new ExprCastContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(1217); match(LPAREN);
				setState(1218); type();
				setState(1219); match(RPAREN);
				setState(1220); expression(17);
				}
				break;

			case 2:
				{
				_localctx = new Expr2Context(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1222);
				_la = _input.LA(1);
				if ( !(((((_la - 79)) & ~0x3f) == 0 && ((1L << (_la - 79)) & ((1L << (INC - 79)) | (1L << (DEC - 79)) | (1L << (ADD - 79)) | (1L << (SUB - 79)))) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(1223); expression(15);
				}
				break;

			case 3:
				{
				_localctx = new Expr3Context(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1224);
				_la = _input.LA(1);
				if ( !(_la==BANG || _la==TILDE) ) {
				_errHandler.recoverInline(this);
				}
				consume();
				setState(1225); expression(14);
				}
				break;

			case 4:
				{
				_localctx = new ExprPrimaryContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1226); primary();
				}
				break;

			case 5:
				{
				_localctx = new ConstructorContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(1227); match(NEW);
				setState(1228); creator();
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(1316);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,142,_ctx);
			while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(1314);
					switch ( getInterpreter().adaptivePredict(_input,141,_ctx) ) {
					case 1:
						{
						_localctx = new Expr4Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1231);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(1232);
						_la = _input.LA(1);
						if ( !(((((_la - 83)) & ~0x3f) == 0 && ((1L << (_la - 83)) & ((1L << (MUL - 83)) | (1L << (DIV - 83)) | (1L << (MOD - 83)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(1233); expression(14);
						}
						break;

					case 2:
						{
						_localctx = new Expr5Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1234);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(1235);
						_la = _input.LA(1);
						if ( !(_la==ADD || _la==SUB) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(1236); expression(13);
						}
						break;

					case 3:
						{
						_localctx = new Expr6Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1237);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(1245);
						switch ( getInterpreter().adaptivePredict(_input,138,_ctx) ) {
						case 1:
							{
							setState(1238); match(LT);
							setState(1239); match(LT);
							}
							break;

						case 2:
							{
							setState(1240); match(GT);
							setState(1241); match(GT);
							setState(1242); match(GT);
							}
							break;

						case 3:
							{
							setState(1243); match(GT);
							setState(1244); match(GT);
							}
							break;
						}
						setState(1247); expression(12);
						}
						break;

					case 4:
						{
						_localctx = new Expr7Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1248);
						if (!(precpred(_ctx, 10))) throw new FailedPredicateException(this, "precpred(_ctx, 10)");
						setState(1249);
						_la = _input.LA(1);
						if ( !(((((_la - 67)) & ~0x3f) == 0 && ((1L << (_la - 67)) & ((1L << (GT - 67)) | (1L << (LT - 67)) | (1L << (LE - 67)) | (1L << (GE - 67)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(1250); expression(11);
						}
						break;

					case 5:
						{
						_localctx = new Expr9Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1251);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(1252);
						_la = _input.LA(1);
						if ( !(_la==EQUAL || _la==NOTEQUAL) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(1253); expression(9);
						}
						break;

					case 6:
						{
						_localctx = new Expr10Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1254);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(1255); match(BITAND);
						setState(1256); expression(8);
						}
						break;

					case 7:
						{
						_localctx = new Expr11Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1257);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(1258); match(CARET);
						setState(1259); expression(7);
						}
						break;

					case 8:
						{
						_localctx = new Expr12Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1260);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(1261); match(BITOR);
						setState(1262); expression(6);
						}
						break;

					case 9:
						{
						_localctx = new Expr13Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1263);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(1264); match(AND);
						setState(1265); expression(5);
						}
						break;

					case 10:
						{
						_localctx = new Expr14Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1266);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(1267); match(OR);
						setState(1268); expression(4);
						}
						break;

					case 11:
						{
						_localctx = new Expr15Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1269);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(1270); match(QUESTION);
						setState(1271); expression(0);
						setState(1272); match(COLON);
						setState(1273); expression(3);
						}
						break;

					case 12:
						{
						_localctx = new AssignmentExpressionContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1275);
						if (!(precpred(_ctx, 1))) throw new FailedPredicateException(this, "precpred(_ctx, 1)");
						setState(1276);
						_la = _input.LA(1);
						if ( !(((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (ASSIGN - 66)) | (1L << (ADD_ASSIGN - 66)) | (1L << (SUB_ASSIGN - 66)) | (1L << (MUL_ASSIGN - 66)) | (1L << (DIV_ASSIGN - 66)) | (1L << (AND_ASSIGN - 66)) | (1L << (OR_ASSIGN - 66)) | (1L << (XOR_ASSIGN - 66)) | (1L << (MOD_ASSIGN - 66)) | (1L << (LSHIFT_ASSIGN - 66)) | (1L << (RSHIFT_ASSIGN - 66)) | (1L << (URSHIFT_ASSIGN - 66)))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						setState(1277); expression(2);
						}
						break;

					case 13:
						{
						_localctx = new ExprDotIdentifierContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1278);
						if (!(precpred(_ctx, 25))) throw new FailedPredicateException(this, "precpred(_ctx, 25)");
						setState(1279); match(DOT);
						setState(1280); match(Identifier);
						}
						break;

					case 14:
						{
						_localctx = new ExprDotthisContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1281);
						if (!(precpred(_ctx, 24))) throw new FailedPredicateException(this, "precpred(_ctx, 24)");
						setState(1282); match(DOT);
						setState(1283); match(THIS);
						}
						break;

					case 15:
						{
						_localctx = new ExprDotNewContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1284);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(1285); match(DOT);
						setState(1286); match(NEW);
						setState(1288);
						_la = _input.LA(1);
						if (_la==LT) {
							{
							setState(1287); nonWildcardTypeArguments();
							}
						}

						setState(1290); innerCreator();
						}
						break;

					case 16:
						{
						_localctx = new Expr16Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1291);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(1292); match(DOT);
						setState(1293); match(SUPER);
						setState(1294); superSuffix();
						}
						break;

					case 17:
						{
						_localctx = new ExprDotExplicitGenericInvocContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1295);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(1296); match(DOT);
						setState(1297); explicitGenericInvocation();
						}
						break;

					case 18:
						{
						_localctx = new ExprArrayContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1298);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(1299); match(LBRACK);
						setState(1300); expression(0);
						setState(1301); match(RBRACK);
						}
						break;

					case 19:
						{
						_localctx = new CallContext(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1303);
						if (!(precpred(_ctx, 19))) throw new FailedPredicateException(this, "precpred(_ctx, 19)");
						setState(1304); match(LPAREN);
						setState(1306);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)))) != 0)) {
							{
							setState(1305); expressionList();
							}
						}

						setState(1308); match(RPAREN);
						}
						break;

					case 20:
						{
						_localctx = new Expr1Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1309);
						if (!(precpred(_ctx, 16))) throw new FailedPredicateException(this, "precpred(_ctx, 16)");
						setState(1310);
						_la = _input.LA(1);
						if ( !(_la==INC || _la==DEC) ) {
						_errHandler.recoverInline(this);
						}
						consume();
						}
						break;

					case 21:
						{
						_localctx = new Expr8Context(new ExpressionContext(_parentctx, _parentState));
						pushNewRecursionContext(_localctx, _startState, RULE_expression);
						setState(1311);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(1312); match(INSTANCEOF);
						setState(1313); type();
						}
						break;
					}
					} 
				}
				setState(1318);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,142,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class PrimaryContext extends ParserRuleContext {
		public PrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_primary; }
	 
		public PrimaryContext() { }
		public void copyFrom(PrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class PrimaryIdentifierContext extends PrimaryContext {
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public PrimaryIdentifierContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPrimaryIdentifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPrimaryIdentifier(this);
		}
	}
	public static class Primary6Context extends PrimaryContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public Primary6Context(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPrimary6(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPrimary6(this);
		}
	}
	public static class Primary5Context extends PrimaryContext {
		public Primary5Context(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPrimary5(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPrimary5(this);
		}
	}
	public static class Primary4Context extends PrimaryContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public Primary4Context(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPrimary4(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPrimary4(this);
		}
	}
	public static class PrimaryLiteralContext extends PrimaryContext {
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public PrimaryLiteralContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPrimaryLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPrimaryLiteral(this);
		}
	}
	public static class PrimarySuperContext extends PrimaryContext {
		public SuperPrimaryContext superPrimary() {
			return getRuleContext(SuperPrimaryContext.class,0);
		}
		public PrimarySuperContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPrimarySuper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPrimarySuper(this);
		}
	}
	public static class Primary1Context extends PrimaryContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Primary1Context(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPrimary1(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPrimary1(this);
		}
	}
	public static class PrimaryThisContext extends PrimaryContext {
		public PrimaryThisContext(PrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterPrimaryThis(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitPrimaryThis(this);
		}
	}

	public final PrimaryContext primary() throws RecognitionException {
		PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
		enterRule(_localctx, 216, RULE_primary);
		try {
			setState(1340);
			switch ( getInterpreter().adaptivePredict(_input,144,_ctx) ) {
			case 1:
				_localctx = new Primary1Context(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(1319); match(LPAREN);
				setState(1320); expression(0);
				setState(1321); match(RPAREN);
				}
				break;

			case 2:
				_localctx = new PrimaryThisContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(1323); match(THIS);
				}
				break;

			case 3:
				_localctx = new PrimarySuperContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(1324); superPrimary();
				}
				break;

			case 4:
				_localctx = new PrimaryLiteralContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(1325); literal();
				}
				break;

			case 5:
				_localctx = new PrimaryIdentifierContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(1326); match(Identifier);
				}
				break;

			case 6:
				_localctx = new Primary4Context(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(1327); type();
				setState(1328); match(DOT);
				setState(1329); match(CLASS);
				}
				break;

			case 7:
				_localctx = new Primary5Context(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(1331); match(VOID);
				setState(1332); match(DOT);
				setState(1333); match(CLASS);
				}
				break;

			case 8:
				_localctx = new Primary6Context(_localctx);
				enterOuterAlt(_localctx, 8);
				{
				setState(1334); nonWildcardTypeArguments();
				setState(1338);
				switch (_input.LA(1)) {
				case SUPER:
				case Identifier:
					{
					setState(1335); explicitGenericInvocationSuffix();
					}
					break;
				case THIS:
					{
					setState(1336); match(THIS);
					setState(1337); arguments();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperPrimaryContext extends ParserRuleContext {
		public SuperPrimaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superPrimary; }
	 
		public SuperPrimaryContext() { }
		public void copyFrom(SuperPrimaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SuperContext extends SuperPrimaryContext {
		public SuperContext(SuperPrimaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterSuper(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitSuper(this);
		}
	}

	public final SuperPrimaryContext superPrimary() throws RecognitionException {
		SuperPrimaryContext _localctx = new SuperPrimaryContext(_ctx, getState());
		enterRule(_localctx, 218, RULE_superPrimary);
		try {
			_localctx = new SuperContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(1342); match(SUPER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatorContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ArrayCreatorRestContext arrayCreatorRest() {
			return getRuleContext(ArrayCreatorRestContext.class,0);
		}
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public CreatedNameContext createdName() {
			return getRuleContext(CreatedNameContext.class,0);
		}
		public CreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_creator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitCreator(this);
		}
	}

	public final CreatorContext creator() throws RecognitionException {
		CreatorContext _localctx = new CreatorContext(_ctx, getState());
		enterRule(_localctx, 220, RULE_creator);
		try {
			setState(1353);
			switch (_input.LA(1)) {
			case LT:
				enterOuterAlt(_localctx, 1);
				{
				setState(1344); nonWildcardTypeArguments();
				setState(1345); createdName();
				setState(1346); classCreatorRest();
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(1348); createdName();
				setState(1351);
				switch (_input.LA(1)) {
				case LBRACK:
					{
					setState(1349); arrayCreatorRest();
					}
					break;
				case LPAREN:
					{
					setState(1350); classCreatorRest();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class CreatedNameContext extends ParserRuleContext {
		public TypeArgumentsOrDiamondContext typeArgumentsOrDiamond(int i) {
			return getRuleContext(TypeArgumentsOrDiamondContext.class,i);
		}
		public TerminalNode Identifier(int i) {
			return getToken(JavaFragmentParser.Identifier, i);
		}
		public List<TerminalNode> Identifier() { return getTokens(JavaFragmentParser.Identifier); }
		public List<TypeArgumentsOrDiamondContext> typeArgumentsOrDiamond() {
			return getRuleContexts(TypeArgumentsOrDiamondContext.class);
		}
		public PrimitiveTypeContext primitiveType() {
			return getRuleContext(PrimitiveTypeContext.class,0);
		}
		public CreatedNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_createdName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterCreatedName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitCreatedName(this);
		}
	}

	public final CreatedNameContext createdName() throws RecognitionException {
		CreatedNameContext _localctx = new CreatedNameContext(_ctx, getState());
		enterRule(_localctx, 222, RULE_createdName);
		int _la;
		try {
			setState(1370);
			switch (_input.LA(1)) {
			case Identifier:
				enterOuterAlt(_localctx, 1);
				{
				setState(1355); match(Identifier);
				setState(1357);
				_la = _input.LA(1);
				if (_la==LT) {
					{
					setState(1356); typeArgumentsOrDiamond();
					}
				}

				setState(1366);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(1359); match(DOT);
					setState(1360); match(Identifier);
					setState(1362);
					_la = _input.LA(1);
					if (_la==LT) {
						{
						setState(1361); typeArgumentsOrDiamond();
						}
					}

					}
					}
					setState(1368);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case SHORT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1369); primitiveType();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InnerCreatorContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() {
			return getRuleContext(NonWildcardTypeArgumentsOrDiamondContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public ClassCreatorRestContext classCreatorRest() {
			return getRuleContext(ClassCreatorRestContext.class,0);
		}
		public InnerCreatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_innerCreator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterInnerCreator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitInnerCreator(this);
		}
	}

	public final InnerCreatorContext innerCreator() throws RecognitionException {
		InnerCreatorContext _localctx = new InnerCreatorContext(_ctx, getState());
		enterRule(_localctx, 224, RULE_innerCreator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1372); match(Identifier);
			setState(1374);
			_la = _input.LA(1);
			if (_la==LT) {
				{
				setState(1373); nonWildcardTypeArgumentsOrDiamond();
				}
			}

			setState(1376); classCreatorRest();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrayCreatorRestContext extends ParserRuleContext {
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ArrayInitializerContext arrayInitializer() {
			return getRuleContext(ArrayInitializerContext.class,0);
		}
		public ArrayCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterArrayCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitArrayCreatorRest(this);
		}
	}

	public final ArrayCreatorRestContext arrayCreatorRest() throws RecognitionException {
		ArrayCreatorRestContext _localctx = new ArrayCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 226, RULE_arrayCreatorRest);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(1378); match(LBRACK);
			setState(1406);
			switch (_input.LA(1)) {
			case RBRACK:
				{
				setState(1379); match(RBRACK);
				setState(1384);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==LBRACK) {
					{
					{
					setState(1380); match(LBRACK);
					setState(1381); match(RBRACK);
					}
					}
					setState(1386);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(1387); arrayInitializer();
				}
				break;
			case BOOLEAN:
			case BYTE:
			case CHAR:
			case DOUBLE:
			case FLOAT:
			case INT:
			case LONG:
			case NEW:
			case SHORT:
			case SUPER:
			case THIS:
			case VOID:
			case IntegerLiteral:
			case FloatingPointLiteral:
			case BooleanLiteral:
			case CharacterLiteral:
			case StringLiteral:
			case NullLiteral:
			case LPAREN:
			case LT:
			case BANG:
			case TILDE:
			case INC:
			case DEC:
			case ADD:
			case SUB:
			case Identifier:
				{
				setState(1388); expression(0);
				setState(1389); match(RBRACK);
				setState(1396);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,153,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1390); match(LBRACK);
						setState(1391); expression(0);
						setState(1392); match(RBRACK);
						}
						} 
					}
					setState(1398);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,153,_ctx);
				}
				setState(1403);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,154,_ctx);
				while ( _alt!=2 && _alt!=ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(1399); match(LBRACK);
						setState(1400); match(RBRACK);
						}
						} 
					}
					setState(1405);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,154,_ctx);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClassCreatorRestContext extends ParserRuleContext {
		public ClassBodyContext classBody() {
			return getRuleContext(ClassBodyContext.class,0);
		}
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ClassCreatorRestContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_classCreatorRest; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterClassCreatorRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitClassCreatorRest(this);
		}
	}

	public final ClassCreatorRestContext classCreatorRest() throws RecognitionException {
		ClassCreatorRestContext _localctx = new ClassCreatorRestContext(_ctx, getState());
		enterRule(_localctx, 228, RULE_classCreatorRest);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1408); arguments();
			setState(1410);
			switch ( getInterpreter().adaptivePredict(_input,156,_ctx) ) {
			case 1:
				{
				setState(1409); classBody();
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitGenericInvocationContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() {
			return getRuleContext(ExplicitGenericInvocationSuffixContext.class,0);
		}
		public ExplicitGenericInvocationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocation; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExplicitGenericInvocation(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExplicitGenericInvocation(this);
		}
	}

	public final ExplicitGenericInvocationContext explicitGenericInvocation() throws RecognitionException {
		ExplicitGenericInvocationContext _localctx = new ExplicitGenericInvocationContext(_ctx, getState());
		enterRule(_localctx, 230, RULE_explicitGenericInvocation);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1412); nonWildcardTypeArguments();
			setState(1413); explicitGenericInvocationSuffix();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonWildcardTypeArgumentsContext extends ParserRuleContext {
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public NonWildcardTypeArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterNonWildcardTypeArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitNonWildcardTypeArguments(this);
		}
	}

	public final NonWildcardTypeArgumentsContext nonWildcardTypeArguments() throws RecognitionException {
		NonWildcardTypeArgumentsContext _localctx = new NonWildcardTypeArgumentsContext(_ctx, getState());
		enterRule(_localctx, 232, RULE_nonWildcardTypeArguments);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1415); match(LT);
			setState(1416); typeList();
			setState(1417); match(GT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeArgumentsOrDiamondContext extends ParserRuleContext {
		public TypeArgumentsContext typeArguments() {
			return getRuleContext(TypeArgumentsContext.class,0);
		}
		public TypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitTypeArgumentsOrDiamond(this);
		}
	}

	public final TypeArgumentsOrDiamondContext typeArgumentsOrDiamond() throws RecognitionException {
		TypeArgumentsOrDiamondContext _localctx = new TypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 234, RULE_typeArgumentsOrDiamond);
		try {
			setState(1422);
			switch ( getInterpreter().adaptivePredict(_input,157,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1419); match(LT);
				setState(1420); match(GT);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1421); typeArguments();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NonWildcardTypeArgumentsOrDiamondContext extends ParserRuleContext {
		public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
			return getRuleContext(NonWildcardTypeArgumentsContext.class,0);
		}
		public NonWildcardTypeArgumentsOrDiamondContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nonWildcardTypeArgumentsOrDiamond; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterNonWildcardTypeArgumentsOrDiamond(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitNonWildcardTypeArgumentsOrDiamond(this);
		}
	}

	public final NonWildcardTypeArgumentsOrDiamondContext nonWildcardTypeArgumentsOrDiamond() throws RecognitionException {
		NonWildcardTypeArgumentsOrDiamondContext _localctx = new NonWildcardTypeArgumentsOrDiamondContext(_ctx, getState());
		enterRule(_localctx, 236, RULE_nonWildcardTypeArgumentsOrDiamond);
		try {
			setState(1427);
			switch ( getInterpreter().adaptivePredict(_input,158,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(1424); match(LT);
				setState(1425); match(GT);
				}
				break;

			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(1426); nonWildcardTypeArguments();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SuperSuffixContext extends ParserRuleContext {
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public SuperSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_superSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterSuperSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitSuperSuffix(this);
		}
	}

	public final SuperSuffixContext superSuffix() throws RecognitionException {
		SuperSuffixContext _localctx = new SuperSuffixContext(_ctx, getState());
		enterRule(_localctx, 238, RULE_superSuffix);
		try {
			setState(1435);
			switch (_input.LA(1)) {
			case LPAREN:
				enterOuterAlt(_localctx, 1);
				{
				setState(1429); arguments();
				}
				break;
			case DOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(1430); match(DOT);
				setState(1431); match(Identifier);
				setState(1433);
				switch ( getInterpreter().adaptivePredict(_input,159,_ctx) ) {
				case 1:
					{
					setState(1432); arguments();
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExplicitGenericInvocationSuffixContext extends ParserRuleContext {
		public SuperSuffixContext superSuffix() {
			return getRuleContext(SuperSuffixContext.class,0);
		}
		public TerminalNode Identifier() { return getToken(JavaFragmentParser.Identifier, 0); }
		public ArgumentsContext arguments() {
			return getRuleContext(ArgumentsContext.class,0);
		}
		public ExplicitGenericInvocationSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_explicitGenericInvocationSuffix; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterExplicitGenericInvocationSuffix(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitExplicitGenericInvocationSuffix(this);
		}
	}

	public final ExplicitGenericInvocationSuffixContext explicitGenericInvocationSuffix() throws RecognitionException {
		ExplicitGenericInvocationSuffixContext _localctx = new ExplicitGenericInvocationSuffixContext(_ctx, getState());
		enterRule(_localctx, 240, RULE_explicitGenericInvocationSuffix);
		try {
			setState(1441);
			switch (_input.LA(1)) {
			case SUPER:
				enterOuterAlt(_localctx, 1);
				{
				setState(1437); match(SUPER);
				setState(1438); superSuffix();
				}
				break;
			case Identifier:
				enterOuterAlt(_localctx, 2);
				{
				setState(1439); match(Identifier);
				setState(1440); arguments();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArgumentsContext extends ParserRuleContext {
		public ExpressionListContext expressionList() {
			return getRuleContext(ExpressionListContext.class,0);
		}
		public ArgumentsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arguments; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterArguments(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitArguments(this);
		}
	}

	public final ArgumentsContext arguments() throws RecognitionException {
		ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
		enterRule(_localctx, 242, RULE_arguments);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1443); match(LPAREN);
			setState(1445);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << BOOLEAN) | (1L << BYTE) | (1L << CHAR) | (1L << DOUBLE) | (1L << FLOAT) | (1L << INT) | (1L << LONG) | (1L << NEW) | (1L << SHORT) | (1L << SUPER) | (1L << THIS) | (1L << VOID) | (1L << IntegerLiteral) | (1L << FloatingPointLiteral) | (1L << BooleanLiteral) | (1L << CharacterLiteral) | (1L << StringLiteral) | (1L << NullLiteral) | (1L << LPAREN))) != 0) || ((((_la - 68)) & ~0x3f) == 0 && ((1L << (_la - 68)) & ((1L << (LT - 68)) | (1L << (BANG - 68)) | (1L << (TILDE - 68)) | (1L << (INC - 68)) | (1L << (DEC - 68)) | (1L << (ADD - 68)) | (1L << (SUB - 68)) | (1L << (Identifier - 68)))) != 0)) {
				{
				setState(1444); expressionList();
				}
			}

			setState(1447); match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class EllipsisTokenContext extends ParserRuleContext {
		public TerminalNode ELLIPSIS3() { return getToken(JavaFragmentParser.ELLIPSIS3, 0); }
		public TerminalNode ELLIPSIS4() { return getToken(JavaFragmentParser.ELLIPSIS4, 0); }
		public TerminalNode ELLIPSIS2() { return getToken(JavaFragmentParser.ELLIPSIS2, 0); }
		public EllipsisTokenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_ellipsisToken; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).enterEllipsisToken(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JavaFragmentListener ) ((JavaFragmentListener)listener).exitEllipsisToken(this);
		}
	}

	public final EllipsisTokenContext ellipsisToken() throws RecognitionException {
		EllipsisTokenContext _localctx = new EllipsisTokenContext(_ctx, getState());
		enterRule(_localctx, 244, RULE_ellipsisToken);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(1449);
			_la = _input.LA(1);
			if ( !(((((_la - 102)) & ~0x3f) == 0 && ((1L << (_la - 102)) & ((1L << (ELLIPSIS4 - 102)) | (1L << (ELLIPSIS3 - 102)) | (1L << (ELLIPSIS2 - 102)))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			consume();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 107: return expression_sempred((ExpressionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0: return precpred(_ctx, 13);

		case 1: return precpred(_ctx, 12);

		case 2: return precpred(_ctx, 11);

		case 3: return precpred(_ctx, 10);

		case 4: return precpred(_ctx, 8);

		case 5: return precpred(_ctx, 7);

		case 6: return precpred(_ctx, 6);

		case 7: return precpred(_ctx, 5);

		case 8: return precpred(_ctx, 4);

		case 9: return precpred(_ctx, 3);

		case 10: return precpred(_ctx, 2);

		case 11: return precpred(_ctx, 1);

		case 12: return precpred(_ctx, 25);

		case 13: return precpred(_ctx, 24);

		case 14: return precpred(_ctx, 23);

		case 15: return precpred(_ctx, 22);

		case 17: return precpred(_ctx, 20);

		case 16: return precpred(_ctx, 21);

		case 19: return precpred(_ctx, 16);

		case 18: return precpred(_ctx, 19);

		case 20: return precpred(_ctx, 9);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3m\u05ae\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\tF\4G\tG\4H\tH\4I"+
		"\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4R\tR\4S\tS\4T\tT"+
		"\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]\t]\4^\t^\4_\t_\4"+
		"`\t`\4a\ta\4b\tb\4c\tc\4d\td\4e\te\4f\tf\4g\tg\4h\th\4i\ti\4j\tj\4k\t"+
		"k\4l\tl\4m\tm\4n\tn\4o\to\4p\tp\4q\tq\4r\tr\4s\ts\4t\tt\4u\tu\4v\tv\4"+
		"w\tw\4x\tx\4y\ty\4z\tz\4{\t{\4|\t|\3\2\3\2\3\2\3\2\7\2\u00fd\n\2\f\2\16"+
		"\2\u0100\13\2\3\2\7\2\u0103\n\2\f\2\16\2\u0106\13\2\3\2\3\2\7\2\u010a"+
		"\n\2\f\2\16\2\u010d\13\2\5\2\u010f\n\2\3\3\5\3\u0112\n\3\3\3\7\3\u0115"+
		"\n\3\f\3\16\3\u0118\13\3\3\3\7\3\u011b\n\3\f\3\16\3\u011e\13\3\3\3\3\3"+
		"\3\4\7\4\u0123\n\4\f\4\16\4\u0126\13\4\3\4\3\4\3\4\3\4\3\5\3\5\5\5\u012e"+
		"\n\5\3\5\3\5\3\5\5\5\u0133\n\5\3\5\3\5\3\6\7\6\u0138\n\6\f\6\16\6\u013b"+
		"\13\6\3\6\3\6\7\6\u013f\n\6\f\6\16\6\u0142\13\6\3\6\3\6\7\6\u0146\n\6"+
		"\f\6\16\6\u0149\13\6\3\6\3\6\7\6\u014d\n\6\f\6\16\6\u0150\13\6\3\6\3\6"+
		"\5\6\u0154\n\6\3\7\3\7\5\7\u0158\n\7\3\b\3\b\5\b\u015c\n\b\3\t\3\t\5\t"+
		"\u0160\n\t\3\n\3\n\3\n\5\n\u0165\n\n\3\n\3\n\5\n\u0169\n\n\3\n\3\n\5\n"+
		"\u016d\n\n\3\n\3\n\3\13\3\13\3\13\3\13\7\13\u0175\n\13\f\13\16\13\u0178"+
		"\13\13\3\13\3\13\3\f\3\f\3\f\5\f\u017f\n\f\3\r\3\r\3\r\7\r\u0184\n\r\f"+
		"\r\16\r\u0187\13\r\3\16\3\16\3\16\3\16\5\16\u018d\n\16\3\16\3\16\5\16"+
		"\u0191\n\16\3\16\5\16\u0194\n\16\3\16\5\16\u0197\n\16\3\16\3\16\3\17\3"+
		"\17\3\17\7\17\u019e\n\17\f\17\16\17\u01a1\13\17\3\20\7\20\u01a4\n\20\f"+
		"\20\16\20\u01a7\13\20\3\20\3\20\5\20\u01ab\n\20\3\20\5\20\u01ae\n\20\3"+
		"\21\3\21\7\21\u01b2\n\21\f\21\16\21\u01b5\13\21\3\22\3\22\3\22\5\22\u01ba"+
		"\n\22\3\22\3\22\5\22\u01be\n\22\3\22\3\22\3\23\3\23\3\23\7\23\u01c5\n"+
		"\23\f\23\16\23\u01c8\13\23\3\24\3\24\7\24\u01cc\n\24\f\24\16\24\u01cf"+
		"\13\24\3\24\3\24\3\25\3\25\7\25\u01d5\n\25\f\25\16\25\u01d8\13\25\3\25"+
		"\3\25\3\26\3\26\5\26\u01de\n\26\3\26\3\26\3\26\3\26\3\26\3\26\5\26\u01e6"+
		"\n\26\3\27\7\27\u01e9\n\27\f\27\16\27\u01ec\13\27\3\27\3\27\7\27\u01f0"+
		"\n\27\f\27\16\27\u01f3\13\27\3\27\3\27\7\27\u01f7\n\27\f\27\16\27\u01fa"+
		"\13\27\3\27\3\27\7\27\u01fe\n\27\f\27\16\27\u0201\13\27\3\27\3\27\7\27"+
		"\u0205\n\27\f\27\16\27\u0208\13\27\3\27\3\27\7\27\u020c\n\27\f\27\16\27"+
		"\u020f\13\27\3\27\3\27\7\27\u0213\n\27\f\27\16\27\u0216\13\27\3\27\3\27"+
		"\7\27\u021a\n\27\f\27\16\27\u021d\13\27\3\27\3\27\7\27\u0221\n\27\f\27"+
		"\16\27\u0224\13\27\3\27\5\27\u0227\n\27\3\30\3\30\3\30\5\30\u022c\n\30"+
		"\3\31\3\31\5\31\u0230\n\31\3\31\3\31\3\31\3\31\7\31\u0236\n\31\f\31\16"+
		"\31\u0239\13\31\3\31\5\31\u023c\n\31\3\32\3\32\3\32\3\33\3\33\3\33\3\34"+
		"\3\34\3\34\3\34\5\34\u0248\n\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\3\37\7\37\u0254\n\37\f\37\16\37\u0257\13\37\3\37\3\37\5\37\u025b"+
		"\n\37\3 \3 \3 \3 \3 \3 \3 \5 \u0264\n \3!\3!\3!\3!\7!\u026a\n!\f!\16!"+
		"\u026d\13!\3!\3!\3\"\3\"\3\"\7\"\u0274\n\"\f\"\16\"\u0277\13\"\3\"\3\""+
		"\3\"\3#\3#\5#\u027e\n#\3#\3#\3#\3#\7#\u0284\n#\f#\16#\u0287\13#\3#\3#"+
		"\5#\u028b\n#\3#\3#\3$\3$\3$\3%\3%\3%\7%\u0295\n%\f%\16%\u0298\13%\3&\3"+
		"&\3&\5&\u029d\n&\3\'\3\'\3\'\7\'\u02a2\n\'\f\'\16\'\u02a5\13\'\3(\3(\5"+
		"(\u02a9\n(\3)\3)\3)\3)\7)\u02af\n)\f)\16)\u02b2\13)\3)\5)\u02b5\n)\5)"+
		"\u02b7\n)\3)\3)\3*\3*\3+\3+\3+\7+\u02c0\n+\f+\16+\u02c3\13+\3+\3+\3+\7"+
		"+\u02c8\n+\f+\16+\u02cb\13+\5+\u02cd\n+\3,\3,\5,\u02d1\n,\3,\3,\3,\5,"+
		"\u02d6\n,\7,\u02d8\n,\f,\16,\u02db\13,\3-\3-\3.\3.\3.\3.\7.\u02e3\n.\f"+
		".\16.\u02e6\13.\3.\3.\3/\3/\3/\3/\5/\u02ee\n/\5/\u02f0\n/\3\60\3\60\3"+
		"\60\7\60\u02f5\n\60\f\60\16\60\u02f8\13\60\3\61\3\61\5\61\u02fc\n\61\3"+
		"\61\3\61\3\62\3\62\3\62\7\62\u0303\n\62\f\62\16\62\u0306\13\62\3\62\3"+
		"\62\5\62\u030a\n\62\3\62\5\62\u030d\n\62\3\63\7\63\u0310\n\63\f\63\16"+
		"\63\u0313\13\63\3\63\3\63\3\63\3\64\7\64\u0319\n\64\f\64\16\64\u031c\13"+
		"\64\3\64\3\64\3\64\3\64\3\65\3\65\3\65\3\65\3\66\3\66\3\66\3\66\3\67\3"+
		"\67\3\67\7\67\u032d\n\67\f\67\16\67\u0330\13\67\38\38\38\38\38\38\58\u0338"+
		"\n8\39\39\39\39\39\59\u033f\n9\39\59\u0342\n9\3:\3:\3;\3;\3;\7;\u0349"+
		"\n;\f;\16;\u034c\13;\3<\3<\3<\3<\3=\3=\3=\5=\u0355\n=\3>\3>\3>\3>\7>\u035b"+
		"\n>\f>\16>\u035e\13>\5>\u0360\n>\3>\5>\u0363\n>\3>\3>\3?\3?\3?\3?\3?\3"+
		"@\3@\7@\u036e\n@\f@\16@\u0371\13@\3@\3@\3A\7A\u0376\nA\fA\16A\u0379\13"+
		"A\3A\3A\5A\u037d\nA\3B\3B\3B\3B\3B\3B\5B\u0385\nB\3B\3B\5B\u0389\nB\3"+
		"B\3B\5B\u038d\nB\3B\3B\5B\u0391\nB\5B\u0393\nB\3C\3C\5C\u0397\nC\3D\3"+
		"D\3D\3D\5D\u039d\nD\3E\3E\3F\3F\3F\3G\7G\u03a5\nG\fG\16G\u03a8\13G\3H"+
		"\3H\3H\3H\5H\u03ae\nH\3I\3I\3I\3J\7J\u03b4\nJ\fJ\16J\u03b7\13J\3J\3J\3"+
		"J\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\3K\5K\u03cf\n"+
		"K\3L\3L\3L\3L\3M\3M\3M\3M\5M\u03d9\nM\3M\3M\3N\3N\3N\5N\u03e0\nN\3N\3"+
		"N\5N\u03e4\nN\3O\3O\3P\3P\3Q\3Q\3Q\3Q\3Q\3Q\3R\3R\3R\3R\3S\3S\3S\3S\3"+
		"S\3S\3T\3T\3T\3T\3T\6T\u03ff\nT\rT\16T\u0400\3T\5T\u0404\nT\3T\5T\u0407"+
		"\nT\3U\3U\3U\3U\3U\3U\7U\u040f\nU\fU\16U\u0412\13U\3U\5U\u0415\nU\3V\3"+
		"V\3V\3V\7V\u041b\nV\fV\16V\u041e\13V\3V\7V\u0421\nV\fV\16V\u0424\13V\3"+
		"V\3V\3W\3W\3W\3W\3W\3W\3X\3X\5X\u0430\nX\3X\3X\3Y\3Y\3Y\3Y\3Z\3Z\5Z\u043a"+
		"\nZ\3Z\3Z\3[\3[\5[\u0440\n[\3[\3[\3\\\3\\\3\\\3\\\3]\3]\3]\7]\u044b\n"+
		"]\f]\16]\u044e\13]\3]\3]\3]\3]\3]\3]\3]\3^\3^\3^\7^\u045a\n^\f^\16^\u045d"+
		"\13^\3_\3_\3_\3_\3_\3`\3`\3`\5`\u0467\n`\3`\3`\3a\3a\3a\7a\u046e\na\f"+
		"a\16a\u0471\13a\3b\7b\u0474\nb\fb\16b\u0477\13b\3b\3b\3b\3b\3b\3c\6c\u047f"+
		"\nc\rc\16c\u0480\3c\6c\u0484\nc\rc\16c\u0485\3d\3d\3d\3d\3d\3d\3d\3d\3"+
		"d\3d\5d\u0492\nd\3e\3e\5e\u0496\ne\3e\3e\5e\u049a\ne\3e\3e\5e\u049e\n"+
		"e\5e\u04a0\ne\3f\3f\5f\u04a4\nf\3g\7g\u04a7\ng\fg\16g\u04aa\13g\3g\3g"+
		"\3g\3g\3g\3h\3h\3i\3i\3i\3i\3j\3j\3j\7j\u04ba\nj\fj\16j\u04bd\13j\3k\3"+
		"k\3l\3l\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\5m\u04d0\nm\3m\3m\3m\3"+
		"m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\5m\u04e0\nm\3m\3m\3m\3m\3m\3m\3m\3m\3"+
		"m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3m\3"+
		"m\3m\3m\3m\3m\3m\3m\3m\3m\3m\5m\u050b\nm\3m\3m\3m\3m\3m\3m\3m\3m\3m\3"+
		"m\3m\3m\3m\3m\3m\3m\5m\u051d\nm\3m\3m\3m\3m\3m\3m\7m\u0525\nm\fm\16m\u0528"+
		"\13m\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\3n\5n\u053d"+
		"\nn\5n\u053f\nn\3o\3o\3p\3p\3p\3p\3p\3p\3p\5p\u054a\np\5p\u054c\np\3q"+
		"\3q\5q\u0550\nq\3q\3q\3q\5q\u0555\nq\7q\u0557\nq\fq\16q\u055a\13q\3q\5"+
		"q\u055d\nq\3r\3r\5r\u0561\nr\3r\3r\3s\3s\3s\3s\7s\u0569\ns\fs\16s\u056c"+
		"\13s\3s\3s\3s\3s\3s\3s\3s\7s\u0575\ns\fs\16s\u0578\13s\3s\3s\7s\u057c"+
		"\ns\fs\16s\u057f\13s\5s\u0581\ns\3t\3t\5t\u0585\nt\3u\3u\3u\3v\3v\3v\3"+
		"v\3w\3w\3w\5w\u0591\nw\3x\3x\3x\5x\u0596\nx\3y\3y\3y\3y\5y\u059c\ny\5"+
		"y\u059e\ny\3z\3z\3z\3z\5z\u05a4\nz\3{\3{\5{\u05a8\n{\3{\3{\3|\3|\3|\2"+
		"\3\u00d8}\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\66"+
		"8:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088\u008a"+
		"\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0\u00a2"+
		"\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8\u00ba"+
		"\u00bc\u00be\u00c0\u00c2\u00c4\u00c6\u00c8\u00ca\u00cc\u00ce\u00d0\u00d2"+
		"\u00d4\u00d6\u00d8\u00da\u00dc\u00de\u00e0\u00e2\u00e4\u00e6\u00e8\u00ea"+
		"\u00ec\u00ee\u00f0\u00f2\u00f4\u00f6\2\17\6\2  ,,\60\60\63\63\6\2\3\3"+
		"\24\24#%()\n\2\5\5\7\7\n\n\20\20\26\26\35\35\37\37\'\'\4\2\23\23**\3\2"+
		"QT\3\2GH\4\2UVZZ\3\2ST\4\2EFLM\4\2KKNN\4\2DD[e\3\2QR\3\2hj\u0622\2\u010e"+
		"\3\2\2\2\4\u0111\3\2\2\2\6\u0124\3\2\2\2\b\u012b\3\2\2\2\n\u0153\3\2\2"+
		"\2\f\u0157\3\2\2\2\16\u015b\3\2\2\2\20\u015f\3\2\2\2\22\u0161\3\2\2\2"+
		"\24\u0170\3\2\2\2\26\u017b\3\2\2\2\30\u0180\3\2\2\2\32\u0188\3\2\2\2\34"+
		"\u019a\3\2\2\2\36\u01a5\3\2\2\2 \u01af\3\2\2\2\"\u01b6\3\2\2\2$\u01c1"+
		"\3\2\2\2&\u01c9\3\2\2\2(\u01d2\3\2\2\2*\u01e5\3\2\2\2,\u0226\3\2\2\2."+
		"\u0228\3\2\2\2\60\u022f\3\2\2\2\62\u023d\3\2\2\2\64\u0240\3\2\2\2\66\u0243"+
		"\3\2\2\28\u024b\3\2\2\2:\u024e\3\2\2\2<\u025a\3\2\2\2>\u0263\3\2\2\2@"+
		"\u0265\3\2\2\2B\u0270\3\2\2\2D\u027d\3\2\2\2F\u028e\3\2\2\2H\u0291\3\2"+
		"\2\2J\u0299\3\2\2\2L\u029e\3\2\2\2N\u02a8\3\2\2\2P\u02aa\3\2\2\2R\u02ba"+
		"\3\2\2\2T\u02cc\3\2\2\2V\u02ce\3\2\2\2X\u02dc\3\2\2\2Z\u02de\3\2\2\2\\"+
		"\u02ef\3\2\2\2^\u02f1\3\2\2\2`\u02f9\3\2\2\2b\u030c\3\2\2\2d\u0311\3\2"+
		"\2\2f\u031a\3\2\2\2h\u0321\3\2\2\2j\u0325\3\2\2\2l\u0329\3\2\2\2n\u0337"+
		"\3\2\2\2p\u0339\3\2\2\2r\u0343\3\2\2\2t\u0345\3\2\2\2v\u034d\3\2\2\2x"+
		"\u0354\3\2\2\2z\u0356\3\2\2\2|\u0366\3\2\2\2~\u036b\3\2\2\2\u0080\u037c"+
		"\3\2\2\2\u0082\u0392\3\2\2\2\u0084\u0396\3\2\2\2\u0086\u0398\3\2\2\2\u0088"+
		"\u039e\3\2\2\2\u008a\u03a0\3\2\2\2\u008c\u03a6\3\2\2\2\u008e\u03ad\3\2"+
		"\2\2\u0090\u03af\3\2\2\2\u0092\u03b5\3\2\2\2\u0094\u03ce\3\2\2\2\u0096"+
		"\u03d0\3\2\2\2\u0098\u03d4\3\2\2\2\u009a\u03dc\3\2\2\2\u009c\u03e5\3\2"+
		"\2\2\u009e\u03e7\3\2\2\2\u00a0\u03e9\3\2\2\2\u00a2\u03ef\3\2\2\2\u00a4"+
		"\u03f3\3\2\2\2\u00a6\u03f9\3\2\2\2\u00a8\u0408\3\2\2\2\u00aa\u0416\3\2"+
		"\2\2\u00ac\u0427\3\2\2\2\u00ae\u042d\3\2\2\2\u00b0\u0433\3\2\2\2\u00b2"+
		"\u0437\3\2\2\2\u00b4\u043d\3\2\2\2\u00b6\u0443\3\2\2\2\u00b8\u0447\3\2"+
		"\2\2\u00ba\u0456\3\2\2\2\u00bc\u045e\3\2\2\2\u00be\u0463\3\2\2\2\u00c0"+
		"\u046a\3\2\2\2\u00c2\u0475\3\2\2\2\u00c4\u047e\3\2\2\2\u00c6\u0491\3\2"+
		"\2\2\u00c8\u049f\3\2\2\2\u00ca\u04a3\3\2\2\2\u00cc\u04a8\3\2\2\2\u00ce"+
		"\u04b0\3\2\2\2\u00d0\u04b2\3\2\2\2\u00d2\u04b6\3\2\2\2\u00d4\u04be\3\2"+
		"\2\2\u00d6\u04c0\3\2\2\2\u00d8\u04cf\3\2\2\2\u00da\u053e\3\2\2\2\u00dc"+
		"\u0540\3\2\2\2\u00de\u054b\3\2\2\2\u00e0\u055c\3\2\2\2\u00e2\u055e\3\2"+
		"\2\2\u00e4\u0564\3\2\2\2\u00e6\u0582\3\2\2\2\u00e8\u0586\3\2\2\2\u00ea"+
		"\u0589\3\2\2\2\u00ec\u0590\3\2\2\2\u00ee\u0595\3\2\2\2\u00f0\u059d\3\2"+
		"\2\2\u00f2\u05a3\3\2\2\2\u00f4\u05a5\3\2\2\2\u00f6\u05ab\3\2\2\2\u00f8"+
		"\u010f\5\4\3\2\u00f9\u010f\5.\30\2\u00fa\u010f\5\u008cG\2\u00fb\u00fd"+
		"\5*\26\2\u00fc\u00fb\3\2\2\2\u00fd\u0100\3\2\2\2\u00fe\u00fc\3\2\2\2\u00fe"+
		"\u00ff\3\2\2\2\u00ff\u010f\3\2\2\2\u0100\u00fe\3\2\2\2\u0101\u0103\5*"+
		"\26\2\u0102\u0101\3\2\2\2\u0103\u0106\3\2\2\2\u0104\u0102\3\2\2\2\u0104"+
		"\u0105\3\2\2\2\u0105\u0107\3\2\2\2\u0106\u0104\3\2\2\2\u0107\u010f\5\u008c"+
		"G\2\u0108\u010a\5\u00c4c\2\u0109\u0108\3\2\2\2\u010a\u010d\3\2\2\2\u010b"+
		"\u0109\3\2\2\2\u010b\u010c\3\2\2\2\u010c\u010f\3\2\2\2\u010d\u010b\3\2"+
		"\2\2\u010e\u00f8\3\2\2\2\u010e\u00f9\3\2\2\2\u010e\u00fa\3\2\2\2\u010e"+
		"\u00fe\3\2\2\2\u010e\u0104\3\2\2\2\u010e\u010b\3\2\2\2\u010f\3\3\2\2\2"+
		"\u0110\u0112\5\6\4\2\u0111\u0110\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0116"+
		"\3\2\2\2\u0113\u0115\5\b\5\2\u0114\u0113\3\2\2\2\u0115\u0118\3\2\2\2\u0116"+
		"\u0114\3\2\2\2\u0116\u0117\3\2\2\2\u0117\u011c\3\2\2\2\u0118\u0116\3\2"+
		"\2\2\u0119\u011b\5\n\6\2\u011a\u0119\3\2\2\2\u011b\u011e\3\2\2\2\u011c"+
		"\u011a\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u011f\3\2\2\2\u011e\u011c\3\2"+
		"\2\2\u011f\u0120\7\2\2\3\u0120\5\3\2\2\2\u0121\u0123\5p9\2\u0122\u0121"+
		"\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0127\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u0128\7\"\2\2\u0128\u0129\5l"+
		"\67\2\u0129\u012a\7A\2\2\u012a\7\3\2\2\2\u012b\u012d\7\33\2\2\u012c\u012e"+
		"\7(\2\2\u012d\u012c\3\2\2\2\u012d\u012e\3\2\2\2\u012e\u012f\3\2\2\2\u012f"+
		"\u0132\5l\67\2\u0130\u0131\7C\2\2\u0131\u0133\7U\2\2\u0132\u0130\3\2\2"+
		"\2\u0132\u0133\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0135\7A\2\2\u0135\t"+
		"\3\2\2\2\u0136\u0138\5\16\b\2\u0137\u0136\3\2\2\2\u0138\u013b\3\2\2\2"+
		"\u0139\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013c\3\2\2\2\u013b\u0139"+
		"\3\2\2\2\u013c\u0154\5\22\n\2\u013d\u013f\5\16\b\2\u013e\u013d\3\2\2\2"+
		"\u013f\u0142\3\2\2\2\u0140\u013e\3\2\2\2\u0140\u0141\3\2\2\2\u0141\u0143"+
		"\3\2\2\2\u0142\u0140\3\2\2\2\u0143\u0154\5\32\16\2\u0144\u0146\5\16\b"+
		"\2\u0145\u0144\3\2\2\2\u0146\u0149\3\2\2\2\u0147\u0145\3\2\2\2\u0147\u0148"+
		"\3\2\2\2\u0148\u014a\3\2\2\2\u0149\u0147\3\2\2\2\u014a\u0154\5\"\22\2"+
		"\u014b\u014d\5\16\b\2\u014c\u014b\3\2\2\2\u014d\u0150\3\2\2\2\u014e\u014c"+
		"\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0151\3\2\2\2\u0150\u014e\3\2\2\2\u0151"+
		"\u0154\5|?\2\u0152\u0154\7A\2\2\u0153\u0139\3\2\2\2\u0153\u0140\3\2\2"+
		"\2\u0153\u0147\3\2\2\2\u0153\u014e\3\2\2\2\u0153\u0152\3\2\2\2\u0154\13"+
		"\3\2\2\2\u0155\u0158\5\16\b\2\u0156\u0158\t\2\2\2\u0157\u0155\3\2\2\2"+
		"\u0157\u0156\3\2\2\2\u0158\r\3\2\2\2\u0159\u015c\5p9\2\u015a\u015c\t\3"+
		"\2\2\u015b\u0159\3\2\2\2\u015b\u015a\3\2\2\2\u015c\17\3\2\2\2\u015d\u0160"+
		"\7\24\2\2\u015e\u0160\5p9\2\u015f\u015d\3\2\2\2\u015f\u015e\3\2\2\2\u0160"+
		"\21\3\2\2\2\u0161\u0162\7\13\2\2\u0162\u0164\7f\2\2\u0163\u0165\5\24\13"+
		"\2\u0164\u0163\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0168\3\2\2\2\u0166\u0167"+
		"\7\23\2\2\u0167\u0169\5T+\2\u0168\u0166\3\2\2\2\u0168\u0169\3\2\2\2\u0169"+
		"\u016c\3\2\2\2\u016a\u016b\7\32\2\2\u016b\u016d\5$\23\2\u016c\u016a\3"+
		"\2\2\2\u016c\u016d\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\5&\24\2\u016f"+
		"\23\3\2\2\2\u0170\u0171\7F\2\2\u0171\u0176\5\26\f\2\u0172\u0173\7B\2\2"+
		"\u0173\u0175\5\26\f\2\u0174\u0172\3\2\2\2\u0175\u0178\3\2\2\2\u0176\u0174"+
		"\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0179\3\2\2\2\u0178\u0176\3\2\2\2\u0179"+
		"\u017a\7E\2\2\u017a\25\3\2\2\2\u017b\u017e\7f\2\2\u017c\u017d\7\23\2\2"+
		"\u017d\u017f\5\30\r\2\u017e\u017c\3\2\2\2\u017e\u017f\3\2\2\2\u017f\27"+
		"\3\2\2\2\u0180\u0185\5T+\2\u0181\u0182\7W\2\2\u0182\u0184\5T+\2\u0183"+
		"\u0181\3\2\2\2\u0184\u0187\3\2\2\2\u0185\u0183\3\2\2\2\u0185\u0186\3\2"+
		"\2\2\u0186\31\3\2\2\2\u0187\u0185\3\2\2\2\u0188\u0189\7\22\2\2\u0189\u018c"+
		"\7f\2\2\u018a\u018b\7\32\2\2\u018b\u018d\5$\23\2\u018c\u018a\3\2\2\2\u018c"+
		"\u018d\3\2\2\2\u018d\u018e\3\2\2\2\u018e\u0190\7=\2\2\u018f\u0191\5\34"+
		"\17\2\u0190\u018f\3\2\2\2\u0190\u0191\3\2\2\2\u0191\u0193\3\2\2\2\u0192"+
		"\u0194\7B\2\2\u0193\u0192\3\2\2\2\u0193\u0194\3\2\2\2\u0194\u0196\3\2"+
		"\2\2\u0195\u0197\5 \21\2\u0196\u0195\3\2\2\2\u0196\u0197\3\2\2\2\u0197"+
		"\u0198\3\2\2\2\u0198\u0199\7>\2\2\u0199\33\3\2\2\2\u019a\u019f\5\36\20"+
		"\2\u019b\u019c\7B\2\2\u019c\u019e\5\36\20\2\u019d\u019b\3\2\2\2\u019e"+
		"\u01a1\3\2\2\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\35\3\2\2"+
		"\2\u01a1\u019f\3\2\2\2\u01a2\u01a4\5p9\2\u01a3\u01a2\3\2\2\2\u01a4\u01a7"+
		"\3\2\2\2\u01a5\u01a3\3\2\2\2\u01a5\u01a6\3\2\2\2\u01a6\u01a8\3\2\2\2\u01a7"+
		"\u01a5\3\2\2\2\u01a8\u01aa\7f\2\2\u01a9\u01ab\5\u00f4{\2\u01aa\u01a9\3"+
		"\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01ad\3\2\2\2\u01ac\u01ae\5&\24\2\u01ad"+
		"\u01ac\3\2\2\2\u01ad\u01ae\3\2\2\2\u01ae\37\3\2\2\2\u01af\u01b3\7A\2\2"+
		"\u01b0\u01b2\5*\26\2\u01b1\u01b0\3\2\2\2\u01b2\u01b5\3\2\2\2\u01b3\u01b1"+
		"\3\2\2\2\u01b3\u01b4\3\2\2\2\u01b4!\3\2\2\2\u01b5\u01b3\3\2\2\2\u01b6"+
		"\u01b7\7\36\2\2\u01b7\u01b9\7f\2\2\u01b8\u01ba\5\24\13\2\u01b9\u01b8\3"+
		"\2\2\2\u01b9\u01ba\3\2\2\2\u01ba\u01bd\3\2\2\2\u01bb\u01bc\7\23\2\2\u01bc"+
		"\u01be\5$\23\2\u01bd\u01bb\3\2\2\2\u01bd\u01be\3\2\2\2\u01be\u01bf\3\2"+
		"\2\2\u01bf\u01c0\5(\25\2\u01c0#\3\2\2\2\u01c1\u01c6\5T+\2\u01c2\u01c3"+
		"\7B\2\2\u01c3\u01c5\5T+\2\u01c4\u01c2\3\2\2\2\u01c5\u01c8\3\2\2\2\u01c6"+
		"\u01c4\3\2\2\2\u01c6\u01c7\3\2\2\2\u01c7%\3\2\2\2\u01c8\u01c6\3\2\2\2"+
		"\u01c9\u01cd\7=\2\2\u01ca\u01cc\5*\26\2\u01cb\u01ca\3\2\2\2\u01cc\u01cf"+
		"\3\2\2\2\u01cd\u01cb\3\2\2\2\u01cd\u01ce\3\2\2\2\u01ce\u01d0\3\2\2\2\u01cf"+
		"\u01cd\3\2\2\2\u01d0\u01d1\7>\2\2\u01d1\'\3\2\2\2\u01d2\u01d6\7=\2\2\u01d3"+
		"\u01d5\5<\37\2\u01d4\u01d3\3\2\2\2\u01d5\u01d8\3\2\2\2\u01d6\u01d4\3\2"+
		"\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01d9\3\2\2\2\u01d8\u01d6\3\2\2\2\u01d9"+
		"\u01da\7>\2\2\u01da)\3\2\2\2\u01db\u01e6\7A\2\2\u01dc\u01de\7(\2\2\u01dd"+
		"\u01dc\3\2\2\2\u01dd\u01de\3\2\2\2\u01de\u01df\3\2\2\2\u01df\u01e0\7="+
		"\2\2\u01e0\u01e1\5\u008cG\2\u01e1\u01e2\7>\2\2\u01e2\u01e6\3\2\2\2\u01e3"+
		"\u01e6\5,\27\2\u01e4\u01e6\5\u00f6|\2\u01e5\u01db\3\2\2\2\u01e5\u01dd"+
		"\3\2\2\2\u01e5\u01e3\3\2\2\2\u01e5\u01e4\3\2\2\2\u01e6+\3\2\2\2\u01e7"+
		"\u01e9\5\f\7\2\u01e8\u01e7\3\2\2\2\u01e9\u01ec\3\2\2\2\u01ea\u01e8\3\2"+
		"\2\2\u01ea\u01eb\3\2\2\2\u01eb\u01ed\3\2\2\2\u01ec\u01ea\3\2\2\2\u01ed"+
		"\u0227\5.\30\2\u01ee\u01f0\5\f\7\2\u01ef\u01ee\3\2\2\2\u01f0\u01f3\3\2"+
		"\2\2\u01f1\u01ef\3\2\2\2\u01f1\u01f2\3\2\2\2\u01f2\u01f4\3\2\2\2\u01f3"+
		"\u01f1\3\2\2\2\u01f4\u0227\5\64\33\2\u01f5\u01f7\5\f\7\2\u01f6\u01f5\3"+
		"\2\2\2\u01f7\u01fa\3\2\2\2\u01f8\u01f6\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9"+
		"\u01fb\3\2\2\2\u01fa\u01f8\3\2\2\2\u01fb\u0227\5:\36\2\u01fc\u01fe\5\f"+
		"\7\2\u01fd\u01fc\3\2\2\2\u01fe\u0201\3\2\2\2\u01ff\u01fd\3\2\2\2\u01ff"+
		"\u0200\3\2\2\2\u0200\u0202\3\2\2\2\u0201\u01ff\3\2\2\2\u0202\u0227\5\66"+
		"\34\2\u0203\u0205\5\f\7\2\u0204\u0203\3\2\2\2\u0205\u0208\3\2\2\2\u0206"+
		"\u0204\3\2\2\2\u0206\u0207\3\2\2\2\u0207\u0209\3\2\2\2\u0208\u0206\3\2"+
		"\2\2\u0209\u0227\58\35\2\u020a\u020c\5\f\7\2\u020b\u020a\3\2\2\2\u020c"+
		"\u020f\3\2\2\2\u020d\u020b\3\2\2\2\u020d\u020e\3\2\2\2\u020e\u0210\3\2"+
		"\2\2\u020f\u020d\3\2\2\2\u0210\u0227\5\"\22\2\u0211\u0213\5\f\7\2\u0212"+
		"\u0211\3\2\2\2\u0213\u0216\3\2\2\2\u0214\u0212\3\2\2\2\u0214\u0215\3\2"+
		"\2\2\u0215\u0217\3\2\2\2\u0216\u0214\3\2\2\2\u0217\u0227\5|?\2\u0218\u021a"+
		"\5\f\7\2\u0219\u0218\3\2\2\2\u021a\u021d\3\2\2\2\u021b\u0219\3\2\2\2\u021b"+
		"\u021c\3\2\2\2\u021c\u021e\3\2\2\2\u021d\u021b\3\2\2\2\u021e\u0227\5\22"+
		"\n\2\u021f\u0221\5\f\7\2\u0220\u021f\3\2\2\2\u0221\u0224\3\2\2\2\u0222"+
		"\u0220\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0225\3\2\2\2\u0224\u0222\3\2"+
		"\2\2\u0225\u0227\5\32\16\2\u0226\u01ea\3\2\2\2\u0226\u01f1\3\2\2\2\u0226"+
		"\u01f8\3\2\2\2\u0226\u01ff\3\2\2\2\u0226\u0206\3\2\2\2\u0226\u020d\3\2"+
		"\2\2\u0226\u0214\3\2\2\2\u0226\u021b\3\2\2\2\u0226\u0222\3\2\2\2\u0227"+
		"-\3\2\2\2\u0228\u022b\5\60\31\2\u0229\u022c\5h\65\2\u022a\u022c\7A\2\2"+
		"\u022b\u0229\3\2\2\2\u022b\u022a\3\2\2\2\u022c/\3\2\2\2\u022d\u0230\5"+
		"T+\2\u022e\u0230\7\62\2\2\u022f\u022d\3\2\2\2\u022f\u022e\3\2\2\2\u0230"+
		"\u0231\3\2\2\2\u0231\u0232\7f\2\2\u0232\u0237\5`\61\2\u0233\u0234\7?\2"+
		"\2\u0234\u0236\7@\2\2\u0235\u0233\3\2\2\2\u0236\u0239\3\2\2\2\u0237\u0235"+
		"\3\2\2\2\u0237\u0238\3\2\2\2\u0238\u023b\3\2\2\2\u0239\u0237\3\2\2\2\u023a"+
		"\u023c\5\62\32\2\u023b\u023a\3\2\2\2\u023b\u023c\3\2\2\2\u023c\61\3\2"+
		"\2\2\u023d\u023e\7/\2\2\u023e\u023f\5^\60\2\u023f\63\3\2\2\2\u0240\u0241"+
		"\5\24\13\2\u0241\u0242\5.\30\2\u0242\65\3\2\2\2\u0243\u0244\7f\2\2\u0244"+
		"\u0247\5`\61\2\u0245\u0246\7/\2\2\u0246\u0248\5^\60\2\u0247\u0245\3\2"+
		"\2\2\u0247\u0248\3\2\2\2\u0248\u0249\3\2\2\2\u0249\u024a\5j\66\2\u024a"+
		"\67\3\2\2\2\u024b\u024c\5\24\13\2\u024c\u024d\5\66\34\2\u024d9\3\2\2\2"+
		"\u024e\u024f\5T+\2\u024f\u0250\5H%\2\u0250\u0251\7A\2\2\u0251;\3\2\2\2"+
		"\u0252\u0254\5\f\7\2\u0253\u0252\3\2\2\2\u0254\u0257\3\2\2\2\u0255\u0253"+
		"\3\2\2\2\u0255\u0256\3\2\2\2\u0256\u0258\3\2\2\2\u0257\u0255\3\2\2\2\u0258"+
		"\u025b\5> \2\u0259\u025b\7A\2\2\u025a\u0255\3\2\2\2\u025a\u0259\3\2\2"+
		"\2\u025b=\3\2\2\2\u025c\u0264\5@!\2\u025d\u0264\5D#\2\u025e\u0264\5F$"+
		"\2\u025f\u0264\5\"\22\2\u0260\u0264\5|?\2\u0261\u0264\5\22\n\2\u0262\u0264"+
		"\5\32\16\2\u0263\u025c\3\2\2\2\u0263\u025d\3\2\2\2\u0263\u025e\3\2\2\2"+
		"\u0263\u025f\3\2\2\2\u0263\u0260\3\2\2\2\u0263\u0261\3\2\2\2\u0263\u0262"+
		"\3\2\2\2\u0264?\3\2\2\2\u0265\u0266\5T+\2\u0266\u026b\5B\"\2\u0267\u0268"+
		"\7B\2\2\u0268\u026a\5B\"\2\u0269\u0267\3\2\2\2\u026a\u026d\3\2\2\2\u026b"+
		"\u0269\3\2\2\2\u026b\u026c\3\2\2\2\u026c\u026e\3\2\2\2\u026d\u026b\3\2"+
		"\2\2\u026e\u026f\7A\2\2\u026fA\3\2\2\2\u0270\u0275\7f\2\2\u0271\u0272"+
		"\7?\2\2\u0272\u0274\7@\2\2\u0273\u0271\3\2\2\2\u0274\u0277\3\2\2\2\u0275"+
		"\u0273\3\2\2\2\u0275\u0276\3\2\2\2\u0276\u0278\3\2\2\2\u0277\u0275\3\2"+
		"\2\2\u0278\u0279\7D\2\2\u0279\u027a\5N(\2\u027aC\3\2\2\2\u027b\u027e\5"+
		"T+\2\u027c\u027e\7\62\2\2\u027d\u027b\3\2\2\2\u027d\u027c\3\2\2\2\u027e"+
		"\u027f\3\2\2\2\u027f\u0280\7f\2\2\u0280\u0285\5`\61\2\u0281\u0282\7?\2"+
		"\2\u0282\u0284\7@\2\2\u0283\u0281\3\2\2\2\u0284\u0287\3\2\2\2\u0285\u0283"+
		"\3\2\2\2\u0285\u0286\3\2\2\2\u0286\u028a\3\2\2\2\u0287\u0285\3\2\2\2\u0288"+
		"\u0289\7/\2\2\u0289\u028b\5^\60\2\u028a\u0288\3\2\2\2\u028a\u028b\3\2"+
		"\2\2\u028b\u028c\3\2\2\2\u028c\u028d\7A\2\2\u028dE\3\2\2\2\u028e\u028f"+
		"\5\24\13\2\u028f\u0290\5D#\2\u0290G\3\2\2\2\u0291\u0296\5J&\2\u0292\u0293"+
		"\7B\2\2\u0293\u0295\5J&\2\u0294\u0292\3\2\2\2\u0295\u0298\3\2\2\2\u0296"+
		"\u0294\3\2\2\2\u0296\u0297\3\2\2\2\u0297I\3\2\2\2\u0298\u0296\3\2\2\2"+
		"\u0299\u029c\5L\'\2\u029a\u029b\7D\2\2\u029b\u029d\5N(\2\u029c\u029a\3"+
		"\2\2\2\u029c\u029d\3\2\2\2\u029dK\3\2\2\2\u029e\u02a3\7f\2\2\u029f\u02a0"+
		"\7?\2\2\u02a0\u02a2\7@\2\2\u02a1\u029f\3\2\2\2\u02a2\u02a5\3\2\2\2\u02a3"+
		"\u02a1\3\2\2\2\u02a3\u02a4\3\2\2\2\u02a4M\3\2\2\2\u02a5\u02a3\3\2\2\2"+
		"\u02a6\u02a9\5P)\2\u02a7\u02a9\5\u00d8m\2\u02a8\u02a6\3\2\2\2\u02a8\u02a7"+
		"\3\2\2\2\u02a9O\3\2\2\2\u02aa\u02b6\7=\2\2\u02ab\u02b0\5N(\2\u02ac\u02ad"+
		"\7B\2\2\u02ad\u02af\5N(\2\u02ae\u02ac\3\2\2\2\u02af\u02b2\3\2\2\2\u02b0"+
		"\u02ae\3\2\2\2\u02b0\u02b1\3\2\2\2\u02b1\u02b4\3\2\2\2\u02b2\u02b0\3\2"+
		"\2\2\u02b3\u02b5\7B\2\2\u02b4\u02b3\3\2\2\2\u02b4\u02b5\3\2\2\2\u02b5"+
		"\u02b7\3\2\2\2\u02b6\u02ab\3\2\2\2\u02b6\u02b7\3\2\2\2\u02b7\u02b8\3\2"+
		"\2\2\u02b8\u02b9\7>\2\2\u02b9Q\3\2\2\2\u02ba\u02bb\7f\2\2\u02bbS\3\2\2"+
		"\2\u02bc\u02c1\5V,\2\u02bd\u02be\7?\2\2\u02be\u02c0\7@\2\2\u02bf\u02bd"+
		"\3\2\2\2\u02c0\u02c3\3\2\2\2\u02c1\u02bf\3\2\2\2\u02c1\u02c2\3\2\2\2\u02c2"+
		"\u02cd\3\2\2\2\u02c3\u02c1\3\2\2\2\u02c4\u02c9\5X-\2\u02c5\u02c6\7?\2"+
		"\2\u02c6\u02c8\7@\2\2\u02c7\u02c5\3\2\2\2\u02c8\u02cb\3\2\2\2\u02c9\u02c7"+
		"\3\2\2\2\u02c9\u02ca\3\2\2\2\u02ca\u02cd\3\2\2\2\u02cb\u02c9\3\2\2\2\u02cc"+
		"\u02bc\3\2\2\2\u02cc\u02c4\3\2\2\2\u02cdU\3\2\2\2\u02ce\u02d0\7f\2\2\u02cf"+
		"\u02d1\5Z.\2\u02d0\u02cf\3\2\2\2\u02d0\u02d1\3\2\2\2\u02d1\u02d9\3\2\2"+
		"\2\u02d2\u02d3\7C\2\2\u02d3\u02d5\7f\2\2\u02d4\u02d6\5Z.\2\u02d5\u02d4"+
		"\3\2\2\2\u02d5\u02d6\3\2\2\2\u02d6\u02d8\3\2\2\2\u02d7\u02d2\3\2\2\2\u02d8"+
		"\u02db\3\2\2\2\u02d9\u02d7\3\2\2\2\u02d9\u02da\3\2\2\2\u02daW\3\2\2\2"+
		"\u02db\u02d9\3\2\2\2\u02dc\u02dd\t\4\2\2\u02ddY\3\2\2\2\u02de\u02df\7"+
		"F\2\2\u02df\u02e4\5\\/\2\u02e0\u02e1\7B\2\2\u02e1\u02e3\5\\/\2\u02e2\u02e0"+
		"\3\2\2\2\u02e3\u02e6\3\2\2\2\u02e4\u02e2\3\2\2\2\u02e4\u02e5\3\2\2\2\u02e5"+
		"\u02e7\3\2\2\2\u02e6\u02e4\3\2\2\2\u02e7\u02e8\7E\2\2\u02e8[\3\2\2\2\u02e9"+
		"\u02f0\5T+\2\u02ea\u02ed\7I\2\2\u02eb\u02ec\t\5\2\2\u02ec\u02ee\5T+\2"+
		"\u02ed\u02eb\3\2\2\2\u02ed\u02ee\3\2\2\2\u02ee\u02f0\3\2\2\2\u02ef\u02e9"+
		"\3\2\2\2\u02ef\u02ea\3\2\2\2\u02f0]\3\2\2\2\u02f1\u02f6\5l\67\2\u02f2"+
		"\u02f3\7B\2\2\u02f3\u02f5\5l\67\2\u02f4\u02f2\3\2\2\2\u02f5\u02f8\3\2"+
		"\2\2\u02f6\u02f4\3\2\2\2\u02f6\u02f7\3\2\2\2\u02f7_\3\2\2\2\u02f8\u02f6"+
		"\3\2\2\2\u02f9\u02fb\7;\2\2\u02fa\u02fc\5b\62\2\u02fb\u02fa\3\2\2\2\u02fb"+
		"\u02fc\3\2\2\2\u02fc\u02fd\3\2\2\2\u02fd\u02fe\7<\2\2\u02fea\3\2\2\2\u02ff"+
		"\u0304\5d\63\2\u0300\u0301\7B\2\2\u0301\u0303\5d\63\2\u0302\u0300\3\2"+
		"\2\2\u0303\u0306\3\2\2\2\u0304\u0302\3\2\2\2\u0304\u0305\3\2\2\2\u0305"+
		"\u0309\3\2\2\2\u0306\u0304\3\2\2\2\u0307\u0308\7B\2\2\u0308\u030a\5f\64"+
		"\2\u0309\u0307\3\2\2\2\u0309\u030a\3\2\2\2\u030a\u030d\3\2\2\2\u030b\u030d"+
		"\5f\64\2\u030c\u02ff\3\2\2\2\u030c\u030b\3\2\2\2\u030dc\3\2\2\2\u030e"+
		"\u0310\5\20\t\2\u030f\u030e\3\2\2\2\u0310\u0313\3\2\2\2\u0311\u030f\3"+
		"\2\2\2\u0311\u0312\3\2\2\2\u0312\u0314\3\2\2\2\u0313\u0311\3\2\2\2\u0314"+
		"\u0315\5T+\2\u0315\u0316\5L\'\2\u0316e\3\2\2\2\u0317\u0319\5\20\t\2\u0318"+
		"\u0317\3\2\2\2\u0319\u031c\3\2\2\2\u031a\u0318\3\2\2\2\u031a\u031b\3\2"+
		"\2\2\u031b\u031d\3\2\2\2\u031c\u031a\3\2\2\2\u031d\u031e\5T+\2\u031e\u031f"+
		"\7i\2\2\u031f\u0320\5L\'\2\u0320g\3\2\2\2\u0321\u0322\7=\2\2\u0322\u0323"+
		"\5\u008cG\2\u0323\u0324\7>\2\2\u0324i\3\2\2\2\u0325\u0326\7=\2\2\u0326"+
		"\u0327\5\u008cG\2\u0327\u0328\7>\2\2\u0328k\3\2\2\2\u0329\u032e\7f\2\2"+
		"\u032a\u032b\7C\2\2\u032b\u032d\7f\2\2\u032c\u032a\3\2\2\2\u032d\u0330"+
		"\3\2\2\2\u032e\u032c\3\2\2\2\u032e\u032f\3\2\2\2\u032fm\3\2\2\2\u0330"+
		"\u032e\3\2\2\2\u0331\u0338\7\65\2\2\u0332\u0338\7\66\2\2\u0333\u0338\7"+
		"8\2\2\u0334\u0338\79\2\2\u0335\u0338\7\67\2\2\u0336\u0338\7:\2\2\u0337"+
		"\u0331\3\2\2\2\u0337\u0332\3\2\2\2\u0337\u0333\3\2\2\2\u0337\u0334\3\2"+
		"\2\2\u0337\u0335\3\2\2\2\u0337\u0336\3\2\2\2\u0338o\3\2\2\2\u0339\u033a"+
		"\7g\2\2\u033a\u0341\5r:\2\u033b\u033e\7;\2\2\u033c\u033f\5t;\2\u033d\u033f"+
		"\5x=\2\u033e\u033c\3\2\2\2\u033e\u033d\3\2\2\2\u033e\u033f\3\2\2\2\u033f"+
		"\u0340\3\2\2\2\u0340\u0342\7<\2\2\u0341\u033b\3\2\2\2\u0341\u0342\3\2"+
		"\2\2\u0342q\3\2\2\2\u0343\u0344\5l\67\2\u0344s\3\2\2\2\u0345\u034a\5v"+
		"<\2\u0346\u0347\7B\2\2\u0347\u0349\5v<\2\u0348\u0346\3\2\2\2\u0349\u034c"+
		"\3\2\2\2\u034a\u0348\3\2\2\2\u034a\u034b\3\2\2\2\u034bu\3\2\2\2\u034c"+
		"\u034a\3\2\2\2\u034d\u034e\7f\2\2\u034e\u034f\7D\2\2\u034f\u0350\5x=\2"+
		"\u0350w\3\2\2\2\u0351\u0355\5\u00d8m\2\u0352\u0355\5p9\2\u0353\u0355\5"+
		"z>\2\u0354\u0351\3\2\2\2\u0354\u0352\3\2\2\2\u0354\u0353\3\2\2\2\u0355"+
		"y\3\2\2\2\u0356\u035f\7=\2\2\u0357\u035c\5x=\2\u0358\u0359\7B\2\2\u0359"+
		"\u035b\5x=\2\u035a\u0358\3\2\2\2\u035b\u035e\3\2\2\2\u035c\u035a\3\2\2"+
		"\2\u035c\u035d\3\2\2\2\u035d\u0360\3\2\2\2\u035e\u035c\3\2\2\2\u035f\u0357"+
		"\3\2\2\2\u035f\u0360\3\2\2\2\u0360\u0362\3\2\2\2\u0361\u0363\7B\2\2\u0362"+
		"\u0361\3\2\2\2\u0362\u0363\3\2\2\2\u0363\u0364\3\2\2\2\u0364\u0365\7>"+
		"\2\2\u0365{\3\2\2\2\u0366\u0367\7g\2\2\u0367\u0368\7\36\2\2\u0368\u0369"+
		"\7f\2\2\u0369\u036a\5~@\2\u036a}\3\2\2\2\u036b\u036f\7=\2\2\u036c\u036e"+
		"\5\u0080A\2\u036d\u036c\3\2\2\2\u036e\u0371\3\2\2\2\u036f\u036d\3\2\2"+
		"\2\u036f\u0370\3\2\2\2\u0370\u0372\3\2\2\2\u0371\u036f\3\2\2\2\u0372\u0373"+
		"\7>\2\2\u0373\177\3\2\2\2\u0374\u0376\5\f\7\2\u0375\u0374\3\2\2\2\u0376"+
		"\u0379\3\2\2\2\u0377\u0375\3\2\2\2\u0377\u0378\3\2\2\2\u0378\u037a\3\2"+
		"\2\2\u0379\u0377\3\2\2\2\u037a\u037d\5\u0082B\2\u037b\u037d\7A\2\2\u037c"+
		"\u0377\3\2\2\2\u037c\u037b\3\2\2\2\u037d\u0081\3\2\2\2\u037e\u037f\5T"+
		"+\2\u037f\u0380\5\u0084C\2\u0380\u0381\7A\2\2\u0381\u0393\3\2\2\2\u0382"+
		"\u0384\5\22\n\2\u0383\u0385\7A\2\2\u0384\u0383\3\2\2\2\u0384\u0385\3\2"+
		"\2\2\u0385\u0393\3\2\2\2\u0386\u0388\5\"\22\2\u0387\u0389\7A\2\2\u0388"+
		"\u0387\3\2\2\2\u0388\u0389\3\2\2\2\u0389\u0393\3\2\2\2\u038a\u038c\5\32"+
		"\16\2\u038b\u038d\7A\2\2\u038c\u038b\3\2\2\2\u038c\u038d\3\2\2\2\u038d"+
		"\u0393\3\2\2\2\u038e\u0390\5|?\2\u038f\u0391\7A\2\2\u0390\u038f\3\2\2"+
		"\2\u0390\u0391\3\2\2\2\u0391\u0393\3\2\2\2\u0392\u037e\3\2\2\2\u0392\u0382"+
		"\3\2\2\2\u0392\u0386\3\2\2\2\u0392\u038a\3\2\2\2\u0392\u038e\3\2\2\2\u0393"+
		"\u0083\3\2\2\2\u0394\u0397\5\u0086D\2\u0395\u0397\5\u0088E\2\u0396\u0394"+
		"\3\2\2\2\u0396\u0395\3\2\2\2\u0397\u0085\3\2\2\2\u0398\u0399\7f\2\2\u0399"+
		"\u039a\7;\2\2\u039a\u039c\7<\2\2\u039b\u039d\5\u008aF\2\u039c\u039b\3"+
		"\2\2\2\u039c\u039d\3\2\2\2\u039d\u0087\3\2\2\2\u039e\u039f\5H%\2\u039f"+
		"\u0089\3\2\2\2\u03a0\u03a1\7\16\2\2\u03a1\u03a2\5x=\2\u03a2\u008b\3\2"+
		"\2\2\u03a3\u03a5\5\u008eH\2\u03a4\u03a3\3\2\2\2\u03a5\u03a8\3\2\2\2\u03a6"+
		"\u03a4\3\2\2\2\u03a6\u03a7\3\2\2\2\u03a7\u008d\3\2\2\2\u03a8\u03a6\3\2"+
		"\2\2\u03a9\u03ae\5\u0090I\2\u03aa\u03ae\5\u0094K\2\u03ab\u03ae\5\n\6\2"+
		"\u03ac\u03ae\5\u00f6|\2\u03ad\u03a9\3\2\2\2\u03ad\u03aa\3\2\2\2\u03ad"+
		"\u03ab\3\2\2\2\u03ad\u03ac\3\2\2\2\u03ae\u008f\3\2\2\2\u03af\u03b0\5\u0092"+
		"J\2\u03b0\u03b1\7A\2\2\u03b1\u0091\3\2\2\2\u03b2\u03b4\5\20\t\2\u03b3"+
		"\u03b2\3\2\2\2\u03b4\u03b7\3\2\2\2\u03b5\u03b3\3\2\2\2\u03b5\u03b6\3\2"+
		"\2\2\u03b6\u03b8\3\2\2\2\u03b7\u03b5\3\2\2\2\u03b8\u03b9\5T+\2\u03b9\u03ba"+
		"\5H%\2\u03ba\u0093\3\2\2\2\u03bb\u03cf\5\u0096L\2\u03bc\u03cf\5\u0098"+
		"M\2\u03bd\u03cf\5\u009aN\2\u03be\u03cf\5\u00a0Q\2\u03bf\u03cf\5\u00a2"+
		"R\2\u03c0\u03cf\5\u00a4S\2\u03c1\u03cf\5\u00a6T\2\u03c2\u03cf\5\u00a8"+
		"U\2\u03c3\u03cf\5\u00aaV\2\u03c4\u03cf\5\u00acW\2\u03c5\u03cf\5\u00ae"+
		"X\2\u03c6\u03cf\5\u00b0Y\2\u03c7\u03cf\5\u00b2Z\2\u03c8\u03cf\5\u00b4"+
		"[\2\u03c9\u03cf\7A\2\2\u03ca\u03cb\5\u00d4k\2\u03cb\u03cc\7A\2\2\u03cc"+
		"\u03cf\3\2\2\2\u03cd\u03cf\5\u00b6\\\2\u03ce\u03bb\3\2\2\2\u03ce\u03bc"+
		"\3\2\2\2\u03ce\u03bd\3\2\2\2\u03ce\u03be\3\2\2\2\u03ce\u03bf\3\2\2\2\u03ce"+
		"\u03c0\3\2\2\2\u03ce\u03c1\3\2\2\2\u03ce\u03c2\3\2\2\2\u03ce\u03c3\3\2"+
		"\2\2\u03ce\u03c4\3\2\2\2\u03ce\u03c5\3\2\2\2\u03ce\u03c6\3\2\2\2\u03ce"+
		"\u03c7\3\2\2\2\u03ce\u03c8\3\2\2\2\u03ce\u03c9\3\2\2\2\u03ce\u03ca\3\2"+
		"\2\2\u03ce\u03cd\3\2\2\2\u03cf\u0095\3\2\2\2\u03d0\u03d1\7=\2\2\u03d1"+
		"\u03d2\5\u008cG\2\u03d2\u03d3\7>\2\2\u03d3\u0097\3\2\2\2\u03d4\u03d5\7"+
		"\4\2\2\u03d5\u03d8\5\u00d8m\2\u03d6\u03d7\7J\2\2\u03d7\u03d9\5\u00d8m"+
		"\2\u03d8\u03d6\3\2\2\2\u03d8\u03d9\3\2\2\2\u03d9\u03da\3\2\2\2\u03da\u03db"+
		"\7A\2\2\u03db\u0099\3\2\2\2\u03dc\u03dd\7\30\2\2\u03dd\u03df\5\u00d0i"+
		"\2\u03de\u03e0\5\u009cO\2\u03df\u03de\3\2\2\2\u03df\u03e0\3\2\2\2\u03e0"+
		"\u03e3\3\2\2\2\u03e1\u03e2\7\21\2\2\u03e2\u03e4\5\u009eP\2\u03e3\u03e1"+
		"\3\2\2\2\u03e3\u03e4\3\2\2\2\u03e4\u009b\3\2\2\2\u03e5\u03e6\5\u0094K"+
		"\2\u03e6\u009d\3\2\2\2\u03e7\u03e8\5\u0094K\2\u03e8\u009f\3\2\2\2\u03e9"+
		"\u03ea\7\27\2\2\u03ea\u03eb\7;\2\2\u03eb\u03ec\5\u00c8e\2\u03ec\u03ed"+
		"\7<\2\2\u03ed\u03ee\5\u0094K\2\u03ee\u00a1\3\2\2\2\u03ef\u03f0\7\64\2"+
		"\2\u03f0\u03f1\5\u00d0i\2\u03f1\u03f2\5\u0094K\2\u03f2\u00a3\3\2\2\2\u03f3"+
		"\u03f4\7\17\2\2\u03f4\u03f5\5\u0094K\2\u03f5\u03f6\7\64\2\2\u03f6\u03f7"+
		"\5\u00d0i\2\u03f7\u03f8\7A\2\2\u03f8\u00a5\3\2\2\2\u03f9\u03fa\7\61\2"+
		"\2\u03fa\u03fb\7=\2\2\u03fb\u03fc\5\u008cG\2\u03fc\u0406\7>\2\2\u03fd"+
		"\u03ff\5\u00b8]\2\u03fe\u03fd\3\2\2\2\u03ff\u0400\3\2\2\2\u0400\u03fe"+
		"\3\2\2\2\u0400\u0401\3\2\2\2\u0401\u0403\3\2\2\2\u0402\u0404\5\u00bc_"+
		"\2\u0403\u0402\3\2\2\2\u0403\u0404\3\2\2\2\u0404\u0407\3\2\2\2\u0405\u0407"+
		"\5\u00bc_\2\u0406\u03fe\3\2\2\2\u0406\u0405\3\2\2\2\u0407\u00a7\3\2\2"+
		"\2\u0408\u0409\7\61\2\2\u0409\u040a\5\u00be`\2\u040a\u040b\7=\2\2\u040b"+
		"\u040c\5\u008cG\2\u040c\u0410\7>\2\2\u040d\u040f\5\u00b8]\2\u040e\u040d"+
		"\3\2\2\2\u040f\u0412\3\2\2\2\u0410\u040e\3\2\2\2\u0410\u0411\3\2\2\2\u0411"+
		"\u0414\3\2\2\2\u0412\u0410\3\2\2\2\u0413\u0415\5\u00bc_\2\u0414\u0413"+
		"\3\2\2\2\u0414\u0415\3\2\2\2\u0415\u00a9\3\2\2\2\u0416\u0417\7+\2\2\u0417"+
		"\u0418\5\u00d0i\2\u0418\u041c\7=\2\2\u0419\u041b\5\u00c4c\2\u041a\u0419"+
		"\3\2\2\2\u041b\u041e\3\2\2\2\u041c\u041a\3\2\2\2\u041c\u041d\3\2\2\2\u041d"+
		"\u0422\3\2\2\2\u041e\u041c\3\2\2\2\u041f\u0421\5\u00c6d\2\u0420\u041f"+
		"\3\2\2\2\u0421\u0424\3\2\2\2\u0422\u0420\3\2\2\2\u0422\u0423\3\2\2\2\u0423"+
		"\u0425\3\2\2\2\u0424\u0422\3\2\2\2\u0425\u0426\7>\2\2\u0426\u00ab\3\2"+
		"\2\2\u0427\u0428\7,\2\2\u0428\u0429\5\u00d0i\2\u0429\u042a\7=\2\2\u042a"+
		"\u042b\5\u008cG\2\u042b\u042c\7>\2\2\u042c\u00ad\3\2\2\2\u042d\u042f\7"+
		"&\2\2\u042e\u0430\5\u00d8m\2\u042f\u042e\3\2\2\2\u042f\u0430\3\2\2\2\u0430"+
		"\u0431\3\2\2\2\u0431\u0432\7A\2\2\u0432\u00af\3\2\2\2\u0433\u0434\7.\2"+
		"\2\u0434\u0435\5\u00d8m\2\u0435\u0436\7A\2\2\u0436\u00b1\3\2\2\2\u0437"+
		"\u0439\7\6\2\2\u0438\u043a\7f\2\2\u0439\u0438\3\2\2\2\u0439\u043a\3\2"+
		"\2\2\u043a\u043b\3\2\2\2\u043b\u043c\7A\2\2\u043c\u00b3\3\2\2\2\u043d"+
		"\u043f\7\r\2\2\u043e\u0440\7f\2\2\u043f\u043e\3\2\2\2\u043f\u0440\3\2"+
		"\2\2\u0440\u0441\3\2\2\2\u0441\u0442\7A\2\2\u0442\u00b5\3\2\2\2\u0443"+
		"\u0444\7f\2\2\u0444\u0445\7J\2\2\u0445\u0446\5\u0094K\2\u0446\u00b7\3"+
		"\2\2\2\u0447\u0448\7\t\2\2\u0448\u044c\7;\2\2\u0449\u044b\5\20\t\2\u044a"+
		"\u0449\3\2\2\2\u044b\u044e\3\2\2\2\u044c\u044a\3\2\2\2\u044c\u044d\3\2"+
		"\2\2\u044d\u044f\3\2\2\2\u044e\u044c\3\2\2\2\u044f\u0450\5\u00ba^\2\u0450"+
		"\u0451\7f\2\2\u0451\u0452\7<\2\2\u0452\u0453\7=\2\2\u0453\u0454\5\u008c"+
		"G\2\u0454\u0455\7>\2\2\u0455\u00b9\3\2\2\2\u0456\u045b\5l\67\2\u0457\u0458"+
		"\7X\2\2\u0458\u045a\5l\67\2\u0459\u0457\3\2\2\2\u045a\u045d\3\2\2\2\u045b"+
		"\u0459\3\2\2\2\u045b\u045c\3\2\2\2\u045c\u00bb\3\2\2\2\u045d\u045b\3\2"+
		"\2\2\u045e\u045f\7\25\2\2\u045f\u0460\7=\2\2\u0460\u0461\5\u008cG\2\u0461"+
		"\u0462\7>\2\2\u0462\u00bd\3\2\2\2\u0463\u0464\7;\2\2\u0464\u0466\5\u00c0"+
		"a\2\u0465\u0467\7A\2\2\u0466\u0465\3\2\2\2\u0466\u0467\3\2\2\2\u0467\u0468"+
		"\3\2\2\2\u0468\u0469\7<\2\2\u0469\u00bf\3\2\2\2\u046a\u046f\5\u00c2b\2"+
		"\u046b\u046c\7A\2\2\u046c\u046e\5\u00c2b\2\u046d\u046b\3\2\2\2\u046e\u0471"+
		"\3\2\2\2\u046f\u046d\3\2\2\2\u046f\u0470\3\2\2\2\u0470\u00c1\3\2\2\2\u0471"+
		"\u046f\3\2\2\2\u0472\u0474\5\20\t\2\u0473\u0472\3\2\2\2\u0474\u0477\3"+
		"\2\2\2\u0475\u0473\3\2\2\2\u0475\u0476\3\2\2\2\u0476\u0478\3\2\2\2\u0477"+
		"\u0475\3\2\2\2\u0478\u0479\5V,\2\u0479\u047a\5L\'\2\u047a\u047b\7D\2\2"+
		"\u047b\u047c\5\u00d8m\2\u047c\u00c3\3\2\2\2\u047d\u047f\5\u00c6d\2\u047e"+
		"\u047d\3\2\2\2\u047f\u0480\3\2\2\2\u0480\u047e\3\2\2\2\u0480\u0481\3\2"+
		"\2\2\u0481\u0483\3\2\2\2\u0482\u0484\5\u008eH\2\u0483\u0482\3\2\2\2\u0484"+
		"\u0485\3\2\2\2\u0485\u0483\3\2\2\2\u0485\u0486\3\2\2\2\u0486\u00c5\3\2"+
		"\2\2\u0487\u0488\7\b\2\2\u0488\u0489\5\u00d6l\2\u0489\u048a\7J\2\2\u048a"+
		"\u0492\3\2\2\2\u048b\u048c\7\b\2\2\u048c\u048d\5R*\2\u048d\u048e\7J\2"+
		"\2\u048e\u0492\3\2\2\2\u048f\u0490\7\16\2\2\u0490\u0492\7J\2\2\u0491\u0487"+
		"\3\2\2\2\u0491\u048b\3\2\2\2\u0491\u048f\3\2\2\2\u0492\u00c7\3\2\2\2\u0493"+
		"\u04a0\5\u00ccg\2\u0494\u0496\5\u00caf\2\u0495\u0494\3\2\2\2\u0495\u0496"+
		"\3\2\2\2\u0496\u0497\3\2\2\2\u0497\u0499\7A\2\2\u0498\u049a\5\u00d8m\2"+
		"\u0499\u0498\3\2\2\2\u0499\u049a\3\2\2\2\u049a\u049b\3\2\2\2\u049b\u049d"+
		"\7A\2\2\u049c\u049e\5\u00ceh\2\u049d\u049c\3\2\2\2\u049d\u049e\3\2\2\2"+
		"\u049e\u04a0\3\2\2\2\u049f\u0493\3\2\2\2\u049f\u0495\3\2\2\2\u04a0\u00c9"+
		"\3\2\2\2\u04a1\u04a4\5\u0092J\2\u04a2\u04a4\5\u00d2j\2\u04a3\u04a1\3\2"+
		"\2\2\u04a3\u04a2\3\2\2\2\u04a4\u00cb\3\2\2\2\u04a5\u04a7\5\20\t\2\u04a6"+
		"\u04a5\3\2\2\2\u04a7\u04aa\3\2\2\2\u04a8\u04a6\3\2\2\2\u04a8\u04a9\3\2"+
		"\2\2\u04a9\u04ab\3\2\2\2\u04aa\u04a8\3\2\2\2\u04ab\u04ac\5T+\2\u04ac\u04ad"+
		"\7f\2\2\u04ad\u04ae\7J\2\2\u04ae\u04af\5\u00d8m\2\u04af\u00cd\3\2\2\2"+
		"\u04b0\u04b1\5\u00d2j\2\u04b1\u00cf\3\2\2\2\u04b2\u04b3\7;\2\2\u04b3\u04b4"+
		"\5\u00d8m\2\u04b4\u04b5\7<\2\2\u04b5\u00d1\3\2\2\2\u04b6\u04bb\5\u00d8"+
		"m\2\u04b7\u04b8\7B\2\2\u04b8\u04ba\5\u00d8m\2\u04b9\u04b7\3\2\2\2\u04ba"+
		"\u04bd\3\2\2\2\u04bb\u04b9\3\2\2\2\u04bb\u04bc\3\2\2\2\u04bc\u00d3\3\2"+
		"\2\2\u04bd\u04bb\3\2\2\2\u04be\u04bf\5\u00d8m\2\u04bf\u00d5\3\2\2\2\u04c0"+
		"\u04c1\5\u00d8m\2\u04c1\u00d7\3\2\2\2\u04c2\u04c3\bm\1\2\u04c3\u04c4\7"+
		";\2\2\u04c4\u04c5\5T+\2\u04c5\u04c6\7<\2\2\u04c6\u04c7\5\u00d8m\23\u04c7"+
		"\u04d0\3\2\2\2\u04c8\u04c9\t\6\2\2\u04c9\u04d0\5\u00d8m\21\u04ca\u04cb"+
		"\t\7\2\2\u04cb\u04d0\5\u00d8m\20\u04cc\u04d0\5\u00dan\2\u04cd\u04ce\7"+
		"!\2\2\u04ce\u04d0\5\u00dep\2\u04cf\u04c2\3\2\2\2\u04cf\u04c8\3\2\2\2\u04cf"+
		"\u04ca\3\2\2\2\u04cf\u04cc\3\2\2\2\u04cf\u04cd\3\2\2\2\u04d0\u0526\3\2"+
		"\2\2\u04d1\u04d2\f\17\2\2\u04d2\u04d3\t\b\2\2\u04d3\u0525\5\u00d8m\20"+
		"\u04d4\u04d5\f\16\2\2\u04d5\u04d6\t\t\2\2\u04d6\u0525\5\u00d8m\17\u04d7"+
		"\u04df\f\r\2\2\u04d8\u04d9\7F\2\2\u04d9\u04e0\7F\2\2\u04da\u04db\7E\2"+
		"\2\u04db\u04dc\7E\2\2\u04dc\u04e0\7E\2\2\u04dd\u04de\7E\2\2\u04de\u04e0"+
		"\7E\2\2\u04df\u04d8\3\2\2\2\u04df\u04da\3\2\2\2\u04df\u04dd\3\2\2\2\u04e0"+
		"\u04e1\3\2\2\2\u04e1\u0525\5\u00d8m\16\u04e2\u04e3\f\f\2\2\u04e3\u04e4"+
		"\t\n\2\2\u04e4\u0525\5\u00d8m\r\u04e5\u04e6\f\n\2\2\u04e6\u04e7\t\13\2"+
		"\2\u04e7\u0525\5\u00d8m\13\u04e8\u04e9\f\t\2\2\u04e9\u04ea\7W\2\2\u04ea"+
		"\u0525\5\u00d8m\n\u04eb\u04ec\f\b\2\2\u04ec\u04ed\7Y\2\2\u04ed\u0525\5"+
		"\u00d8m\t\u04ee\u04ef\f\7\2\2\u04ef\u04f0\7X\2\2\u04f0\u0525\5\u00d8m"+
		"\b\u04f1\u04f2\f\6\2\2\u04f2\u04f3\7O\2\2\u04f3\u0525\5\u00d8m\7\u04f4"+
		"\u04f5\f\5\2\2\u04f5\u04f6\7P\2\2\u04f6\u0525\5\u00d8m\6\u04f7\u04f8\f"+
		"\4\2\2\u04f8\u04f9\7I\2\2\u04f9\u04fa\5\u00d8m\2\u04fa\u04fb\7J\2\2\u04fb"+
		"\u04fc\5\u00d8m\5\u04fc\u0525\3\2\2\2\u04fd\u04fe\f\3\2\2\u04fe\u04ff"+
		"\t\f\2\2\u04ff\u0525\5\u00d8m\4\u0500\u0501\f\33\2\2\u0501\u0502\7C\2"+
		"\2\u0502\u0525\7f\2\2\u0503\u0504\f\32\2\2\u0504\u0505\7C\2\2\u0505\u0525"+
		"\7-\2\2\u0506\u0507\f\31\2\2\u0507\u0508\7C\2\2\u0508\u050a\7!\2\2\u0509"+
		"\u050b\5\u00eav\2\u050a\u0509\3\2\2\2\u050a\u050b\3\2\2\2\u050b\u050c"+
		"\3\2\2\2\u050c\u0525\5\u00e2r\2\u050d\u050e\f\30\2\2\u050e\u050f\7C\2"+
		"\2\u050f\u0510\7*\2\2\u0510\u0525\5\u00f0y\2\u0511\u0512\f\27\2\2\u0512"+
		"\u0513\7C\2\2\u0513\u0525\5\u00e8u\2\u0514\u0515\f\26\2\2\u0515\u0516"+
		"\7?\2\2\u0516\u0517\5\u00d8m\2\u0517\u0518\7@\2\2\u0518\u0525\3\2\2\2"+
		"\u0519\u051a\f\25\2\2\u051a\u051c\7;\2\2\u051b\u051d\5\u00d2j\2\u051c"+
		"\u051b\3\2\2\2\u051c\u051d\3\2\2\2\u051d\u051e\3\2\2\2\u051e\u0525\7<"+
		"\2\2\u051f\u0520\f\22\2\2\u0520\u0525\t\r\2\2\u0521\u0522\f\13\2\2\u0522"+
		"\u0523\7\34\2\2\u0523\u0525\5T+\2\u0524\u04d1\3\2\2\2\u0524\u04d4\3\2"+
		"\2\2\u0524\u04d7\3\2\2\2\u0524\u04e2\3\2\2\2\u0524\u04e5\3\2\2\2\u0524"+
		"\u04e8\3\2\2\2\u0524\u04eb\3\2\2\2\u0524\u04ee\3\2\2\2\u0524\u04f1\3\2"+
		"\2\2\u0524\u04f4\3\2\2\2\u0524\u04f7\3\2\2\2\u0524\u04fd\3\2\2\2\u0524"+
		"\u0500\3\2\2\2\u0524\u0503\3\2\2\2\u0524\u0506\3\2\2\2\u0524\u050d\3\2"+
		"\2\2\u0524\u0511\3\2\2\2\u0524\u0514\3\2\2\2\u0524\u0519\3\2\2\2\u0524"+
		"\u051f\3\2\2\2\u0524\u0521\3\2\2\2\u0525\u0528\3\2\2\2\u0526\u0524\3\2"+
		"\2\2\u0526\u0527\3\2\2\2\u0527\u00d9\3\2\2\2\u0528\u0526\3\2\2\2\u0529"+
		"\u052a\7;\2\2\u052a\u052b\5\u00d8m\2\u052b\u052c\7<\2\2\u052c\u053f\3"+
		"\2\2\2\u052d\u053f\7-\2\2\u052e\u053f\5\u00dco\2\u052f\u053f\5n8\2\u0530"+
		"\u053f\7f\2\2\u0531\u0532\5T+\2\u0532\u0533\7C\2\2\u0533\u0534\7\13\2"+
		"\2\u0534\u053f\3\2\2\2\u0535\u0536\7\62\2\2\u0536\u0537\7C\2\2\u0537\u053f"+
		"\7\13\2\2\u0538\u053c\5\u00eav\2\u0539\u053d\5\u00f2z\2\u053a\u053b\7"+
		"-\2\2\u053b\u053d\5\u00f4{\2\u053c\u0539\3\2\2\2\u053c\u053a\3\2\2\2\u053d"+
		"\u053f\3\2\2\2\u053e\u0529\3\2\2\2\u053e\u052d\3\2\2\2\u053e\u052e\3\2"+
		"\2\2\u053e\u052f\3\2\2\2\u053e\u0530\3\2\2\2\u053e\u0531\3\2\2\2\u053e"+
		"\u0535\3\2\2\2\u053e\u0538\3\2\2\2\u053f\u00db\3\2\2\2\u0540\u0541\7*"+
		"\2\2\u0541\u00dd\3\2\2\2\u0542\u0543\5\u00eav\2\u0543\u0544\5\u00e0q\2"+
		"\u0544\u0545\5\u00e6t\2\u0545\u054c\3\2\2\2\u0546\u0549\5\u00e0q\2\u0547"+
		"\u054a\5\u00e4s\2\u0548\u054a\5\u00e6t\2\u0549\u0547\3\2\2\2\u0549\u0548"+
		"\3\2\2\2\u054a\u054c\3\2\2\2\u054b\u0542\3\2\2\2\u054b\u0546\3\2\2\2\u054c"+
		"\u00df\3\2\2\2\u054d\u054f\7f\2\2\u054e\u0550\5\u00ecw\2\u054f\u054e\3"+
		"\2\2\2\u054f\u0550\3\2\2\2\u0550\u0558\3\2\2\2\u0551\u0552\7C\2\2\u0552"+
		"\u0554\7f\2\2\u0553\u0555\5\u00ecw\2\u0554\u0553\3\2\2\2\u0554\u0555\3"+
		"\2\2\2\u0555\u0557\3\2\2\2\u0556\u0551\3\2\2\2\u0557\u055a\3\2\2\2\u0558"+
		"\u0556\3\2\2\2\u0558\u0559\3\2\2\2\u0559\u055d\3\2\2\2\u055a\u0558\3\2"+
		"\2\2\u055b\u055d\5X-\2\u055c\u054d\3\2\2\2\u055c\u055b\3\2\2\2\u055d\u00e1"+
		"\3\2\2\2\u055e\u0560\7f\2\2\u055f\u0561\5\u00eex\2\u0560\u055f\3\2\2\2"+
		"\u0560\u0561\3\2\2\2\u0561\u0562\3\2\2\2\u0562\u0563\5\u00e6t\2\u0563"+
		"\u00e3\3\2\2\2\u0564\u0580\7?\2\2\u0565\u056a\7@\2\2\u0566\u0567\7?\2"+
		"\2\u0567\u0569\7@\2\2\u0568\u0566\3\2\2\2\u0569\u056c\3\2\2\2\u056a\u0568"+
		"\3\2\2\2\u056a\u056b\3\2\2\2\u056b\u056d\3\2\2\2\u056c\u056a\3\2\2\2\u056d"+
		"\u0581\5P)\2\u056e\u056f\5\u00d8m\2\u056f\u0576\7@\2\2\u0570\u0571\7?"+
		"\2\2\u0571\u0572\5\u00d8m\2\u0572\u0573\7@\2\2\u0573\u0575\3\2\2\2\u0574"+
		"\u0570\3\2\2\2\u0575\u0578\3\2\2\2\u0576\u0574\3\2\2\2\u0576\u0577\3\2"+
		"\2\2\u0577\u057d\3\2\2\2\u0578\u0576\3\2\2\2\u0579\u057a\7?\2\2\u057a"+
		"\u057c\7@\2\2\u057b\u0579\3\2\2\2\u057c\u057f\3\2\2\2\u057d\u057b\3\2"+
		"\2\2\u057d\u057e\3\2\2\2\u057e\u0581\3\2\2\2\u057f\u057d\3\2\2\2\u0580"+
		"\u0565\3\2\2\2\u0580\u056e\3\2\2\2\u0581\u00e5\3\2\2\2\u0582\u0584\5\u00f4"+
		"{\2\u0583\u0585\5&\24\2\u0584\u0583\3\2\2\2\u0584\u0585\3\2\2\2\u0585"+
		"\u00e7\3\2\2\2\u0586\u0587\5\u00eav\2\u0587\u0588\5\u00f2z\2\u0588\u00e9"+
		"\3\2\2\2\u0589\u058a\7F\2\2\u058a\u058b\5$\23\2\u058b\u058c\7E\2\2\u058c"+
		"\u00eb\3\2\2\2\u058d\u058e\7F\2\2\u058e\u0591\7E\2\2\u058f\u0591\5Z.\2"+
		"\u0590\u058d\3\2\2\2\u0590\u058f\3\2\2\2\u0591\u00ed\3\2\2\2\u0592\u0593"+
		"\7F\2\2\u0593\u0596\7E\2\2\u0594\u0596\5\u00eav\2\u0595\u0592\3\2\2\2"+
		"\u0595\u0594\3\2\2\2\u0596\u00ef\3\2\2\2\u0597\u059e\5\u00f4{\2\u0598"+
		"\u0599\7C\2\2\u0599\u059b\7f\2\2\u059a\u059c\5\u00f4{\2\u059b\u059a\3"+
		"\2\2\2\u059b\u059c\3\2\2\2\u059c\u059e\3\2\2\2\u059d\u0597\3\2\2\2\u059d"+
		"\u0598\3\2\2\2\u059e\u00f1\3\2\2\2\u059f\u05a0\7*\2\2\u05a0\u05a4\5\u00f0"+
		"y\2\u05a1\u05a2\7f\2\2\u05a2\u05a4\5\u00f4{\2\u05a3\u059f\3\2\2\2\u05a3"+
		"\u05a1\3\2\2\2\u05a4\u00f3\3\2\2\2\u05a5\u05a7\7;\2\2\u05a6\u05a8\5\u00d2"+
		"j\2\u05a7\u05a6\3\2\2\2\u05a7\u05a8\3\2\2\2\u05a8\u05a9\3\2\2\2\u05a9"+
		"\u05aa\7<\2\2\u05aa\u00f5\3\2\2\2\u05ab\u05ac\t\16\2\2\u05ac\u00f7\3\2"+
		"\2\2\u00a5\u00fe\u0104\u010b\u010e\u0111\u0116\u011c\u0124\u012d\u0132"+
		"\u0139\u0140\u0147\u014e\u0153\u0157\u015b\u015f\u0164\u0168\u016c\u0176"+
		"\u017e\u0185\u018c\u0190\u0193\u0196\u019f\u01a5\u01aa\u01ad\u01b3\u01b9"+
		"\u01bd\u01c6\u01cd\u01d6\u01dd\u01e5\u01ea\u01f1\u01f8\u01ff\u0206\u020d"+
		"\u0214\u021b\u0222\u0226\u022b\u022f\u0237\u023b\u0247\u0255\u025a\u0263"+
		"\u026b\u0275\u027d\u0285\u028a\u0296\u029c\u02a3\u02a8\u02b0\u02b4\u02b6"+
		"\u02c1\u02c9\u02cc\u02d0\u02d5\u02d9\u02e4\u02ed\u02ef\u02f6\u02fb\u0304"+
		"\u0309\u030c\u0311\u031a\u032e\u0337\u033e\u0341\u034a\u0354\u035c\u035f"+
		"\u0362\u036f\u0377\u037c\u0384\u0388\u038c\u0390\u0392\u0396\u039c\u03a6"+
		"\u03ad\u03b5\u03ce\u03d8\u03df\u03e3\u0400\u0403\u0406\u0410\u0414\u041c"+
		"\u0422\u042f\u0439\u043f\u044c\u045b\u0466\u046f\u0475\u0480\u0485\u0491"+
		"\u0495\u0499\u049d\u049f\u04a3\u04a8\u04bb\u04cf\u04df\u050a\u051c\u0524"+
		"\u0526\u053c\u053e\u0549\u054b\u054f\u0554\u0558\u055c\u0560\u056a\u0576"+
		"\u057d\u0580\u0584\u0590\u0595\u059b\u059d\u05a3\u05a7";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}