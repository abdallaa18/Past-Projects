package edu.iastate.cs228.proj4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * 
 * @author Abdalla Abdelrahman
 *
 */
public class TestEntryTree {
	
	/**
	 * This method tests that we will get a NullPointerException if the keyarr has a null element.
	 */
	@Test(expected = NullPointerException.class)
	public void addTest01() {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		
		Character[] charArray = new Character[4];
		charArray[0] = 'A';
		charArray[1] = 'B';
		charArray[2] = null;
		charArray[3] = 'D';
		
		String value = "Exception";
		
		tree.add(charArray, value);
	}
	/**
	 * This test ensures that the add method works properly and that it returns true, when it is a successful add. Or false when it was not successful, or null when needed.
	 */
	@Test
	public void addTest02() {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		EntryTree<Character, String>.Node temp = tree.root;
		
		Character[] charArray = new Character[4];
		charArray[0] = 'A';
		charArray[1] = 'B';
		charArray[2] = 'C';
		charArray[3] = 'D';
		String value = "Alphabet";
		
		assertTrue(tree.add(charArray, value));
		
		assertNull(temp.key);
		assertNull(temp.value);
		
		temp = temp.child;
		
		assertEquals(charArray[0], temp.key);
		assertNull(temp.value);
		
		temp = temp.child;
		
		assertEquals(charArray[1], temp.key);
		assertNull(temp.value);
		
		temp = temp.child;
		
		assertEquals(charArray[2], temp.key);
		assertNull(temp.value);
		
		temp = temp.child;
		
		assertEquals(charArray[3], temp.key);
		assertEquals("Alphabet", temp.value);
		
		temp = tree.root;
		
		Character[] charArray2 = new Character[4];
		charArray2[0] = 'A';
		charArray2[1] = 'B';
		charArray2[2] = 'E';
		charArray2[3] = 'G';
		String value2 = "Second";

		assertTrue(tree.add(charArray2, value2));
		
		assertNull(temp.key);
		assertNull(temp.value);
		
		temp = temp.child;
		
		assertEquals(charArray[0], temp.key);
		assertNull(temp.value);
		
		temp = temp.child;
		
		assertEquals(charArray[1], temp.key);
		assertNull(temp.value);
		
		temp = temp.child;
		
		assertEquals(charArray[2], temp.key);
		assertNull(temp.value);
		
		temp = temp.next;
		
		assertEquals(charArray2[2], temp.key);
		assertNull(temp.value);
		
		temp = temp.child;
		
		assertEquals(charArray2[3], temp.key);
		assertEquals("Second", temp.value);
		
		temp = temp.parent;
		
		temp = temp.prev;
		
		assertEquals(charArray[2], temp.key);
		assertNull(temp.value);
		
		temp = temp.child;
		
		assertEquals(charArray[3], temp.key);
		assertEquals("Alphabet", temp.value);
		
		Character[] charArray3 = new Character[0];
		String value3 = "null";
		assertFalse(tree.add(charArray3, value3));
		
		Character[] nullArray = null;
		
		assertFalse(tree.add(nullArray, value3));
		String nullValue = null;
		assertFalse(tree.add(charArray2, nullValue));
	}
	
	/**
	 * This method tests that we will get a NullPointerException if the keyarr has a null element.
	 */
	
	@Test(expected = NullPointerException.class)
	public void searchTest03() {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		
		Character[] charArray = new Character[4];
		charArray[0] = 'A';
		charArray[1] = 'B';
		charArray[2] = null;
		charArray[3] = 'D';
		
		Character[] charArray2 = new Character[4];
		charArray2[0] = 'A';
		charArray2[1] = 'B';
		charArray2[2] = 'E';
		charArray2[3] = 'G';
		
		String value2 = "Second";
		tree.add(charArray2, value2);
		
		tree.search(charArray);
		
		
		
	}
	/**
	 * This test ensures that the search method works properly, and that it returns null when needed.
	 */
	@Test
	public void searchTest04() {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		Character[] charArray = new Character[4];
		charArray[0] = 'A';
		charArray[1] = 'B';
		charArray[2] = 'C';
		charArray[3] = 'D';
		String value = "Alphabet";
		tree.add(charArray, value);
		
		assertEquals(value, tree.search(charArray));
		
		Character[] charArray2 = new Character[2];
		charArray2[0] = 'A';
		charArray2[1] = 'B';

		String value2 = "Second";
		
		assertNull(tree.search(charArray2));
		
		tree.add(charArray2, value2);
		assertEquals(value2, tree.search(charArray2));
		
		Character[] charArray3 = new Character[4];
		charArray3[0] = 'E';
		charArray3[1] = 'F';
		charArray3[2] = 'G';
		charArray3[3] = 'H';
		
		assertNull(tree.search(charArray3));
		
		Character[] emptyArray = new Character[0];
		assertNull(tree.search(emptyArray));

		Character[] nullArray = null;
		assertNull(tree.search(nullArray));
	}
	
	/**
	 * This method tests that we will get a NullPointerException if the keyarr has a null element.
	 */
	
	@Test(expected = NullPointerException.class)
	public void prefixTest04() {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		Character[] charArray = new Character[4];
		charArray[0] = 'A';
		charArray[1] = 'B';
		charArray[2] = null;
		charArray[3] = 'D';
		
		Character[] charArray2 = new Character[4];
		charArray2[0] = 'A';
		charArray2[1] = 'B';
		charArray2[2] = 'E';
		charArray2[3] = 'G';
		String value2 = "Second";
		tree.add(charArray2, value2);
		tree.prefix(charArray);
	}
	/**
	 * This test makes sure that the prefix method works properly and outputs null when needed.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void prefixTest05() {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		
		Character[] charArray = new Character[4];
		charArray[0] = 'A';
		charArray[1] = 'B';
		charArray[2] = 'C';
		charArray[3] = 'D';
		
		Character[] nullArray = null;
		
		Character[] charArray2 = new Character[4];
		charArray2[0] = 'A';
		charArray2[1] = 'B';
		charArray2[2] = 'E';
		charArray2[3] = 'G';
		String value2 = "Second";
		
		tree.add(charArray2, value2);
		
		assertNull(tree.prefix(nullArray));
		
		Character[] expectedArray = new Character[2];
		expectedArray[0] = 'A';
		expectedArray[1] = 'B';
		
		assertEquals(expectedArray, tree.prefix(charArray));
		
		Character[] charArray3 = new Character[4];
		charArray3[0] = 'E';
		charArray3[1] = 'F';
		charArray3[2] = 'G';
		charArray3[3] = 'H';
		
		assertNull(tree.prefix(charArray3));
		
		Character[] emptyArray = new Character[0];
		assertNull(tree.prefix(emptyArray));

	
		
	}
	
	/**
	 * This method tests that we will get a NullPointerException if the keyarr has a null element.
	 */
	@Test(expected = NullPointerException.class)
	public void removeTest06() {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		Character[] charArray = new Character[4];
		charArray[0] = 'A';
		charArray[1] = 'B';
		charArray[2] = 'C';
		charArray[3] = 'D';
		
		String value = "Alphabet";
		
		tree.add(charArray, value);
		Character[] charArray2 = new Character[4];
		charArray2[0] = 'A';
		charArray2[1] = 'B';
		charArray2[2] = null;
		charArray2[3] = 'D';
		tree.remove(charArray2);
	}
	
	/**
	 * This method tests the functionability of the remove method. This method adds various entries to the tree, and checks the result of remove. It makes sure that all
	 * specifications(when it is supposed to return null) is met. It also makes sure that the nodes will be reassigned after we remove nodes that have siblings.  
	 */
	
	@Test
	public void removeTest07() {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();
		Character[] charArray = new Character[4];
		charArray[0] = 'A';
		charArray[1] = 'B';
		charArray[2] = 'C';
		charArray[3] = 'D';
		
		String value = "Alphabet";
		
		tree.add(charArray, value);
		
		Character[] charArray2 = new Character[4];
		charArray2[0] = 'A';
		charArray2[1] = 'B';
		charArray2[2] = 'G';
		charArray2[3] = 'H';
		
		String value2 = "secondAlphabet";
		tree.add(charArray2, value2);
		
		Character[] charArray3 = new Character[4];
		charArray3[0] = 'E';
		charArray3[1] = 'F';
		charArray3[2] = 'G';
		charArray3[3] = 'H';
		String value3 = "thirdAlphabet";
		
		tree.add(charArray3,value3);
		
		EntryTree<Character, String>.Node temp = tree.root;
		assertNull(temp.key);
		assertNull(temp.value);
		temp = temp.child;
		assertEquals(charArray3[0], temp.next.key);
		assertEquals(value3, tree.remove(charArray3));
		assertNull(temp.next);
		
		temp = temp.child;
		assertEquals(charArray[2], temp.child.key);
		assertEquals(value, tree.remove(charArray));
		assertEquals(charArray2[2], temp.child.key);	
		
		Character[] charArray4 = new Character[4];
		charArray4[0] = 'W';
		charArray4[1] = 'X';
		charArray4[2] = 'Y';
		charArray4[3] = 'Z';
		
		assertNull(tree.remove(charArray4));
		
		Character[] emptyArray = new Character[0];
		assertNull(tree.search(emptyArray));

		Character[] nullArray = null;
		assertNull(tree.search(nullArray));
	}
	/**
	 * This method tests the functionablity of the getAll method by adding multiple entries, and comparing the output with an expected 2-D String Array.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getAllTest08() {
		EntryTree<Character, String> tree = new EntryTree<Character, String>();

		assertNull(tree.getAll());
		
		Character[] charArray = new Character[3];
		charArray[0] = 'I';
		charArray[1] = 'S';
		charArray[2] = 'U';
		
		String value = "COMS 228";
		tree.add(charArray, value);
		
		Character[] charArray2 = new Character[2];
		charArray2[0] = 'I';
		charArray2[1] = 'A';
		
		String value2 = "Engineering";
		tree.add(charArray2, value2);
		
		Character[] charArray3 = new Character[4];
		charArray3[0] = 'I';
		charArray3[1] = 'O';
		charArray3[2] = 'W';
		charArray3[3] = 'A';
	
		String value3 = "Ames";
		tree.add(charArray3, value3);
		
		String[][] expectedResult = new String[3][2];
		expectedResult[0][0] = "ISU";
		expectedResult[0][1] = "COMS 228";
		expectedResult[1][0] = "IA";
		expectedResult[1][1] = "Engineering";
		expectedResult[2][0] = "IOWA";
		expectedResult[2][1] = "Ames";
		
		assertEquals(expectedResult, tree.getAll());
	
	}
}
