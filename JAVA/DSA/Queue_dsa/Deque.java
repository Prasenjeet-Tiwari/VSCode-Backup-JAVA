import java.util.LinkedList;

public class Deque {
    /*
     * we have the following operations:
     *      addFirt()
     *      addLast()
     *      removeFirst()
     *      removeLast()
     *      getFirst()
     *      getLast()
     */

    public static class stack{
        public static Deque<Integer> ds=new LinkedList<>();

        public static void push(int data){
            ds.addLast(data);
        }
        public static void pop(){
            ds.removeLast();
        }
        public static void peek(){
            ds.getFirst();

        }

        public stack() {
        }
    }
    public static class queue{
        public static Deque<Integer> dq=new LinkedList<>();

        public static void offer(int data){
            dq.addLast(data);

        }
        public static void poll(){
            dq.removeFirst();
        }
        public static void peek(){
            dq.getLast();
        }
        
    }
    

    public static void main(String[] args) {
        stack s= new stack();
        queue q=new queue();

    }
}
