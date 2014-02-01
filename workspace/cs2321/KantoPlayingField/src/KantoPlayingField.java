import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

public class KantoPlayingField extends JApplet implements MouseListener, ActionListener, KeyListener {
	private String[] p1_PM;
	private String[] p2_PM;
	//menubar
	private JMenuBar menubar = new JMenuBar();
	//menus
	private JMenu mPlace = new JMenu("Place");
	private JMenu mPoke1 = new JMenu("P1 PkMn"); 
	private JMenu mPoke2 = new JMenu("P2 PkMn");
	//menuitems
	//-place
	private JMenuItem place_rPoint = new JMenuItem("Rally Point");
	private JMenuItem place_path = new JMenuItem("Path");
	//-player 1 pkmn
	private JMenuItem poke1_1 = new JMenuItem(p1_PM[1]);
	private JMenuItem poke1_2 = new JMenuItem(p1_PM[2]);
	private JMenuItem poke1_3 = new JMenuItem(p1_PM[3]);
	private JMenuItem poke1_4 = new JMenuItem(p1_PM[4]);
	private JMenuItem poke1_5 = new JMenuItem(p1_PM[5]);
	private JMenuItem poke1_6 = new JMenuItem(p1_PM[6]);
	//-player 2 pkmn
	private JMenuItem poke2_1 = new JMenuItem(p2_PM[1]);
	private JMenuItem poke2_2 = new JMenuItem(p2_PM[2]);
	private JMenuItem poke2_3 = new JMenuItem(p2_PM[3]);
	private JMenuItem poke2_4 = new JMenuItem(p2_PM[4]);
	private JMenuItem poke2_5 = new JMenuItem(p2_PM[5]);
	private JMenuItem poke2_6 = new JMenuItem(p2_PM[6]);
	
	private Display display = new Display();
	private SplashScreen splash;
	
	
	
	public void init(){
		new KantoPlayingField();
		
	}
	
	public KantoPlayingField(){
		
		splash = new SplashScreen();
		//add menus to bar
		menubar.add(mPlace);
		menubar.add(mPoke1);
		menubar.add(mPoke2);
		//add options to menus
		//place menu
		mPlace.add(place_rPoint);
		mPlace.add(place_path);
		//player 1 pkmn
//		mPoke1.add(a);
		
		// Sets up the container
		Container window = getContentPane();
		window.setLayout(null);
//		window.setBackground(Color.black);
		window.setSize(800,600);
		window.setLocation(300,100);
		window.setVisible(true);
		setJMenuBar(menubar);
//		menubar.setLocation(0, 130);
//		menubar.setSize(600, 80);
		
	}

	//--------------------------------------------------
	// Listener Methods
	//--------------------------------------------------
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//--------------------------------------------------
	// End of Listener Methods
	//--------------------------------------------------

}
