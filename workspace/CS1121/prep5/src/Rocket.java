import java.awt.*;
import animator.*;

/**
 * A rocket in a simple game
 */

public class Rocket extends Animated {

	//named constants
	private final int radius = 5;
	private final int diameter = 2*radius;
	
	//properties
	private double x;
	private double y;
	private double xdir;
	private double ydir;
	private Asteroid asteroid;
	
	/**
	 * Save initial coordinates, initial movement rates and a
	 * reference to the asteroid
	 * 
	 *  @param intx The initial x-coordinate.
	 *  @param inty The initial y-coordinate.
	 *  @param xd The amount to move each tick in the x direction.
	 *  @param yd The amount to move each tick in the y direction.
	 *  @param ast A reference to the asteroid. 
	 */
	public Rocket(int initx, int inity, double xd, double yd, Asteroid ast) {
		x = initx;
		y = inity;
		xdir = xd;
		ydir = yd;
		asteroid = ast;
	}// end of constructor
	/**
	 * Draw the rocket and check for hitting the asteroid.
	 */
	
	public void draw() {
		
		// draw the rocket
		screen.setColor(Color.red);
		screen.fillOval((int)x-radius,(int)y-radius, diameter, diameter);
		
		//see if it hits the asteroid
		int ax = asteroid.getX();
		int ay = asteroid.getY();
		int ar = asteroid.getRadius();
		double dist = Math.sqrt((x-ax)*(x-ax)+(y-ay)*(y-ay));
		if (dist < radius+ar) {
			
			//if so, then tell the asteroid it has been hit
			asteroid.hit();
		}
		//move the rocket
		x += xdir;
		y += ydir;
		
	}//end of draw method
}//end of rocket class
