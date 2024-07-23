public class doubly {

    //class
    class Node{

        int data;
        Node next;
        Node prv;

        public Node(int data){
            this.data=data;
            this.next=null;
            this.prv=null;
        }
    }

    //variables
    public static Node head;
    public static Node tail;
    public static int size;

    //addFirst
    public void addFirst(int data){

        Node newnode= new Node(data);
        size++;

        if(head==null){
            tail=head=newnode;    
            return;        
        }

        newnode.next=head;
        head.prv=newnode;
        head=newnode;
    }

    //addLast
    public void addLast(int data){
        Node newnode= new Node(data);
        size++;

        if(head==null){
            tail=head=newnode;
        }

        newnode.prv= head;
        head.next=newnode;
        tail=  newnode;
    }

    //print
    public void print(){
        Node temp=head;

        if(head==null){
            System.out.println("Empty lsit.");
        }

        while(temp!=null){
            System.out.print(temp.data + " ");
            temp=temp.next;
        }
        System.out.println();
    }

    //delFirst
    public void delFirst(){
        if(head==null){
            System.out.println("list is empty. ");
            return;
        }

        if(size==1){
            size--;
            head=tail=null;

        }

        size--;
        head=head.next;
        head.prv=null;        
    }
    
    //delLast
    public void delLast(){
        if(head==null){
            System.out.println("list is empty. ");
            return;
        }

        if(size==1){
            size--;
            head=tail=null;

        }

        size--;
        tail=tail.prv;
        tail.next=null;
    }

    //reverse
    public void reverse(){
        if(head==null || head.next==null){
            return;
        }

        Node prv=null;
        Node curr=head;
        Node next;
        while(curr!=null){
            next=curr.next;
            curr.next=prv;
            curr.prv=next;

            prv= curr;
            curr=curr.prv;
        }
        tail=head;
        head=prv;

    }

    public static void main(String[] args) {

        doubly list= new doubly();

        list.addFirst(4);   list.addFirst(3);   list.addFirst(2);   list.addFirst(1);   
        list.print();
        System.out.println("size: "+size + "\n");

        list.reverse();
        list.print();
        System.out.println("size: "+size + "\n");

        list.delFirst();
        list.print();
        System.out.println("size: "+size + "\n");

        list.delLast();
        list.print();
        System.out.println("size: "+size + "\n");
        
    }
}
