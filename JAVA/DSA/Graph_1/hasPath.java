import java.util.*;

public class hasPath {
    static class Edge {
        int src, dest, wt;
        Edge(int s, int d, int w) {
            src = s;
            dest = d;
            wt = w;
        }
    }

    // Create the graph
    static void createGraph(ArrayList<Edge>[] graph) {
        for (int i = 0; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0, 1, 1));
        graph[0].add(new Edge(0, 2, 1));

        graph[1].add(new Edge(1, 0, 1));
        graph[1].add(new Edge(1, 3, 1));

        graph[2].add(new Edge(2, 0, 1));
        graph[2].add(new Edge(2, 4, 1));

        graph[3].add(new Edge(3, 1, 1));
        graph[3].add(new Edge(3, 4, 1));
        graph[3].add(new Edge(3, 5, 1));

        graph[4].add(new Edge(4, 2, 1));
        graph[4].add(new Edge(4, 3, 1));
        graph[4].add(new Edge(4, 5, 1));

        graph[5].add(new Edge(5, 3, 1));
        graph[5].add(new Edge(5, 4, 1));
        graph[5].add(new Edge(5, 6, 1));

        graph[6].add(new Edge(6, 5, 1)); // back edge for completeness
    }


    public static boolean hasPath(ArrayList<Edge>[] graph, int src, int dest, boolean[] visited) {
        if (src == dest) {
            return true; // found the path
        }

        visited[src] = true;

        for (int i=0; i<graph[src].size(); i++) {
            Edge e=graph[src].get(i);

            if (!visited[e.dest] && hasPath(graph, e.dest, dest, visited)) {
                return true; // path exists via neighbor
            }
        }
        return false; // no path found from this vertex
    }

    public static void main(String[] args) {
        int V = 7;
        ArrayList<Edge>[] graph = new ArrayList[V];
        createGraph(graph);

        boolean[] visited = new boolean[V];

        int src = 0;
        int dest = 6;

        if (hasPath(graph, src, dest, visited)) {
            System.out.println("Path exists between " + src + " and " + dest);
        } else {
            System.out.println("No path exists between " + src + " and " + dest);
        }
    }
}
