package com.algorithm.graph;

import com.algorithm.Stack;

public class EdgeWeightedDirectedCycle {
    private boolean[] marked;
    private DirectedEdge[] edgeTo;
    private boolean[] onStack;
    private Stack<DirectedEdge> cycle;

    public EdgeWeightedDirectedCycle(EdgeWeightedDigraph g) {
        onStack = new boolean[g.V()];
        edgeTo = new DirectedEdge[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(EdgeWeightedDigraph g, int v) {
        onStack[v] = true;
        marked[v] = true;

        for (DirectedEdge e : g.adj(v)) {
            int w = e.to();
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = e;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                while (e.from() != w) {
                    cycle.push(e);
                }
                return;
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<DirectedEdge> cycle() {
        return cycle;
    }
}
