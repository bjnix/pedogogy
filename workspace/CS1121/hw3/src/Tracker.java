import java.awt.*;
import javax.swing.*;

/**
 * Keep track of and do everything.
 */
class Tracker {

	// properties

	private final JLabel msg;
	private final JLabel aLabel;
	private final JLabel bLabel;
	private final JLabel cLabel;
	private final JTextField inPut;
	private boolean aPushed = false; //code = 1
	private boolean bPushed = false; //code = 2
	private boolean cPushed = false; //code = 3
	private int numberEntered = 0;
	private int i;
	private int target;
	private int aCap = 0;
	private int aCon = 0;
	private int bCap = 0;
	private int bCon = 0;
	private int cCap = 0;
	private int cCon = 0;


	/**
	 * Save reference to Jlabel.
	 *
	 * @param m A reference to the label.
	 */
	public Tracker(JLabel m, JLabel cA, JLabel cB, JLabel cC, JTextField i) {


		aLabel = cA;
		bLabel = cB;
		cLabel = cC;
		msg = m;
		inPut = i;
	} // end of constructor

	/**
	 * Process text input.
	 * @param input The input string from the text field.
	 */
	public void textEntered(String input) {
		i = Integer.parseInt(input);

		// if already have value for Container A volume, enter into Container B volume
		if (numberEntered == 0) {
			aCap = i;

			aLabel.setText( "capacity " + aCap + ", contains " + aCon);
			bLabel.setText( "capacity " + bCap + ", contains " + bCon);
			cLabel.setText( "capacity " + cCap + ", contains " + cCon);
			inPut.setText("");
			msg.setText("capacity of B?");

			numberEntered = 1;	    
		}
		else if (numberEntered == 1) {
			bCap = i;

			aLabel.setText( "capacity " + aCap + ", contains " + aCon);
			bLabel.setText( "capacity " + bCap + ", contains " + bCon);
			cLabel.setText( "capacity " + cCap + ", contains " + cCon);
			inPut.setText("");
			msg.setText("capacity of C?");

			numberEntered = 2;

		}
		else if (numberEntered == 2) {
			cCap = i;

			aLabel.setText( "capacity " + aCap + ", contains " + aCon);
			bLabel.setText( "capacity " + bCap + ", contains " + bCon);
			cLabel.setText( "capacity " + cCap + ", contains " + cCon);
			inPut.setText(""); 
			msg.setText("contents of A?");

			numberEntered = 3;

		}
		else if (numberEntered == 3) {
			if(i > aCap){
				i = aCap;
			}
			aCon = i;

			aLabel.setText( "capacity " + aCap + ", contains " + aCon);
			bLabel.setText( "capacity " + bCap + ", contains " + bCon);
			cLabel.setText( "capacity " + cCap + ", contains " + cCon);
			inPut.setText(""); 
			msg.setText("contents of B?");

			numberEntered = 4;

		}
		else if (numberEntered == 4) {
			if(i > bCap){
				i = bCap;
			}
			bCon = i;

			aLabel.setText( "capacity " + aCap + ", contains " + aCon);
			bLabel.setText( "capacity " + bCap + ", contains " + bCon);
			cLabel.setText( "capacity " + cCap + ", contains " + cCon);
			inPut.setText(""); 
			msg.setText("contents of C?");

			numberEntered = 5;

		}
		else if (numberEntered == 5) {
			if(i > cCap){
				i = cCap;
			}
			cCon = i;

			aLabel.setText( "capacity " + aCap + ", contains " + aCon);
			bLabel.setText( "capacity " + bCap + ", contains " + bCon);
			cLabel.setText( "capacity " + cCap + ", contains " + cCon);
			inPut.setText(""); 
			msg.setText("target value?");

			numberEntered = 6;

		}
		else if (numberEntered == 6) {
			target = i;

			aLabel.setText( "capacity " + aCap + ", contains " + aCon);
			bLabel.setText( "capacity " + bCap + ", contains " + bCon);
			cLabel.setText( "capacity " + cCap + ", contains " + cCon);
			inPut.setText(""); 
			msg.setText("get the target value");

			numberEntered = 7;

		}

	} // end of textEntered method

	/**
	 * React to button pushes.
	 *
	 * @param whichOne Which button was pushed.
	 */
	public void buttonPushed(int whichOne) {

		//reset button
		if (whichOne == 4){
			target = 0;
			aCap = 0;
			bCap = 0;
			cCap = 0;
			aCon = 0;
			bCon = 0;
			cCon = 0;
			i = 0;

			aLabel.setText( "capacity " + aCap + ", contains " + aCon);
			bLabel.setText( "capacity " + bCap + ", contains " + bCon);
			cLabel.setText( "capacity " + cCap + ", contains " + cCon);
			inPut.setText(""); 
			msg.setText("capacity of A?");

			numberEntered = 0;

		}

		if (numberEntered == 7){

			if (whichOne == 3){
				cPushed = true;
			}
			if (whichOne == 2){
				bPushed = true;
			}
			if (whichOne == 1){
				aPushed = true;
			}
			
			// otherwise record which button pushed 
			

				if (aPushed == true){
					if (whichOne == 2){


						bCon += aCon;
						aCon = 0;

						aPushed = false;
						bPushed = false;

						if(bCon > bCap){
							aCon = bCon - bCap;
							bCon = bCap;

						}
						aLabel.setText( "capacity " + aCap + ", contains " + aCon);
						bLabel.setText( "capacity " + bCap + ", contains " + bCon);
						cLabel.setText( "capacity " + cCap + ", contains " + cCon);
					}
					if(whichOne == 3){
						cCon += aCon;
						aCon = 0;

						aPushed = false;
						cPushed = false;

						if(cCon > cCap){
							aCon = cCon - cCap;
							cCon = cCap;

						}
						aLabel.setText( "capacity " + aCap + ", contains " + aCon);
						bLabel.setText( "capacity " + bCap + ", contains " + bCon);
						cLabel.setText( "capacity " + cCap + ", contains " + cCon);
					}
				}
				if (bPushed == true){
					if(whichOne == 1){
						aCon += bCon;
						bCon = 0;

						aPushed = false;
						bPushed = false;

						if(aCon > aCap){
							bCon = aCon - aCap;
							aCon = aCap;

						}
						aLabel.setText( "capacity " + aCap + ", contains " + aCon);
						bLabel.setText( "capacity " + bCap + ", contains " + bCon);
						cLabel.setText( "capacity " + cCap + ", contains " + cCon);
					}
					if(whichOne == 3){
						cCon += bCon;
						bCon = 0;

						aPushed = false;
						cPushed = false;

						if(cCon > cCap){
							bCon = cCon - cCap;
							cCon = cCap;

						}
						aLabel.setText( "capacity " + aCap + ", contains " + aCon);
						bLabel.setText( "capacity " + bCap + ", contains " + bCon);
						cLabel.setText( "capacity " + cCap + ", contains " + cCon);
					}

				}
				if (cPushed == true){
					if(whichOne == 1){
						aCon += cCon;
						cCon = 0;

						aPushed = false;
						cPushed = false;

						if(aCon > aCap){
							cCon = aCon - aCap;
							aCon = aCap;

						}
						aLabel.setText( "capacity " + aCap + ", contains " + aCon);
						bLabel.setText( "capacity " + bCap + ", contains " + bCon);
						cLabel.setText( "capacity " + cCap + ", contains " + cCon);
					}
					if(whichOne == 2){
						bCon += cCon;
						cCon = 0;

						cPushed = false;
						bPushed = false;

						if(bCon > bCap){
							cCon = bCon - bCap;
							bCon = bCap;

						}
						aLabel.setText( "capacity " + aCap + ", contains " + aCon);
						bLabel.setText( "capacity " + bCap + ", contains " + bCon);
						cLabel.setText( "capacity " + cCap + ", contains " + cCon);
					}

				}
				//if target met, display win
				if (aCon == target ||
						bCon == target ||
						cCon == target ){

					msg.setText("you win!");
					aLabel.setText( "capacity " + aCap + ", contains " + aCon);
					bLabel.setText( "capacity " + bCap + ", contains " + bCon);
					cLabel.setText( "capacity " + cCap + ", contains " + cCon);
				}
			}	

	} // end of buttonPushed method

} // end of Tracker class
