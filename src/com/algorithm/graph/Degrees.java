package com.algorithm.graph;

import com.algorithm.Queue;

public class Degrees {
    private int[] inDegree;
    private int[] outDegree;

    public Degrees(Digraph g) {
        int size = g.V();
        inDegree = new int[size];
        outDegree = new int[size];
        for (int v = 0; v < size; v++) {
            for (int w : g.adj(v)) {
                outDegree[v]++;
                inDegree[w]++;
            }
        }
    }

    public int indegree(int v) {
        return inDegree[v];
    }

    public int outdegree(int v) {
        return outDegree[v];
    }

    public Iterable<Integer> sources() {
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < inDegree.length; v++) {
            if (inDegree[v] == 0) {
                queue.enqueue(v);
            }
        }
        return queue;
    }

    public Iterable<Integer> sinks() {
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < outDegree.length; v++) {
            if (outDegree[v] == 0) {
                queue.enqueue(v);
            }
        }
        return queue;
    }

    public boolean isMap() {
        if (outDegree == null || outDegree.length <= 0) {
            return false;
        }
        for (int out : outDegree) {
            if (out != 1) {
                return false;
            }
        }
        return true;
    }
}
