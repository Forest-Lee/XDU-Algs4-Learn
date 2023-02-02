package com.franklee.algs4.exp.exp3;

import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("data/usa.txt"));
        int pointNum = in.nextInt();
        int edgeNum = in.nextInt();
        Point[] points = new Point[pointNum];
        for (int i = 0; i < pointNum; i++) {
            int idx = in.nextInt();
            points[idx] = new Point(in.nextDouble(), in.nextDouble());
        }
        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(pointNum);
        for (int i = 0; i < edgeNum; i++) {
            int v = in.nextInt();
            int w = in.nextInt();
            double weight = points[v].distanceTo(points[w]);
            DirectedEdge edge = new DirectedEdge(v, w, weight);
            graph.addEdge(edge);
            edge = new DirectedEdge(w, v, weight);
            graph.addEdge(edge);
        }

        in = new Scanner(System.in);
        System.out.println("----------------------------------------");
        System.out.println("please input the start and end point:");
        int from = in.nextInt();
        int to = in.nextInt();

        long startTime = System.currentTimeMillis();
        DijkstraSP sp = new DijkstraSP(graph, points, from, to);
        long endTime = System.currentTimeMillis();
        if (sp.hasPathTo(to)) {
            Stack<DirectedEdge> st = sp.pathTo(to);
            double distance = 0.0;
            System.out.print("path: ");
            int size = st.size();
            while (st.size() > 0) {
                DirectedEdge edge = st.pop();
                distance += points[edge.from()].distanceTo(points[edge.to()]);
                if (st.size() == size - 1) System.out.print(edge.from());
                System.out.print("->" + edge.to());
            }
            System.out.println("\ndistance: " + distance);
        } else {
            System.out.println("no path");
        }
        System.out.print("running time: " + (endTime - startTime) + "ms ");
        System.out.println("\n----------------------------------------");
    }
}
