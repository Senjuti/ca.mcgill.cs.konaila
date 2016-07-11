package ca.mcgill.cs.konaila.selection.features;

import ca.mcgill.cs.konaila.AbstractErrorStrategy;

public class PropertyExtractorErrorStrategy extends AbstractErrorStrategy {

	private String query;

	public PropertyExtractorErrorStrategy(int cid, String source, String code, String query,
			boolean useDatabase, int numberOfPriorTrials) {
		super(cid,source,code,useDatabase,numberOfPriorTrials);
		this.query = query;
	}
	
	@Override
	public void reparse(String newCode) {
		PropertyExtractor.addFeatures(cid, source, newCode, query, useDatabase, numberOfPriorTrials + 1);

	}

}
