package cs2321;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class PokeSelectionScreen extends JFrame implements ActionListener {
	
	private JButton close = new JButton("continue");

	private PokeSelectionScreenPanel display;
	private AgentDemoApplet main;
	
	public PokeSelectionScreen(AgentDemoApplet a, ArraySequence<Picture> pictures){
		
		main = a;
		
		display = new PokeSelectionScreenPanel(pictures);
		display.setLocation(0,0);
		display.setSize(800,725);
		
		//Set up Container
		Container window = getContentPane();
		window.setLayout(null);
		window.add(display);
		window.add(close);
		
		//Set up close button
		close.addActionListener(this);
		close.setSize(100,30);
		close.setLocation(660,730);
		close.setVisible(true);
		
		//Set up Frame
		setSize(800,800);
		setLocation(0,0);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		
}

	public void actionPerformed(ActionEvent e) {
		
		if( display.SEL_STATE == display.DONE){
			main.setPokemon(display.getPokemon()[0], display.getPokemon()[1]);
			dispose();
		}
	}
}
