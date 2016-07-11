package ca.mcgill.cs.konaila.database.androidGuide;


public class AndroidCodeFragment {
	private String parseStructure;
	private String parseProblem;
	private String content;
	private int internalId;
	private String query;
	
//	static final String log = "./android-guide.csv";
	
//	static {
//		try {
//			FileUtils.writeStringToFile(new File(log), "structure\tproblem\tid\n", false);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
	
	public AndroidCodeFragment() {}
	
	public AndroidCodeFragment(String structure, String problem, String content,
			int id) {
		super();
		this.parseStructure = structure;
		this.parseProblem = problem;
		this.content = content;
		this.internalId = id;
		
//		try {
//			FileUtils.writeStringToFile(new File(log), (structure + "\t" + problem + "\t" + id + "\n"),true);
//		} catch (IOException e){
//			e.printStackTrace();
//		}
	}

	public String getParseStructure() {
		return parseStructure;
	}

	public String getParseProblem() {
		return parseProblem;
	}

	public String getContent() {
		return content;
	}

	public int getInternalId() {
		return internalId;
	}
	
	public String getQuery() {
		return query;
	}

	public void setParseStructure(String structure) {
		this.parseStructure = structure;
	}

	public void setParseProblem(String problem) {
		this.parseProblem = problem;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setId(int id) {
		this.internalId = id;
	}

	public void setQuery(String query) {
		this.query = query;
	}
}