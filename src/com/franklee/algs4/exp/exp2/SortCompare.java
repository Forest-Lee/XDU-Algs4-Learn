package com.franklee.algs4.exp.exp2;

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class SortCompare {
    private static final int[] SIZES = {100, 1000, 5000, 10000, 50000, 100000};
    private static LinkedHashMap<String, ArrayList<Long>> ansTimes = new LinkedHashMap<>();

    public static Integer[] randomArray(int size) {
        Integer[] a = new Integer[size];
        for (int i = 0; i < size; i++) {
            a[i] = StdRandom.uniform(size);
        }
        StdRandom.shuffle(a);
        return a;
    }

    public static void test(Comparable[] arr) {
        // test five sort methods
        for (int i = 0; i < 5; i++) {
            ArrayList<Long> runTimes = new ArrayList<>();
            long runTime;
            // test each sort method 10 times
            for (int j = 0; j < 10; j++) {
                Comparable[] aux = arr.clone();
                long startTime = System.currentTimeMillis();
                switch (i) {
                    case 0:
                        InsertionSort.sort(aux);
                        runTime = System.currentTimeMillis() - startTime;
                        runTimes.add(runTime);
                        if (j == 9) ansTimes.put("IS", runTimes);    // record when finish 10 times tests
                        break;
                    case 1:
                        TopDownMergeSort.sort(aux);
                        runTime = System.currentTimeMillis() - startTime;
                        runTimes.add(runTime);
                        if (j == 9) ansTimes.put("TDM", runTimes);
                        break;
                    case 2:
                        BottomUpMergeSort.sort(aux);
                        runTime = System.currentTimeMillis() - startTime;
                        runTimes.add(runTime);
                        if (j == 9) ansTimes.put("BUM", runTimes);
                        break;
                    case 3:
                        QuickSort.sort(aux);
                        runTime = System.currentTimeMillis() - startTime;
                        runTimes.add(runTime);
                        if (j == 9) ansTimes.put("RQ", runTimes);
                        break;
                    case 4:
                        Quick3waySort.sort(aux);
                        runTime = System.currentTimeMillis() - startTime;
                        runTimes.add(runTime);
                        if (j == 9) ansTimes.put("QD3P", runTimes);
                        break;
                }
            }
        }
    }

    public static void show() {
        for (String key : ansTimes.keySet()) {
            int sum = 0;
            System.out.printf("%4s", key);
            for (Long time : ansTimes.get(key)) {
                System.out.printf("%6d", time);
                sum += time;
            }
            System.out.printf("\t|\t %.2f", sum / 10.0);
            System.out.println();
        }
        System.out.println("--------------------------------------------------------------------------------\n");

    }

    public static void main(String[] args) {
        for (int size : SIZES) {
            Integer[] arr = randomArray(size);
            System.out.printf("========== array size: %d ==========\n", size);
            System.out.println("Running Time Results    (ms)");
            System.out.println("--------------------------------------------------------------------------------");
//            Arrays.sort(arr);
            test(arr);
            show();
        }
    }
}
