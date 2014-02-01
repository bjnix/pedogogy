import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Listen for text field event and tell tracker.
 */
class TextListener implements ActionListener {

    // properties
    private final Tracker msg1;
    private final JTextField theField;
    

    /**
     * Save property values.
     *
     * @param t A reference to the tracker object.
     * @param f A reference to the text field listened to.
     * @param m2 
     */
    public TextListener(Tracker m1, JTextField f) {

	msg1 = m1;
	theField = f;
	} // end of constructor

    /**
     * Tell tracker about text event.
     *
     * @param event Unused.
     */
    public void actionPerformed(ActionEvent event) {

	// get the string and send it to the tracker
	String input = theField.getText();
	
	msg1.textEntered(input);
	//theField.setText("");
	
	} // end of actionPerformed method

    } // end of TextListener class
