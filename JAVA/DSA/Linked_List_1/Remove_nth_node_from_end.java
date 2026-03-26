import java.util.*;

public class Remove_nth_node_from_end{
    
    public class Node{
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

    // Remove Nth element from the end
    public void remove(int n){

        if(head==null || n <= 0){
            System.out.println("Invalid value of n: " + n);
            return;
        }

        Node temp1= head, temp2= head;

        //calc the size
        int size=0;
        while ( temp1!=null){
            size++;
            temp1=temp1.next;
        }

        //if n is greater than size or less than or equal to 0
        if (n > size) {
            System.out.println("Invalid value of n: " + n);
            return;
        }

        //if first element  
        if(n==size){
            head=head.next;
            return;
        }

        //else
        int j=1;    //we assigned 1 cause we are counting element and not the index
        int k= size-n; // its the position just before the required index from start
        temp1=head;      // cause temp is pointing to taila fte size calc
        while(j< k){
            temp1=temp1.next;
            j++;
        }

        temp1.next= temp1.next.next;    //since here temp is the element before the required element
    }

//Static Variables    
    public Node head;
    public Node tail;

    
    public static void main(String[] args) {
        
        Remove_nth_node_from_end a= new Remove_nth_node_from_end();   // create the linked list which is an object of main class
    

        a.add_first(1);     a.add_first(2);     a.add_first(3);     a.add_first(4);     a.add_first(5);
        a.print();

        a.remove(1);
        a.print();

    }
}