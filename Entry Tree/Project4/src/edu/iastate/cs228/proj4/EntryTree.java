package edu.iastate.cs228.proj4;

/**
 * 
 * @author Abdalla Abdelrahman
 *
 *
 * An entry tree class.
 *
 *
 */
public class EntryTree<K, V> 
{
 // Dummy root node. 
 // Made public for grading.
 public Node root;
	
 /**
  * 
  * You are allowed to add at most TWO more data fields to 
  * EntryTree class of int type ONLY if you need to.
  *  
  */
 
 
 // All made public for grading.
 public class Node implements EntryNode<K, V> 
 {
  public Node child; // reference to the first child node
  public Node parent; // reference to the parent node
  public Node prev; // reference to the previous sibling
  public Node next; // reference to the next sibling
  public K key; // the key for this node
  public V value; // the value at this node

  public Node(K aKey, V aValue) 
  {
   key = aKey;
   value = aValue;
   child = null;
   parent = null;
   prev = null;
   next = null;
  }

  @Override
  public EntryNode<K, V> parent() 
  {
   // TODO
	  
   return this.parent;
  }

  @Override
  public EntryNode<K, V> child() 
  {
   // TODO
   return this.child;
  }

  @Override
  public EntryNode<K, V> next() 
  {
   // TODO
   return this.next;
  }

  @Override
  public EntryNode<K, V> prev() 
  {
   // TODO
   return this.prev;
  }

  @Override
  public K key() 
  {
   // TODO
   return this.key;
  }

  @Override
  public V value() 
  {
   // TODO
   return this.value;
  }
 }

 public EntryTree() 
 {
  root = new Node(null, null);
 }

 /**
  * Returns the value of the entry with a specified key sequence, 
  * or {@code null} if this tree contains no entry with this key 
  * sequence.
  * 
  * This method returns {@code null} if {@code keyarr} is null or 
  * if its length is {@code 0}. If any element of {@code keyarr} 
  * is {@code null}, then the method throws a 
  * {@code NullPointerException}. The method returns the value of 
  * the entry with the key sequence in {@code keyarr} or {@code null} 
  * if this tree contains no entry with this key sequence. An example 
  * is given in provided sample input and output files to illustrate 
  * this method.  
  *
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public V search(K[] keyarr) 
 {
  // TODO
	 Node temp = root;
	 Node cursor = null;
		 if(keyarr == null || keyarr.length == 0)
			 return null;
		 
		 for(int i = 0; i <= keyarr.length -1; i++) {
			 if(keyarr[i] == null)
				 throw new NullPointerException();
			 else {
				cursor = temp.child;
			
				 while(cursor != null && cursor.key != keyarr[i]) {
					 cursor = cursor.next;
				 }
				temp = cursor;
				
				if(temp == null)
					return null;
				 }
	
		 }

		  return temp.value;
 }

 /**
  * 
  * This method returns an array of type {@code K[]} with the longest 
  * prefix of the key sequence specified in {@code keyarr} such that 
  * the keys in the prefix label the nodes on the path from the root 
  * to a node. The length of the returned array is the length of the 
  * longest prefix.
  * 
  * This method returns {@code null} if {@code keyarr} is {@code null}, 
  * or if its length is {@code 0}. If any element of {@code keyarr} is
  * {@code null}, then the method throws a {@code NullPointerException}. 
  * A prefix of the array {@code keyarr} is a key sequence in the subarray 
  * of {@code keyarr} from index {@code 0} to any index {@code m>=0}, 
  * i.e., greater than or equal to; the corresponding suffix is a key
  * sequence in the subarray of {@code keyarr} from index {@code m+1} to
  * index {@code keyarr.length-1}. The method returns an array of type
  * {@code K[]} with the longest prefix of the key sequence specified in
  * {@code keyarr} such that the keys in the prefix are, respectively,
  * with the nodes on the path from the root to a node. The lngth of the
  * returned array is the length of the longest prefix. Note that if the
  * length of the longest prefix is {@code 0}, then the method returns 
  * {@code null}. This method can be used to select a shorted key sequence
  * for an {@code add} command to create a shorter path of nodes in the
  * tree. An example is given in the attachment to illustrate how this
  * method is used with the {@code #add(K[] keyarr, V aValue)} method.  
  * 
  * NOTE: In this method you are allowed to use 
  * {@link java.util.Arrays}'s {@code copyOf} method only.
  * 
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */

 public K[] prefix(K[] keyarr) 
 {
	// TODO
		 Node temp = root;
		 Node cursor = null;
		int size = 0;
		 if(keyarr == null || keyarr.length == 0)
			 return null;
		 
		 for(int i = 0; i <= keyarr.length -1; i++) {
			 if(keyarr[i] == null)
				 throw new NullPointerException();
		 }
		 for(int i = 0; i <= keyarr.length -1; i++) {				
				cursor = temp.child;
				
				 while(cursor != null && cursor.key != keyarr[i]) {
					 cursor = cursor.next;
				 }
				temp = cursor;
				if(temp == null)
					break;
				size++;
				 }
 if(size == 0)
	 return null;
		 K[] result = java.util.Arrays.copyOf(keyarr, size);
		  return result;
 }

 /**
  * 
  * This method returns {@code false} if {@code keyarr} is {@code null}, 
  * its length is {@code 0}, or {@code aValue} is {@code null}. If any 
  * element of {@code keyarr} is {@code null}, then the method throws a
  * {@code NullPointerException}.
  * 
  * This method locates the node {@code P} corresponding to the longest 
  * prefix of the key sequence specified in {@code keyarr} such that the 
  * keys in the prefix label the nodes on the path from the root to the node. 
  * If the length of the prefix is equal to the length of {@code keyarr}, 
  * then the method places {@code aValue} at the node {@code P} (in place of 
  * its old value) and returns {@code true}. Otherwise, the method creates a 
  * new path of nodes (starting at a node {@code S}) labelled by the 
  * corresponding suffix for the prefix, connects the prefix path and suffix 
  * path together by making the node {@code S} a child of the node {@code P}, 
  * and returns {@code true}. An example input and output files illustrate 
  * this method's operation.
  *
  * NOTE: In this method you are allowed to use 
  * {@link java.util.Arrays}'s {@code copyOf} method only.
  * 
  * @param keyarr Read description.
  * @param Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  */
 public boolean add(K[] keyarr, V aValue) 
 {
  // TODO
	 if(keyarr == null || keyarr.length == 0 || aValue == null)
		 return false;
	 
	 for(int i = 0; i <= keyarr.length -1; i++) {
		 if(keyarr[i] == null)
		
			 
			 throw new NullPointerException();
	 }
	
	
	 
	 
	 Node temp = root;
	
	 Node other;


	 	for(int i = 0; i <= keyarr.length - 1; i++) {
	 		 Node cursor = temp.child;
			 while(cursor != null && cursor.key != keyarr[i])
				 cursor = cursor.next;
			 if(cursor == null) {
				 cursor = new Node(keyarr[i], null);
				 cursor.parent = temp;
				 if(temp.child == null) {
					 cursor.parent.child = cursor;
				 }
				 else {
					 other = temp.child;
					 while(other.next != null) {
						 other = other.next;
					 }
					 
					cursor.prev = other;
					 other.next = cursor;
				 }
			 }
			 temp = cursor;
			 }
	 

			
		 
	 temp.value = aValue;
  return true;
 }

 /**
  * Removes the entry for a key sequence from this tree and returns its value
  * if it is present. Otherwise, it makes no change to the tree and returns
  * {@code null}.
  * 
  * This method returns {@code null} if {@code keyarr} is {@code null} or its
  * length is {@code 0}. If any element of {@code keyarr} is {@code null}, then
  * the method throws a {@code NullPointerException}. The method returns 
  * {@code null} if the tree contains no entry with the key sequence specified
  * in {@code keyarr}. Otherwise, the method finds the path with the key sequence,
  * saves the value field of the node at the end of the path, sets the value field
  * to {@code null}.
  * 
  * The following rules are used to decide whether the current node and higher
  * nodes on the path need to be removed. The root cannot be removed. Any node 
  * whose value is not {@code null} cannot be removed. Consider a non-root node 
  * whose value is {@code null}. If the node is a leaf node (has no children),
  * then the node is removed. Otherwise, if the node is the parent of a single
  * child and the child node is removed, then the node is removed. Finally, the
  * method returns the saved old value.
  * 
  * 
  * @param keyarr Read description.
  * @return Read description.
  * @throws NullPointerException Read description.
  *   
  */
 public V remove(K[] keyarr) 
 {
  // TODO
	 Node temp = root;
	 Node cursor;
	int i;
	 V result = search(keyarr);
	 if(keyarr == null || keyarr.length == 0)
		 return null;
	 
	 for( i = 0; i <= keyarr.length -1; i++) {
		 if(keyarr[i] == null)
			 throw new NullPointerException();
		
		 }
	 i = 0;
	 while( i <= keyarr.length -1) {
		 for(cursor = temp.child; cursor != null && cursor.key != keyarr[i]; cursor = cursor.next) {
		 }
		
		 temp = cursor;
		 if(temp == null)
			 return null;
		 i++;
	 }
	 




	 temp.value = null;
	 removeHelper(temp);
	 return result;
 }
 /**This method helps the remove method by searching if the child has a next, and if it does it will reassign the next to be the child (if the child needs to be removed). It will also the
  *  
  * @param nodeSearch The node at which you want to start searching at.
  */
 private void removeHelper(Node nodeSearch) {
	 

	 
	 if(nodeSearch.child == null && nodeSearch.value == null) {
			if(nodeSearch.parent != null && nodeSearch.parent.child == nodeSearch) {
				nodeSearch.parent.child = nodeSearch.next;
				Node secondNode = nodeSearch.parent;
				removeHelper(secondNode);
			}
			if(nodeSearch.prev == null)
				return;
			else
				nodeSearch.prev.next = nodeSearch.next;
			if(nodeSearch.next == null)
				return;
			else
				nodeSearch.next.prev = nodeSearch.prev;
			
			
			
	 }
 }

 /**
  * 
  * This method prints the tree on the console in the output format 
  * shown in provided sample output file. If the tree has no entry, 
  * then the method just prints out the line for the dummy root node.
  * 
  */
 public void showTree() 
 {
  // TODO
	 System.out.print(root.key() + "::" + root.value());
	 System.out.println();
	 showTreeHelper(1,root.child);
 }
 
 /**
  * This helper method helps the showTree method by iterating through the height of the tree, and adds two dots * the level in the tree. It then adds four dots for every line.
  * This method will go down the list, and print out how the tree is supposed to look. This method will (with implementation of showTree()) will start at the root child.
  * @param height The height at which you start adding dots to the tree.
  * @param entryNode The node at which you want to start at.
  */
 private void showTreeHelper(int height, EntryNode<K, V> entryNode) {
	 int i;
	 
	for(; entryNode != null; entryNode = entryNode.next()) {
		 i = 0;
		for(;  i < height; i++) {
			 System.out.print("..");
			
		 }
		 
		 if(entryNode != root) {
		
			 System.out.print("...."+entryNode.key()+"::" + entryNode.value());
			 System.out.println();
			 
		 }
		 
		 showTreeHelper(height+1,entryNode.child());
		 
	 }
 }
 
 /**
  * 
  * Returns all values in this entry tree together with their keys.
  * The order of outputs would be similar to level order traversal,
  * i.e., first you would get all values together with their keys in
  * first level from left to right, then second level, and so on.
  * If tree has no values then it would return {@code null}.
  *
  * For the example image given in description, the 
  * returned String[][] would look as follows:
  * 
  *  {{"IA","Grow"}, {"ISU","CS228"}}   
  * 
  * NOTE: In this method you are allowed to use 
  * {@link java.util.LinkedList}.
  * 
  *  
  */	
 /**
  * This method is a recursive method in which it  helps the getAll method by iterating through the tree, and adding the children of each node. 
  * @param leaves This a Linked list that will contain the children of each node. 
  * @param startingNode This will be node that which we will start searching
  */
private void leavesHelper(java.util.LinkedList<Node> leaves, EntryNode<K, V> startingNode) {
	if(startingNode != null) {
		startingNode = startingNode.child();
		for(; startingNode != null; startingNode = startingNode.next()) {
			leaves.add((EntryTree<K, V>.Node) startingNode);
			Node newStart = (EntryTree<K, V>.Node) startingNode;
			leavesHelper(leaves, newStart);
		}
	}
	else
		return;
}
 public String[][] getAll()
 {
	 
  // TODO
	 if(root.child == null)
		 return null;
	 
	 String key = "";
	 int i = 0;
	 java.util.LinkedList<Node> leaves = new java.util.LinkedList<Node>();
	 leavesHelper(leaves,root);
	 java.util.LinkedList<Node> parents = new java.util.LinkedList<Node>();

	 while(i <= leaves.size()-1) {
		 if(leaves.get(i).value() != null)
			 parents.add(leaves.get(i));
		 i++;
	 }
	 
	 String[][] result = new String[parents.size()][2];
	 i = 0;
	 while(i <= parents.size()-1) {
		 Node cursor = parents.get(i);
		 V val = cursor.value;
		 key = "";
		 while(cursor.parent != null) {
			 key = cursor.key + key;
			 cursor = cursor.parent;
		 }
		 result[i][0] = key;
		 result[i][1] = (String) val;
		 i++;
	 }

  return result;
 }
}
