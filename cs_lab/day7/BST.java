import java.util.*;

public class BST{


    static class Node {
        int data;
        Node left, right;

        Node(int val) {
            data = val;
            left = right = null;
        }
    }

    Node root;


    Node insert(Node root, int val) {
        if (root == null)
            return new Node(val);
        if (val < root.data)
            root.left = insert(root.left, val);
        else if (val > root.data)
            root.right = insert(root.right, val);
        return root;
    }


    boolean search(Node root, int key) {
        if (root == null)
            return false;
        if (root.data == key)
            return true;
        if (key < root.data)
            return search(root.left, key);
        return search(root.right, key);
    }


    Node findMin(Node root) {
        while (root.left != null)
            root = root.left;
        return root;
    }

    Node deleteNode(Node root, int key) {
        if (root == null) {
            System.out.println("Key not found!");
            return null;
        }

        if (key < root.data)
            root.left = deleteNode(root.left, key);
        else if (key > root.data)
            root.right = deleteNode(root.right, key);
        else {
            // Node found
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            Node temp = findMin(root.right);
            root.data = temp.data;
            root.right = deleteNode(root.right, temp.data);
        }
        return root;
    }


    void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    void preorder(Node root) {
        if (root == null) return;
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);
    }

    void postorder(Node root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.data + " ");
    }


    boolean isBST(Node root, long min, long max) {
        if (root == null)
            return true;
        if (root.data <= min || root.data >= max)
            return false;
        return isBST(root.left, min, root.data) && isBST(root.right, root.data, max);
    }


    public static void main(String[] args) {
        BST tree = new BST();
        Scanner sc = new Scanner(System.in);
        int choice, val;

        while (true) {
            System.out.println("Binary Search Tree Operations ");
            System.out.println("1. Insert");
            System.out.println("2. Delete");
            System.out.println("3. Search");
            System.out.println("4. Inorder Traversal");
            System.out.println("5. Preorder Traversal");
            System.out.println("6. Postorder Traversal");
            System.out.println("7. Check if BST");
            System.out.println("8. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.next();
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter value to insert: ");
                    val = sc.nextInt();
                    tree.root = tree.insert(tree.root, val);
                    System.out.println("Inserted " + val);
                    break;

                case 2:
                    System.out.print("Enter value to delete: ");
                    val = sc.nextInt();
                    tree.root = tree.deleteNode(tree.root, val);
                    break;

                case 3:
                    System.out.print("Enter value to search: ");
                    val = sc.nextInt();
                    System.out.println(tree.search(tree.root, val) ? "Found" : "Not Found");
                    break;

                case 4:
                    System.out.print("Inorder Traversal: ");
                    tree.inorder(tree.root);
                    System.out.println();
                    break;

                case 5:
                    System.out.print("Preorder Traversal: ");
                    tree.preorder(tree.root);
                    System.out.println();
                    break;

                case 6:
                    System.out.print("Postorder Traversal: ");
                    tree.postorder(tree.root);
                    System.out.println();
                    break;

                case 7:
                    System.out.println(tree.isBST(tree.root, Long.MIN_VALUE, Long.MAX_VALUE)
                            ? "The tree is a BST"
                            : "The tree is NOT a BST");
                    break;

                case 8:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
