package ca.mcgill.cs.konaila.presentation;

import org.junit.Assert;
import org.junit.Test;

import ca.mcgill.cs.konaila.presentation.practices.ShortenIdentifiersToInitials;

public class TestShortenIdentifier {
	
	@Test
	public void test1() {
		String s = ShortenIdentifiersToInitials.shorten("MessageDialog");		
		Assert.assertEquals("md", s);
	}
	
	@Test
	public void test2() {
		String s = ShortenIdentifiersToInitials.shorten("event");
		Assert.assertEquals("e", s);
	}
	
	@Test
	public void test3() {
		String s = ShortenIdentifiersToInitials.shorten("cpuTimeTakenbyJob");
		Assert.assertEquals("cttj", s);
	}
	
	@Test
	public void test4() {
		String s = ShortenIdentifiersToInitials.shorten("CPUTimeTakenbyJob");
		Assert.assertEquals("cttj" , s);
	}
	
	@Test
	public void test5() {
		String s = ShortenIdentifiersToInitials.shorten("getCPUTimeTakenbyJob");
		Assert.assertEquals("gcttj" , s);
	}

}
