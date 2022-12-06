package com.franklee.algs4.ch2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Shell {
    private Shell() { }

    public static void sort(Comparable[] a) {
        int n = a.length;
        int h = 1;
        while (h < n / 3) h = 3 * h + 1; // 1, 4, 13, 40, 121, 364, 1093, ...
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j - h]); j -= h) { // h-sort the array
                    swap(a, j, j - h);
                }
            }
            assert isHSorted(a, h);
            h /= 3;
        }
        assert isSorted(a);
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void swap(Object[] a, int i, int j) {
        Object temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static boolean isSorted(Comparable[] a) {
        for (int i = 1; i < a.length; i++) {
            if (less(a[i], a[i-1])) return false;
        }
        return true;
    }

    private static boolean isHSorted(Comparable[] a, int h) {
        for (int i = h; i < a.length; i++) {
            if (less(a[i], a[i-h])) return false;
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
        Shell.sort(a);
        show(a);
    }
}
