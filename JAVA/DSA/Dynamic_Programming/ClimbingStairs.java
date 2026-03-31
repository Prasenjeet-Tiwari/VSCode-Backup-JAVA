class ClimbingStairs{

    // 🔹 Recursion (Brute Force)
    public static int stairsR(int n){
        if(n < 0) return 0;
        if(n == 0) return 1;

        return stairsR(n-1) + stairsR(n-2);
    }


    // 🔹 Bottom-Up DP (Tabulation)
    public static int stairsDP_Tabulation(int n, int dp[]){
        if(n <= 1) return 1;

        // base cases
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }


    // 🔹 Top-Down DP (Memoization)
    public static int stairsDP_Memo(int n, int dp[]){
        if(n < 0) return 0;
        if(n == 0) return 1;

        if(dp[n] != -1) return dp[n];

        dp[n] = stairsDP_Memo(n-1, dp) + stairsDP_Memo(n-2, dp);
        return dp[n];
    }


    public static void main(String[] args) {
        
        int n = 5;

        // recursion
        System.out.println("Recursion: " + stairsR(n));

        // tabulation
        int dp1[] = new int[n+1];
        System.out.println("Tabulation: " + stairsDP_Tabulation(n, dp1));

        // memoization
        int dp2[] = new int[n+1];
        java.util.Arrays.fill(dp2, -1);
        System.out.println("Memoization: " + stairsDP_Memo(n, dp2));
    }
}