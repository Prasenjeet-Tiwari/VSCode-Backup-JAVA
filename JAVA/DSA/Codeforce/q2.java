import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            int[] S = new int[n];
            int[] T = new int[n];

            for (int i = 0; i < n; i++) S[i] = sc.nextInt();
            for (int i = 0; i < n; i++) T[i] = sc.nextInt();

            Arrays.sort(S);
            Arrays.sort(T);

            boolean possible = true;
            for (int i = 0; i < n; i++) {
                if (S[i] % k != T[i] % k) {
                    possible = false;
                    break;
                }
            }

            System.out.println(possible ? "YES" : "NO");
        }
        sc.close();
    }
}
