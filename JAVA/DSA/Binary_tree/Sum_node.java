import java.util.*;
public class Sum_node {
    //Node class
    static class Node{
        int data;
        Node left;
        Node right;

        Node(int data){
            this.data=data;
            this.left=null;
            this.right=null;
        }
    }

// BinaryTree class and its func( BuildTree, inorder, preorder, postorder)
    static class BinaryTree{
        static int idx=-1;

        //to find the root
        public static Node BuildTree(int nodes[]){
            idx++;
            if(nodes[idx]==-1){
                return null;
            }
            Node newNode= new Node( nodes[idx]);
            newNode.left=(BuildTree(nodes));
            newNode.right=(BuildTree(nodes));

            return newNode;
       
        }

        //Sum of nodes
        public static int SumNodes(Node root){
            if(root==null){
                return 0;
            }
            int lf=SumNodes(root.left);
            int rf=SumNodes(root.right);

            return lf + rf + root.data;
        }
    }

//MAIN
    public static void main(String[] args) {
        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};

        /*                  1
                           / \
                          2   3
                         / \   \
                        4   5   6
        */


        //creating table and printing root
        BinaryTree tree= new BinaryTree();
        Node root= tree.BuildTree(nodes);
        System.out.println("Root: "+root.data);

        int sum= tree.SumNodes(root);
        System.out.println("The sum of all the nodes: "+sum);
    }
    
}
 