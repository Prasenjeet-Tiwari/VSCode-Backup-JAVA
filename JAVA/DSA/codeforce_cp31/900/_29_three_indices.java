import java.util.*;

public class _29_three_indices {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }

            boolean found = false;

            for (int j = 1; j < n - 1 && !found; j++) {

                int left = -1, right = -1;

                // find i < j such that arr[i] < arr[j]
                for (int i = 0; i < j; i++) {
                    if (arr[i] < arr[j]) {
                        left = i;
                        break;
                    }
                }

                // find k > j such that arr[k] < arr[j]
                for (int k = j + 1; k < n; k++) {
                    if (arr[k] < arr[j]) {
                        right = k;
                        break;
                    }
                }

                if (left != -1 && right != -1) {
                    System.out.println("YES");
                    System.out.println((left + 1) + " " + (j + 1) + " " + (right + 1));
                    found = true;
                }
            }

            if (!found) {
                System.out.println("NO");
            }
        }
        sc.close();
    }
}
