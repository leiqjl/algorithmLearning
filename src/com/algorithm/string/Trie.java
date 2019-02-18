package com.algorithm.string;

public class Trie {

    private TrieNode root = new TrieNode('/');

    class TrieNode {
        char data;
        TrieNode[] children;
        boolean isEndingChar;

        public TrieNode(char c) {
            this.data = c;
            children = new TrieNode[26];
            isEndingChar = false;
        }
    }

    public void insert(String s) {
        char[] chars = s.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < chars.length; i++) {
            int idx = chars[i] - 'a';
            if (p.children[idx] == null) {
                TrieNode node = new TrieNode(chars[i]);
                p.children[idx] = node;
            }
            p = p.children[idx];
        }
        p.isEndingChar = true;
    }

    public boolean find(String s) {
        char[] chars = s.toCharArray();
        TrieNode p = root;
        for (int i = 0; i < chars.length; i++) {
            int idx = chars[i] - 'a';
            if (p.children[idx] == null) {
                return false;
            }
            p = p.children[idx];
        }
        return p.isEndingChar;
    }
}
