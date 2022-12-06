package com.franklee.algs4.ch1;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class Ex1_4_08 {
    public static void main(String[] args) {
        int[] arr = new In(args[0]).readAllInts();
        StdOut.println(countNumOfEqualPairs(arr));
    }

    public static int countNumOfEqualPairs(int[] arr) {
        Arrays.sort(arr);
        int count = 0;
        int currentFrequency = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) {
                currentFrequency++;
            } else {
                if (currentFrequency > 1) {
                    count += (currentFrequency - 1) * currentFrequency / 2;
                    currentFrequency = 1;
                }
            }
        }

        if (currentFrequency > 1) {
            count += (currentFrequency - 1) * currentFrequency / 2;
        }
        return count;
    }
}
