/*2. Determine
a. Real and equal roots
b. Real and inequal roots
c. Complex/imaginary roots
Of a quadratic equation of the form a*x^2 + b*x + c = 0*/

#include <stdio.h>
#include <math.h>

// Function to calculate complex roots
void complex_root(int a, int b, int c) {
    double real = -b / (2.0 * a);
    double imag = sqrt(-1 * (b * b - 4 * a * c)) / (2.0 * a);
    printf("Complex roots: %.2f + %.2fi and %.2f - %.2fi\n", real, imag, real, imag);
}

int main() {

    //variable declaration
    int a, b, c;
    double root1, root2;

    // Getting input
    printf("Enter the value of a: ");
    scanf("%d", &a);
    printf("Enter the value of b: ");
    scanf("%d", &b);
    printf("Enter the value of c: ");
    scanf("%d", &c);

    // Checking nature of roots
    int D = (b*b) - (4*a*c);

    //real unequal roots
    if (D > 0) {
        printf("Real and unequal roots\n");
        root1 = (-b - sqrt(D)) / (2.0 * a);
        root2 = (-b + sqrt(D)) / (2.0 * a);
        printf("Roots: %.2f and %.2f\n", root1, root2);

    //real equal roots
    } else if (D == 0) {
        printf("Real and equal roots\n");
        root1 = root2 = -b / (2.0 * a);
        printf("Root: %.2f\n", root1);

    //Complex/imaginary roots
    } else if (D < 0) {
        printf("Complex/imaginary roots\n");
        complex_root(a, b, c);
    }

    return 0;
}
