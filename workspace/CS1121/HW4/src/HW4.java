import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Demonstrate inheritance via an extended JPanel.
 */
public class HW4 extends JApplet {

    /**
     * Set up GUI.
     */
    public void init() {

	// set up window
	Container window = getContentPane();
	window.setLayout(new FlowLayout());
	window.setBackground(Color.black);

	// create display and buttons
	JButton draw = new JButton("Draw");
	JButton move = new JButton("Select/Move");
	JButton reo = new JButton("Reorder");
	JButton res = new JButton("Reset");

	// set object configurations
	res.setOpaque(true);
	res.setBackground(Color.red);
	//set up Display and MyButton classes	
	Display d = new Display(draw,move,reo,res);
	MyButton reslist = new MyButton("reset");
	//set up listener for reset
	reslist.setDisplay(d);
	//add listener to reset
	res.addActionListener(reslist);
	// add display and buttons to window
	window.add(d);
	window.add(draw);
	window.add(move);
	window.add(reo);
	window.add(res);
		
    } // end of init method

} // end of HW4 class



