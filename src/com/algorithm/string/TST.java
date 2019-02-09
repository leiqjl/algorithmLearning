package com.algorithm.string;

import com.algorithm.Queue;

public class TST<V> {
    private Node root;

    private class Node {
        char c;
        Node left, mid, right;
        V val;
    }

    public V get(String key) {
        Node x = get(root, key, 0);
        return x == null ? null : x.val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        char c = key.charAt(d);
        if (c < x.c) {
            return get(x.left, key, d);
        } else if (c > x.c){
            return get(x.right, key, d);
        } else if (d < key.length()-1) {
            return get(x.mid, key, d+1);
        } else {
            return x;
        }
    }

    public void put(String key, V val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, V val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d < key.length() - 1) {
            x.mid = put(x.mid, key, val, d+1);
        } else {
            x.val = val;
        }
        return x;
    }

    public void delete(String key) {
        root = delete(root, key, 0);
    }

    private Node delete(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            x.val = null;
        } else {
            char c = key.charAt(d);
            if (c < x.c) {
                x.left = delete(x.left, key, d);
            } else if (c > x.c) {
                x.right = delete(x.right, key, d);
            } else {
                x.mid = delete(x.mid, key, d+1);
            }
        }
        if (x.val != null) {
            return x;
        }
        if (x.left != null || x.mid != null || x.right != null) {
            return x;
        }
        return null;
    }

    public String longestPrefixOf(String s) {
        int length = 0;
        Node x = root;
        int d = 0;
        while (x != null && d < s.length()) {
            char c = s.charAt(d);
            if (c < x.c) {
                x = x.left;
            } else if (c > x.c) {
                x = x.right;
            } else {
                d++;
                if (x.val != null) {
                    length = d;
                }
                x = x.mid;
            }
        }
        return s.substring(0, length);
    }

    public Iterable<String> keys() {
        return keysWithPrefix("");
    }

    public Iterable<String> keysWithPrefix(String pre) {
        Queue<String> q = new Queue<>();
        Node x = get(root, pre, 0);
        if (x == null) {
            return q;
        }
        if (x.val != null) {
            q.enqueue(pre);
        }
        collect(x.mid, pre, q);
        return q;
    }

    private void collect(Node x, String pre, Queue<String> q) {
        if (x == null) {
            return;
        }
        collect(x.left, pre, q);
        collect(x.right,pre, q);
        if (x.val != null) {
            q.enqueue(pre+x.c);
        }
        collect(x.mid, pre + x.c, q);
    }

    public Iterable<String> keysThatMatch(String pat) {
        Queue<String> q = new Queue<>();
        collect(root, "", pat, q, 0);
        return q;
    }

    private void collect(Node x, String pre, String pat, Queue<String> q, int d) {
        if (x == null) {
            return;
        }
        char c = pat.charAt(d);
        if (c == '.' || c < x.c) {
            collect(x.left, pre, pat, q, d);
        }
        if (c == '.' || c == x.c) {
            int length = pat.length();
            if (d == length-1 && x.val != null) {
                q.enqueue(pre+x.c);
            }
            if (d < length-1) {
                collect(x.mid, pre+x.c, pat, q, d+1);
            }
        }
        if (c == '.' || c > x.c) {
            collect(x.right, pre, pat, q, d);
        }
    }

    public static void main(String[] args) {
        TST<Integer> tst = new TST<>();
        tst.put("shells", 15);
        tst.put("sells", 11);
        tst.put("sea", 14);
        tst.put("surely", 13);
        tst.put("she", 10);
        tst.put("shore", 7);
        tst.put("the", 8);
        tst.put("by", 4);
        tst.put("are", 12);

        Iterable<String> sh = tst.keysWithPrefix("sh");
        sh.forEach(System.out::println);

        System.out.println("-------------");
        Iterable<String> strings = tst.keysThatMatch("s..");
        strings.forEach(System.out::println);
    }

}
