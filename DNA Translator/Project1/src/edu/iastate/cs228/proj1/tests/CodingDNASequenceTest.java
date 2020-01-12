package edu.iastate.cs228.proj1.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.iastate.cs228.proj1.CodingDNASequence;


/**
 * 
 * @author Abdalla Abdelrahman
 *
 */

public class CodingDNASequenceTest {
	@Test
	public void test01_checkStartCodon(){
		char[] arrayTest1 = {'A'};
		CodingDNASequence test1 = new CodingDNASequence(arrayTest1);
		assertFalse(test1.checkStartCodon());
		
		char[] arrayTest2 = {'A', 'C', 'G', 'T'};
		CodingDNASequence test2 = new CodingDNASequence(arrayTest2);
		assertFalse(test2.checkStartCodon());
		
		char[] arrayTest3 = {'A', 'C', 'G', 'T', 'A', 'T', 'G'};
		CodingDNASequence test3 = new CodingDNASequence(arrayTest3);
		assertTrue(test3.checkStartCodon());
		}
	@Test
	public void test02_codonTranslate(){
		String s = "ATGACAAGTCCATAA";
		char[] arrayTest1 = s.toCharArray();
		char [] result1 = new char[arrayTest1.length];
		
		result1[0] = 'M';
		result1[1] = 'T';
		result1[2] = 'S';
		result1[3] = 'P';
		
		CodingDNASequence test1 = new CodingDNASequence(arrayTest1);
		
		assertArrayEquals(test1.translate(), result1);
		
		String s2 = "ATGAGATAATAC";
		char[] arrayTest2 = s2.toCharArray();
		
	char[] result2 = new char[arrayTest2.length];
		
	result2[0] = 'M';
	result2[1] = 'R';
	
		
	CodingDNASequence test2 = new CodingDNASequence(arrayTest2);
	
	assertArrayEquals(result2, test2.translate());
	}
@Test(expected = IllegalArgumentException.class)
public void test03_ConstructorTest(){
	String stringTest1 = new String("$");
	 CodingDNASequence test1 = new  CodingDNASequence(stringTest1.toCharArray());
	 
	 String stringTest2 = new String("BDE");
	 CodingDNASequence test2 = new  CodingDNASequence(stringTest2.toCharArray());
}
@Test
public void test04_ConstructorTest(){
	String stringTest1 = new String("ACT");
	 CodingDNASequence test1 = new  CodingDNASequence(stringTest1.toCharArray());
	 
	 String stringTest2 = new String("AGT");
	 CodingDNASequence test2 = new  CodingDNASequence(stringTest2.toCharArray());
}
@Test(expected = RuntimeException.class)
public void test05_translateErrorTest(){
	String stringTest1 = new String("AGTATT");
	char[] arrayTest1 = stringTest1.toCharArray();
	CodingDNASequence test1 = new  CodingDNASequence(arrayTest1);
	 test1.translate();
	
	 String stringTest2 = new String("AATTGCAAGTCAGCATAGCGTAGACT");
	 char[] arrayTest2 = stringTest2.toCharArray();
	 
	 CodingDNASequence test2 = new  CodingDNASequence(arrayTest2);
	 test2.translate();
	 
}
}
