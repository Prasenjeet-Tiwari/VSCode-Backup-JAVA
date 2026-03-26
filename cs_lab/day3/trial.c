#include<stdio.h>

struct Node{
    int data;
    struct Node *next;
};

struct Node* head=NULL;

struct Node* addAtFirst(int data){

    struct Node *newNode = (struct Node)malloc(sizeof(struct Node));
    newNode->data=data;
    newNode->next=head;
    return newNode;
}

int main(){

    
    
    

    return 0;
}