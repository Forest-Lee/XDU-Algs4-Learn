package com.franklee.algs4.ch2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Heap {
    private Heap() { }

    public static void sort(Comparable[] pq) {
        int n = pq.length;

        // heapify phase
        for (int k = n/2; k >= 1; k--) {
            sink(pq, k, n);
        }

        // sortdown phase
        int k = n;
        while (k > 1) {
            exch(pq, 1, k--);
            sink(pq, 1, k);
        }
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private static void sink(Comparable[] a, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(a, j, j+1)) j++; // j is the index of the larger child
            if (!less(a, k, j)) break;
            exch(a, k, j);
            k = j;
        }
    }

    private static boolean less(Comparable[]a, int i, int j) {
        return a[i-1].compareTo(a[j-1]) < 0;
    }

    /***************************************************************************
     * Helper functions for comparisons and swaps.
     ***************************************************************************/

    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i-1];
        a[i-1] = a[j-1];
        a[j-1] = swap;
    }
    
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.print(a[i] + " ");
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        Heap.sort(a);
        show(a);
    }
}
