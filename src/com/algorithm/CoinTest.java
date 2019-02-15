package com.algorithm;

public class CoinTest {
    public static int test(int n) {
        int[] coin = {1, 3, 5};
        int[] arr = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            arr[i] = Integer.MAX_VALUE;
        }
        for (int i = 1; i < n + 1; i++) {
            for (int j : coin) {
                if (j <= i && arr[i - j] + 1 < arr[i]) {
                    arr[i] = arr[i - j] + 1;
                }
            }
        }
        return arr[n];
    }

    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            System.out.println(i+"  "+test(i));
        }
    }
}
