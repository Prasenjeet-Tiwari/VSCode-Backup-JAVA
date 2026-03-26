#include <iostream>
using namespace std;

#define N 3

void input(int a[N][N]) {
    for(int i = 0; i < N; i++)
        for(int j = 0; j < N; j++)
            cin >> a[i][j];
}

void multiply(int A[N][N], int B[N][N], int result[N][N]) {
    for(int i = 0; i < N; i++)
        for(int j = 0; j < N; j++) {
            result[i][j] = 0;
            for(int k = 0; k < N; k++)
                result[i][j] += A[i][k] * B[k][j];
        }
}

bool equalMatrix(int A[N][N], int B[N][N]) {
    for(int i = 0; i < N; i++)
        for(int j = 0; j < N; j++)
            if(A[i][j] != B[i][j]) 
                return false;
    return true;
}

int main() {
    int A[N][N], B[N][N], C[N][N];
    int AB[N][N], BC[N][N], ABC1[N][N], ABC2[N][N], BA[N][N];

    cout << "Enter Matrix A:\n";
    input(A);
    cout << "Enter Matrix B:\n";
    input(B);
    cout << "Enter Matrix C:\n";
    input(C);

    multiply(A, B, AB);
    multiply(B, C, BC);
    multiply(AB, C, ABC1);
    multiply(A, BC, ABC2);

    if(equalMatrix(ABC1, ABC2))
        cout << "\nMatrix Multiplication is Associative.\n";
    else
        cout << "\nMatrix Multiplication is NOT Associative.\n";

    multiply(B, A, BA);

    if(equalMatrix(AB, BA))
        cout << "Matrix Multiplication is Commutative.\n";
    else
        cout << "Matrix Multiplication is NOT Commutative.\n";

    return 0;
}
