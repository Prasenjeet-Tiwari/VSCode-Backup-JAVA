import java.util.*;
public class interleave_even {
    public static void func(Queue<Integer> q){
        Queue<Integer> left= new LinkedList<>();
        int size=q.size();
        for(int i=0; i<size/2; i++){
            left.add(q.poll());
        }

        for(int j=0; j<size/2; j++){
            q.add(left.poll());
            q.add(q.poll());
        }
    }

    public static void main(String[] args) {
        Queue<Integer> q= new LinkedList<>();

        q.offer(0); q.offer(1); q.offer(2); 
        q.offer(3); q.offer(4); q.offer(5);
        
        System.out.print("Before: ");
        for(int k : q){
            System.out.print(k + "-> ");
        }
        System.out.print("\n");

        func(q);

        System.out.print("After : ");
        for(int k : q){
            System.out.print(k + "-> ");
        }
    }
}
