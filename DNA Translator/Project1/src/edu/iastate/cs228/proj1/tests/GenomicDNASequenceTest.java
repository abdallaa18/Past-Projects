package edu.iastate.cs228.proj1.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import edu.iastate.cs228.proj1.GenomicDNASequence;
import edu.iastate.cs228.proj1.Sequence;

/**
 * 
 * @author Abdalla Abdelrahman
 *
 */
public class GenomicDNASequenceTest {

@Test(expected = IllegalArgumentException.class)
public void test01_constructorTest() {
	 String stringTest1 = new String("$");
	 GenomicDNASequence test1 = new GenomicDNASequence(stringTest1.toCharArray());
	 
	 String stringTest2 = new String("BDE");
	 GenomicDNASequence test2 = new GenomicDNASequence(stringTest2.toCharArray());
	 
}
@Test
public void test02_constructorTest() {
	 String stringTest2 = new String("aCGT");
	 GenomicDNASequence test2 = new GenomicDNASequence(stringTest2.toCharArray());
}
@Test(expected = IllegalArgumentException.class)
public void test03_markCodingTest() {
	String stringTest1 = new String("AGTACTATC");
	 GenomicDNASequence test1 = new GenomicDNASequence( stringTest1.toCharArray());
	 test1.markCoding(2, 1);
	 test1.markCoding(-1, 3);
	 test1.markCoding(1, 9);
	 test1.markCoding(1, 10);
}
@Test(expected = IllegalArgumentException.class)
public void test04_extractExonsTest() {
	String stringTest1 = new String("AGTACTATCATTTAGATC");
	int[] position = new int[0];
	int[] position2 = {1,2, 3};
	int[] position3 = {1,2, 0,4};
	int[] position4 = {1,2,0,11};
	int[] position5 = {1,2,1,4};
	 GenomicDNASequence test1 = new GenomicDNASequence( stringTest1.toCharArray());
	test1.extractExons(position);
	test1.extractExons(position2);
	test1.extractExons(position3);
	test1.extractExons(position4);
	test1.extractExons(position5);
}
@Test
public void test05_extractExonsTest() {
	 String demodna = new String("AATGCCAGTCAGCATAGCGTAGACT");
	 int[] ardemo = {1, 5, 8, 10, 13, 16};
	GenomicDNASequence test1 = new GenomicDNASequence( demodna.toCharArray());
	test1.markCoding(1, 16);
	char[] result = test1.extractExons(ardemo);
	char[] expectedResult = {'A', 'T', 'G', 'C', 'C', 'T', 'C', 'A', 'A', 'T', 'A', 'G'};
	assertArrayEquals(expectedResult, result);
	
	 String demodna2 = new String("AATGCAAGTCAGCATAGCGTAGACT");
	 int[] ardemo2 = {1, 5, 8, 10, 13, 15};
	GenomicDNASequence test2 = new GenomicDNASequence( demodna2.toCharArray());
	test2.markCoding(1, 15);
	char[] result2 = test2.extractExons(ardemo2);
	char[] expectedResult2 = {'A', 'T', 'G', 'C', 'A', 'T', 'C', 'A', 'A', 'T', 'A'};
	assertArrayEquals(expectedResult2, result2);
}
}
