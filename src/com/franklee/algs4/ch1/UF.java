package com.franklee.algs4.ch1;

public interface UF {
    void union(int p, int q);
    int count();
    int find(int p);
    boolean connected(int p, int q);
}
