package com.franklee.algs4.exp.exp2;

import java.util.Scanner;

public class TopDownMergeSort extends CommonSort {
    //private static Comparable[] aux;

    private TopDownMergeSort() { }

    /**
     * Merge a[lo..mid] with a[mid+1..hi] using aux[lo..hi].
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(a, lo, mid);
        assert isSorted(a, mid+1, hi);

        int i = lo, j = mid+1;

        // copy to aux[]
        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        // merge back to a[]
        for (int k = lo; k <= hi; k++) {
            if (i > mid)                    a[k] = aux[j++];
            else if (j > hi)                a[k] = aux[i++];
            else if (less(aux[j], aux[i]))  a[k] = aux[j++];
            else                            a[k] = aux[i++];
        }

        assert isSorted(a, lo, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo) return;
        int mid = lo + (hi - lo)/2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void main(String[] args) {
        String[] a = new Scanner(System.in).nextLine().split(" ");
        TopDownMergeSort.sort(a);
        show(a);
    }
}
