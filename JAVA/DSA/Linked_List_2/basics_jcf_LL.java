import java.util.*;
public class basics_jcf_LL {
    
    public static void main(String[] args) {

        //object
        LinkedList<Integer> list = new LinkedList<>();
        
        //addiing first and last

        list.add(2);
        list.addFirst(1);
        list.addLast(3);
        System.out.println(list);

        //remove first and last
        list.removeLast();
        list.removeFirst();
        System.out.println(list);

    }
}
