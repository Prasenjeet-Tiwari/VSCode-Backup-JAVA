#include <stdio.h>
int tree(int a, int b);


int main()
{
    int a,b;
    printf("pass two number which you wish to add. Be quick:\n");
    scanf("%d %d",&a,&b);
    int pomp;
    pomp=tree(a,b);
    printf("the answer is:%d",pomp);
    return 0;
}
int tree(int a, int b)  ///function argument defining type
{
    return a+b;        ///main calc defining with retun all together can be done separately like---[int pp=a+b; retun (pp)]
}