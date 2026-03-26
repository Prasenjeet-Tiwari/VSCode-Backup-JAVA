#include<stdio.h>

/* To find the greatest number form the three user inputs*/

int main(){

    //declare variables
    int num1;
    int num2;
    int num3;

    //Getting input 
    printf("Enter the First number:");
    scanf("%d", &num1);
    printf("Enter the Second number:");
    scanf("%d", &num2);
    printf("Enter the Third number:");
    scanf("%d", &num3);

    //Condition checking to find greates number
    if( (num1>= num2) && (num1>= num3)){
        printf("%d is the greatest number", num1);
    }
    else if( (num2>= num1) && (num2>= num3)){
        printf("%d is the greatest number", num2);
    }
    else if((num3>= num1) && (num3>= num2)){
        printf("%d is the greatest number", num3);
    }

    //Error Handling
    else{
        printf("Error!! invalid input");
    }

    return 0;
}