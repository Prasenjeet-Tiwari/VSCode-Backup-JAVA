import java.util.*;

public class Merge_tree {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static Node mergeBST(Node root1, Node root2) {

        // STEP 1
        // make sorted arrays of both trees
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();

        inOrder(root1, list1);
        inOrder(root2, list2);

        // STEP 2
        // merger both arrays in sorted manner

        ArrayList<Integer> list3 = new ArrayList<>();
        int j = 0, k = 0;
        while (j < list1.size() && k < list2.size()) {
            if (list1.get(j) <= list2.get(k)) {
                list3.add(list1.get(j));
                j++;
            } else {
                list3.add(list2.get(k));
                k++;
            }
        }

        while (j < list1.size()) { // remaining elements of list 1
            list3.add(list1.get(j));
            j++;
        }

        while (k < list2.size()) { // remaining elements of list 2
            list3.add(list2.get(k));
            k++;
        }

        // STEP 3
        // Now make a BALANCED tree of list3 list

        return createBst(list3, 0, list3.size() - 1);

    }

    public static void inOrder(Node root, ArrayList<Integer> list) {

        if (root == null) {
            return;
        }

        inOrder(root.left, list);
        list.add(root.data);
        inOrder(root.right, list);

    }

    public static void printInOrder(Node root) {
        if (root == null) {
            return;
        }

        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }

    public static Node createBst(ArrayList<Integer> list, int start, int end) {

        if (start > end) {
            return null;
        }

        int mid = start + (end -start)/ 2;

        Node root = new Node(list.get(mid));
        root.left = createBst(list, start, mid-1);
        root.right = createBst(list, mid + 1, end);

        return root;
    }

    public static void main(String args[]) {

                                    //         BST1
        Node root1 = new Node(2);   //          2
        root1.left = new Node(1);   //         / \
        root1.right = new Node(4);  //        1   4
        
                                    //          BST2
        Node root2 = new Node(9);   //          9
        root2.left = new Node(3);   //         / \
        root2.right = new Node(12); //        3   12

       Node root =  mergeBST(root1,root2);

       printInOrder(root);

    }

}