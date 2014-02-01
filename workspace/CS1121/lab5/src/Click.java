import java.awt.*;
import animator.*;
/**
 * ball that moves towards the last mouse click
 */
public class Click extends Animated implements ClickListener  {

	//named constants
	private final int radius = 20;
	private final int diameter = 2*radius;
	
	//properties
	private double x = 60;
	private double y = 200;
	private double xdir;
	private double ydir;
	private int savex;
	private int savey;
	private Arrow arrow;
	private boolean alive = true;
	public Click (Arrow a){
		arrow = a;
	}
	
	
	public void draw(){
		if (alive == true){
		//draw the circle
		screen.setColor(Color.red);
		screen.fillOval((int)x-radius,(int)y-radius, diameter, diameter);
		}
		//see if it hits the asteroid
		int ax = arrow.getX();
		int ay = arrow.getY();
		int ar = arrow.getRadius();
		double dist = Math.sqrt((x-ax)*(x-ax)+(y-ay)*(y-ay));
		if (dist < radius+ar) {
			
			//if so, then tell the asteroid it has been hit
			arrow.hit();
			hit();
		}
		
		//move the rocket
		x += xdir;
		y += ydir;
		
	}//end draw method
		
	public void click(int x, int y) {
		
		/**
		 * respond to a click on the screen.
		 * 
		 * @param x The x-coordinate of the click.
		 * @param y the y-coordinate of the click.
		 * 
		 */
		//compute movement rates in both directions
		double xdist = x - savex;
		double ydist = y - savey;
		double hyp = Math.sqrt(xdist*xdist + ydist*ydist);
		xdir = xdist/hyp;
		ydir = ydist/hyp;
		
		
	}//end of click method
	public void hit() {

        alive = false;
    } // end of hit method
}//end Click class
