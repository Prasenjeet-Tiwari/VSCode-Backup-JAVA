#include <stdio.h>

int main()
{
    char name[100]; /// assunimg the name won't be longer than 100 letters

    printf("Please enter your name: "); /// asking for user input
    fgets(name, sizeof(name), stdin);

    printf("Hello, %s!.\n", name); /// printing the required output

    return 0;
}