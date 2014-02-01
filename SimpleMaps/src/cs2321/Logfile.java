package cs2321;

import net.datastructures.*;

public class Logfile<K, V> implements Dictionary<K, V> {

	private LinkedSequence<Entry<K,V>> data;
	
	public Logfile(){
	}

	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	public Entry<K, V> find(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterable<Entry<K, V>> findAll(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	public Entry<K, V> insert(K key, V value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Entry<K, V> remove(Entry<K, V> e) throws InvalidEntryException {
		// TODO Auto-generated method stub
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}
		
}
