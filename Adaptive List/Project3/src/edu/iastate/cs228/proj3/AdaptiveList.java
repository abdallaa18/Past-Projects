package edu.iastate.cs228.proj3;

import java.lang.reflect.Array;
import java.util.Arrays;

/*
 *  @author
 *
 *
 *  An implementation of List<E> based on a doubly-linked list 
 *  with an array for indexed reads/writes
 *
 */

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Objects;
/**
 * 
 * @author Abdalla Abdelrahman

 */




public class AdaptiveList<E> implements List<E>
{
  public class ListNode 
  {                     
    public E data;        
    public ListNode next; 
    public ListNode prev; 
    
    public ListNode(E item)
    {
      data = item;
      next = prev = null;
    }
  }
  
  public ListNode head;  // dummy node made public for testing.
  public ListNode tail;  // dummy node made public for testing.
  private int numItems;  // number of data items
  private boolean linkedUTD; // true if the linked list is up-to-date.

  public E[] theArray;  // the array for storing elements
  private boolean arrayUTD; // true if the array is up-to-date.

  public AdaptiveList()
  {
    clear();
  }

  @Override
  public void clear()
  {
    head = new ListNode(null);
    tail = new ListNode(null);
    head.next = tail;
    tail.prev = head;
    numItems = 0;
    linkedUTD = true;
    arrayUTD = false;
    theArray = null;
  }

  public boolean getlinkedUTD()
  {
    return linkedUTD;
  }

  public boolean getarrayUTD()
  {
    return arrayUTD;
  }

  public AdaptiveList(Collection<? extends E> c)
  
  {
	  this();
	  for(E e : c) {
		  add(e);
	  }
    // TODO
	  if( c == null)
		  throw new NullPointerException();
	  
	  theArray = (E[]) c.toArray();
  }

  // Removes the node from the linked list.
  // This method should be used to remove a node 
  // from the linked list.
  private void unlink(ListNode toRemove)
  {
    if ( toRemove == head || toRemove == tail )
      throw new RuntimeException("An attempt to remove head or tail");
    toRemove.prev.next = toRemove.next;
    toRemove.next.prev = toRemove.prev;
  }

  // Inserts new node toAdd right after old node current.
  // This method should be used to add a node to the linked list.
  private void link(ListNode current, ListNode toAdd)
  {
    if ( current == tail )
      throw new RuntimeException("An attempt to chain after tail");
    if ( toAdd == head || toAdd == tail )
      throw new RuntimeException("An attempt to add head/tail as a new node");
    toAdd.next = current.next;
    toAdd.next.prev = toAdd;
    toAdd.prev = current;
    current.next = toAdd;
  }

  @SuppressWarnings("unchecked")
private void updateArray() // makes theArray up-to-date.
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! linkedUTD )
      throw new RuntimeException("linkedUTD is false");
    // TODO
theArray = (E[]) toArray();
arrayUTD = true;


  }

  private void updateLinked() // makes the linked list up-to-date.
  {
    if ( numItems < 0 )
      throw new RuntimeException("numItems is negative: " + numItems);
    if ( ! arrayUTD )
      throw new RuntimeException("arrayUTD is false");

    if ( theArray == null || theArray.length < numItems )
      throw new RuntimeException("theArray is null or shorter");

    // TODO
    ListNode firstNode = head.next;
    int i = 0;
while(firstNode.next != null) {
	firstNode.data= theArray[i];
	firstNode = firstNode.next;
	i++;
}
linkedUTD = true;
  }
  
  @Override
  public int size()
  {
    // TODO
	  if(!linkedUTD)
		  updateLinked();
	  if(numItems > Integer.MAX_VALUE) {
		  return Integer.MAX_VALUE;
	  }
    return numItems;
  }

  @Override
  public boolean isEmpty()
  {
    // TODO
	  if(!linkedUTD)
		  updateLinked();
    return head.next == tail; 
  }

  @Override
  public boolean add(E obj)
  {
    // TODO
	  if(!linkedUTD)
		  updateLinked();
	  if(obj == null)
		  throw new NullPointerException();
	  if(numItems == 0) {
	  ListNode toAdd = new ListNode(obj);
  
	  link(head, toAdd);
	numItems++;
	arrayUTD = false;
    return true; 
	  }
	  else {
		  ListNode toAdd = new ListNode(obj);
		  
		  link(tail.prev, toAdd);
		  numItems++; 
			arrayUTD = false;
		  return true; 
	  }
  }

  @Override
  public boolean addAll(Collection< ? extends E> c)
  {
    // TODO
	  if(!linkedUTD)
		  updateLinked();
	  if(c == null || c.contains(null))
		  throw new NullPointerException();
	  if(c.isEmpty())
		  return false;
	  for(E e : c) {
		  add(e);
	  }
	  arrayUTD = false;
    return true; 
  }

  @SuppressWarnings("null")
@Override
  public boolean remove(Object obj)
  {
    // TODO
	  if(!linkedUTD)
		  updateLinked();
	  if(obj == null)
		  throw new NullPointerException();
	  ListNode prev, cur;
	  for(prev = null, cur = head; cur != null; prev = cur, cur = cur.next)
		  if(obj == cur.data || obj != null && obj == cur.data)
			  break;
	  
	  if(cur == null)
		  return false;
	  if(prev != null)
		  prev.next = cur.next;
	  else
		  head = head.next;
	  numItems--;
	
	  arrayUTD = false;
    return true;
  }

  private void checkIndex(int pos) // a helper method
  {
    if ( pos >= numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkIndex2(int pos) // a helper method
  {
    if ( pos > numItems || pos < 0 )
     throw new IndexOutOfBoundsException(
       "Index: " + pos + ", Size: " + numItems);
  }

  private void checkNode(ListNode cur) // a helper method
  {
    if ( cur == null || cur == tail )
     throw new RuntimeException(
      "numItems: " + numItems + " is too large");
  }

  private ListNode findNode(int pos)   // a helper method
  {
    ListNode cur = head;
    for ( int i = 0; i < pos; i++ )
    {
      checkNode(cur);
      cur = cur.next;
    }
    checkNode(cur);
    return cur;
  }

  @Override
  public void add(int pos, E obj)
  {
    // TODO
	  if(obj == null)
		  throw new NullPointerException();
	  checkIndex2(pos);
	  if(!linkedUTD)
		  updateLinked();
	
	  ListNode temp = new ListNode(obj);
	  ListNode prev = findNode(pos);
	  link(prev, temp);
	  numItems++;
	  
	  arrayUTD = false;
  }

  @Override
  public boolean addAll(int pos, Collection< ? extends E> c)
  {
    // TODO
	  if(!linkedUTD)
		  updateLinked();
checkIndex2(pos);
if(c == null || c.contains(null))
	throw new NullPointerException();
if(c.isEmpty())
	return false;
int i = pos;
for(E e: c) {
	if(e == null)
		  throw new NullPointerException();
	add(i, e);
	i++;
}
arrayUTD = false;
return true;
  }

  @Override
  public E remove(int pos)
  {
    // TODO
	  if(!linkedUTD)
		  updateLinked();
	  checkIndex(pos);
	  ListNode theNode = findNode(pos);
	  ListNode firstNode = head;
	
	  while(firstNode != null) {
		  if(firstNode == theNode) {
			  tail.prev = firstNode;
			  theNode = firstNode.next;
			  remove(firstNode.next.data);
		  }
		  firstNode = firstNode.next;
	  }
	
	  arrayUTD = false;
   return theNode.data;
     
  }

  @Override
  public E get(int pos)
  {
    // TODO
	  checkIndex(pos);
	  if(!arrayUTD) 
		  updateArray();
		  
	  checkIndex(pos);
	  ListNode cur = findNode(pos+1);
	  
    return cur.data; 
  }

  @Override
  public E set(int pos, E obj)
  {
    // TODO
	  if(obj == null)
		  throw new NullPointerException();
	  checkIndex(pos);
	  E result = null;
	  if(!arrayUTD) 
		  updateArray();
	for(int i = 0; i < numItems; i++ ) {
		if(i == pos) {
			result = theArray[i];
			theArray[i] = obj;
		}
	}
	linkedUTD = false;
    return result; 
  } 

  /**
   *  If the number of elements is at most 1, 
   *  the method returns false. Otherwise, it 
   *  reverses the order of the elements in the 
   *  array without using any additional array, 
   *  and returns true. Note that if the array 
   *  is modified, then linkedUTD needs to be set 
   *  to false.
   */
  public boolean reverse()
  {
    // TODO
	  if(!arrayUTD) 
		  updateArray();
	  if(numItems <= 1)
		  return false;
	  
	  
	  E temp;

	  for (int i = 0; i < numItems/2; i++)
	    {
	       temp = theArray[i];
	       theArray[i] = theArray[numItems-1 - i];
	       theArray[numItems-1 - i] = temp;
	    }
	  linkedUTD = false;
    return true;
  }

  
  /** 
   *  If the number of elements is at most 1, 
   *  the method returns false. Otherwise, it 
   *  swaps the items positioned at even index 
   *  with the subsequent one in odd index without 
   *  using any additional array, and returns true.
   *  Note that if the array is modified, then 
   *  linkedUTD needs to be set to false. 
   */
  public boolean reorderOddEven()
  {
    // TODO
	  
	  if(!arrayUTD) 
		  updateArray();
	  if(numItems <= 1)
		  return false;
	  for(int i = 0; i < numItems-1; i = i+2) {
		
		  E temp = theArray[i];
		  theArray[i] = theArray[i+1];
		  theArray[i+1] = temp;
		  
	  }
	  linkedUTD = false;
    return true;
  }
  
  @Override
  public boolean contains(Object obj)
  {
    // TODO
	  if(!linkedUTD)
		  updateLinked();
	  if(obj == null)
		  throw new NullPointerException();
	  ListNode cur;
	  for(cur = head; cur!= null; cur = cur.next) {
		  if(obj == cur.data || obj != null && obj.equals(cur.data))
			  return true;
	  }
	  return false;
  }

  @Override
  public boolean containsAll(Collection< ? > c)
  {
    // TODO
	  if(!linkedUTD)
		  updateLinked();
	  if(c == null || c.contains(null))
		  throw new NullPointerException();
	  for(Object o : c)
		  if(!contains(o))
			  return false;
   return true; 
  }


  @Override
  public int indexOf(Object obj)
  {
    // TODO
	  
	  if(obj == null)
		  throw new NullPointerException();
	  if(!linkedUTD)
		  updateLinked();
	  ListNode cur;
	  int pos = 0;
	  for(cur = head.next; cur != null; cur = cur.next, pos++) {
		  if(obj == cur.data || obj != null && obj.equals(cur.data))
			  return pos;
	  }
    return -1; 
  }

  @Override
  public int lastIndexOf(Object obj)
  {
    // TODO
	  if(obj == null)
		  throw new NullPointerException();
	  if(!linkedUTD)
		  updateLinked();
	  if(!contains(obj))
		  return -1;
	  else {
	  ListIterator<E> iter = listIterator(numItems);
	  while(iter.hasPrevious()) {
		E data = iter.previous();
		if(obj == data || obj != null && obj.equals(data))
				return iter.nextIndex();
	  }
	  }
	return -1;
  }

  @Override
  public boolean removeAll(Collection<?> c)
  {
    // TODO
	  if(c == null || c.contains(null))
		  throw new NullPointerException();
	  boolean changed = false;
	  if(!linkedUTD)
		  updateLinked();

	  for(Object e : this.toArray()) {
		  if(c.contains(e)) {
			  this.remove(e);
			changed = true;
			arrayUTD = false;
		  }
	  }
	 
	  return changed;
  }

  @Override
  public boolean retainAll(Collection<?> c)
  {
    // TODO
	  if(c == null || c.contains(null))
		  throw new NullPointerException();	  
	  if(!linkedUTD)
		  updateLinked();
	  ListNode current = head.next;
	  boolean changed = false;
	  while(current != tail) {
		  if(!c.contains(current.data)) {
			  remove(current.data);
			  changed = true;
			  arrayUTD = false;
		  }
		  current = current.next;
	  }
	  return changed;
  }

  @Override
  public Object[] toArray()
  {
    // TODO

	    E[] result = (E[])new Object[numItems]; // Unchecked cast
	      int index = 0;
	      ListNode currentNode = head.next;
	      while ((index < numItems) && (currentNode != null))
	      {
	         result[index] = currentNode.data;
	         index++;
	         currentNode = currentNode.next;
	      } // end while
	      	
			return result;
  }
  
  
  /**
   * In here you are allowed to use only 
   * java.util.Arrays.copyOf method.
   */
  @Override
  public <T> T[] toArray(T[] arr)
  {
    //TODO
	  if(!linkedUTD)
		  updateLinked();
	  if(arr == null) {
		  throw new NullPointerException();
	  }
	  if(arr.length < numItems)
	  arr = Arrays.copyOf(arr, numItems);
	  for(int i = 0; i < numItems; i++) {
		  arr[i] = (T) this.toArray()[i];
	  }
	  if(arr.length > numItems)
		  arr[numItems] = null;
	  return arr;
  }

  @Override
  public List<E> subList(int fromPos, int toPos)
  {
    throw new UnsupportedOperationException();
    //
  }

  private class AdaptiveListIterator implements ListIterator<E>
  {
    private int    index;  // index of next node;
    private ListNode cur;  // node at index - 1
    private ListNode last; // node last visited by next() or previous()
    

    public AdaptiveListIterator()
    {
      if ( ! linkedUTD ) updateLinked();
      // TODO
  last = null;
  cur = findNode(0);
  index = 0;
    }
    public AdaptiveListIterator(int pos)
    {
      if ( ! linkedUTD ) updateLinked();
      // TODO
     checkIndex2(pos);
     index = pos;
     last = null;
     if(pos == 0)
    	 cur = null;
     else 
    	 cur = findNode(pos);
    }

    @Override
    public boolean hasNext()
    {
      // TODO
    	 if(!linkedUTD)
   		  updateLinked();
    	if (index >= numItems)
    		return false;
    	else
    		return true;
    }

    @Override
    public E next()
    {
      //TODO
    	if(!hasNext())
    		throw new NoSuchElementException();
    	if(index > numItems)
    		throw new RuntimeException();
    	 if(!linkedUTD)
   		  updateLinked();
      index++;
      if(cur == null)
    	  last = cur = head.next;
      else
    	  last = cur = cur.next;
      return cur.data;
    } 
    /**
     * Searches through the list, until the the node is found.
     * @param node The node that will be looked for
     * @return ListNote of the node that is found
     */
    private ListNode lookPrev(ListNode node) 
    {
    	ListNode tmp;
    	if (node == head)
    		return null;
    	for (tmp = head; tmp.next != node; tmp = tmp.next)
    		if (tmp.next == null)
    			throw new RuntimeException("node not on list");
    	return tmp;
    	}
    
    @Override
    public boolean hasPrevious()
    {
      // TODO
    	 if(!linkedUTD)
   		  updateLinked();
      return index > 0; 
    }

    @Override
    public E previous()
    {
      // TODO
    	 if(!linkedUTD)
   		  updateLinked();
    	if (!hasPrevious())
    		throw new NoSuchElementException();
    	if (index <= 0)
    		throw new RuntimeException("index 2");
    	index--;
    	last = cur;
    	cur = lookPrev(cur);
    	return last.data;
    	
    }
    
    @Override
    public int nextIndex()
    {
      // TODO
    	 if(!linkedUTD)
   		  updateLinked();
    	if(index >= numItems)
    		return numItems;
    	else
      return index; 
    }

    @Override
    public int previousIndex()
    {
      // TODO
    	 if(!linkedUTD)
   		  updateLinked();
    	if(index < 0)
    		return -1;
    	else
      return index - 1; 
    }

    @Override
    public void remove()
    {
      // TODO
    	 if(!linkedUTD)
   		  updateLinked();
    	if (last == null) 
    		throw new IllegalStateException();
    	if (cur == last){
    		if (index <= 0)
    			throw new RuntimeException();
    		index--;
    		} 
    	if (last == head)
    	{
    		if (head == null)
        		throw new NoSuchElementException();
        	head = head.next;
        	numItems--;
    		cur = null;
    		}
    	else{
    		numItems--;
    		if (cur == last)
    			cur = lookPrev(cur);
    		cur.next = last.next;
    		}
    	last = null;
    	arrayUTD = false;
    	}

    @Override
    public void add(E obj)
    { 
      // TODO
    	 if(!linkedUTD)
   		  updateLinked();
    	if (cur == null){
    		ListNode toAdd = new ListNode(obj);
        	toAdd.data = obj;
        	toAdd.next = head;
        	head = toAdd;
        	numItems++;
    		last = null;
    		cur = head;
    		index = 1;
    		return;
    		}
    	ListNode toAdd = new ListNode(obj);
    	toAdd.data = obj;
    	toAdd.next = cur.next;
    	cur.next = toAdd;
    	cur = toAdd;
    	last = null;
    	index++;
    	numItems++;
    	arrayUTD = false;
    } 

    @Override
    public void set(E obj)
    {
      // TODO
    	if (last == null)
    		throw new IllegalStateException();
    	last.data = obj;
    }
    
  } // AdaptiveListIterator
  
  @Override
  public boolean equals(Object obj)
  {
    if ( ! linkedUTD ) updateLinked();
    if ( (obj == null) || ! ( obj instanceof List<?> ) )
      return false;
    List<?> list = (List<?>) obj;
    if ( list.size() != numItems ) return false;
    Iterator<?> iter = list.iterator();
    for ( ListNode tmp = head.next; tmp != tail; tmp = tmp.next )
    {
      if ( ! iter.hasNext() ) return false;
      Object t = iter.next();
      if ( ! (t == tmp.data || t != null && t.equals(tmp.data) ) )
         return false;
    }
    if ( iter.hasNext() ) return false;
    return true;
  }

  @Override
  public Iterator<E> iterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator()
  {
    return new AdaptiveListIterator();
  }

  @Override
  public ListIterator<E> listIterator(int pos)
  {
    checkIndex2(pos);
    return new AdaptiveListIterator(pos);
  }

  // Adopted from the List<E> interface.
  @Override
  public int hashCode()
  {
    if ( ! linkedUTD ) updateLinked();
    int hashCode = 1;
    for ( E e : this )
       hashCode = 31 * hashCode + ( e == null ? 0 : e.hashCode() );
    return hashCode;
  }

  // You should use the toString*() methods to see if your code works as expected.
  @Override
  public String toString()
  {
   // Other options System.lineSeparator or
   // String.format with %n token...
   // Not making data field.
   String eol = System.getProperty("line.separator");
   return toStringArray() + eol + toStringLinked();
  }

  public String toStringArray()
  {
    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent array:" + eol );
    strb.append('[');
    if ( theArray != null )
      for ( int j = 0; j < theArray.length; )
      {
        if ( theArray[j] != null )
           strb.append( theArray[j].toString() );
        else
           strb.append("-");
        j++;
        if ( j < theArray.length )
           strb.append(", ");
      }
    strb.append(']');
    return strb.toString();
  }

  public String toStringLinked()
  {
    return toStringLinked(null);
  }

  // iter can be null.
  public String toStringLinked(ListIterator<E> iter)
  {
    int cnt = 0;
    int loc = iter == null? -1 : iter.nextIndex();

    String eol = System.getProperty("line.separator");
    StringBuilder strb = new StringBuilder();
    strb.append("A sequence of items from the most recent linked list:" + eol );
    strb.append('(');
    for ( ListNode cur = head.next; cur != tail; )
    {
      if ( cur.data != null )
      {
        if ( loc == cnt )
        {
          strb.append("| ");
          loc = -1;
        }
        strb.append(cur.data.toString());
        cnt++;

        if ( loc == numItems && cnt == numItems )
        {
          strb.append(" |");
          loc = -1;
        }
      }
      else
         strb.append("-");
      
      cur = cur.next;
      if ( cur != tail )
         strb.append(", ");
    }
    strb.append(')');
    return strb.toString();
  }
}
