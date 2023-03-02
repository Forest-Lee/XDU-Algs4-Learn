package com.franklee.algs4.ch1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Stopwatch;

public class ThreeSum {
    public static int count(int[] a) {
        int n = a.length;
        int count = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                for (int k = j + 1; k < n; k++)
                    // O(n^3)
                    if((long) a[i] + a[j] + a[k] == 0)
                        count++;
        return count;
    }
    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        Stopwatch timer = new Stopwatch();
        int count = count(a);
        System.out.println("elapsed time = " + timer.elapsedTime() + "s");
        System.out.println(count);
    }
}
