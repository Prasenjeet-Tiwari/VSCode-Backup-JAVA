#include <stdio.h>
int main(void)
{
    int a,b;
    printf("Please enter two numbers(write second number after pressing enter): \n");
    scanf("%d %d", &a,&b);
    if(a>b)
    {
        printf("%d is  greater than %d",a,b);
    }
    else
    {
        printf("%d is greater than %d",b,a);
    }
}
