package ca.mcgill.cs.konaila.selection.features;

import java.util.ArrayList;
import java.util.List;

public class QueryUtil {

	public static List<String> getCodeWords(String name) {
		char[] chars = name.toCharArray();
		List<String> words = new ArrayList<String>();
		int i = 0;
		int begWord = 0;
		for (char c : chars) {
			if (Character.isUpperCase(c)) {
				if (i > 0) {
					begWord = addWord(words, i, begWord, name);
				}
			}
			i += 1;
		}
		if (i > 0) {
			addWord(words, i, begWord, name);
		}

		List<String> fixedWords = fixSingleCapitalLetters(words);

		return fixedWords;
	}

	public static boolean queryContainTerm(String query, String word) {
		if( word.length()==1) return false;
		return query.toLowerCase().indexOf(word.toLowerCase()) != -1;
	}



	private static List<String> fixSingleCapitalLetters(List<String> words) {
		int begSingleCapitalLetterToBeJoined = -1;
		int k = 0;
		List<String> fixedWords = new ArrayList<String>();
		for (String word : words) {
			if (word.length() == 1 && Character.isUpperCase(word.charAt(0))) {
				if (begSingleCapitalLetterToBeJoined == -1) {
					begSingleCapitalLetterToBeJoined = k;
				}
				k++;
				continue;
			} else {
				if (begSingleCapitalLetterToBeJoined != -1) {
					StringBuffer capitalizedWords = new StringBuffer();
					for (int j = begSingleCapitalLetterToBeJoined; j < k; j++) {
						String w = words.get(j);
						capitalizedWords.append(w);
					}
					fixedWords.add(capitalizedWords.toString());
					begSingleCapitalLetterToBeJoined = -1;
				}
			}
			fixedWords.add(word);
			k++;
		}
		// add the last one
		if (begSingleCapitalLetterToBeJoined != -1) {
			StringBuffer capitalizedWords = new StringBuffer();
			for (int j = begSingleCapitalLetterToBeJoined; j < k; j++) {
				String w = words.get(j);
				capitalizedWords.append(w);
			}
			fixedWords.add(capitalizedWords.toString());
		}
		return fixedWords;
	}

	private static int addWord(List<String> words, int i, int begWord,
			String name) {
		String word = "";
		word += name.substring(begWord, i);
		words.add(word);
		begWord = i;
		return begWord;
	}
	
}
