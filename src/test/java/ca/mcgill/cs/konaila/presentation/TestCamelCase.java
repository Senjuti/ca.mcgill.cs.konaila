package ca.mcgill.cs.konaila.presentation;

import java.util.ArrayList;
import java.util.LinkedList;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;
import org.junit.Assert;
import org.junit.Test;

public class TestCamelCase {
	
	@Test
	public void testMessageDialog() {
		LinkedList<String> s = SplitCamelCase.splitCamelCaseString("MessageDialog");
		Assert.assertEquals("Message" , s.get(0));
		Assert.assertEquals("Dialog", s.get(1));
		Assert.assertEquals(2, s.size());
	}
	
	@Test
	public void test2() {
		LinkedList<String> s = SplitCamelCase.splitCamelCaseString("event");
		Assert.assertEquals("event" , s.get(0));
		Assert.assertEquals(1, s.size());
	}
	
	@Test
	public void test3() {
		String s1 = "cpuTimeTakenbyJob";
		LinkedList<String> s = SplitCamelCase.splitCamelCaseString(s1);
		Assert.assertEquals("cpu" , s.get(0));
		Assert.assertEquals(4, s.size());
		
		
		String s2 = StringUtils.join(s," ");
		String initials = StringUtils.lowerCase(WordUtils.initials(s2));
		Assert.assertEquals("cttj",initials);
	}
	
	@Test
	public void test4() {
		String name = "CPUTimeTakenbyJob";
		LinkedList<String> segments = SplitCamelCase.splitCamelCaseString(name);
		ArrayList<String> result = new ArrayList<String>();
		Assert.assertEquals("CPU" , segments.get(0));
		Assert.assertEquals(4, segments.size());
		

//		
//		String[] additionals = name.split(".");
//		
//	
//		String s2 = StringUtils.join(segments,"");
//		
//		String diff = StringUtils.difference("CPUTimeTakenbyJob", s2);
//
//		String diff2 = StringUtils.difference(s2,"CPUTimeTakenbyJob");
//		String initials = StringUtils.lowerCase(WordUtils.initials(s2));
		

	}
	
	@Test
	public void test5() {
		String s1 = "getCPUTimeTakenbyJob";
		LinkedList<String> s = SplitCamelCase.splitCamelCaseString(s1);
		String s2 = s.toString();
		String diff = StringUtils.difference(s1, s2);
		Assert.assertEquals("CPU" , s.get(1));
		Assert.assertEquals(5, s.size());
	}


}
