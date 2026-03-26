import java.util.*;
public class reverse_queue {

    public static void func(Queue<Integer> q){
        Stack<Integer> s= new Stack<>();
        while(!q.isEmpty()){
            s.push(q.poll());
        }
        while(!s.isEmpty()){
            q.offer(s.pop());
        }

    }
    public static void print(Queue<Integer> q){
        for(int k: q){
            System.out.print(k+" => ");
        }
         System.out.print("\n");
    }
    

    public static void main(String[] args) {
        
        Queue<Integer> q= new LinkedList<>();
        q.offer(0); q.offer(1); q.offer(2); q.offer(3); q.offer(4); 
        System.out.print("Before: ");
        print(q);

        func(q);

        System.out.print("After : ");
        print(q);
    }
}
