import java.util.*;

class Detect_loop_undirectional_using_DFS{

    static class Edge{
        int src;
        int dest;
        int wt;

        Edge(int src, int dest, int wt){
            this.src=src;
            this.dest=dest;
            this.wt=wt;
        }
    }

    public static void create_graph(ArrayList<Edge>[] graph){

        for(int i = 0; i<graph.length; i++){
            graph[i] = new ArrayList<>();
        }

        graph[0].add(new Edge(0,1,1));
        graph[0].add(new Edge(0,2,1));

        graph[1].add(new Edge(1,0,1));
        graph[1].add(new Edge(1,3,1));

        graph[2].add(new Edge( 2,  0,  1));
        graph[2].add(new Edge( 2,  4,  1));

        graph[3].add(new Edge( 3,  1,  1));
        graph[3].add(new Edge( 3,  4,  1));
        graph[3].add(new Edge( 3,  5,  1));

        graph[4].add(new Edge( 4,  2,  1));
        graph[4].add(new Edge( 4,  3,  1));
        graph[4].add(new Edge( 4,  5,  1));

        graph[5].add(new Edge( 5,  6,  1));
        graph[5].add(new Edge( 5,  3,  1));
        graph[5].add(new Edge( 5,  4,  1));

    }
    public static boolean detectLoop(ArrayList<Edge>[] graph){
        boolean visited[]= new boolean[graph.length];

        for(int i=0; i< graph.length; i++){
            if(!visited[i]){
                if(detectLoopUtil(graph,visited,i,-1)){
                    //cycyle exists in oneof the connected component
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean detectLoopUtil(ArrayList<Edge>[] graph,boolean[] visited, int curr,int par){

        visited[curr]=true;

        for(int i=0; i<graph[curr].size(); i++){
            Edge e= graph[curr].get(i);
            //case 1 --> not visited yet
            if(!visited[e.dest] && detectLoopUtil(graph, visited, e.dest,curr)){
                return true;
            }
            //case 2 --> visited and its not parent
            else if(visited[e.dest]&& e.dest!=par){
                return true;
            }
            //case 3 --> continue
        }
        return false;
    }

    public static void main(String[] args) {

        int V = 7;
        @SuppressWarnings("unchecked")
        ArrayList<Edge> graph[] = new ArrayList[V];
        create_graph(graph);
        /*
         *        1 ____ 3
         *       /       | \
         *      0        |  5 --6
         *       \       | /
         *        2 ____ 4
         */
        System.out.println("Loop exists in the graph? : " + detectLoop(graph));
        
    }

}