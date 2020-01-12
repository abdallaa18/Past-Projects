package edu.iastate.cs228.proj1.tests;
import org.junit.Test;
/**
 * 
 * @author Abdalla Abdelrahman
 *
 */
import static org.junit.Assert.*;
import edu.iastate.cs228.proj1.ProteinSequence;
public class ProteinSequenceTest {
@Test(expected = IllegalArgumentException.class)
	public void test01_Constructor(){
		String probst4 = new String("B");
		ProteinSequence probj = new ProteinSequence(probst4.toCharArray());
		
		String probst5 = new String("ABFF");
		ProteinSequence probj2 = new ProteinSequence(probst5.toCharArray());
		
		String probst6 = new String("{") ;
		ProteinSequence probj3 = new ProteinSequence(probst6.toCharArray());
}

@Test
public void test21_validLetterTest(){
	String arrayTest1 = new String("TTG");
	 ProteinSequence test1 = new ProteinSequence( arrayTest1.toCharArray());
	 
	 assertTrue(test1.isValidLetter('T'));
	 assertTrue(test1.isValidLetter('t'));

	 
}
}
