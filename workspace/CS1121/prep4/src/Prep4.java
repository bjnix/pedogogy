import java.awt.*;
import animator.*;

/**
 * draw a black circle that moves according to the arrow keys.
 * assume the coordinates supplied by the animator are the center of the
 * circle.
 */

public class Prep4 extends Animated{
	
	//properties
	private int radius = 20;
	private int diameter = 2*radius;
	private Other other;
	
	
	
	public void startup(){
		
		other = new Other();
		animator.include(other);
	} // end of startup method
	
	/**
	 * Draw the circle, centered at x,y.
	 * 
	 * @param x the x-coordinate from the Animator.
	 * @param y the y-coordinate from the Animator.
	 * 
	 */
	public void draw(int x, int y){
		
	/**
	 * Create an Other object and include it in the animation.
	 */
		
		// draw a black circle
		screen.setColor(Color.black);
		screen.fillOval(x-radius, y-radius, diameter, diameter);
			
		//draw a black line between the two circles
		int cx = other.getX();
		int cy = other.getY();
		Color color1 = other.getColor();
		screen.drawLine(x, y, cx, cy);
		
		double dist = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		int otherRadius = other.getRadius(); 
		if (dist < (radius + otherRadius)) {
			if (color1 == Color.red)
				other.goBlack();
		}
		if (color1 == Color.black){
			other.goGreen();
		}
		if (dist < (radius + otherRadius)) {
			if (color1 == Color.green)
					other.goYellow();
		}
		if (dist < (radius + otherRadius)) {
			if (color1 == Color.yellow)
					other.goCyan();
		}
		if (dist < (radius + otherRadius)) {
			if (color1 == Color.cyan)
					other.goRed();
		}
	}//end draw method
	
}//end Prep4 class
