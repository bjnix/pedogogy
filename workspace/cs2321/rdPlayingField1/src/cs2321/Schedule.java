package cs2321;

public class Schedule {
	
	private PlayingFieldConstructionPanel display;
	private LinkedSequence<Pokemon> pokemon;
	
	public Schedule(PlayingFieldConstructionPanel p, LinkedSequence<Pokemon> po){
		display = p;
		pokemon = po;
	}
	
	public synchronized void getReady() throws InterruptedException{
		
		this.wait();
		
	}
	
	public synchronized void start(){
		
		notifyAll();
		
	}
	
	/**
	 * Sometimes throws an error during battles because a pokemon is destroyed while he is calculating his enemy
	 * 
	 */
	public synchronized void checkForBattles(Pokemon p) throws InterruptedException{
		
		for (Pokemon poke : pokemon){
			if (!poke.equals(p)){
				if ((poke.getCurrentRoute()!=null && p.getCurrentRoute()!=null && 
						poke.getCurrentRoute().equals(p.getCurrentRoute()) &&
						poke.equals(display.findPokemon(p.getCoordinates()[0], p.getCoordinates()[1])))
						||
						(poke.getCity()!=null && p.getCity()!=null && poke.getCity().equals(p.getCity()))){
					p.battle(poke);
				}
			}
		}
		
		display.repaint();
		
	}
	
	public synchronized void kill(Pokemon winner, Pokemon loser){
		
		int i = 0;
		for (Pokemon pkmn : pokemon){
			if (loser.equals(pkmn)){
				pkmn.stop();
				pokemon.remove(i);
			}
			i++;
		}
		display.repaint();

	}
	
	public synchronized void end() throws InterruptedException{
		
		for(Pokemon pkmn : pokemon)
			pkmn.stop();
		
		
	}

}
