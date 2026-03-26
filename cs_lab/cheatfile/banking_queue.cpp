#include <iostream>
using namespace std;

class Node {
public:
    int token;
    string name;
    Node* next;
    Node(int t, string n) {
        token = t;
        name = n;
        next = NULL;
    }
};

class BankQueue {
    Node* front;
    Node* rear;
public:
    BankQueue() {
        front = rear = NULL;
    }

    void enqueue(int token, string name) {
        Node* temp = new Node(token, name);
        if(rear == NULL) {
            front = rear = temp;
        } else {
            rear->next = temp;
            rear = temp;
        }
        cout << "Customer added: " << name << " (Token: " << token << ")\n";
    }

    void dequeue() {
        if(front == NULL) {
            cout << "No customers in queue.\n";
            return;
        }
        cout << "Serving Customer: " << front->name << " (Token: " << front->token << ")\n";
        Node* temp = front;
        front = front->next;
        delete temp;

        if(front == NULL) rear = NULL;
    }

    void display() {
        if(front == NULL) {
            cout << "Queue is empty.\n";
            return;
        }
        Node* temp = front;
        cout << "\nCurrent Queue: \n";
        while(temp != NULL) {
            cout << "Token: " << temp->token << " - Name: " << temp->name << endl;
            temp = temp->next;
        }
    }
};

int main() {
    BankQueue q;
    int choice, token;
    string name;

    while(true) {
        cout << "\n--- Bank Queue Menu ---\n";
        cout << "1. Add Customer\n2. Serve Customer\n3. View Queue\n4. Exit\nEnter choice: ";
        cin >> choice;

        switch(choice) {
            case 1:
                cout << "Enter token number and name: ";
                cin >> token >> name;
                q.enqueue(token, name);
                break;
            case 2:
                q.dequeue();
                break;
            case 3:
                q.display();
                break;
            case 4:
                return 0;
            default:
                cout << "Invalid choice!";
        }
    }
}
