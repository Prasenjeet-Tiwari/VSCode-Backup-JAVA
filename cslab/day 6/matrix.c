#include <stdio.h>

// Fill matrix with user input
void fill_matrix(int r, int c, int arr[r][c]){
    for(int i = 0; i < r; i++){
        for(int j = 0; j < c; j++){
            printf("Enter the element at index value [%d][%d]: ", i+1, j+1);
            scanf("%d", &arr[i][j]);
        }
    }
    printf("\n");
}

// Print matrix
void print_matrix(int r, int c, int arr[r][c]){
    for(int i = 0; i < r; i++){
        for(int j = 0; j < c; j++){
            printf("%d ", arr[i][j]);
        }
        printf("\n");  // To print each row on a new line
    }
}

// Matrix addition
void addition(int r, int c, int arr2[r][c], int arr3[r][c], int arr4[r][c]){
    for(int i = 0; i < r; i++){
        for(int j = 0; j < c; j++){
            arr4[i][j] = arr2[i][j] + arr3[i][j];
        }
    }
}

// Matrix multiplication
void multiplication(int r1, int c1, int arr2[r1][c1], int r2, int c2, int arr3[r2][c2], int arr4[r1][c2]){
    for(int i = 0; i < r1; i++){
        for(int j = 0; j < c2; j++){
            arr4[i][j] = 0;  // Initialize result matrix element to 0
            for(int k = 0; k < c1; k++){
                arr4[i][j] += arr2[i][k] * arr3[k][j];
            }
        }
    }
}

// MAIN
int main(){

    // Dot product of two vectors
    int vec1[3], vec2[3];

    printf("// Enter values for vector 1 //\n");
    for(int i = 0; i < 3; i++){
        printf("Enter value for vec1[%d]: ", i+1);
        scanf("%d", &vec1[i]);
    }

    printf("\n// Enter values for vector 2 //\n");
    for(int i = 0; i < 3; i++){
        printf("Enter value for vec2[%d]: ", i+1);
        scanf("%d", &vec2[i]);
    }

    int dot_product = 0;
    for(int i = 0; i < 3; i++){
        dot_product += vec1[i] * vec2[i];
    }
    printf("Dot product of the two vectors: %d\n\n", dot_product);

    // Getting matrix dimensions for addition
    int r1, c1, r2, c2;
    printf("//For array number 1 for addition// \n");
    printf("Enter the rows number: ");
    scanf("%d", &r1);
    printf("Enter the columns number: ");
    scanf("%d", &c1);
    printf("\n");

    printf("//For array number 2 for addition// \n");
    printf("Enter the rows number: ");
    scanf("%d", &r2);
    printf("Enter the columns number: ");
    scanf("%d", &c2);
    printf("\n");

    int arr2[r1][c1];
    int arr3[r2][c2];

    fill_matrix(r1, c1, arr2);
    fill_matrix(r2, c2, arr3);

    // Matrix addition
    if(r1 == r2 && c1 == c2){
        int arr4[r1][c1];
        printf("Matrix addition is possible\n");
        addition(r1, c1, arr2, arr3, arr4);
        print_matrix(r1, c1, arr4);
    } else {
        printf("Matrix addition not possible due to dimension mismatch\n");
    }

    // Getting matrix dimensions for multiplication
    printf("//For MULTIPLICATION array number 1 // \n");
    printf("Enter the rows number: ");
    scanf("%d", &r1);
    printf("Enter the columns number: ");
    scanf("%d", &c1);
    printf("\n");

    printf("//For MULTIPLICATION array number 2 // \n");
    printf("Enter the rows number: ");
    scanf("%d", &r2);
    printf("Enter the columns number: ");
    scanf("%d", &c2);
    printf("\n");

    if(c1 == r2){
        printf("Matrix multiplication is possible\n");

        // Fill the matrices again for multiplication
        fill_matrix(r1, c1, arr2);
        fill_matrix(r2, c2, arr3);

        int arr5[r1][c2];  // Resultant matrix will have dimensions r1 x c2
        multiplication(r1, c1, arr2, r2, c2, arr3, arr5);

        printf("Result of matrix multiplication:\n");
        print_matrix(r1, c2, arr5);
    } else {
        printf("Matrix multiplication not possible due to dimension mismatch\n");
    }

    return 0;
}
