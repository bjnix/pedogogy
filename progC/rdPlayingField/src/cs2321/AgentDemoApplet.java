package cs2321;

import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
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

/**
 *@author cjricker
 *@author bjnix
 */
public class AgentDemoApplet extends JApplet implements ActionListener, KeyListener {
	
	//TODO
	public final static int NUMBER_OF_POKEMON = 2;

	private ArraySequence<Pokemon> p1_PKMN = null;
	private ArraySequence<Pokemon> p2_PKMN = null;
	
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
	
	private ArraySequence<Picture> pictures = new ArraySequence<Picture>();
	public static int SEL_X = 125;
	public static int SEL_Y = 125;

	public void init(){
		
		// Loads the images
		ImageLoader images = new ImageLoader();
		try{
			pictures.addLast(new Picture(getImage(getCodeBase(),"../images/Kanto.jpg"),0,0)); // Map
			pictures.getLast().setAbsoluteSize(1000, 668);
			pictures.addLast(new Picture(getImage(getCodeBase(),"../images/City.png"),0,0)); // City
			pictures.getLast().setAbsoluteSize(16, 16);
			pictures.addLast(new Picture(getImage(getCodeBase(),("../images/CitySelected.png")),0,0)); // Selected City
			pictures.getLast().setAbsoluteSize(16, 16);
			
			//first selection screen read-in
			Image pokeSel1 = getImage(getCodeBase(),("../images/PokeSelect1.jpg"));
			pictures.addLast(new Picture(pokeSel1,320,590));//Add Pikachu 3
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(230,230);
			
			pictures.addLast(new Picture(pokeSel1,560,922));//add Ninetails 4
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(370,370);
			
			pictures.addLast(new Picture(pokeSel1,548,0));//add Blastoise 5
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(430,430);
			
			pictures.addLast(new Picture(pokeSel1,920,1788));//add Abra 6
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(306,306);
			
			pictures.addLast(new Picture(pokeSel1,1956,1338));//add Parasect 7
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(386,386);
			
			pictures.addLast(new Picture(pokeSel1,2307,426));//add Pidgeot 8
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(465,465);
			
			pictures.addLast(new Picture(pokeSel1,3537,886));//add Sandslash 9
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(352,352);
		
			
			//second selection screen file read-in
			Image pokeSel2 = getImage(getCodeBase(),("../images/PokeSelect2.jpg"));
			
			pictures.addLast(new Picture(pokeSel2,420,2248));//add Mewtwo 10
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(408,408);
			
			pictures.addLast(new Picture(pokeSel2,3905,575));//add Gastly 11
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(240,240);
			
			pictures.addLast(new Picture(pokeSel2,2211,1330));//add Scyther 12
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(402,402);
			
			pictures.addLast(new Picture(pokeSel2,2392,405));//add Onyx 13
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(450,450);
			
			pictures.addLast(new Picture(pokeSel2,66,1330));//add Gyarados 14 
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(435,435);

			pictures.addLast(new Picture(pokeSel2,2585,2180));//add Articuno 15
			pictures.getLast().setDim(SEL_X, SEL_Y); pictures.getLast().setSourceDim(473,473);
			
			
			
			// Begin adding pokemon icons
			Image allPokemon = getImage(getCodeBase(),("../images/AllPokemon.png"));
			pictures.addLast(new Picture(allPokemon,1932,18));//add Pikachu 16
			pictures.getLast().setDim(46, 45);pictures.getLast().setSourceDim(46, 45);
			
			pictures.addLast(new Picture(allPokemon,961,79)); //add Ninetails 17
			pictures.getLast().setDim(77,74);pictures.getLast().setSourceDim(77, 74);

			pictures.addLast(new Picture(allPokemon,646,9));//add Blastoise 18
			pictures.getLast().setDim(68, 63);pictures.getLast().setSourceDim(68, 63);

			pictures.addLast(new Picture(allPokemon,1685,92));//add Parasect 19
			pictures.getLast().setDim(57, 57);pictures.getLast().setSourceDim(57, 57);

			pictures.addLast(new Picture(allPokemon,974,178));//add Abra 20
			pictures.getLast().setDim(52, 44);pictures.getLast().setSourceDim(52, 44);

			pictures.addLast(new Picture(allPokemon,1371,6));//add Pidgeot 21  
			pictures.getLast().setDim(53, 66);pictures.getLast().setSourceDim(53, 66);

			pictures.addLast(new Picture(allPokemon,172,99));//add Sandslash 22
			pictures.getLast().setDim(54, 48);pictures.getLast().setSourceDim(54, 48);
			
			pictures.addLast(new Picture(allPokemon, 1929, 410));//add Mewtwo 23
			pictures.getLast().setDim(67, 65); pictures.getLast().setSourceDim(67, 65);
			
			pictures.addLast(new Picture(allPokemon, 1286, 248));//add Gastly 24
			pictures.getLast().setDim(69, 54); pictures.getLast().setSourceDim(69, 54);
			
			pictures.addLast(new Picture(allPokemon, 1762,330));//add Scyther 25
			pictures.getLast().setDim(71,60); pictures.getLast().setSourceDim(71,60);
			
			pictures.addLast(new Picture(allPokemon, 1525,243));//add Onyx 26
			pictures.getLast().setDim(72, 73); pictures.getLast().setSourceDim(72, 73);
			
			pictures.addLast(new Picture(allPokemon, 320, 400));//add Gyarados 27
			pictures.getLast().setDim(79,80); pictures.getLast().setSourceDim(79, 80);
			
			pictures.addLast(new Picture(allPokemon, 1446, 404));//Articuno 28 
			pictures.getLast().setDim(69,71); pictures.getLast().setSourceDim(69,71);
			
			
			// Selection Screen Graphics
			pictures.addLast(new Picture(getImage(getCodeBase(),("../images/pokeBanner.jpg")),0,0)); // pokeBanner 29
			pictures.getLast().setAbsoluteSize(800, 209);
			
			pictures.addLast(new Picture(getImage(getCodeBase(),("../images/p1Select.png")),0,0)); // p1 selection token 30
			pictures.getLast().setAbsoluteSize(30, 30);
			
			pictures.addLast(new Picture(getImage(getCodeBase(),("../images/p2Select.png")),0,0)); // p2 selection token 31
			pictures.getLast().setAbsoluteSize(30, 30);
			
			pictures.addLast(new Picture(getImage(getCodeBase(),("../images/RareCandy.png")),0,0)); // rare candy 32
			pictures.getLast().setDim(75,70); pictures.getLast().setSourceDim(271, 229);
			
		} catch (IllegalArgumentException e4){
			System.out.println("error IllegalArgumentException, something really weird happened");
		}
		
		// Starts up the selection screen
		pokeSelect = new PokeSelectionScreen(this, pictures);
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
		display.setPictures(pictures);

		
		
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
			display.setString("Calculating paths...");
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
			menuBar.remove(mreset);
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
		
		if (e.getSource() instanceof JMenuItem)
			selected = (JMenuItem)e.getSource();
		else
			return;
		
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
		// If "V" is pressed remove a city
		// If "B" is pressed add a route
		// If "N" is pressed remove a route
		
		display.repaint();
	}
	//--------------------------------------------------
	// End of Listener Methods
	//--------------------------------------------------


}
