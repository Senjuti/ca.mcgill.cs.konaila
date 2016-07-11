package ca.mcgill.cs.konaila.selection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import ca.mcgill.cs.konaila.presentation.Tabs;
import ca.mcgill.cs.konaila.selection.DatabasePredictions.SelectedElement;

public class ReconstructSummary {
	
	public static String reconstructCode(Connection c, int cid, Collection<Integer> selectedUids) throws SQLException, IOException {	
		
		List<SelectedElement> elements = DatabasePredictions.getCodeForSelectedElements(c, cid, selectedUids);			
		
		String intermediateSummary = "";
		for( SelectedElement e : elements ) {
			
			String code = e.getCode().startsWith(".") ? e.getDisplayWhole() : e.getCode();
			
			code = code.replace("\t", " ").replace("\n"," ").replace("  ", " ");

			String line = Tabs.getTab(e.getIndentationLevel()) + code;
			
			intermediateSummary += line + "\n";
			
			System.out.println(e.getUid() + ": " + line);	
		}
		return intermediateSummary;
	}
	
	public static String reconstructCodeBetter(Connection c, int cid, Collection<Integer> uids) throws SQLException, IOException {	

		Map<Integer,SelectedElement> charStartToElement = DatabasePredictions.getCodeForSelectedElementsMap(c, cid, uids);

		List<Integer> charStarts = new ArrayList<Integer>(charStartToElement.keySet());
		Collections.sort(charStarts);

		String intermediateSummary = "";
		int currentLine = 0;
		for (Integer charStart : charStarts) {
			SelectedElement e = charStartToElement.get(charStart);

			String code = e.getCode();
			code = code.replace("  ", " ");
//			code = code.replace("\t", " ").replace("\n", " ")
//					.replace("  ", " ");

			String line = "";
			
			if( currentLine != e.getLineStart() ) {
				intermediateSummary += "\n";
//				line += Tabs.getTab(e.getIndentationLevel());
				currentLine = e.getLineStart();
			}
			
			line += code;
			intermediateSummary += line;
			System.out.println(e.getUid() + ": " + line);
		}	 

		return intermediateSummary.trim();
	}

}
