package ca.mcgill.cs.konaila.presentation.practices;

import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import ca.mcgill.cs.konaila.presentation.SplitCamelCase;

public class ShortenIdentifiersToInitials {
	
	public static String shorten(String string) {

		LinkedList<String> segments = SplitCamelCase.splitCamelCaseString(string);
				
		String s2 = StringUtils.join(segments, " ");
		String initials = StringUtils.lowerCase(WordUtils.initials(s2));
		
		return initials;
	}
}

