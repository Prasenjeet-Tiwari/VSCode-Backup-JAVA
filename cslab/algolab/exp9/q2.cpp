#include<bits/stdc++.h>
using namespace std;

void printOptimal(int i, int j, vector<vector<int>> &bracket, char &name){

    if(i == j){
        cout << name++;
        return;
    }

    cout << "(";

    printOptimal(i, bracket[i][j], bracket, name);
    printOptimal(bracket[i][j] + 1, j, bracket, name);

    cout << ")";
}

void matrixChainOrder(int p[], int n){

    int dp[n][n];
    vector<vector<int>> bracket(n, vector<int>(n));

    for(int i=1; i<n; i++){
        dp[i][i] = 0;
    }

    for(int len = 2; len < n; len++){

        for(int i = 1; i < n - len + 1; i++){

            int j = i + len - 1;
            dp[i][j] = INT_MAX;

            for(int k = i; k < j; k++){

                int cost = dp[i][k] 
                         + dp[k+1][j] 
                         + p[i-1] * p[k] * p[j];

                if(cost < dp[i][j]){
                    dp[i][j] = cost;
                    bracket[i][j] = k;
                }
            }
        }
    }

    cout << "Minimum multiplications: " << dp[1][n-1] << endl;

    char name = 'A';
    cout << "Optimal Parenthesization: ";
    printOptimal(1, n-1, bracket, name);
}