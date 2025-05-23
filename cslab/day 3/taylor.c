#include <Stdio.h>
//to calculate the taylor series expansion of e^x =1+ x + (x^2)/2! + (x^3)/3! + ......

// Function to calculate the factorial of each term
int factorial(int n) {
    int fact = 1;
    for (int i = 1; i <= n; i++) {
        fact *= i;
    }
    return fact;
}

// Function to calculate Taylor series value for a given value of x and n
double taylor(int x, int n) {
    double sum = 1;
    int count = 1;
    double pow_x = x;
    while (count < n) {
        int fac = factorial(count);
        sum = sum + (pow_x) / fac;
        count++;
        pow_x = pow_x * x;
    }
    return sum;
}

// Main function
int main() {
    int n;
    double x;

    printf("Enter the number of terms (n): ");
    scanf("%d", &n);

    printf("Enter the value of x: ");
    scanf("%lf", &x);

    double output = taylor(x, n);
    printf("The output is: %lf\n", output);

    return 0;
}