#include <iostream>
using namespace std;

class Queue {
    int arr[100];
    int front, rear;
public:
    Queue() {
        front = 0;
        rear = -1;
    }

    void enqueue(int x) {
        if(rear == 99) {
            cout << "Queue Overflow!" << endl;
            return;
        }
        arr[++rear] = x;
    }

    int dequeue() {
        if(front > rear) {
            cout << "Queue Underflow!" << endl;
            return -1;
        }
        return arr[front++];
    }

    void display() {
        if(front > rear) {
            cout << "Queue is Empty!" << endl;
            return;
        }
        cout << "Queue Elements: ";
        for(int i = front; i <= rear; i++)
            cout << arr[i] << " ";
        cout << endl;
    }

    int sumFirstTen() {
        int sum = 0;
        for(int i = front; i < front + 10; i++)
            sum += arr[i];
        return sum;
    }
};

int main() {
    Queue q;

    cout << "Enter 10 positive elements: ";
    for(int i = 0; i < 10; i++) {
        int x;
        cin >> x;
        q.enqueue(x);
    }

    int sum = q.sumFirstTen();
    cout << "Sum of first 10 elements = " << sum << endl;

    if(sum >= 30) {
        cout << "\nSum >= 30, removing first 5 elements...\n";
        for(int i = 0; i < 5; i++) q.dequeue();
        q.display();
    } 
    else {
        cout << "\nSum < 30, inserting 5 more elements...\n";
        cout << "Enter 5 more elements: ";
        for(int i = 0; i < 5; i++) {
            int x;
            cin >> x;
            q.enqueue(x);
        }

        sum = q.sumFirstTen();
        cout << "New sum: " << sum << endl;
    }

    return 0;
}
