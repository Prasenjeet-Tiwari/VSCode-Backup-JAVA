import java.util.*;
public class rotated_sorted_twosum {


    public static boolean func(ArrayList<Integer> arr, int target) {
        
        int lp=0; //smallest value index
        int rp=0; //larget value index
        
        int n= arr.size(); // length of arrlist

        for(int i=0; i<arr.size(); i++){
            if(arr.get(i) > arr.get(i+1)){
                lp= i+1;
                rp=i;
                break;
            }
        }
        
        while(lp!=rp){

            if(arr.get(lp) + arr.get(rp) == target){    // just returning true of condition is met
                return true;                
            }
            else if(arr.get(lp) + arr.get(rp) > target){
                rp=(n+rp-1)%n;  //property of modular operator
            }
            else if(arr.get(lp) + arr.get(rp) < target){
                lp=(lp+1)%n;    //property of modular operator
            }
        }
        return false;

    }
    public static void main(String[] args) {

        ArrayList<Integer> arr= new ArrayList<>();
        arr.add(7); arr.add(8); arr.add(9);arr.add(1);
        arr.add(2); arr.add(3); arr.add(4); arr.add(5); arr.add(6);

        int target= 10;
        System.out.println("Target: "+ target+" , Such target exist?  Ans: " + func(arr, target));
    }
}
