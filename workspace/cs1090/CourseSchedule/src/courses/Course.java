package courses;

import java.awt.Graphics;
import java.util.Vector;

import schedule.Displayable;
/**
 * Class for common course stuff
 *
 */

public abstract class Course implements Displayable {
	protected String edLevel;//undergraduate or graduate
	protected String dept; //two character department
	protected String number; // the course number
	protected String name;	// the full name of the course
	protected String grade; // the grade the student has received in this course
	protected Vector<Course> prereqs; // the courses that are prerequisites for this course
	protected int recitationHours; // the number of recitation hours
	protected int lectureHours; // the number of lecture hours
	protected int labHours; // the number of lab hours
	protected boolean isSeminar; // is this course a seminar
	
	/**
	 * Add a prerequisite course for this student
	 * 
	 * @param course an Undergraduate course
	 */
	public void addPrereq(Course course) 
	{
		prereqs.add(course);
	}
	/**
	 * Determine if a student has taken the course
	 * 
	 * @return true if there is a grade for the course
	 */
	public boolean hasTaken() 
	{
		return grade != null;
	}
	/**
	 * Determine if a student has passed the course
	 * 
	 * @return true if the grade is not an F
	 */
	public boolean hasPassed(String type) 
	{
		if(type.equals("graduate"))
		{
		return (grade != null && grade.compareTo("B") <= 0);
		}
		else if(type.equals("undergraduate"))
		{
		return (grade != null && grade.compareTo("F") <= 0);
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Get the prerequisites for a course
	 * @return a Vector of Undergraduate courses
	 */
	public Vector<Course> getPrereqs() 
	{
		return prereqs;
	}
	/**
	 * Get the course number 
	 * 
	 * @return the course number
	 */
	public String getCourseNumber() {
		return number;
	}
	
	/**
	 * Return the full name of the course
	 * 
	 * @return the name of the course
	 */
	public String getCourseName() {
		return name;
	}
	
	/**
	 * Get the number of recitation hours for the course
	 * 
	 * @return the recitation hours
	 */
	public int getRecitationHours() {
		return recitationHours;
	}
	/**
	 * Get the grade the student has earned
	 * 
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * Get the number of lecture hours for the course
	 * 
	 * @return the lecture hours
	 */
	public int getLectureHours() {
		return lectureHours;
	}
	
	/** 
	 * Get the number of lab hours for the course
	 * 
	 * @return the lab hours
	 */
	public int getLabHours() {
		return labHours;
	}
	
	/**
	 * Get a complete listing for this course name
	 * 
	 * @return a String contain course department, name and number
	 */
	/**
	 * Set the grade earned by the student for this course
	 * 
	 * @param grade the grade earned
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	
	
}
