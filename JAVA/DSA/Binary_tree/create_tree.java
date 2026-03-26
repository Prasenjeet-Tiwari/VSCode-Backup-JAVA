import java.util.*;

import org.w3c.dom.Node;

public class create_tree {

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

        //Inorder print
        public static void inorder(Node root){
            if(root==null){
                return;
            }
            System.out.print(root.data + " ");
            inorder(root.left);
            inorder(root.right);
        }

        //Preorder print
        public static void preorder(Node root){
            if(root==null){
                return;
            }
            preorder(root.left);
            System.out.print(root.data + " ");
            preorder(root.right);
        }

        //PostOrder
        public static void postorder(Node root){
            if(root==null){
                return;
            }
            postorder(root.left);
            postorder(root.right);
            System.out.print(root.data + " ");
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

        //inorder
        System.out.print("InOrder : ");
        tree.inorder(root);
        System.out.println();

        //Preorder
        System.out.print("PreOrder : ");
        tree.preorder(root);
        System.out.println();

        //Postorder
        System.out.print("PostOrder : ");
        tree.postorder(root);
        System.out.println();

    }
}
