package cs2321;

import net.datastructures.*;
import java.util.Comparator;

public class LookupTable<K, V> implements Map<K, V> {
	
	private Entry<K,V>[] data;
	private Comparator<K> comparator;
	
	public LookupTable(){
		comparator = new DefaultKeyComparator<K>();
	}
	
	/**
	 * 
	 * @param comparator - on construction, set comparator
	 */
	public LookupTable(Comparator<K> comparator) {
		this.comparator = comparator;
	}
	
	/**
	 * @param comparator the comparator to set
	 */
	public void setComparator(Comparator<K> comparator) {
		this.comparator = comparator;
	}

	public Iterable<Entry<K, V>> entries() {
		// TODO Auto-generated method stub
		return null;
	}

	public V get(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Iterable<K> keys() {
		// TODO Auto-generated method stub
		return null;
	}

	public V put(K key, V value) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	public V remove(K key) throws InvalidKeyException {
		// TODO Auto-generated method stub
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Iterable<V> values() {
		// TODO Auto-generated method stub
		return null;
	}

}
