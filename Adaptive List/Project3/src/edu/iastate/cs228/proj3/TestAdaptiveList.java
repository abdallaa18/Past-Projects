package edu.iastate.cs228.proj3;


import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import org.junit.runners.MethodSorters;

import edu.iastate.cs228.proj3.AdaptiveList.ListNode;

import org.junit.FixMethodOrder;
import org.junit.Rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * @author Abdalla Abdelrahman
 * 
* Various JUNIT tests that test the various methods within Adaptive Test
 * 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAdaptiveList {
	/**
	 * This test checks if the list is empty before and after something is inputted
	 */
	@Test
	public void isEmptyTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		assertTrue(seq.isEmpty());
		seq.add("Should not be empty");
		assertFalse(seq.isEmpty());
		
	}
	/**
	 * This test makes sure that the size of the list is changing properly after add and remove.
	 */
	@Test
	public void sizeTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		assertEquals(0, seq.size());
		seq.add("1");
		seq.add("2");
		seq.add("3");
		assertEquals(3,seq.size());
		seq.remove("3");
		assertEquals(2,seq.size());

	}
	/**
	 * This test makes sure that items are being inserted into the list properly(at the end) and that the boolean for linkedList and array are set to expected values.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void addTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("1");
		assertFalse(seq.getarrayUTD());
		assertTrue(seq.getlinkedUTD());
		seq.add("2");
		seq.get(1);
		assertEquals(seq.theArray, seq.toArray());
		seq.add("3");
		
		String[] expectedArray = {"1", "2", "3"};
		assertArrayEquals(expectedArray, seq.toArray());
		assertNotEquals(seq.theArray,seq.toArray());
	}
	/**
	 * this test checks that the addall method works properly. This is done by adding an arraylist containing items onto the end of seq list and that the boolean for linkedList and array are set to expected values.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void addAllTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		ArrayList<String> str = new ArrayList<String>();
		str.add("1");
		str.add("2");
		str.add("3");
		
		seq.addAll(str);
		String[] expectedArray = {"1", "2", "3"};
		assertFalse(seq.getarrayUTD());
		assertTrue(seq.getlinkedUTD());
		assertArrayEquals(expectedArray, seq.toArray());
		seq.clear();
		
		seq.add("this should be first");
		seq.get(0);
		assertEquals(seq.theArray, seq.toArray());
		seq.addAll(str);
		String[] expectedArray2 = {"this should be first", "1","2","3"};
		assertArrayEquals(expectedArray2, seq.toArray());
		assertNotEquals(seq.theArray,seq.toArray());
	}
	/**
	 * This methods tests the remove(obj) method. This is done by adding various objects then removing one and checking to see that the list changed properly. 
	 * It also makes sure and that the boolean for linkedList and array are set to expected values.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void removeObjectTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("1");
		seq.add("2");
		seq.add("3");
		seq.add("4");
		seq.add("5");
		seq.add("6");
		seq.get(1);
		assertEquals(seq.theArray, seq.toArray());
		seq.remove("4");
		String[] expectedArray = {"1", "2", "3", "5","6"};
		assertFalse(seq.getarrayUTD());
		assertTrue(seq.getlinkedUTD());
		assertArrayEquals(expectedArray, seq.toArray());
		assertNotEquals(seq.theArray,seq.toArray());
	}
	/**
	 * Similar to the add method, this test makes sure that we add the item at the inputed index. 
	 * It also makes sure that the boolean for linkedList and array are set to expected values.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void addPosTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("1");
		seq.add("2");
		seq.add("4");
		seq.add("5");
		seq.add("6");
		
		seq.add(2, "3");
		String[] expectedArray = {"1", "2", "3", "4", "5","6"};
		assertArrayEquals(expectedArray, seq.toArray());
		seq.add(0, "0");
		assertFalse(seq.getarrayUTD());
		assertTrue(seq.getlinkedUTD());
		String[] expectedArray2 = {"0", "1", "2", "3", "4", "5","6"};
		assertArrayEquals(expectedArray2, seq.toArray());
		seq.add(7, "7");
		seq.get(1);
		assertEquals(seq.theArray, seq.toArray());
		String[] expectedArray3 = {"0", "1", "2", "3", "4", "5","6","7"};
		assertArrayEquals(expectedArray3, seq.toArray());
		
		assertNotEquals(seq.theArray,seq.toArray());
	}
	/**
	 * This method makes sure that we can add a collection at the inputed index. This is tested by first adding items, then inserting an arraylist into the inputed index.
	 * It also makes sure that the boolean for linkedList and array are set to expected values.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void addAllPosTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		ArrayList<String> str = new ArrayList<String>();
		seq.add("0");
		seq.add("7");
		str.add("1");
		str.add("2");
		str.add("3");
		str.add("4");
		str.add("5");
		str.add("6");
		seq.get(1);
		assertEquals(seq.theArray, seq.toArray());
		String[] expectedArray = {"0", "7"};
		assertArrayEquals(expectedArray, seq.toArray());
		seq.addAll(1, str);
		String[] expectedArray2 = {"0", "1", "2", "3", "4", "5","6","7"};
		assertArrayEquals(expectedArray2, seq.toArray());
		assertFalse(seq.getarrayUTD());
		assertTrue(seq.getlinkedUTD());
		assertNotEquals(seq.theArray,seq.toArray());
	}
	/**
	 * Similar to remove(obj), this test makes sure we are able to remove the object at an index. This is tested by adding items to the list, then removing at the position. 
	 * It also makes sure that the boolean for linkedList and array are set to expected values.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void removePosTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("1");
		seq.add("2");
		seq.add("3");
		seq.add("4");
		seq.add("5");
		seq.add("6");
		seq.get(1);
		assertEquals(seq.theArray, seq.toArray());
		String[] expectedArray = {"1","2","4","5","6"};
		assertEquals("3", seq.remove(2));
		assertArrayEquals(expectedArray, seq.toArray());
		seq.remove(0);
		String[] expectedArray2 = {"2","4","5","6"};
		assertArrayEquals(expectedArray2, seq.toArray());
		seq.remove(seq.size()-1);
		String[] expectedArray3 = {"2","4","5"};
		assertArrayEquals(expectedArray3, seq.toArray());
		assertFalse(seq.getarrayUTD());
		assertTrue(seq.getlinkedUTD());
		assertNotEquals(seq.theArray,seq.toArray());
	}
	/**
	 * This method tests the functionality of the get method. This is tested by adding items, then calling get to see if it returns the proper object.
	 * It also makes sure that the boolean for linkedList and array are set to expected values.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("1");
		seq.add("2");
		seq.add("3");
		seq.add("4");
		seq.add("5");
		seq.add("6");
		assertEquals("1", seq.get(0));
		assertTrue(seq.getarrayUTD());
		assertTrue(seq.getlinkedUTD());
		seq.remove(0);
		assertEquals("2",seq.get(0));
		assertEquals(seq.theArray,seq.toArray());
	}
	/**
	 * This method is tested by adding items, then using the set method to change an object at the given index. This is tested by asserting that the array is updated and changed.
	 * It also makes sure that the boolean for linkedList and array are set to expected values.
	 */
	@Test
	public void setTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("1");
		seq.add("2");
		seq.add("3");
		seq.add("3");
		seq.add("5");
		seq.add("6");
		
		assertEquals("3", seq.set(3,"4"));
		assertTrue(seq.getarrayUTD());
		assertFalse(seq.getlinkedUTD());
		String[] expectedArray = {"1", "2", "3", "4", "5","6"};
		String[] expectedLinkedArray = {"1", "2", "3", "3", "5","6"};
		assertArrayEquals(expectedArray, seq.theArray);
		assertArrayEquals(expectedLinkedArray, seq.toArray());
		assertTrue(seq.getarrayUTD());
		assertFalse(seq.getlinkedUTD());
	}
	/**
	 * This test tests the reverse method. This is done by adding items, then calling the reverse method on them. We then assert that the array is equal to expected array.
	 * It also makes sure that the boolean for linkedList and array are set to expected values.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void reverseTest() {
		AdaptiveList<Integer> seq = new AdaptiveList<Integer>();
	seq.add(1);
	assertFalse(seq.reverse());
	seq.add(2);
	seq.add(3);
	seq.add(4);
	assertTrue(seq.reverse());
	assertTrue(seq.getarrayUTD());
	assertFalse(seq.getlinkedUTD());
	Integer[] expectedArray = {4,3,2,1};
	assertEquals(expectedArray, seq.theArray);
	assertNotEquals(seq.theArray,seq.toArray());
	}
	
	/**
	 * This test tests the ReOrderEvenOdd method. This is done by adding items, then calling the ReOrderEvenOdd method on them. 
	 * We then assert that the array is equal to expected array.
	 * It also makes sure that the boolean for linkedList and array are set to expected values.
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void reorderTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("1");
		assertFalse(seq.reorderOddEven());
		seq.add("2");
		seq.add("3");
		seq.add("4");
		seq.add("5");
		seq.add("6");
		assertTrue(seq.reorderOddEven());
		assertTrue(seq.getarrayUTD());
		assertFalse(seq.getlinkedUTD());
		String[] expectedArray = {"2", "1", "4", "3", "6","5"};
		assertEquals(expectedArray, seq.theArray);
		assertNotEquals(seq.theArray,seq.toArray());
		seq.clear();
		seq.add("0");
		seq.add("1");
		seq.add("2");
		seq.add("3");
		seq.add("4");
		seq.add("5");
		seq.add("6");
		seq.reorderOddEven();
		String[] expectedArray2 = {"1", "0", "3", "2", "5","4", "6"};
		assertEquals(expectedArray2, seq.theArray);
		}
	/**
	 * This tests checks to make sure that contain works properly. This is done by adding a bunch of objects into the AdaptiveList, then calling the contains method
	 * on an object that is included, and an object that is not included.
	 * It also makes sure that the boolean for linkedList and array are set to expected values.
	 */
	@Test
	public void containsTest() {
		AdaptiveList<Integer> seq = new AdaptiveList<Integer>();
	seq.add(1);
	seq.add(2);
	seq.add(3);
	seq.add(4);
	assertTrue(seq.contains(1));
	assertFalse(seq.contains(6));
	seq.set(2, 5);
	assertTrue(seq.getarrayUTD());
	assertFalse(seq.getlinkedUTD());
	}
	
	/**
	 * This tests checks to make sure that containAll works properly. This is done by adding a bunch of objects into the AdaptiveList, then calling the containAll method
	 * using an arrayList that contains multiple objects. This then checks whether each item is included, and then tests  an object that is not included.
	 * It also makes sure that the boolean for linkedList and array are set to expected values
	 */
	@Test
	public void containsAllTest() {
		AdaptiveList<Integer> seq = new AdaptiveList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.add(8);
		seq.add(9);
		seq.add(10);
	
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		c.add(7);
		assertTrue(seq.containsAll(c));
		c.add(0);
		assertFalse(seq.containsAll(c));
		seq.set(2, 5);
		assertTrue(seq.getarrayUTD());
		assertFalse(seq.getlinkedUTD());
	}
	
	/**
	 * This tests the indexOf method. This is done by adding items to the AdaptiveList, then using the method to get the index. It then calls the indexOf method to get the index
	 * that the first time that this object appears.
	 * It also makes sure that the boolean for linkedList and array are set to expected values
	 */
	@Test
	public void indexOfTest() {
		AdaptiveList<Integer> seq = new AdaptiveList<Integer>();
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(1);
		seq.add(6);
		seq.add(7);
		seq.add(8);
		seq.add(9);
		seq.add(10);
		
		assertEquals(0,seq.indexOf(1));
		assertEquals(-1, seq.indexOf(0));
		seq.set(2, 5);
		assertTrue(seq.getarrayUTD());
		assertFalse(seq.getlinkedUTD());
	}
	
	/**
	 * This test makes sure that the lastIndexOf method works properly. This is done by adding the same object into the AdaptiveList, and then calling the method.
	 * It is later asserted that index is the last time that object appears.
	 * It also makes sure that the boolean for linkedList and array are set to expected values
	 */
	@Test
	public void lastIndexOfTest() {
		AdaptiveList<Integer> seq = new AdaptiveList<Integer>();
		seq.add(3);
		seq.add(3);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.add(8);
		seq.add(3);
		seq.add(10);
		
		assertEquals(8, seq.lastIndexOf(3));
		assertEquals(-1, seq.lastIndexOf(0));
	}
	/**
	 * this test makes sure that the removeAll method works properly. This is done by adding objects into the AdaptiveList, and objects into the ArrayList
	 * The removeAll method is then called, and the list is then compared to expected values
	 * It also makes sure that the boolean for linkedList and array are set to expected values
	 */
	@Test
	public void removeAllTest() {
		AdaptiveList<Integer> seq = new AdaptiveList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(3);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.add(8);
		seq.add(9);
		seq.add(10);
	assertFalse(seq.removeAll(c));
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		c.add(7);
	assertTrue(seq.removeAll(c));
	Integer[] expectedArray = {1,2,8,9,10};
	assertArrayEquals(expectedArray,seq.toArray());
	assertNotEquals(seq.theArray, seq.toArray());
	assertFalse(seq.getarrayUTD());
	assertTrue(seq.getlinkedUTD());
	}
	/**
	 * This test makes sure that the retainAll method works properly. This is done by adding objects into the AdaptiveList, and objects into the ArrayList
	 * The retainAll method is then called, and the list is then compared to expected values
	 * It also makes sure that the boolean for linkedList and array are set to expected values
	 */
	@Test
	public void retainAllTest() {
		AdaptiveList<Integer> seq = new AdaptiveList<Integer>();
		ArrayList<Integer> c = new ArrayList<Integer>();
		
		c.add(3);
		c.add(4);
		c.add(5);
		c.add(6);
		c.add(7);
		c.add(-1);
		assertFalse(seq.retainAll(c));
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		seq.add(5);
		seq.add(6);
		seq.add(7);
		seq.add(8);
		seq.add(9);
		seq.add(10);
		
		assertFalse(seq.getarrayUTD());
		assertTrue(seq.getlinkedUTD());
		seq.get(1);
		assertTrue(seq.getarrayUTD());
		assertTrue(seq.retainAll(c));
	
		assertFalse(seq.retainAll(c));
		assertFalse(seq.getarrayUTD());
		assertTrue(seq.getlinkedUTD());
	}
/**
 * This test checks to make sure hasNext(), next(), and nextIndex() work properly. This is done by passing the iterator through a list, and checks each index to see if it has a next
 * checks to make sure it gets the right next object, and the next index.
 */
	@Test
	public void nextTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("A");
		seq.add("B");
		seq.add("C");
		ListIterator<String> i = seq.listIterator();
		assertEquals(0, i.nextIndex());
		assertTrue(i.hasNext());		
		assertEquals("A", i.next());
		assertEquals(1, i.nextIndex());
		assertTrue(i.hasNext());
		assertEquals("B", i.next());
		assertTrue(i.hasNext());
		assertEquals(2, i.nextIndex());
		assertEquals("C", i.next());
		assertFalse(i.hasNext());
		assertEquals(3, i.nextIndex());
	}
	/**
	 * This test checks to make sure hasPrevious(), previous(), and previousIndex() work properly. This is done by passing the iterator through a list, and checks 
	 * each index to see if it has a previous checks to make sure it gets the right previous object, and the previous index.
	 */
	@Test
	public void previousTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("A");
		seq.add("B");
		seq.add("C");
		ListIterator<String> i = seq.listIterator(3);
		assertTrue(i.hasPrevious());
		assertEquals(2, i.previousIndex());
		assertEquals("C", i.previous());
		assertTrue(i.hasPrevious());
		assertEquals(1, i.previousIndex());
		assertEquals("B", i.previous());
		assertTrue(i.hasPrevious());
		assertEquals(0, i.previousIndex());
		assertEquals("A", i.previous());
		assertFalse(i.hasPrevious());
		assertEquals(-1, i.previousIndex());
	}
	/**
	 * This test makes sure that we are able to remove using the iterator. This tests gets the next then remove until the array is empty.
	 */
	@Test
	public void removeIteratorTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("A");
		seq.add("B");
		seq.add("C");
		 ListIterator<String> it = seq.listIterator();
         int size = seq.size();
         Object[] array = seq.toArray();
        for (int i = 0; i < seq.size(); ++i) {
            assertEquals(array[i], it.next());
            assertEquals(i, size - seq.size());
            it.remove();
        }
	}
	/**
	 * This test makes sure that add for the iterator works properly. This test iterates through the Adaptive list, and adds 0 before each object.
	 */
	@Test
	public void addIteratorTest() {
		AdaptiveList<Integer> seq = new AdaptiveList<Integer>();
		seq.add(1);
		seq.add(2);
		seq.add(3);
		seq.add(4);
		 ListIterator<Integer> it = seq.listIterator();

        while (it.hasNext()) {
            it.add(0);
            it.next();
        }

         Integer[] expected = new Integer[] {
                0, 1, 0, 2, 0, 3, 0, 4
        };

        assertArrayEquals(expected, seq.toArray());
        }
	/**
	 * This tests makes sure that the set method for the iterator works properly. This is done by iterating through the Adaptive List and setting each element to Z.
	 */
	@Test
	public void setIteratorTest() {
		AdaptiveList<String> seq = new AdaptiveList<String>();
		seq.add("A");
		seq.add("B");
		seq.add("C");
		seq.add("A");
		  ListIterator<String> it = seq.listIterator();

	        while (it.hasNext()) {
	            it.next();
	            it.set("Z");
	        }

	        final String[] expected = new String[] {"Z", "Z", "Z", "Z"};

	        assertArrayEquals(expected, seq.toArray());
	}
}