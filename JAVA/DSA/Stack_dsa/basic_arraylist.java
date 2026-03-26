import java.util.*;

public class basic_arraylist {

    static class stack{

        static ArrayList<Integer> list= new ArrayList<>();

        //IsEmpty
        public static boolean IsEmpty(){
            return list.size()==0;
        }

        //push
        public static void push(int data){
            list.add(data);

        }

        //pop
        public static int pop(){
            if(IsEmpty()==true){
                System.out.println("lsit is empty nothing to remove.");
                return -1;
            }
            int val = list.remove(list.size()-1);
            return val;
        }

        //peek
        public static int peek(){
            if(IsEmpty()==true){
                System.out.println("lsit is empty nothing to remove.");
                return -1;
            }
            int val = list.get(list.size()-1);
            return val;
        }
}

    public static void main(String[] args) {

        stack s= new stack();
        s.push(1);  s.push(2);  s.push(3);  s.push(4);  s.push(5);  

        while(!s.IsEmpty()){
            System.out.print(s.peek()+ " ");
            s.pop();
        }
        
    } 
}
