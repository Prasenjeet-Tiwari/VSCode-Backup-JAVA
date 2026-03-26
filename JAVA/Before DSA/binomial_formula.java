import java.util.Scanner;
class binomial_formula 
{
    public static long binomial(int a, int b) {
        long n = factorial(a);
        long r = factorial(b);
        long n_differ_r = factorial(a - b);

        long bino = n / (r * n_differ_r);
        return bino;
    }

    public static long factorial(int a) {
        long j = 1;
        if (a != 0) {
            for (int i = 1; i <= a; i++) {
                j *= i;
            }
        }
        return j;
    }
    public static void main(String args[])
    {
        Scanner sc= new Scanner(System.in);
        System.out.print("Enter the n of [n C r] of the binomial expansion: ");
        int x=sc.nextInt();
        System.out.print("Enter the  r of  [n C r] of the binomial expansion: ");
        int y=sc.nextInt();
        long result = binomial(x, y);
        System.out.println("The binomial expansion output is: "+result);
        sc.close();
    }

}
    

