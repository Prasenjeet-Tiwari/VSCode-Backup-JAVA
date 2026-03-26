import java.util.*;

public class codeforce {

    public static int Max_1(int[] arr){    //Time complexity O(N)
        if(arr.length==1){
            return arr[0];
        }

        int i;
        for(i=1; i<arr.length; i++){
            if(arr[i-1]>arr[i]){
                return arr[i-1];  
            }
        }
        return arr[i-1];
    }

    public static int Max_2( int[] arr){

        int mid= arr.length%2==0? arr.length/2 : (arr.length -1)/2

        while()
    }
    public static void main(String[] args) {
        
            int[] arr1= {1,2,3,4,5,6,5,4,3,2,1};
            int[] arr2= {4,3,2,1};
            int[] arr3= {4,5,6,7};
            int[] arr4= {1};

            System.out.println("Output: "+Max(arr1));
            System.out.println("Output: "+Max(arr2));
            System.out.println("Output: "+Max(arr3));
            System.out.println("Output: "+Max(arr4));
        
    }
}
