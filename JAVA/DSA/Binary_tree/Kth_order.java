import java.util.*;
public class Kth_order {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
            this.left=left;
            this.right=right;
        }
    }

    public static void Kth_order(Node node, int level, int k){

        if(node==null){
            return;
        } 
        if(level==k){
            System.out.print( node.data + " ");
            return;
        }
        int updated_level=level +1;
        Kth_order(node.left , updated_level,  k);
        Kth_order(node.right , updated_level,  k);
    }



    public static void main(String[] args) {


        Node root = new Node(1);                //           1
        root.left= new Node(2);                 //         /   \  
        root.right= new Node(3);                //       2       3  
        root.left.left= new Node(4);            //     /   \   /   \
        root.left.right= new Node(5);           //    4     5  6    7
        root.right.left= new Node(6);
        root.right.right= new Node(7);

        int k =3;
        Kth_order(root, 1 , k);

    }   
}
