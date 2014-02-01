package cs2321;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SplashScreen extends JFrame implements ActionListener {
	
	private JButton close = new JButton("continue");
	private JTextArea text = new JTextArea();
	
	public SplashScreen(){
		
		//Set up Container
		Container window = getContentPane();
		window.setLayout(null);
		window.add(close);
		window.add(text);
		
		//Set up Frame
		setSize(250,500);
		setLocation(300,100);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
		//Set up close button
		close.addActionListener(this);
		close.setSize(100,30);
		close.setLocation(140,440);
		
		//Set up text
		text.setLocation(0,0);
		text.setSize(250,500);
		text.setBackground(Color.white);
		text.setEditable(false);
		text.setText("\n   Welcome to Pokemon World Demo! \n\n\n" +
				"                    Controls \n\n" +
				"       c - Places a city \n\n" +
				"       v - Removes a city \n\n" +
				"       b - Adds a new route between the \n             next 2 clicked cities \n\n" +
				"       n - Removes a route \n\n");
		
	}

	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
