import java.util.*;
public class Indian_min_coin {
    public static void main(String[] args) {
        
        //units is used to count number of each demonatization used
        int demonatization[]={1,2,5,10,20,50,100,500,2000};
        int units[]=new int[demonatization.length];

        int amount=121;
        int count=0;

        //starting from last cause we want least number of coins to use

        int i=demonatization.length -1;

        while(amount!=0 && i>=0){
            if(amount>=demonatization[i]){
                amount=amount-demonatization[i];
                units[i]+=1;
                count++;
            }else{
                i-=1;
            }
        }
        System.out.println("No of coins used:"+count);
        for(int j=0; j<demonatization.length; j++){
            System.out.println("Note "+demonatization[j]+" count: "+units[j]);
        }
    }
}
