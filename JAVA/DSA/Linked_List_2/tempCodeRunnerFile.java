import java.util.*;
public class remove_cycle {

    static Node head;
    static Node tail;
    static Node common;

    static class Node{
        int data;
        Node next;

        Node(int data){
            this.data=data;
            this.next=null;
        }
    }

    public void add_first(int data){
        Node temp= new Node(data);

        if(head==null){
            head=tail=temp;
            return;
        }

        temp.next=head;
        head=temp;
        return;
    }

    public static boolean remove_loop(){
        Node slow=head;
        Node fast=head;
        boolean cycle=false;

        //finding forst meeting point between fast and slow
        while(fast!=null || fast.next!=null ){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                cycle=true;
                break;
            }
        }

        
        if(cycle==false){   //if cycle doesn't exists
            return false;
        }else{              //if exist then finding second intersection
            slow=head;
            Node prv= null;    // we will store the node just before the fast so that at end we can point it to null

            while(slow!=fast){
                prv=fast;
                slow=slow.next;
                fast=fast.next;
            }
            prv.next=null
            return true
        }
    }
    
    public static void remove_loop(){
        Node slow= head;
        Node temp= common;
        while(slow!=temp ){
            slow=slow.next;
            temp=temp.next;
            if(temp==slow){
                slow=null;
                return;
            }

        }

    }
    public static void Main_remove(){
        if(loopcheck()){
            remove_loop();
            System.out.println("Loop existed. We have removed the loop");

        }else{
            System.out.println("No loop present in the list");
        }
    }


    public static void main(String[] args) {
        remove_cycle list= new remove_cycle();

        list.add_first(1);
        list.add_first(2);
        list.add_first(3);
        list.head.next.next.next= list.head.next;

        Main_remove();
        
    }
}
