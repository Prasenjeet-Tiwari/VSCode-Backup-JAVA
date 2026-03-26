#include <stdio.h>

int main()
{
    ////// another way to represnt  (EASIER WAY+)
    int firstNo;
    int secondNo;

    printf("Enter first number : ");
    scanf("%d", &firstNo);

    printf("Enter second number : ");
    scanf("%d", &secondNo);

    firstNo > secondNo ? printf("%d is greater than %d\n", firstNo, secondNo) : printf("%d is smaller than %d\n", firstNo, secondNo);


    /// another way to represnt  (EASIER WAY+)
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
    ///(out of context) following here is how to get to know different byte lebgth
     {
        short a;
        long b;
        long long c;
        long double d;

        printf("size of short = %d bytes\n", sizeof(a));
        printf("size of long = %d bytes\n", sizeof(b));
        printf("size of long long = %d bytes\n", sizeof(c));
        printf("size of long double= %d bytes\n", sizeof(d));
        return 0;
     }
}