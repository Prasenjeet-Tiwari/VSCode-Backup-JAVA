import java.util.*;

public class Good_factorial {

    public static int factorial(int n){     // note here we defined the function as int and not void;
        if(n<0){                            // this is safety case if input is <0
            return 1;
        }
        if(n==0){                           
            return 1;
        }  
        int fm= factorial(n-1);
        int output= n*fm;
        return output;
     } 
    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
        System.out.print("Enter the number: ");
        int a=input.nextInt();
        System.out.println("teh factorial of teh  number is : "+factorial(a));
    }
}
