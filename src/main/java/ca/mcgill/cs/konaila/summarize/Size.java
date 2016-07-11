package ca.mcgill.cs.konaila.summarize;

public 	enum Size {
	Small(3,50),
	Big(5,50);

	private int lineLength;
	private int width;
	Size(int lineLength, int width) {
		this.lineLength = lineLength;
		this.width = width;  
	}
	int getLineLength() { return lineLength; }
	int getWidth() {return width; }
}
