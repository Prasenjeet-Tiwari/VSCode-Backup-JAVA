#include <iostream>
using namespace std;

class CircularQueue {
    int arr[5];
    int front, rear, size;
public:
    CircularQueue() {
        front = rear = -1;
        size = 5;
    }

    void enqueue(int x) {
        if((rear + 1) % size == front) {
            cout << "Queue Overflow!\n";
            return;
        }
        if(front == -1) front = 0;
        rear = (rear + 1) % size;
        arr[rear] = x;
        cout << x << " inserted\n";
    }

    void dequeue() {
        if(front == -1) {
            cout << "Queue Underflow!\n";
            return;
        }
        cout << arr[front] << " deleted\n";

        if(front == rear)
            front = rear = -1;
        else
            front = (front + 1) % size;
    }

    void display() {
        if(front == -1) {
            cout << "Queue empty\n";
            return;
        }
        cout << "Circular Queue: ";
        int i = front;
        while(true) {
            cout << arr[i] << " ";
            if(i == rear) break;
            i = (i + 1) % size;
        }
        cout << endl;
    }
};

int main() {
    CircularQueue q;
    int ch, val;

    while(true) {
        cout << "\n1.Enqueue\n2.Dequeue\n3.Display\n4.Exit\nChoose: ";
        cin >> ch;

        switch(ch) {
            case 1:
                cout << "Enter value: ";
                cin >> val;
                q.enqueue(val);
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
