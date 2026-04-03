#include<bits/stdc++.h>
using namespace std;

void printOptimal(int i, int j, int bracket[][100], char &name){

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

    int dp[100][100];          // fixed size array
    int bracket[100][100];     // to store split points

    for(int i=1; i<n; i++){
        dp[i][i] = 0;
    }

    for(int len = 2; len < n; len++){

        for(int i = 1; i < n - len + 1; i++){

            int j = i + len - 1;
            dp[i][j] = INT_MAX;

            for(int k = i; k < j; k++){

                int cost = dp[i][k] + dp[k+1][j] + p[i-1] * p[k] * p[j];

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


// Optional separate function (unchanged logic)
int matrixChain(int p[], int n){

    // dp[i][j] = minimum cost to multiply matrices from i to j
    int dp[100][100];

    // cost is 0 when multiplying one matrix
    for(int i=1; i<n; i++){
        dp[i][i] = 0;
    }

    // len = chain length
    for(int len = 2; len < n; len++){

        for(int i = 1; i < n - len + 1; i++){

            int j = i + len - 1;
            dp[i][j] = INT_MAX;

            // try every partition k
            for(int k = i; k < j; k++){
                //optimal multiplacation cost of
                //cost of:  left side + right side + current partition
                int cost = dp[i][k] + dp[k+1][j]+ p[i-1] * p[k] * p[j];

                dp[i][j] = min(dp[i][j], cost);
            }
        }
    }

    return dp[1][n-1];
}

int main(){

    // Test Case 1
    int p1[] = {10,100,20,5,80};
    matrixChainOrder(p1, 5);

    cout << "\n\n";

    // Test Case 2
    int p2[] = {4,3,6,3,1,7};
    matrixChainOrder(p2, 6);

    cout << "\n\n";

    // Test Case 3
    int p3[] = {30,35,15,5,10,20,25};
    matrixChainOrder(p3, 7);

    return 0;
}