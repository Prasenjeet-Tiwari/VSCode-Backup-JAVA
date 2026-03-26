import java.util.*;

public class _2_raspberries {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int k = sc.nextInt();

            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }

            int ans = Integer.MAX_VALUE;

            if (k == 4) {
                int evenCount = 0;
                boolean divisibleBy4 = false;

                for (int x : a) {
                    if (x % 4 == 0) divisibleBy4 = true;
                    if (x % 2 == 0) evenCount++;
                    
                    int rem = x % 4;
                    if (rem == 0) ans = 0;
                    else ans = Math.min(ans, 4 - rem);  // ✅ Fixed formula
                }

                if (divisibleBy4 || evenCount >= 2) {
                    System.out.println(0);
                } else if (evenCount == 1) {
                    // Either make one divisible by 4, OR make one odd even (cost 1)
                    System.out.println(Math.min(ans, 1));
                } else {
                    // Either make one divisible by 4, OR make two odds even (cost 2)
                    System.out.println(Math.min(ans, 2));
                }
            } else {
                // k = 2, 3, or 5: just need one number divisible by k
                for (int x : a) {
                    int rem = x % k;
                    if (rem == 0) {
                        ans = 0;
                        break;
                    }
                    ans = Math.min(ans, k - rem);
                }
                System.out.println(ans);
            }
        }
    }
}