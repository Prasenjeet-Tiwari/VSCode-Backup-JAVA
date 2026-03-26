import java.util.*;
public class fibbonacci {

    //Note:  1st fibo series number is not 0 but 1 and second is also 1 
    //       so if n=1 means fibo(1)= fibo(1st term)=1
    //       and n=2 means   fibo(2)= fibo(2nd term)=1

    public static int fibo(int n){
        if(n==0|| n==1){            
            return n;
        }
        int fib1= fibo(n-1);
        int fib2= fibo(n-2); 
        return fib1 + fib2;
    }
    public static void main(String[] args){
        System.out.print("Enter the nth number fiboonacci to find: ");
        Scanner input = new Scanner(System.in);
        int userint= input.nextInt();
        System.out.println("The ans is : "+ fibo(userint));
        input.close();
    }
}
