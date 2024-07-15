#include <stdio.h>

int main() 
{
    int height;

    do 
    {
        printf("Enter the Height: ");
        scanf("%d", &height);
    } 
    while (height < 1);

    for (int i = 1; i <= height; i++) 
    {
        // Print spaces on the left side
        for (int j = 0; j < height - i; j++)
        {
            printf(" ");
        }
        
        // Print left hashes
        for (int j = 0; j < i; j++)
        {
            printf("#");
        }
        
        // Print gap
        printf("  ");
        
        // Print right hashes
        for (int j = 0; j < i; j++) 
        {
            printf("#");
        }
        
        // Move to the next line
        printf("\n");
    }

    return 0;
}