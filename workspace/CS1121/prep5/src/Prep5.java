
import animator.*;
import java.awt.*;
/**
 * Start up a game with a ship, asteroid and rockets.
 * 
 */
public class Prep5 extends Animated {
	/**
	 * create the asteroid and ship and add them to the animation.
	 */
	public void startup() {
		//create asteroid with the given initial position (100,100)
		Asteroid asteroid = new Asteroid(100,100);
		animator.include(asteroid);
		
		//create ship
		Ship ship = new Ship(asteroid);
		animator.include(ship);
		//assign clicklistener
		animator.setClickListener(ship);
		
	}//end of startup method
}//end of Prep5 class
