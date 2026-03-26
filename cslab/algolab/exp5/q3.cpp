/*
 * Strassen's Matrix Multiplication Formulas
 * 
 * 7 Intermediate Products:
 * M1 = (A11 + A22) * (B11 + B22)
 * M2 = (A21 + A22) * B11
 * M3 = A11 * (B12 - B22)
 * M4 = A22 * (B21 - B11)
 * M5 = (A11 + A12) * B22
 * M6 = (A21 - A11) * (B11 + B12)
 * M7 = (A12 - A22) * (B21 + B22)
 * 
 * Final Matrix Elements:
 * C11 = M1 + M4 - M5 + M7
 * C12 = M3 + M5
 * C21 = M2 + M4
 * C22 = M1 - M2 + M3 + M6
 */
#include <bits/stdc++.h>
using namespace std;

#define MAX 128

void add(int A[MAX][MAX], int B[MAX][MAX],
         int C[MAX][MAX], int n){
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            C[i][j]=A[i][j]+B[i][j];
}

void sub(int A[MAX][MAX], int B[MAX][MAX],
         int C[MAX][MAX], int n){
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            C[i][j]=A[i][j]-B[i][j];
}

void strassen(int A[MAX][MAX], int B[MAX][MAX],
              int C[MAX][MAX], int n){

    if(n==1){
        C[0][0]=A[0][0]*B[0][0];
        return;
    }

    int k=n/2;

    int A11[MAX][MAX],A12[MAX][MAX],A21[MAX][MAX],A22[MAX][MAX];
    int B11[MAX][MAX],B12[MAX][MAX],B21[MAX][MAX],B22[MAX][MAX];

    // Θ(n²) divide step (copying)
    for(int i=0;i<k;i++){
        for(int j=0;j<k;j++){
            A11[i][j]=A[i][j];
            A12[i][j]=A[i][j+k];
            A21[i][j]=A[i+k][j];
            A22[i][j]=A[i+k][j+k];

            B11[i][j]=B[i][j];
            B12[i][j]=B[i][j+k];
            B21[i][j]=B[i+k][j];
            B22[i][j]=B[i+k][j+k];
        }
    }

    int M1[MAX][MAX],M2[MAX][MAX],M3[MAX][MAX],M4[MAX][MAX];
    int M5[MAX][MAX],M6[MAX][MAX],M7[MAX][MAX];
    int T1[MAX][MAX],T2[MAX][MAX];

    add(A11,A22,T1,k);
    add(B11,B22,T2,k);
    strassen(T1,T2,M1,k);

    add(A21,A22,T1,k);
    strassen(T1,B11,M2,k);

    sub(B12,B22,T2,k);
    strassen(A11,T2,M3,k);

    sub(B21,B11,T2,k);
    strassen(A22,T2,M4,k);

    add(A11,A12,T1,k);
    strassen(T1,B22,M5,k);

    sub(A21,A11,T1,k);
    add(B11,B12,T2,k);
    strassen(T1,T2,M6,k);

    sub(A12,A22,T1,k);
    add(B21,B22,T2,k);
    strassen(T1,T2,M7,k);

    int C11[MAX][MAX],C12[MAX][MAX],C21[MAX][MAX],C22[MAX][MAX];

    add(M1,M4,T1,k); sub(T1,M5,T2,k); add(T2,M7,C11,k);
    add(M3,M5,C12,k);
    add(M2,M4,C21,k);
    sub(M1,M2,T1,k); add(T1,M3,T2,k); add(T2,M6,C22,k);

    // Join
    for(int i=0;i<k;i++){
        for(int j=0;j<k;j++){
            C[i][j]=C11[i][j];
            C[i][j+k]=C12[i][j];
            C[i+k][j]=C21[i][j];
            C[i+k][j+k]=C22[i][j];
        }
    }
}

int main(){

    int n=128;

    int A[MAX][MAX],B[MAX][MAX],C[MAX][MAX];

    srand(time(0));

    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++){
            A[i][j]=rand()%100;
            B[i][j]=rand()%100;
        }

    clock_t start=clock();

    strassen(A,B,C,n);

    clock_t end=clock();

    double time_taken =
        double(end-start)/CLOCKS_PER_SEC*1000;

    cout<<"Matrix Size: "<<n<<" x "<<n<<endl;
    cout<<"Execution Time: "<<time_taken<<" ms"<<endl;
}
