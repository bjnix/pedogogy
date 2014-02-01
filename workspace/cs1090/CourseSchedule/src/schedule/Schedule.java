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
	private Scanner gradesInput;
	private boolean killSwitch = false;

	private Vector<Course> classes;


	//-------------------------------------------
	//
	// init - set up the GUI
	//
	//-------------------------------------------



	public void init() {

		//display();/////////////////////////////////////////




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
		courseDisplay.setStuff(display);


	} // end init method
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

		sort();
		System.out.println(classes);
	}
	//-------------------------------------------
	//
	// actionPerformed - respond to events
	//
	//-------------------------------------------

	public void actionPerformed(ActionEvent e) 
	{
		String cQName = input.getText();//set input to cQName

		if(e.getSource()== getGrade)//if getGrade button is pushed, get grade
		{
			if(cQName.equals(""))
			{
				//don't do anything
			}
			else
			{
				for(int i = 0; i<classes.size(); ++i)
				{
					if(classes.get(i).getCourseNumber().equals(cQName))//go through elements in class Vector and find class number equal to cQName
					{
						result.setText(classes.get(i).getGrade());
					}
				}
			}
		}
		if(e.getSource() == canTake)
		{
			for(int i = 0; i<classes.size(); ++i)
			{
				if(classes.get(i).getCourseNumber().equals(cQName))
				{

				}
			}
		}
		if(e.getSource()==showPrereqs)
		{
			System.out.println("running 'show prereqs'");
			Vector<Displayable> dispPre = new Vector<Displayable>();
			Vector<String> coursePre = new Vector<String>();
			String crsFind = "";
			for(int i = 0; i<classes.size(); ++i)
			{
				System.out.println("input " + cQName);
				System.out.println("number " + classes.get(i).getCourseNumber());

				if(classes.get(i).getCourseNumber().equals(cQName))
				{

					for(int n = 0; n < classes.get(i).getPrereqs().size(); ++n ){

						coursePre.add(classes.get(i).getCourseNumber());
					}




					for(int j = 0; j< coursePre.size(); ++j)
					{

						for(int k = 0; k<classes.size()-1.; ++k)
						{
							if (classes.get(k).getCourseNumber().equals(coursePre.get(i)))
							{
								dispPre.add(classes.get(k));

								prereqDisplay.setStuff(dispPre);
							}
						}

					}
				}

			}

		}
	} // end of actionPerformed method
	public void scanSetLevel(Scanner gradesInput)
	{
		System.out.println("+++");
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

		//properties for scanner
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

		Vector<Displayable> coursesDispl = new Vector<Displayable>();
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

			if(gradesInput.hasNext("none")){
				pRFull = gradesInput.nextLine();
				System.out.println(pRFull);
			}
			else
			{
				pRFull = gradesInput.next();
				for(int i = 0; i < classes.size(); ++i)
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
		for(int i = 0; i<classes.size(); ++i)
		{
			System.out.println(classes);
			int upDown;
			System.out.println(classes.size());
			for(int j = 1; j<classes.size(); ++i)
			{

				upDown = classes.get(i).getCourseName().compareTo(classes.get(j).getCourseName());

				if(upDown > 0)
				{


					classes.add(i, classes.get(j));
					classes.remove(j+1);


				}
				else if(upDown == 0)
				{
					
				}
				if(upDown < 0)
				{
					
				}
			}

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



