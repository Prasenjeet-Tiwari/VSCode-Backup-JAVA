import java.util.*;

public class Palindrome {
    
    class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public Node head;
    public Node tail;

    // To print the linked list
    public void print() {
        Node current = head;

        if (head == null) {
            System.out.print("The linked list is empty");
            return;
        }

        System.out.print("Elements: ");
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    // Adding first
    public void addFirst(int data) {
        Node temp = new Node(data); // Node constructor activated

        if (head == null) { // Extra case if the linked list is empty
            head = temp;
            tail = temp;
            return;
        }

        temp.next = head;
        head = temp;
    }

    // Fast slow approach
    public Node findMid() {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next; // +1
            fast = fast.next.next; // +2
        }
        return slow;
    }

    public boolean checkPalindrome() {
        if (head == null || head.next == null) {
            return true;
        }

        // Step 1: Find mid
        Node mid = findMid();

        // Step 2: Reverse the second half
        Node prev = null;
        Node curr = mid;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        Node right = prev; // Because currently prev points to the end of the node, but since it's reversed it becomes head
        Node left = head;

        // Step 3: Check for palindrome
        while (right != null) {
            if (left.data != right.data) {
                return false;
            }
            right = right.next;
            left = left.next;
        }
        return true;
    }

    public static void main(String[] args) {
        Palindrome a = new Palindrome();
        a.addFirst(1);
        a.addFirst(2);
        a.addFirst(3);
        a.addFirst(2);
        a.addFirst(1);
        boolean ans1 = a.checkPalindrome();
        System.out.println("The sequence is palindrome: " + ans1);

        Palindrome b = new Palindrome();
        b.addFirst(1);
        b.addFirst(2);
        b.addFirst(2);
        b.addFirst(1);
        boolean ans2 = b.checkPalindrome();
        System.out.println("The sequence is palindrome: " + ans2);

        Palindrome c = new Palindrome();
        c.addFirst(1);
        c.addFirst(4);
        c.addFirst(3);
        c.addFirst(2);
        c.addFirst(1);
        boolean ans3 = c.checkPalindrome();
        System.out.println("The sequence is palindrome: " + ans3);
    }
}
