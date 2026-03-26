import java.util.ArrayList;

public class Root_To_leave_permutation {

    //Node
    public static class Node {
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

    //ArrayList
    public static ArrayList<Integer> arr=new ArrayList<>(); 

    //Main Function
    public static void Root_to_leave(Node root,ArrayList<Integer> arr){
        if(root==null){
            return;
        }
        arr.add(root.data);
        if(root.left==null && root.right==null){
            print(arr);
        }
        Root_to_leave(root.left, arr);
        Root_to_leave(root.right, arr);
        arr.remove(arr.size()-1);

    }

    //Print Func
    public static void print(ArrayList<Integer> arr){
        for(int i: arr){
            System.out.print(i+" ");
        }
        System.out.println();
    }
    

    //MAIN
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

        Root_to_leave(root, arr);

    }
}
