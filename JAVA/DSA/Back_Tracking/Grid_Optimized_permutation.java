import java.util.*;

public class Grid_Optimized_permutation{

    public static int  factorial(int n) {
      
      if (n==0){
        return 0;
      }
      if(n==1){
        return 1;
      }
      return n*factorial(n-1);
    
    }

    public static void main(String[] args) {
        int n = 3;
        int m=3;
        int output = factorial(n+m-2)/(factorial(n-1)*factorial(m-1));
        
        System.out.println("Total solutions: "+output);
    }
}
