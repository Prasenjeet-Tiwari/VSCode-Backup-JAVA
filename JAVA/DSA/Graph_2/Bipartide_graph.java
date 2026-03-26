import java.util.*;

class Bipartide_graph{

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
    public static boolean detectBipartide(ArrayList<Edge>[] graph){
        
        int colour[]=new int[graph.length];
        for( int i=0; i<colour.length; i++){
            colour[i]=-1; //-1 means no colour, 0 means Yellow, 1 means Blue
        }
        int curr_col=0;

        Queue<Integer> q=new LinkedList<>();
        for(int i =0; i<graph.length; i++){
            if(colour[i]==-1){
                q.add(i);
                colour[i]=curr_col==0? 1:0;
                while(!q.isEmpty()){
                    int curr=q.remove();
                    for( int j=0; j<graph[curr].size(); j++){
                        Edge e=graph[curr].get(j);
                        if(colour[e.dest]==-1){
                            colour[e.dest]=curr_col==0? 1:0;
                            q.add(e.dest);
                        }else if(e.dest!=-1 && colour[e.dest]==curr_col){
                            return false;
                        }
                    }
                }
                
            }
        }
        return true;
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
        
        System.out.println("Is it bipartide? : "+detectBipartide(graph));
    }

}