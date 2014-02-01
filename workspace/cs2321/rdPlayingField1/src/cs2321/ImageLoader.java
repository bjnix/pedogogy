package cs2321;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.io.FilePermission;
import java.net.URL;

public class ImageLoader {

	private ArraySequence<Picture> pictures = new ArraySequence<Picture>();
	
	public static final int SEL_X = 125;
	public static final int SEL_Y = 125;
	
	public static int MAP = 0;
	public static int CITY = 1;
	public static int SELECTED_CITY = 2;
	public static int PIKACHU_S = 3;
	public static int NINETAILS_S = 4;
	public static int BLASTOISE_S = 5;
	public static int ABRA_S = 6;
	public static int PARASECT_S = 7;
	public static int PIDGEOT_S = 8;
	public static int SANDSLASH_S = 9;
	public static int MEWTWO_S = 10;
	public static int GASTLY_S = 11;
	public static int SCYTHER_S = 12;
	public static int ONYX_S = 13;
	public static int GYARADOS_S = 14;
	public static int ARTICUNO_S = 15;
	
	public static int PIKACHU = 16;
	public static int NINETAILS = 17;
	public static int BLASTOISE = 18;
	public static int PARASECT = 19;
	public static int ABRA = 20;
	public static int PIDGEOT = 21;
	public static int SANDSLASH = 22;
	public static int MEWTWO = 23;
	public static int GASTLY = 24;
	public static int SCYTHER = 25;
	public static int ONIX = 26;
	public static int GYARADOS = 27;
	public static int ARTICUNO = 28;
	public static int POKEBANNER = 29;
	public static int P_1 = 30;
	public static int P_2 = 31;
	public static int RARE_CANDY = 32;
	
	/**
	 * 0 Map
	 * 1 City
	 * 2 Selected City
	 *****Selection screen images
	 * 3 Pikachu
	 * 4 Ninetails
	 * 5 Blastoise
	 * 6 Abra
	 * 7 Parasect
	 * 8 Pidgeot
	 * 9 Sandslash
	 * 10 Mewtwo
	 * 11 Gastly
	 * 12 Scyther
	 * 13 Onyx
	 * 14 Gyarados
	 * 15 Articuno
	 ******Game images
	 * 16 Pikachu
	 * 17 NineTails
	 * 18 Blastoise
	 * 19 Parasect
	 * 20 Abra
	 * 21 Pidgeot
	 * 22 Sandslash
	 * 23 Mewtwo
	 * 24 Gastly
	 * 25 Scyther
	 * 26 Onyx
	 * 27 Gyarados
	 * 28 Articuno
	 * 29 pokeBanner
	 * 30 player 1 selection token
	 * 31 player 2 selection token
	 *  
	 */
	public ImageLoader(){
		
		try{
			pictures.addLast(new Picture(ImageIO.read(new File("../images/Kanto.jpg")),0,0)); // Map
			pictures.getLast().setAbsoluteSize(1000, 668);
			pictures.addLast(new Picture(ImageIO.read(new File("../images/City.png")),0,0)); // City
			pictures.getLast().setAbsoluteSize(16, 16);
			pictures.addLast(new Picture(ImageIO.read(new File("../images/CitySelected.png")),0,0)); // Selected City
			pictures.getLast().setAbsoluteSize(16, 16);
			
			//first selection screen read-in
			Image pokeSel1 = ImageIO.read(new File("../images/PokeSelect1.jpg"));
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
			Image pokeSel2 = ImageIO.read(new File("../images/PokeSelect2.jpg"));
			
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
			Image allPokemon = ImageIO.read(new File("../images/AllPokemon.png"));
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
			pictures.addLast(new Picture(ImageIO.read(new File("../images/pokeBanner.jpg")),0,0)); // pokeBanner 29
			pictures.getLast().setAbsoluteSize(800, 209);
			
			pictures.addLast(new Picture(ImageIO.read(new File("../images/p1Select.png")),0,0)); // p1 selection token 30
			pictures.getLast().setAbsoluteSize(30, 30);
			
			pictures.addLast(new Picture(ImageIO.read(new File("../images/p2Select.png")),0,0)); // p2 selection token 31
			pictures.getLast().setAbsoluteSize(30, 30);
			
			pictures.addLast(new Picture(ImageIO.read(new File("../images/RareCandy.png")),0,0)); // rare candy 32
			pictures.getLast().setDim(75,70); pictures.getLast().setSourceDim(271, 229);
			
				
			
			
			
			
		} catch (IOException e3) {			
			System.out.println("error IOException, Images not found");
		} catch (IllegalArgumentException e4){
			System.out.println("error IllegalArgumentException, something really weird happened");
		}

	}
	
	public ArraySequence<Picture> getPictures(){
		return pictures;
	}

}
