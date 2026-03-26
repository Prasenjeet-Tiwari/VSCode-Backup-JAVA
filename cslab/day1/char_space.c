#include<stdio.h>
#include<string.h>

int main(){

    //Declaring variable
    char name[100];

    //user input
    printf("Enter your name : ");
    scanf("%[^\n]s",&name);

    //printing the name
    printf("Your name is : %s", name);

    return(0);
}