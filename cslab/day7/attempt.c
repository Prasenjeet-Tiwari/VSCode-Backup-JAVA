#include <stdio.h>
#include <math.h>
#include<string.h>

int main(){

    char str1[100];
    printf("name:");
    fgets(str1,sizeof(str1),stdin);
    printf("%s",str1);
    printf("size: %d", strlen(str1));

    return 0;
}
