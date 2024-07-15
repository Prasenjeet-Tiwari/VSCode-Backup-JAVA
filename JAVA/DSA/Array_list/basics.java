import java.util.ArrayList;
import java.util.Collections;
//  or use jaava.util.*;

public class basics{
    public static void main(String[] args){

    //creating the array lsit 

        ArrayList<Integer> arrlist= new ArrayList<>();

    //to add an element use: arrayname.add()

        arrlist.add(10);
        arrlist.add(20);
        arrlist.add(30);
        arrlist.add(40);
        System.out.println("Updated arraylist: " + arrlist );

    //to fetch element of an array at index i use: arrayname.get()

        System.out.println("\nElement at index 0: " + arrlist.get(0));
        System.out.println("Element at index 1: " + arrlist.get(1));
        System.out.println("Element at index 2: " + arrlist.get(2));
        System.out.println("Element at index 3: " + arrlist.get(3));
        //use arrayname.add(index, value); to add at specific index

    //to remove element of an array at index i use: arrayname.remove()
        
        System.out.println("\nRemoving Element at index 3: " + arrlist.remove(3));
        System.out.println("Updated arraylist: " + arrlist );
        

    // to find the length of list use : arrarname.size()

        System.out.println("\nSixe of ArrayList: " + arrlist.size());

    //to check an element present in the array use: arrayname.contain()

        System.out.println("\nArrayList contains 100: " + arrlist.contains(100));
        System.out.println("ArrayList contains 20: " + arrlist.contains(20));

    // to Sort list use : collections.sort()

        Collections.sort(arrlist);
        System.out.println("\nSorted ArrayList: " + arrlist);

    //to change value of an array at index i use: arrayname.set(index, new_value);

        arrlist.set(0, 50);
        System.out.println("\nUpdated after changing value of  arraylist: " + arrlist );
    }
}