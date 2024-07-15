import java.util.*;

public class natural_number {

    public static int sum(int n){     // note here we defined the function as int and not void;
        if(n==1){                           
            return 1;
        }  
        int sm= sum(n-1);
        int output= n+sm;
        return output;
     } 
    public static void main(String[] args){
        Scanner input= new Scanner(System.in);
        System.out.print("Enter the number: ");
        int a=input.nextInt();
        System.out.println("The sum of the number is : "+sum(a));
    }
}
