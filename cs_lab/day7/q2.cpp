#include <iostream>
using namespace std;

class Node {
public:
    int data;
    Node *prev, *next;
    Node(int val) : data(val), prev(nullptr), next(nullptr) {}
};

class DoublyLinkedList {
    Node* head;
public:
    DoublyLinkedList() : head(nullptr) {}

    void insertEnd(int val) {
        Node* n = new Node(val);
        if (!head) { head = n; return; }
        Node* temp = head;
        while (temp->next) temp = temp->next;
        temp->next = n;
        n->prev = temp;
    }

    void deleteNode(int val) {
        Node* temp = head;
        while (temp && temp->data != val) temp = temp->next;
        if (!temp) return;
        if (temp->prev) temp->prev->next = temp->next;
        else head = temp->next;
        if (temp->next) temp->next->prev = temp->prev;
        delete temp;
    }

    void display() {
        Node* t = head;
        while (t) {
            cout << t->data << " ";
            t = t->next;
        }
        cout << endl;
    }
};

int main() {
    DoublyLinkedList dll;
    dll.insertEnd(1);
    dll.insertEnd(2);
    dll.insertEnd(3);
    cout << "DLL after insertion: ";
    dll.display();
    dll.deleteNode(2);
    cout << "DLL after deleting 2: ";
    dll.display();
    dll.insertEnd(4);
    cout << "DLL after inserting 4: ";
    dll.display();
    return 0;
}
