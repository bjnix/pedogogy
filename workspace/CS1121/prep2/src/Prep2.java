import java.awt.*;
import animator.*;

public class Prep2 extends Animated {

	private int xpos = 0;
	private int ypos = 0;
	private int direction = 1;
		
	//draw blue square
	public void draw(){
		
	screen.setColor(Color.blue);
	screen.fillRect(xpos, ypos, 100, 100);
	
	if (direction == 1) {
		if (xpos > animator.getSceneWidth()){
			direction = 2;
		}
		else{
     xpos = xpos + 1;
			}
		}
	
	if (direction == 2) {
		if (ypos > animator.getSceneHeight()){
			direction = 3;
		}
		else{
     ypos = ypos + 1;
			}
		}
	
	if (direction == 3) {
		if (xpos < animator.getSceneWidth()){
			direction = 4;
		}
		else{
     xpos = xpos - 1;
			}
		}
	
	if (direction == 4) {
		if (ypos < animator.getSceneHeight()){
			direction = 1;
		}
		else{
     ypos = ypos - 1;
			}
		}
	}//end of draw method
	
}//end of prep2 class
