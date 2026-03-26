#include<stdio.h>
//to calculate the nth factorial of a number 

// function to calculate the factorial usiong recursion 

int fac(int n){
    int factorial=1;
    if(n==0 || n==1){
        return factorial;
    }
    while(n>1){
        factorial*=n;
        n--;
    }
    return factorial;
}

//main function
int main(){
    int n;
    printf("Enter the nth number : ");
    scanf("%d", &n);

    //function call
    int output= fac(n);
    printf("The factorial of %d is : %d", n, output);
    return 0;

}
