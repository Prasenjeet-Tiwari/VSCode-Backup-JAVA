import java.util.*;

public class TopView {

    // Node class
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
        }
    }

    static class Info {
        Node node;
        int hd; // horizontal distance

        Info(Node node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    public static void TopView(Node root) {
        if (root == null) return;

        Queue<Info> q = new LinkedList<>();
        Map<Integer, Node> map = new TreeMap<>();  // TreeMap to auto-sort by hd

        q.add(new Info(root, 0));

        while (!q.isEmpty()) {
            Info curr = q.poll();

            // if this hd is not already in map, add it
            if (!map.containsKey(curr.hd)) {
                map.put(curr.hd, curr.node);
            }

            if (curr.node.left != null) {
                q.add(new Info(curr.node.left, curr.hd - 1));
            }

            if (curr.node.right != null) {
                q.add(new Info(curr.node.right, curr.hd + 1));
            }
        }

        // Print top view from leftmost to rightmost hd
        for (Node node : map.values()) {
            System.out.print(node.data + " ");
        }
    }

    // MAIN
    public static void main(String[] args) {
        //            1
        //          /   \
        //         2     3
        //          \     
        //           4    
        //            \
        //             5
        //              \
        //               6

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.right = new Node(4);
        root.left.right.right = new Node(5);
        root.left.right.right.right = new Node(6);

        TopView(root);  // Output: 2 1 3 6
    }
}
