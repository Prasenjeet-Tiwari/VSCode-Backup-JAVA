import java.util.*;

public class Basics{

// Array
    static class Array_queue {

        static int arr[];
        static int rear;
        static int size;

        Array_queue(int n){
            arr = new int[n];
            size = n;
            rear = -1;
        }

        // isEmpty
        public boolean isEmpty() {
            return rear == -1;
        }

        // push  T.C: O(1)
        public void push(int data){
            if (rear == size - 1) {
                System.out.println("Queue Overflow");
                return;
            }
            rear = rear + 1;
            arr[rear] = data;
        }

        // pop   T.C: O(n)
        public int pop() {
            if (isEmpty()) {
                System.out.print("Queue Underflow  ");
                return -1;
            }
            int front = arr[0];
            for (int i = 0; i < rear; i++) {
                arr[i] = arr[i + 1];
            }
            rear = rear - 1;
            return front;
        }

        // peek  T.C: O(1)
        public int peek() {
            if (isEmpty()) {
                System.out.println("Queue Underflow");
                return -1;
            }
            return arr[0];
        }
    }

//Circular Array  OR Arraydeque

    static class Circular_array_queue{
        int size;
        int arr[];
        int rear;
        int front;

        Circular_array_queue(int n){
            arr= new int[n];
            size=n;
            rear=-1;
            front=-1;
        }

        //isEmpty
        public boolean isEmpty(){
            return (rear==-1 && front==-1);
        }

        //isFull
        public boolean isFull(){
            return (rear+1)%size == front;
        }



        //add
        public void push(int data){
            if(isFull()){
                System.out.print("Queue Overflow");
                return;
            }

            if(front==-1){  //adding the first element case
                front=0;
            }
            rear=rear+1;
            arr[rear]=data;
        }

        //pop
        public int pop(){
            if(isEmpty()){
                System.out.print("Queue underflow ");
                return -1;
            }

            int val = arr[front];

            if(rear ==front){   // removing last elemnt case
                rear=front=-1;
            }else{

                front= (front+1)%size;  
            }
            return val;
        }

        //peek
        public int peek(){
            if(isEmpty()){
                System.out.println("Queue underflow");
                return -1;
            }
            return arr[front];
        }
    }

// Linked List
    static class Linked_list_queue {
        
        LinkedList<Integer> LL = new LinkedList<>();

        // push
        public void push(int data) {
            LL.addLast(data);
        }

        // pop
        public int pop() {
            if (LL.isEmpty()) {
                System.out.print("Queue Underflow  ");
                return -1;
            }
            return LL.removeFirst();
        }

        // peek
        public int peek() {
            if (LL.isEmpty()) {
                System.out.println("Queue Underflow  ");
                return -1;
            }
            return LL.getFirst();
        }
    }

//MAIN
    public static void main(String[] args) {

        // Array Queue
        System.out.println("Array Queue: ");

        Array_queue aq = new Array_queue(3);
        aq.push(10);        aq.push(20);        aq.push(30);
        System.out.println("Peek: " + aq.peek());
        System.out.println("Pop: " + aq.pop());
        System.out.println("Pop: " + aq.pop());
        System.out.println("Pop: " + aq.pop());
        System.out.println("Pop: " + (aq.pop() != -1 ? aq.pop() : null) + '\n');

        // LinkedList Queue
        System.out.println("Linked List Queue: ");

        Linked_list_queue lq = new Linked_list_queue();
        lq.push(51);        lq.push(52);        lq.push(53);
        System.out.println("Peek: " + lq.peek());
        System.out.println("Pop: " + lq.pop());
        System.out.println("Pop: " + lq.pop());
        System.out.println("Pop: " + lq.pop());
        System.out.println("Pop: " + (lq.pop() != -1 ? lq.pop() : null) + '\n'); 

        // Circular Array
        System.out.println("Circular OR Arraydeque Queue: ");

        Circular_array_queue cq= new Circular_array_queue(3);
        cq.push(101);        cq.push(102);        cq.push(103);
        System.out.println("Peek: " + cq.peek());
        System.out.println("Pop: " + cq.pop());
        System.out.println("Pop: " + cq.pop());
        System.out.println("Pop: " + cq.pop());
        System.out.println("Pop: " + (cq.pop() != -1 ? cq.pop() : null) + '\n'); 

    }
}
