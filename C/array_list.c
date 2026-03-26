/*NOTE ON ARRAY
name=can
now
name[0]='c' ;  name[1]='a' ;  name[2]='n' ;  name[3]='0'
Reason: name={"c","a","n","/0"}
*/

#include <stdio.h>
int main(void)
{
    printf("Enter the length of the array :");
    int s;
    scanf("%d", &s);
    int q[s];
    for (int p = 1; p<=s; p++)
    {
        printf("Input data number %d:", p);
        int y;
        scanf("%d", &y);
        q[p - 1] = y;
    }
    printf("q = ");
    for (int i = 0; i < s; i++)
    {
        printf("%d ", q[i]);
    }
}