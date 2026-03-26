#include <iostream>
using namespace std;

#define SIZE 3

// Function to input a matrix
void inputMatrix(int a[SIZE][SIZE]) {
    for(int i = 0; i < SIZE; i++)
        for(int j = 0; j < SIZE; j++)
            cin >> a[i][j];
}

// Function to check associativity of addition
bool checkAssociativeAddition(int A[SIZE][SIZE], int B[SIZE][SIZE], int C[SIZE][SIZE]) {
    int left[SIZE][SIZE], right[SIZE][SIZE];

    // left = (A + B) + C
    for(int i = 0; i < SIZE; i++)
        for(int j = 0; j < SIZE; j++)
            left[i][j] = A[i][j] + B[i][j] + C[i][j];

    // right = A + (B + C)
    for(int i = 0; i < SIZE; i++)
        for(int j = 0; j < SIZE; j++)
            right[i][j] = A[i][j] + B[i][j] + C[i][j];

    // Compare
    for(int i = 0; i < SIZE; i++)
        for(int j = 0; j < SIZE; j++)
            if(left[i][j] != right[i][j])
                return false;

    return true;
}

int main() {
    int A[SIZE][SIZE], B[SIZE][SIZE], C[SIZE][SIZE];

    cout << "Enter elements of Matrix A:\n";
    inputMatrix(A);
    cout << "Enter elements of Matrix B:\n";
    inputMatrix(B);
    cout << "Enter elements of Matrix C:\n";
    inputMatrix(C);

    if(checkAssociativeAddition(A, B, C))
        cout << "\nMatrix Addition is Associative.\n";
    else
        cout << "\nMatrix Addition is NOT Associative.\n";

    cout << "\nSubtraction is NEVER associative.\n";

    return 0;
}
