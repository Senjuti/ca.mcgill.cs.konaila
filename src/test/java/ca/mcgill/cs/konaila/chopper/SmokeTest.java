package ca.mcgill.cs.konaila.chopper;

import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;

public class SmokeTest {

	@Test
	public void test() {		
		Collection<SelectionUnit> units = Chopper.parseAndChopFile(-1, "source1", 
				"public class Test {\n public static void main(String[] args) {\n \n}\n}", false, 0);
		Assert.assertEquals(2, units.size());
	}

}
