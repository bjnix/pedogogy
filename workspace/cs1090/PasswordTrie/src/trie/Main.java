package trie;
import java.io.*;

public class Main {

	/**
	 *  Main program for testing TRIE implementation
	 *  
	 *  This is not an end all test, you should be able to handle special cases with nulls, etc.
	 * @param PasswordTrie 
	 * @throws IOException 
	 *  
	 */
	
	public static void main(String[] args) throws IOException 
	{
		PasswordTrieInterface theTree = new PasswordTrie();
		
		//Test File input
		theTree.readInFile("input.txt");
		theTree.printPasswords();
		
		//Test Check Negative
		if(theTree.checkPassword("sadness"))
			System.out.println("sadness is in the trie.");
		else
			System.out.println("sadness is not in the trie.");
			
		//Test Add
		System.out.println(theTree.addPassword("hats"));
		System.out.println(theTree.addPassword("aaaa"));
		theTree.printPasswords();
		
		//Test Check Positive
		if(theTree.checkPassword("aaaa"))
			System.out.println("aaaa is in the trie.");
		else
			System.out.println("aaaa is not in the trie.");
		
		//Test Delete
		System.out.println(theTree.deletePassword("sadness"));
		System.out.println(theTree.deletePassword("hat"));
		theTree.printPasswords();
		
	}

}
