package com.algorithm.dp;

public class DynamicTest {
    private int[][] matrix;
    private int minDist = Integer.MAX_VALUE;
    private int[][] mem;

    public DynamicTest(int[][] matrix) {
        this.matrix = matrix;
    }


    private void minDistBT(int i, int j, int[][] matrix, int dist, int n) {
        if (i == n - 1 && j == n - 1) {
            if (dist < minDist) {
                minDist = dist;
            }
            return;
        }
        if (i < n - 1) {
            minDistBT(i + 1, j, matrix, dist + matrix[i + 1][j], n);
        }
        if (j < n - 1) {
            minDistBT(i, j + 1, matrix, dist + matrix[i][j + 1], n);
        }
    }

    public int minDistBT(int[][] a, int n) {
        minDistBT(0, 0, a, a[0][0], n);
        return minDist;
    }


    public int minDistDP(int[][] matrix, int n) {
        int[][] states = new int[n][n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += matrix[i][0];
            states[i][0] = sum;
        }
        sum = 0;
        for (int j = 0; j < n; j++) {
            sum += matrix[0][j];
            states[0][j] = sum;
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                states[i][j] = Math.min(states[i][j-1], states[i-1][j]) + matrix[i][j];
            }
        }
        return states[n-1][n-1];
    }

    public int minDist(int[][] matrix, int n) {
        mem = new int[n][n];
        return minDist(n - 1, n - 1, matrix);
    }

    private int minDist(int i, int j, int[][] matrix) {
        if (i == 0 && j == 0) {
            return matrix[0][0];
        }
        if (mem[i][j] > 0) {
            return mem[i][j];
        }
        int minLeft = Integer.MAX_VALUE;
        if (j - 1 >= 0) {
            minLeft = minDist(i, j - 1, matrix);
        }
        int minUp = Integer.MAX_VALUE;
        if (i - 1 >= 0) {
            minUp = minDist(i-1, j, matrix);
        }
        int currMinDist = matrix[i][j] + Math.min(minLeft, minUp);
        mem[i][j] = currMinDist;
        return currMinDist;
    }


    public static void main(String[] args) {
        int[][] matrix = {{1, 3, 5, 9},
                {2, 1, 3, 4},
                {5, 2, 6, 7},
                {6, 8, 4, 3}};
        DynamicTest test = new DynamicTest(matrix);
//        System.out.println(test.minDistBT(matrix, 4));
        System.out.println(test.minDistDP(matrix, 4));
        System.out.println(test.minDist(matrix, 4));
//        System.out.println(test.minDist);

    }
}
