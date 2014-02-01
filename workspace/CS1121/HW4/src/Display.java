import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * A class that inherits from JPanel (and is its own listener).
 */
public class Display extends JPanel implements MouseListener, ActionListener {

	// properties 
	private final int radius = 3;
	private final int diameter = radius*2;
	private int x,y;
	private int count = 0;
	private int index = -5;
	private boolean drawP = false;
	private boolean selectedP = false;
	private JButton draw;
	private JButton selected;
	private JButton reorder;
	private JButton reset;


	// initialize coordinate arrays
	int [] xcoord = new int[10];
	int [] ycoord = new int[10];





	/**
	 * Set up display.
	 */
	public Display() {

		setPreferredSize(new Dimension(500,500));
		setBackground(Color.gray);
		this.addMouseListener(this);


	} // end of constructor

	/*
	 * set up gui
	 */
	public Display(JButton d, JButton m, JButton ro, JButton rs) {


		//set display dimentions
		setPreferredSize(new Dimension(500,500));
		setBackground(Color.gray);
		this.addMouseListener(this);
		//save references to buttons
		draw = d;
		selected = m;
		reorder = ro;
		reset = rs;
		//add listeners to buttons
		d.addActionListener(this);
		m.addActionListener(this);
		ro.addActionListener(this);
		rs.addActionListener(this);
	}


	public void paintComponent(Graphics g) {


		super.paintComponent(g);


		//if a dot is clicked, paint it green, otherwise paint it black
		for (int i = 0; i<count; i=i+1 ){
			if(i == index){
				g.setColor(Color.green);
				g.fillOval(xcoord[i]-2,ycoord[i]-2,diameter,diameter);
			}
			else{

				g.setColor(Color.black);
				g.fillOval(xcoord[i]-2,ycoord[i]-2,diameter,diameter);
			}

		}
		//draw line between the points
		for (int a = 0; a<count-1; a+=1){
			int xa1 = xcoord[a];
			int ya1 = ycoord[a];
			int xa2 = xcoord[a+1];
			int ya2 = ycoord[a+1];


			// draw line
			g.setColor(Color.red);
			g.drawLine(xa1, ya1, xa2, ya2);
		}




	} // end of paintComponent method


	public void mouseClicked(MouseEvent event) {

		//if draw button clicked, allow draw
		if(drawP == true){
			if(count < 10){
				index=-5;
				xcoord[count] = event.getX();
				ycoord[count] = event.getY();
				count+=1;
				repaint();

			}

		}


		//if the button is selected, move it to the location of the next click
		if(index >= 0){
			x = event.getX();
			y = event.getY();
			if(index >=0 && index <= 10){
				xcoord[index] = x;
				ycoord[index] = y;
				index = -5;
				repaint();
			}
		}
		//if the select/move button is pushed then allow to select
		else if(selectedP == true){
			x = event.getX();
			y = event.getY();
		//if the dot is clicked, then turn green and allow to move
			for(int i = 0; i<count; i+=1 ){
				int xx = xcoord[i];
				int yy = ycoord[i];
				double dist = Math.sqrt((xx-x)*(xx-x)+(yy-y)*(yy-y));
				if(dist <= radius){
					index = i;
				}
			}
			repaint();
		}
	} // end of mouseClicked method

	/**
	 * Change background color
	 *
	 * @param event unused.
	 */
	public void mouseEntered(MouseEvent event) {
		
		setBackground(Color.white);

	} // end of mouseEntered method

	/**
	 * Change background color
	 *
	 * @param event unused.
	 */
	public void mouseExited(MouseEvent event) {
		setBackground(Color.gray);

	} // end of mouseExited method

	public void mousePressed(MouseEvent event) {

	} // end of mousePressed method

	public void mouseReleased(MouseEvent event) {

	} // end of mouseReleased method
	
	public void actionPerformed(ActionEvent e) {
		//if draw is pushed, allow to draw and only draw
		if(e.getSource() == draw){
			drawP = true;
			selectedP = false;
		}
		//if select/move is pushed, allow only to select and move
		if(e.getSource() == selected){
			selectedP = true;
			drawP = false;
		}
		//if reorder is pushed then find the closest path between all of the points
		if(e.getSource() == reorder){
			drawP = false;
			selectedP = false;
			int startAt = 0;
			int smallPos = startAt;
			//find smallest path 
			for (int i = startAt; i < count; i += 1) {
				double smallest = 10000;
				for (int j = i+1; j < count; j += 1) {
					double dotDist = Math.sqrt((xcoord[i]-xcoord[j])*(xcoord[i]-xcoord[j])+(ycoord[i]-ycoord[j])*(ycoord[i]-ycoord[j]));
					if (dotDist < smallest) {
						smallest = dotDist;
						smallPos = j;

					}
				}

				//replace smallest
				if (i+1 < count) {
					int tempx = xcoord[smallPos];
					int tempy = ycoord[smallPos];
					xcoord[smallPos] = xcoord[i+1];
					ycoord[smallPos] = ycoord[i+1];
					xcoord[i+1] = tempx;
					ycoord[i+1] = tempy;
				}


			}
			repaint();
		}
		if (e.getSource() == reset){



		}

	}
	/*
	 * reset method 
	 */
	public void reset(){
		for (int i = 0; i<count; i+=1){
			xcoord[i]= 0;
			ycoord[1]= 0;
		}

		drawP = false;
		selectedP = false;
		x=0;
		y=0;
		count = 0;
		index = -5;
		drawP = false;
		selectedP = false;
		repaint();
		
	}


} // end of Display class
