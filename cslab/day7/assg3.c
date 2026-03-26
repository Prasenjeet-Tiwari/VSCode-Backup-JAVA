#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void countFrequency(int a[], int n, int k) {
    int range = 2 * k + 1; 
    int freq[range]= {0};    

    for (int i = 0; i < n; i++) {
        freq[a[i] + k]++; 
    }

    printf("Frequencies of each value from -%d to %d:\n", k, k);
    for (int i = -k; i <= k; i++) {
        printf("%d: %d\n", i, freq[i + k]);
    }
}

int main() {
    int n, k;

    printf("Enter the number of elements (n): ");
    scanf("%d", &n);
    printf("Enter the range limit (k): ");
    scanf("%d", &k);

    int a[n];

    printf("Array elements:\n");
    for (int i = 0; i < n; i++) {
        a[i] = (rand() % (2 * k + 1)) - k; 
        printf("%d ", a[i]);
    }
    printf("\n");

    countFrequency(a, n, k);

    return 0;
}
