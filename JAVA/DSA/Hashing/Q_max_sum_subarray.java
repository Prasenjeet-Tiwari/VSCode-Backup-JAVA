import java.util.HashMap;

public class Q_max_sum_subarray {

    public static void main(String args[]) {

        int arr[] = {10, 2, -2, -20, 10};

        // --------------------------
        // Count subarrays with sum = 0
        // --------------------------
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0, ans = 0;

        // Initialize with 0 sum seen once (important for cases like prefix itself = 0)
        map.put(0, 1);

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];

            // If this sum was seen before → subarray from previous index+1 to i has sum 0
            if (map.containsKey(sum)) {
                ans += map.get(sum);
            }

            // Update the frequency of this sum
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        System.out.println("Number of subarrays with sum = 0 : " + ans);

        // --------------------------
        // Count subarrays with sum = k
        // --------------------------
        int k = -10;
        int[] array = {10, 2, -2, -20, 10};

        map.clear();
        sum = 0;
        ans = 0;

        // Again, initialize with 0 sum seen once
        map.put(0, 1);

        for (int i = 0; i < array.length; i++) {
            sum += array[i];

            // Check if (sum - k) exists → means there is a subarray ending at i with sum k
            if (map.containsKey(sum - k)) {
                ans += map.get(sum - k);
            }

            // Update frequency of current sum **AND NOT (SUM-K)
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        System.out.println("Number of subarrays with sum = " + k + " : " + ans);
    }
}
