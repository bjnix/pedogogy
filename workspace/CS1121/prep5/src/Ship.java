import animator.*;
import java.awt.*;

/**
 * A spaceship in a simple game.
 */

public class Ship extends Animated implements ClickListener {

	//named constants
	private final int radius = 20;
	private final int diameter = 2*radius;
	
	//properties
	private Asteroid asteroid;
	private int savex;
	private int savey;
	
	/**
	 * Save the reference to the asteroid.
	 * 
	 * @param ast A reference to an asteroid.
	 * 
	 */
	public Ship(Asteroid ast) {
		
		asteroid = ast;
	}//end of constructor
	/**
	 * Draw the ship using arrow keys.
	 * 
	 * @param x The x-coordinate from the animator.
	 **/
	public void draw(int x, int y) {
		
		// draw the ship 
		screen.setColor(Color.blue);
		screen.fillOval(x-radius, y-radius, diameter, diameter);
	
		
	}//end draw method
	
	
	/**
	 * respond to a click on the screen.
	 * 
	 * @param x The x-coordinate of the click.
	 * @param y the y-coordinate of the click.
	 * 
	 */
	public void click(int x, int y) {
		//compute movement rates in both directions
		double xdist = x - savex;
		double ydist = y - savey;
		double hyp = Math.sqrt(xdist*xdist + ydist*ydist);
		double xrate = xdist/hyp;
		double yrate = ydist/hyp;
		
		// create a new rocket and add it to the animation
		Rocket r = new Rocket(savex, savey, xrate, yrate, asteroid);
			animator.include(r);
		
		
		}// end of click method
	
}// end of Ship class
