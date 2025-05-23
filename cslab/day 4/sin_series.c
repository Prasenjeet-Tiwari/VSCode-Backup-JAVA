#include <stdio.h>
#include <math.h>

// Factorial function
long long factorial(int n) {
    if (n == 1 || n == 0) {
        return 1;
    }
    return n * factorial(n - 1);
}

// Sin series function
double sin_series(int n, double x, double pow_x, int fact, double sum) {
    if (n == 1) {
        return sum;
    }
    sum += pow_x / factorial(2 * fact + 1);
    pow_x = (-1) * x * x;
    fact++;
    n--;
    return sin_series(n, x, pow_x, fact, sum);
}

int main() {
    int n; double x;

    // User input
    printf("Enter the number of terms: ");
    scanf("%d", &n);
    printf("Enter the value of x (in radians): ");
    scanf("%lf", &x);


    // Calculate sin(x) using Taylor series
    double result = sin_series(n, x, x, 0, 0);
    printf("The approximate value of sin(%lf) is: %lf\n", x, result);

    return 0;
}