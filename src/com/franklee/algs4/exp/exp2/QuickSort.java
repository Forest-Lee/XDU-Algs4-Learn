package com.franklee.algs4.exp.exp2;

import edu.princeton.cs.algs4.StdRandom;
import java.util.Scanner;

public class QuickSort extends CommonSort {
    private QuickSort() { }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1);
        assert isSorted(a);
    }

    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
        assert isSorted(a, lo, hi);
    }

    /**
     * Partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi].
     * @return the index j
     */
    private static int partition(Comparable[] a, int lo, int hi) {
        int i = lo, j = hi + 1;
        Comparable v = a[lo];
        while (true) {
            while (less(a[++i], v)) { // find an item >= v on the left to swap
                if (i == hi) break;
            }
            while (less(v, a[--j])) { // find an item <= v on the right to swap
                if (j == lo) break; // redundant
            }
            if (i >= j) break; // check if pointers cross
            exch(a, i, j);
        }
        exch(a, lo, j); // now, a[lo..j-1] <= a[j] <= a[j+1..hi]
        return j;
    }

    /**
     * Rearranges the array so that a[k] contains the kth smallest key.
     * @return the key of rank k
     */
    private static Comparable select(Comparable[] a, int k) {
        if (k < 0 | k >= a.length) {
            throw new IllegalArgumentException("index is not between 0 and " + a.length + ": " + k);
        }
        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;
        while (hi > lo) {
            int j = partition(a, lo, hi);
            if      (j < k) lo = j + 1;
            else if (j > k) hi = j - 1;
            else    return a[k];
        }
        return a[k];
    }

    public static void main(String[] args) {
        String[] a = new Scanner(System.in).nextLine().split(" ");
        QuickSort.sort(a);
        show(a);

        StdRandom.shuffle(a);
        System.out.println();
        for (int i = 0; i < a.length; i++) { // display results again using select
            String ith = (String) QuickSort.select(a, i);
            System.out.print(ith + " ");
        }
    }
}
