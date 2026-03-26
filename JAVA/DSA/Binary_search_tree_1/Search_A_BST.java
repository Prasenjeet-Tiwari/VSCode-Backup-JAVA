
public class Search_A_BST {
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
    public static boolean search(Node root, int val){
        if(root==null){
            return false;
        }if(root.data==val){
            return true;
        }
        if(root.data>val){
            return search(root.left, val);
        }else{
            return search(root.right, val);
        }
    }


    public static void main(String[] args) {
        Node root = new Node(5);                //           5
        root.left= new Node(2);                 //         /   \  
        root.right= new Node(8);                //       2       8  
        root.left.left= new Node(1);            //     /   \       \
        root.left.right= new Node(3);           //    1     3       9
        root.right.left= new Node(9);

        boolean output= search(root, 1);
        System.out.println("The data exists:  "+output);
    }
    
}
