import java.util.*;
public class Min_abs_diff {
    public static void main(String[] args) {
        
        int arr1[]={1,2,3};
        int arr2[]={2,1,3};


        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int min_value=0;

        for(int i=0; i<arr1.length; i++){
            min_value+= Math.abs( arr1[i] - arr2[i] );
        }
        System.out.println("The minimum difference is : "+min_value);

    }
}
