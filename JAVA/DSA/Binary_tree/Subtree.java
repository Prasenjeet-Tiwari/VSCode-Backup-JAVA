import java.util.*;

import org.w3c.dom.Node;

public class Subtree {
    
    //Node class
    static class Node{
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

    //Identical check func
    public static boolean isIdentical(Node node, Node subRoot){
        
        //if both are null
        if(node == null && subRoot == null){
            return true;
        }
        //case for false
        else if(node == null || subRoot == null || node.data != subRoot.data){
            return false;
        }

        //checking left side
        if(!isIdentical(node.left,subRoot.left)){
            return false;
        }
        //cheking right side
        if(!isIdentical(node.right, subRoot.right)){
            return false;
        }

        return true;
    }

    //SubTree func
    public static  boolean isSubtree(Node root,Node subRoot){

        //base case
        if(root == null){ // when we reach at the end of the tree and found no identical root
            return false;
        }

        //checking the current node itself
        if(root.data == subRoot.data){
            if(isIdentical(root,subRoot)){
                return true;
            }
        }

        //checking left and right part of the tree
        boolean left = isSubtree(root.left, subRoot);
        boolean right = isSubtree(root.right,subRoot);

        return left || right;
    }

    // MAIN
    public static void main(String args[]){

        // main tree 
        Node root = new Node(1);               //              1
        root.left = new Node(2);               //            /   \
        root.right = new Node(3);              //           2     3
        root.left.left = new Node(4);          //         /  \   / \
        root.left.right = new Node(5);         //        4    5 6   7
        root.right.left = new Node(6);         
        root.right.right = new Node(7);        
        
        // sub tree
        Node subRoot = new Node(2);            //         2
        subRoot.left = new Node(4);            //       /   \
        subRoot.right = new Node(5);           //      4     5
        
        System.out.println("The given Tree a 'SubTree' of the 'MainTree' : "+isSubtree(root,subRoot));
    }
}