package com.franklee.algs4.exp.exp2;

public abstract class CommonSort {

    protected static boolean less(Comparable v, Comparable w) {
        return  v.compareTo(w) < 0;
    }

    protected static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    protected static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length);
    }

    protected static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo+1; i < hi; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    protected static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}
