package com.franklee.algs4.ch2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick3way {
    private Quick3way() { }

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

    /***************************************************************************
     *  Helper sorting functions.
     ***************************************************************************/

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    /***************************************************************************
     *  Check if array is sorted - useful for debugging.
     ***************************************************************************/

    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length-1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Quick3way.sort(a);
        show(a);
    }
}
