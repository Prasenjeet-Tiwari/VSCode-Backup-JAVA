import java.util.*;

public class _14_button {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt(); // number of test cases

        while (t-- > 0) {

            int a = scanner.nextInt(); // ana
            int k = scanner.nextInt(); // katie
            int c = scanner.nextInt(); // common

            if(a-c == k-c){
                if(c%2!=0){
                    System.out.println("First");
                }else{
                    System.out.println("Second");
                }

            }else{
                if(a-c >k-c){
                    System.out.println("First");
                }else{
                    System.out.println("Second");

                }
            }
            
        }

        scanner.close();
    }
}
