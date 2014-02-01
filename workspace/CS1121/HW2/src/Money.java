import java.awt.*;
import animator.*;
/**
 * 
 *A dot representing money that whose coordinates are randomly redrawn after
 *coming in contact with Person
 */
public class Money extends Animated{

	//named constants
	private final int radius = 5;
	private final int diameter = radius*2;
	
	//properties
	private int randX = 20;
	private int randY = 30;
	private boolean hit = false;
	private int scenewidth;
	private int sceneheight;
	
	
	public void draw() {
		//draw money
		screen.setColor(Color.green);
		screen.fillOval(randX, randY, diameter, diameter);
	
		scenewidth = animator.getSceneWidth();
		sceneheight = animator.getSceneHeight();
		if(hit == true){
			randX = (int)(Math.random()*scenewidth-radius);
			randY = (int)(Math.random()*sceneheight-radius);
			hit =false;
		}
		
		
		
	}//end draw method
	
	public int getX(){
		return randX;
	}
	public int getY(){
		return randY;
	}
	public int getRadius(){
		return radius;
	}
	public void hit(){
		hit = true;
		
	}
	
}//end Money class
