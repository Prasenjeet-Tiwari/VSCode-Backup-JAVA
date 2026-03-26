#include <stdio.h>
#include <stdlib.h>

// Define the structure for the Node
struct Node {
    int data;
    struct Node* next;
};

// Function prototypes
void addNodeAtEnd(struct Node** head, int data);
void removeNode(struct Node** head, int data);
int findIndex(struct Node* head, int data);
void insertAtIndex(struct Node** head, int data, int index);
void printList(struct Node* head);

// Function to add a node at the end
void addNodeAtEnd(struct Node** head, int data) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    struct Node* last = *head;
    newNode->data = data;
    newNode->next = NULL;

    if (*head == NULL) {
        *head = newNode;
        return;
    }

    while (last->next != NULL) {
        last = last->next;
    }

    last->next = newNode;
}

// Function to remove a node by value
void removeNode(struct Node** head, int data) {
    struct Node* temp = *head;
    struct Node* prev = NULL;

    if (temp != NULL && temp->data == data) {
        *head = temp->next; // Move head to the next node
        free(temp);
        return;
    }

    while (temp != NULL && temp->data != data) {
        prev = temp;
        temp = temp->next;
    }

    if (temp == NULL) {
        printf("Node with data %d not found.\n", data);
        return;
    }

    prev->next = temp->next;
    free(temp);
}

// Function to find the index of a node
int findIndex(struct Node* head, int data) {
    struct Node* current = head;
    int index = 0;

    while (current != NULL) {
        if (current->data == data) {
            return index;
        }
        current = current->next;
        index++;
    }

    return -1; // Return -1 if the data is not found
}

// Function to insert a node at a specific index
void insertAtIndex(struct Node** head, int data, int index) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    struct Node* current = *head;
    int i = 0;

    newNode->data = data;
    newNode->next = NULL;

    if (index == 0) {
        newNode->next = *head;
        *head = newNode;
        return;
    }

    while (current != NULL && i < index - 1) {
        current = current->next;
        i++;
    }

    if (current == NULL) {
        printf("The previous node is NULL. Index out of range.\n");
        return;
    }

    newNode->next = current->next;
    current->next = newNode;
}

// Helper function to print the list
void printList(struct Node* head) {
    struct Node* temp = head;
    while (temp != NULL) {
        printf("%d -> ", temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}

// Main function to test the linked list operations
int main() {
    struct Node* head = NULL;

    // Add nodes to the list
    addNodeAtEnd(&head, 10);
    addNodeAtEnd(&head, 20);
    addNodeAtEnd(&head, 30);
    addNodeAtEnd(&head, 40);
    printList(head); // Output: 10 -> 20 -> 30 -> 40 -> NULL

    // Remove a node
    removeNode(&head, 20);
    printList(head); // Output: 10 -> 30 -> 40 -> NULL

    // Find index of a node
    int index = findIndex(head, 30);
    printf("Index of node with data 30: %d\n", index); // Output: 1

    // Insert at a specific index
    insertAtIndex(&head, 25, 1);
    printList(head); // Output: 10 -> 25 -> 30 -> 40 -> NULL

    return 0;
}
