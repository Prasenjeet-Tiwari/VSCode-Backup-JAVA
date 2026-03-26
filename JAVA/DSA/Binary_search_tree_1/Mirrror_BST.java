import java.util.*;

public class Mirrror_BST {
    
    //Node
    public static class Node {
        int data;
        Node left, right;
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    //Print function
     public static void func_print(Node root){
        Queue<Node> q= new LinkedList<>();
        q.add(root);
        q.add(null);
        
        while(!q.isEmpty()){
            Node temp= q.remove();
            if(temp==null){
                if(q.isEmpty()){
                    break;
                }else{
                    System.out.println();
                    q.add(null);
                }
            }else{
                if(temp.left!=null){
                    q.add(temp.left);
                }
                if(temp.right!=null){
                    q.add(temp.right);
                }
                System.out.print(temp.data+" ");
            }

        }
    }

    public static Node Mirror(Node root){
        if(root==null){
            return null;
        }

        Node lh= Mirror(root.left);
        Node rh= Mirror(root.right);
        root.left=rh;
        root.right=lh;
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(8);                 //           8
        root.left = new Node(5);                 //         /   \
        root.right = new Node(10);               //        5     10
        root.left.left = new Node(3);            //      /   \      \
        root.left.right = new Node(6);           //     3     6      11
        root.left.left.left = new Node(1);       //   /   \             \
        root.left.left.right = new Node(4);      //  1     4             14
        root.right.right = new Node(11);
        root.right.right.right = new Node(14);  
        func_print(root);
        System.out.println();
        Node temp=Mirror(root);
        func_print(temp);

    }

}
