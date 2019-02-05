package com.algorithm.graph;

import com.algorithm.Queue;

public class BellmanFordSP {
    private double[] distTo;
    private DirectedEdge[] edgeTo;
    private boolean[] onQ;
    private Queue<Integer> queue;
    private int cost;
    private Iterable<DirectedEdge> cycle;

    public BellmanFordSP(EdgeWeightedDigraph g, int s) {
        int vSize = g.V();
        distTo = new double[vSize];
        edgeTo = new DirectedEdge[vSize];
        onQ = new boolean[vSize];
        queue = new Queue<>();
        for (int v = 0; v < vSize; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        queue.enqueue(s);
        onQ[s] = true;
        while (!queue.isEmpty() && !hasNegativeCycle()) {
            int v = queue.dequeue();
            onQ[v] = false;
            relax(g, v);
        }
    }


    private void relax(EdgeWeightedDigraph g, int v) {
        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (distTo[w] > distTo[v] + e.weight()) {
                distTo[w] = distTo[v] + e.weight();
                edgeTo[w] = e;
                if (!onQ[w]) {
                    queue.enqueue(w);
                    onQ[w] = true;
                }
            }
        }

        if (cost++ % g.V() == 0) {
            findNegativeCycle();
        }
    }

    private boolean hasNegativeCycle() {
        return cycle != null;
    }

    private void findNegativeCycle() {
        int V = edgeTo.length;
        EdgeWeightedDigraph spt = new EdgeWeightedDigraph(V);
        for (int v = 0; v < V; v++) {
            if (edgeTo[v] != null) {
                spt.addEdge(edgeTo[v]);
            }
        }
        EdgeWeightedDirectedCycle cf = new EdgeWeightedDirectedCycle(spt);
        cycle = cf.cycle();
    }

    public Iterable<DirectedEdge> negativeCycle() {
        return cycle;
    }


}
