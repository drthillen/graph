package package1;

import java.util.*;

/* Simulating a Graph for simplicity not efficiency, the clever usage of LinkedList/AdjacencyList could substantially increase performance */

public class DirectedGraph {

    Vertex[] vertices;
    Set<Edge> edges = new HashSet<>();
    int N = 20;

    DirectedGraph(int N) {
        this.N = N;
        vertices = new Vertex[N];
    }

    public void addEdge(int from, int to, int weight) throws IndexOutOfBoundsException {
        // Check bounds
        if (from >= vertices.length || from < 0) {
            throw new IndexOutOfBoundsException("" + from + " is out of bounds for vertices of size: " + N);
        } else if (to >= vertices.length || to < 0) {
            throw new IndexOutOfBoundsException("" + to + " is out of bounds for vertices of size: " + N);
        } else if (vertices[from] == null) {
            throw new IllegalArgumentException("Add Edge From a null pointer is impossible");
        } else if (vertices[to] == null) {
            throw new IllegalArgumentException("Add Edge To a null pointer is impossible");
        }
        //
        Edge e = new Edge(from, to, weight);
        this.vertices[from].addOutEdge(e);
        this.vertices[to].addIncEdge(e);
        this.edges.add(e);
    }

    public void addEdge(int from, int to) throws IndexOutOfBoundsException {
        addEdge(from, to, 1);
    }

    public void removeEdge(int from, int to) {
        if (from >= N || from < 0) {
            throw new IndexOutOfBoundsException("" + from + " is out of bounds for vertices of size: " + N);
        } else if (to >= N || to < 0) {
            throw new IndexOutOfBoundsException("" + to + " is out of bounds for vertices of size: " + N);
        } else if (vertices[from] == null) {
            throw new IllegalArgumentException("Remove Edge From a null pointer is impossible");
        } else if (vertices[to] == null) {
            throw new IllegalArgumentException("Remove Edge To a null pointer is impossible");
        }
        Vertex fromVertex = vertices[from];
        Vertex toVertex = vertices[to];
        for (Edge e : fromVertex.outgoingEdges) {
            if (e.to == to) {
                this.edges.remove(e);
                toVertex.removeIncEdge(e);
                fromVertex.removeOutEdge(e);
            }
        }
    }

    public void removeEdge(Edge e) {
        e.delete(this);
    }

    public static void main(String[] args) throws Exception {
        System.out.println("Graph main function");
    }

    public String toString() {
        String s = "";
        s += "Print Graph";

        return "";
    }

}

class Vertex {
    int id;
    List<Edge> incomingEdges = new LinkedList<>();
    List<Edge> outgoingEdges = new LinkedList<>();

    Vertex(int id) {
        this.id = id;
    }

    void addIncEdge(Edge e) {
        incomingEdges.add(e);
    }

    void addOutEdge(Edge e) {
        outcomingEdges.add(e);
    }

    void removeOutEdge(Edge e) throws Exception {
        int index = outgoingEdges.indexOf(e);
        if (index < 0) {
            throw new NotFoundException("Could not remove edge, since not found.");
        } else {
            outgoingEdges.remove(index);
        }
    }

    void removeIncEdge(Edge e) throws Exception {
        int index = incomingEdges.indexOf(e);
        if (index < 0) {
            throw new NotFoundException("Could not remove edge, since not found.");
        } else {
            incomingEdges.remove(index);
        }
    }

    public String toString() {
        return "V: " + id;
    }
}

class Edge {
    int from;
    int to;
    int weight;

    Edge(int from, int to) {
        Edge(from, to, 1);
    }

    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    void delete(DirectedGraph G) {
        Vertex fromVertex = G.vertices[this.from];
        Vertex toVertex = G.vertices[this.to];
        G.edges.remove(e);
        toVertex.removeIncEdge(e);
        fromVertex.removeOutEdge(e);
    }

    @Override
    public String toString() {
        return "E: " + this.from + " -> " + this.to;
    }
}

class NotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    NotFoundException(String s) {
        super(s);
    }
}