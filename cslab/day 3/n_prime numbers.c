#include <stdio.h>

int is_prime(int num) {
    if (num <= 1) return 0; // 0 and 1 are not prime numbers
    for (int j = 2; j * j <= num; j++) { // Check divisibility up to the square root of num
        if (num % j == 0) return 0; // Not prime
    }
    return 1; // Prime
}

int main() {
    int n;
    printf("Enter the number of prime numbers to find (n): ");
    scanf("%d", &n);

    int count = 0; // Count of found prime numbersw
    int i = 2; // Starting from the first prime number

    while (count < n) {
        if (is_prime(i)) { // Check if i is prime
            printf("%d ", i); // Print the prime number
            count++; // Increment count of found primes
        }
        i++; // Move to the next number
    }

    printf("\n"); // Print newline at the end
    return 0;
}
