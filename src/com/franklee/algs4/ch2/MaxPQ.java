package com.franklee.algs4.ch2;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class MaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;   // store items at indices 1 to n
    private int N;      // number of elements on priority queue

    public MaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity+1];
        N = 0;
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(Key[] keys) {
        N = keys.length;
        pq = (Key[]) new Comparable[keys.length+1];
        for (int i = 0; i < N; i++) {
            pq[i+1] = keys[i];
        }
        // heapify pq
        for (int k = N/2; k >= 1; k--) {
            sink(k);
        }
        assert isMaxHeap();
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public int size() {
        return N;
    }

    public Key max() {
        if (isEmpty()) throw new RuntimeException("Priority queue underflow");
        return pq[1];
    }

    public void resize(int capacity) {
        assert capacity > N;
        Key[] temp = (Key[]) new Comparable[capacity];
        for (int i = 1; i <= N; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void insert(Key x) {
        if (N == pq.length-1) resize(2*pq.length);
        pq[++N] = x;        // insert at the end
        swim(N);            // restore heap property
        assert isMaxHeap();
    }

    public Key delMax() {
        if (isEmpty()) throw new RuntimeException("Priority queue underflow");
        Key max = pq[1];
        exch(1, N--);
        pq[N+1] = null;     // avoid loitering and help with garbage collection
        sink(1);            // restore heap property
        if ((N > 0) && (N == (pq.length-1)/4)) resize(pq.length/2);
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
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(j, j+1)) j++; // j is the index of the larger child
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
        for (int i = 1; i <= N; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = N+1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }

    private boolean isMaxHeapOrdered(int k) {
        if (k > N) return true;
        int left = 2*k, right = 2*k + 1;
        if (left  <= N && less(k, left))  return false;
        if (right <= N && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    public static void main(String[] args) {
        MaxPQ<String> pq = new MaxPQ<String>(   );
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMax() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }
}
