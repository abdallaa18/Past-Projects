package edu.iastate.cs228.proj4;

import java.util.*;
import java.io.*;

/**
 * @author 
 * 
 * 
 * An application class that uses tree class to process a file of
 * commands (one per line). Each command line consists of the name of
 * a public method of the tree class followed by its arguments in
 * string form if the method has arguments. The name of the file is 
 * available to the main method from its String[] parameter at index 0.
 * You can assume that the command file is always in correct format. The 
 * main method creates an object of the tree class with K being 
 * Character and V being String, reads each line from the command file, 
 * decodes the line by splitting into String parts, forms the corresponding 
 * arguments, and calls the public method from the tree object with 
 * the arguments, and prints out the result on the console. Note that the 
 * name of a public method in the tree class on each command line 
 * specifies that the public method should be called from the tree 
 * object. A sample input file of commands and a sample output file are 
 * provided. 
 * 
 * The sample output file was produced by redirecting the console output
 * to a file, i.e.,
 * 
 * java Dictionary infile.txt > outfile.txt
 *  
 * 
 * NOTE that all methods of tree class can be present as commands
 * except for getAll method inside the input file.
 * 
 * 
 */

public class Dictionary 
{

 public static void main(String[] args) 
 {
  // TODO
	 
	 EntryTree<Character, String> tree = new EntryTree<Character, String>();
	 FileReader file = null;
	 try {
		 file = new FileReader(args[0]);
	 } catch(FileNotFoundException e) {
	 System.out.println("The file was not found");
	 }
	 
	 Scanner s = new Scanner(file);
	 while(s.hasNextLine()) {
		 String str;
		 str = s.nextLine();
		 String[] strArray = str.split("\\s+");
		 if(strArray[0].equals("showTree")) {
			 System.out.println("Command: " + strArray[0]);
			 System.out.println("Result from showTree: ");
			 tree.showTree() ;
			
		 }
		 else if(strArray[0].equals("add")) {
			 System.out.println("Command: " + strArray[0] + " " + strArray[1] + " " + strArray[2]);
			 char[] b = strArray[1].toCharArray();
			 Character[] a = new Character[b.length];
			 		for(int i = 0; i <= b.length-1; i++) {
			 			a[i] = b[i];
			 		}
			 System.out.println("Result from add: " + tree.add(a, strArray[2]));
		
		 }
		 else if(strArray[0].equals("search")) {
			 System.out.println("Command: " + strArray[0] + " " + strArray[1]);
			 char[] b = strArray[1].toCharArray();
			 Character[] a = new Character[b.length];
			 		for(int i = 0; i <= b.length-1; i++) {
			 			a[i] = b[i];
			 		}
			 System.out.println("Result from search: " + tree.search(a));
		
		 }
		 else if(strArray[0].equals("prefix")) {
			 System.out.println("Command: " + strArray[0] + " " + strArray[1]);
			 char[] b = strArray[1].toCharArray();
			 Character[] a = new Character[b.length];
			 		for(int i = 0; i <= b.length-1; i++) {
			 			a[i] = b[i];
			 		}
			 System.out.println("Result from prefix: " + Arrays.toString(tree.prefix(a)));
		
		 }
		 else if(strArray[0].equals("remove")) {
			 System.out.println("Command: " + strArray[0] + " " + strArray[1]);
			 char[] b = strArray[1].toCharArray();
			 Character[] a = new Character[b.length];
			 		for(int i = 0; i <= b.length-1; i++) {
			 			a[i] = b[i];
			 		}
			 System.out.println("Result from remove: " + tree.remove(a));
		
		 }
	 }
	 s.close();
 }

}
