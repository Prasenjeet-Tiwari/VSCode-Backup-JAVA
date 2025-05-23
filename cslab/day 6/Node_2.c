#include <stdio.h>
#include <stdlib.h>

// Define the structure for the Node (for Stack)
struct Node {
    int data;
    struct Node* next;
};

// Function prototypes
void push(struct Node** top, int data);  // Add element to stack
void pop(struct Node** top);              // Remove top element from stack
int peek(struct Node* top);               // View top element of stack
int isEmpty(struct Node* top);            // Check if stack is empty
void printStack(struct Node* top);        // Print the stack

// Function to push an element onto the stack
void push(struct Node** top, int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = data;
    newNode->next = *top;  // Point new node to the current top
    *top = newNode;        // Update the top to the new node
    printf("Pushed %d onto stack.\n", data);
}

// Function to pop the top element from the stack
void pop(struct Node** top) {
    if (*top == NULL) {
        printf("Stack is empty. Cannot pop.\n");
        return;
    }
    
    struct Node* temp = *top;
    *top = (*top)->next;  // Update top to the next node
    printf("Popped %d from stack.\n", temp->data);
    free(temp);  // Free memory of the popped node
}

// Function to peek the top element of the stack
int peek(struct Node* top) {
    if (top == NULL) {
        printf("Stack is empty.\n");
        return -1;  // Return -1 to indicate the stack is empty
    }
    return top->data;
}

// Function to check if the stack is empty
int isEmpty(struct Node* top) {
    return top == NULL;
}

// Helper function to print the stack
void printStack(struct Node* top) {
    if (top == NULL) {
        printf("Stack is empty.\n");
        return;
    }
    
    struct Node* temp = top;
    printf("Stack: ");
    while (temp != NULL) {
        printf("%d ", temp->data);
        temp = temp->next;
    }
    printf("\n");
}

// Main function to test the stack operations
int main() {
    struct Node* stack = NULL;  // Initialize the stack as empty

    // Push elements onto the stack
    push(&stack, 10);
    push(&stack, 20);
    push(&stack, 30);
    push(&stack, 40);
    printStack(stack); // Output: Stack: 40 30 20 10

    // Peek the top element of the stack
    int top = peek(stack);
    printf("Top element is: %d\n", top); // Output: Top element is: 40

    // Pop elements from the stack
    pop(&stack); // Output: Popped 40 from stack.
    pop(&stack); // Output: Popped 30 from stack.
    printStack(stack); // Output: Stack: 20 10

    // Check if the stack is empty
    if (isEmpty(stack)) {
        printf("Stack is empty.\n");
    } else {
        printf("Stack is not empty.\n");
    }

    return 0;
}
