import java.awt.*;
import animator.*;
/**
 * 
 *A red ball that chases Person
 *when Person touches Money, Snake increases speed and size
 *when clicked on with the mouse, snake pauses for 100 calls of the draw method 
 *and is no longer a threat
 */
public class Snake extends Animated implements ClickListener {

	//named constant
	private final int d = 30;
	
	//properties
	private int x = 200;
	private int y = 400;
	private Person person;
	private int grow = 0;
	private int speed = 0;
	private int t = 0;
	private int xx;
	private int yy;
	private int rr;
	
	
	public Snake(Person p){
		person = p;
	}//end of constructor




	public void draw(){
		speed = person.getScore();
		grow = person.getScore()*6;
		int diameter = d+grow;
		int radius = diameter/2;
		screen.setColor(Color.red);
		screen.fillOval(x-radius, y-radius, diameter, diameter);

		//save radius for Click method
		rr = radius;
		
		//stop snake for 100 counts
		if (t>0){
			t = t-1;
		}

		//save x and y for click method
		xx = x;
		yy = y;
		
		//get the position of Person
		int px = person.getX();
		int py = person.getY();



		//find dist and hyp
		double xdist = x - px;
		double ydist = y - py;
		double hyp = Math.sqrt(xdist*xdist + ydist*ydist);
		double xrate = xdist/hyp;
		double yrate = ydist/hyp;
		
		//move in the direction of Person
		if ( t == 0){

			if (speed != 0){
			x = (int)(x - xrate * (speed+1));
			y = (int)(y - yrate * (speed+1));
			}
			//see if Snake hits Person
			int pr = person.getRadius();
			double dist = Math.sqrt((x-px)*(x-px)+(y-py)*(y-py));
			if (dist < (rr + pr)) {

				person.getHit();
				reset();
			}//end if action

		}//end if counter

	}//end draw method

	public void click(int x, int y){

		double dist = Math.sqrt((xx-x)*(xx-x)+(yy-y)*(yy-y));
		if (dist <= rr ) {
		t = 100;
		}//end if
		
	}//end click method

	public void reset(){
		x = 200;
		y = 400;

	}//end reset method
	
	public int getX(){
		return x;
	}//end getX method
	
	public int getY(){
		return y;
	}//end getY method
	

}//end Snake class
