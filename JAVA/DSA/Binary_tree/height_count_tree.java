
public class height_count_tree {
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
    
    static class BinaryTree{
        static int idx=-1;
        
        public static Node BuildTree(int nodes[]){
            idx++;
            Node currNode= new Node(nodes[idx]);
            if(nodes[idx]==-1){
                return null;
            }
            currNode.left=BuildTree(nodes);
            currNode.right=BuildTree(nodes);
    
    
            return currNode;
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
    // Count Number of Nodes
        public static int TreeNodeCount(Node root){
            if(root==null){
                return 0;
            }
            int lc=TreeNodeCount(root.left);
            int rc=TreeNodeCount(root.right);
            return lc+rc+1;
            
        }

    }
    
    public static void main(String[] args) {
        
        int nodes[]={1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};

        BinaryTree tree= new BinaryTree();
        Node root=tree.BuildTree(nodes);
        System.out.println("Root: "+root.data);

        /*                  1
                           / \
                          2   3
                         / \   \
                        4   5   6
        */

        int height= tree.TreeHeight(root);
        System.out.println("The height is: "+height);

        int count= tree.TreeNodeCount(root);
        System.out.println("The Number of nodes is: "+count);
    }
}
