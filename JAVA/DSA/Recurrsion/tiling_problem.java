import java.util.Scanner;

// Number of ways to tile a 2 x n grid; size of each tile 2 x 1
/* Possible ways:  ------        or ||||
                   ------                                     */
public class tiling_problem {

    public static int tile(int n) {
        if (n == 0 || n == 1) { // since n=0 is also a case that is no tiles placed
            return 1;
        }
        int horizontal = tile(n - 1); // horizontal selection
        int vertical = tile(n - 2); // vertical selection
        int total = horizontal + vertical;
        return total;
    }

    public static void main(String[] args) {
        System.out.print("Enter the value of n for 2 x n: ");
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();

        System.out.println("The output is: " + tile(n));
    }
}
