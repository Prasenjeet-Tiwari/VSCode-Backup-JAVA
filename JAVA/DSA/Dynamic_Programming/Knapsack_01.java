/*
    0/1 Knapsack Problem

    Types:
    1. Fractional Knapsack → can take fractions (Greedy)
    2. 0/1 Knapsack → either take item or not (DP)
    3. Unbounded Knapsack → can take item multiple times

    In this code, we solve 0/1 Knapsack using:
    - Recursion
    - Memoization (Top-Down DP)
    - Tabulation (Bottom-Up DP)
    - Space Optimized DP (1D array)
*/

class Knapsack_01 {

    // ===================== RECURSION =====================
    /*
        Time Complexity: O(2^n)
        Space Complexity: O(n) (recursion stack)

        Idea:
        For each item, we have 2 choices:
        1. Include it (if weight allows)
        2. Exclude it
    */
    public static int recursion(int wt[], int val[], int W, int n) {
        //Note: we use n-1 to retrieve value not direct n

        // Base Case:
        // If no items left OR capacity is 0 → profit = 0
        if (W == 0 || n == 0)
            return 0;

        // If current item's weight is less than or equal to capacity
        if (wt[n - 1] <= W) {

            // Include the item
            int include = val[n - 1] + recursion(wt, val, W - wt[n - 1], n - 1);

            // Exclude the item
            int exclude = recursion(wt, val, W, n - 1);

            // Return maximum of both choices
            return Math.max(include, exclude);
        } else {
            // Cannot include → only exclude
            return recursion(wt, val, W, n - 1);
        }
    }

    // ===================== MEMOIZATION =====================
    /*
        Time Complexity: O(n * W)
        Space Complexity: O(n * W)

        Idea:
        Store already computed states in dp[n][W]

        #### HERE N IS THE NUMBER OF OPTIONS (ROWS)
        #### AND W IS WEIGHT (COLUMN)
    */
    public static int memoization(int wt[], int val[], int W, int n, int dp[][]) {

        // Base Case
        if (W == 0 || n == 0)
            return 0;

        // If already computed → return stored value
        if (dp[n][W] != -1)
            return dp[n][W];

        if (wt[n - 1] <= W) {

            int include = val[n - 1] + memoization(wt, val, W - wt[n - 1], n - 1, dp);

            int exclude = memoization(wt, val, W, n - 1, dp);

            dp[n][W] = Math.max(include, exclude);
        } else {
            dp[n][W] = memoization(wt, val, W, n - 1, dp);
        }

        return dp[n][W];
    }

    // ===================== TABULATION =====================
    /*
        Time Complexity: O(n * W)
        Space Complexity: O(n * W)

        dp[i][j] =
        Maximum profit using first i items with capacity j

        Transition:
        if weight <= capacity:
            dp[i][j] = max(
                value + dp[i-1][j - weight],   // include
                dp[i-1][j]                    // exclude
            )
        else:
            dp[i][j] = dp[i-1][j]


        #### HERE N IS THE NUMBER OF OPTIONS (ROWS)
        #### AND W IS WEIGHT (COLUMN)
    */
    public static int tabulation(int wt[], int val[], int W, int n) {

        // DP table
        int dp[][] = new int[n + 1][W + 1];

        // Build table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= W; j++) {

                int value = val[i - 1];   // current item's value
                int weight = wt[i - 1];  // current item's weight

                if (weight <= j) {
                    // Include item
                    int include = value + dp[i - 1][j - weight];

                    // Exclude item
                    int exclude = dp[i - 1][j];

                    dp[i][j] = Math.max(include, exclude);
                } else {
                    // Cannot include
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][W];
    }

    // ===================== SPACE OPTIMIZED (1D DP) =====================
    /*
        Time Complexity: O(n * W)
        Space Complexity: O(W)

        IMPORTANT:
        Traverse capacity BACKWARDS to avoid overwriting values
    */
    public static int tabulation1D(int wt[], int val[], int W, int n) {

        int dp[] = new int[W + 1];

        for (int i = 0; i < n; i++) {
            for (int j = W; j >= wt[i]; j--) {

                dp[j] = Math.max(
                        dp[j],                      // exclude
                        val[i] + dp[j - wt[i]]      // include
                );
            }
        }

        return dp[W];
    }

    // ===================== MAIN FUNCTION =====================
    public static void main(String[] args) {

        int val[] = {15, 14, 10, 45, 30};
        int wt[] = {2, 5, 1, 3, 4};
        int W = 7;
        int n = wt.length;

        // -------- Recursion --------
        System.out.println("Recursion Output: "
                + recursion(wt, val, W, n));

        // -------- Memoization --------
        int dp[][] = new int[n + 1][W + 1];

        // Initialize dp with -1
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                dp[i][j] = -1;
            }
        }

        System.out.println("Memoization Output: "
                + memoization(wt, val, W, n, dp));

        // -------- Tabulation --------
        System.out.println("Tabulation Output: "
                + tabulation(wt, val, W, n));

        // -------- Space Optimized --------
        System.out.println("1D DP Output: "
                + tabulation1D(wt, val, W, n));
    }
}