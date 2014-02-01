package courses;

import java.util.Vector;
import java.awt.Graphics;

public class Graduate extends Course {

	
		
	
	/**
	 * Create a Graduate object
	 * 
	 * @param typ the type of
	 * @param dept the two-letter department code for the course
	 * @param num the course number
	 * @param name the full course name
	 * @param rec the number of recitation hours
	 * @param labHrs 
	 * @param lecHrs 
	 * @param seminar is course a seminar
	 * @param grade 
	 */
	public Graduate(String num, String nam, int rec, int lec, int lab, boolean seminar, String grade) {
		
		
		number = num;
		this.name = nam;
		recitationHours = rec;
		lectureHours = lec;
		labHours = lab;
		isSeminar = seminar;
		this.grade = grade;
		prereqs = new Vector<Course>(); 
	}
	
	
	
	/**
	 * Get a complete listing for this course name
	 * 
	 * @return a String contain course department, name and number
	 */
	public String getCourseString() {
		String str = number + ": " + name + " (0,"+recitationHours+",0)";
		if (isSeminar)
			return str + ": Seminar";
		else 
			return str;
	}
    
	/**
	 * Display course information
	 * @param g a graphics object
	 * @param line the line on which to display the information
	 */
    public void display(Graphics g, int line) {

	    g.drawString(getCourseString(),10,20*line+20);
	} // end of display method
}
