import java.awt.*;
import animator.*;

/**
 * 
 * @author brent nix, Dan Stevenson
 *
 */
/**
 * A program in which there are two balls, one that moves toward the last mouse click and the
 * other which moves according to the arrows
 * when the two touch they die
 */
public class Lab5 extends Animated {
	private Arrow arrow;
	private Click click;
	
	public void startup(){
		arrow = new Arrow();
		animator.include(arrow);
		
		click = new Click(arrow);
		animator.include(click);
		animator.setClickListener(click);
	}

}
