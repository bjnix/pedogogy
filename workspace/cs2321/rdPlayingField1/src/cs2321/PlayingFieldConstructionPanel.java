package cs2321;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

/**
 * #TODO: Project Part C: complete this class 
 * This the panel for constructing the playing field
 * adding agents
 * and running the simulations
 * 
 * Note, you may not need all methods functioning. 
 * 
 */
public class PlayingFieldConstructionPanel extends JPanel implements MouseListener, MouseMotionListener{

	private ArraySequence<Picture> pictures;
	private LinkedSequence<Pokemon> pokemon = new LinkedSequence<Pokemon>();
	private Pokemon selected_pokemon;
	private PokemonGraph graph = new PokemonGraph();
	private int numCities;
	private int numRoutes;
	private Info info;
	private int mouseX;
	private int mouseY;

	private boolean[] edit = new boolean[26];

	private boolean one_city_selected = false;
	private City selected_city;
	private City end;

	private static int ADD_CITY = 0;
	private static int REMOVE_CITY = 1;
	private static int ADD_ROUTE = 2;
	private static int REMOVE_ROUTE = 3;
	private static int ESCAPE = 5;
	private static int ADD_POKEMON = 6;
	private static int SELECT_END = 7;

	public PlayingFieldConstructionPanel(){
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	/**
	 * Gives the display access to the pictures
	 * 
	 * @param p
	 */
	public void setPictures(ArraySequence<Picture> p){
		pictures = p;
	}

	public void start() throws InterruptedException{

		if (end == null){
			for (City city : graph.vertices()){
				if (city.getName().equals("City 0"))
					end = city;
			}
		}

		Schedule schedule = new Schedule(this,pokemon);

		for (Pokemon poke : pokemon){
			poke.setSchedule(schedule);
			poke.generatePath(end);
			new Thread(poke).start();
		}

		Thread.sleep(1000);
		schedule.start();
		repaint();

	}

	//--------------------------------------------
	// Paint Component
	//--------------------------------------------
	public void paintComponent(Graphics g){

		super.paintComponent(g);
		pictures.get(0).draw(g, 0, 0);

		for (Route route : graph.edges()){ // Draws all routes

			g.setColor(Color.blue);

			g.fillPolygon(route.getPolygon());
			g.fillPolygon(route.getPolygon());
		}

		for (City city : graph.vertices()){ // Draws all cities
			int[] pos = city.getCoordinates();
			if (selected_city != null && city.equals(selected_city) && one_city_selected)
				pictures.get(2).draw(g, pos[0]-8, pos[1]-8); // Draws the selected city differently
			else
				pictures.get(1).draw(g, pos[0]-8, pos[1]-8); // Draws all other cities
		}
		
		if (end != null)
			pictures.get(ImageLoader.RARE_CANDY).draw(g, end.getCoordinates()[0]-37, end.getCoordinates()[1]-35);

		for (Pokemon pkmn : pokemon) // Draws all pokemon
			pkmn.draw(g,pkmn.getCoordinates()[0],pkmn.getCoordinates()[1]);

		if (selected_pokemon!= null)
			selected_pokemon.draw(g,mouseX,mouseY);

		if (info != null){ // Displays the info of the object being hovered over
			info.draw(g, mouseX, mouseY, edit[1]? 1 : edit[2]? 2 : edit[3]? 3 : -1);

		}else if (edit[ADD_CITY]){	// If adding a city
			info = new Info(); //TODO pallet town example
			info.setInfoable(new City(0,0, KnownCities.pallet_town.contains(mouseX, mouseY)? "Pallet Town" : "City " + numCities));
			info.draw(g, mouseX, mouseY, ADD_CITY);
		}

	}
	private Polygon drawThickLine(int x1, int y1, int x2, int y2, int thickness) {

		int dX = x2 - x1;
		int dY = y2 - y1;
		// Distance formula
		double lineLength = Math.sqrt(dX * dX + dY * dY);

		double scale = (double)(thickness) / (1 * (double)lineLength);

		// The x,y increments from an endpoint needed to create a rectangle...
		double ddx = -scale * (double)dY;
		double ddy = scale * (double)dX;
		ddx += (ddx > 0) ? 0.5 : -0.5;
		ddy += (ddy > 0) ? 0.5 : -0.5;
		int dx = (int)ddx;
		int dy = (int)ddy;

		// Now we can compute the corner points...
		int xPoints[] = new int[4];
		int yPoints[] = new int[4];

		xPoints[0] = x1 + dx; yPoints[0] = y1 + dy;
		xPoints[1] = x1 - dx; yPoints[1] = y1 - dy;
		xPoints[2] = x2 - dx; yPoints[2] = y2 - dy;
		xPoints[3] = x2 + dx; yPoints[3] = y2 + dy;

		Polygon path = new Polygon();
		path.xpoints = xPoints;
		path.ypoints = yPoints;
		path.npoints = 4;
		return path;
	}
	//--------------------------------------------
	// End of Paint Component
	//--------------------------------------------

	//--------------------------------------------
	// Edit the Playing Field
	//--------------------------------------------
	/**
	 * Returns the city found at the given coordinates, or null if no city is found.
	 */
	public City findCity(int x, int y){

		for (City city : graph.vertices())
			if (Math.abs(city.getCoordinates()[0] - x) <= 8 && Math.abs(city.getCoordinates()[1] - y) <= 8)
				return city;

		return null;
	}
	private Route findRoute(int x, int y){

		for (Route route : graph.edges()){
			if (route.getPolygon().contains(x,y)){
				return route;
			}
		}

		return null;
	}
	public Pokemon findPokemon(int x, int y){

		for (Pokemon poke : pokemon){
			if (Math.abs(poke.getCoordinates()[0]-x) <= 40 && poke.getCoordinates()[1]-y <= 60 && poke.getCoordinates()[1]-y >=0){
				return poke;
			}
		}

		return null;
	}
	/**
	 * Allows the display to start or stop editing the playing field.
	 * All valid values for edit_ID are defined as private static final integers
	 */
	public void editField(int edit_ID, boolean b){

		if (edit_ID<0)
			return;

		selected_pokemon = null;

		for (int i = 0; i < edit.length; i++)
			edit[i] = false;

		if (edit_ID == ESCAPE)
			return;

		edit[edit_ID] = b;
	}

	public void addCity(MouseEvent e){	
		int[] pos = {e.getX(),e.getY()};
		City found = findCity(pos[0],pos[1]);
		if (found == null){
			graph.insertVertex(new City(e.getX(),e.getY(), "City " + numCities));
			numCities++;
		}
	}
	public void removeCity(MouseEvent e){
		int[] pos = {e.getX(),e.getY()};
		City found = findCity(pos[0],pos[1]);
		if (found != null)
			graph.removeVertex(found);
	}
	public void addRoute(MouseEvent e){

		City temp = findCity(e.getX(),e.getY());
		if (temp==null)
			return;

		Route route = graph.insertEdge(selected_city, temp);
		route.setPolygon(drawThickLine(selected_city.getCoordinates()[0],selected_city.getCoordinates()[1],temp.getCoordinates()[0],
				temp.getCoordinates()[1],4));
		route.setName("Route " + numRoutes);
		numRoutes++;

		repaint();
	}
	public void removeRoute(MouseEvent e){
		Route found = findRoute(e.getX(),e.getY());
		if (found != null)
			graph.removeEdge(found);
		repaint();
	}
	public void selectPokemon(Pokemon p){
		editField(ADD_POKEMON,true);
		selected_pokemon = p;
	}

	public void reset(){
		for (int i = 0; i < 4; i++){
			for (City city : graph.vertices()){
				city.setVisited(false, i);
				city.setPrev(null, i);
			}
		}
		for (Pokemon poke : pokemon)
			poke.stop();
		pokemon = new LinkedSequence<Pokemon>();
		selected_pokemon = null;
		one_city_selected = false;
		selected_city = null;
		info = null;
		editField(0,false);
		repaint();
	}
	//-------------------------------------------
	// End of Edit the Playing Field
	//-------------------------------------------


	//-----------------------
	// Listener Methods
	//-----------------------

	public void mouseClicked(MouseEvent e) {

		City city = findCity(e.getX(),e.getY());

		if (edit[ADD_CITY])
			addCity(e);

		if (edit[REMOVE_CITY])
			removeCity(e);

		if (edit[ADD_ROUTE]){		
			if (one_city_selected){ //if a city is already selected
				addRoute(e);
				selected_city = null; //deselect city
				one_city_selected = false; //deselect city
			}
			else{
				selected_city = findCity(e.getX(),e.getY()); //select city
				if (selected_city != null) //if first click is not on a city
					one_city_selected = true; //select city
			}
		}

		if (edit[REMOVE_ROUTE])
			removeRoute(e);

		if (edit[ADD_POKEMON] && city != null){
			selected_pokemon.setCity(city);
			selected_pokemon.setPosition(city.getCoordinates()[0], city.getCoordinates()[1]);
			pokemon.addLast(selected_pokemon);
			selected_pokemon.setGraph(graph);
			selected_pokemon = null;
			edit[ADD_POKEMON] = false;
		}

		if (edit[SELECT_END]){
			end = findCity(e.getX(),e.getY());
		}

		repaint();
	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent arg0) {

	}

	public void mouseReleased(MouseEvent arg0) {

	}

	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		mouseX = e.getX();
		mouseY = e.getY();

		//		System.out.println(mouseX + ", " + mouseY);

		City city = findCity(mouseX,mouseY);
		Route route = findRoute(mouseX,mouseY);
		Pokemon poke = findPokemon(mouseX,mouseY);
		info = new Info();

		if (poke != null)
			info.setInfoable(poke);
		else if (city != null)
			info.setInfoable(city);
		else if (route != null)
			info.setInfoable(route);
		else
			info = null;


		repaint();

	}
	//------------------------------
	// End of Listener Methods
	//------------------------------

}
