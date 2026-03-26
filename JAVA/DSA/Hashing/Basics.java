import java.util.*;

public class Basics {

    public static void main(String[] args) {
        
        //HashMap is unordered data set, that is data is stored randamly and in no order.

        HashMap<String,Integer> hm = new HashMap<>();

    //put(Key, value)   -O(1)
        hm.put("a",1);        
        hm.put("b",1);        
        hm.put("c",1);        
        hm.put("d",1);        
        hm.put("e",1);  

        System.out.println(hm);

    //get(key)  -O(1)
        System.out.print("The value of key \"a\" is :");
        int output=hm.get("a");
        System.out.println(output);

    //containsKey(key)  -O(1)
        System.out.println(hm.containsKey("a"));
        System.out.println(hm.containsKey("z"));


    //remove(key)       -O(1) (will remove both the key and value pair)
        System.out.println( hm.remove("a"));
        System.out.println(hm);

    //size 
        System.out.println("Size of HashMap :"+hm.size()+" and the hm is: "+hm);

    //isEmpty  and clear  (hm.clear clears the whole hashmap )

        hm.clear();
        System.out.println("The HashMap is empty: "+hm.isEmpty());
        
    //iterating in HashMap Usinig "keySet"
        hm.put("Apple",5);   hm.put("Banana",2);   hm.put("Cat",4);   hm.put("Dog",5);   hm.put("Eye",3);  

        Set<String> keys= hm.keySet();
        System.out.println("Keys are : "+keys);

        for (String k : keys) {
            System.out.println("The key :"+k+" the value: "+hm.get(k));
        }
    
    }
}
