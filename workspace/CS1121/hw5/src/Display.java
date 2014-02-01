import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * A class that inherits from JPanel (and is its own listener).
 */
public class Display extends JPanel implements MouseListener, ActionListener {

	// properties 

	private int x,y = 0;
	private int diameter;
	private int radius = diameter/2;
	private JButton enter;
	private JButton clear;
	private JButton clos0;
	private JButton clos2;
	private JButton reset;
	private JTextField xInput;
	private JTextField yInput;
	private JTextField zInput;
	private int smallPos = -5;
	private int smallPos2 = -5;
	private int closePos = -5;


	// initialize the coordinate array and the point vector

	private Vector<int[]> points = new Vector<int []>(); 


	/*
	 * set up gui
	 */
	public Display(JTextField xt, JTextField yt, JTextField zt, JButton e, JButton c, JButton c0, JButton c2, JButton res) {


		//set display dimentions
		setPreferredSize(new Dimension(500,500));
		setBackground(Color.white);
		this.addMouseListener(this);
		//save references to buttons
		xInput = xt;
		yInput = yt;
		zInput = zt;
		enter = e;
		clear = c;
		clos0 = c0;
		clos2 = c2;
		reset = res;

		//add action listeners 
		e.addActionListener(this);
		c.addActionListener(this);
		c0.addActionListener(this);
		c2.addActionListener(this);
		res.addActionListener(this);


	}
	/**
	 * Process text input.
	 * @param input The input string from the text field.
	 */



	public void paintComponent(Graphics g) {


		super.paintComponent(g);


		
		
		
		for (int i = 0; i<points.size(); i=i+1 ){
			//if a dot tagged as being one of the two closest,paint it blue
			if (i==smallPos||i==smallPos2){
			g.setColor(Color.blue);
			g.fillOval(points.get(i)[0],500-points.get(i)[1],(points.get(i)[2]/50+1)*2,(points.get(i)[2]/50+1)*2);
			}
			//if a dot is tagged as being the closest to 0,0,0, paint it red
			else if (i==closePos){
				g.setColor(Color.red);
				g.fillOval(points.get(i)[0],500-points.get(i)[1],(points.get(i)[2]/50+1)*2,(points.get(i)[2]/50+1)*2);
			}
			//otherwise paint it black
			else{
			g.setColor(Color.black);
			g.fillOval(points.get(i)[0],500-points.get(i)[1],(points.get(i)[2]/50+1)*2,(points.get(i)[2]/50+1)*2);
			}
		}






	} // end of paintComponent method


	public void mouseClicked(MouseEvent event) {

		x = event.getX();
		y = event.getY();
		//if the dot is clicked, then delete the biggest one
		for(int i = 0; i<points.size(); i+=1 ){
			int xx = points.get(i)[0];
			int yy = 500 - points.get(i)[1];
			radius = points.get(i)[2]/50+1;
			double dist = Math.sqrt((xx+radius-x)*(xx+radius-x)+(yy+radius-y)*(yy+radius-y));
			if(dist <= radius){
				points.remove(i);


			}

			repaint();
		}
	} // end of mouseClicked method


	public void mouseEntered(MouseEvent event) {

	}// end of mouseEntered method
	public void mouseExited(MouseEvent event) {

	} // end of mouseExited method

	public void mousePressed(MouseEvent event) {

	} // end of mousePressed method

	public void mouseReleased(MouseEvent event) {

	} // end of mouseReleased method

	public void actionPerformed(ActionEvent e) {
		//if draw is pushed, allow to draw and only draw
		if(e.getSource() == enter){

			int[] coord = new int[3];

			int xco = Integer.parseInt(xInput.getText());
			int yco = Integer.parseInt(yInput.getText());
			int zco = Integer.parseInt(zInput.getText());
			coord[0] = xco;
			coord[1] = yco;
			coord[2] = zco;
			points.add(coord);
			xInput.setText("");
			yInput.setText("");
			zInput.setText("");
			

		}


		//if clear is pushed, clear x,y,z JTextFields
		if(e.getSource() == clear){
			xInput.setText("");
			yInput.setText("");
			zInput.setText("");

		}
		//if "Closest to 0,0,0," is pushed then find the dot closest to 0,0,0
		if(e.getSource() == clos0){
			
			double smallest = 10000;
			for(int i = 0; i < points.size(); i += 1){
				int x1 = points.get(i)[0];
				int y1 = 500-points.get(i)[1];
				int z1 = points.get(i)[2];
				double dotDist = Math.sqrt((x1)*(x1)+(y1)*(y1)+(z1)*(z1));
				
				if (dotDist < smallest){
					smallest = dotDist;
					closePos = i;
				}
			}
		}
		if(e.getSource() == clos2){
			
			//find smallest path 

			for (int i = 0; i < points.size(); i += 1) {
				double smallest = 10000;
				int x1 = points.get(i)[0];
				int y1 = 500-points.get(i)[1];
				int z1 = points.get(i)[2];
				for (int j = i+1; j < points.size(); j += 1) {
					int x2 = points.get(j)[0];
					int y2 = 500-points.get(j)[1];
					int z2 = points.get(j)[2];
					double dotDist = Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)+(z2-z1)*(z2-z1));
					if (dotDist > smallest) {
						smallest = dotDist;
						smallPos2 = i;
						smallPos = j;
					}
					System.out.println("smallPos " + smallPos);
					System.out.println("smallPos2 " + smallPos2);
				}
				
			}

					
					repaint();
				}
			if (e.getSource() == reset){

				points.clear();
				x=0;
				y=0;
				smallPos = -5;
				smallPos2 = -5;
				closePos = -5;
				xInput.setText("");
				yInput.setText("");
				zInput.setText("");

				repaint();
			}
			repaint();
		}
	

} // end of Display class

////replace smallest
//if (i+1 < count) {
//	int tempx = points.get(smallPos);
//	int tempy = ycoord[smallPos];
//	xcoord[smallPos] = xcoord[i+1];
//	ycoord[smallPos] = ycoord[i+1];
//	xcoord[i+1] = tempx;
//	ycoord[i+1] = tempy;
//}


