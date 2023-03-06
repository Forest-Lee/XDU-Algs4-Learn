package com.franklee.algs4.ch4;

import com.franklee.algs4.ch2.IndexMinPQ;
import edu.princeton.cs.algs4.Edge;
import edu.princeton.cs.algs4.Queue;

public class PrimMST {
    private Edge[] edgeTo;          // shortest edge from tree vertex
    private double[] distTo;        // distTo[w] = edgeTo[w].weight()
    private boolean[] marked;       // marked[v] = true iff v on tree
    private IndexMinPQ<Double> pq;  // eligible crossing edges
    private double weight;          // weight of MST

    public PrimMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        marked = new boolean[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[0] = 0.0;
        pq.insert(0, 0.0);  // assume G is connected
        while (!pq.isEmpty()) {
            visit(G, pq.delMin());
        }
    }

    private void visit(EdgeWeightedGraph G, int v) {
        // add v to tree, update data structures
        marked[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (marked[w]) continue;    // skip if ineligible
            if (e.weight() < distTo[w]) {
                weight += e.weight();
                edgeTo[w] = e;
                distTo[w] = e.weight();
                if (pq.contains(w)) pq.change(w, distTo[w]);
                else                pq.insert(w, distTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<Edge>();
        for (int v = 1; v < edgeTo.length; v++) {   // tip: edgeTo[0] == null
            mst.enqueue(edgeTo[v]);
        }
        return mst;
    }

    public double weight() {
        return weight;
    }
}
