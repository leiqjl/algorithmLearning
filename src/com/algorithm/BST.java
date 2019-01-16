package com.algorithm;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int n;

        public Node(K key, V val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.n;
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.n = size(x.left) + size(x.right) + 1;
        return x;
    }

    public K min() {
        Node x = min(root);
        return x == null ? null : x.key;
    }

    private Node min(Node x) {
        return x == null ? null : min(x.left);
    }

    public K max() {
        Node x = max(root);
        return x == null ? null : x.key;
    }

    private Node max(Node x) {
        return x == null ? null : max(x.right);
    }

    public K floor(K key) {
        Node x = floor(root, key);
        return x == null ? null : x.key;
    }

    private Node floor(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }
        Node node = floor(x.right, key);
        return node == null ? x : node;
    }

    public K ceiling(K key) {
        Node x = ceiling(root, key);
        return x == null ? null : x.key;
    }


    private Node ceiling(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }
        Node node = ceiling(x.left, key);
        return node == null ? x : node;
    }

    public K select(int k) {
        Node select = select(root, k);
        return select == null ? null : select.key;
    }

    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int size = size(x);
        if (size > k) {
            return select(x.left, k);
        } else if (size < k) {
            return select(x.right, k - size - 1);
        } else {
            return x;
        }
    }

    public int rank(K key) {
        return rank(key, root);
    }

    private int rank(K key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return size(x.left) + 1 + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }
}
