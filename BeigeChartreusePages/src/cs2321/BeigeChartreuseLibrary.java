package cs2321;

import java.io.*;
import java.util.Scanner;
import net.datastructures.*;

/**
 * @author bsiever
 *
 *	TODO: Add fields for appropriate data structures, 
 *        Complete initForSize() method to initialize data structures
 *        complete addRecord() to add a new record to all data structures
 *        complete removeRecord() to remove a record from all data structures
 *        complete search methods to access data
 */
public class BeigeChartreuseLibrary {	

	//# TODO Add any necessary fields:


	/*
	 * Create data structures for the expected size
	 * Any existing data is lost.
	 * 
	 * @param size - The expected amount of data 
	 */
	private void initForSize(int size) {
		//# TODO
		// If possible, initialize data structures for expected size
		// (More or less data may actually be used, but the user "expects" 
		//  this amount of data)
		// All Maps NEED default constructors, but you can also add 
		// non-default constructors if needed.

	}

	/**
	 * 
	 * @param rec - a record to be added to all dictionaries
	 */
	public void addRecord(Record rec) {
		//# TODO add Records to data structure(s).
		//# Be sure to update the Record's entry information after the Record is added.

	}

	/**
	 * 
	 * @param rec - a record to be removed from all dictionaries
	 */
	public void removeRecord(Record rec) {
		//# TODO remove Records from data structure(s).

	}

	/**
	 * Returns a sorted collection of records which include the given Last name.
	 * @param name - a first or last name
	 * @return Sorted Iterable collection of matching Records
	 */
	public Iterable<Record> searchByLastName(String lastName) {
		//# TODO
		//System.out.println("Search by last name for: " + lastName);
		return null;
	}

	/**
	 * Returns a sorted collection of records which include the given First and Last name.
	 * @param name - a first or last name
	 * @return Sorted Iterable collection of matching Records
	 */ 
	public Iterable<Record> searchByFullName(String lastName, String firstName) {
		//# TODO
		//System.out.println("Searching by Full Name for:" + firstName + " " + lastName);
		return null;
	}

	/**
	 * Returns a sorted collection of records which include either a matching first name or last name. 
	 * Duplicates should only be included once.
	 * 
	 * @param name - a first or last name
	 * @return Sorted Iterable collection of matching Records
	 */ 
	public Iterable<Record> searchByName(String name) {
		//# TODO
		//System.out.println("Searching by Name for:" + name);
		return null;
	}

	/**
	 * Returns a sorted collection of records which include the given phone number.
	 * @param phoneNumber
	 * @return Sorted Iterable collection of matching Records
	 */
	public Iterable<Record> searchByPhoneNumber(PhoneNumber phoneNumber) {
		//# TODO
		//System.out.println("Searching by Phone Number for: " + phoneNumber);
		return null;
	}

	/**
	 * Returns a sorted collection of records which include cities with the given name.
	 * @param city
	 * @return Sorted Iterable collection of matching Records
	 */
	public Iterable<Record> searchByCity(String city) {
		//# TODO
		//System.out.println("Searching by City for:" + city);
		return null;
	}

	/**
	 * Returns a sorted collection of records which include the given city and state.
	 * @param city
	 * @param state
	 * @return Sorted Iterable collection of matching Records
	 */
	public Iterable<Record> searchByCityState(String city, StateAbbr state) {
		//# TODO
		//System.out.println("Searching by City for:" + city + ", "+state);		
		return null;
	}

	/**
	 * Returns a sorted collection of records which include the given state.
	 * @param state
	 * @return Sorted Iterable collection of matching Records
	 */
	public Iterable<Record> searchByState(StateAbbr state) {
		//# TODO
		//System.out.println("Searching by state for:" +state);		
		return null;
	}

	/**
	 * Returns a sorted collection of records which include the given zipcode.
	 * @param zip
	 * @return Sorted Iterable collection of matching Records
	 */
	public Iterable<Record> searchByZip(ZipCode  zip) {
		//# TODO
		//System.out.println("Searching by zip for:" +zip);		
		return null;
	}

	/**
	 * Returns a sorted collection of records which include all records with zipcodes in the given range (inclusive).
	 * @param startZip
	 * @param stopZip
	 * @return Sorted Iterable collection of matching Records
	 */
	public Iterable<Record> searchByZipRange(ZipCode  startZip, ZipCode stopZip) {
		//# TODO
		//System.out.println("Searching by zip codes from:" +startZip + " to: "+stopZip);		
		return null;
	}

	/**
	 * Create an empty library
	 */
	public BeigeChartreuseLibrary() {
		initForSize(367);		
	}

	/*
	 * Create a new record using an appropriately ordered set of fields and add it to the data base
	 */
	private void addRecordFromFields(String[] fields) {
        /*System.out.println("Adding: " 
		            +fields[0]+" "
		            +fields[1]+" "
		            +fields[2]+" "
		            +fields[3]+" "
		            +fields[4]+" "
		            +fields[5]+" "
		            +fields[6]+" "); */
		//read record attributes in, one at a time
		Record thisRecord = new Record();
		thisRecord.setFirstName(fields[0]);
		thisRecord.setLastName(fields[1]);
		thisRecord.setAddress(fields[2]);
		thisRecord.setCity(fields[3]);
		thisRecord.setState(new StateAbbr(fields[4]));
		thisRecord.setZipCode(new ZipCode(fields[5]));
		thisRecord.setPhoneNo(new PhoneNumber(fields[6]));

		//add this record appropriately
		addRecord(thisRecord);		
	}

	/**
	 * Destroy the current data base and load new data from a file
	 * 
	 * @param filename the file to use as a source
	 * @throws IOException Either file not found or IO error
	 */
	public void initDBFromFile(String filename) throws IOException {
		//open and read the file
		try {
			BufferedReader filein = new BufferedReader(new FileReader(new File(filename)));
			int size = Integer.parseInt(filein.readLine());

			// Create and initialize data structures
			initForSize(size);

			// Read record file, parse lines, and add records to data base
			for(String line = filein.readLine(); line != null; line = filein.readLine()){
				String[] fields = line.split(",");
				addRecordFromFields(fields);
			}
			filein.close();
		} catch (Exception e){
			throw new IOException();
		}
	}


	/**
	 * Test driver - loads the default library and allows interaction 
	 * via typing queries into the console window pane.
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) {
		// Create the main library object
		BeigeChartreuseLibrary bcLib = new BeigeChartreuseLibrary();

		// Try to read the default library
		try {
			bcLib.initDBFromFile("PhoneBookSortedByZip.csv");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("File IO Error");
			System.exit(1);
		}

		//  Queries can be simulated by typing into the console in Eclipse, and using Ctrl-d (Ctrl-z in Windows) when finished.
		Scanner queries = new Scanner(System.in);
		Iterable<Record> result = null;
		System.out.print("Enter query: ");
		while(queries.hasNext()) {
			String query = queries.nextLine();
			String[] fields = query.split(",");

			if(fields[0].equals("searchLastName")) {
				result = bcLib.searchByLastName(fields[1]);
			} else
				if(fields[0].equals("searchFullName")) {
					result = bcLib.searchByFullName(fields[1],fields[2]);
				} else
					if(fields[0].equals("searchName")) {
						result = bcLib.searchByName(fields[1]);
					} else
						if(fields[0].equals("searchPhone")) {
							result = bcLib.searchByPhoneNumber(new PhoneNumber(fields[1]));
						} else
							if(fields[0].equals("searchCity")) {
								result = bcLib.searchByCity(fields[1]);
							} else
								if(fields[0].equals("searchCityState")) {
									result = bcLib.searchByCityState(fields[1],new StateAbbr(fields[2]));
								} else
									if(fields[0].equals("searchState")) {
										result = bcLib.searchByState(new StateAbbr(fields[1]));
									} else
										if(fields[0].equals("searchZip")) {
											result = bcLib.searchByZip(new ZipCode(fields[1]));
										} else
											if(fields[0].equals("searchZipRange")) {
												result = bcLib.searchByZipRange(new ZipCode(fields[1]),new ZipCode(fields[2]));
											} else 
												if(fields[0].equals("add")) {
													String[] newFields = new String[fields.length-1];
													System.arraycopy(fields, 1, newFields, 0, fields.length-1);
													bcLib.addRecordFromFields(newFields);
												} else
													if(fields[0].equals("remove")) {
														if(result == null) 
															System.out.println("No data to remove");
														else {
															int idx = Integer.parseInt(fields[1]);
															for(Record r:result) {
																idx--;
																if(idx==0) {
																	bcLib.removeRecord(r);
																	result = null;
																	break;
																}
															}
															if(result!=null) {
																System.out.println("Invalid record index");
																result = null;
															}
														}
													} else {
														System.out.println("Invalid query");
														result = null;
													}
			// Print results
			if(result != null){
				System.out.println("Results:\n--------");
				int found=0;
				for(Record record: result){
					System.out.println(record);
					found++;
				}
				System.out.println("--------");
				System.out.println(found + " matching records found");
			} 
			System.out.print("Enter query: ");
		}	
	}
}
