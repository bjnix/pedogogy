package cs2321;

import java.awt.Graphics;

import net.datastructures.Sequence;

public class Pokemon implements Runnable, Infoable {

	private long speed;
	private int search;
	private int power;
	private double[] coordinates = new double[2];
	private double mx;
	private double my;
	private boolean stopped;
	private Sequence<Route> path;
	private PokemonGraph graph;
	private AI ai;
	private Picture picture;
	private String ID;
	private City current_city;
	private City next_city;
	private City end;
	private Route current_route;
	private Schedule schedule;
	private int player;
	private int PIN;
	private double random = Math.random();

	public Pokemon(int pin, PokemonGraph g, long speed, int searchNum, Picture p, String id, int x, int y, int power, int player){

		search = searchNum;
		this.speed = speed;
		graph = g;
		picture = p;
		ID = id;
		coordinates[0] = x;
		coordinates[1] = y;
		this.power = power;
		this.player = player;
		this.PIN = pin;
	}

	public void setPosition(int x, int y){
		coordinates[0] = x;
		coordinates[1] = y;
	}
	
	public double getPower(){
		return power*(1+random);
	}
	
	public String getID(){
		return ID;
	}

	public void setCity(City c){
		current_city = c;
	}
	
	public int getPlayer(){
		return player;
	}

	public City getCity(){
		return current_city;
	}
	
	public Route getCurrentRoute(){
		return current_route;
	}

	public void setSchedule(Schedule s){
		stopped = false;
		schedule = s;
	}

	public void setGraph(PokemonGraph g){
		graph = g;
	}
	
	public void stop(){
		stopped = true;
	}

	public void draw(Graphics g, int x, int y){
		picture.draw(g, x-picture.getCenterX(), y-picture.getHeight());
	}
	
	public void drawStats(Graphics g, int x, int y){
		String searchStat = (search==1)?"Best pathfinding" :
							(search==2)?"Good Pathfinding" :
							(search==3)?"Okay Pathfinding" :
							(search==4)?"Worst Pathfinding" :
								"No Pathfinding";
		g.drawString(searchStat, x, y);
		g.drawString("Power : " + power, x+10, y+15);
	}

	public String showInfo(){
		return ID;
	}
	
	public String toString(){
		return ID;
	}

	public int[] getCoordinates(){
		int[] temp = new int[2];
		temp[0] = (int)coordinates[0];
		temp[1] = (int)coordinates[1];
		return temp;
	}

	public void generatePath(City end){
		
		this.end = end;	
		ai = new AI(PIN);
		
		path = (search==1)? ai.shortestPath(graph, current_city, end) :
				(search==2)? ai.BFS(current_city, end) :
				(search==3)? ai.DFS(current_city, end) : 
				(search==4)? ai.random(current_city, end) : null;
				
		next_city = null;
		current_route = null;
	}

	public void run() {

		try {
			schedule.getReady();
		} catch (InterruptedException e){
			e.printStackTrace();
		}
		
		if (path == null || path.size() == 0){
			return;
		}

		while (current_city == null || !current_city.equals(end)){
			
			if (stopped){
				return;
			}
			
			if (current_route == null){
				if (current_city != null){
					current_route = path.removeFirst();
					next_city = graph.opposite(current_city, current_route);
					current_city = null;
				}
			}
			else{
				double dx = next_city.getCoordinates()[0]-coordinates[0];
				double dy = next_city.getCoordinates()[1]-coordinates[1];
				double d = Math.sqrt(dx*dx+dy*dy);
				mx = dx/d * speed;
				my = dy/d * speed;
				
				coordinates[0]+=mx;coordinates[1]+=my;
			}
			
			if (Math.abs(next_city.getCoordinates()[0] - coordinates[0]) <= 8 &&
					Math.abs(next_city.getCoordinates()[1] - coordinates[1]) <= 8){
				current_city = next_city;
				next_city = null;
				current_route = null;
				if (current_city.equals(end))
					break;
			}

			try {
				schedule.checkForBattles(this);
				Thread.sleep(speed*10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		try {
			schedule.end(this);
			System.out.println(ID + " Wins!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void die(Pokemon winner) throws InterruptedException{
		
		System.out.println(ID + " was killed by " +  winner.getID());
		schedule.kill(winner,this);
	}

}
