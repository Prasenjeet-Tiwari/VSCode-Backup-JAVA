#include <stdlib.h>
#include <stdio.h>
//#include <time.h>

#define max 6
#define min 1

int roll() {
    return (rand() % (max - min + 1)) + min;
}
void feq(int i, int f[]) {
    f[i - 1]++; 
}

int main() {
    int n;
    printf("Enter the number of dice rolls: ");
    scanf("%d", &n);

    int arr[n];  
    int f[max] = {0}; 

    //srand(time(0));

    for (int i = 0; i < n; i++) {
        int j = roll();
        arr[i] = j;    
        feq(j, f);     
    }

    printf("Values of dice rolls: ");
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    printf("Frequency of dice faces:\n");
    for (int i = 0; i < max; i++) {
        printf("%d: %d\n", i + 1, f[i]);
    }

    return 0;
}
