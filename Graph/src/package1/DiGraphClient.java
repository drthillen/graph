package package1;

import java.util.*;

/* Simulating a Graph for simplicity not efficiency, the clever usage of LinkedList/AdjacencyList could substantially increase performance */

public class DiGraphClient {

    public static void main(String[] args) {
        /*
         * 1. Enter size of graph, i.e. |V|:
         * 
         * Instructions:
         * 
         * -1: Exit
         * 
         * 0: print
         * 
         * 1: followed by line 'n', add vertex n
         * 
         * 2: followed by line 'n m', add edge from n to m
         * 
         * 3: initialize all vertices
         */

        Scanner getInstructions = new Scanner(System.in);
        DirectedGraph diGraph = new DirectedGraph(getInstructions.nextInt());
        getInstructions.nextLine();
        boolean done = false;
        while (!done) {
            if (getInstructions.hasNext()) {

                String nextLine = getInstructions.nextLine();
                Scanner lineScanInstructions = new Scanner(nextLine);
                int instruction = lineScanInstructions.nextInt();
                int frequency = new StringTokenizer(nextLine, " ").countTokens();
                lineScanInstructions.close();
                if (frequency == 1) { // One operation
                    if (instruction == -1) {
                        done = true;
                    } else if (instruction == 0) {
                        System.out.println("Print Graph");
                        System.out.println(diGraph);
                    } else if (instruction == 1) {
                        // add Vertex
                        nextLine = getInstructions.nextLine();
                        lineScanInstructions = new Scanner(nextLine);
                        //
                        int index = lineScanInstructions.nextInt();
                        diGraph.vertices[index] = new Vertex(index);
                    } else if (instruction == 2) {
                        // add Edge
                        //
                        try {
                            nextLine = getInstructions.nextLine();
                            lineScanInstructions = new Scanner(nextLine);
                            int frequency2 = new StringTokenizer(nextLine, " ").countTokens();
                            //
                            if (frequency2 > 3 || frequency2 < 2) {
                                System.err.println("Too many or too few tokens");
                            } else {
                                int from = lineScanInstructions.nextInt();
                                int to = lineScanInstructions.nextInt();
                                if (frequency == 3) {
                                    int w = lineScanInstructions.nextInt();
                                    diGraph.addEdge(from, to, w);
                                } else {
                                    diGraph.addEdge(from, to);
                                }
                            }

                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    } else if (instruction == 3) {
                        diGraph.initializeAllVertices();
                        System.out.println("All remaining vertices have been initialized");
                    } else if (instruction == 4) {
                        // remove Edge
                        //
                        try {
                            nextLine = getInstructions.nextLine();
                            lineScanInstructions = new Scanner(nextLine);
                            int frequency2 = new StringTokenizer(nextLine, " ").countTokens();
                            //
                            if (frequency2 != 2) {
                                System.err.println("Too many or too few tokens");
                            } else {
                                int from = lineScanInstructions.nextInt();
                                int to = lineScanInstructions.nextInt();

                                diGraph.removeEdge(from, to);
                            }

                        } catch (Exception e) {
                            System.err.println(e.getMessage());
                        }
                    }
                }
            }
        }
    }
}
