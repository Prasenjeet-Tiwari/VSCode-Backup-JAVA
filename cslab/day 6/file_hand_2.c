#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *file; // File pointer
    char buffer[100]; // Buffer to hold data read from file

    // 1. Writing to the file
    file = fopen("example.txt", "w"); // Open file in write mode
    if (file == NULL) {
        printf("Error opening file for writing.\n");
        return 1;
    }

    fprintf(file, "Hello, World!\n"); // Write to the file
    fprintf(file, "This is a file handling example.\n");
    fprintf(file, "File handling in C is powerful.\n");
    fclose(file); // Close the file after writing

    // 2. Reading from the file
    file = fopen("example.txt", "r"); // Open file in read mode
    if (file == NULL) {
        printf("Error opening file for reading.\n");
        return 1;
    }

    printf("Contents of the file:\n");
    while (fgets(buffer, sizeof(buffer), file) != NULL) { // Read line by line
        printf("%s", buffer); // Print each line
    }

    // 3. Using fseek to jump to a specific location
    printf("\nUsing fseek to jump to the middle of the file:\n");
    fseek(file, 0, SEEK_SET); // Reset file pointer to the start
    fseek(file, 20, SEEK_SET); // Move 20 bytes from the beginning
    if (fgets(buffer, sizeof(buffer), file) != NULL) { // Read from new position
        printf("Data after 20 bytes: %s", buffer);
    }

    fclose(file); // Close the file after reading

    return 0;
}
