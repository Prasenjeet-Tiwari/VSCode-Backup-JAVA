import java.util.*;

public class Cheap_factorial {

    public static void factorial(int n, int sum){
        if(n<1){
            System.out.println("The factorial is 1");
            return;
        }
        else if(n==1){
            sum*=n;
            System.out.println("the faactorial of the number is : "+sum);
            return;
        }
        sum*=n;
        factorial(n-1,sum);
    }
    public static void main(String args []){
        System.out.print("Enter the number: ");
        Scanner input = new Scanner(System.in);
        int userinput=input.nextInt();
        int sum=1;
        factorial(userinput,sum);   
    }
}
