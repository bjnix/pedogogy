import animator.*;
import java.awt.*;

/**
 * and asteroid in a simple game
 */
public class Asteroid extends Animated {
	
	//named constants
	private final int radius = 10;
	private final int diameter = 2*radius;
	
	//properties
	private int x;
	private int y;
	private int xdir = 1;
	private int ydir = 1;
	private boolean alive = true;
	
	/**
	 * Save the initial coordinates for asteroid.
	 * 
	 * @param initx The initial x-coordinate.
	 * @param inity The initial y-coordinate.
	 */
	 public Asteroid(int initx, int inity) {
		 
		 x = initx;
		 y = inity;
	 } //end of constructor
	 
	 /**
	  * Draw the asteroid.
	  */
	 public void draw() {
		 // draw the asteroid
		 screen.setColor(Color.black);
		 screen.fillOval(x-radius, y-radius, diameter, diameter);
		 
		 // move it
		 x += xdir;
		 y += ydir;
		 
		 //bounce it off the walls
		 if (x == radius) {
			 xdir = 1;
		 }
		 if (x == animator.getSceneWidth()-radius) {
			 xdir = -1;
		 }
		 if (y == radius) {
			 ydir = 1;
		 }
		 if (y == animator.getSceneHeight()-radius){
			 ydir = -1;
		 }
	
		 //draw the asteroid if still alive
		 screen.setColor(Color.black);
		 if (alive){
			 screen.fillOval(x-radius,y-radius,diameter,diameter);
		 }
	 }//end of draw method

	 /**
	     * Get the current x-coordinate of this asteroid.
	     *
	     * @return the x-coordinate.
	     */
	    public int getX() {

	        return x;
	    } // end of getX method

	    /**
	     * Get the current y-coordinate of this asteroid.
	     *
	     * @return the y-coordinate.
	     */
	    public int getY() {

	        return y;
	    } // end of getY method

	    /**
	     * Get the radius of this asteroid.
	     *
	     * @return the radius.
	     */
	    public int getRadius() {

	        return radius;
	    } // end of getRadius method

	    /**
	     * Record that this asteroid has been hit.
	     */
	    public void hit() {

	        alive = false;
	    } // end of hit method

}//end of Asteroid class
