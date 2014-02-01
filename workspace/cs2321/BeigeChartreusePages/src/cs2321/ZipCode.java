package cs2321;

/**
 * @author cdbrown
 *
 *	TODO:  compareTo(), and any other appropriate methods
 *
 */
public class ZipCode implements Comparable<ZipCode> {

	/**
	 * @param code
	 */
	private String zip;
	public ZipCode(String code) {
		zip = code;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ZipCode o) {
		
		return zip.compareTo(o.toString());
	}

	public String toString() {
		
		return zip;		
	}
	public boolean equals(Object o)
	{
		if(o instanceof ZipCode)
		{
			return this.compareTo((ZipCode)o) == 0;
		}
		return false;
	}
	public int hashCode()
	{
		return zip.hashCode();
	}
	

}
