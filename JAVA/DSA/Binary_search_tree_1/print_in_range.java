
public class print_in_range {
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
    public static void PrintInRange(Node root,int  min, int max){

        if(root==null){
            return;
        }
        if(root.data>=min && root.data<=max){
            PrintInRange(root.left, min, max);
            System.out.print(root.data+" ");
            PrintInRange(root.right, min, max);

        }else if(root.data<min){
            PrintInRange(root.right, min, max);

        }else if(root.data>max){  //data > max
            PrintInRange(root.left, min, max);
        }

    }


    public static void main(String[] args) {
        Node root = new Node(5);                //           5
        root.left= new Node(2);                 //         /   \  
        root.right= new Node(8);                //       2       8  
        root.left.left= new Node(1);            //     /   \       \
        root.left.right= new Node(3);           //    1     3       9
        root.right.left= new Node(9);
       

        PrintInRange(root,2,8);
    }

    
    
}
