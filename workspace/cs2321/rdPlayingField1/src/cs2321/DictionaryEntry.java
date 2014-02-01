package cs2321;

import net.datastructures.Entry;

public class DictionaryEntry<K,V> implements Entry<K, V> {
	
	private K key;
	private V value;
	
	public DictionaryEntry(K k, V v){
		
		key = k;
		value = v;
	}

	@Override
	public K getKey() {
		
		return key;
	}

	@Override
	public V getValue() {
		
		return value;
	}

}
