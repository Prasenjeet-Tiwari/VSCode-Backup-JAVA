#include <stdio.h>
#include <math.h>

void install(int i, int sum){
    if(i == sum){
        printf("%d ", i);
    }
}

void isArmstrong(int n) {
    for (int i = 1; i <= n; i++) {
        
        int count = 0, num = i, temp = i, rem = 0, sum = 0;

        while (num != 0) {
            num = num / 10;
            count++;
        }

        while (temp != 0) {
            rem = temp % 10;
            sum += pow(rem, count);
            temp = temp / 10;
        }

        install(i, sum);
    }
}

int main() {
    int n;
    printf("Please enter the value of n: ");
    scanf("%d", &n);

    isArmstrong(n);
    return 0;
}
