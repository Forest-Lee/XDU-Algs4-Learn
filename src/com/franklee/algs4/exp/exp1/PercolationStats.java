package com.franklee.algs4.exp.exp1;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

import java.io.*;

public class PercolationStats {
    private final int N;                // size of grid
    private final int T;                // number of experiments
    private final double[] ratios;      // ratios[i] = ratio of open sites in i-th experiment
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

    // a helper output stream data type to write data to both the console and a file
    private static class MultiOutputStream extends OutputStream {
        private OutputStream output1;
        private OutputStream output2;

        public MultiOutputStream(OutputStream output1, OutputStream output2) {
            this.output1 = output1;
            this.output2 = output2;
        }

        @Override
        public void write(int b) throws IOException {
            output1.write(b);
            output2.write(b);
        }
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
            MultiOutputStream mos = new MultiOutputStream(System.out, ps);
            System.setOut(new PrintStream(mos));
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
            mos.close();
            ps.close(); // close stream (if not, writing to file will repeat twice the second time the method is called)
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
                PrintStream ps = new PrintStream(new FileOutputStream("ans/ans1.txt", true));
                MultiOutputStream mos = new MultiOutputStream(System.out, ps);
                System.setOut(new PrintStream(mos));
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
