package cs2321;

import java.awt.Polygon;

public class KnownCities {
	
	public static Polygon pallet_town = new Polygon();
	
	public KnownCities(){
		int[] x = new int[]{227,202,233,274};
		int[] y = new int[]{420,456,501,460};
		pallet_town.xpoints = x;
		pallet_town.ypoints = y;
		pallet_town.npoints = 4;
	}

}
