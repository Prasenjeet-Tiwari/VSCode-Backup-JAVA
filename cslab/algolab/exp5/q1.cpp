#include <bits/stdc++.h>
using namespace std;

#define MAX 128   // maximum size allowed

int main() {

    int n = 128;   // change size here for testing

    int A[MAX][MAX], B[MAX][MAX], C[MAX][MAX];

    // Random seed
    srand(time(0));

    // Generate random matrices (values < 100)
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            A[i][j] = rand() % 100;
            B[i][j] = rand() % 100;
        }
    }

    // ---- Start timing ----
    clock_t start = clock();

    // Straightforward multiplication
    for(int i=0;i<n;i++){
        for(int j=0;j<n;j++){
            C[i][j] = 0;
            for(int k=0;k<n;k++){
                C[i][j] += A[i][k] * B[k][j];
            }
        }
    }

    // ---- End timing ----
    clock_t end = clock();

    double time_taken =
        double(end - start) / CLOCKS_PER_SEC * 1000;

    cout << "Matrix Size : " << n << " x " << n << endl;
    cout << "Execution Time : "
         << time_taken << " ms" << endl;

    return 0;
}
