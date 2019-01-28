package com.algorithm.graph;


import com.algorithm.Queue;

import java.util.Stack;

public class DepthFirstOrder {
    private boolean[] marked;
    //前序 dfs
    private Queue<Integer> pre;
    //后序顶点完成的顺序
    private Queue<Integer> post;
    private Stack<Integer> reversePost;

    public DepthFirstOrder(Digraph g) {
        pre = new Queue<>();
        post = new Queue<>();
        reversePost = new Stack<>();
        marked = new boolean[g.V()];
        for (int v = 0; v < g.V(); v++) {
            if (!marked[v]) {
                dfs(g, v);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        pre.enqueue(v);
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre() {
        return pre;
    }

    public Iterable<Integer> post() {
        return post;
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}
