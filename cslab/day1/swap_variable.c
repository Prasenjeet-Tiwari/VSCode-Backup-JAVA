#include<stdio.h>

int main(){

//Using extra variable

    int num1=3;
    int num2=7; 

    printf("Before Swap: \n");
    printf("num1: %d \n",num1);
    printf("num2: %d \n\n",num2);
    
    int temp= num1;
    num1=num2;
    num2=temp;

    printf("\nAfter Swap: \n");
    printf("num1: %d \n",num1);
    printf("num2: %d \n\n",num2);

//Without using extra variable (using aditiona and substraction)

    int num3=10;
    int num4=20;

    printf("Before Swap: \n");
    printf("num3: %d \n",num3);
    printf("num4: %d \n\n",num4);

    num3= num3 +num4;
    num4 =num3 -num4;
    num3 =num3 -num4;

    printf("After swap: \n");
    printf("num3: %d \n",num3);
    printf("num4: %d \n",num4);

//Without using extra variable (using XOR)

    int num5=9;
    int num6=6;

    printf("Before Swap: \n");
    printf("num5: %d \n",num5);
    printf("num6: %d \n\n",num6);

    num5=num5^num6;
    num6=num5^num6;
    num5=num5^num6;

    printf("After swap: \n");
    printf("num5: %d \n",num5);
    printf("num6: %d \n",num6);

    return 0;
}


