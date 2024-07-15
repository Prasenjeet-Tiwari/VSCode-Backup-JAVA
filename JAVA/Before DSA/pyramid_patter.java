import java.util.*;
    public class pyramid_patter {
        public static void main( String[] args){
            Scanner sc= new Scanner(System.in);
            System.out.print("Input the number of lines that you wish to get the pattern: ");
            int rangenum = sc.nextInt();
            System.out.println();
            for(int i=1; i<=rangenum; i++)
            {
                int space=rangenum-i;
                for(int k=1;k<=space;k++)
                {
                    System.out.print(' ');
                }
                for(int j=1; j<=i; j++)
                {
                    System.out.print("* ");                 
                }
                System.out.println();
                
            }
            sc.close();
         
    }
}
