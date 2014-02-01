import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
*Brent Nix
*cs1121 L02
*
*/
public class HW5 extends JApplet {

    /**
     * Set up GUI.
     */
    public void init() {

	// set up window
	Container window = getContentPane();
	window.setLayout(new FlowLayout());
	window.setBackground(Color.gray);

	// create display and buttons
	JLabel x = new JLabel("x");
	JLabel y = new JLabel("y");
	JLabel z = new JLabel("z");
	JTextField xText = new JTextField(10);
	JTextField yText = new JTextField(10);
	JTextField zText = new JTextField(10);
	JButton enter = new JButton("enter");
	JButton clear = new JButton("clear");
	JButton closest0 = new JButton("Closest to 0,0,0");
	JButton closest2 = new JButton("Closest to each other");
	JButton res = new JButton("Reset");

	// set object configurations
//	res.setOpaque(true);
//	res.setBackground(Color.red);
	//set up Display and MyButton classes	
	Display d = new Display(xText,yText,zText,enter, clear, closest0, closest2, res);
//	MyButton reslist = new MyButton("reset");
//	//set up listener for reset
//	reslist.setDisplay(d);
//	//add listener to reset
//	res.addActionListener(reslist);
	// add display and buttons to window
	window.add(x);
	window.add(xText);
	window.add(y);
	window.add(yText);
	window.add(z);
	window.add(zText);
	window.add(enter);
	window.add(clear);
	window.add(d);
	window.add(closest0);
	window.add(closest2);
	window.add(res);
		
	//add listeners
//	TextListener xtList = new TextListener(d,x);
//	TextListener ytList = new TextListener(d,y);
//	TextListener ztList = new TextListener(d,z);
//	ButtonListener enterList = new ButtonListener(1,d);
//	ButtonListener clearList = new ButtonListener(2,d);
//	ButtonListener clos0List = new ButtonListener(3,d);
//	ButtonListener clos2List = new ButtonListener(4,d);
//	ButtonListener resList = new ButtonListener(5,d);
	
	//add listeners to text fields and buttons
//	xText.addActionListener(xtList);
//	yText.addActionListener(ytList);
//	zText.addActionListener(ytList);
//	enter.addActionListener(enterList);
//	clear.addActionListener(clearList);
//	closest0.addActionListener(clos0List);
//	closest2.addActionListener(clos2List);
//	res.addActionListener(resList);

    } // end of init method

} // end of HW4 class
