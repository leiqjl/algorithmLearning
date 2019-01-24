package com.algorithm;

public class SeparateChainingHashST<K, V> {
    private int n;
    private int m;
    private SequentialSearchST<K, V>[] st;

    public SeparateChainingHashST() {
        this(997);
    }

    public SeparateChainingHashST(int m) {
        this.m = m;
        st = (SequentialSearchST<K, V>[]) new SequentialSearchST[m];
        for (int i = 0; i < m; i++) {
            st[i] = new SequentialSearchST<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0xfffffff) % m;
    }

    public V get(K key) {
        return st[hash(key)].get(key);
    }

    public void put(K key, V val) {
        st[hash(key)].put(key, val);
    }

}
