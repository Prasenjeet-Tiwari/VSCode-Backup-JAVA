import java.util.*;

public class shortest_distance_between_nodes{

//Node class
    static class Node {
        int data;
        Node left;
        Node right;

        //constructor
        public Node(int data){
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }
// LCA function   
    public static Node optimizedLca(Node root, int n1, int n2){

        if(root == null || root.data == n1 || root.data == n2  ){
            return root;
        }

        Node leftLca = optimizedLca(root.left,n1,n2);
        Node rightLca = optimizedLca(root.right,n1,n2);


        if(leftLca == null){
            return rightLca;
        }
        if(rightLca == null){
            return leftLca;
        }
        return root;

    }
//distance function
    public static int distance(Node lca, int num) {
        if(lca==null){
            return -1;
        }
        if(lca.data==num){
            return 0;
        }

        int left= distance(lca.left, num);
        int right= distance(lca.right, num);

        if(left==-1 && right ==-1){
            return 0;
        }else if(left==-1){
            return right +1;
        }else{
            return left +1;
        }
    }

//Shortest distance function
    public static int Shortest_Distance(Node root, int num1 , int num2){
        
        Node lca = optimizedLca(root, num1, num2);

        int dis1 = distance(lca, num1);
        int dis2 = distance(lca, num2);

        return dis1 +dis2;
    }
    public static void main(String args[]){

        Node root = new Node(1);                //           1
        root.left= new Node(2);                 //         /   \  
        root.right= new Node(3);                //       2       3  
        root.left.left= new Node(4);            //     /   \   /   \
        root.left.right= new Node(5);           //    4     5  6    7
        root.right.left= new Node(6);
        root.right.right= new Node(7);

    System.out.println("The Shortest Distance : " + Shortest_Distance(root,4,5));
    System.out.println("The Shortest Distance : " + Shortest_Distance(root,4,7));

    }
}