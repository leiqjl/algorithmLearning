package com.algorithm.graph;

import com.algorithm.MinPQ;
import com.algorithm.Queue;
import com.algorithm.UF;

public class KruskalMST {
    private Queue<Edge> mst;
    private double weight;
    public KruskalMST(EdgeWeightedGraph g) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge edge : g.edges()) {
            pq.insert(edge);
        }
        UF uf = new UF(g.V());
        while (!pq.isEmpty() && mst.size() < g.V()-1) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v, w)) {
                continue;
            }
            uf.union(v, w);
            mst.enqueue(e);
            weight += e.weight();
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
}
