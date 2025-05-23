#include<stdio.h>

void main(){

    int n;
    printf("Enter a number: ");
    scanf("%d", &n);

    int name[]={'a','b','c'};
    printf("The number is prime %d and %d",sizeof(name), sizeof(n));
    
    return;
}