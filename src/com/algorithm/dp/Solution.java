package com.algorithm.dp;

/**
 * 求最长增长序列
 * 2,9,3,6,5,1,7的最长递增序列为2，3，5，7长度为4
 */
public class Solution {

    public int test(int[] a, int n) {
        int[] maxLen = new int[n];
        maxLen[0] = 1;
        for (int i = 1; i < n; i++) {
            int tmp = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] < a[i] && a[j] + 1 > tmp) {
                    tmp = maxLen[j] + 1;
                }
            }
            maxLen[i] = tmp;
        }
        int result = 0;
        for (int i = 0; i < n; i++) {
            if (maxLen[i] > result) {
                result = maxLen[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] a = {2, 9, 3, 6, 5, 1, 7};
        System.out.println(new Solution().test(a, a.length));
    }
}
