package ca.mcgill.cs.konaila.data;

public class StackOverflowCodeFragment {
	String text;
	int postId;
	String postType;
	String question;
	String threadUrl;
	String codeTermClass;
	boolean codeTermInferred;
	int creationDate;
	int lastActivityDate;
	int score;
	int questionId;
	int questionCreationDate;
	int questionScore;
	String sternaFilename;

	StackOverflowCodeFragment(String text, int postId, 
			String postType, String question,
			String threadUrl, 
			String codeTermClass, boolean codeTermInferred, 
			int lastActivityDate, int score,
			int questionId, int questionCreationDate, int questionScore,
			String sternaFilename) {
		this.text = text;
		this.postId = postId;
		this.postType = postType;
		this.question = question;
		this.threadUrl = threadUrl;
		this.codeTermClass = codeTermClass;
		this.codeTermInferred = codeTermInferred;
		this.lastActivityDate = lastActivityDate;
		this.score = score;
		this.questionId = questionId;
		this.questionCreationDate = questionCreationDate;
		this.questionScore = questionScore;
		this.sternaFilename = sternaFilename;
	}

	public String getText() {
		return text;
	}

	public int getPostId() {
		return postId;
	}

	public String getPostType() {
		return postType;
	}

	public String getQuestion() {
		return question;
	}

	public String getThreadUrl() {
		return threadUrl;
	}

	public String getCodeTermClass() {
		return codeTermClass;
	}

	public boolean isCodeTermInferred() {
		return codeTermInferred;
	}

	public int getCreationDate() {
		return creationDate;
	}

	public int getLastActivityDate() {
		return lastActivityDate;
	}

	public int getScore() {
		return score;
	}

	public int getQuestionId() {
		return questionId;
	}

	public int getQuestionCreationDate() {
		return questionCreationDate;
	}
	
	public int getQuestionScore() {
		return questionScore;
	}
	
	public String getSternaFilename() {
		return sternaFilename;
	}
} 