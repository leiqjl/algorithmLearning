package com.algorithm.string;

import java.util.PriorityQueue;

public class HuffmanCode {
    int[] cnt = new int[26];

    Node root;

    public class Node implements Comparable {
        char c;
        int data;
        Node left,right;
        boolean isEndingChar;
        public Node(char c) {
            this.c = c;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }

        @Override
        public int compareTo(Object o) {
            if (o instanceof Node) {
                int data = ((Node) o).data;
                return this.data - data;
            }
            return 0;
        }
    }

    public HuffmanCode(String s) {
        for (char c : s.toCharArray()) {
            cnt[c-'a']++;
        }
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < cnt.length; i++) {
            if (cnt[i] > 0) {
                Node node = new Node((char)(i+'a'));
                node.data = cnt[i];
                node.isEndingChar = true;
                queue.add(node);
            }
        }

        while (!queue.isEmpty() && queue.size() > 1) {
            Node left = queue.remove();
            Node right = queue.remove();
            Node p = new Node(left.data+right.data, left, right);
            queue.add(p);
        }
        if (!queue.isEmpty()) {
            root = queue.remove();
        }
    }


    private void print(Node node, String s) {
        if (node == null) {
            return;
        }
        if (node.isEndingChar) {
            System.out.println(node.c+",code:"+s+",count:"+node.data);
        }
        print(node.left, s+0);
        print(node.right, s+1);
    }

    public void print() {
        print(root, "");
    }

    public static void main(String[] args) {
        String s = "sbcdabcdsdddcbb";
        HuffmanCode code = new HuffmanCode(s);
        code.print();

    }
}
