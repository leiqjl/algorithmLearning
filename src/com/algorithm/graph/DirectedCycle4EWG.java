package com.algorithm.graph;

import com.algorithm.Stack;

public class DirectedCycle4EWG {
    private boolean[] marked;
    private int[] edgeTo;
    private Stack<Integer> cycle;
    private boolean[] onStack;

    public DirectedCycle4EWG(EdgeWeightedDigraph g) {
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
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
        for (DirectedEdge edge : g.adj(v)) {
            int w = edge.to();
            if (this.hasCycle()) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }

    public Iterable<Integer> cycle() {
        return cycle;
    }
}
