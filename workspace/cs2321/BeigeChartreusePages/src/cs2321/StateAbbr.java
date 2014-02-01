package cs2321;

/**
 * @author cdbrown
 *
 *	TODO:  compareTo(), and any other appropriate methods
 *
 */
public class StateAbbr implements Comparable<StateAbbr>{

	/**
	 * @param num
	 */
	private String state;
	public StateAbbr(String num) {
		state = num;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(StateAbbr o) {
		
		return state.compareTo(o.toString());
	}

	public String toString() {
		
		return state;		
	}
	public boolean equals(Object o)
	{
		if(o instanceof StateAbbr)
		{
			return this.compareTo((StateAbbr)o) == 0;
		}
		return false;
	}
	public int hashCode()
	{
		return state.hashCode();
	}

}
