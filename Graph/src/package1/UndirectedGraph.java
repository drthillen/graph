package package1;

import java.util.*;

public class UndirectedGraph {

    LinkedList<Integer>[] vertices;
    int V;

    UndirectedGraph(int N) {
        System.out.println("Graph with size: " + N + " initialized.");
        this.V = N;
        this.vertices = new LinkedList[V];
        initializeVertices();
    }

    void initializeVertices() {
        for (int i = 0; i < V; i++) {
            this.vertices[i] = new LinkedList<Integer>();
        }
    }

    void addEdge(int u, int v) throws Exception {
        if (u < 0 || u >= V || v < 0 || v >= V) {
            throw new Exception("Index out of Bounds!");
        } else {
            this.vertices[u].add(v);
            this.vertices[v].add(u);
        }
    }

    void removeEdge(int u, int v) {
        Iterator<Integer> iter1 = this.vertices[u].iterator();
        while (iter1.hasNext()) {
            Integer i = iter1.next();
            if (i == v) {
                iter1.remove();
            }
        }
        Iterator<Integer> iter2 = this.vertices[v].iterator();
        while (iter2.hasNext()) {
            Integer i = iter2.next();
            if (i == u) {
                iter2.remove();
            }
        }
    }

    int[][] DFS2() {
        /* This DFS function allows us to find AKs, bridges and find low numbers! */
        /*
         * TODO: Further improvement: leave out visited and use dfs == 0 AKs and bridges
         * only exist in connected graphs --> shorten DFS function and make algorithm
         * more correct.
         */
        boolean[] visited = new boolean[this.V];
        int[] dfs = new int[this.V];
        int[] low = new int[this.V];
        int[] isArtVert = new int[this.V];

        int[] pred = new int[this.V];
        int order = 0;
        int s = 0;

        for (int v = s; v < this.V; v++) {
            if (!visited[v]) {
                pred[v] = -1;
                order = DFSVisit2(v, order, dfs, low, pred, visited, isArtVert);
            }
        }
        int[][] r = new int[][] { dfs, low, isArtVert };
        return r;
    }

    private int DFSVisit2(int v2, int order, int[] dfs, int[] low, int[] pred, boolean[] visited, int[] isArtVert) {
        // NEEDS to be fixed !! error if there is a cycle in which the starting point is
        // contained: input2.txt ....--> ex 2 contains fix.
        visited[v2] = true;
        int lowI = order;
        dfs[v2] = order;
        order++;
        for (int u : this.vertices[v2]) {
            if (order > 1 && v2 == 0) {
                // First Condition for Articulation Node
                isArtVert[v2] = 1;
            }
            if (!visited[u]) { // edge {v2, u} is in DFS Tree!
                // Neighbour in tree
                pred[u] = v2;
                order = DFSVisit2(u, order, dfs, low, pred, visited, isArtVert);
                lowI = Math.min(lowI, low[u]); // low of neighbour connected by normal edge.
                // System.out.println("Low of normaledge is: " + low[u] + " from V" + v2 + " ->"
                // + u);
                if (dfs[v2] <= low[u]) {
                    isArtVert[v2] = 1;
                    System.out.println("" + v2 + " is a artVert!");
                    if (dfs[v2] != low[u]) {
                        System.out.println("Edge : " + v2 + " -> " + u + " is a bridge!");
                    }
                }
            } else if (pred[v2] != u) {
                // back edge
                lowI = Math.min(lowI, dfs[u]);
                // System.out.println("DFS of backedge is: " + dfs[u] + " from V" + v2 + " ->" +
                // u);
            }
        }
        low[v2] = lowI;
        return order;
    }

    boolean containsCycleDFS() {
        // Detect cycle
        /*
         * To detect a cycle one must follow a DFS path. If we encounter a visited node
         * that is not the predecessor, there must be a cycle.
         */

        boolean[] visited = new boolean[this.V];
        int[] pred = new int[this.V];
        //
        for (int u = 0; u < this.V; u++) {
            if (!visited[u]) {
                pred[u] = -1;
                if (containsCycleDFSUtil(u, pred, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean containsCycleDFSUtil(int v, int[] pred, boolean[] visited) {
        /*
         * returns true, if this node is in a cycle or this node leads to a cycle. Which
         * doesn't mean that it has to be contained in a cycle...
         */
        visited[v] = true;
        for (int w : this.vertices[v]) {
            if (visited[w] && pred[v] != w) {
                return true;
            } else if (!visited[w]) {
                pred[w] = v;
                if (containsCycleDFSUtil(w, pred, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    public int[] BFS(int startingVertex) {

        /*
         * Breadth first Search: returns an integer array which contains a number w/ the
         * distance to the starting vertex. WE print out if the graph is not a
         * component.
         */
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startingVertex);
        boolean[] visited = new boolean[this.V];
        //

        int[] distances = new int[this.V];
        distances[startingVertex] = 0;
        while (queue.size() > 0) {
            int currBFS = queue.poll();
            for (int v : this.vertices[currBFS]) {
                if (!visited[v]) {
                    queue.add(v);
                    distances[v] = distances[currBFS] + 1;
                    visited[v] = true;
                }
            }
        }
        if (hasUnvisited(visited)) {
            System.err.println("More than one component!");
        }

        return distances;
    }

    boolean hasUnvisited(boolean[] visited) {
        if (visited.length > 10_000) {
            return hasUnvisitedParallel(visited);
        } else {
            for (boolean b : visited) {
                if (!b) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean hasUnvisitedParallel(boolean[] visited) {
        // implement using threads...
        for (boolean b : visited) {
            if (!b) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String s = "Graph: \n";
        int i = 0;
        for (LinkedList<Integer> li : this.vertices) {
            s += "V" + i++ + ": " + li + "\n";
        }
        return s;
    }

    public static void main(String[] args) {
        //
    }

}