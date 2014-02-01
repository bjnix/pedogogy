import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class Buttons extends JApplet implements ActionListener {

	private int sizeb=120;
	private int highnum1=-1;
	private int highnum2=-1;
	private JButton butnt []= new JButton [sizeb*sizeb];

	private Color col [] = {Color.red,Color.yellow,Color.blue,
			Color.black,Color.white,Color.cyan,Color.green};
	private String co [] = {"red","yellow","blue",
			"black","white","cyan","green"};
	private JMenu other = new JMenu();
	private JMenuItem coln [] = new JMenuItem[7];
	private JMenuItem reset =new JMenuItem(); 
	private JMenuItem quit =new JMenuItem(); 
	private JMenuItem drawNine =new JMenuItem(); 
	private JMenuItem drawOne =new JMenuItem(); 
	private JMenuItem highlight =new JMenuItem(); 
	private Color pkdcl = Color.red;
	private boolean spread=true;
	private boolean single=false;
	private boolean high=true;
	private boolean high1=true;

	public Buttons() {

		// set up window
		Container window = getContentPane();
		window.setLayout(new FlowLayout());

		// add menus
		JMenuBar bar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenu color = new JMenu("Colors");
		other = new JMenu("palet");

		int cl=0;
		while(cl<co.length){
			coln[cl]= new JMenuItem(co[cl]);
			color.add(coln[cl]);
			coln[cl].addActionListener(this);
			cl+=1;
		}
		reset = new JMenuItem("reset");
		drawNine = new JMenuItem("3x3 square");
		drawOne = new JMenuItem("one square");
		highlight = new JMenuItem("dump");
		quit = new JMenuItem("quit");	

		bar.add(menu);
		menu.add(color);
		menu.add(other);
		other.add(drawNine);
		other.add(highlight);
		menu.add(reset);
		menu.add(quit);

		reset.addActionListener(this);
		drawNine.addActionListener(this);
		drawOne.addActionListener(this);
		highlight.addActionListener(this);

		quit.addActionListener(this);
		setJMenuBar(bar);

		// register listener on quit button


		// set default close operation (terminate entire program)
		//		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// set size and make it visible
		setSize(800,500);
		setVisible(true);
	} // end of constructor
	public void init(){
		Container window1 = getContentPane();
		window1.setLocation(600, 600);
		window1.setLayout(new FlowLayout());
		window1.setBackground(Color.BLACK);
		Container window = getContentPane();
		window.setPreferredSize(new Dimension(500,700));
		window.setLayout(new GridLayout(sizeb,sizeb));
		window.setBackground(Color.BLACK);
		int t=0;

		while(sizeb*sizeb>t){
			butnt[t]=new JButton();
			butnt[t].setBackground(Color.white);
			window.add(butnt[t]);
			butnt[t].addActionListener(this);
			t+=1;
		}



	}//end of init method



	public void actionPerformed(ActionEvent e) {

		int c=0;
		while(c<coln.length){
			if(e.getSource().equals(coln[c])){
				pkdcl=col[c];
				break;
			}
			else{c+=1;}
		}
		if (e.getSource().equals(reset)){
			this.resetm();
		}
		if (e.getSource().equals(drawNine)){
			this.sploch(2);
		}
		if (e.getSource().equals(drawOne)){
			this.sploch(1);
		}
		if (e.getSource().equals(highlight)){
			this.sploch(3);
		}
		if (e.getSource().equals(quit)){
		}
		int k=0;
		while (k<(sizeb*sizeb)){
			if(e.getSource().equals(butnt[k])){
				if (!spread){
					this.spread(k);
					break;
				}
				else if (!high){
					this.highlight(k);
					break;
				}
				else{
					butnt[k].setBackground(pkdcl);
					break;
				}
			}
			else {k+=1;}
		}
	}//end of action listener

	//resets buttons to white
	public void resetm(){
		int t=0;
		while(sizeb*sizeb>t){
			butnt[t].setBackground(Color.white);
			t+=1;
		}
	}//end of reset method

	public void sploch(int bp){
		if(bp==1&&!spread){
			spread=true;
			single=spread;
			high1=true;
			other.remove(drawOne);
			other.add(drawNine);
		}
		else if (bp==2&&!single){
			spread=false;
			single=true;
			high1=true;
			other.remove(drawNine);
			other.add(drawOne);
		}
		else if(bp==1&&!high){
			single=false;
			high1=true;
			high=true;
			other.remove(drawOne);
			other.add(highlight);
		}
		else if (bp==2&&!high){
			spread=false;
			single=true;
			high1=true;
			high=true;
			other.remove(drawNine);
			other.add(highlight);
		}
		else if(bp==3&&!spread){
			spread=true;
			high=false;
			other.remove(highlight);
			other.add(drawNine);
		}
		else if (bp==3&&!single){
			single=true;
			high=false;
			other.remove(highlight);
			other.add(drawOne);
		}
	}//end of size method

	public void highlight(int k){
		if (high1){
			highnum1=k;
			butnt[k].setBackground(pkdcl);
			high1=false;
		}
		else {
			highnum2=k;
			k=highnum1;
			if(highnum1>highnum2){
				k=highnum2;
				highnum2=highnum1;
				highnum1=k;
				
			}
			if(highnum2%sizeb<highnum1%sizeb){
				int change=highnum2%sizeb-highnum1%sizeb;
				highnum1+=change;
				highnum2-=change;
				k=highnum1;
			}
			if(highnum1<highnum2){
				int mult=highnum2%sizeb-highnum1%sizeb;
				while(k<=highnum2){
					if ((highnum2-k)%sizeb==0){
						butnt[k].setBackground(pkdcl);
						k+=sizeb;
						k-=mult;
					}
					else {
						butnt[k].setBackground(pkdcl);
						k+=1;
					}
				}
			}
			

			high1=true;
		}
	}//end of highlight method

	public void spread(int k){
		if(k>sizeb){
			butnt[k-sizeb].setBackground(pkdcl);
		}
		if(k<sizeb*(sizeb-1)){
			butnt[k+sizeb].setBackground(pkdcl);
		}
		if(k%sizeb!=0){
			butnt[k-1].setBackground(pkdcl);
		}
		if(k%sizeb!=sizeb-1){
			butnt[k+1].setBackground(pkdcl);
		}
		if(k%sizeb!=0&&k>sizeb){
			butnt[k-sizeb-1].setBackground(pkdcl);
		}
		if(k%sizeb!=0&&k<sizeb*(sizeb-1)){
			butnt[k+sizeb-1].setBackground(pkdcl);
		}
		if(k%sizeb!=sizeb-1&&k>sizeb){
			butnt[k-sizeb+1].setBackground(pkdcl);
		}
		if(k%sizeb!=sizeb-1&&k<sizeb*(sizeb-1)){
			butnt[k+sizeb+1].setBackground(pkdcl);
		}
		butnt[k].setBackground(pkdcl);

	}//End of spread method

}//end of Buttons Class
