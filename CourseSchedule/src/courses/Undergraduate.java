package courses;

import java.awt.Graphics;
import java.util.*;

public class Undergraduate extends Course {

	
		
	/**
	 * Create an Undergraduate object
	 * 
	 * @param dept the two-letter department code for the course
	 * @param num the course number
	 * @param name the full course name
	 * @param name2 
	 * @param rec the number of recitation hours
	 * @param lec the number of lecture hours
	 * @param lab the number of lab hours
	 * @param isSeminar 
	 * @param grade the student's grade in the course
	 */
	public Undergraduate(String num, String nam, int rec, int lec, int lab, boolean seminar, String grade) {
		
		
		number = num;
		this.name = nam;
		recitationHours = rec;
		lectureHours = lec;
		labHours = lab;
		isSeminar = seminar;
		this.grade = grade;
		prereqs = new Vector<Course>(); 
	}
	
	
	public String getCourseString() {
		return number + ": " + name + " ("+lectureHours+","+recitationHours+","+labHours+")";
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
