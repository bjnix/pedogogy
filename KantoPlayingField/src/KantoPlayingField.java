import java.awt.Color;
import java.awt.Container;
import java.awt.event.*;
import javax.swing.*;

public class KantoPlayingField extends JApplet implements MouseListener, ActionListener, KeyListener {
	
	private JMenuBar menubar = new JMenuBar();
	private JMenu menu = new JMenu();
	private JMenuItem button = new JMenuItem();
	private Display display = new Display();
	private SplashScreen splash;
	
	public void init(){
		new KantoPlayingField();
	}
	
	public KantoPlayingField(){
		
		splash = new SplashScreen();
		menubar.add(menu);
		menu.add(button);
		
		// Sets up the container
		Container window = getContentPane();
		window.setLayout(null);
//		window.setBackground(Color.black);
		window.setSize(800,600);
		window.setLocation(300,100);
		window.setVisible(true);
		setJMenuBar(menubar);
		menubar.setLocation(0, 130);
		menubar.setSize(600, 80);
		
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
