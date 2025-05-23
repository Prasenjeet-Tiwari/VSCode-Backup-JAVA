#include<stdio.h>

int main() {
    // Variable declaration
    float input1, input2;  // Changed to float for decimal numbers
    char operator;

    // Taking input
    printf("Please input the value of input1: ");
    scanf("%f", &input1);
    printf("Please input the value of input2: ");
    scanf("%f", &input2);

    // Operation to perform
    printf("Enter the operation to perform (+, -, *, /): ");
    scanf(" %c", &operator);  // Added space before %c to skip whitespace

    switch (operator) {
        case '+':
            printf("input1 + input2: %.2f\n", input1 + input2);
            break;
        case '-':
            printf("input1 - input2: %.2f\n", input1 - input2);
            break;
        case '*':
            printf("input1 * input2: %.2f\n", input1 * input2);
            break;
        case '/':
            if (input2 != 0) {  // Check for division by zero
                printf("input1 / input2: %.2f\n", input1 / input2);
            } else {
                printf("Error! Division by zero is not allowed.\n");
            }
            break;
        default:
            printf("Error!! Invalid operator.\n");
            break;
    }

    return 0;
}