import java.util.*;

public class EvenLarger {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            long[] a = new long[n + 1]; // 1-based indexing
            for (int i = 1; i <= n; i++) {
                a[i] = sc.nextLong();
            }

            long sumEven = 0, sumOdd = a[1];
            long operations = 0;

            for (int i = 2; i <= n+1; i++) {
                if (i % 2 == 0) sumEven += a[i];
                else sumOdd += a[i];

                if (sumEven < sumOdd) {
                    long diff = sumOdd - sumEven;
                    operations += diff;
                    sumOdd -= diff; // always reduce odd contribution
                }
            }

            System.out.println(operations);
        }
        sc.close();
    }
}
