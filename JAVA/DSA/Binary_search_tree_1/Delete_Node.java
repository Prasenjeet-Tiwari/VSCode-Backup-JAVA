
public class Delete_Node{
    public static class Node{
        int  data;
        Node left;
        Node right;

        Node(int data) {
            this.data=data;
            this.left=null;
            this.right=null;
        }
        
    }

    public static Node delete(Node root, int val){

        //null case
        if(root==null){
            return null;
        }

        //search
        if(root.data>val){
            root.left=delete(root.left, val);
        }else if(root.data<val) {
           root.right= delete(root.right, val);
        }
        else{

            //case1: both side null
            if(root.left==null && root.right==null){
                return null;
            
            }
            //case2: one side null other side has node
            else if(root.left==null){
                return root.right;

            }else if(root.right==null){
                return root.left;

            }
            //case3: both side node
            else{

                /*find the minimum valuye Node in the right side of the node to be deleted
                copy the value of minimum node into deleted node
                delete the minimum node*/

                Node minRight= minimum_Right(root.right);
                root.data=minRight.data;
                root.right=delete(root.right, minRight.data);
            }

        }
        return root;

    }
    public static Node minimum_Right(Node root){
        Node prv= root;
        while(root!=null){
            prv=root;
            root=root.left;
        }
        return prv;   
    }


    public static void inorder_print(Node root){
        if(root==null){
            return;
        }
        inorder_print(root.left);
        System.out.print(root.data);
        inorder_print(root.right);
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

        System.out.print("Before: ");
        inorder_print(root);

        System.out.println();
        System.out.print("After:  ");
        delete(root, 5);
        inorder_print(root);
    }
    
}
