import java.util.*;

public class Validate_BST_inorder_Approach {

    public static class Node {
        int data;
        Node left, right;
        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    // ------------------ Approach 1: Inorder + Queue ------------------
    public static Queue<Integer> q = new LinkedList<>();

    public static void add_Inorder_In_Queue(Node root) {
        if (root == null) return;
        add_Inorder_In_Queue(root.left);
        q.add(root.data);
        add_Inorder_In_Queue(root.right);
    }

    public static boolean Validate(Node root) {
        q.clear(); // make sure queue is empty before traversal
        add_Inorder_In_Queue(root);

        if (q.isEmpty()) return true;

        int prev = q.poll();
        while (!q.isEmpty()) {
            int curr = q.peek();
            if (curr <= prev) {
                return false; // not strictly increasing
            }
            prev = q.poll();
        }
        return true;
    }

    // ------------------ Approach 2: Advanced Recursive (Node-based bounds) ------------------
    public static boolean isValidBST(Node root) {
        return isValidUtil(root, null, null);
    }

    private static boolean isValidUtil(Node root, Node leftBound, Node rightBound) {
        if (root == null) return true;

        // Check bounds
        if (leftBound != null && root.data <= leftBound.data) return false;
        if (rightBound != null && root.data >= rightBound.data) return false;

        // Recurse for subtrees with updated bounds
        return isValidUtil(root.left, leftBound, root) &&
               isValidUtil(root.right, root, rightBound);
    }

    // ------------------ MAIN ------------------
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

        // Inorder Approach
        boolean result = Validate(root);
        System.out.println("The BST is valid (inorder method)?   : " + result);

        // Recursive Node-based Approach
        result = isValidBST(root);
        System.out.println("The BST is valid (recursion method)? : " + result);
    }
}
