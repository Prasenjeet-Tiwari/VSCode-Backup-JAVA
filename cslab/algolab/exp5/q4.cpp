#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

#define MAX 128

// add submatrices using offsets
void add(int A[MAX][MAX], int aRow, int aCol,
         int B[MAX][MAX], int bRow, int bCol,
         int C[MAX][MAX], int cRow, int cCol,
         int size)
{
    for(int i=0;i<size;i++)
        for(int j=0;j<size;j++)
            C[cRow+i][cCol+j] =
                A[aRow+i][aCol+j] +
                B[bRow+i][bCol+j];
}

// Divide & Conquer with Θ(1) divide
void multiply(int A[MAX][MAX], int aRow, int aCol,
              int B[MAX][MAX], int bRow, int bCol,
              int C[MAX][MAX], int cRow, int cCol,
              int size)
{
    // base case
    if(size==1){
        C[cRow][cCol] =
            A[aRow][aCol] * B[bRow][bCol];
        return;
    }

    int k = size/2;

    // Temporary matrices
    int T1[MAX][MAX], T2[MAX][MAX];

    // ---- C11 ----
    multiply(A,aRow,aCol,
             B,bRow,bCol,
             C,cRow,cCol,k);

    multiply(A,aRow,aCol+k,
             B,bRow+k,bCol,
             T1,0,0,k);

    add(C,cRow,cCol,T1,0,0,C,cRow,cCol,k);

    // ---- C12 ----
    multiply(A,aRow,aCol,
             B,bRow,bCol+k,
             C,cRow,cCol+k,k);

    multiply(A,aRow,aCol+k,
             B,bRow+k,bCol+k,
             T1,0,0,k);

    add(C,cRow,cCol+k,T1,0,0,C,cRow,cCol+k,k);

    // ---- C21 ----
    multiply(A,aRow+k,aCol,
             B,bRow,bCol,
             C,cRow+k,cCol,k);

    multiply(A,aRow+k,aCol+k,
             B,bRow+k,bCol,
             T1,0,0,k);

    add(C,cRow+k,cCol,T1,0,0,C,cRow+k,cCol,k);

    // ---- C22 ----
    multiply(A,aRow+k,aCol,
             B,bRow,bCol+k,
             C,cRow+k,cCol+k,k);

    multiply(A,aRow+k,aCol+k,
             B,bRow+k,bCol+k,
             T1,0,0,k);

    add(C,cRow+k,cCol+k,T1,0,0,
        C,cRow+k,cCol+k,k);
}

int main()
{
    int n = 128;

    int A[MAX][MAX], B[MAX][MAX], C[MAX][MAX]={0};

    srand(time(0));

    for(int i=0;i<n;i++)
        for(int j=0;j<n;j++){
            A[i][j]=rand()%100;
            B[i][j]=rand()%100;
        }

    clock_t start = clock();

    multiply(A,0,0,B,0,0,C,0,0,n);

    clock_t end = clock();

    double time_taken =
        double(end-start)/CLOCKS_PER_SEC*1000;

    cout<<"Matrix Size: "<<n<<" x "<<n<<endl;
    cout<<"Execution Time: "<<time_taken<<" ms"<<endl;
}
