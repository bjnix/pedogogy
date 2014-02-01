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
		private LinkedList<Node> childCollection;


		/**
		 * Create a new node.
		 *
		 * @param v The value referred to by this node.
		 * @param n The next node in the list (or null if the end).
		 */
		public Node(char a) {

			childCollection = new LinkedList<Node>();
			element = a;

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
	Node root = null;
	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#addPassword(java.lang.String)
	 */
	@Override

	public String addPassword(String password) {
		// TODO Auto-generated method stub

		if (root==null)
		{
			root = new Node('%');
		}
		Node first = root;
		char c = ' ';
		if (root.childCollection.size()!=0){

			if (checkPassword(password)==true){
				return null;
			}
		}
		for(int i = 0; i<password.length(); i++)
		{ 
			c = password.charAt(i);
			//System.out.println(c);
			boolean found = false;
			for(int j = 0; j<first.childCollection.size();j++)
			{

				if (first.childCollection.get(j).getValue() == c)
				{
					found = true;
					first = first.childCollection.get(j);
					System.out.println(first.getValue());
					break;

				}

			}
			if (found == false)
			{
				Node C = new Node(c);
				first.childCollection.add(C);
				first = C;
				System.out.println(first.getValue());
			}

		}
		Node endd = new Node('$');

		first.childCollection.add(endd);
		System.out.println(endd.getValue());
		return null;


	}


	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#checkPassword(java.lang.String)
	 */
	@Override
	public boolean checkPassword(String password) {
		// TODO Auto-generated method stub
		Node first = root;
		int i = 0;


		if(checkPassword(password+'$',i,first)== true){
			return true;
		}
		else{
			return false;
		}

	}
	private boolean checkPassword(String password,int i, Node first)
	{

		if (first.getValue()=='$')
			return true;


		for (int j = 0; j<first.childCollection.size();j++)
		{
			if(first.childCollection.get(j).getValue()==password.charAt(i))
			{
				return checkPassword(password,i+1,first.childCollection.get(j));				
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#deletePassword(java.lang.String)
	 */
	@Override
	public String deletePassword(String password) {
		// TODO Auto-generated method stub
		Node first = root;
		int i = 0;
		deletePassword(password+'$',i,first);
		return null;
	}
	private boolean deletePassword(String password, int i, Node first){
		System.out.println(first.getValue());
		if((""+first.getValue()).equals("$"))
		{
			return true;
		}

		for (int j = 0; j<first.childCollection.size();j++)
		{

			if(first.childCollection.get(j).getValue()==password.charAt(i))
			{
				if(deletePassword(password,i+1,first.childCollection.get(j)))
					first.childCollection.remove(j);

			}

		}
		if(first.childCollection.size()==0){
			return true;

		}
		return false;
	}

	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#printPasswords()
	 */
	@Override
	public void printPasswords() {
		// TODO Auto-generated method stub
		Node first = root;
		String seen = "";
		printPasswords(first,seen);

	}
	private void printPasswords(Node first, String seen){
		if (first.getValue()=='$')
		{
			System.out.println(seen);
			return ;
		}
		else
		{
			if (first.getValue() != '%')
			{
				seen = seen + first.getValue();
			}
			for (int i = 0; i<first.childCollection.size(); i++)
			{
				printPasswords(first.childCollection.get(i),seen);
			}

		}

	}


	/* (non-Javadoc)
	 * @see trie.PasswordTrieInterface#readInFile(java.lang.String)
	 */

	public void readInFile(String filename) throws FileNotFoundException {
		// TODO read in file and tokenize it.
		//create scanner object and output object
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
			addPassword(PassWrd);
			//System.out.println(PassWrd);

		}while(p.hasNext());
		p.close();//close file handlers
		return;//stop 
	}
}
