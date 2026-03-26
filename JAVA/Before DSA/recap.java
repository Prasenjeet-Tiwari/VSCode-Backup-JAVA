import java.util.*;
public class recap{
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the last number: ");
        int num=sc.nextInt();
        char k=65;
        Outerloop:
        for(int i=1; i<=num; i++){
            
            for(int j=0; j<i; j++)
            {
                System.out.print(k++);
                
            }
            System.out.println();
        }

        sc.close();
    }
}
