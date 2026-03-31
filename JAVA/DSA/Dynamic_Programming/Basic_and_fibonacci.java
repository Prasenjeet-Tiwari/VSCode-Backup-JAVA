class Basic_and_fibonacci {

    // 🔹 Recursive (Brute Force)
    // Time: O(2^n), Space: O(n)
    public int fiboRec(int n) {
        if (n <= 1) return n;
        return fiboRec(n - 1) + fiboRec(n - 2);
    }


    // 🔹 Bottom-Up DP (Tabulation)(small->big)
    // Iterative(for / while) approach (no recursion)
    // Builds solution from base cases → n
    // Time: O(n), Space: O(n)
    public int fiboTab(int n) {
        if (n <= 1) return n;

        int dp[] = new int[n + 1];

        // base cases
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }


    // 🔹 Top-Down DP (Memoization)(big->small)
    // Recursion + caching
    // Avoids recomputation
    // Time: O(n), Space: O(n)
    public int fiboMemo(int n, int dp[]) {
        if (n <= 1) return n;

        // already computed
        if (dp[n] != -1) return dp[n];

        dp[n] = fiboMemo(n - 1, dp) + fiboMemo(n - 2, dp);
        return dp[n];
    }


    public static void main(String[] args) {

        int n = 5;

        Basic_and_fibonacci fb = new Basic_and_fibonacci();

        // Recursive
        System.out.println("Recursive: " + fb.fiboRec(n));

        // Bottom-Up
        System.out.println("Tabulation: " + fb.fiboTab(n));

        // Top-Down
        int dp[] = new int[n + 1];
        java.util.Arrays.fill(dp, -1);

        System.out.println("Memoization: " + fb.fiboMemo(n, dp));
    }
}