import java.util.*;

public class Simple_ques {

// Reverse
    public static void Reverse(ArrayList<Integer> arrlist){
        for(int i = arrlist.size() - 1; i >= 0; i--){
            System.out.print(arrlist.get(i) + " ");
        }
    }
// Max Min
    public static void Max_Min(ArrayList<Integer> arrlist){
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for(int i = 0; i < arrlist.size(); i++){
            max = Math.max(max, arrlist.get(i));
            min = Math.min(min, arrlist.get(i));  
        }
        System.out.println("\n");
        System.out.println("The maximum value is: " + max);
        System.out.println("The minimum value is: " + min);
    }
// Swap 2 numbers
    public static void Swap(ArrayList<Integer> arrlist, int index_1, int index_2){

        System.out.println("\nBefore swap: " + arrlist);
        int temp = arrlist.get(index_1);
        arrlist.set(index_1, arrlist.get(index_2));
        arrlist.set(index_2, temp);  // Fix here
        System.out.println("After swap: " + arrlist);

    }

    public static void main(String[] args) {

        ArrayList<Integer> arrlist = new ArrayList<>();
        arrlist.add(3);
        arrlist.add(4);
        arrlist.add(1);
        arrlist.add(9);

        Reverse(arrlist);
        Max_Min(arrlist);
        Swap(arrlist, 1, 2);

    }
}
