#include<iostream>
using namespace std;

#define MAX 100

class Stack{
    int arr[MAX];
    int top;

public:
    Stack(){
        top=-1;
    }
    void push(int data){
        if(top==MAX){
            cout<<"Overflow"<<endl; return;
        }
        arr[++top]=data;
        cout<<"Data successfully entered"<<endl;
    }

    void pop(){
        if(top<0){
            cout<<"Underflow"<<endl; return;
        }
        cout<<arr[top--]<<"popped from stack"<<endl;
    }
    void peek(){
        if(top<0) cout<<"Stack is empty"<<endl; return;
        cout<<"Top element : "<<arr[top];

    }
    void display(){
        if (top == -1) {
            cout << "Stack is empty\n"<<endl;
            return;
        }
        for(int i=0; i<=top;i++){
            cout<<arr[i]<<" ";
        }
    }
};

int main(){

    Stack s;
    int data ; int choice;

    while(1){
        cout<<"Enter the operation to perform"<<endl;
        cout<<"1.Push    2. Pop    3.Peek   4.Display    5.Exit"<<endl;

        cin>>choice;

        switch(choice){
            case(1):
                cout<<"Enter data:";
                cin>>data;
                s.push(data); break;
            case(2):
                s.pop(); break;
            case(3):
                s.peek(); break;
            case(4):
                s.display(); break;
            case(5):
                exit(0); break;
        }
    }
    return 0;
}