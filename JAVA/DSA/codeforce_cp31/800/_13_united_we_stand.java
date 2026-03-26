import java.util.*;

public class _13_united_we_stand {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt(); // number of test cases

        while (t-- > 0) {

            int n = scanner.nextInt(); // length of array
            List<Long> a = new ArrayList<>();
            List<Long> b = new ArrayList<>();
            List<Long> c = new ArrayList<>();

            long max = Long.MIN_VALUE;

            for (int i = 0; i < n; i++) {
                long x = scanner.nextLong();
                a.add(x);
                max = Math.max(max, x);
            }

            // count how many times max appears
            int countMax = 0;
            for (long x : a) {
                if (x == max) countMax++;
            }

            // if all elements are equal → no valid split
            if (countMax == n) {
                System.out.println(-1);
                continue;
            }

            // split arrays
            for (long x : a) {
                if (x == max) {
                    c.add(x);
                } else {
                    b.add(x);
                }
            }

            // output
            System.out.println(b.size() + " " + c.size());

            for (long x : b) {
                System.out.print(x + " ");
            }
            System.out.println();

            for (long x : c) {
                System.out.print(x + " ");
            }
            System.out.println();
        }

        scanner.close();
    }
}
