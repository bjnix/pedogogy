//import java.awt.*;
import animator.*;
/**
 * 
 * @author Brent Nix
 * 
 * A program in which a person (yellow dot) attempts to get the money (green dot)
 * and is being chased by a snake (red dot)
 *
 */
public class HW2 extends Animated {
	
	public Money money;
	public Person person;
	public Snake snake;
	
	
	
	public void startup() {
	
	
		//create money 
		Money money = new Money();
		animator.include(money);
		//create person 
		Person person = new Person(money);
		animator.include(person);
		//create Snake 
		Snake snake = new Snake(person);
		animator.include(snake);
		
		
		//assign clicklistener
		animator.setClickListener(snake);
		
	}
	
	
}//end HW2 class