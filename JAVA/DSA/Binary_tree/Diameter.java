
public class Diameter {
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

    //Height of the tree
        public static int TreeHeight(Node root){

            if(root==null){
                return 0;
            }

            int lf=TreeHeight(root.left);
            int rh=TreeHeight(root.right);
            return Math.max(lf , rh) +1;
        }

    //Diameter O(N^2)- Time Complexity
        public static int Diameter_1(Node root){
            if(root==null){
                return 0;
            }
            int lf=Diameter_1(root.left);      //calc left and right height of each node 
            int lh=TreeHeight(root.left);

            int rf=Diameter_1(root.right);
            int rh=TreeHeight(root.right);

            int selfDiam= lh + rh + 1;      //diameter if it passes through root

            return Math.max( selfDiam, Math.max(lf+rf+1 , selfDiam) ); 
        }

    //Diameter O(N)-Time Complexity
        
        //class for info 
        static class Info{
            int diam;
            int ht;
             
            public Info(int diam, int ht){
                this.diam=diam;
                this.ht=ht;
            }
        }

        //func for diameter O(N)
        public static Info Diameter_2(Node root){   
            if(root==null){
                return new Info(0,0);
            }

            Info leftInfo=Diameter_2(root.left);
            Info rightInfo=Diameter_2(root.right);

            int own_diam= Math.max(Math.max(leftInfo.diam, rightInfo.diam) , leftInfo.ht + rightInfo.ht +1);
            int own_ht=Math.max(leftInfo.ht, rightInfo.ht);

            return new Info(own_diam, own_ht);
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

        System.out.println("The Maximum Diameter between two nodes O(N^2): "+ tree.Diameter_1(root));
        System.out.println("The Maximum Diameter between two nodes O(N): "+ tree.Diameter_2(root).diam);
    }
    
}
 