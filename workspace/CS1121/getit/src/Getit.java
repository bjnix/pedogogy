import java.awt.*;

import animator.*;

public class Getit extends Animated {
	private int radius = 30;
	private int diameter = radius * 2;
	private Other other;
	
	public void startup(){
		other = new Other();
		animator.include(other);
	}
	public void draw(int x, int y){
		
		screen.setColor(Color.green);
		screen.fillOval(x, y, diameter, diameter);
		
		int cx = other.getX();
		int cy = other.getY();
		
		
		
		//when ball touches Other, other half sizes
		
		double dist = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		int riteRadius = other.getRadius(); 
		if (dist < (radius + riteRadius)) {
			{ other.getSmaller(); }
		}
		
	}//end draw method
	
}//end Getit class
