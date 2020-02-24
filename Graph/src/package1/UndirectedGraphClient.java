package package1;

import java.util.*;
import java.io.*;

public class UndirectedGraphClient {

    static void instructionFalseInput(UndirectedGraph G) {
        System.out.println("False Input");
    }

    static void instructionPrint(UndirectedGraph G) {
        System.out.println(G);
    }

    static void instructionAddEdge(UndirectedGraph G, int u, int v) {
        try {
            G.addEdge(u, v);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    static void instructionRemoveEdge(UndirectedGraph G, int u, int v) {
        G.removeEdge(u, v);
    }

    public static void main(String[] args) throws FileNotFoundException {
        File input = new File("Graph/input1.txt");

        Scanner scanInputLine = new Scanner(input);
        Scanner instructionScanner = new Scanner(scanInputLine.nextLine());
        int N = instructionScanner.hasNextInt() ? instructionScanner.nextInt() : 20;
        UndirectedGraph G = new UndirectedGraph(N);
        boolean done = false;
        while (!done) {
            instructionScanner.close();
            if (scanInputLine.hasNextLine()) {
                instructionScanner = new Scanner(scanInputLine.nextLine());
                int instruction = instructionScanner.hasNextInt() ? instructionScanner.nextInt() : -2;

                if (instruction == -2) {
                    instructionFalseInput(G);
                } else if (instruction == -1) {
                    // done
                    done = true;
                } else if (instruction == 0) {
                    instructionPrint(G);
                } else if (instruction == 1) {
                    instructionScanner = new Scanner(scanInputLine.nextLine());
                    int u = instructionScanner.hasNextInt() ? instructionScanner.nextInt() : -1;
                    int v = instructionScanner.hasNextInt() ? instructionScanner.nextInt() : -1;
                    if (u != -1 && v != -1) {
                        instructionAddEdge(G, u, v);
                    }
                } else if (instruction == 2) {
                    instructionScanner = new Scanner(scanInputLine.nextLine());
                    int u = instructionScanner.hasNextInt() ? instructionScanner.nextInt() : -1;
                    int v = instructionScanner.hasNextInt() ? instructionScanner.nextInt() : -1;
                    if (u != -1 && v != -1) {
                        instructionRemoveEdge(G, u, v);
                    }
                } else if (instruction == 11) {
                    G.DFS(new boolean[G.N]);
                } else if (instruction == 12) {
                    G.DFS_Number(new boolean[G.N]);
                } else if (instruction == 121) {
                    System.out.println(Arrays.toString(G.DFS_Number(new boolean[G.N])));
                } else if (instruction == 13) {
                    G.DFS_Low(new boolean[G.N]);
                } else if (instruction == 131) {
                    System.out.println(Arrays.toString(G.DFS_Low(new boolean[G.N])));
                }

            }
        }
    }

}