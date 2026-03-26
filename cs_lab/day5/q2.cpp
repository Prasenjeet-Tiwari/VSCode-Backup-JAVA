
#include <iostream>
#include <cstdlib>
using namespace std;

struct Node {
    int data;
    Node* next;
};

class Stack {
    Node* top;

public:
    Stack() {
        top = NULL;
    }

    // Push operation
    void push(int data) {
        Node* newNode = new Node();
        if (newNode == NULL) {
            cout << "No free memory" << endl;
            return;
        }
        newNode->data = data;
        newNode->next = top;
        top = newNode;
        cout << "Data entered successfully" << endl;
    }

    // Pop operation
    void pop() {
        if (top == NULL) {
            cout << "Empty stack" << endl;
            return;
        }
        Node* temp = top;
        int record = top->data;
        top = top->next;
        delete temp;
        cout << record << " has been popped" << endl;
    }

    // Peek (top element)
    void peek() {
        if (top == NULL) {
            cout << "Empty stack" << endl;
            return;
        }
        cout << "Top element: " << top->data << endl;
    }

    // Display all elements
    void display() {
        if (top == NULL) {
            cout << "Empty stack" << endl;
            return;
        }
        Node* temp = top;
        cout << "Stack elements: ";
        while (temp != NULL) {
            cout << temp->data << " -> ";
            temp = temp->next;
        }
        cout << "NULL" << endl;
    }
};

int main() {
    Stack s;
    int data, choice;

    while (true) {
        cout << "\nEnter the operation to perform" << endl;
        cout << "1. Push    2. Pop    3. Peek   4. Display    5. Exit" << endl;
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter data: ";
                cin >> data;
                s.push(data);
                break;
            case 2:
                s.pop();
                break;
            case 3:
                s.peek();
                break;
            case 4:
                s.display();
                break;
            case 5:
                exit(0);
            default:
                cout << "Invalid choice!" << endl;
        }
    }
    return 0;
}
