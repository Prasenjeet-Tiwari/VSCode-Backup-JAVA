#include <stdio.h>  
  
int main()  
{  
    int A;  
    float B;
    char type
  
    printf("Enter the number type integer (i) or float(f): ");  
    scanf("%s", &type);  

    if(type=="i"){
            printf("Enter the number : ");  
            scanf("%d", &A);  
            if (A > 0)  
                printf("%d is positive.", A);  
            else if (A < 0)  
                printf("%d is negative.", A);  
            else if (A == 0)  
                printf("%d is zero.", A);

    }else if(type=="f"){
            printf("Enter the number : ");  
            scanf("%f", &B);  
            if(B > 0)  
                printf("%d is positive.", B);  
            else if (B < 0)  
                printf("%d is negative.", B);  
            else if (B == 0)  
                printf("%d is zero.", B);

    }else{
        printf("invalid input")
    }
  
    if (A > 0)  
        printf("%d is positive.", A);  
    else if (A < 0)  
        printf("%d is negative.", A);  
    else if (A == 0)  
        printf("%d is zero.", A);  
  
    return 0;