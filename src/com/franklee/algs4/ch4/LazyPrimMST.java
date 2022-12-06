package com.franklee.algs4.ch4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class LazyPrimMST {
    private boolean[] marked;   // MST vertices
    private Queue<Edge> mst;    // MST edges
    private MinPQ<Edge> pq;     // crossing (and ineligible) edges

    public LazyPrimMST(EdgeWeightedGraph G) {
        pq = new MinPQ<Edge>();
        mst = new Queue<Edge>();
        marked = new boolean[G.V()];

        visit(G, 0); // assumes G is connected
        while (!pq.isEmpty() && mst.size() < G.V() - 1) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);
            if (marked[v] && marked[w]) continue; // ignore ineligible edges(both endpoints in MST)
            mst.enqueue(e);
            if (!marked[v]) visit(G, v);;
            if (!marked[w]) visit(G, w);
        }
    }

    /**
     * Mark v and add to pq all edges from v to unmarked vertices.
     */
    private void visit(EdgeWeightedGraph G, int v) {
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            if (!marked[e.other(v)]) pq.insert(e);
        }
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges()) {
            weight += e.weight();
        }
        return weight;
    }
}
