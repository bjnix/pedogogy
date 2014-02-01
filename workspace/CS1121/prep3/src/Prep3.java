import java.awt.*;
import animator.*;

/**
 * 
 * @author Costco
 *
 */
public class Prep3 extends Animated{

	//the angle in degrees
	private int angle = 0;

	/**
	 * draw three things
	 * 
	 */
	public void draw(int x, int y) {

		// draw the yellow square
		screen.setColor(Color.yellow);
		int sx = (int)(Math.cos(Math.toRadians(angle))*200 + 300);
		int sy = (int)(Math.sin(Math.toRadians(angle))*200 + 300);
		screen.fillRect(sx-25,sy-25,50,50);

		screen.setColor(Color.black);
		screen.fillOval((sx + x)/2, (sy + y)/2, 20, 20);

		screen.setColor(Color.blue);
		screen.fillOval(x-25,y-25,50,50);
		
		

		//update the angle
		angle = (angle + 1) % 360;
	} // end of draw method

}//end of Prep class 	

