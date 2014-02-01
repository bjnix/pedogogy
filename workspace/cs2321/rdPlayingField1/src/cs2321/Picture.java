package cs2321;
import java.awt.Graphics;
import java.awt.Image;

/**
 * @author cjricker
 * 
 * A class that stores a picture of a given image and is able to draw itself on a given graphics object
 */
public class Picture {
	
	// Image and coordinates of the desired part
	private Image image;
	private int sourcex;
	private int sourcey;
	private int sourceWidth = 40;
	private int sourceHeight = 40;
	private int width = 40;
	private int height = 40;
	
	/**
	 * @param i The Image to be drawn
	 * @param initx The x coordinate of the source
	 * @param inity The y coordinate of the source
	 */
	public Picture(Image i,int initx, int inity){
		image = i;
		sourcex = initx;
		sourcey = inity;
	}
	
	public Image getImage(){
		return image;
	}
	
	public int getCenterX(){
		return width/2;
	}
	
	public int getHeight(){
		return height;
	}
	
	/**
	 * Draws a picture on a graphics object
	 * @param g The graphics object on which to draw
	 * @param x The x coordinate of the destination
	 * @param y The y coordinate of the destination
	 */
	public void draw(Graphics g,int x, int y){
		g.drawImage(image,x,y,x+width,y+height,sourcex,sourcey,sourcex+sourceWidth,sourcey+sourceHeight,null);
	}
	
	/**
	 * Sets the dimensions of the picture on the source
	 */
	public void setSourceDim(int a, int b){
		sourceWidth = a;
		sourceHeight = b;
	}
	
	/**
	 *  Sets the size of the picture on the destination
	 */
	public void setDim(int a, int b){
		width = a; 
		height = b;
	}
	
	/**
	 * Sets the size of the picture on BOTH the destination and the source
	 * @param a
	 * @param b
	 */
	public void setAbsoluteSize(int a, int b){
		setSourceDim(a,b);
		setDim(a,b);
	}
	
	public String toString(){
		return "" + sourcex + " , " + sourcey;
	}

}
