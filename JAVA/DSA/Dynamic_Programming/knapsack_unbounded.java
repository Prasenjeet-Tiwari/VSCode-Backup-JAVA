

class knapsack_unbounded{

    public static int tabulation(int wt[],int val[], int W, int n){
        
        int dp[][]=new int[n+1][W+1];

        for(int i=1; i<n+1; i++){
            for(int j=1; j<W+1; j++){
                int value=val[i-1];
                int weight=wt[i-1];

                //condition satisfied
                if(weight<=j){

                    //include and exclude
                    
                    //note this timein include we use i
                    //and not i-1 cause this time we
                    //can reuse the current item too

                    int inc=value + dp[i][j-weight];
                    int exc= dp[i-1][j];

                    dp[i][j] = Math.max(inc, exc);

                }
                //condition not satified
                else{
                    dp[i][j] = dp[i-1][j];
                }
            }
        }


        // (Optional) Print DP Table
        System.out.println("DP Table:");
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= W; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[n][W];
    }

    public static void main(String[] args) {

        int val[] = {15, 14, 10, 45, 30};
        int wt[] = {2, 5, 1, 3, 4};
        int W = 7;
        int n = wt.length;

        System.out.println("Tabulation (unbounded Knapsack): "+tabulation(wt,val,W,n));
        
    }
}