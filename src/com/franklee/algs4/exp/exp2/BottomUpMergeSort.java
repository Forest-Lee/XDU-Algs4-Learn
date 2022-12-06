package com.franklee.algs4.exp.exp2;

import java.util.Scanner;

public class BottomUpMergeSort extends CommonSort {
    //private static Comparable[] aux;

    private BottomUpMergeSort() { }

    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        int i = lo, j = mid+1;

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        for (int k = lo; k <= hi; k++) {
            if (i > mid)                    a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }
    }

    public static void sort(Comparable[] a) {
        int n = a.length;
        Comparable[] aux = new Comparable[a.length];
        for (int len = 1; len < n; len *= 2) {
            for (int lo = 0; lo < n-len; lo += len+len) {
                int mid = lo+len-1;
                int hi = Math.min(lo+len+len-1, n-1);
                merge(a, aux, lo, mid, hi);
            }
        }
        assert isSorted(a);
    }

    public static void main(String[] args) {
        String[] a = new Scanner(System.in).nextLine().split(" ");
        BottomUpMergeSort.sort(a);
        show(a);
    }
}
