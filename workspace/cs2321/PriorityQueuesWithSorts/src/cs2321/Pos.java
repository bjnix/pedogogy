package cs2321;
import net.datastructures.*;
/**
 * An single position class for a Priority Queue that contains a Key and a Value
 * @author Brent Nix
 */
public class Pos<K, V> implements Entry<K, V> {

	K Key;
	V Value;
	//constructor
	public Pos(K key, V value)
	{
		Key = key;
		Value = value;
	}
	@Override
	@TimeComplexity("O(1)")
	public K getKey() 
	{
		//TCJ: time complexity does not vary with size - it is constant
		return Key;
	}

	@Override
	@TimeComplexity("O(1)")
	public V getValue() 
	{
		//TCJ: time complexity does not vary with size - it is constant
		return Value;
	}

	
}
