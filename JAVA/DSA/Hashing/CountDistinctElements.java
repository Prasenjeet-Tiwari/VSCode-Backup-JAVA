import java.util.*;

public class CountDistinctElements{

    public static void main(String args[]){

        int[] arr={1,2,3,4,5,6,7,2,3,4,1,};

        HashSet<Integer> hs= new HashSet<>();
        for(int i=0; i<arr.length; i++){
             hs.add(arr[i]);
        }
       System.out.println(hs);
       System.out.println("the number if distinct elements : "+hs.size());
    }
}