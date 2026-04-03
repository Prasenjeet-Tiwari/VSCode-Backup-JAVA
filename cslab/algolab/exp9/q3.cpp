#include<bits/stdc++.h>
using namespace std;

int maxCoins(int arr[], int n){

    // Step 1: Create new array with boundaries (1 at both ends)
    vector<int> value(n + 2);

    value[0] = 1;           // left boundary
    value[n + 1] = 1;       // right boundary

    for(int i = 1; i <= n; i++){
        value[i] = arr[i - 1];
    }

    // Step 2: DP table
    // dp[i][j] = maximum coins from bursting balloons in range i to j
    int dp[n + 2][n + 2];

    // Initialize DP with 0
    for(int i = 0; i < n + 2; i++){
        for(int j = 0; j < n + 2; j++){
            dp[i][j] = 0;
        }
    }

    // Step 3: Fill DP table
    // len = length of current subarray
    for(int len = 1; len <= n; len++){

        // i = starting index of subarray
        for(int i = 1; i <= n - len + 1; i++){

            int j = i + len - 1;  // ending index

            // Try every balloon k as the LAST balloon to burst
            for(int k = i; k <= j; k++){

                // coins gained if k is last
                int coins = value[i - 1] * value[k] * value[j + 1];

                // add coins from left and right subproblems
                coins += dp[i][k - 1];   // left side
                coins += dp[k + 1][j];   // right side

                // take maximum
                dp[i][j] = max(dp[i][j], coins);
            }
        }
    }

    // Final answer: bursting all balloons from 1 to n
    return dp[1][n];
}

int main(){

    int arr[] = {3, 1, 5, 8};
    int n = sizeof(arr) / sizeof(arr[0]);

    cout << "Maximum coins: " << maxCoins(arr, n);

    return 0;
}