#include <stdio.h>

// to calculate and print the fibonnacci number till nth term

int main(){

    int n; 
    printf("enter the nth term of fibo: ");
    scanf("%d",&n);
    int prv=0;
    int curr=1;

    //printinig the  zeorth term
    printf("%d ", prv);

    for(int i=1; i<=n; i++){
        int temp= curr;
        curr= curr+prv;
        prv=temp;
        printf("%d ", prv);
    }
    return 0;
}