import java.awt.*;
import animator.*;

public class Triad extends Animated {

public void draw(){
	screen.setColor(Color.magenta);
	screen.fillRoundRect(animator.getSceneWidth()-340, animator.getSceneHeight()-250, 320, 250, 100, 100);
	
	screen.setColor(Color.white);
	screen.fillRoundRect(animator.getSceneWidth()-280, animator.getSceneHeight()-230, 90, 60, 100, 100);
	screen.fillRoundRect(animator.getSceneWidth()-130, animator.getSceneHeight()-230, 90, 60, 100, 100);
	
	screen.setColor(Color.black);
	screen.fillRoundRect(animator.getSceneWidth()-270, animator.getSceneHeight()-220, 30, 30, 100, 100);
	screen.fillRoundRect(animator.getSceneWidth()-120, animator.getSceneHeight()-220, 30, 30, 100, 100);
	
	screen.fillOval(animator.getSceneWidth()-180, animator.getSceneHeight()-80, 60, 40);
}
}
