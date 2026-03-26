import java.util.*;
/*
    Notes on Map Implementations in Java
    -------------------------------------

    1) HashMap
       - Stores key-value pairs.
       - Keys are unique, values can be duplicate.
       - Order is NOT guaranteed (iteration order depends on hashing).
       - Operations: put, get, remove → O(1) average time.
       - Allows one null key and multiple null values.

    2) LinkedHashMap
       - Stores key-value pairs.
       - Maintains **insertion order** of keys.
       - Internally: HashMap + doubly-linked list.
       - Slightly slower than HashMap, but predictable order.
       - Useful when you need both fast lookups and ordered iteration.

    3) TreeMap
       - Stores key-value pairs.
       - Maintains **sorted order of keys** (natural order or custom Comparator).
       - Internally uses a Red-Black Tree.
       - Operations: put, get, remove → O(log n).
       - Does NOT allow null keys (throws NullPointerException).

    ⚡ Quick Recap:
       - HashMap → Unordered (fastest for lookups).
       - LinkedHashMap → Ordered by insertion.
       - TreeMap → Ordered by sorting of keys.
*/

public class HashMap_LinkedHashMap_TreeMap {


    public static void main(String[] args) {

        // HashMap stores data in unordered way
        //whereas 

    //HasMap
        HashMap<String, Integer> hm= new HashMap<>();
        hm.put("India",100);
        hm.put("China",200);
        hm.put("America",300);

    //LinkedHashMap(Linked HashMap stores data in ordered way, the order in which they were entered by the user)
        LinkedHashMap<String, Integer> lhm= new LinkedHashMap<>();
        lhm.put("India",100);
        lhm.put("China",200);
        lhm.put("America",300);

    //TreeMap   (store data  in SORTED order manner)
    //Implemented using Red Black Trees (they are BST tree which are sself balancing)
    //But this time all the operation will be done in O(log(n))
        TreeMap<String,Integer> thm =  new TreeMap<>();
        thm.put("India",100);
        thm.put("America",300);
        thm.put("China",200);

        System.out.println(hm);     //{China=200, America=300, India=100}
        System.out.println(lhm);    //{India=100, China=200, America=300}
        System.out.println(thm);    //{India=100, China=200, America=300}

    }
    


}
