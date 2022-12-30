package com.franklee.algs4.exp.exp3;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.IndexMultiwayMinPQ;

import java.util.Stack;

public class DijkstraSP {
    private double[] distTo;        // distTo[v] = distance  of shortest s->v path
    private DirectedEdge[] edgeTo;  // edgeTo[v] = last edge on shortest s->v path
//    private IndexMinPQ<Double> pq;
    private IndexMultiwayMinPQ<Double> pq;  // optimization3: use IndexMultiwayMinPQ
    private Point[] points;

    public DijkstraSP(EdgeWeightedDigraph G, Point[] points, int from, int to) {
        distTo = new double[G.V()];
        edgeTo = new DirectedEdge[G.V()];
        this.points = points;

        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[from] = 0.0;

//        pq = new IndexMinPQ<Double>(G.V());
        pq = new IndexMultiwayMinPQ<>(G.V(), 8);  // optimization3: use IndexMultiwayMinPQ
        pq.insert(from, distTo[from]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            if (v == to) return;    // optimization1: stop when reach the destination
            for (DirectedEdge e : G.adj(v))
                relax(e, to);
        }
    }

    private void relax(DirectedEdge e, int to) {
        int v = e.from(), w = e.to();
        double weight = distTo[v] + e.weight() + points[w].distanceTo(points[to]) - points[v].distanceTo(points[to]);   // optimization2: A* algorithm
//        double weight = distTo[v] + e.weight();
        if (distTo[w] > weight) {
            distTo[w] = weight;
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }

    public double distTo(int v) {
        return distTo[v];
    }

    public boolean hasPathTo(int v) {
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Stack<DirectedEdge> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<DirectedEdge> path = new Stack<DirectedEdge>();
        for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

}
