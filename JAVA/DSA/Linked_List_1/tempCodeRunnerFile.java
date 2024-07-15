import java.util.*;

public class pallindrome {
    
    class Node{
        int data;
        Node next;

        Node(int data){
            this.data=data;
            this.next=null;
        }
    }

    //to print the linked list
    public void print(){      
        Node current= head;

        if(head == null){    
            System.out.print( " The linked list is empty ");
            return;
        }

        System.out.print( "Elements: ");
        while(current!=null){  //rest case
            System.out.print( current.data + " ");
            current=current.next;
        }
        System.out.println();
        
    }

    //adding first
    public void add_first(int data){    
        Node temp = new Node(data);     //Node constructor activated
         
        if(head==null){     // extra case if the linked list is empty
             head=temp; 
             tail=temp; 
             
             return;
             
        }
        
        temp.next=head;
        head=temp;
        
        return;
    }

    //fast slow approch
    public static Node findmid(){
        Node fast= head;
        Node slow= head;
        while(fast!=null && fast.next!=null){
            slow=slow.next; //+1
            fast=fast.next; //+2
        }
        return slow;
    }

    public static boolean check_pallindrome(){

        if(head==null || head.next==null){
            return true;
        }

        //step 1 : find mid
        Node mid= findmid();

        //step 2: reverse the second half
        Node prv=null;
        Node curr= mid;
        Node next;

        while(curr!=null){
            next= curr.next;
            curr.next=prv;
            prv=curr;
            curr=next;
        }

        Node left= prv; // because currently prv points to the end of the node, but since its reversed it becomes head
        Node right=head;

        //step 3: check for palindrome
        while(right!=null){
            if(right.data != left.data){
                return false;
            }
        }
        return true;
    }

    static Node head;
    static Node tail;

    public static void main(String[] args) {

        pallindrome a= new pallindrome();
        a.add_first(1);     a.add_first(2);     a.add_first(3);     a.add_first(2);     a.add_first(1);     
        boolean ans1= check_pallindrome();
        System.out.println("The sequence is palindrome :  "+ans1);

        pallindrome b= new pallindrome();
        b.add_first(1);     b.add_first(2);         b.add_first(2);     b.add_first(1);     
        boolean ans2= check_pallindrome();
        System.out.println("The sequence is palindrome :  "+ans2);
        

    }



}
