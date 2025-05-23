#include <stdio.h>
#include <stdlib.h>

// Define node structure
struct Node {
    int data;
    struct Node* next;
};

// Function to add a node at the beginning
void addNode(struct Node** head, int newData) {
    struct Node* newNode = (struct Node*)malloc(sizeof(struct Node));
    newNode->data = newData;
    newNode->next = *head;
    *head = newNode;
}

// Function to delete a node from the beginning
void deleteNode(struct Node** head) {
    if (*head == NULL) {
        printf("List is empty\n");
        return;
    }
    struct Node* temp = *head;
    *head = (*head)->next;
    free(temp);
}

void printList(struct Node* head) {
    struct Node* temp = head;
    while (temp != NULL) {
        printf("%d -> ", temp->data);
        temp = temp->next;
    }
    printf("NULL\n");
}

int main() {
    struct Node* head = NULL; // Empty list initially

    // Add nodes to the list
    addNode(&head, 10);
    addNode(&head, 20);
    addNode(&head, 30);
    printf("Linked list: ");
    printList(head);

    // Delete a node from the list
    deleteNode(&head);
    printf("After deletion: ");
    printList(head);

    return 0;
}
