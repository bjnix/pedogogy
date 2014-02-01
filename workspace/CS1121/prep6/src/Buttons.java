import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * 
 * @author Brent
 *
 */

public class Buttons extends JApplet{

	//properties
	private JButton b1 = new JButton("one");
	private JButton b2 = new JButton("two");
	private JButton b3 = new JButton("three");
	private JLabel lbl = new JLabel("no button pushed");
	private Listener L1 = new Listener(lbl,"one pushed");
	private Listener L2 = new Listener(lbl,"two pushed");
	private Listener L3 = new Listener(lbl,"three pushed");
	
	public void init(){

		//set up window
		Container window = getContentPane();
		window.setLayout(new FlowLayout(FlowLayout.LEFT));

		window.setBackground(Color.orange);

		//add buttons and label
		window.add(b1);
		b1.setBackground(Color.red);

		window.add(b2);
		b2.setBackground(Color.blue);

		window.add(b3);
		b3.setBackground(Color.green);

		window.add(lbl);

		// set up listeners
		b1.addActionListener(L1);
		b2.addActionListener(L2);
		b3.addActionListener(L3);
		

	}//end init method


}//end Buttons class

class Listener implements ActionListener {
	private JLabel theLabel;
	private String theText;
	
	public Listener(JLabel lp, String sp) {
		
		theLabel = lp;
		theText = sp;
		}//end of constructor
	
	public void actionPerformed(ActionEvent event) {
		
		theLabel.setText(theText);
	}// end of actionPerformed method
	
}//end Listener Class
