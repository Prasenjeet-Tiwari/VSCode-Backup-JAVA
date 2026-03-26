#include <stdio.h>

// Function to print a number if it is perfect
void printIfPerfect(int sum, int num) {
    if (sum == num) {
        printf("%d is a perfect number.\n", num);
    }
}

// Function to find and print all perfect numbers up to n
void findPerfectNumbers(int n) {
    for (int i = 1; i <= n; i++) {
        int sum = 0;

        // Calculate sum of proper divisors of i
        for (int j = 1; j < i; j++) {
            if (i % j == 0) {
                sum += j;
            }
        }

        // Check if the number is perfect
        printIfPerfect(sum, i);
    }
}

int main() {
    int n;

    // Input upper limit
    printf("Enter the value of n: ");
    scanf("%d", &n);

    // Find and print perfect numbers up to n
    findPerfectNumbers(n);

    return 0;
}
