package HuffmanEncoder;

/**
 * This is the interface for the Huffman Encoder. You are not allowed to modify
 * this file.
 * 
 * DO NOT MODIFY THIS FILE OR YOUR CODE WILL NOT COMPILE!!!
 * 
 * @author Bryan Franklin
 * 
 */
public interface HuffmanInterface {

	/**
	 * The method initializes the Huffman encoder and must be called before any
	 * other methods.
	 * 
	 * @param str
	 *            The string to be compressed.
	 */
	public void setString(String str);

	/**
	 * Returns the number of occurrences of a given character in the string that
	 * was passed to setString.
	 * 
	 * @param ch
	 *            The character to return the frequency for.
	 * @return The number of occurrences of character ch in the string passed to
	 *         setString.
	 */
	public int charFrequency(char ch);

	/**
	 * Converts a single character to a string that represents a huffman code.
	 * The string returned will consist of '0' (zero) and '1' (one) characters
	 * that represent the bit sequence of the huffman code.
	 * 
	 * @param ch
	 *            The character to be encoded
	 * @return A string representation of the huffman code that corresponds to
	 *         character ch
	 */
	public String charToCode(char ch);

	/**
	 * Converts a huffman code as described in charToCode back into the
	 * character that it respresents.
	 * 
	 * @param code
	 *            A string of '0' (zero) and '1' (one) character that represent
	 *            a huffman bit sequence
	 * @return The character that corresponds to the huffman code.
	 */
	public char codeToChar(String code);

	/**
	 * Converts the entire string passed to setString into a huffman encoded
	 * string of '0' (zero) and '1' (one) characters representing the bits in a
	 * properly encoded sequesnce.
	 * 
	 * @return A string that represents the full encoding of the string passed
	 *         to setString.
	 */
	public String encodeString();

}
