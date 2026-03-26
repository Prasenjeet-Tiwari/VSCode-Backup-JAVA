#include<stdio.h>
#include<math.h>

int main(){

    // Declare variables
    int a, b, c;

    // Taking input of a, b, and c
    printf("Enter the value of a: ");
    scanf("%d", &a);  
    printf("Enter the value of b: ");
    scanf("%d", &b);  
    printf("Enter the value of c: ");
    scanf("%d", &c);  

    if(b*b - 4*a*c >= 0){ 
        // Calculating square root
        double root_1 = (-b - sqrt((b*b) - 4*a*c)) / (2.0*a);  
        double root_2 = (-b + sqrt((b*b) - 4*a*c)) / (2.0*a);  
        printf("The roots are %f and %f.", root_1, root_2);  
    } else {
        // Error handling
        printf("The roots are not real.");
    }
    return 0;
}