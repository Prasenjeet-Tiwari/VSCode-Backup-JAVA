#include<stdio.h>
    // to calculate 1+2+3+......+n


    //using loop to find sum 
    int sum(int n){
        int sum=0;
        for(int i=0; i<=n; i++){
            sum+=i;
        }
        return sum;
    }

    //main function
    int main(){
        int n;
        printf("enter the nth term : ");
        scanf("%d", &n);

        int output= sum(n);
        printf("The net sum till nth term is : %d", output);
        return 0;
    }
