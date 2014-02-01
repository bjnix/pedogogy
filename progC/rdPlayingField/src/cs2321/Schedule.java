package cs2321;

import net.datastructures.EmptyDequeException;

public class Schedule {

	private PlayingFieldConstructionPanel display;
	private LinkedSequence<Pokemon> pokemon;

	public Schedule(PlayingFieldConstructionPanel p, LinkedSequence<Pokemon> po){
		display = p;
		pokemon = po;
	}

	public synchronized void getReady() throws InterruptedException{

		display.setString("Calculating paths.....");
		wait();

	}

	public synchronized void start(){

		notifyAll();

	}

	/**
	 * Sometimes throws an error during battles because a pokemon is destroyed while he is calculating his enemy
	 * 
	 */
	public synchronized void checkForBattles(Pokemon p) throws InterruptedException{

		try{
			for (Pokemon poke : pokemon){
				if (!poke.equals(p)){
					if (poke.getPlayer() == p.getPlayer())
						break;
					if ((poke.getCurrentRoute()!=null && p.getCurrentRoute()!=null && 
							poke.getCurrentRoute().equals(p.getCurrentRoute()) &&
							poke.equals(display.findPokemon(p.getCoordinates()[0], p.getCoordinates()[1])))
							||
							(poke.getCity()!=null && p.getCity()!=null && poke.getCity().equals(p.getCity()))){
						Pokemon loser = (poke.getPower()>p.getPower())? p : poke;
						Pokemon winner = (poke.getPower()<p.getPower())? p : poke;
						loser.die(winner);
					}
				}
			}
		} catch (net.datastructures.InvalidPositionException e){
			// A pokemon was destroyed while calculating his enemy
		}

		display.repaint();

	}

	public synchronized void kill(Pokemon winner, Pokemon loser){
		
		display.setString(winner.getID() + " has destroyed " + loser.getID() + "!");

		int i = 0;
		for (Pokemon pkmn : pokemon){
			if (loser.equals(pkmn)){
				pkmn.stop();
				pokemon.remove(i);
			}
			i++;
		}
		
		display.repaint();
		
		if (pokemon.size() == 1)
			try {
				this.end(pokemon.getFirst());
			} catch (EmptyDequeException e) {
			} catch (InterruptedException e) {
			}

	}

	public synchronized void end(Pokemon p) throws InterruptedException{

		display.setString(p.getID() + " Wins!!");
		
		for(Pokemon pkmn : pokemon)
			pkmn.stop();

	}

}
