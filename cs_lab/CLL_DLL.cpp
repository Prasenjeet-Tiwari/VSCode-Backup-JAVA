#include <bits/stdc++.h>
using namespace std;

// Node class
class Node {
public:
    int data;
    Node* next;

    Node(int data) {
        this->data = data;
        this->next = nullptr;
    }
};

// Linked List class
class LinkedList {
public:
    Node* head;

    //  Default constructor (start with empty list)
    LinkedList() {
        head = nullptr;
    }

    // Parameterized constructor (start with one node)
    LinkedList(int data) {
        head = new Node(data);
    }

    // Insert new node at beginning
    void insert(int data) {
        Node* temp = new Node(data);
        temp->next = head;
        head = temp;
    }

    // Delete node from beginning
    void remove() {
        if (head == nullptr) {
            cout << "Underflow" << endl;
            return;
        }
        Node* temp = head;
        head = head->next;
        delete temp;
    }

    // Print all nodes
    void print() {
        if (head == nullptr) {
            cout << "List is empty" << endl;
            return;
        }

        Node* temp = head;
        while (temp != nullptr) {
            cout << temp->data << " ";
            temp = temp->next;
        }
        cout << endl;
    }
};

// Main function
int main() {
    cout << "List 1 (empty constructor):" << endl;
    LinkedList ll1;        // uses default constructor
    ll1.insert(10);
    ll1.insert(20);
    ll1.insert(30);
    ll1.print();            // Output: 30 20 10

    ll1.remove();           // remove head (30)
    ll1.print();            // Output: 20 10


    cout << "\nList 2 (parameterized constructor):" << endl;
    LinkedList ll2(5);      // starts with node 5
    ll2.insert(15);
    ll2.insert(25);
    ll2.print();            // Output: 25 15 5
}