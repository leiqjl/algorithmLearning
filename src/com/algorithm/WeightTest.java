package com.algorithm;

public class WeightTest {
    // 回溯算法实现。注意：我把输入的变量都定义成了成员变量。
    private int maxW = Integer.MIN_VALUE; // 结果放到 maxW 中
    private int maxV = Integer.MIN_VALUE; // 结果放到 maxV 中
    private int[] weight = {2, 2, 4, 6, 3};  // 物品重量
    private int[] value = {3, 4, 8, 9, 6};  //物品价值
    private int n = 5; // 物品个数
    private int w = 9; // 背包承受的最大重量
    private boolean[][] mem = new boolean[5][10];

    //max weight
    public void weight(int i, int cw) {
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        weight(i + 1, cw);
        if (cw + weight[i] <= w) {
            weight(i + 1, cw + weight[i]);
        }
    }

    //max weight with mem
    public void weightM(int i, int cw) {
        if (cw == w || i == n) {
            if (cw > maxW) {
                maxW = cw;
            }
            return;
        }
        if (mem[i][cw]) {
            return;
        }
        mem[i][cw] = true;
        weightM(i + 1, cw);
        if (cw + weight[i] <= w) {
            weightM(i + 1, cw + weight[i]);
        }
    }

    //max weight
    public int knapsack(int n, int w) {
        boolean[][] states = new boolean[n][w + 1];
        states[0][0] = true;
        states[0][weight[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= w; j++) {
                //不放i
                if (states[i - 1][j]) {
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; j++) {
                //放i
                if (states[i - 1][j]) {
                    states[i][j+weight[i]] = true;
                }
            }
        }

        for (int i = w; i >= 0; i--) {
            if (states[n - 1][i]) {
                return i;
            }
        }
        return 0;
    }

    //max weight
    public int knapsack1(int n, int w) {
        boolean[] states = new boolean[w + 1];
        states[0] = true;
        states[weight[0]] = true;
        for (int i = 1; i < n; i++) {
            for (int j = w-weight[i]; j >= 0 ; j--) {
                //放i
                if (states[j]) {
                    states[j+weight[i]] = true;
                }
            }
        }
        for (int i = w; i >=0; i--) {
            if (states[i]) {
                return i;
            }
        }
        return 0;
    }

    //max value
    public void value(int i, int cw, int cv) {
        if (cw == w || i == n) {
            if (cv > maxV) {
                maxV = cv;
            }
            return;
        }
        value(i + 1, cw, cv);
        if (cw + weight[i] <= w) {
            value(i + 1, cw + weight[i], cv + value[i]);
        }
    }

    //max weight
    public int knapsackV(int n, int w) {
        int[][] states = new int[n][w + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < w + 1; j++) {
                states[i][j] = -1;
            }
        }
        states[0][0] = 0;
        states[0][weight[0]] = value[0];
        for (int i = 1; i < n; i++) {
            int tmp = w - weight[i];
            for (int j = 0; j <= w; j++) {
                if (states[i - 1][j] > -1) {
                    //不放i
                    states[i][j] = states[i - 1][j];
                }
            }
            for (int j = 0; j <= w - weight[i]; j++) {
                //放i
                if (states[i - 1][j] > -1) {
                    int v = states[i - 1][j] + value[i];
                    if (v > states[i][j + weight[i]]) {
                        states[i][j + weight[i]] = v;
                    }
                }
            }
        }

        int max = -1;
        for (int i = w; i >= 0; i--) {
            if (states[n - 1][i] > max) {
                max = states[n - 1][i];
            }
        }
        return max;
    }



    public static void main(String[] args) {
        WeightTest test = new WeightTest();

        test.value(0, 0, 0);
        System.out.println(test.maxV);

        int knapsack = test.knapsack(5, 9);
        System.out.println(knapsack);
        System.out.println(test.knapsack1(5, 9));
        System.out.println(test.knapsackV(5, 9));


    }

}
