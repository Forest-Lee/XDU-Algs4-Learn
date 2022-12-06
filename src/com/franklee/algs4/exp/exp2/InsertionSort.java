package com.franklee.algs4.exp.exp2;

import java.util.Scanner;

public class InsertionSort extends CommonSort {
    private InsertionSort() {}

    public static void sort(Comparable[] a) {
        int n = a.length;
        for (int i = 1; i < n; i++) {
            for (int j = i; j > 0 && less(a[j], a[j-1]) ; j--) {
                exch(a, j, j-1);
            }
        }
        assert isSorted(a);
    }

    public static void main(String[] args) {
        String[] a = new Scanner(System.in).nextLine().split(" ");
        InsertionSort.sort(a);
        show(a);
    }
}
