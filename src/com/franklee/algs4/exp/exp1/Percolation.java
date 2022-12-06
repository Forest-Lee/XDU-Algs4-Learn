package com.franklee.algs4.exp.exp1;

public class Percolation {
    private final UF grid;              // N-by-N grid (include virtual top and bottom)
    private final boolean[] status;     // status[i] = open status of site i
    private final int N;                // size of grid
    private int count;                  // number of open sites

    public Percolation(int N, int choice) {
        if (N <= 0) {
            throw new IllegalArgumentException("N must be greater than 0!");
        }
        this.N = N;
        if (choice == 1) {
            this.grid = new WeightedQuickUnionUF(N * N + 2);
        } else {
            this.grid = new QuickFindUF(N * N + 2);
        }
        status = new boolean[N * N + 2];
        status[0] = status[N * N + 1] = true;   // virtual top and bottom are open
    }

    public int count() {
        return count;
    }

    // validate that (i, j) is a valid index
    public void validate(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N) {
            throw new IndexOutOfBoundsException("i and j should be between 1 and N!");
        }
    }

    // get the index of site (i, j)
    private int linearIndex(int row, int col) {
        validate(row, col);
        return (row - 1) * N + col;
    }

    // open site (row, col) and connect it to its open neighbors
    public void open(int row, int col) {
        validate(row, col);
        int index = linearIndex(row, col);
        status[index] = true;
        count++;

        if (row == 1) {
            grid.union(0, index);
        }
        if (row == N) {
            grid.union(index, N * N + 1);
        }
        neighborConnect(row, col, row - 1, col);    // up
        neighborConnect(row, col, row + 1, col);    // down
        neighborConnect(row, col, row, col - 1);    // left
        neighborConnect(row, col, row, col + 1);    // right
    }

    // connect neighbor if it is open
    private void neighborConnect(int rowA, int colA, int rowB, int colB) {
        if (0 < rowB && rowB <= N && 0 < colB && colB <= N && isOpen(rowB, colB)) {
            grid.union(linearIndex(rowA, colA), linearIndex(rowB, colB));
        }
    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return status[linearIndex(row, col)];
    }

    // if site (row, col) is connected to virtual top?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return grid.connected(0, linearIndex(row, col));
    }

    // the system percolates if virtual top is connected to virtual bottom
    public boolean percolates() {
        return grid.connected(0, N * N + 1);
    }
}
