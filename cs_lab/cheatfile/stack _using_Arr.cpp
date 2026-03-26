#include <iostream>
using namespace std;

#define MAX 100

class Stack {
    int arr[MAX], top;
public:
    Stack() { top = -1; }

    void push(int x) {
        if(top == MAX-1) {
            cout << "Stack Overflow\n";
            return;
        }
        arr[++top] = x;
        cout << x << " pushed\n";
    }

    void pop() {
        if(top == -1) {
            cout << "Stack Underflow\n";
            return;
        }
        cout << arr[top--] << " popped\n";
    }

    void peek() {
        if(top == -1) cout << "Stack is empty\n";
        else cout << "Top element: " << arr[top] << endl;
    }

    void display() {
        if(top == -1) {
            cout << "Stack is empty\n";
            return;
        }
        cout << "Stack elements: ";
        for(int i=top; i>=0; i--) cout << arr[i] << " ";
        cout << endl;
    }
};

int main(){
    Stack st;
    st.push(10);
    st.push(20);
    st.push(30);
    st.display();
    st.peek();
    st.pop();
    st.display();
    return 0;
}
