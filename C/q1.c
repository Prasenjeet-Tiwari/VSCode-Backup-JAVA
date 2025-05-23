#include <stdio.h>

int main() {
    int integer;
    float Float;
    char character;
    double Double;

    printf("You are requested to provide input accordingly : \n\n");

    printf("Enter the value of integer: ");
    scanf("%d", &integer);
    printf("You entered : %d\n", integer);
    printf("The memory address of the variable is : %p\n\n", &integer);

    printf("Enter the value of float: ");
    scanf("%f", &Float);
    printf("You entered : %f\n", Float);
    printf("The memory address of the variable is : %p\n\n", &Float);

    printf("Enter the value of char: ");
    scanf(" %c", &character);
    printf("You entered : %c\n", character);
    printf("The memory address of the variable is : %p\n\n", &character);

    printf("Enter the value of double: ");
    scanf("%lf", &Double);
    printf("You entered : %lf\n", Double);
    printf("The memory address of the variable is : %p\n\n", &Double);

    return 0;
}