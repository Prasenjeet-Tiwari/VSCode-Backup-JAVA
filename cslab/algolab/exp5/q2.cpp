#include <bits/stdc++.h>
using namespace std;

#define MAX 128

// Add matrices
void add(int A[MAX][MAX], int B[MAX][MAX],
         int C[MAX][MAX], int size) {

    for(int i=0;i<size;i++)
        for(int j=0;j<size;j++)
            C[i][j] = A[i][j] + B[i][j];
}

// Divide & Conquer multiplication
void multiply(int A[MAX][MAX], int B[MAX][MAX],
              int C[MAX][MAX], int size) {

    // Base case
    if(size == 1){
        C[0][0] = A[0][0] * B[0][0];
        return;
    }

    int newSize = size/2;

    int A11[MAX][MAX], A12[MAX][MAX], A21[MAX][MAX], A22[MAX][MAX];
    int B11[MAX][MAX], B12[MAX][MAX], B21[MAX][MAX], B22[MAX][MAX];
    int C11[MAX][MAX], C12[MAX][MAX], C21[MAX][MAX], C22[MAX][MAX];
    int P1[MAX][MAX], P2[MAX][MAX];

    // Split matrices
    for(int i=0;i<newSize;i++){
        for(int j=0;j<newSize;j++){
            A11[i][j]=A[i][j];
            A12[i][j]=A[i][j+newSize];
            A21[i][j]=A[i+newSize][j];
            A22[i][j]=A[i+newSize][j+newSize];

            B11[i][j]=B[i][j];
            B12[i][j]=B[i][j+newSize];
            B21[i][j]=B[i+newSize][j];
            B22[i][j]=B[i+newSize][j+newSize];
        }
    }

    // C11 = A11B11 + A12B21
    multiply(A11,B11,P1,newSize);
    multiply(A12,B21,P2,newSize);
    add(P1,P2,C11,newSize);

    // C12
    multiply(A11,B12,P1,newSize);
    multiply(A12,B22,P2,newSize);
    add(P1,P2,C12,newSize);

    // C21
    multiply(A21,B11,P1,newSize);
    multiply(A22,B21,P2,newSize);
    add(P1,P2,C21,newSize);

    // C22
    multiply(A21,B12,P1,newSize);
    multiply(A22,B22,P2,newSize);
    add(P1,P2,C22,newSize);

    // Join matrices
    for(int i=0;i<newSize;i++){
        for(int j=0;j<newSize;j++){
            C[i][j]=C11[i][j];
            C[i][j+newSize]=C12[i][j];
            C[i+newSize][j]=C21[i][j];
            C[i+newSize][j+newSize]=C22[i][j];
        }
    }
}

int main() {

    int n = 128;   // must be power of 2

    int A[MAX][MAX], B[MAX][MAX], C[MAX][MAX];

    srand(time(0));

    // Random matrices (<100)
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++){
            A[i][j] = rand()%100;
            B[i][j] = rand()%100;
        }

    clock_t start = clock();

    multiply(A,B,C,n);

    clock_t end = clock();

    double time_taken =
        double(end-start)/CLOCKS_PER_SEC*1000;

    cout<<"Matrix Size : "<<n<<" x "<<n<<endl;
    cout<<"Execution Time : "<<time_taken<<" ms"<<endl;

    return 0;
}
