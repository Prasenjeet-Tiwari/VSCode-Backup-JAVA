#include <iostream>
using namespace std;

template <class T>
class Queue {
    T arr[100];
    int front, rear;

public:
    Queue() {
        front = 0;
        rear = -1;
    }

    void push(T x) {
        arr[++rear] = x;
    }

    void pop() {
        if(front > rear) cout << "Queue Empty\n";
        else front++;
    }

    void display() {
        for(int i = front; i <= rear; i++)
            cout << arr[i] << " ";
        cout << endl;
    }
};

// specialization for char
template <>
class Queue<char> {
    string arr[100];
    int front, rear;

public:
    Queue() {
        front = 0;
        rear = -1;
    }

    void push(string x) {
        arr[++rear] = x;
    }

    void pop() {
        if(front > rear) cout << "Queue Empty\n";
        else front++;
    }

    void display() {
        for(int i = front; i <= rear; i++)
            cout << arr[i] << " ";
        cout << endl;
    }
};

int main() {
    Queue<int> q1;
    q1.push(1);
    q1.push(2);
    q1.display();

    Queue<char> q2;
    q2.push("apple");
    q2.push("banana");
    q2.display();
}