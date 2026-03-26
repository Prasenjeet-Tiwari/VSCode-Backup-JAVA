import java.util.*;
public class remove_cycle {

    static Node head;
    static Node tail;

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
        while(fast!=null && fast.next!=null ){
            slow=slow.next;
            fast=fast.next.next;
            if(slow==fast){
                cycle=true;
                break;
            }
        }

        
        if(cycle==false){   //if cycle doesn't exists
            System.out.println("No loop in the list");
            return false;
        }
        
        //if exist then finding second intersection
        slow=head;
        Node prv=null ;    // we will store the node just before the fast so that at end we can point it to null
        while(slow!=fast){
            prv = fast;
            slow=slow.next;
            fast=fast.next;
        }
        
        prv.next=null;
        System.out.println("Loop existed but we have removed it");
        return true;
        
    }


    public static void main(String[] args) {
        remove_cycle list= new remove_cycle();

        list.add_first(1);
        list.add_first(2);
        list.add_first(3);
        list.head.next.next.next= list.head.next;
        System.out.print("Case 1: ");
        remove_loop();

        list.add_first(1);
        list.add_first(2);
        list.add_first(3);
        System.out.print("Case 2: ");
        remove_loop();
        
    }
}
