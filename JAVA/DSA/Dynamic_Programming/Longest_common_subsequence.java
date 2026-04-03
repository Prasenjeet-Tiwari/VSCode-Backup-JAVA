class Longest_common_subsequence {

    /*My bad actually we need to return the length of max substring
    I returned the substring itself inthe recursion(Function)
    NVM in tabulation(Function) i will try to correct it */
    
    public static String recursion(String s1, String s2) {

        // base case
        if (s1.length() == 0 || s2.length() == 0)
            return "";

        // if last characters match
        if (s1.charAt(s1.length() - 1) == s2.charAt(s2.length() - 1)) {

            return recursion(s1.substring(0, s1.length() - 1), s2.substring(0, s2.length() - 1))  + s1.charAt(s1.length() - 1);

        } else {

            //if not same character at end then we have 2 oprions

            //either shrink s1 and not shrink s2
            String op1 = recursion(s1.substring(0, s1.length() - 1), s2);
            ////either shrink s2 and not shrink s1
            String op2 = recursion(s1, s2.substring(0, s2.length() - 1));

            // return longer one
            return (op1.length() > op2.length()) ? op1 : op2;
        }
    }

    // public  static int tabulation(String s1, String s2){

    //     int n=s1.length();    int m=s2.length();
    //     int dp[][]=new int[n+1][m+1];

    //     //n -> s1 ->rows
    //     //m -> s2 -> columns

    //     for (int i = 0; i < n+1; i++) {
    //         for (int j = 0; j < m+1; j++) {
    //             if(s1.charAt(i)==s2.charAt(j)){
    //                 //include
    //                 dp[i][j]=dp[i-1][j] + 1;
    //             }else{
    //                 int add=dp[i][j-1] + 1;
    //                 int del=dp [i-1][j] + 1;
    //                 int rep=dp [i-1][j-1] + 1;

    //                 dp[i][j]=Math.max(add , Math.max(del, rep));
    //             }
    //         }
    //     }

    //     // (Optional) Print DP Table
    //     System.out.println("DP Table:");
    //     for (int i = 0; i < n+1; i++) {
    //         for (int j = 0; j < m +1; j++) {
    //             System.out.print(dp[i][j] + " ");
    //         }
    //         System.out.println();
    //     }


    //     return dp[n][m];

    // }

    public static void main(String[] args) {

        String str1 = "abcde";
        String str2 = "ace";

        String result = recursion(str1, str2);
        System.out.println("LCS: " + result);
    }
}