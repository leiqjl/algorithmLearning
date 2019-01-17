package com.algorithm;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class BST<K extends Comparable<K>, V> {
    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int n;
        private int h;

        public Node(K key, V val, int n) {
            this.key = key;
            this.val = val;
            this.n = n;
        }

        public Node(K key, V val, int n, int h) {
            this.key = key;
            this.val = val;
            this.n = n;
            this.h = h;
        }
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.n;
    }

    public int height() {
        return height(root);
    }

    private int height(Node x) {
        return x == null ? -1 : x.h;
    }

    public int height1() {
        return height1(root);
    }

    private int height1(Node x) {
        if (x == null) {
            return -1;
        }
        int lh = height1(x.left);
        int rh = height1(x.right);
        return Math.max(lh, rh) + 1;
    }


    public V get(K key) {
        return get(root, key);
    }

    public V getIteration(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                return x.val;
            }
        }
        return null;
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
            return new Node(key, val, 1, 0);
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
        x.h = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    public void putIteration(K key, V val) {
        Node z = new Node(key, val, 1, 0);
        if (root == null) {
            root = z;
            return;
        }
        Node parent = null, x = root;
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (x != null) {
            parent = x;
            stack.push(x);
            int cmp = key.compareTo(x.key);
            if (cmp < 0) {
                x = x.left;
            } else if (cmp > 0) {
                x = x.right;
            } else {
                x.val = val;
                return;
            }
        }
        int cmp = key.compareTo(parent.key);
        if (cmp < 0) {
            parent.left  = z;
        } else {
            parent.right = z;
        }
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            pop.n = size(pop.left) + size(pop.right) + 1;
            pop.h = Math.max(height(pop.left), height(pop.right)) + 1;
        }
    }

    public K minIteration() {
        Node x = root;
        while (x != null && x.left != null) {
            x = x.left;
        }
        return x == null ? null : x.key;
    }

    public K min() {
        Node x = min(root);
        return x == null ? null : x.key;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    public K max() {
        Node x = max(root);
        return x == null ? null : x.key;
    }

    private Node max(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return min(x.right);
    }

    public K maxIteration() {
        Node x = root;
        while (x != null && x.right != null) {
            x = x.right;
        }
        return x == null ? null : x.key;
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
    public K floorIteration(K key) {
        if (root == null) {
            return null;
        }
        Node target = null;
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x.key;
            } else if (cmp < 0){
                x = x.left;
            } else {
                target = x;
                x = x.right;
            }
        }
        return target == null ? null : target.key;
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

    public K ceilingIteration(K key) {
        if (root == null) {
            return null;
        }
        Node target = null;
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x.key;
            } else if (cmp > 0){
                x = x.right;
            } else {
                target = x;
                x = x.left;
            }
        }
        return target == null ? null : target.key;
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

    public int rankIteration(K key) {
        Node x = root;
        if (x == null) {
            return 0;
        }
        while (x != null) {
            //TODO
        }
        return 0;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.n = size(x.left) + size(x.right) + 1;
        x.h = Math.max(height1(x.left), height1(x.right)) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.n = size(x.left) + size(x.right) + 1;
        x.h = Math.max(height1(x.left), height1(x.right)) + 1;
        return x;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            if (x.right == null) {
                return x.left;
            }
            if (x.left == null) {
                return x.right;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.n = size(x.left) + size(x.right) + 1;
        x.h = Math.max(height1(x.left), height1(x.right)) + 1;
        return x;
    }

    public void print() {
        print(root);
        System.out.println();
    }

    private void print(Node x) {
        if (x == null) {
            return;
        }
        print(x.left);
        System.out.print(x.key + ":" + x.val + " ");
        print(x.right);
    }

    public Iterable<K> keys() {
        return keys(min(), max());
    }

    public Iterable<K> keys(K lo, K hi) {
        Queue<K> queue = new ArrayDeque<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    private void keys(Node x, Queue<K> queue, K lo, K hi) {
        if (x == null) {
            return;
        }
        int cmplo = lo.compareTo(x.key);
        int cmphi = hi.compareTo(x.key);
        if (cmplo < 0) {
            keys(x.left, queue, lo, hi);
        }
        if (cmplo <= 0 && cmphi >= 0) {
            queue.add(x.key);
        }
        if (cmphi > 0) {
            keys(x.right, queue, lo, hi);
        }
    }

}
