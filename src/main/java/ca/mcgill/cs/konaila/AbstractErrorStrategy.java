package ca.mcgill.cs.konaila;

import java.io.StringReader;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.RuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import ca.mcgill.cs.konaila.chopper.ParsingStats;
import ca.mcgill.cs.konaila.parse.XmlCheck;

public abstract class AbstractErrorStrategy extends BailErrorStrategy {
	
	public static final String COMMENT_TOKEN_ADDED = "// <ADDED BY KONAILA> ";
	
	protected int cid;
	protected String source;
	protected String code;
	protected int numberOfPriorTrials;
	protected boolean useDatabase;
	
	public AbstractErrorStrategy(int cid, String source, String code, boolean useDatabase,int numberOfPriorTrials) {
		super();
		this.cid = cid;
		this.source = source;
		this.code = code;
		this.useDatabase = useDatabase;
		this.numberOfPriorTrials = numberOfPriorTrials;
	}

	@Override
	public void reportError(Parser recognizer, RecognitionException e) {
					
		super.reportError(recognizer, e);
		
		String justification = "cannot parse";
		
		Token offendingToken = e.getOffendingToken();
		int index = offendingToken.getTokenIndex();
		int state = e.getOffendingState();
		Object o = recognizer.getExpectedTokens();

		RuleContext context = e.getCtx();
		String contextString = context==null ? "" : context.getClass().getSimpleName();		
		
		int numberOfLines = code.split("\n").length;
		
		// sure 
		{
			if( offendingToken.getText().equals("<EOF>")){
				justification = "EOF reached in Java parsing -- something missing at the end";
				ParsingStats.write(source, code,  cid,  index,  offendingToken.getText(),
						state, contextString, justification, useDatabase);
				return;
			} else {
				SAXParseException saxException = XmlCheck.xmlCheck(new InputSource(new StringReader(code.trim())));
				if( saxException == null) {
					justification = "XML - definitely";
					ParsingStats.write(source, code,  cid,  index,  offendingToken.getText(),
							state, contextString, justification, useDatabase);
					return;
				} else if( saxException.getLineNumber() == numberOfLines ){
					justification = " XML but incomplete";
					ParsingStats.write(source, code,  cid,  index,  offendingToken.getText(),
							state, contextString, justification, useDatabase);
					return;
				} else if( saxException.getLineNumber() > 5 ) {
					justification = " XML until line " + saxException.getLineNumber();
					ParsingStats.write(source, code,  cid,  index,  offendingToken.getText(),
							state, contextString, justification, useDatabase);
					return;
				} 			
			}	
		}
		
		if( numberOfPriorTrials < 3 ) {
			String newCode = "";
			String[] lines = code.split("\n");
			for( int i=0; i < lines.length; i++) {
				String line = lines[i];
				int lineNumber = i+1;
				if( offendingToken.getLine() == lineNumber) {
					newCode += COMMENT_TOKEN_ADDED + line + "\n";
				} else {
					newCode += line + "\n";
				}
			}
			reparse(newCode);
			return;
		} 
			
		ParsingStats.write(source, code,  cid,  index,  offendingToken.getText(),
				state, contextString, justification, useDatabase);
		return;			
	}
	
	public abstract void reparse(String newCode);
	
	@Override
	public void reportMatch(Parser recognizer) {
		super.reportMatch(recognizer);
	}
	
	@Override
	public Token recoverInline(Parser recognizer) {
		try { 
			return super.recoverInline(recognizer);	
		} catch (ParseCancellationException e) {

//			cid=1226 with ... in the middle of the code
			
			String justification = "cannot parse";
			Token offendingToken = recognizer.getCurrentToken();
			Object o = recognizer.getExpectedTokens();
			int index = -1;
			String contextString = "";			

			ParsingStats.write(source, code,  cid,  index,  offendingToken.getText(),
					-1, contextString, justification, useDatabase);
			throw e;
		}
	}

	@Override
	public void recover(Parser arg0, RecognitionException e) {		
		super.recover(arg0, new RecognitionException(arg0,null,null));
	}
}