import java.util.*;

public class stack_using_two_queue {
    static class StackUsingQueue {
        static Queue<Integer> q1 = new LinkedList<>();   // main
        static Queue<Integer> q2 = new LinkedList<>();   // auxiliary

        // Push operation (costly push, O(n))
        public static void add(int data) {
            q2.offer(data);  // insert new element into q2
            while (!q1.isEmpty()) {
                q2.offer(q1.poll());  // move all from q1 -> q2
            }
            // swap q1 and q2
            Queue<Integer> temp = q1;
            q1 = q2;
            q2 = temp;
        }

        // Pop operation
        public static void delete() {
            if (q1.isEmpty()) {
                System.out.println("Underflow");
                return;
            }
            q1.poll();
        }

        // Peek operation
        public static void peek_element() {
            if (q1.isEmpty()) {
                System.out.println("Underflow");
                return;
            }
            System.out.println("Peeked Element: " + q1.peek());
        }

        // Print stack
        public static void print() {
            if (q1.isEmpty()) {
                System.out.println("Stack is empty");
                return;
            }
            for (int val : q1) {
                System.out.print(val + " -> ");
            }
            System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        StackUsingQueue suq = new StackUsingQueue();
        suq.add(0);
        suq.add(1);
        suq.add(2);
        suq.add(3);
        suq.add(4);

        suq.print();
        suq.peek_element();

        suq.delete();
        suq.delete();

        suq.print();
        suq.peek_element();
    }
}
