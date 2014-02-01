import java.awt.*;

import animator.*;


public class Jive extends Animated {

	// properties
	private int radius = 30;
	private int diameter =2*radius;
	private int x = 500;
	private int y = 50;
	private int xdir = 1;
	private int ydir = 1;
	private Color color = Color.red;
	/**
	 * draw red circle centered at x,y, that bounces around the window
	 */
	public void draw(){
		
		
		//draw the circle
		screen.setColor(color);
		screen.fillOval(x-radius, y-radius, diameter, diameter);
		
		//move the circle
		x = x - xdir;
		y = y - ydir;
		
		//change direction if necessary
		if (x == radius){
			xdir = - 1;
			}
		if (x == animator.getSceneWidth()-radius){
			xdir = + 1;
			}
		if (y == radius){
			ydir = - 1;
			}
		if (y == animator.getSceneHeight()-radius){
			ydir = + 1;
			}
	}//end of draw method
	
	/**
	 * get the x-coordinate of where the circle was drawn.
	 * 
	 * @return the x coordinate.
	 */
	public int getX() {
		
		return x;
	} //end of getX method
	/**
	 * get the y-coordinate of where the circle was drawn.
	 * 
	 * @return the y-coordinate.
	 */
	public int getY() {
		
		return y;
	}//end of get y method
	
	
	/**
	 * Get the radius of this circle.
	 * 
	 * @return the radius.
	 */
	public int getRadius() {
		
		return radius;
	}//end of getRadius method
	/**
	 * Change the color of the circle to black.
	 * @return 
	 */
	public Color getColor(){
		return color;
	}
	public void goBlack() {
		
		color = Color.black;
	}// end of goBlack method
	public void goYellow(){
		color = Color.yellow;
	}
	public void goRed(){
		color = Color.red;
	}
	public void goCyan(){
		color = Color.cyan;
	}
	public void goGreen(){
		color = Color.green;
	}
}//end of  class

