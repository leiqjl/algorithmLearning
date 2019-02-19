package com.algorithm.dp;

public class CoinTest {
    private static int[] coin = {1, 3, 5};
    private static int[] mem;

    public static int test(int n) {
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

    public static int calCoinDP(int n) {
        mem = new int[n+1];
        return coinDP(n);
    }

    public static int coinDP(int n) {
        if (n == 1 || n == 3 || n == 5) {
            return 1;
        }
        if (mem[n] > 0) {
            return mem[n];
        }
        int a = 0;
        if (n > 5) {
            a = coinDP(n - 5);
        }
        int b = 0;
        if (n > 3) {
            b = coinDP(n - 3);
        }
        int c = 0;
        if (n > 1) {
            c = coinDP(n - 1);
        }
        int tmp = Math.min(Math.min(a, b), c) + 1;
        mem[n] = tmp;
        return tmp;
    }

    public static int minCoins(int money) {
        if (money == 1 || money == 3 || money == 5) {
            return 1;
        }
        boolean [][] state = new boolean[money][money + 1];
        if (money >= 1) state[0][1] = true;
        if (money >= 3) state[0][3] = true;
        if (money >= 5) state[0][5] = true;
        for (int i = 1; i < money; i++) {
            for (int j = 1; j <= money; j++) {
                if (state[i-1][j]) {
                    if (j + 1 <= money) state[i][j + 1] = true;
                    if (j + 3 <= money) state[i][j + 3] = true;
                    if (j + 5 <= money) state[i][j + 5] = true;
                    if (state[i][money]) {
                        return i+1;
                    }
                }
            }
        }
        return money;
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 12; i++) {
//            System.out.println(i + "  " + test(i));
//        }

        System.out.println("2:"+minCoins(2));
        System.out.println("4:"+minCoins(4));
        System.out.println("6:"+minCoins(6));
        System.out.println("11:"+minCoins(11));
        System.out.println("49:"+minCoins(49));

    }
}
