package edu.iastate.cs228.proj1.tests;

import org.junit.Test;
import static org.junit.Assert.*;
import edu.iastate.cs228.proj1.Sequence;
/**
 * 
 * @author Abdalla Abdelrahman
 *
 */
public class SequenceTest {
	@Test
	public void test01_validLetterTest(){
		String arrayTest1 = new String("TTG");
		 Sequence test1 = new Sequence( arrayTest1.toCharArray());
		 
		 assertTrue(test1.isValidLetter('T'));
		 assertTrue(test1.isValidLetter('t'));
		 assertFalse(test1.isValidLetter('%'));
	}
	@Test
	public void test02_toStringTest(){
		String stringTest1 = new String("TTG");
		 Sequence test1 = new Sequence( stringTest1.toCharArray());
		 String stringResult1 = test1.toString();
		 assertEquals(stringTest1, stringResult1);
		 
		String stringTest2 = new String("GhStrE");
		Sequence test2 = new Sequence( stringTest2.toCharArray());
		String stringResult2 = test2.toString();
		assertEquals(stringTest2, stringResult2);
	}
	@Test
	public void test03_getSeqTes(){
		String stringTest1 = new String("ATGTGA");
		Sequence test1 = new Sequence(stringTest1.toCharArray());
		char[] expectedResult1 = {'A','T','G','T','G','A'};
		assertArrayEquals(expectedResult1, test1.getSeq());
		
		String stringTest2 = new String("ThisTest");
		Sequence test2 = new Sequence(stringTest2.toCharArray());
		char[] expectedResult2 = {'T','h','i','s','T','e','s','t'};
		assertArrayEquals(expectedResult2, test2.getSeq());
	}
	@Test
	public void test03_getLengthTest(){
		String stringTest1 = new String("ATGCATTAG");
		Sequence test1 = new Sequence(stringTest1.toCharArray());
		assertEquals(9,test1.seqLength());
		
		String stringTest2 = new String("AATTAG");
		Sequence test2 = new Sequence(stringTest2.toCharArray());
		assertEquals(6,test2.seqLength());
		
		String stringTest3 = new String("AAG");
		Sequence test3 = new Sequence(stringTest3.toCharArray());
		assertEquals(3,test3.seqLength());
	}
	@Test(expected = IllegalArgumentException.class)
	public void test04_Constructor(){
		String stringTest1 = new String("ATGC$ATTAG");
		Sequence test1 = new Sequence(stringTest1.toCharArray());
		
		String stringTest2 = new String("&");
		Sequence test2 = new Sequence(stringTest2.toCharArray());
	}
	@Test
	public void test05_equalsTest() {
	
			String stringTest1 = new String("ATGTGA");
			Sequence test1 = new Sequence(stringTest1.toCharArray());
			
			char[] obj = {'A','T','G','T','G','A'};
			char[] obj2 = {'A','T','G','T','G'};
			char[] obj3 = {'T','T','G','T','G','A'};
			char[] obj4 = {'a','t','g','t','g','a'};
			assertEquals(true, test1.equals(obj));
			assertEquals(false, test1.equals(obj2));
			assertEquals(false, test1.equals(obj3));
			assertEquals(true, test1.equals(obj4));
		
	}

}
