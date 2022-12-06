package com.franklee.algs4.exp.exp1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.io.*;

public class PercolationStats {
    private final int N;
    private final int T;
    private final double[] ratios;
    private final double[] runTimes;
    private final double CONFIDENCE_95 = 1.96;

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("N and T must be greater than 0!");
        }
        this.N = N;
        this.T = T;
        ratios = new double[T];
        runTimes = new double[T];
    }

    public void test(int choice) {
        // Monte Carlo simulation
        for (int i = 0; i < T; i++) {
            long startTime = System.currentTimeMillis();
            Percolation percolation = new Percolation(N, choice);
            int openSites = 0;
            while (!percolation.percolates()) {
                int row = StdRandom.uniform(N) + 1;
                int col = StdRandom.uniform(N) + 1;
                if (!percolation.isOpen(row, col)) {
                    percolation.open(row, col);
                    openSites++;
                }
            }
            ratios[i] = (double) openSites / (N * N);
            long endTime = System.currentTimeMillis();
            runTimes[i] = (double) (endTime - startTime);
        }
        show(choice);
    }

    public double mean() {
        return StdStats.mean(ratios);
    }

    public double stddev() {
        return StdStats.stddev(ratios);
    }

    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(ratios.length);
    }

    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(ratios.length);
    }

    public void show(int choice) {
        double avgRunTime = StdStats.mean(runTimes);
        double ansMean = mean();
        double ansStddev = stddev();
        double ansConfidenceLo = confidenceLo();
        double ansConfidenceHi = confidenceHi();
        try{
            PrintStream ps = new PrintStream(new FileOutputStream("ans/ans1.txt", true));
            System.setOut(new PrintStream(ps));
            if (choice == 1) {
                System.out.println("Algorithm currently in use: WeightedQuickUnionUF");
            } else {
                System.out.println("Algorithm currently in use: QuickFindUF");
            }
            System.out.println("--------------------------------------------------");
            System.out.printf("mean                    = %.16f\n", ansMean);
            System.out.printf("stddev                  = %.16f\n", ansStddev);
            System.out.printf("confidenceLo            = %.16f\n", ansConfidenceLo);
            System.out.printf("confidenceHi            = %.16f\n", ansConfidenceHi);
            System.out.printf("average running time    = %.4fms\n", avgRunTime);
            System.out.println("--------------------------------------------------");
            System.out.println();
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            if (args.length != 2) {
                throw new IllegalArgumentException("Please input two arguments: N T!");
            }
            int N = Integer.parseInt(args[0]);
            int T = Integer.parseInt(args[1]);
            try {
                PrintStream ps = new PrintStream(new FileOutputStream("ans/ans1.txt"));
                System.setOut(new PrintStream(ps));
                System.out.printf("********** N = %d, T = %d **********\n", N, T);
                System.out.println("--------------------------------------------------");
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
            }
            PercolationStats percolationStats = new PercolationStats(N, T);
            percolationStats.test(0);   // QuickFindUF
            percolationStats.test(1);   // WeightedQuickUnionUF
        } catch (NumberFormatException e) {
            System.out.println("Please input two integers!");
        } catch (IllegalArgumentException e) {
            // TODO Auto-generated catch block
            System.out.println(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        }
    }
}
