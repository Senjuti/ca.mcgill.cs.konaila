package ca.mcgill.cs.konaila.database.androidGuide;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;

public class LoadAndroidGuideFromStudy {

	static final String dir = "./database/android-guide/";
//	static final String[] dirFirstLevel = new String[] {"blockStatements","classBody","compilationUnit","cannotParse"};
	enum Structure {
		blockStatements ("JavaBlockStatements"),
		classBody ("JavaClassBodyMemberDeclaration"),
		compilationUnit ("JavaCompilationUnit"),
		cannotParse ("cannot parse");
		String displayName;
		Structure( String name ) {
			this.displayName = name;
		}		
		String getDisplayName() { return displayName; }
	}
	
	static final String[] dirSecondLevel = new String[] {"ellipses","comment-count-as-statement","mixing-class-body-and-method-body","missing-puntuation","missing-token","unmatched-brackets"};
	static final String queriesFilename = dir + "queries.csv";
	
	public static void main(String[] args) throws IOException {
		getAndroidGuide();
	}
	
	public static Collection<AndroidCodeFragment> getAndroidGuide() throws IOException {
		
		Collection<AndroidCodeFragment> result = new ArrayList<AndroidCodeFragment>();
		
		for( Structure s : Structure.values() ) {
			String displayName1 = s.getDisplayName();
			String dirName1 = s.name();
			String d = dir + dirName1;
			
			Collection<File> okFiles = FileUtils.listFiles(new File(d), TrueFileFilter.INSTANCE,FalseFileFilter.INSTANCE);
			for( File okFile : okFiles) {
				try {
					String filename = okFile.getName();
					result.add(new AndroidCodeFragment(
							displayName1,
							"no-problem",
							FileUtils.readFileToString(okFile),
							Integer.parseInt(filename.substring("J".length(), filename.length()-".java".length()))));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if( dirName1.equals("cannotParse")) {
				System.out.println();
			}
			for( String d2 : dirSecondLevel  ) {
				d = dir + dirName1 + "/" + d2;
				if( new File(d).exists() ) {
					Collection<File> problemFiles = FileUtils.listFiles(new File(d), TrueFileFilter.INSTANCE,FalseFileFilter.INSTANCE);
					for( File problemFile : problemFiles ) {
						try{
							String filename = problemFile.getName();
							String extract = filename.substring("J".length(), filename.length()-".java".length());
							result.add(new AndroidCodeFragment(
									displayName1,
									d2,
									FileUtils.readFileToString(problemFile),
									Integer.parseInt(filename.substring("J".length(), filename.length()-".java".length()))));						
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		
		loadQueries(result);
		
		return result;
	}
	
	public static void loadQueries(Collection<AndroidCodeFragment> codeFragments) throws IOException {
		Map<Integer,AndroidCodeFragment> index = new HashMap<Integer,AndroidCodeFragment>();
		for( AndroidCodeFragment c : codeFragments ) {
			index.put(c.getInternalId(),c);
		}
		
		List<String> rows = FileUtils.readLines(new File(queriesFilename));
		rows.remove(0); // remove header
		for( String row : rows ) {
			String[] tokens = row.split("\t");
			int cid = Integer.parseInt(tokens[0]);
			if( index.containsKey(cid)) {
				String query = tokens[1];
//				for( int i=1; i < tokens.length; i++ ) {
//					query += tokens[i];
//					if( i != tokens.length - 1 ) {
//						query += " > ";
//					}
//				}
				index.get(cid).setQuery(query);
			}
		}		
	}
}
