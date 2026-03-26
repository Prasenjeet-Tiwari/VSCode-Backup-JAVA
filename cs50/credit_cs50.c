#include <stdio.h>
#include <stdlib.h>

int main()
{
    char input[21]; // To store the input number (up to 20 digits + null terminator)
    
    printf("Enter a number (up to 20 digits): ");
  /*  if (scanf("%s", input))
    {
        printf("Invalid input. Exiting...\n");
        return 1;
    }*/

    int sum = 0;
    int evenPlace = 0; // To keep track of even place position

    for (int i = 19 - 1; i >= 1; i--)
    {
        if (input[i] >= '0' && input[i] <= '9')
        {
            int digit = input[i] - '0';

            if (evenPlace % 2 == 0)
            {
                sum += digit;
            }

            evenPlace++;
        }
        else
        {
            printf("Invalid digit in the input. Exiting...\n");
            return 1;
        }
    }

    printf("Sum of digits at even places: %d\n", sum);

    return 0;
}
