package cn.itcast.po;

import java.util.Map;

public class MyEntry<K, V> implements Map.Entry<K, V> {
	public K key;
	public V value;

	public K getKey() {
		// TODO Auto-generated method stub
		return null;
	}

	public V getValue() {
		// TODO Auto-generated method stub
		return null;
	}

	public V setValue(V value) {
		// TODO Auto-generated method stub
		return null;
	}

	public MyEntry(K key, V value) {
		super();
		this.key = key;
		this.value = value;
	}

	@Override
	public String toString() {
		return "MyEntry [key=" + key + ", value=" + value + "]";
	}
	
}
