package com.algorithm.graph;

import com.algorithm.Bag;

public class Digraph {
    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Digraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }
    }

    public Digraph(Digraph g) {
        this.V = g.V;
        this.E = g.E;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = g.adj[v];
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public boolean addEdge(int v, int w) {
        if (hasEdge(v, w)) {
            return false;
        }
        if (v == w) {
            return false;
        }
        adj[v].add(w);
        E++;
        return true;
    }

    public Digraph reverse() {
        Digraph r = new Digraph(V);
        for (int v = 0; v < V; v++) {
            for (int w : adj(v)) {
                r.addEdge(w, v);
            }
        }
        return r;
    }

    public boolean hasEdge(int v, int w) {
        rangeCheck(v, w);
        for (int item : adj[v]) {
            if (item == w) {
                return true;
            }
        }
        return false;
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= V) {
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
        }
    }

    private void rangeCheck(int v, int w) {
        rangeCheck(v);
        rangeCheck(w);
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+V;
    }

}
