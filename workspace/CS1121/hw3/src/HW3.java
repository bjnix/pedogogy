import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class HW3 extends JApplet {

	/**
	 * Set up GUI.
	 */
	public void init() {

		// set up window
		
		Container window = getContentPane();
		
		window.setLayout(new GridLayout(5,2,5,5));
		
		// create buttons and label
		JTextField input = new JTextField(3);
		JButton cont1 = new JButton("A");
		JButton cont2 = new JButton("B");
		JButton cont3 = new JButton("C");
		JButton restart = new JButton("restart");
		JLabel cap1 = new JLabel("",SwingConstants.CENTER);
		JLabel cap2 = new JLabel("",SwingConstants.CENTER);
		JLabel cap3 = new JLabel("",SwingConstants.CENTER);
		JLabel msg1 = new JLabel("Capacity for A?",SwingConstants.CENTER);
		JLabel blnk = new JLabel("");

		input.setOpaque(true);
		input.setBackground(Color.cyan);
		msg1.setOpaque(true);
		msg1.setBackground(Color.yellow);
		blnk.setOpaque(true);
		blnk.setBackground(Color.black);
		

		// add buttons and labels to window (in order)
		window.add(msg1);
		window.add(input);
		window.add(cont1);
		window.add(cap1);
		window.add(cont2);
		window.add(cap2);
		window.add(cont3);
		window.add(cap3);
		window.add(restart);
		window.add(blnk);

		// create object that keeps track of things
		Tracker m1 = new Tracker(msg1, cap1, cap2, cap3, input);
		

		// create listeners 
		TextListener tList = new TextListener(m1,input);
		ButtonListener cont1List = new ButtonListener(1,m1);
		ButtonListener cont2List = new ButtonListener(2,m1);
		ButtonListener cont3List = new ButtonListener(3,m1);
		ButtonListener restartList = new ButtonListener(4,m1);

		// add listeners to textfield and buttons
		input.addActionListener(tList);
		cont1.addActionListener(cont1List);
		cont2.addActionListener(cont2List);
		cont3.addActionListener(cont3List);
		restart.addActionListener(restartList);


	} // end of init method
	
} // end of HW3 class
