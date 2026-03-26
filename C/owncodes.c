#include <stdio.h>
int main(void)
{
    double a=3, b=50;
    printf("user please input a numeric value: ");
    scanf("%lf", &a);
    printf("user please input a numeric value: ");
    scanf("%lf", &b);
    
    while (b > a)
    {
        printf("lambda varient=%lf\n", a);
        a+=1;
    }
    
    
    
    
            
        for (int i=1; i<=5; ++i)
        {
            printf("%d\n", i);
        }

        return 0;
}
