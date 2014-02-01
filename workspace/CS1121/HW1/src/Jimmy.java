import java.awt.*;

import animator.*;

public class Jimmy extends Animated{

	public void draw(){
		screen.setColor(Color.blue);
		screen.fillRoundRect(30,20, 320, 250, 100, 100);
		
		screen.setColor(Color.white);
		screen.fillRoundRect(50, 40, 90, 60, 100, 100);
		screen.fillRoundRect(250, 40, 90, 60, 100, 100);
		
		screen.setColor(Color.black);
		screen.fillRoundRect(95, 65, 30, 30, 100, 100);
		screen.fillRoundRect(295, 65, 30, 30, 100, 100);
		
		screen.fillOval(110, 200, 60, 40);
	}
	}

