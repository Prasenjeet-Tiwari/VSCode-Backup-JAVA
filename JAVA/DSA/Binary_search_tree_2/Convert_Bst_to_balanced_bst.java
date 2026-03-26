
import java.util.*;

public class Convert_Bst_to_balanced_bst{
    public static void levelorder(Node root) {
        if (root == null) {
            return;
        }
        Queue<Node> q = new LinkedList<>();
        q.add(root);
        q.add(null);

        while (!q.isEmpty()) {
            Node curr = q.remove();
            if (curr == null) {
                System.out.println();
                if (q.isEmpty()) {
                    break;
                } else {
                    q.add(null);
                }
            } else {
                System.out.print(curr.data + " ");
                if (curr.left != null) {
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    q.add(curr.right);
                }
            }
        }
    }

    public static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void inorder(Node root, ArrayList<Integer> arr){
        if(root==null){
            return;
        }
        inorder(root.left,arr);
        arr.add(root.data);
        inorder(root.right,arr);
    }
    public static Node BST(ArrayList<Integer> arr,int start, int end){
        if(start>end){
            return null;
        }
        int mid=(start+end)/2;
        Node root=new Node(arr.get(mid));

        root.left=BST(arr, start, mid-1);
        root.right=BST(arr, mid+1, end);
        return root;
    }

    public static void ConvertToBst(Node root){

        ArrayList<Integer> arr= new ArrayList<>();
        inorder(root, arr);
        Node StartRoot=BST(arr,0,arr.size()-1);
        levelorder(StartRoot);

    }
    public static void main(String[] args) {
        Node root = new Node(5);                //          5
        root.left= new Node(3);                 //        /   \  
        root.right= new Node(8);                //       3     8  
        root.left.left= new Node(2);            //      /       \
        root.left.left.left= new Node(1);       //     2         9
        root.right.right= new Node(9);          //    /
                                                //   1

        ConvertToBst(root);
    }
}