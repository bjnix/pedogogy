/**
 * trie tree
 * Constructs trie tree for storing passwords
 */
package trie;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;



public class PasswordTrie implements PasswordTrieInterface{

	//properties 
	private class Node  {

		// properties
		private char element;
		private LinkedList<Node> childCollection = new LinkedList<Node>();

		/**
		 * Create a new node.
		 *
		 * @param v The value referred to by this node.
		 * @param n The next node in the list (or null if the end).
		 */
		public Node(char a, LinkedList<Node> n) {
			element = a;
			childCollection = n;
		} // end of constructor		


		/**
		 * Get the integer value referred to by this node.
		 *
		 * @return The value.
		 */
		public char getValue() {
			return element;
		} // end of getValue method

		/**
		 * Get the next node in the list.
		 *
		 * @return A reference to the next node (or null if this is the last
		 *         node in the list).
		 */
		

	} // end of Node class

	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#addPassword(java.lang.String)
	 */
	@Override
	public String addPassword(String password) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#checkPassword(java.lang.String)
	 */
	@Override
	public boolean checkPassword(String password) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#deletePassword(java.lang.String)
	 */
	@Override
	public String deletePassword(String password) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#printPasswords()
	 */
	@Override
	public void printPasswords() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#readInFile(java.lang.String)
	 */
	
	public void readInFile(String filename) throws FileNotFoundException {
		// TODO read in file and tokenize it.
		//create scanner object and output object
		System.out.println("----");
		Scanner p = null;
		try
		{
			p = new Scanner(new File(filename));

		}
		catch (FileNotFoundException e) {
		     System.err.println(e);
		     System.exit(-1);
		}
		do
		{
			
			String PassWrd = p.nextLine();
			
			System.out.println(PassWrd);
			
		}while(p.hasNext());
		p.close();//close file handlers
		return;//stop 
	}
}
