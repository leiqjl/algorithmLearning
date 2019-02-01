package com.algorithm.graph;

import com.algorithm.MinPQ;
import com.algorithm.Queue;

public class LazyPrimMST {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph g) {
        pq = new MinPQ<>();
        marked = new boolean[g.V()];
        mst = new Queue<>();
        visit(g, 0);
        while (!pq.isEmpty()) {
            Edge edge = pq.delMin();
            int v = edge.either();
            int w = edge.other(v);
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.enqueue(edge);
            if (!marked[v]) {
                visit(g, v);
            }
            if (!marked[w]) {
                visit(g, w);
            }
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }

}
