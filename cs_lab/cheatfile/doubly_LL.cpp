#include <iostream>
using namespace std;

// Node class for DLL
class Node {
public:
    int data;
    Node* prev;
    Node* next;

    Node(int val){
        data = val;
        prev = nullptr;
        next = nullptr;
    }
};

// Doubly Linked List class
class DoublyLinkedList {
private:
    Node* head;
    Node* tail;

public:
    DoublyLinkedList(){
        head = nullptr;
        tail = nullptr;
    }

    // Insert at beginning
    void insertAtBeginning(int val){
        Node* newNode = new Node(val);
        if(head == nullptr){
            head = tail = newNode;
        } else {
            newNode->next = head;
            head->prev = newNode;
            head = newNode;
        }
        cout << "Inserted " << val << " at beginning.\n";
    }

    // Insert at end
    void insertAtEnd(int val){
        Node* newNode = new Node(val);
        if(tail == nullptr){
            head = tail = newNode;
        } else {
            tail->next = newNode;
            newNode->prev = tail;
            tail = newNode;
        }
        cout << "Inserted " << val << " at end.\n";
    }

    // Insert after a given value
    void insertAfter(int target, int val){
        Node* temp = head;
        while(temp != nullptr && temp->data != target){
            temp = temp->next;
        }
        if(temp == nullptr){
            cout << "Target " << target << " not found.\n";
            return;
        }
        Node* newNode = new Node(val);
        newNode->next = temp->next;
        newNode->prev = temp;

        if(temp->next != nullptr)
            temp->next->prev = newNode;
        else
            tail = newNode; // update tail if at end

        temp->next = newNode;
        cout << "Inserted " << val << " after " << target << ".\n";
    }

    // Delete by value
    void deleteByValue(int val){
        Node* temp = head;
        while(temp != nullptr && temp->data != val)
            temp = temp->next;

        if(temp == nullptr){
            cout << "Value " << val << " not found.\n";
            return;
        }

        if(temp->prev != nullptr)
            temp->prev->next = temp->next;
        else
            head = temp->next; // deleting head

        if(temp->next != nullptr)
            temp->next->prev = temp->prev;
        else
            tail = temp->prev; // deleting tail

        delete temp;
        cout << "Deleted " << val << ".\n";
    }

    // Search
    bool search(int val){
        Node* temp = head;
        while(temp != nullptr){
            if(temp->data == val) return true;
            temp = temp->next;
        }
        return false;
    }

    // Display forward
    void displayForward(){
        Node* temp = head;
        cout << "DLL Forward: ";
        while(temp != nullptr){
            cout << temp->data << " ";
            temp = temp->next;
        }
        cout << endl;
    }

    // Display backward
    void displayBackward(){
        Node* temp = tail;
        cout << "DLL Backward: ";
        while(temp != nullptr){
            cout << temp->data << " ";
            temp = temp->prev;
        }
        cout << endl;
    }

    // Count nodes
    int count(){
        int cnt = 0;
        Node* temp = head;
        while(temp != nullptr){
            cnt++;
            temp = temp->next;
        }
        return cnt;
    }
};

// -------------------- MAIN --------------------
int main(){
    DoublyLinkedList dll;
    int choice, val, target;

    while(true){
        cout << "\n====== DOUBLY LINKED LIST MENU ======";
        cout << "\n1. Insert at beginning";
        cout << "\n2. Insert at end";
        cout << "\n3. Insert after a value";
        cout << "\n4. Delete by value";
        cout << "\n5. Search";
        cout << "\n6. Display forward";
        cout << "\n7. Display backward";
        cout << "\n8. Count nodes";
        cout << "\n9. Exit";
        cout << "\nEnter your choice: ";
        cin >> choice;

        switch(choice){
            case 1:
                cout << "Enter value: ";
                cin >> val;
                dll.insertAtBeginning(val);
                break;
            case 2:
                cout << "Enter value: ";
                cin >> val;
                dll.insertAtEnd(val);
                break;
            case 3:
                cout << "Enter target value: ";
                cin >> target;
                cout << "Enter value to insert: ";
                cin >> val;
                dll.insertAfter(target, val);
                break;
            case 4:
                cout << "Enter value to delete: ";
                cin >> val;
                dll.deleteByValue(val);
                break;
            case 5:
                cout << "Enter value to search: ";
                cin >> val;
                if(dll.search(val))
                    cout << val << " FOUND in DLL.\n";
                else
                    cout << val << " NOT found in DLL.\n";
                break;
            case 6:
                dll.displayForward();
                break;
            case 7:
                dll.displayBackward();
                break;
            case 8:
                cout << "Total nodes: " << dll.count() << endl;
                break;
            case 9:
                cout << "Exiting...\n";
                return 0;
            default:
                cout << "Invalid choice! Try again.\n";
        }
    }
}
