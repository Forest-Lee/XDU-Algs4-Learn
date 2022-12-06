package com.franklee.algs4.exp.exp2;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Scanner;

public class Quick3waySort extends CommonSort {
    private Quick3waySort() { }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
        assert isSorted(a);
    }

    /**
     * Quicksort the subarray a[lo..hi] using 3-way partitioning.
     */
    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];
        int i = lo + 1;
        while (i <= gt) {
            int cmp = a[i].compareTo(v);
            if (cmp < 0)      exch(a, lt++, i++);
            else if (cmp > 0) exch(a, i, gt--);
            else              i++;
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]
        sort(a, lo, lt-1);
        sort(a, gt+1, hi);
        assert isSorted(a, lo, hi);
    }

    public static void main(String[] args) {
        String[] a = new Scanner(System.in).nextLine().split(" ");
        Quick3waySort.sort(a);
        show(a);
    }
}
