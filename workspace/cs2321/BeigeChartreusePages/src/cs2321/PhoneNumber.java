package cs2321;

/**
 * @author cdbrown
 *
 *	TODO:  compareTo(), and any other appropriate methods
 *
 */
public class PhoneNumber implements Comparable<PhoneNumber> {
	
	private String numb;
	public PhoneNumber(String num) {
		numb = num;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(PhoneNumber o) {
		return numb.compareTo(o.toString());
	}
	
	public String toString() {
		
		return numb;		
	}
	public boolean equals(Object o)
	{
		if(o instanceof PhoneNumber)
		{
			return this.compareTo((PhoneNumber)o) == 0;
		}
		return false;
	}
	public int hashCode()
	{
		return numb.hashCode();
	}

}
