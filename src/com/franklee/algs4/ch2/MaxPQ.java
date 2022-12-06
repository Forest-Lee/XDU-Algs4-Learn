package com.franklee.algs4.ch2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // store items at indices 1 to n
    private int n = 0;  // number of elements on priority queue

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity+1];
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(Key[] keys) {
        n = keys.length;
        pq = (Key[]) new Comparable[keys.length+1];
        for (int i = 0; i < n; i++) {
            pq[i+1] = keys[i];
        }
        for (int k = n/2; k >= 1; k--) {
            sink(k);
        }
        assert isMaxHeap();
    }

    /***************************************************************************
     *  PQ operations.
     ***************************************************************************/

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key max() {
        if (isEmpty()) throw new RuntimeException("Priority queue underflow");
        return pq[1];
    }

    public void resize(int capacity) {
        assert capacity > n;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void insert(Key x) {
        if (n == pq.length-1) { // double size of array if necessary
            resize(2*pq.length);
        }
        pq[++n] = x;
        swim(n);
    }

    public Key delMax() {
        Key max = pq[1]; // Retrieve max key from top.
        exch(1, n--); // Exchange with last item.
        pq[n+1] = null; // Avoid loitering.
        sink(1);
        if ((n > 0) && (n == (pq.length-1)/4)) { // shrink size of array if necessary
            resize(pq.length/2);
        }
        assert isMaxHeap();
        return max;
    }

    /***************************************************************************
     * Helper functions to restore the heap invariant.
     ***************************************************************************/

    private void swim(int k) {
        while (k > 1 && less(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(j, j+1)) j++; // j is the index of the larger child
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    /***************************************************************************
     * Helper functions for compares and swaps.
     ***************************************************************************/

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    /***************************************************************************
     *  Check if array is a max heap.
     ***************************************************************************/

    private boolean isMaxHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n+1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }

    private boolean isMaxHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2*k, right = 2*k + 1;
        if (left  <= n && less(k, left))  return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.n + " left on pq)");
    }
}
