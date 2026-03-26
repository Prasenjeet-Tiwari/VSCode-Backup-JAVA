import java.util.*;

public class Bst_a_sorted_Array {

    public static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

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

    public static Node BST(int arr[], int start, int end) {
        if (start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;
        Node root = new Node(arr[mid]);
        root.left = BST(arr, start, mid - 1);
        root.right = BST(arr, mid + 1, end);
        return root;
    }

    public static void main(String[] args) {
        int arr[] = {3, 4, 5, 6, 7, 8, 9, 10};

        System.out.print("Before: ");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        Node root = BST(arr, 0, arr.length - 1);

        System.out.println("After (Level Order):");
        levelorder(root);
    }
}
