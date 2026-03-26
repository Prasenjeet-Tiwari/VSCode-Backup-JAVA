#include<stdio.h>

void isPal(int n){
    
    for(int i=1; i<=n; i++){

        int temp= i;    int rev=0;  int rem=0;
        while(temp!=0){
            rem = temp%10;
            rev= (rev*10) + rem;
            temp= temp/10;
        }

        if(rev==i){
            printf("%d ", i);
        }
    }   
}

void main(){

    int n;
    printf("Please Enter the value of n :");
    scanf("%d", &n);

    isPal(n);

}