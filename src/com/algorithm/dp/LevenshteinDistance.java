package com.algorithm.dp;

public class LevenshteinDistance {
    private char[] a;
    private int n;
    private char[] b;
    private int m;
    private int minDist = Integer.MAX_VALUE;

    public LevenshteinDistance(String s1, String s2) {
        a = s1.toCharArray();
        n = s1.length();
        b = s2.toCharArray();
        m = s2.length();
    }

    public int lwstBT() {
        lwstBT(0, 0, 0);
        return minDist;
    }

    private void lwstBT(int i, int j, int edist) {
        if (i == n || j == m) {
            if (i < n) {
                edist += (n - i);
            }
            if (j < m) {
                edist += (m - j);
            }
            if (edist < minDist) {
                minDist = edist;
            }
            return;
        }
        if (a[i] == b[j]) {
            lwstBT(i + 1, j + 1, edist);
        } else {
            lwstBT(i + 1, j, edist + 1);
            lwstBT(i, j + 1, edist + 1);
            lwstBT(i + 1, j + 1, edist + 1);
        }
    }

    public int lwstDP() {
        int[][] minDist = new int[n][m];
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) {
                minDist[0][j] = j;
            } else if (j != 0) {
                minDist[0][j] = minDist[0][j - 1] + 1;
            } else {
                minDist[0][j] = 1;
            }
        }
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) {
                minDist[i][0] = i;
            } else if (i != 0) {
                minDist[i][0] = minDist[i - 1][0] + 1;
            } else {
                minDist[i][0] = 1;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    minDist[i][j] = min(minDist[i - 1][j - 1], minDist[i][j - 1] + 1, minDist[i - 1][j] + 1);
                } else {
                    minDist[i][j] = min(minDist[i - 1][j - 1]+1, minDist[i][j - 1] + 1, minDist[i - 1][j] + 1);
                }
            }
        }
        return minDist[n-1][m-1];
    }

    private int min(int x, int y, int z) {
        int tmp = x;
        if (y < tmp) {
            tmp = y;
        }
        if (z < tmp) {
            tmp = z;
        }
        return tmp;
    }

    public static void main(String[] args) {
        String s1 = "mitcmu";
        String s2 = "mtacnu";
        LevenshteinDistance lsd = new LevenshteinDistance(s1, s2);
//        System.out.println(lsd.lwstBT());
        System.out.println(lsd.lwstDP());


        s1 = "mitcmu";
        s2 = "mimcnu";
        lsd = new LevenshteinDistance(s1, s2);
        System.out.println(lsd.lwstDP());
    }
}
