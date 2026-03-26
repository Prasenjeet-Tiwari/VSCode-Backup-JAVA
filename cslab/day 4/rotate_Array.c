#include<stdio.h>

int main() {

    // Correct initialization of the array
    int arr1[] = {1, 2, 3, 4, 5, 6};
    int j ;
    printf("Enter the value of n(rotation times): ");
    scanf("%d",&j);
    int count = 0;

    // Rotate the array 'j' times
    while (count != j) {
        int temp = arr1[0];  // Store the first element
        for (int i = 0; i < 5; i++) {  // Shift all elements to the left
            arr1[i] = arr1[i+1];
        }
        arr1[5] = temp;  // Put the first element at the end
        count++;
    }

    // Print the rotated array
    for (int i = 0; i < 6; i++) {
        printf("%d ", arr1[i]);
    }

    return 0;
}
