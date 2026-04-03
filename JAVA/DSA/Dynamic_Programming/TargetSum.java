class TargetSum {

    public static boolean tabulation(int t, int arr[], int n) {
        // dp[i][j] = can we form sum j using first i elements
        boolean dp[][] = new boolean[n + 1][t + 1];

        // Base Case:
        // Sum = 0 is always possible (empty subset)
        for (int i = 0; i <n+1; i++) {
            dp[i][0] = true;
        }

        // Sum > 0 with 0 elements is NOT possible
        for (int j = 1; j <t+1; j++) {
            dp[0][j] = false;
        }

        // Fill DP table
        for (int i = 1; i <n+1; i++) {
            for (int j = 1; j <t+1; j++) {

                int num = arr[i - 1];

                // condition satisfied
                if (num <= j) {
                    //             exclude  ||  include
                    dp[i][j] = dp[i - 1][j] || dp[i - 1][j - num];
                }
                //condition nto satisfied
                else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        // (Optional) Print DP Table
        System.out.println("DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= t; j++) {
                System.out.print((dp[i][j] ? "T " : "F "));
            }
            System.out.println();
        }

        return dp[n][t];
    }

    public static void main(String[] args) {

        int number[] = {4, 2, 7, 1, 3};
        int t = 10;
        int n = number.length;

        boolean result = tabulation(t, number, n);

        System.out.println("\nSubset with sum " + t + " exists: " + result);
    }
}