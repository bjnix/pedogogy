package schedule;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.*;

import com.sun.org.apache.bcel.internal.generic.ArrayType;
import courses.*;

/**
 * @author Brent Nix
 * CS1090
 * Spring 2010
 */
/**
 * Sets up GUI
 * Scans in .txt file
 * Uses ActionListener detect button pushes and runs button code
 */
public class Schedule extends JApplet implements ActionListener {

	// properties
	private JTextField input = new JTextField(10);
	private JButton getGrade = new JButton("get grade");
	private JButton canTake = new JButton("can take?");
	private JButton showPrereqs = new JButton("show prereqs");
	private JLabel result = new JLabel("", SwingConstants.CENTER);
	private Display courseDisplay = new Display(); 
	private Display prereqDisplay = new Display();
	private Vector <Displayable> display = new Vector <Displayable>();
	private Vector <Displayable> display1 = new Vector <Displayable>();
	private Scanner gradesInput;
	private boolean killSwitch = false;

	private Vector<Course> classes;



	/**
	 * init - set up the GUI
	 */	
	public void init() {


		// set up main window
		Container window = getContentPane();
		window.setLayout(new BorderLayout());

		// set up control panel
		JPanel control = new JPanel();
		control.setLayout(new FlowLayout());
		control.add(getGrade);
		control.add(canTake);
		control.add(showPrereqs);

		// align input, control and result
		JPanel top = new JPanel();
		top.setLayout(new GridLayout(3,1));
		top.add(input);
		top.add(control);

		result.setOpaque(true);
		result.setBackground(Color.yellow);
		top.add(result);

		// construct left part of window
		JPanel left = new JPanel();
		left.setLayout(new BorderLayout());
		left.add(top,BorderLayout.NORTH);	

		prereqDisplay.setBackground(Color.cyan);
		left.add(prereqDisplay,BorderLayout.CENTER);

		window.add(left, BorderLayout.WEST);


		// set up listeners
		input.addActionListener(this);
		getGrade.addActionListener(this);
		canTake.addActionListener(this);
		showPrereqs.addActionListener(this);

		// set up display
		courseDisplay.setBackground(Color.white);

		window.add(courseDisplay, BorderLayout.CENTER);
		window.validate();
		classes = new Vector<Course>();
		Scanner();
		sort();



	} // end init method


	/**	
	 * creates new Scanner and scans in *.txt
	 */
	public void Scanner()
	{
		//create scanner object and output object
		try
		{
			gradesInput = new Scanner(new File("../grades"));

		}
		catch(IOException e)
		{
		}
		while(gradesInput.hasNext())
		{
			scanSetLevel(gradesInput);
			if(killSwitch)
			{
				gradesInput.close();//close file handlers
				return;//stop 
			}
		}
	}//end Scanner method


	/**
	 *  actionPerformed - respond to events
	 */
	public void actionPerformed(ActionEvent e) 
	{
		String cQName = input.getText();//set input to cQName
		//----------------------------------------------------
		// if getGrade button is pushed, get grade
		//----------------------------------------------------	

		if(e.getSource()== getGrade && !(cQName.equals("")))
		{
			for(int i = 0; i<classes.size(); i++)
			{
				if(classes.get(i).getCourseNumber().equals(cQName))//go through elements in class Vector and find class number equal to cQName
				{
					result.setText(classes.get(i).getGrade());
				}
			}
		}

		//----------------------------------------------------
		// can take button returns if student can take a class
		//----------------------------------------------------	

		if(e.getSource() == canTake && !(cQName.equals("")))
		{
			for(int i = 0; i<classes.size(); i++)
			{
				if(classes.get(i).getCourseNumber().equals(cQName))
				{

					for(int j = 0; j < classes.get(i).getPrereqs().size(); j++)
					{
						if(classes.get(i).getPrereqs().get(j).hasTaken()==false || classes.get(i).getPrereqs().get(j).hasPassed(classes.get(i).getCourseNumber())==false)
						{
							result.setText("NO");
							break;
						}
						if(classes.get(i).getPrereqs().get(j).hasTaken()==true && classes.get(i).getPrereqs().get(j).hasPassed(classes.get(i).getCourseNumber())==true)
						{
							result.setText("YES");
						}
					}

				}
			}
		}
		//----------------------------------------------------
		// Show Prereqs shows the prereqs for a given class
		//----------------------------------------------------	
		if(e.getSource()==showPrereqs && !(cQName.equals("")))
		{
			for(int i = 0; i<classes.size(); i++)
			{
				if(classes.get(i).getCourseNumber().equals(cQName))
				{
					classes.get(i).getPrereqs();
					for(int j = 0; j < classes.get(i).getPrereqs().size(); j++)
					{
						display.clear();
						display.add(classes.get(i).getPrereqs().get(j));	
					}
					prereqDisplay.setStuff(display);
					repaint();
				}
			}
		}
	} // end of actionPerformed method
	public void scanSetLevel(Scanner gradesInput)
	{

		if(gradesInput.hasNext("done"))
		{
			killSwitch();
		}
		else if(gradesInput.hasNext("undergraduate") || gradesInput.hasNext("graduate"))
		{
			Course course = runCourse();//add  course
			classes.add(course);
		}
	}
	public Course runCourse()
	{
		//properties for scanner
		String edLevel = "";//level of study
		String pRFull;//the course number of a prereq

		String number; // the course number
		String name;	// the full name of the course
		int recHrs; // the number of recitation hours
		int lecHrs; // the number of lecture hours
		int labHrs; // the number of lab hours
		String grade; // the grade the student has received in this course
		boolean isSeminar = false; // is this course a seminar

		Course retval = null;

		//save input
		edLevel = gradesInput.nextLine();
		number = gradesInput.nextLine();
		name = gradesInput.nextLine();
		recHrs = gradesInput.nextInt();
		lecHrs = gradesInput.nextInt();
		labHrs = gradesInput.nextInt();


		if(gradesInput.hasNext("seminar"))
		{
			gradesInput.next();
			isSeminar = true;
		}
		grade = gradesInput.next();


		if(edLevel.equals("undergraduate"))//create undergraduate object
		{
			retval = new Undergraduate(number, name, recHrs, lecHrs, labHrs, isSeminar, grade);



		}
		else if(edLevel.equals("graduate"))//create undergraduate object
		{
			retval = new Graduate(number, name, recHrs, lecHrs, labHrs, isSeminar, grade);

		}
		else
		{
			System.out.println("error edLevel");
		}

		//----------------------------------		
		//		
		//		scan in prereqs
		//				
		//----------------------------------		

		while(! gradesInput.hasNext("graduate") &&! gradesInput.hasNext("undergraduate") &&! gradesInput.hasNext("done"))
		{

			if(gradesInput.hasNext("none"))
			{
				pRFull = gradesInput.nextLine();
			}
			else
			{
				pRFull = gradesInput.next();
				for(int i = 0; i < classes.size(); i++)
				{
					Course foundCourse = null;
					String name01 = classes.get(i).getCourseNumber();
					if(name01.equals(pRFull))
					{
						foundCourse =  classes.get(i);
					}

					retval.addPrereq(foundCourse);
				}
				gradesInput.nextLine();
			}					
		}
		return retval;
	}
	/**
	 * sorts classes vector
	 */
	public void sort()
	{
		for(int i = 0; i<classes.size()-1; i++)
		{

			

			for(int j = 0; j<classes.size(); j++)
			{
				int upDown = classes.get(i).getCourseName().compareTo(classes.get(j).getCourseName());
				if(upDown < 0)
				{
					Course goo = classes.get(i);
					Course doo = classes.get(j);
					classes.add(i, doo);
					classes.add(j, goo);
					classes.remove(i);
					classes.remove(j);
					
				}

			}

		}
		for(int i = 0; i<classes.size(); i++)
		{
			display1.add(classes.elementAt(i));
			courseDisplay.setStuff(display1);
			repaint();
		}

	}
	/**
	 * stops the scanner
	 */
	public void killSwitch()
	{
		killSwitch = true;
		return;
	}

} // end of Schedule class



