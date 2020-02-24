package package1;

import java.util.*;

public class UndirectedGraph {

    LinkedList<Integer>[] vertices;
    int N;

    UndirectedGraph(int N) {
        this.N = N;
        this.vertices = new LinkedList[N];
        initializeVertices();
    }

    void initializeVertices() {
        for (int i = 0; i < N; i++) {
            this.vertices[i] = new LinkedList<Integer>();
        }
    }

    void addEdge(int u, int v) throws Exception {
        if (u < 0 || u >= N || v < 0 || v > N) {
            throw new Exception("Index out of Bounds!");
        } else {
            this.vertices[u].add(v);
            this.vertices[v].add(u);
        }
    }

    void removeEdge(int u, int v) {
        Iterator iter1 = this.vertices[u].iterator();
        while (iter1.hasNext()) {
            Integer i = iter1.next();
            if (i == v) {
                iter1.remove();
            }
        }
        Iterator iter2 = this.vertices[v].iterator();
        while (iter2.hasNext()) {
            Integer i = iter2.next();
            if (i == u) {
                iter2.remove();
            }
        }
    }

    void DFS(boolean[] visited) {
        // @params: boolean[N]
        for (int i = 0; i < this.N; i++) {
            if (visited[i] == false) {
                DFS_Visit(i, visited);
            }
        }
    }

    void DFS_Visit(int u, boolean[] visited) {
        visited[u] = true;
        System.out.println("Visit of " + u);
        for (Integer v : this.vertices[u]) {
            if (!visited[v]) {
                DFS_Visit(v, visited);
            }
        }
    }

    int[] DFS_Number(boolean[] visited) {
        int[] dfs = new int[this.N];
        int current = 1;
        for (int i = 0; i < this.N; i++) {
            if (visited[i] == false) {
                current = DFS_Number_Visit(i, visited, dfs, current);
            }
        }
        return dfs;
    }

    int DFS_Number_Visit(int u, boolean[] visited, int[] dfs, int current) {
        visited[u] = true;
        dfs[u] = current;
        for (Integer v : this.vertices[u]) {
            if (!visited[v]) {
                current = DFS_Number_Visit(v, visited, dfs, ++current);
            }
        }
        return current;
    }

    int[] DFS_Low(boolean[] visited) {
        int[] dfs = new int[this.N];
        int[] LOW = new int[this.N];
        int[] pred = new int[this.N];
        int current = 1;
        int s = 1;
        pred[s] = -1;
        for (int i = s; i < this.N; i++) {
            if (visited[i] == false) {
                current = DFS_Low_Visit(i, visited, dfs, LOW, pred, current);
            }
        }
        return LOW;
    }

    int DFS_Low_Visit(int u, boolean[] visited, int[] dfs, int[] LOW, int[] pred, int current) {
        visited[u] = true;
        dfs[u] = current;
        int low = current;
        int low2 = current;
        for (Integer v : this.vertices[u]) {
            if (!visited[v]) {
                pred[v] = u;
                low = Math.min(DFS_Low_Visit(v, visited, dfs, LOW, pred, ++current), low);

            } else if (visited[v] && pred[u] != v) {
                low2 = Math.min(low2, dfs[v]);

            }
        }
        LOW[u] = Math.min(low2, low);
        return LOW[u];
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
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