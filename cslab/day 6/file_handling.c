#include <stdio.h> 
#include <ctype.h>  // For isalpha, isdigit

// Function to read and print file content
void read_and_print_file(FILE *file) {
    char ch;
    while ((ch = fgetc(file)) != EOF) {
        putchar(ch);  // Print each character to console
    }
}

// Function to calculate the number of alphabets, digits, and special characters
void calculate_char_types(FILE *file, int *alphabets, int *digits, int *specials) {
    char ch;
    *alphabets = *digits = *specials = 0;

    // Move the file pointer to the beginning before reading
    fseek(file, 0, SEEK_SET);

    // Read the file and classify each character
    while ((ch = fgetc(file)) != EOF) {
        if (isalpha(ch)) {
            (*alphabets)++;
        } else if (isdigit(ch)) {
            (*digits)++;
        } else if (!isspace(ch)) {
            (*specials)++;
        }
    }
}

int main() {
    FILE *file;
    char filename[100];
    int alphabets, digits, specials;
    long position;
    char ch;

    // Ask user for the filename
    printf("Enter the filename to open: ");
    scanf("%s", filename);

    // Open file for reading
    file = fopen(filename, "r");
    if (file == NULL) {
        printf("Error opening file for reading.\n");
        return 1;
    }

    // Read and print file content
    printf("\n// File Content //\n");
    read_and_print_file(file);

    // Calculate number of alphabets, digits, and special characters
    calculate_char_types(file, &alphabets, &digits, &specials);
    printf("\n// Character Count //\n");
    printf("Alphabets: %d\n", alphabets);
    printf("Digits: %d\n", digits);
    printf("Special characters: %d\n", specials);

    // Move the file pointer using fseek and print a specific character
    printf("\nEnter the position (in bytes) to move to in the file: ");
    scanf("%ld", &position);

    fseek(file, position, SEEK_SET);  // Move to the given position
    ch = fgetc(file);
    if (ch != EOF) {
        printf("Character at position %ld is: %c\n", position, ch);
    } else {
        printf("Invalid position or end of file reached.\n");
    }

    // Append to the file
    char text_to_append[100];
    printf("\nEnter text to append to the file: ");
    getchar();  // Clear buffer from previous input
    fgets(text_to_append, sizeof(text_to_append), stdin);

    FILE *append_file = fopen(filename, "a");
    if (append_file == NULL) {
        printf("Error opening file for appending.\n");
        fclose(file);
        return 1;
    }
    fputs(text_to_append, append_file);
    printf("Text has been appended to the file.\n");

    // Close the files
    fclose(file);
    fclose(append_file);

    return 0;
}
