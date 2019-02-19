package com.algorithm.dp;

public class LongCommonStr {
    public int lcs(String s1, String s2) {
        char[] a = s1.toCharArray();
        int n = s1.length();
        char[] b = s2.toCharArray();
        int m = s2.length();
        int[][] maxLcs = new int[n][m];
        for (int j = 0; j < m; j++) {
            if (a[0] == b[j]) {
                maxLcs[0][j] = 1;
            } else if (j != 0) {
                maxLcs[0][j] = maxLcs[0][j - 1];
            } else {
                maxLcs[0][j] = 0;
            }
        }

        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) {
                maxLcs[i][0] = 1;
            } else if (i != 0) {
                maxLcs[i][0] = maxLcs[i - 1][0];
            } else {
                maxLcs[i][0] = 0;
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    maxLcs[i][j] = max(maxLcs[i - 1][j - 1] + 1, maxLcs[i - 1][j], maxLcs[i][j - 1]);
                } else {
                    maxLcs[i][j] = max(maxLcs[i - 1][j - 1], maxLcs[i - 1][j], maxLcs[i][j - 1]);
                }
            }
        }


        return maxLcs[n - 1][m - 1];
    }

    private int max(int x, int y, int z) {
        return Math.max(x, Math.max(y, z));
    }

    public static void main(String[] args) {
        String s1 = "abcdefg";
        String s2 = "acccheafg";
        LongCommonStr lcs = new LongCommonStr();
        System.out.println(lcs.lcs(s1, s2));
    }
}
