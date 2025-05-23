#include <stdio.h>
#include <math.h> // Required for sqrt()

int main() {
    double a, b, c;
    double discriminant, root1, root2, realPart, imaginaryPart;

    // Input coefficients a, b, c
    printf("Enter coefficients a, b, and c: ");
    scanf("%lf %lf %lf", &a, &b, &c);

    // Check if it's a valid quadratic equation
    if (a == 0) {
        printf("This is not a quadratic equation.\n");
        return 0;
    }

    // Calculate the discriminant
    discriminant = b * b - 4 * a * c;

    // Determine the nature of roots
    if (discriminant > 0) {
        // Real and unequal roots
        root1 = (-b + sqrt(discriminant)) / (2 * a);
        root2 = (-b - sqrt(discriminant)) / (2 * a);
        printf("Roots are real and unequal.\n");
        printf("Root 1 = %.6lf\n", root1);
        printf("Root 2 = %.6lf\n", root2);
    } else if (discriminant == 0) {
        // Real and equal roots
        root1 = root2 = -b / (2 * a);
        printf("Roots are real and equal.\n");
        printf("Root 1 = Root 2 = %.6lf\n", root1);
    } else {
        // Complex/imaginary roots
        realPart = -b / (2 * a);
        imaginaryPart = sqrt(-discriminant) / (2 * a);
        printf("Roots are complex and imaginary.\n");
        printf("Root 1 = %.6lf + %.6lfi\n", realPart, imaginaryPart);
        printf("Root 2 = %.6lf - %.6lfi\n", realPart, imaginaryPart);
    }

    return 0;
}
