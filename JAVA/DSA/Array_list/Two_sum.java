import java.util.ArrayList;

public class Two_sum {

    public static void two_sum(ArrayList<Integer>  arr, int target) {

        int lp=0;
        int rp= arr.size()-1;
        
        while(lp!=rp){
   
            if(arr.get(lp) + arr.get(rp) > target){     //case 1: lp+rp >target
                rp--;
            }
            else if(arr.get(lp) + arr.get(rp) < target ){    //case 1: lp+rp <target
                lp--;
            }
            else if(arr.get(lp) + arr.get(rp) == target){   //reqired condition
                System.out.println("Indexes:"+lp + " and "+ rp);
                System.out.println("Values: "+arr.get(lp) + " and "+arr.get(rp));
                System.out.println("The target sum is: "+ target);

                return ;
            }
        }  
    }
    public static void main(String[] args) {

        // NOTE:  the arraylsit has to be sorted

        ArrayList<Integer> arr= new ArrayList<>();
        arr.add(1); arr.add(2); arr.add(3); arr.add(4); arr.add(5); arr.add(6);
        int target = 5;
        two_sum(arr, target );
        
    }
}
