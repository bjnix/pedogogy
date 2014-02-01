package HuffmanEncoder;


/**
 * This class implements a huffman encoder for strings. The encoded string will
 * be strings of '0' and '1' characters.
 * 
 * @author Your Name
 * 
 */
public class HuffmanEncoder implements HuffmanInterface {

	public void setString(String str) {
		// TODO
	}

	@Override
	public int charFrequency(char ch) {
		// TODO
		return -1;
	}

	@Override
	public String charToCode(char ch) {
		// TODO
		return "2";
	}

	@Override
	public char codeToChar(String code) {
		// TODO
		return (char) -1;
	}

	@Override
	public String encodeString() {
		// TODO
		return "2";
	}

	/**
	 * This is a simple main to test some of the functionality of the
	 * HuffmanEncoder class. This is not an extensive test, and more thorough
	 * testing should be done to ensure that it works properly.
	 * 
	 * @param args
	 *            Unused
	 */
	public static void main(String[] args) {
		HuffmanEncoder encoder = new HuffmanEncoder();

		String str = "The quick grey fox jumps over the lazy brown dog.";

		// process the string to be encoded
		encoder.setString(str);

		// print frequencies of lower case letters
		for (char ch = 'a'; ch <= 'z'; ++ch)
			System.out.println(ch + " occurs " + encoder.charFrequency(ch)
					+ " times.");

		// print Huffman codes for lower case letters
		for (char ch = 'a'; ch <= 'z'; ++ch)
			System.out.println(ch + " -> " + encoder.charToCode(ch));

		String encoded = encoder.encodeString();
		System.out.println(str + " -> " + encoded);
		System.out.println("encoded length is " + encoded.length() + " bits.");
		System.out.println("original length is " + (str.length() * 8)
				+ " bits.");

		str = "a banana";
		encoder.setString(str);
		encoded = encoder.encodeString();
		System.out.println(str + " -> " + encoded);
		System.out.println("encoded length is " + encoded.length() + " bits.");
		System.out.println("original length is " + (str.length() * 8)
				+ " bits.");
	}
}
