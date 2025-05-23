#include <stdio.h>
#include <stdlib.h>
#include<string.h>

struct employee {
    int id;
    char name[50];
    char phone[50]; // Store phone number as a string
};

// Function to print employee details
void printing(struct employee emp) {
    printf("\nEmployee Details:\n");
    printf("ID: %d\n", emp.id);
    printf("Name: %s\n", emp.name);
    printf("Phone: %s\n", emp.phone);
}

void printxyz(struct employee *emp) {
    printf("\nEmployee Details:\n");
    printf("ID: %d\n", emp->id);
    printf("Name: %s\n", emp->name);
    printf("Phone: %s\n", emp->phone);
}

int main() {
    struct employee emp1, emp2;

    emp1.id=10;
    strcpy(emp1.name,"dhjd");
    strcpy(emp1.phone,"09876554");

    // Taking input for emp2
    printf("\nEnter the ID for Employee 2: ");
    scanf("%d", &emp2.id);
    printf("Enter the name for Employee 2: ");
    scanf(" %[^\n]", emp2.name);
    printf("Enter the phone number for Employee 2: ");
    scanf("%s", emp2.phone);

    /*#include <stdio.h>

// Define a structure for student information
struct Student {
    char name[100];
    int roll_no;
};

int main() {
    int n;

    // Ask the user for the number of students
    printf("Enter the number of students (max 100): ");
    scanf("%d", &n);

    // Validate the input
    if (n <= 0 || n > 100) {
        printf("Invalid number of students. Please enter a number between 1 and 100.\n");
        return 1;
    }

    // Declare an array of students
    struct Student students[100];

    // Input details for each student
    for (int i = 0; i < n; i++) {
        printf("\nEnter details for student %d:\n", i + 1);
        printf("Name: ");
        scanf(" %[^\n]", students[i].name); // Read string with spaces
        printf("Roll No: ");
        scanf("%d", &students[i].roll_no);
    }

    // Display the details of each student
    printf("\nStudent Details:\n");
    for (int i = 0; i < n; i++) {
        printf("Student %d:\n", i + 1);
        printf("Name: %s\n", students[i].name);
        printf("Roll No: %d\n", students[i].roll_no);
    }

    return 0;
}
*/

    // Printing the details
    printing(emp1);
    printing(emp2);

    printxyz(&emp1);
    printxyz(&emp2);

    return 0;
}
