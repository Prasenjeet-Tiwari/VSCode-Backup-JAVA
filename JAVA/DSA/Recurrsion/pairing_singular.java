import java.util.*;

public class pairing_singular {
    
    public static int combination(int n ){
        if(n==2 || n==1){
            return n;
        }
        int solo= combination(n-1);
        int paired= combination(n-2);
        
        int output= solo + (n-1)*paired;     //i.e f(n)= f(n-1) + (n-1)*f(n-2)

        return output;
    }
    public static void main(String[] args){
        System.out.print("The total number of people present: ");
        Scanner input= new Scanner(System.in);
        int user= input.nextInt();
        System.out.println("the total number of combinations are : " + combination(user));
        
        input.close();
    }
}
