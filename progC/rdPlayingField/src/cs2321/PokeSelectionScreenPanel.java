package cs2321;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;



public class PokeSelectionScreenPanel extends JPanel implements MouseListener, MouseMotionListener {

	public static int P1 = 1;
	public static int P2 = 2;
	public static int DONE = 3;
	public static int SEL_STATE = P1;

	private Pokemon info;

	protected ArraySequence<Pokemon> p1Pkmn = new ArraySequence<Pokemon>();
	protected ArraySequence<Pokemon> p2Pkmn = new ArraySequence<Pokemon>();

	protected ArraySequence<Integer[]> p1PkmnTags = new ArraySequence<Integer[]>();
	protected ArraySequence<Integer[]> p2PkmnTags = new ArraySequence<Integer[]>();

	ArraySequence<Picture> pictures;


	public PokeSelectionScreenPanel(ArraySequence<Picture> p){
		pictures = p;
		addMouseListener(this);
		addMouseMotionListener(this);
		repaint();
	}
	
	public ArraySequence<Pokemon>[] getPokemon(){
		ArraySequence<Pokemon>[] temp = (ArraySequence<Pokemon>[])new ArraySequence[2];
		temp[0] = p1Pkmn; temp[1] = p2Pkmn;
		return temp;
	}

	public void paintComponent(Graphics g){
		super.paintComponent(g);


		pictures.get(ImageLoader.POKEBANNER).draw(g, 0, 0);
		pictures.get(ImageLoader.PIKACHU_S).draw(g, 125+15, 225);
		pictures.get(ImageLoader.NINETAILS_S).draw(g, 250+15, 225);
		pictures.get(ImageLoader.BLASTOISE_S).draw(g, 375+15, 225);
		pictures.get(ImageLoader.ABRA_S).draw(g, 500+15, 225);
		pictures.get(ImageLoader.PARASECT_S).draw(g, 125+15, 350);
		pictures.get(ImageLoader.PIDGEOT_S).draw(g, 250+15, 350);
		pictures.get(ImageLoader.SANDSLASH_S).draw(g, 375+15, 350);
		pictures.get(ImageLoader.ARTICUNO_S).draw(g, 500+15, 350);
		pictures.get(ImageLoader.GASTLY_S).draw(g, 125+15, 475);
		pictures.get(ImageLoader.SCYTHER_S).draw(g, 250+15, 475);
		pictures.get(ImageLoader.ONYX_S).draw(g, 375+15, 475);
		pictures.get(ImageLoader.GYARADOS_S).draw(g, 500+15, 475);
		pictures.get(ImageLoader.MEWTWO_S).draw(g, 125+15, 600);


		for(int i = 0; i < p1PkmnTags.size() ; i++)
			pictures.get(ImageLoader.P_1).draw(g, p1PkmnTags.get(i)[0], p1PkmnTags.get(i)[1]);

		for(int i = 0; i < p2PkmnTags.size() ; i++)
			pictures.get(ImageLoader.P_2).draw(g, p2PkmnTags.get(i)[0], p2PkmnTags.get(i)[1]);
		
		int y = 300;
		for(int i = 0; i < p1Pkmn.size(); i++){
			p1Pkmn.get(i).draw(g, 50, y);
			y += 80;
		}
		
		y = 300;
		for (int i = 0; i < p2Pkmn.size(); i++){
			p2Pkmn.get(i).draw(g, 750, y);
			y+= 80;
		}

		if (info != null){
			info.draw(g, 350, 690);
			g.drawString(info.showInfo(), 390, 700);
			info.drawStats(g, 500, 655);
		}


	}

	public void mouseDragged(MouseEvent arg0) {

	}


	public void mouseMoved(MouseEvent arg0) {
		
		if (SEL_STATE == DONE)
			return;
		
		info = getPKMN(arg0.getX(),arg0.getY());
		repaint();
		
	}


	public void mouseClicked(MouseEvent arg0) {
		if (SEL_STATE == DONE)
			return;

		Pokemon poke = getPKMN(arg0.getX(),arg0.getY());
		
		if (poke!= null)
			setPKMN(poke,arg0.getX(),arg0.getY());

		repaint();
	}
	
	public void setPKMN(Pokemon poke, int x, int y){

		Integer[] coord = {x-15,y-15};
		
		if(SEL_STATE == P1)
		{
			for (int i = 0; i < p1Pkmn.size(); i ++)
				if(p1Pkmn.get(i).showInfo().equals(poke.showInfo()))
					return;
			
			p1Pkmn.addLast(poke);
			p1PkmnTags.addLast(coord);
		}
		
		if(SEL_STATE == P2)
		{
			for (int i = 0; i < p2Pkmn.size(); i ++)
				if(p2Pkmn.get(i).showInfo().equals(poke.showInfo()))
					return;
			
			p2Pkmn.addLast(poke);
			p2PkmnTags.addLast(coord);
		}
		
		//TODO
		SEL_STATE = (p1Pkmn.size()<AgentDemoApplet.NUMBER_OF_POKEMON)? P1 : (p2Pkmn.size()<AgentDemoApplet.NUMBER_OF_POKEMON)? P2 : DONE;
	}
	
	private Pokemon getPKMN(int xx, int yy){
		
		Pokemon poke = null;

		if ((140 < xx && xx < 265) && (225 < yy && yy < 350 ))//Pikachu
			poke = new Pokemon(1, null, 10, 2, pictures.get(ImageLoader.PIKACHU),"Pikachu", -10, -10, 20, SEL_STATE);

		if ((265 < xx && xx < 390) && (225 < yy && yy < 350 ))//Ninetails
			poke = new Pokemon(2, null, 10, 2, pictures.get(ImageLoader.NINETAILS),"Ninetails", -10, -10, 30, SEL_STATE);

		if ((390 < xx && xx < 515) && (225 < yy && yy < 350 ))//Blastoise
			poke = new Pokemon(3, null, 10, 4, pictures.get(ImageLoader.BLASTOISE),"Blastoise", -10, -10, 40, SEL_STATE);

		if ((515 < xx && xx < 640) && (225 < yy && yy < 350 ))//Abra
			poke = new Pokemon(4, null, 10, 1, pictures.get(ImageLoader.ABRA),"Abra", -10, -10, 10, SEL_STATE);

		if ((140 < xx && xx < 265) && (350 < yy && yy < 475 ))//Parasect
			poke = new Pokemon(5, null, 10, 2, pictures.get(ImageLoader.PARASECT),"Parasect", -10, -10, 10, SEL_STATE);

		if ((265 < xx && xx < 390) && (350 < yy && yy < 475 ))//Pidgeot
			poke = new Pokemon(6, null, 10, 2, pictures.get(ImageLoader.PIDGEOT),"Pidgeot", -10, -10, 40, SEL_STATE);

		if ((390 < xx && xx < 515) && (350 < yy && yy < 475 ))//Sandslash
			poke = new Pokemon(7, null, 10, 3, pictures.get(ImageLoader.SANDSLASH),"Sandslash", -10, -10, 40, SEL_STATE);

		if ((515 < xx && xx < 640) && (350 < yy && yy < 475 ))//Articuno
			poke = new Pokemon(8, null, 10, 4, pictures.get(ImageLoader.ARTICUNO),"Articuno", -10, -10, 50, SEL_STATE);

		if ((140 < xx && xx < 265) && (475 < yy && yy < 600 ))//Gastly
			poke = new Pokemon(9, null, 10, 1, pictures.get(ImageLoader.GASTLY),"Gastly", -10, -10, 20, SEL_STATE);

		if ((265 < xx && xx < 390) && (475 < yy && yy < 600 ))//Scyther
			poke = new Pokemon(10, null, 10, 2, pictures.get(ImageLoader.SCYTHER),"Scyther", -10, -10, 30, SEL_STATE);

		if ((390 < xx && xx < 515) && (475 < yy && yy < 600 ))//Onix
			poke = new Pokemon(11, null, 10, 3, pictures.get(ImageLoader.ONIX),"Onix", -10, -10, 40, SEL_STATE);

		if ((515 < xx && xx < 640) && (475 < yy && yy < 600 ))//Gyarados
			poke = new Pokemon(12, null, 10, 4, pictures.get(ImageLoader.GYARADOS),"Gyarados", -10, -10, 40, SEL_STATE);

		if ((140 < xx && xx < 265) && (600 < yy && yy < 725 ))//Mewtwo
			poke = new Pokemon(13, null, 10, 1, pictures.get(ImageLoader.MEWTWO),"Mewtwo", -10, -10, 60, SEL_STATE);
		
		return poke;
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

}
