package ca.mcgill.cs.konaila.chopper;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class TestNonPublicMethod {

	@Test
	public void test() throws Exception {

//		String file = "/home/annie/git/konaila/database/eclipse-faq/E015.java";
		String file = "/home/annie/git/konaila/database/eclipse-faq/E035.java";
		String code = FileUtils.readFileToString(
				new File(file));
		Chopper.parseFile(100000, "test", 
				code, false, 0);
		
	}

}
