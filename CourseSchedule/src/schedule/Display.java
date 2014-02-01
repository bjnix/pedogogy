package schedule;

import java.awt.Graphics;
import java.util.Vector;

import javax.swing.JPanel;

//-------------------------------------------------------------
//
// Display - class to set up the display
//
//-------------------------------------------------------------

class Display extends JPanel {

	Vector<Displayable> stuff = null; /* the stuff to display */


	/**
	 * Set the Vector of stuff to display
	 * @param s a Vector object containing Displayable items
	 */
	public void setStuff(Vector<Displayable> s) {
		stuff = s;
	}

	/**
	 * display the contents of the abstraction
	 * 
	 * @param g a reference to a Graphics object
	 */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		
		if (stuff != null)
			for (int i = 0; i < stuff.size(); i++)
				stuff.elementAt(i).display(g, i);
		
	} // end of paintComponent method

} // end of Display class
