package ca.mcgill.cs.konaila.parse;

public class CodeFragmentPrerequisite {
	
	public static final int MINIMUM_NUMBER_OF_LINES = 10;
	public static final int MINIMUM_NUMBER_OF_CHARACTERS = 80*MINIMUM_NUMBER_OF_LINES;
	
	public static boolean isLongEnough(String code) {
		int numberOfLines = getLength(code);
		int numberOfCharacters = code.length();
		
		return numberOfLines >= MINIMUM_NUMBER_OF_LINES ||
				numberOfCharacters >= MINIMUM_NUMBER_OF_CHARACTERS;
	}

	public static boolean isLongEnoughMore(String code) {			
		return isLongEnough(code) && getLengthNonEmptyLines(code)>=MINIMUM_NUMBER_OF_LINES;
	}
	
	public static int getLength(String code) {
		return code.split("\n").length;
	}

	public static int getLengthNonEmptyLines(String code) {
		int numberOfLines = 0;
		String[] codeLines = code.split("\n");
		
		for( String codeLine : codeLines) {
			if( codeLine.trim().length()>0) {
				numberOfLines += 1;
			}
		}
				
		return numberOfLines;
	}
	
}
