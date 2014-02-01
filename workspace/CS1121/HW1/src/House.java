import animator.*;
import java.awt.*;

/**
 * @author Brent Nix
 */
public class House extends Animated {
	//properties
	private int radius = 20;
	private int diameter = 2*radius;
	private Rite rite;
	private Jive jive;
	private Hum hum;
	private Court court;
	private Move move;
	private Tall tall;
	private Triad triad;
	private Jimmy jimmy;
	private Ha ha;
	private Dub dub;
	
public void startup(){
	
		jimmy = new Jimmy();
		animator.include(jimmy);
		
		triad = new Triad();
		animator.include(triad);
		
		rite = new Rite();
		animator.include(rite);
		
		jive = new Jive();
		animator.include(jive);
		
		hum = new Hum();
		animator.include(hum);
		
		court = new Court();
		animator.include(court);
		
		move = new Move();
		animator.include(move);
		
		tall = new Tall();
		animator.include(tall);
		
		ha = new Ha();
		animator.include(ha);
		
		dub = new Dub();
		animator.include(dub);
		
} // end of startup method
	
	/**
	 * Draw the circle, centered at x,y.
	 * 
	 * @param x the x-coordinate from the Animator.
	 * @param y the y-coordinate from the Animator.
	 * 
	 */
	public void draw(int x, int y){
		
	/**
	 * Create an Other object and include it in the animation.
	 */
		
		
		
		// draw a black circle
		screen.setColor(Color.black);
		screen.fillOval(x-radius, y-radius, diameter, diameter);
			
		
		int cx = rite.getX();
		int cy = rite.getY();
		
		Color color1 = rite.getColor();
		
		//when ball touches Rite, go crazy
		
		double dist = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		int riteRadius = rite.getRadius(); 
		if (dist < (radius + riteRadius)) {
			if (color1 == Color.red){ 
				{ rite.goBlack(); }
		}}
		
		if (dist < (radius + riteRadius)) {
			if (color1 == Color.black){
				{rite.goGreen();}
		}}

		if (dist < (radius + riteRadius)) {
			if (color1 == Color.green){
				{rite.goYellow();}
		}}
		if (dist < (radius + riteRadius)) {
			if (color1 == Color.yellow){
				{rite.goCyan();}
		}}
		if (dist < (radius + riteRadius)) {
			if (color1 == Color.cyan){
				{rite.goRed();}
		}}
		
		cx = jive.getX();
		cy = jive.getY();
		color1=jive.getColor();
		double dist1 = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		
		//when ball touches Jive, go crazy
		
		int jiveRadius = jive.getRadius(); 
		if (dist1 < (radius + jiveRadius)) {
			if (color1 == Color.red){
				{jive.goBlack();}
		}}
		if (dist1 < (radius + jiveRadius)) {
			if (color1 == Color.black){
				{jive.goGreen();}
		}}
		if (dist1 < (radius + jiveRadius)) {
			if (color1 == Color.green){
				{jive.goYellow();}
		}}
		if (dist1 < (radius + jiveRadius)) {
			if (color1 == Color.yellow){
				{jive.goCyan();}
		}}
		if (dist1 < (radius + jiveRadius)) {
			if (color1 == Color.cyan){
				{jive.goRed();}
		}}
		
		cx = hum.getX();
		cy = hum.getY();
		color1=hum.getColor();
		double dist2 = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		
		//when ball touches Hum, go crazy
		
		int humRadius = hum.getRadius(); 
		if (dist2 < (radius + humRadius)) {
			if (color1 == Color.red){
				{hum.goBlack();}
		}}
		if (dist2 < (radius + humRadius)) {
			if (color1 == Color.black){
				{hum.goGreen();}
		}}
		if (dist2 < (radius + humRadius)) {
			if (color1 == Color.green){
				{hum.goYellow();}
		}}
		if (dist2 < (radius + humRadius)) {
			if (color1 == Color.yellow){
				{hum.goCyan();}
		}}
		if (dist2 < (radius + humRadius)) {
			if (color1 == Color.cyan){
				{hum.goRed();}
		}}

		cx = court.getX();
		cy = court.getY();
		color1=court.getColor();
		double dist3 = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		
		//when ball touches Court, go crazy
		
		int courtRadius = court.getRadius(); 
		if (dist3 < (radius + courtRadius)) {
			if (color1 == Color.red){
				{court.goBlack();}
		}}
		if (dist3 < (radius + courtRadius)) {
			if (color1 == Color.black){
				{court.goGreen();}
		}}
		if (dist3 < (radius + courtRadius)) {
			if (color1 == Color.green){
				{court.goYellow();}
		}}
		if (dist3 < (radius + courtRadius)) {
			if (color1 == Color.yellow){
				{court.goCyan();}
		}}
		if (dist3 < (radius + courtRadius)) {
			if (color1 == Color.cyan){
				{court.goRed();}
		}}
		cx = move.getX();
		cy = move.getY();
		color1=move.getColor();
		double dist4 = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		
		//when ball touches move, go crazy
		
		int moveRadius = move.getRadius(); 
		if (dist4 < (radius + moveRadius)) {
			if (color1 == Color.red){
				{move.goBlack();}
		}}
		if (dist4 < (radius + moveRadius)) {
			if (color1 == Color.black){
				{move.goGreen();}
		}}
		if (dist4 < (radius + moveRadius)) {
			if (color1 == Color.green){
				{move.goYellow();}
		}}
		if (dist4 < (radius + moveRadius)) {
			if (color1 == Color.yellow){
				{move.goCyan();}
		}}
		if (dist4 < (radius + moveRadius)) {
			if (color1 == Color.cyan){
				{move.goRed();}
		}}
		
		cx = tall.getX();
		cy = tall.getY();
		color1=tall.getColor();
		double dist5 = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		
		//when ball touches Tall, go crazy
		
		int tallRadius = tall.getRadius(); 
		if (dist5 < (radius + tallRadius)) {
			if (color1 == Color.red){
				{tall.goBlack();}
		}}
		if (dist5 < (radius + tallRadius)) {
			if (color1 == Color.black){
				{tall.goGreen();}
		}}
		if (dist5 < (radius + tallRadius)) {
			if (color1 == Color.green){
				{tall.goYellow();}
		}}
		if (dist5 < (radius + tallRadius)) {
			if (color1 == Color.yellow){
				{tall.goCyan();}
		}}
		if (dist5 < (radius + tallRadius)) {
			if (color1 == Color.cyan){
				{tall.goRed();}
		}}
		cx = ha.getX();
		cy = ha.getY();
		color1=ha.getColor();
		double dist6 = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		
		//when ball touches Ha, go crazy
		
		int haRadius1 = ha.getRadius(); 
		if (dist6 < (radius + haRadius1)) {
			if (color1 == Color.red){
				{ha.goBlack();}
		}}
		if (dist6 < (radius + haRadius1)) {
			if (color1 == Color.black){
				{ha.goGreen();}
		}}
		if (dist6 < (radius + haRadius1)) {
			if (color1 == Color.green){
				{ha.goYellow();}
		}}
		if (dist6 < (radius + haRadius1)) {
			if (color1 == Color.yellow){
				{ha.goCyan();}
		}}
		if (dist6 < (radius + haRadius1)) {
			if (color1 == Color.cyan){
				{ha.goRed();}
		}}
		cx = dub.getX();
		cy = dub.getY();
		color1=dub.getColor();
		double dist7 = Math.sqrt((x-cx)*(x-cx)+(y-cy)*(y-cy));
		
		//when ball touches Dub, go crazy
		
		int dubRadius11 = dub.getRadius(); 
		if (dist7 < (radius + dubRadius11)) {
			if (color1 == Color.red){
				{dub.goBlack();}
		}}
		if (dist7 < (radius + dubRadius11)) {
			if (color1 == Color.black){
				{dub.goGreen();}
		}}
		if (dist7 < (radius + dubRadius11)) {
			if (color1 == Color.green){
				{dub.goYellow();}
		}}
		if (dist7 < (radius + dubRadius11)) {
			if (color1 == Color.yellow){
				{dub.goCyan();}
		}}
		if (dist7 < (radius + dubRadius11)) {
			if (color1 == Color.cyan){
				{dub.goRed();}
		}}
	}//end draw method
}//end House class