import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyButton extends JButton implements ActionListener {

	private  Display theDisplay;
	
    public MyButton(String label) {
    	
	super(label);
	
    }
    public void setDisplay(Display d) {
    	
	theDisplay = d;
	
	} // end of constructor

    public void actionPerformed(ActionEvent event) {
 
    	//run setDisplay when button pushed
    	theDisplay.reset();

    } // end of actionListener method

} // end of MyButton class

