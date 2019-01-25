package com.algorithm;

public class SeparateChainingHashST<K, V> {
    private int N;
    private int M;
    private SequentialSearchST<K, V>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int M) {
        this.M = M;
        st = (SequentialSearchST<K, V>[]) new SequentialSearchST[M];
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % M;
    }

    public V get(K key) {
        return st[hash(key)].get(key);
    }

    public void put(K key, V val) {
        st[hash(key)].put(key, val);
    }
}
