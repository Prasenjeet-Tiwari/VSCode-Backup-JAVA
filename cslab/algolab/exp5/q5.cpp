#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

#define MAX 128

// ADD
void add(int A[MAX][MAX], int ar, int ac,
         int B[MAX][MAX], int br, int bc,
         int C[MAX][MAX], int cr, int cc,
         int n)
{
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            C[cr+i][cc+j] =
                A[ar+i][ac+j] +
                B[br+i][bc+j];
}

// SUBTRACT
void sub(int A[MAX][MAX], int ar, int ac,
         int B[MAX][MAX], int br, int bc,
         int C[MAX][MAX], int cr, int cc,
         int n)
{
    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++)
            C[cr+i][cc+j] =
                A[ar+i][ac+j] -
                B[br+i][bc+j];
}

// STRASSEN WITH Θ(1) DIVIDE
void strassen(int A[MAX][MAX], int ar, int ac,
              int B[MAX][MAX], int br, int bc,
              int C[MAX][MAX], int cr, int cc,
              int n)
{
    if(n==1){
        C[cr][cc] =
            A[ar][ac] * B[br][bc];
        return;
    }

    int k = n/2;

    int M1[MAX][MAX],M2[MAX][MAX],M3[MAX][MAX];
    int M4[MAX][MAX],M5[MAX][MAX],M6[MAX][MAX],M7[MAX][MAX];
    int T1[MAX][MAX],T2[MAX][MAX];

    // M1
    add(A,ar,ac,A,ar+k,ac+k,T1,0,0,k);
    add(B,br,bc,B,br+k,bc+k,T2,0,0,k);
    strassen(T1,0,0,T2,0,0,M1,0,0,k);

    // M2
    add(A,ar+k,ac,A,ar+k,ac+k,T1,0,0,k);
    strassen(T1,0,0,B,br,bc,M2,0,0,k);

    // M3
    sub(B,br,bc+k,B,br+k,bc+k,T2,0,0,k);
    strassen(A,ar,ac,T2,0,0,M3,0,0,k);

    // M4
    sub(B,br+k,bc,B,br,bc,T2,0,0,k);
    strassen(A,ar+k,ac+k,T2,0,0,M4,0,0,k);

    // M5
    add(A,ar,ac,A,ar,ac+k,T1,0,0,k);
    strassen(T1,0,0,B,br+k,bc+k,M5,0,0,k);

    // M6
    sub(A,ar+k,ac,A,ar,ac,T1,0,0,k);
    add(B,br,bc,B,br,bc+k,T2,0,0,k);
    strassen(T1,0,0,T2,0,0,M6,0,0,k);

    // M7
    sub(A,ar,ac+k,A,ar+k,ac+k,T1,0,0,k);
    add(B,br+k,bc,B,br+k,bc+k,T2,0,0,k);
    strassen(T1,0,0,T2,0,0,M7,0,0,k);

    // C11
    add(M1,0,0,M4,0,0,T1,0,0,k);
    sub(T1,0,0,M5,0,0,T2,0,0,k);
    add(T2,0,0,M7,0,0,C,cr,cc,k);

    // C12
    add(M3,0,0,M5,0,0,C,cr,cc+k,k);

    // C21
    add(M2,0,0,M4,0,0,C,cr+k,cc,k);

    // C22
    sub(M1,0,0,M2,0,0,T1,0,0,k);
    add(T1,0,0,M3,0,0,T2,0,0,k);
    add(T2,0,0,M6,0,0,C,cr+k,cc+k,k);
}

int main()
{
    int n = 128;

    int A[MAX][MAX],B[MAX][MAX],C[MAX][MAX]={0};

    srand(time(0));

    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++){
            A[i][j]=rand()%100;
            B[i][j]=rand()%100;
        }

    clock_t start = clock();

    strassen(A,0,0,B,0,0,C,0,0,n);

    clock_t end = clock();

    double time_taken =
        double(end-start)/CLOCKS_PER_SEC*1000;

    cout<<"Matrix Size : "<<n<<" x "<<n<<endl;
    cout<<"Execution Time : "<<time_taken<<" ms"<<endl;
}
