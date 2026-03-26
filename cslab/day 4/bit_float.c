#include <stdio.h>
#include <stdint.h>

// Function to convert a float to its bit representation
void floatToBits(float num) {
    uint32_t *ptr = (uint32_t *)&num; // Treat the float as a 32-bit integer
    printf("Bit representation: ");
    for (int i = 31; i >= 0; i--) {
        printf("%d", (*ptr >> i) & 1); // Extract and print each bit
    }
    printf("\n");
}

// Function to convert bit representation back to a float
float bitsToFloat(uint32_t bits) {
    float *ptr = (float *)&bits; // Treat the 32-bit integer as a float
    return *ptr;
}

int main() {
    float num;
    printf("Enter a float number: ");
    scanf("%f", &num);

    // Convert float to bits
    printf("Original number: %f\n", num);
    floatToBits(num);

    // Convert bits back to float
    uint32_t bits = *((uint32_t *)&num); // Get the bit representation
    float convertedNum = bitsToFloat(bits);
    printf("Converted back to float: %f\n", convertedNum);

    return 0;
}
// #include <stdio.h>
// #include <math.h>

// // Function to convert the integer part of the float to binary
// void intToBinary(int num) {
//     if (num > 1)
//         intToBinary(num / 2);
//     printf("%d", num % 2);
// }

// // Function to convert the fractional part of the float to binary
// void fracToBinary(float num) {
//     int count = 0;
//     while (num > 0 && count < 8) { // Limit to 8 digits for simplicity
//         num *= 2;
//         int bit = (int)num;
//         printf("%d", bit);
//         num -= bit;
//         count++;
//     }
// }

// // Function to print float in binary
// void floatToBinary(float num) {
//     int intPart = (int)num;   // Extract integer part
//     float fracPart = num - intPart; // Extract fractional part

//     // Print integer part in binary
//     intToBinary(intPart);

//     printf(".");

//     // Print fractional part in binary
//     fracToBinary(fracPart);
//     printf("\n");
// }

// // Function to convert the float to hexadecimal
// void floatToHex(float num) {
//     printf("Hexadecimal: %a\n", num);
// }

// // Function to convert the float to octal
// void floatToOctal(float num) {
//     int intPart = (int)num;
//     printf("Octal: ");
//     printf("%o", intPart); // Print only integer part as octal
//     printf("\n");
// }

// int main() {
//     float num;

//     // Input float number
//     printf("Enter a floating point number: ");
//     scanf("%f", &num);

//     printf("Binary: ");
//     floatToBinary(num); // Convert to binary

//     floatToOctal(num);   // Convert to octal
//     floatToHex(num);     // Convert to hexadecimal

//     return 0;
// }


