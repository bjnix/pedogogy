import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Listen for button events and tell tracker.
 */
class ButtonListener implements ActionListener {

    // properties
    private final int which;
    private final Tracker theTracker;

    /**
     * Save property values.
     *
     * @param w A code for which button it listens for.
     * @param t A reference to the tracker object.
     */
    public ButtonListener(int w, Tracker t) {

	which = w;
	theTracker = t;
	} // end of constructor

    /**
     * React to button push and tell tracker.
     *
     * @param event Unused.
     */
    public void actionPerformed(ActionEvent event) {

	// tell tracker which button was pushed
	theTracker.buttonPushed(which);
	} // end of actionPerformed method

    } // end of ButtonListener class
