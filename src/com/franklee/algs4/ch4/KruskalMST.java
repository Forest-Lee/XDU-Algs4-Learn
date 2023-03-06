package com.franklee.algs4.ch4;

import com.franklee.algs4.ch1.QuickUnionUF;
import com.franklee.algs4.ch2.MinPQ;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.Queue;

public class KruskalMST {
    private Queue<Edge> mst;    // edges in MST
    private double weight;      // weight of MST

    public KruskalMST(EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }

        QuickUnionUF uf = new QuickUnionUF(G.V());
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();               // get lowest-weight edge from pq
            int v = e.either(), w = e.other(v);
            if (uf.connected(v, w)) continue;   // skip if ineligible
            uf.union(v, w);
            mst.enqueue(e);
            weight += e.weight();
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }
}
