package cs2321;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Info{
	
	private Infoable infoable = null;
	
	public Info(){

	}
	
	public void setInfoable(Infoable i){
		infoable = i;
	}
	
	public void draw(Graphics g, int x, int y, int edit){
		g.setColor(Color.yellow);
		x -= (x<=880)?0:120;
		y -= (y<=618)?0:50;
		g.fillRect(x,y, 120, 50);
		g.setColor(Color.black);
		g.drawString(infoable.showInfo(), x+20, y+25);
		g.drawString(edit==0? "Click to add city.":
				edit==1 && infoable instanceof City? "Click to remove.":
				edit==2 && infoable instanceof City? "Click to add route.": 
				edit==3 && infoable instanceof Route? "Click to remove." :
						   infoable instanceof Pokemon? "Power: " + (int)((Pokemon)infoable).getPower() + "" :
							   "", x+10, y+40);
	}

}
