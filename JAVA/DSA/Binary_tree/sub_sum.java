
import java.util.LinkedList;
import java.util.Queue;

public class sub_sum {

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
    public static int func(Node root){

        if(root==null){
            return 0;
        }
        int prv_root_left_data=root.left==null?0:root.left.data;
        int prv_root_right_data=root.left==null?0:root.right.data;

        int left_data=func(root.left);
        int right_data=func(root.right);    
        root.data= left_data + right_data + prv_root_left_data + prv_root_right_data;   

        return left_data + right_data + prv_root_left_data + prv_root_right_data;
    }

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


    public static void main(String args []){

        Node root = new Node(1);                //           1
        root.left= new Node(2);                 //         /   \  
        root.right= new Node(3);                //       2       3  
        root.left.left= new Node(4);            //     /   \   /   \
        root.left.right= new Node(5);           //    4     5  6    7
        root.right.left= new Node(6);
        root.right.right= new Node(7);

        func(root);
        func_print(root);
    }
    
}
