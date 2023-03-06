package com.franklee.algs4.ch5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MSD {
    private MSD() {
    }

    public static void sort(String[] a) {
        int N = a.length;
        String[] aux = new String[N];
        sort(a, 0, N - 1, 0, aux);
    }

    private static int charAt(String s, int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }

    private static void sort(String[] a, int lo, int hi, int d, String[] aux) {
        if (hi <= lo) return;
        int R = 256;
        int[] count = new int[R+2];

        // compute frequency counts
        for (int i = lo; i <= hi; i++) {
            count[charAt(a[i], d)+2]++;
        }

        // compute cumulates
        for (int r = 0; r < R + 1; r++) {
            count[r+1] += count[r];
        }

        // for most significant char, 0th position is end-of-string
        if (d == a[lo].length()) {
            count[0] = count[R+1];
            count[R+1] = 0;
        }

        // move data
        for (int i = lo; i <= hi; i++) {
            aux[count[charAt(a[i], d)+1]++] = a[i];
        }

        // copy back
        for (int i = lo; i <= hi; i++) {
            a[i] = aux[i-lo];
        }

        // recursively sort for each character (excludes sentinel -1)
        for (int r = 0; r < R; r++) {
            sort(a, lo+count[r], lo+count[r+1]-1, d+1, aux);
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int N = a.length;

        sort(a);

        for (int i = 0; i < N; i++) {
            StdOut.println(a[i]);
        }
    }
}
