package cs2321;

import net.datastructures.Position;

/**
 * @author cdbrown
 *
 *	TODO:  toString() 
 *
 */
public class Record implements Comparable<Record>{

	private String firstName;
	private String lastName;
	private String address;
	private String city;
	private StateAbbr state;
	private ZipCode zipCode;
	private PhoneNumber phoneNo;
	
	//# TODO: If needed, modify fields to make entries location aware
	private Position<Record> firstNamePosition;
	private Position<Record> lastNamePosition;
	private Position<Record> addressPosition;
	private Position<Record> cityPosition;
	private Position<Record> statePosition;
	private Position<Record> zipCodePosition;
	private Position<Record> phoneNoPosition;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Display Record according to assignment format
		return "";
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Record o) {
		int comparison = lastName.compareTo(o.lastName);
		if(comparison != 0)
			return comparison;

		comparison = firstName.compareTo(o.firstName);
		if(comparison != 0)
			return comparison;

		comparison = state.compareTo(o.state);
		if(comparison != 0)
			return comparison;
		
		comparison = city.compareTo(o.city);
		if(comparison != 0)
			return comparison;

		comparison = address.compareTo(o.address);
		if(comparison != 0)
			return comparison;

		comparison = zipCode.compareTo(o.zipCode);
		if(comparison != 0)
			return comparison;

		return phoneNo.compareTo(o.phoneNo);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (phoneNo == null) {
			if (other.phoneNo != null)
				return false;
		} else if (!phoneNo.equals(other.phoneNo))
			return false;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;

		return true;
	}

	/**
	 * @return the First Name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the First Name to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the Last Name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the Last Name to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public StateAbbr getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(StateAbbr state) {
		this.state = state;
	}

	/**
	 * @return the zipCode
	 */
	public ZipCode getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(ZipCode zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the phoneNo
	 */
	public PhoneNumber getPhoneNo() {
		return phoneNo;
	}

	/**
	 * @param phoneNo the phoneNo to set
	 */
	public void setPhoneNo(PhoneNumber phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Position<Record> getFirstNamePosition() {
		return firstNamePosition;
	}

	public void setFirstNamePosition(Position<Record> firstNamePosition) {
		this.firstNamePosition = firstNamePosition;
	}

	public Position<Record> getLastNamePosition() {
		return lastNamePosition;
	}

	public void setLastNamePosition(Position<Record> lastNamePosition) {
		this.lastNamePosition = lastNamePosition;
	}

	public Position<Record> getAddressPosition() {
		return addressPosition;
	}

	public void setAddressPosition(Position<Record> addressPosition) {
		this.addressPosition = addressPosition;
	}

	public Position<Record> getCityPosition() {
		return cityPosition;
	}

	public void setCityPosition(Position<Record> cityPosition) {
		this.cityPosition = cityPosition;
	}

	public Position<Record> getStatePosition() {
		return statePosition;
	}

	public void setStatePosition(Position<Record> statePosition) {
		this.statePosition = statePosition;
	}

	public Position<Record> getZipCodePosition() {
		return zipCodePosition;
	}

	public void setZipCodePosition(Position<Record> zipCodePosition) {
		this.zipCodePosition = zipCodePosition;
	}

	public Position<Record> getPhoneNoPosition() {
		return phoneNoPosition;
	}

	public void setPhoneNoPosition(Position<Record> phoneNoPosition) {
		this.phoneNoPosition = phoneNoPosition;
	}
}
