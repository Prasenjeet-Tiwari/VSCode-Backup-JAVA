#include <stdio.h>

// Main function
int main() {
    
    // Declare variables
    int integer;
    float Float;
    char character;
    double Double;

    // user  input
    printf("You are requested to provide input accordingly : \n\n");

    // Integer
    printf("Enter the value of integer: ");
    scanf("%d", &integer);
    printf("You entered : %d\n", integer);
    printf("The memory address of the variable is : %p\n\n", (void*)&integer);

    // Float
    printf("Enter the value of float: ");
    scanf("%f", &Float);
    printf("You entered : %f\n", Float);
    printf("The memory address of the variable is : %p\n\n", (void*)&Float);

    // Char
    printf("Enter the value of char: ");
    scanf(" %c", &character);  
    printf("You entered : %c\n", character);
    printf("The memory address of the variable is : %p\n\n", (void*)&character);

    // Double
    printf("Enter the value of double: ");
    scanf("%lf", &Double);
    printf("You entered : %lf\n", Double);
    printf("The memory address of the variable is : %p\n\n", (void*)&Double);

    return 0;
}
