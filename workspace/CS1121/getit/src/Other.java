import java.awt.*;
import animator.*;

public class Other extends Animated {
	private int radius = 30;
	private int diameter = radius*2;
	private int x = 100;
	private int y = 100;
	private int xdir = 1;
	private int ydir = 1;
	
	
	public void draw (){
		//draw the circle
		screen.setColor(Color.yellow);
		screen.fillOval(x-radius, y-radius, diameter, diameter);
		
		//move the circle
		x = x + xdir;
		y = y + ydir;
		
		//change direction if necessary
		if (x == radius){
			xdir = 1;
		}
		if (x == animator.getSceneWidth()-radius){
			xdir = -1;
		}
		if (y == radius){
			ydir = 1;
		}
		if (y == animator.getSceneHeight()-radius){
			ydir = -1;
		}
	}//end draw method
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getRadius(){
		return radius;
	}
	public void getSmaller(){
		radius = radius/2;
	}

}//end of Other class
