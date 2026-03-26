import java.util.*;

public class max_water {
    public static void water(ArrayList<Integer> arr){

        int max=0;
        int height=0; int width=0; int volume=0; 
        int lp=0;
        int rp= arr.size()-1;
        while(lp<rp){

            height= Math.min(arr.get(lp) , arr.get(rp));
            width= rp-lp;
            volume= width * height;
            max= Math.max(volume, max);

            if(arr.get(lp)>arr.get(rp)){
                rp--;
            }else{
                lp++;
            }
        }
        System.out.println("The max voulume of water: "+ max);
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr= new ArrayList<>();
        arr.add(7); arr.add(1); arr.add(4); arr.add(5); 
        water(arr);
    }
}
