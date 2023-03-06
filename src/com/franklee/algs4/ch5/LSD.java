package com.franklee.algs4.ch5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class LSD {
    private LSD() {}

    public static void sort(String[] a, int W) {
        int N = a.length;
        int R = 256;
        String[] aux = new String[N];

        // sort by key-indexed counting on dth character
        for (int d = W-1; d >= 0; d--) {
            // compute frequency counts
            int[] count = new int[R+1];
            for (int i = 0; i < N; i++) {
                count[a[i].charAt(d)+1]++;
            }

            // compute cumulates
            for (int r = 0; r < R; r++) {
                count[r+1] += count[r];
            }

            // move data
            for (int i = 0; i < N; i++) {
                aux[count[a[i].charAt(d)]++] = a[i];
            }

            // copy back
            for (int i = 0; i < N; i++) {
                a[i] = aux[i];
            }
        }
    }

    public static void main(String[] args) {
        String[] a = StdIn.readAllStrings();
        int N = a.length;

        int w = a[0].length();
        for (int i = 0; i < N; i++) {
            assert a[i].length() == w : "Strings must have fixed length";
        }

        sort(a, w);

        for (int i = 0; i < N; i++) {
            StdOut.println(a[i]);
        }
    }
}
