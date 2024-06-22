package io.dev.v1.graph.representation;

/******************************************************************************
 *  Compilation:  javac Graph.java
 *  Execution:    java Graph input.txt
 *  Dependencies: Bag.java Stack.java In.java StdOut.java
 *  Data files:   https://algs4.cs.princeton.edu/41graph/tinyG.txt
 *                https://algs4.cs.princeton.edu/41graph/mediumG.txt
 *                https://algs4.cs.princeton.edu/41graph/largeG.txt
 *
 *  A graph, implemented using an array of sets.
 *  Parallel edges and self-loops allowed.
 *
 *  % java Graph tinyG.txt
 *  13 vertices, 13 edges
 *  0: 6 2 1 5
 *  1: 0
 *  2: 0
 *  3: 5 4
 *  4: 5 6 3
 *  5: 3 4 0
 *  6: 0 4
 *  7: 8
 *  8: 7
 *  9: 11 10 12
 *  10: 9
 *  11: 9 12
 *  12: 11 9
 *
 *  % java Graph mediumG.txt
 *  250 vertices, 1273 edges
 *  0: 225 222 211 209 204 202 191 176 163 160 149 114 97 80 68 59 58 49 44 24 15
 *  1: 220 203 200 194 189 164 150 130 107 72
 *  2: 141 110 108 86 79 51 42 18 14
 *  ...
 *
 ******************************************************************************/

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 *  The {@code Graph} class represents an undirected graph of vertices
 *  named 0 through <em>V</em> – 1.
 *  It supports the following two primary operations: add an edge to the graph,
 *  iterate over all of the vertices adjacent to a vertex. It also provides
 *  methods for returning the degree of a vertex, the number of vertices
 *  <em>V</em> in the graph, and the number of edges <em>E</em> in the graph.
 *  Parallel edges and self-loops are permitted.
 *  By convention, a self-loop <em>v</em>-<em>v</em> appears in the
 *  adjacency list of <em>v</em> twice and contributes two to the degree
 *  of <em>v</em>.
 *  <p>
 *  This implementation uses an <em>adjacency-lists representation</em>, which
 *  is a vertex-indexed array of {@link LinkedList} objects.
 *  It uses &Theta;(<em>E</em> + <em>V</em>) space, where <em>E</em> is
 *  the number of edges and <em>V</em> is the number of vertices.
 *  All instance methods take &Theta;(1) time. (Though, iterating over
 *  the vertices returned by {@link #adj(int)} takes time proportional
 *  to the degree of the vertex.)
 *  Constructing an empty graph with <em>V</em> vertices takes
 *  &Theta;(<em>V</em>) time; constructing a graph with <em>E</em> edges
 *  and <em>V</em> vertices takes &Theta;(<em>E</em> + <em>V</em>) time.
 *  <p>
 *  For additional documentation, see
 *  <a href="https://algs4.cs.princeton.edu/41graph">Section 4.1</a>
 *  of <i>Algorithms, 4th Edition</i> by Robert Sedgewick and Kevin Wayne.
 *
 *  @author Robert Sedgewick
 *  @author Kevin Wayne
 */
public class UndirectedGraph {

    private static final String NEWLINE = System.getProperty("line.separator");

    private final int numberOfVertices;
    private int numberOfEdges;
    private final List<Integer>[] adjacencyLists;

    public UndirectedGraph(int numberOfVertices) {
        if (numberOfVertices < 0)
            throw new IllegalArgumentException("Number of vertices must be non-negative");
        this.numberOfVertices = numberOfVertices;
        this.numberOfEdges = 0;
        this.adjacencyLists = new LinkedList[numberOfVertices]; //(List<Integer>[])
        for (int v = 0; v < numberOfVertices; v++) {
            adjacencyLists[v] = new LinkedList<>();
        }
    }

    /**
     * Adds the undirected edge v-w to this graph.
     */
    public void addEdge(int v, int w) {
        validateVertex(v); validateVertex(w);

        numberOfEdges++;

        List<Integer> listV = adjacencyLists[v];
        listV.add(w);
        List<Integer> listW = adjacencyLists[w];
        listW.add(v);
    }

    /**
     * Initializes a new graph that is a deep copy of graph.
     */
    public UndirectedGraph(UndirectedGraph graph) {
        this.numberOfVertices = graph.numberOfVertices();
        this.numberOfEdges = graph.numberOfEdges();
        if (numberOfVertices < 0) throw new IllegalArgumentException("Number of vertices must be non-negative");

        // update adjacency lists
        adjacencyLists = (LinkedList<Integer>[]) new LinkedList[numberOfVertices];
        for (int v = 0; v < numberOfVertices; v++) {
            adjacencyLists[v] = new LinkedList<>();
        }

        for (int v = 0; v < graph.numberOfVertices(); v++) {
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for (int w : graph.adjacencyLists[v]) {
                reverse.push(w);
            }
            for (int w : reverse) {
                adjacencyLists[v].add(w);
            }
        }
    }

    public int numberOfVertices() {
        return numberOfVertices;
    }

    public int numberOfEdges() {
        return numberOfEdges;
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= numberOfVertices)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (numberOfVertices -1));
    }

    /**
     *  Returns the vertices adjacent to vertex {@code v}.
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adjacencyLists[v];
    }

    /**
     * Returns the degree of vertex v.
     */
    public int degree(int v) {
        validateVertex(v);
        return adjacencyLists[v].size();
    }


    /**
     * Returns a string representation of this graph. Returns the number of vertices <em>V</em>, followed by the number of edges <em>E</em>, followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(numberOfVertices + " vertices, " + numberOfEdges + " edges " + NEWLINE);
        for (int v = 0; v < numberOfVertices; v++) {
            s.append(v + ": ");
            for (int w : adjacencyLists[v]) {
                s.append(w + " ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args) throws IOException {
        URL url = new URL("https://algs4.cs.princeton.edu/41graph/tinyG.txt");
        URLConnection site = url.openConnection();
        InputStream is     = site.getInputStream();
        Scanner in = new Scanner(is);
        UndirectedGraph G = new UndirectedGraph(in);
        System.out.println(G);
    }

    public UndirectedGraph(Scanner in) {
        try {
            if (in == null)
                throw new IllegalArgumentException("argument is null");

            this.numberOfVertices = in.nextInt(); //.readInt();
            if (numberOfVertices < 0)
                throw new IllegalArgumentException("number of vertices in a Graph must be non-negative");

            this.numberOfEdges = in.nextInt();
            if (numberOfEdges < 0)
                throw new IllegalArgumentException("number of edges in a Graph must be non-negative");

            this.adjacencyLists = (LinkedList<Integer>[]) new LinkedList[numberOfVertices];
            for (int v = 0; v < numberOfVertices; v++) {
                adjacencyLists[v] = new LinkedList<>();
            }

            for (int i = 0; i < numberOfEdges; i++) {
                int v = in.nextInt();
                int w = in.nextInt();
                validateVertex(v);
                validateVertex(w);
                addEdge(v, w);
            }
        }catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Graph constructor", e);
        }
    }

}
