#include <iostream>
using namespace std;

class Node {
public:
    int data;
    Node* next;
    Node(int val) : data(val), next(nullptr) {}
};

class CircularLinkedList {
    Node* head;
public:
    CircularLinkedList() : head(nullptr) {}

    void insertEnd(int val) {
        Node* newNode = new Node(val);
        if (!head) {
            head = newNode;
            head->next = head;
            return;
        }
        Node* temp = head;
        while (temp->next != head)
            temp = temp->next;
        temp->next = newNode;
        newNode->next = head;
    }

    void deleteNode(int val) {
        if (!head) return;
        Node *curr = head, *prev = nullptr;
        do {
            if (curr->data == val) break;
            prev = curr;
            curr = curr->next;
        } while (curr != head);

        if (curr->data != val) return; // Not found

        if (curr == head && curr->next == head) {
            delete curr;
            head = nullptr;
            return;
        }
        if (curr == head) {
            Node* temp = head;
            while (temp->next != head) temp = temp->next;
            head = head->next;
            temp->next = head;
            delete curr;
            return;
        }
        prev->next = curr->next;
        delete curr;
    }

    void display() {
        if (!head) {
            cout << "List is empty\n";
            return;
        }
        Node* temp = head;
        do {
            cout << temp->data << " ";
            temp = temp->next;
        } while (temp != head);
        cout << endl;
    }
};

int main() {
    CircularLinkedList cll;
    cll.insertEnd(10);
    cll.insertEnd(20);
    cll.insertEnd(30);
    cout << "CLL after insertion: ";
    cll.display();
    cll.deleteNode(20);
    cout << "CLL after deleting 20: ";
    cll.display();
    cll.insertEnd(40);
    cout << "CLL after inserting 40: ";
    cll.display();
    return 0;
}
