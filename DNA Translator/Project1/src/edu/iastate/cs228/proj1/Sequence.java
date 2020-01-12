package edu.iastate.cs228.proj1;

/**
 * 
 * @author Abdalla Abdelrahman
 *
 */

public class Sequence
{
  protected char[] seqarr;

  /**
   The constructor first uses the {@link #isValidLetter(char)} method to check
   if every character in the array {@code sarr} is valid. If so, it makes and
   keeps a copy of the array {@code sarr} in the field {@code seqarr} of type {@code char[]} with {@code protected} access. Otherwise, it throws an 
  {@link java.lang.IllegalArgumentException} with the message {@code "Invalid sequence letter for class X"} where {@code X} denotes 
 {@code  'edu.iastate.cs228.proj1.Sequence'} or the name of a subclass of which an object is created. Examples are given in the project description page to 
   illustrate what exactly is denoted by {@code X} after the subclasses of class
   {@code Sequence} are defined. Note that the length of the field {@code seqarr} is equal to the length of the array {@code sarr}. 
   This constructor should be implemented in such a way that it is unnecessary to have more than one line of code in the body of the constructor
    of any subclass of this class.
   
   @param sarr See {@link #Sequence(char[])}.
   @throws IllegalArgumentException See {@link #Sequence(char[])}.
  */  
  public Sequence(char[] sarr)
  {
    // TODO
	  seqarr = new char[sarr.length];

	  for(int i = 0; i < sarr.length;i++) {				//for the length of the given array
		  if(!isValidLetter(sarr[i])) {					//if it is not a valid letter
			  throw new IllegalArgumentException("Invalid sequence letter for class" + this.getClass().getName());  //IllegalArgumentException is thrown
		  }
		   seqarr[i] = sarr[i]; // copies the given array to seqarr
	  }
  }

  
  /**
   The method returns the length of the character array {@code seqarr}.
   @return See {@link #seqLength()}.
  */
  public int seqLength()
  {
	  
	return seqarr.length; //returns the length of seqarr
    // TODO
  }
  
  /**
   The method creates and returns a copy of {@code char} array {@code seqarr}.
   @return See {@link #getSeq()}.
  */
  public char[] getSeq() {
  char[] seqarrCopy = new char[seqarr.length]; //initializes a copy array
  for(int i = 0; i < seqarr.length; i++) { //for all values in the seqarr array
	  seqarrCopy[i] = seqarr[i]; //the copy array copies it
  }
  
	return seqarrCopy; // returns the copy array
    // TODO
  }

  /**
   The method returns the string representation of the {@code char} array {@code seqarr}.
   @return See {@link #toString()}.
  */
  public String toString()
  {
	  String s = "";
	  for(int i = 0; i < seqarr.length; i++) { //adds the current character to the string
		  s += seqarr[i];
	  }
	return s; // returns the string version of the array
    // TODO
  }

  /**
   The method returns {@code true} if the arguments {@code obj} is not {@code null}
    and is of the same type as this object such that both objects represent the identical sequence of characters
     in a case insensitive mode ("ACgt" is identical to "AcGt"). The {@link #equals(Object)} method should be 
     defined in such a way that there is no need to define it again in any subclass of class {@code Sequence}. 
    In other words, when an object of the subclass calls the {@link #equals(Object)} method, 
    it should return the right answer.
   
   @return See {@link #equals(Object)}
  */
  public boolean equals(Object obj)
  {
	  // TODO
	  
	  char[] a = (char[]) obj; 	//casts the object to a type of char array
	  char[] b = this.getSeq(); //gets the array of this
	  if(obj == null) { 		//if the object is null 
		  return false;			// it will return false
	  }
	  if(a.length == b.length) {		//if the two lengths are the same
		  for(int i = 0; i< a.length;i++) { //it will check if the two char arrays have the same charactars in order.
			  a[i] = Character.toLowerCase(a[i]);
			  b[i] = Character.toLowerCase(b[i]); 	//case insensitive
			  if(a[i]!=b[i]) // if they are not the same character
				  return false;	// it will return false		
		  }
		  }
	  else
		  return false; //if the two lengths are not the same it will fail
			  
	  
	return true; //if everything passes it will return true
  
  }

  
  /**
   The method returns {@code true} if the character {@code let} is an 
   uppercase (e.g., invoking {@link java.lang.Character#isUpperCase(char)} with {@code let} is {@code true}) or lowercase 
   ((e.g., invoking {@link java.lang.Character#isLowerCase(char)} with {@code let} is {@code true})). Otherwise,
it returns {@code false}.
   @param let See {@link #isValidLetter(char)}.
   @return See {@link #isValidLetter(char)}.
  */
  public boolean isValidLetter(char let)
  {
	  if(Character.isUpperCase(let) || Character.isLowerCase(let)) // if the character is uppercase or lower case
		  return true;		//it will pass
	  else
		  return false; 		//otherwise false
    // TODO
  }

}
