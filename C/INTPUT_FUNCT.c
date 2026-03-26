/*#include <stdio.h>

char get_int(char a);
int teto();
int main(void)
{
    const char a;
    a=get_int("tt");
    printf(a)



}
char get_int( char d)
{
    printf(d);
    teto();
}
int teto()
{
    int s;
    scanf("%d",&s);
    return s;
}
*/
#include <stdio.h>
int get_int() 
{
    int n;
    scanf(" %d ", &n); 
    return n;
}
int main() 
{
    int n = get_int();
    printf(" %d ", n);
    printf(n);
    return 0;
}