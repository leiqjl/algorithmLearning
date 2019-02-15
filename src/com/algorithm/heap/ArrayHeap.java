package com.algorithm.heap;

public class ArrayHeap {
    private int[] a;
    private int n;
    private int size;

    public ArrayHeap(int capacity) {
        a = new int[capacity + 1];
        n = capacity;
        size = 0;
    }

    public void insert(int data) {
        if (size >= n) {
            return;
        }
        size++;
        a[size] = data;
        int i = size;
        while (i / 2 > 0 && a[i] > a[i / 2]) {
            swap(a, i, i / 2);
            i = i / 2;
        }
    }

    private void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public void removeMax() {
        if (size == 0) {
            return;
        }
        a[1] = a[size];
        size--;
        heapif(a, size, 1);
    }

    private void heapif(int[] a, int n, int i) {
        while (true) {
            int tmp = i;
            if (i * 2 <= n && a[i] < a[i * 2]) {
                tmp = i * 2;
            }
            if ((i * 2 + 1) <= n && a[i] < a[i * 2 + 1]) {
                tmp = i * 2 + 1;
            }
            if (tmp == i) {
                break;
            }
            swap(a, i, tmp);
            i = tmp;
        }
    }

    public void sort(int[] a, int n) {
        buildHeap(a, n);
        int k = n;
        while (k > 1) {
            swap(a, 1, k);
            --k;
            heapif(a, k, 1);
        }

    }

    private void buildHeap(int[] a, int n) {
        for (int i = n / 2; i >= 1; i--) {
            heapif(a, n, i);
        }
    }
}
