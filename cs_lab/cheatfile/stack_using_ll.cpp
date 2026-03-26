#include <iostream>
using namespace std;

class Node {
public:
    int data;
    Node *next;
    Node(int x) : data(x), next(NULL) {}
};

class Stack {
    Node *top;
public:
    Stack() { top = NULL; }

    void push(int x) {
        Node *temp = new Node(x);
        temp->next = top;
        top = temp;
        cout << x << " pushed\n";
    }

    void pop() {
        if(top == NULL) {
            cout << "Stack Underflow\n";
            return;
        }
        cout << top->data << " popped\n";
        Node *temp = top;
        top = top->next;
        delete temp;
    }

    void peek() {
        if(!top) cout << "Stack is empty\n";
        else cout << "Top element: " << top->data << endl;
    }

    void display() {
        if(!top) {
            cout << "Stack is empty\n";
            return;
        }
        cout << "Stack elements: ";
        Node *temp = top;
        while(temp) {
            cout << temp->data << " ";
            temp = temp->next;
        }
        cout << endl;
    }
};

int main() {
    Stack st;
    st.push(5);
    st.push(15);
    st.push(25);
    st.display();
    st.peek();
    st.pop();
    st.display();
    return 0;
}
