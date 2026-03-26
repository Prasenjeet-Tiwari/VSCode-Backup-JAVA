#include<stdio.h>

void rec(int value){
    if(value==1){
        printf("1");
        return;
    }
    if(value==0){
        printf("0");
        return;
    }

    rec(value/2);
    printf("%d",value%2);
}

int gcd(int a, int b) {
    if (b == 0) {
        return a;
    }
    return gcd(b, a % b);
}

void func(int i, int j) {
    if (i > j) {
        return; // Base case: stop when i exceeds j
    }
    for (int t = i; t >=1; t-- ) {
        printf("%d ", t); // Print numbers from 1 to i
    }
    printf("\n");
    func(i + 1, j); // Recursive call with incremented row
}

void main(){
    int n=8;
    rec(3.2);
    printf("\n the gcd : %d\n",gcd(48,16));

    func(1,5);
}
//fgets(str1,sizeof(str1),stdin);