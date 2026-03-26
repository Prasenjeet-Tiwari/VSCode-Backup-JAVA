#include <stdio.h>

void scan_array(int *arr, int size) {
    printf("Enter %d integers:\n", size);
    for (int i = 0; i < size; i++) {
        scanf("%d", &arr[i]);
    }
}

void print_array(int *arr, int size) {
    printf("Array elements are:\n");
    for (int i = 0; i < size; i++) {
        printf("%d ", *(arr +i));
    }
    printf("\n");
}

int main() {
    int arr[5];
    scan_array(arr, 5);
    print_array(arr, 5);
    return 0;
}
