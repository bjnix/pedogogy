package cs2321;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JApplet;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

/**
 * #TODO: Project Part C: complete this class 
 * add the necessary html code in comment block at the top of the file. 
 * 
 * Note, you may not need all methods functioning. 
 * 
 */

public class AgentDemoApplet extends JApplet implements ActionListener, KeyListener {
	
	//TODO
	public final static int NUMBER_OF_POKEMON = 2;

	private ArraySequence<Pokemon> p1_PKMN = null;
	private ArraySequence<Pokemon> p2_PKMN = null;
	
	private String[] p1_PM = {"1","2","3","4","5","6"};
	private String[] p2_PM = {"1","2","3","4","5","6"};
	//--------
	//menubar
	//--------
	private JMenuBar menuBar = new JMenuBar();

	//---------
	//menus
	//---------
	private JMenuItem mstart = new JMenuItem("Start!");
	private JMenuItem mreset = new JMenuItem("Reset");
	private JMenuItem mCity = new JMenuItem("Place City");
	private JMenuItem mRemCity = new JMenuItem("Remove City");
	private JMenuItem mPath = new JMenuItem("Place Path");
	private JMenuItem mRemPath = new JMenuItem("Remove Path");
	private JMenuItem mCandy = new JMenuItem("Place Rare Candy");
	private JMenuItem selected = null;
	private JMenu mPoke1 = new JMenu("P1 PkMn"); 
	private JMenu mPoke2 = new JMenu("P2 PkMn");
	private JTextField txt = new JTextField();
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	
	private Color DEFAULT_COLOR;
	private final char ESCAPE = '';

	//-player 1 pkmn
	protected JMenuItem[] p1_pokemon = new JMenuItem[6];

	//end player 1 pkmn

	//-player 2 pkmn
	protected JMenuItem[] p2_pokemon = new JMenuItem[6];

	//end player 2 pkmn

	private PlayingFieldConstructionPanel display = new PlayingFieldConstructionPanel();
	private SplashScreen splash;
	private PokeSelectionScreen pokeSelect;

	public void init(){
		
		// Loads the images
		ImageLoader images = new ImageLoader();
		
		// Starts up the selection screen
		pokeSelect = new PokeSelectionScreen(this, images.getPictures());
		pokeSelect.setVisible(true);
		
		// Starts the splash screen
		splash = new SplashScreen();
		splash.setVisible(true);
		
		
		// Sets up the container
		Container window = getContentPane();
		window.setLayout(null);
		setSize(1000,695);
		setJMenuBar(menuBar);

		//----------------
		//add menus to bar
		//----------------
		menuBar.add(mstart);
		menuBar.add(mCity);
		menuBar.add(mRemCity);
		menuBar.add(mPath);
		menuBar.add(mRemPath);
		menuBar.add(mCandy);
		menuBar.add(mPoke1);
		menuBar.add(mPoke2);

		// Set up default color
		DEFAULT_COLOR = mCity.getBackground();

		//---------------------
		//add options to menus
		//---------------------

		//-------------------------
		//Add listeners to buttons
		//-------------------------
		mstart.addActionListener(this);
		mCity.addActionListener(this);
		mRemCity.addActionListener(this);
		mPath.addActionListener(this);
		mRemPath.addActionListener(this);
		mCandy.addActionListener(this);

		// Set key listener functionality
		window.add(txt);
		txt.addKeyListener(this);

		// Sets up the playing field
		window.add(display);
		display.setSize(1001,669);
		display.setLocation(0,0);
		display.setPictures(images.getPictures());

		
		
//		Cursor cursor = toolkit.createCustomCursor(pictures.get(4).getImage(), new Point(31,0), "New Cursor");
//		display.setCursor(cursor);
		
		mCity.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mstart.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mRemCity.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mPath.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mRemPath.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mCandy.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mPoke1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		mPoke2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	}
	
	public void setPokemon(ArraySequence<Pokemon> p1, ArraySequence<Pokemon> p2){
		p1_PKMN = p1;
		p2_PKMN = p2;
		
		//TODO
		for (int i = 0; i < NUMBER_OF_POKEMON; i++){
			p1_pokemon[i] = new JMenuItem(p1_PKMN.get(i).showInfo());
			p1_pokemon[i].addActionListener(this);
			mPoke1.add(p1_pokemon[i]);
			p2_pokemon[i] = new JMenuItem(p2_PKMN.get(i).showInfo());
			p2_pokemon[i].addActionListener(this);
			mPoke2.add(p2_pokemon[i]);
		}
	}

	//--------------------------------------------------
	// Listener Methods
	//--------------------------------------------------

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == null)
			return;

		if (selected != null){
			selected.setBackground(DEFAULT_COLOR);
			selected.setForeground(Color.black);
		}
		
		if (e.getSource().equals(txt)){
			selected = null;
			return;
		}
		
		if (e.getSource().equals(mstart)){
			menuBar.add(mreset);
			mreset.addActionListener(this);
			mreset.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			menuBar.remove(mstart);
			menuBar.remove(mCity);
			mCity.removeKeyListener(this);
			menuBar.remove(mRemCity);
			mRemCity.removeKeyListener(this);
			menuBar.remove(mPath);
			mPath.removeKeyListener(this);
			menuBar.remove(mRemPath);
			mRemPath.removeKeyListener(this);
			menuBar.remove(mCandy);
			menuBar.remove(mPoke1);
			menuBar.remove(mPoke2);
			menuBar.validate();
			try {
				display.start();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		
		if (e.getSource().equals(mreset)){
			menuBar.add(mstart);
			menuBar.add(mreset);	
			menuBar.add(mCity);
			menuBar.add(mRemCity);
			menuBar.add(mPath);
			menuBar.add(mRemPath);
			menuBar.add(mCandy);
			menuBar.add(mPoke1);
			menuBar.add(mPoke2);
			menuBar.validate();
			display.reset();
		}
		
		if (e.getSource().equals(mCandy)){
			display.editField(7, true);
		}

		if(e.getSource().equals(mCity)){
			display.editField(0, true);
			selected = mCity;
		}

		if(e.getSource().equals(mPath)){
			display.editField(2, true);
			selected = mPath;
		}

		if(e.getSource().equals(mRemCity)){
			display.editField(1, true);
			selected = mRemCity;
		}

		if(e.getSource().equals(mRemPath)){
			display.editField(3, true);
			selected = mRemPath;
		}
		
		selected = (JMenuItem)e.getSource();
		
		//TODO
		for (int i = 0; i < NUMBER_OF_POKEMON; i++){
			
			if (selected.equals(p1_pokemon[i]))
				display.selectPokemon(p1_PKMN.get(i));
			
			if (selected.equals(p2_pokemon[i]))
				display.selectPokemon(p2_PKMN.get(i));
		}

		if (selected != null && selected != mreset){
			selected.setBackground(Color.gray);
			selected.setForeground(Color.white);
		}
		
		repaint();
		display.repaint();
	}
	public void keyPressed(KeyEvent e) {
		
	}
	public void keyReleased(KeyEvent e) {
		
	}
	public void keyTyped(KeyEvent k) {
		
		char source = k.getKeyChar();
		display.editField(source=='c'?0: source=='v'?1: source=='b'?2: source=='n'?3: k.getKeyChar()==ESCAPE? 5 : -1, true);
		ActionEvent e = new ActionEvent(source=='c'?mCity: source=='v'?mRemCity: source=='b'?mPath: source=='n'?mRemPath:
			k.getKeyChar()==ESCAPE? txt : -1, 1, "clicked");
		actionPerformed(e);
		// If "C" is pressed add a city
		// If "X" is pressed remove a city
		// If "Z" is pressed add a route
		// If "V" is pressed remove a route
		
		display.repaint();
	}
	//--------------------------------------------------
	// End of Listener Methods
	//--------------------------------------------------


}
