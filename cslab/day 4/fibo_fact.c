#include <stdio.h>

// Fibonacci function using recursion
int fibo(int n) {
    if (n == 0) {  // Adding base case for n == 0
        return 0;
    }
    if (n == 1 || n == 2) {
        return 1;
    }
    return fibo(n - 1) + fibo(n - 2);
}

// Factorial function using recursion
int fact(int n) {
    if (n == 0 || n == 1) {
        return 1;
    }
    return n * fact(n - 1);
}

int main() {
    int fi, fa;

    // User input for Fibonacci
    printf("Enter the nth term of Fibonacci to find: ");
    scanf("%d", &fi);

    // User input for factorial
    printf("Enter the number to calculate its factorial: ");
    scanf("%d", &fa);

    // Check if the input for Fibonacci and factorial are valid
    if (fi < 0 || fa < 0) {
        printf("Please enter non-negative integers only.\n");
        return 1;
    }

    // Calculate Fibonacci and factorial
    int output_fibo = fibo(fi);
    int output_fact = fact(fa);

    // Display results
    printf("The %dth term of the Fibonacci sequence is: %d\n", fi, output_fibo);
    printf("The factorial of %d is: %d\n", fa, output_fact);

    return 0;
}
