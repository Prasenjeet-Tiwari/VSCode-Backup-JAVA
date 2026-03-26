import java.util.*;

/*
 Notes on HashSet, LinkedHashSet, and TreeSet:

 1. HashSet:
    - Stores unique elements only.
    - No guaranteed order of elements (unordered).
    - Average time complexity of add, remove, search = O(1).
    - Internally uses a HashMap.
    - Allows one null value.

 2. LinkedHashSet:
    - Same as HashSet (unique elements only).
    - Maintains insertion order.
    - Slightly slower than HashSet due to order maintenance.
    - Uses a HashMap + doubly linked list internally.

 3. TreeSet:
    - Stores unique elements only.
    - Maintains elements in **sorted order** (natural order by default).
    - Implements NavigableSet, backed by a TreeMap (Red-Black Tree).
    - Operations (add, remove, search) take O(log n) time.
    - Does not allow to store null element (throws NullPointerException).

⚡ Quick Recap:
   - HashSet → Unordered (looks random, based on hashing).
   - LinkedHashSet → Ordered by insertion.
   - TreeSet → Ordered by sorting
*/

public class HashSet_LinkedHashSet_TreeSet {
    public static void main(String[] args) {
        
        // ================================
        // HashSet Example
        // ================================
        System.out.println("HashSet (Unordered):");
        HashSet<Integer> hs = new HashSet<>();
        hs.add(3);
        hs.add(4);
        hs.add(2);
        hs.add(1);

        // Iterating HashSet (unordered)
        for (Integer j : hs) {
            System.out.print(j + " ");
        }
        System.out.println("\n");


        // ================================
        // LinkedHashSet Example
        // ================================
        System.out.println("LinkedHashSet (Insertion order preserved):");
        LinkedHashSet<Integer> lhs = new LinkedHashSet<>();
        lhs.add(3);
        lhs.add(4);
        lhs.add(2);
        lhs.add(1);

        // Iterating LinkedHashSet (insertion order preserved)
        for (Integer i : lhs) {
            System.out.print(i + " ");
        }
        System.out.println("\n");


        // ================================
        // TreeSet Example
        // ================================
        System.out.println("TreeSet (Sorted order):");
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(3);
        ts.add(4);
        ts.add(2);
        ts.add(1);

        // Iterating TreeSet (sorted order)
        for (Integer i : ts) {
            System.out.print(i + " ");
        }
        System.out.println("\n");
    }
}
