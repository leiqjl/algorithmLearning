package com.algorithm;

public class SortCount {
    private int num = 0;

    public int count(int[] a) {
        num = 0;
        mergeSortCount(a, 0, a.length - 1);
        return num;
    }

    private void mergeSortCount(int[] a, int lo, int hi) {
        if (lo >= hi) {
            return;
        }
        int mi = (lo + hi) / 2;
        mergeSortCount(a, lo, mi);
        mergeSortCount(a, mi + 1, hi);
        merge(a, lo, mi, hi);
    }

    private void merge(int[] a, int lo, int mi, int hi) {
        int[] tmp = new int[hi - lo + 1];
        int i = lo, j = mi + 1, k = 0;
        while (i <= mi && j <= hi) {
            if (a[i] <= a[j]) {
                tmp[k++] = a[i++];
            } else {
                num += (hi - 1 + 1);
                tmp[k++] = a[j++];
            }
        }
        while (i <= mi) {
            tmp[k++] = a[i++];
        }
        while (j <= hi) {
            tmp[k++] = a[j++];
        }
        for (i = 0; i < hi - lo; i++) {
            a[lo + i] = tmp[i];
        }
    }
}
