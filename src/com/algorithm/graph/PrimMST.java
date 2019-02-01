package com.algorithm.graph;

import com.algorithm.IndexMinPQ;
import com.algorithm.Queue;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class PrimMST {
    private Edge[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private IndexMinPQ<Double> pq;

    public PrimMST(EdgeWeightedGraph g) {
        edgeTo = new Edge[g.V()];
        distTo = new double[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        pq = new IndexMinPQ<>(g.V());

        distTo[0] = 0.0;
        pq.insert(0, 0.0);
        while (!pq.isEmpty()) {
            visit(g, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (marked[w]) {
                continue;
            }
            if (e.weight() < distTo[w]) {
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) {
                    pq.changeKey(w, distTo[w]);
                } else {
                    pq.insert(w, distTo[w]);
                }
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null) {
                mst.enqueue(e);
            }
        }
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }

    public static void main(String[] args) throws IOException {

        EdgeWeightedGraph g = null;
        String pathname = "src/com/algorithm/graph/ewg.txt";
        try (FileReader reader = new FileReader(pathname);
             BufferedReader br = new BufferedReader(reader)
        ) {
            String str1 = br.readLine();
            g = new EdgeWeightedGraph(Integer.valueOf(str1));
            int e = Integer.valueOf(br.readLine());

            String line;
            for (int i = 0; i < e; i++) {
                line = br.readLine();
                String[] s = line.split(" ");
                Edge edge = new Edge(Integer.valueOf(s[0]),Integer.valueOf(s[1]),Double.valueOf(s[2]));
                g.addEdge(edge);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrimMST primMST = new PrimMST(g);

    }
}
