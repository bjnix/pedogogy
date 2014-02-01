package cs2321;

import net.datastructures.Entry;

public class PQEntry<K, V> implements Entry<K,V> {
	
	private K key;
	private V value;
	
	public PQEntry(K k, V v){
		
		key = k;
		value = v;
	}
	
	public PQEntry(){
		
		key = null;
		value = null;
	}

	public K getKey() {
		return key;
	}
//	Graph <String, PathInfo> playingField = setup( "initialPlayingField.csv" );
	public V getValue() {
		return value;
	}
	
	public String toString(){
		return key + ", " + value;
	}

}
