import java.util.*;

class linkedlist_main {
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

        if(head == null){    // extra case if the linked list is empty
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
             head=tail=temp; 
             size++;
             return;
             
        }
        
        temp.next=head;
        head=temp;
        size++;
        return;
    }

//adding last
    public void add_last(int data){
        
        Node temp = new Node(data);
        if(head==null){    // extra case if the linked list is empty
            head = tail =temp;
            size++;
            return;
        }

        tail.next=temp;
        tail=temp;
        size++;
    }

// add at an index
    public void add_idx(int idx, int data){
        
        if(idx==0){     // if index=0 calling add_first func
            add_first(data);
            return;
        }

        Node temp = head;
        int k=0;
        while(k<idx-1){     // note: idx-1
            temp=temp.next;
            k++;
            size++;
        }
        Node newnode = new Node(data);

        newnode.next=temp.next;
        temp.next=newnode;
        size++;
        
    }

//size
    public void size(){
        // not creating any function just increment and decrement "size" variable at required places
        System.out.print( "The size is : " + size +"\n");
    }

//remove first
    public void remove_first(){ 
         
        if(head==null){     // extra case if the linked list is empty
             System.out.println("The Linked List is EMPTY, nothing to remove ");
             return;
        }

        Node temp = head; 
        head=temp.next;
        size--;
        return;
    }

//remove last
    public void remove_last(){ 
         
        if(head==null){     // extra case if the linked list is empty
             System.out.println("The Linked List is EMPTY, nothing to remove ");
             return;
        }
        else if(size==1){   //extra case if only 1 element present
            Node temp = head; 
            head=tail=null;
            size--;
            return;
        }

        Node temp = head; 
        int k=0;
        while(k<size-2){     // note-- (size-1 : last elemnt) , (size-2 : second last element)
            temp=temp.next;
            k++;
        }
        temp.next=null;
        tail=temp;
        size--;
        return;
    }

// Search element and return its index
    public static int search(int data_value){
        
        if(head==null){
            System.out.println("The Linked List is Empty");
            return -1;
        }

        Node temp= head;
        for(int i=0; i<= size-1; i++){
            if(temp.data==data_value){
                return i;
            }
            temp=temp.next;
        }
        System.out.println("The element NOT FOUND");
        return -1;


    }

//Search BUT Recursion
    // public static int Rec_search(Node head, int data_value){
    //     if(head==null){
    //         System.out.println("The Linked list is Empty. OR .  Element not Present in List");
    //     }
    //     else if(head.data == data_value){
    //         return 0;
    //     }
    //     int idx= Rec_search(head.next, data_value);

    //     if(idx==-1){
    //         return -1;
    //     }

    //     return idx+1;

    // }

//Reverse a linked list
    public static void reverse(){

        if (head == null) {
            System.out.println("The linked list is empty");
            return;
        }

        Node prev = null;
        Node curr = head;
        tail = head;
        Node next;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
        
    }
    
//Static Variables    
    public static Node head;
    public static Node tail;
    public static int size;
    
    public static void main(String[] args) {
        
        linkedlist_main a= new linkedlist_main();   // create the linked list which is an object of main class, strange i know

    //Adding at first
        System.out.println("Adding at first");      
        a.add_first(3);
        a.print();
        a.size();
        System.out.println("\n-----------\n");

    //Adding at last
        System.out.println("Adding at last");       
        a.add_last(5);
        a.print();
        a.size();
        System.out.println("\n-----------\n");

    //Adding at index
        System.out.println("Adding at index");      
        a.add_idx(1,4);
        a.print();
        a.size();
        System.out.println("\n-----------\n");
        
    //Removing in first
        System.out.println("Removing in first");    
        a.remove_first();
        a.print();
        a.size();
        System.out.println("\n-----------\n");

    //Removing in last
        System.out.println("Removing in last");     
        a.remove_last();
        a.print();
        a.size();
        System.out.println("\n-----------\n");

    //Search element and return its index
        System.out.println("Search element and return its index");      
        int s= 4;
        int search_index= a.search(s);
        if(search_index!=-1){
            System.out.println("The index is: "+ search_index);
        }
        System.out.println("\n-----------\n");

    //reverse the list
        a.add_first(3); a.add_first(2); a.add_first(1);
        
        System.out.println("Before reversing: " );      a.print();
        a.reverse();
        System.out.println("After reversing:  " );      a.print();
        System.out.println("\n-----------\n");
        
        
    }
}