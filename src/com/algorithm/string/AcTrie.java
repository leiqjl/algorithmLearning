package com.algorithm.string;

import java.util.LinkedList;
import java.util.Queue;

public class AcTrie {
    private AcNode root = new AcNode('/');

    class AcNode {
        char data;
        AcNode[] children = new AcNode[26];
        boolean isEndingChar = false;
        int len = -1;
        AcNode fail;

        public AcNode(char c) {
            this.data = c;
        }
    }

    public void insert(String s) {
        char[] chars = s.toCharArray();
        AcNode p = root;
        for (int i = 0; i < chars.length; i++) {
            int idx = chars[i] - 'a';
            if (p.children[idx] == null) {
                AcNode node = new AcNode(chars[i]);
                p.children[idx] = node;
            }
            p = p.children[idx];
        }
        p.isEndingChar = true;
        p.len = chars.length;
    }

    public void buildFailurePointer() {
        Queue<AcNode> queue = new LinkedList<>();
        root.fail = null;
        queue.add(root);
        while (!queue.isEmpty()) {
            AcNode a = queue.remove();
            for (AcNode ac : a.children) {
                if (ac == null) {
                    continue;
                }
                if (a == root) {
                    ac.fail = root;
                } else {
                    AcNode fail = a.fail;
                    while (fail != null) {
                        AcNode failChild = fail.children[ac.data - 'a'];
                        if (failChild != null) {
                            ac.fail = failChild;
                            break;
                        }
                        fail = fail.fail;
                    }
                    if (fail == null) {
                        ac.fail = root;
                    }
                }
                queue.add(ac);
            }
        }
    }

    public void match(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        AcNode node = root;
        for (int i = 0; i < n; i++) {
            int idx = chars[i] - 'a';
            while (node.children[idx] == null && node != root) {
                node = node.fail;
            }
            node = node.children[idx];
            if (node == null) {
                node = root;
            }
            AcNode tmp = node;
            while (tmp != null) {
                if (tmp.isEndingChar) {
                    int pos = i - tmp.len + 1;
                    System.out.println("匹配起始下标 " + pos + "；长度 " + tmp.len);
                    System.out.println(s.substring(pos, pos+tmp.len));
                }
                tmp = tmp.fail;
            }
        }
    }

    public boolean find(String s) {
        char[] chars = s.toCharArray();
        AcNode p = root;
        for (int i = 0; i < chars.length; i++) {
            int idx = chars[i] - 'a';
            if (p.children[idx] == null) {
                return false;
            }
            p = p.children[idx];
        }
        return p.isEndingChar;
    }

    public static void main(String[] args) {
        AcTrie test = new AcTrie();
        test.insert("abcd");
        test.insert("bcd");
        test.insert("c");
        test.buildFailurePointer();
        String s = "sbcdabcds";
        test.match(s);
    }
}
