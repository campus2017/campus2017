package com.qunar.campus2017.characterCounter.entity;

import java.util.Map;

/**
 * Created by chunming.xcm on 2017/2/24.
 */
public class MyEntry<K, V> implements Map.Entry<K, V> {
    private K key;
    private V value;

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public K setKey(K key) {
        return this.key = key;
    }

    public V setValue(V value) {
        return this.value = value;
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
