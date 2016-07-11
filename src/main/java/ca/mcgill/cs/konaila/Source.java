package ca.mcgill.cs.konaila;

public enum Source {
	
	Android("android-guide", "ANDROID-CONTENT", "Android Official Guide Code Fragments"), 
	Eclipse("eclipse-faq", "ECLIPSE-CONTENT", "Eclipse FAQ Code Fragments"),
	StackOverflow("stackoverflow", "STACKOVERFLOW-CONTENT", "StackOverflow Code Fragments");

	private String dbSourceString;
	private String templateStub;
	private String title;
	
	Source(String dbSourceString, String templateStub, String title) {
		this.dbSourceString = dbSourceString;
		this.templateStub = templateStub;
		this.title = title;
	}
	
	public String getTemplateStub() {	
		return templateStub;
	}
	
	public String getDbSourceString() {
		return dbSourceString;
	}
	
	public String getTitle() {
		return title;
	}
	
	public static String getReadableSource(String targetDbSourceString) {
		Source[] sources = Source.values();
		for( Source source : sources ) {
			if( source.getDbSourceString().equals(targetDbSourceString) ) {
				return source.name();
			}
		}
		throw new RuntimeException();
	}
}