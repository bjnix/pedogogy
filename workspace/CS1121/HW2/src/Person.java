import java.awt.*;
import animator.*;
/**
 * 
 *Person is controlled by the keyboard directional keys.
 *if Person touches Money, the score increases
 *if Person touches Snake, the game restarts
 *
 */
public class Person extends Animated{
	//named constants
	private final int radius = 10;
	private final int diameter = radius*2;

	//properties
	private int score = 0;
	private int xx = 100;
	private int yy = 40;
	private boolean hit = false;
	private boolean hitmoney = false;
	private Money money;
	public Person(Money m){
		money = m;
	}
	public void draw(int x, int y){
		screen.setColor(Color.white);
		screen.drawString("" + score, 10, 10);		
		animator.setBackgroundColor(Color.black);
		screen.setColor(Color.yellow);
		screen.fillOval(x-radius, y-radius, diameter, diameter);

		//save coordinates of Person
		xx = x;
		yy = y;

		//see if Person hits the Money
		int mx = money.getX();
		int my = money.getY();
		int mr = money.getRadius();
		double dist = Math.sqrt((x-mx)*(x-mx)+(y-my)*(y-my));
		//if person hits
		if (dist < radius + mr) {
			money.hit();
			hitmoney = true;
		}
		else{
			hitmoney = false;
		}
		if (hitmoney == true){
			score = score + 1;
			hitmoney = false;
		}
		if (hit == true){
			score = 0;
			hit = false;
		}
	}//end draw method
/**
 * retrieves Score value
 * @return
 */
	public int getScore(){
		return score;
	}
	/**
	 * retrieves x value of Person object
	 * @return
	 */
	public int getX(){
		return xx;
	
	}
	/**
	 * retrieves y value of Person object
	 * @return
	 */
	
	public int getY(){
		return yy;
	}
	/**
	 * retrieves radius of Person object
	 * @return
	 */
	public int getRadius(){
		return radius;
	}
	/**
	 * when called, tells Person that it has collided with Snake. 
	 */
	public void getHit(){
		hit = true;
	}

}//end Person class
