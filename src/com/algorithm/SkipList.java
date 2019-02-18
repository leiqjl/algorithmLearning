package com.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SkipList {

    private static final int MAX_LEVEL = 16;

    private int levelCount = 1;

    private Node head = new Node();

    private Random random = new Random();

    public class Node {
        private int data = -1;
        private Node[] forwards = new Node[MAX_LEVEL];
        private int maxLevel = 0;

        public Node() {
        }

        public Node(int data, int maxLevel) {
            this.data = data;
            this.maxLevel = maxLevel;
        }

        @Override
        public String toString() {
            return "{ data: " + data + "; levels: " + maxLevel + " }";
        }
    }

    public void insert(int val) {
        int level = randomLevel();
        Node newNode = new Node(val, level);
        Node[] tmp = new Node[level];
        for (int i = 0; i < level; i++) {
            tmp[i] = head;
        }
        Node node = head;
        //每次结点对应位置
        for (int i = level - 1; i >= 0; i--) {
            while (node.forwards[i] != null && node.forwards[i].data < val) {
                node = node.forwards[i];
            }
            tmp[i] = node;
        }

        for (int i = 0; i < level; i++) {
            newNode.forwards[i] = tmp[i].forwards[i];
            tmp[i].forwards[i] = newNode;
        }

        //update level
        if (levelCount < level) {
            levelCount = level;
        }

    }

    public Node get(int val) {
        Node node = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (node.forwards[i] != null && node.forwards[i].data < val) {
                node = node.forwards[i];
            }
        }
        if (node.forwards[0] != null && node.forwards[0].data == val) {
            return node.forwards[0];
        }
        return null;
    }

    public void delete(int val) {
        Node[] tmp = new Node[levelCount];
        Node node = head;
        for (int i = levelCount - 1; i >= 0; i--) {
            while (node.forwards[i] != null && node.forwards[i].data < val) {
                node = node.forwards[i];
            }
            tmp[i] = node;
        }
        if (node.forwards[0] != null && node.forwards[0].data == val) {
            for (int i = levelCount - 1; i >= 0; i--) {
                if (tmp[i].forwards[i] != null && node.forwards[i].data == val) {
                    tmp[i].forwards[i] = tmp[i].forwards[i].forwards[i];
                }
            }
        }
    }

    public List<Node> get(int lo, int hi) {
        Node node = get(lo);
        List<Node> list = new ArrayList<>();
        if (node != null) {
            while (node != null && node.data <= hi) {
                list.add(node);
                node = node.forwards[0];
            }
        }
        return list;
    }

    public void printAll() {
        Node node = head;
        while (node.forwards[0] != null) {
            System.out.print(node.forwards[0] + " ");
            node = node.forwards[0];
        }
        System.out.println();
    }

    // 随机 level 次，如果是奇数层数 +1，防止伪随机
    private int randomLevel() {
        int level = 1;
        for (int i = 1; i < MAX_LEVEL; ++i) {
            if (random.nextInt() % 2 == 1) {
                level++;
            }
        }
        return level;
    }

    public static void main(String[] args) {
        SkipList skipList = new SkipList();
        for (int i = 1; i <= 10; i++) {
            skipList.insert(i);
        }
        skipList.printAll();
        List<Node> nodes = skipList.get(3, 7);
        System.out.println(nodes);
    }
}
