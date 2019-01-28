package com.algorithm.graph;

public class KosarajuSCC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public KosarajuSCC(Digraph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        DepthFirstOrder order = new DepthFirstOrder(g.reverse());
        for (int s : order.reversePost()) {
            if (!marked[s]) {
                dfs(g, s);
                count++;
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }

    public boolean stronglyConnected(int v, int w) {
        return id[v] == id[w];
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        Digraph g = new Digraph(13);
        g.addEdge(0, 2);
        g.addEdge(0, 6);
        g.addEdge(6, 7);
        g.addEdge(7, 8);
        g.addEdge(8, 7);
        g.addEdge(2, 3);
        g.addEdge(2, 4);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(4, 11);
        g.addEdge(11, 9);
        g.addEdge(9, 6);
        g.addEdge(9, 8);
        g.addEdge(9, 12);
        g.addEdge(12, 10);
        g.addEdge(12, 11);
        g.addEdge(10, 9);
        g.addEdge(5, 0);
        g.addEdge(5, 3);
        g.addEdge(1, 0);
        DepthFirstOrder order = new DepthFirstOrder(g.reverse());
        Iterable<Integer> post = order.post();
        System.out.print("pre:");
        visit(order.pre());
        System.out.print("post:");
        visit(post);
        System.out.print("reversePost:");
        visit(order.reversePost());
        DepthFirstOrder order1 = new DepthFirstOrder(g.reverse());
        System.out.print("reversePost1:");
        visit(order1.reversePost());
        KosarajuSCC scc = new KosarajuSCC(g);
        System.out.println(scc.count());

    }

    private static void visit(Iterable<Integer> iterable) {
        iterable.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }


}
