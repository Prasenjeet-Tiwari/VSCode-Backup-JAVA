import java.util.*;

public class zigzag {

    // Node class
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Variables
    static Node head;
    static Node tail;

    // Add element to the end
    public static void addLast(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }

    // Print list
    public static void print() {
        Node temp = head;
        if (head == null) {
            System.out.println("Empty list.");
            return;
        }
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    // Zigzag
    public static void zigzag() {
        // Get mid
        if (head == null || head.next == null) {
            return;
        }
        Node slow = head;
        Node fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        Node mid = slow;

        // Reverse second half
        Node curr = mid.next;
        mid.next = null;
        Node prev = null;
        Node next;
        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node left = head;
        Node right = prev;

        // Alternate merge
        while (left != null && right != null) {
            Node leftNext = left.next;
            Node rightNext = right.next;

            left.next = right;
            if (leftNext == null) {
                break;
            }
            right.next = leftNext;

            left = leftNext;
            right = rightNext;
        }
    }

    public static void main(String[] args) {
        zigzag list = new zigzag();
        list.addLast(1);   list.addLast(2);   list.addLast(3);   list.addLast(4);   list.addLast(5);   list.addLast(6);
        System.out.print("Original List: ");
        list.print();

        list.zigzag();

        System.out.print("Zigzag List: ");
        list.print();
    }
}
