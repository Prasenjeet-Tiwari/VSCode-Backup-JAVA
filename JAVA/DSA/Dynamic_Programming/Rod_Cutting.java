class Rod_Cutting{

    public static int tabulation(int length[], int price[], int rodLength, int n){

        int dp[][]=new int[n+1][rodLength+1];

        for(int i=1; i<n+1; i++){
            for(int j=1; j<rodLength+1; j++){
                int len=length[i-1];
                int p=price[i-1];
                if(len<=j){
                    //since we can ag ain cut with same length so i not i-1
                    //similar to unbounded knapsack
                    int inc= p + dp[i][j-len];
                    int exc=dp[i-1][j];
                    dp[i][j]=Math.max(inc,exc);
                }else{
                    dp[i][j]=dp[i-1][j];
                }
            }
        }


        // (Optional) Print DP Table
        System.out.println("DP Table:");
        for (int i = 0; i < n+1; i++) {
            for (int j = 0; j < rodLength +1; j++) {
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[n][rodLength];

    }



    public static void main(String[] args) {
        
        int lenght[]={2,3,4,5,6,7,8};
        int price[]={5,8,9,10,17,17,20};

        int rodLength= 8;
        int n=price.length;

        System.out.println("Tabulation Rod Cutting: "+ tabulation(lenght, price, rodLength, n));

    }
}