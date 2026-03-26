import java.util.*;

public class _6_doremy_paint {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- > 0) {
            int len = sc.nextInt();
            HashMap<Integer, Integer> hm = new HashMap<>();

            for (int i = 0; i < len; i++) {
                int x = sc.nextInt();
                hm.put(x, hm.getOrDefault(x, 0) + 1);
            }

            // Case 1: only one distinct element
            if (hm.size() == 1) {
                System.out.println("YES");
                continue;
            }

            // Case 2: more than two distinct elements
            if (hm.size() > 2) {
                System.out.println("NO");
                continue;
            }

            // Case 3: exactly two distinct elements
            int[] freq = new int[2];
            int idx = 0;
            for (int val : hm.values()) {
                freq[idx++] = val;
            }

            if (len % 2 == 0) {
                System.out.println(freq[0] == freq[1] ? "YES" : "NO");
            } else {
                System.out.println(Math.abs(freq[0] - freq[1]) == 1 ? "YES" : "NO");
            }
        }
    }
}
