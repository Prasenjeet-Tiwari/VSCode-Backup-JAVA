#include <stdio.h>
#include <stdlib.h>

int main() {
    // Using malloc to allocate memory for an integer
    int* ptr1 = (int*)malloc(sizeof(int));  // Allocates memory for 1 integer
    if (ptr1 == NULL) {
        printf("Memory allocation failed using malloc.\n");
        return 1;  // Exit if memory allocation fails
    }
    *ptr1 = 10;  // Assign a value to the allocated memory
    printf("Value allocated using malloc: %d\n", *ptr1);
    
    // Using calloc to allocate memory for an array of 5 integers
    int* ptr2 = (int*)calloc(5, sizeof(int));  // Allocates memory for an array of 5 integers, initialized to 0
    if (ptr2 == NULL) {
        printf("Memory allocation failed using calloc.\n");
        free(ptr1);  // Free previously allocated memory
        return 1;  // Exit if memory allocation fails
    }
    printf("Values allocated using calloc (initialized to 0): ");
    for (int i = 0; i < 5; i++) {
        printf("%d ", ptr2[i]);
    }
    printf("\n");

    // Using realloc to resize the memory block allocated by malloc or calloc
    ptr2 = (int*)realloc(ptr2, 10 * sizeof(int));  // Resize the array to hold 10 integers
    if (ptr2 == NULL) {
        printf("Memory reallocation failed using realloc.\n");
        free(ptr1);  // Free previously allocated memory
        return 1;  // Exit if reallocation fails
    }
    printf("Memory resized using realloc. New array values: ");
    for (int i = 0; i < 10; i++) {
        ptr2[i] = i + 1;  // Initialize new elements
        printf("%d ", ptr2[i]);
    }
    printf("\n");

    // Free the dynamically allocated memory
    free(ptr1);  // Free memory allocated by malloc
    free(ptr2);  // Free memory allocated by calloc/realloc

    printf("Memory has been freed successfully.\n");

    return 0;
}
