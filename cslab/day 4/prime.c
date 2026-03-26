#include<stdio.h>

void isPrime (int n){
    
    int count=0;
    for(int i=2; i<=n; i++){
        for(int j=2; j<i; j++){
            if(i%j==0){
                count++; break;
            }
        }

        //if prime then printing it
        if(count==0){
            printf("%d ",i);
        }
        count=0;    //resetting value of count
    }
}

void main(){
    int n;

    printf("Please Enter the value of n:");
    scanf("%d", &n);

    isPrime(n);

}