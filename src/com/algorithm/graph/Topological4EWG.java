package com.algorithm.graph;

/**
 * 拓扑排序
 */
public class Topological4EWG {
    private Iterable<Integer> order;

    public Topological4EWG(EdgeWeightedDigraph g) {
        DirectedCycle4EWG cycle = new DirectedCycle4EWG(g);
        if (!cycle.hasCycle()) {
            DepthFirstOrder4EWG depthFirstOrder = new DepthFirstOrder4EWG(g);
            order = depthFirstOrder.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        return order != null;
    }
}
