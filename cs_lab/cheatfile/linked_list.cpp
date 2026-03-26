#include <iostream>
using namespace std;

class Node {
public:
    int data;
    Node* next;

    Node(int value) {
        data = value;
        next = nullptr;
    }
};

class LinkedList {
public:
    Node* head;

    LinkedList() {
        head = nullptr;
    }

    // Insert at beginning
    void insertBeginning(int value) {
        Node* newNode = new Node(value);
        newNode->next = head;
        head = newNode;
    }

    // Insert at end
    void insertEnd(int value) {
        Node* newNode = new Node(value);
        
        if (!head) {
            head = newNode;
            return;
        }

        Node* temp = head;
        while (temp->next != nullptr)
            temp = temp->next;
        
        temp->next = newNode;
    }

    // Insert at specific position
    void insertPosition(int value, int pos) {
        if (pos == 1) {
            insertBeginning(value);
            return;
        }

        Node* newNode = new Node(value);
        Node* temp = head;

        for (int i = 1; temp != nullptr && i < pos - 1; i++)
            temp = temp->next;

        if (!temp) {
            cout << "Position out of range!\n";
            return;
        }

        newNode->next = temp->next;
        temp->next = newNode;
    }

    // Delete from beginning
    void deleteBeginning() {
        if (!head) {
            cout << "List is empty!\n";
            return;
        }

        Node* temp = head;
        head = head->next;
        delete temp;
        cout << "Deleted from beginning.\n";
    }

    // Delete from end
    void deleteEnd() {
        if (!head) {
            cout << "List is empty!\n";
            return;
        }

        if (!head->next) {
            delete head;
            head = nullptr;
            cout << "Deleted last node.\n";
            return;
        }

        Node* temp = head;
        while (temp->next->next != nullptr)
            temp = temp->next;

        delete temp->next;
        temp->next = nullptr;
        cout << "Deleted from end.\n";
    }

    // Delete at position
    void deletePosition(int pos) {
        if (!head) {
            cout << "List is empty!\n";
            return;
        }

        if (pos == 1) {
            deleteBeginning();
            return;
        }

        Node* temp = head;
        for (int i = 1; temp != nullptr && i < pos - 1; i++)
            temp = temp->next;

        if (!temp || !temp->next) {
            cout << "Position out of range!\n";
            return;
        }

        Node* delNode = temp->next;
        temp->next = delNode->next;
        delete delNode;

        cout << "Node deleted from position " << pos << ".\n";
    }

    // Search element
    void search(int value) {
        Node* temp = head;
        int pos = 1;

        while (temp) {
            if (temp->data == value) {
                cout << "Element found at position " << pos << endl;
                return;
            }
            temp = temp->next;
            pos++;
        }

        cout << "Element not found!\n";
    }

    // Count nodes
    void countNodes() {
        Node* temp = head;
        int count = 0;

        while (temp) {
            count++;
            temp = temp->next;
        }

        cout << "Total nodes: " << count << endl;
    }

    // Reverse list
    void reverse() {
        Node *prev = nullptr, *curr = head, *next = nullptr;

        while (curr) {
            next = curr->next;
            curr->next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;
        cout << "List reversed.\n";
    }

    // Display list
    void display() {
        if (!head) {
            cout << "List is empty!\n";
            return;
        }

        Node* temp = head;
        cout << "Linked List: ";
        while (temp) {
            cout << temp->data << " -> ";
            temp = temp->next;
        }
        cout << "NULL\n";
    }
};


// ---------------- MAIN ----------------

int main() {
    LinkedList list;
    int choice, value, pos;

    while (true) {
        cout << "\n===== LINKED LIST MENU =====\n";
        cout << "1. Insert at Beginning\n";
        cout << "2. Insert at End\n";
        cout << "3. Insert at Position\n";
        cout << "4. Delete from Beginning\n";
        cout << "5. Delete from End\n";
        cout << "6. Delete at Position\n";
        cout << "7. Search Element\n";
        cout << "8. Count Nodes\n";
        cout << "9. Reverse List\n";
        cout << "10. Display List\n";
        cout << "11. Exit\n";
        cout << "Enter choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter value: ";
                cin >> value;
                list.insertBeginning(value);
                break;

            case 2:
                cout << "Enter value: ";
                cin >> value;
                list.insertEnd(value);
                break;

            case 3:
                cout << "Enter value and position: ";
                cin >> value >> pos;
                list.insertPosition(value, pos);
                break;

            case 4:
                list.deleteBeginning();
                break;

            case 5:
                list.deleteEnd();
                break;

            case 6:
                cout << "Enter position to delete: ";
                cin >> pos;
                list.deletePosition(pos);
                break;

            case 7:
                cout << "Enter value to search: ";
                cin >> value;
                list.search(value);
                break;

            case 8:
                list.countNodes();
                break;

            case 9:
                list.reverse();
                break;

            case 10:
                list.display();
                break;

            case 11:
                cout << "Exiting program...\n";
                return 0;

            default:
                cout << "Invalid choice! Try again.\n";
        }
    }
}
