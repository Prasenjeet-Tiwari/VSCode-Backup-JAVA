import java.util.*;
public class fractional_kapsack {
    public static void main(String[] args) {
        int weight[]={10,20,30};
        int value[]={60,100,120};

        int capacity=50;

        // Nx2 matrix to store index and ratio of value/weight
        double ratio[][]=new double[weight.length][2];

        for(int i=0; i<weight.length; i++){
            ratio[i][0]=i;
            ratio[i][1]= value[i]/ (double)weight[i] ;
        }

        //ascending order sorting only possible via this func--later reverse the loop
        Arrays.sort(ratio,Comparator.comparingDouble(o->o[1]));

        double amount=0;

        for(int j=ratio.length-1; j>=0; j--){
            int index= (int)ratio[j][0];
            if(capacity > weight[index]){
                capacity = capacity-weight[index];
                amount+=value[index];
            }else{
                amount+=capacity*ratio[j][1];
            }
        }

        System.out.println("The total amount:"+amount);
    }  
}
