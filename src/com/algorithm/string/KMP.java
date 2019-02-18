package com.algorithm.string;

import java.util.Arrays;

public class KMP {

    public static int kmp(char[] a, int n, char[] b, int m) {
        int[] next = getNext(b, m);
        int j = 0;
        for (int i = 0; i < n; i++) {
            while (j > 0 && a[i] != b[j]) {
                j = next[j - 1] + 1;
            }
            if (a[i] == b[j]) {
                j++;
            }
            if (j == m) {
                return i - m + 1;
            }
        }
        return -1;
    }

    private static int[] getNext(char[] b, int m) {
        int[] next = new int[m];
        next[0] = -1;
        int k = -1;
        for (int i = 1; i < m; i++) {
            while (k != -1 && b[k + 1] != b[i]) {
                k = next[k];
            }
            if (b[k + 1] == b[i]) {
                k++;
            }
            next[i] = k;
        }
        return next;
    }

    public static void main(String[] args) {
        String pattern = "ababacd";
        int[] next = getNext(pattern.toCharArray(), pattern.length());
        String s = "asabsabasababsababasababasababacsababacd";
        int kmp = kmp(s.toCharArray(), s.length(), pattern.toCharArray(), pattern.length());
        System.out.println(Arrays.toString(next));
        System.out.println(kmp);
        System.out.println(s.length()+" "+pattern.length());
        System.out.println(s.substring(kmp, kmp+pattern.length()));
    }
}
