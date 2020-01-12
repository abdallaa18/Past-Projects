package edu.iastate.cs228.proj1.tests;


import org.junit.Test;
import static org.junit.Assert.*;
import edu.iastate.cs228.proj1.DNASequence;
/**
 * 
 * @author Abdalla Abdelrahman
 *
 */

public class DNASequenceTest {
	@Test
	public void test01_validLetterTest(){
		char[] arrayTest1 = {'A', 'C', 'G'};
		DNASequence test1 = new DNASequence(arrayTest1);
		
		assertTrue(test1.isValidLetter('A'));
		assertTrue(test1.isValidLetter('a'));
		assertTrue(test1.isValidLetter('C'));
		assertTrue(test1.isValidLetter('c'));
		assertTrue(test1.isValidLetter('G'));
		assertTrue(test1.isValidLetter('g'));
		assertTrue(test1.isValidLetter('T'));
		assertTrue(test1.isValidLetter('t'));
		assertFalse(test1.isValidLetter('d'));
		assertFalse(test1.isValidLetter('#'));
		}
@Test(expected = IllegalArgumentException.class)
public void test02_constructorTest(){
	String probst2 = new String("TDG");
	DNASequence dnaseqobj = new DNASequence( probst2.toCharArray() );
	
	String stringTest1 = new String("%");
	DNASequence test1 = new DNASequence(stringTest1.toCharArray() );
}
@Test
public void test03_constructorTest(){
	String stringTest1 = new String("ACTG");
	DNASequence test1 = new DNASequence( stringTest1.toCharArray() );
}
}
