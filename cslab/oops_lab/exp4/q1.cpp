#include <iostream>
using namespace std;

template <class T>
class Stack {
    T arr[100];
    int top;

public:
    Stack() { top = -1; }

    void push(T x) {
        arr[++top] = x;
    }

    void pop() {
        if(top == -1) cout << "Stack Empty\n";
        else top--;
    }

    void display() {
        for(int i = top; i >= 0; i--)
            cout << arr[i] << " ";
        cout << endl;
    }
};

// specialization for char
template <>
class Stack<char> {
    string arr[100];
    int top;

public:
    Stack() { top = -1; }

    void push(string x) {
        arr[++top] = x;
    }

    void pop() {
        if(top == -1) cout << "Stack Empty\n";
        else top--;
    }

    void display() {
        for(int i = top; i >= 0; i--)
            cout << arr[i] << " ";
        cout << endl;
    }
};

int main() {
    Stack<int> s1;
    s1.push(10);
    s1.push(20);
    s1.display();

    Stack<char> s2;
    s2.push("hello");
    s2.push("world");
    s2.display();
}