import java.util.*;

public class merge_sort {

    // Node class represents a node in the linked list
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next=null;
        }
    }

    private Node head;  // Head of the linked list
    private Node tail;  // Tail of the linked list

    // Method to print the elements of the linked list
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

// Method to add a new node with given data to the end of the linked list
    public void add(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;  
        }
    }

// Method to find the middle node of a linked list using slow and fast pointers
    public Node getmid(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node slow = head;
        Node fast = head;
        // Move fast pointer 
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;  // Return the middle node
    }

// Method to merge two sorted linked lists into one sorted linked list
    private Node merge(Node left, Node right) {

        Node merged = new Node(Integer.MIN_VALUE);  // Dummy node to start the merged list
        Node temp = merged;  // Temporary pointer to build the merged list

        // Merge the two lists while maintaining sorted order
        while (left != null && right != null) {
            if (left.data <= right.data) {
                temp.next = left;
                left = left.next;
                temp = temp.next;
            } else {
                temp.next = right;
                right = right.next;
                temp = temp.next;
            }
              // Move temp pointer forward
        }

        // Append remaining nodes of left or right list if any

        while (left != null || right != null) {

            temp.next = (left != null) ? left : right;
        }

        return merged.next;  // Return the merged list (excluding the dummy node)
    }

    // Method to recursively sort a linked list using merge sort
    public Node merge_sort(Node head) {
        // Base case: if the list is empty or has only one node, return it
        if (head == null || head.next == null) {
            return head;
        }

        // Find the middle node of the list
        Node mid = getmid(head);
        Node right_head = mid.next;  // Head of the right sublist
        mid.next = null;  // Split the list into two halves

        // Recursively sort the left and right halves
        Node newLeft = merge_sort(head);
        Node newRight = merge_sort(right_head);

        // Merge the sorted left and right halves
        return merge(newLeft, newRight);
    }

    public static void main(String[] args) {
        merge_sort merge_sort = new merge_sort();

        // Adding elements to the linked list
        merge_sort.add(2);        merge_sort.add(3);        merge_sort.add(1);        merge_sort.add(5);        merge_sort.add(4);

        System.out.print("Before sorting: ");
        merge_sort.print();

        // Perform merge sort on the linked list
        merge_sort.head = merge_sort.merge_sort(merge_sort.head);

        System.out.print("After  sorting: ");
        merge_sort.print();
    }
}
