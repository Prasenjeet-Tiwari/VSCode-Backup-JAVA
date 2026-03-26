import java.util.*;
public class linkedlist {

    static class Node{
        int data;
        Node next;


        Node(int data){
            this.data=data;
            this.next=null;
        }
    }
    
    static class stack{

        //Node
        static Node head=null;

        //IsEmpty
        public static boolean IsEmpty(){
            return head==null;
        }

        //push
        public static void push(int data){
            Node newhead= new Node(data);

            if(IsEmpty()==true){
                head=newhead;
                return;

            }
            newhead.next=head;
            head=newhead;
        }

        //pop
        public static int pop(){

            if(IsEmpty()==true){
                return -1;
            }
            int val= head.data;
            head=head.next;
            return val;
        }

        //peek
        public static int peek(){

            if(IsEmpty()==true){
                return -1;
            }
            int val= head.data;
            
            return val;
        }

    }   

    public static void main(String[] args) {

        stack s= new stack();
        s.push(1);  s.push(3);  s.push(5);  s.push(7);  s.push(9);  

        while(!s.IsEmpty()){
            System.out.print(s.peek()+" ");
            s.pop();
        }
        
    }
    
}
