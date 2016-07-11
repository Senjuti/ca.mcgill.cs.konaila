package ca.mcgill.cs.konaila.summarize;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import ca.mcgill.cs.konaila.database.DatabaseChopperStats;
import ca.mcgill.cs.konaila.presentation.formatting.FormattingServer;
import ca.mcgill.cs.konaila.presentation.formatting.How;
import ca.mcgill.cs.konaila.selection.DatabasePredictions;
import ca.mcgill.cs.konaila.ui.SummarySerlvet.SummarizationMethod;

public class LineLengthUtilOnAllSelectionUnits {
	
	
	public static Map<Integer,Pair<Float,Integer>> getUidToCharactersPerLine(
			Connection c, int cid, int width) throws SQLException, IOException {
		
		Map<Integer,Pair<Float,Integer>> uidToCharactersPerLine = 
				new HashMap<Integer,Pair<Float,Integer>>();
		
		Map<Integer,String> uidToFormattedCode = getUidToFormattedCode(c, cid, width);
				
		for( Entry<Integer,String> e : uidToFormattedCode.entrySet() ) {
			int uid = e.getKey();
			String formattedCode = e.getValue();
			
			int numberOfLines = getNumberOfLines(formattedCode);
			int characters = getNumberOfCharactersPerLine(formattedCode);
			
			float charactersPerLine = (float) characters / (float) numberOfLines;
			uidToCharactersPerLine.put(uid, new ImmutablePair<Float,Integer>(
					charactersPerLine,numberOfLines));
		}
		return uidToCharactersPerLine;
	}
	
	
	public static Map<Integer,Integer> getNumberOfLinesPerUnitAfterFormatter(
			Connection c, int cid, SummarizationMethod summarizationMethod, 
			int width) throws SQLException, IOException {
		
		Map<Integer,Integer> numberOfLines = new HashMap<Integer,Integer>();
		Map<Integer,String> uidToFormattedCode = getUidToFormattedCode(c, cid, width);
						
		for( Entry<Integer,String> e : uidToFormattedCode.entrySet() ) {
			int uid = e.getKey();
			String formattedCode = e.getValue();
			int numberOfLine = getNumberOfLines(formattedCode);
			numberOfLines.put(uid, numberOfLine);
		}
		return numberOfLines;
	}
	
	public static Map<Integer,String> getUidToFormattedCode(
			Connection c, int cid, int width) throws SQLException, IOException {
		
		Map<Integer,String> uidToFormattedCode = new HashMap<Integer,String>();
						
		Map<Integer,String> uidToCode = DatabaseChopperStats.selectSelectionUnitCode(
				c, cid);
		
		for( Entry<Integer,String> e : uidToCode.entrySet() ) {
			int uid = e.getKey();
			String code = e.getValue();
			String formattedCode = 
					FormattingServer.format(code,  How.Compact, width,
							FormattingServer.URL);
			uidToFormattedCode.put(uid,formattedCode);
		}
		return uidToFormattedCode;
	}
	
	public static int getNumberOfLinesAfterFormatter(Connection c, int cid, 
			int uid, SummarizationMethod summarizationMethod, int width) 
					throws SQLException, IOException {
		
		String code = DatabasePredictions.selectSelectionUnitCode(
				c, cid, uid, summarizationMethod);
		
		String formattedCode = 
				FormattingServer.format(code,  How.Compact, width,
						FormattingServer.URL);
		int numberOfLines = getNumberOfLines(formattedCode);
		
		return numberOfLines;		
	}
	
	public static int getNumberOfLines(String code) {
		return code.length() - code.replace("\n","").length();		
	}
	
	public static int getNumberOfCharactersPerLine(String code) {
		return code.replace("\n","")
				.replace(" ","")
				.length();		
	}

}
