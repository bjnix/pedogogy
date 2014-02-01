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
				"       v - allows selection of a city \n\n" +
				"       i - allows insertion of a vertex \n\n" +
				"       p - adds a new route between the \n             next 2 clicked cities \n\n" +
				"       t - sets the rare candy position \n\n" +
				"       r - begins gameplay");
		
	}

	public void actionPerformed(ActionEvent e) {
		dispose();
	}

}
