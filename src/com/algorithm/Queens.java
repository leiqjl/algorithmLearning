package com.algorithm;

public class Queens {
    int[] result;

    public Queens() {
        result = new int[8];
        for (int i = 0; i < 8; i++) {
            result[i] = -1;
        }
    }


    public void cal8queens(int row) {
        if (row == 8) {
            printQueens(result);
            return;
        }
        for (int col = 0; col < 8; col++) {
            if (isOk(row, col)) {
                result[row] = col;
                cal8queens(row + 1);
            }
        }
    }

    private boolean isOk(int row, int col) {
        int leftUp = col - 1, rightUp = col + 1;
        for (int i = row - 1; i >= 0; i--) {
            if (result[i] == col) {
                return false;
            }
            if (leftUp >= 0) {
                if (result[i] == leftUp) {
                    return false;
                }
            }
            if (rightUp < 8) {
                if (result[i] == rightUp) {
                    return false;
                }
            }
            leftUp--;
            rightUp++;
        }
        return true;
    }

    private void printQueens(int[] result) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (result[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Queens test = new Queens();
        test.cal8queens(0);
    }
}
