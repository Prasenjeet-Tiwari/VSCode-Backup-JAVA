#include <iostream>
using namespace std;

// Function to merge two sorted halves
void merge(int arr[], int left, int mid, int right) {
    int i = left, j = mid + 1, k = 0;
    int size = right - left + 1;
    int temp[size];

    // Merge elements in sorted order
    while(i <= mid && j <= right) {
        if(arr[i] < arr[j])
            temp[k++] = arr[i++];
        else
            temp[k++] = arr[j++];
    }

    // Copy remaining elements
    while(i <= mid)
        temp[k++] = arr[i++];
    while(j <= right)
        temp[k++] = arr[j++];

    // Copy back to original array
    for(i = left, k = 0; i <= right; i++, k++)
        arr[i] = temp[k];
}

// Recursive Merge Sort
void mergeSort(int arr[], int left, int right) {
    if(left < right) {
        int mid = (left + right) / 2;

        mergeSort(arr, left, mid);     // Sort left half
        mergeSort(arr, mid + 1, right); // Sort right half

        merge(arr, left, mid, right);   // Merge halves
    }
}

// Function to print array
void printArray(int arr[], int n) {
    for(int i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;
}

int main() {
    int arr[] = {38, 27, 43, 3, 9, 82, 10};
    int n = sizeof(arr) / sizeof(arr[0]);

    cout << "Original Array: ";
    printArray(arr, n);

    mergeSort(arr, 0, n - 1);

    cout << "Sorted Array using Merge Sort: ";
    printArray(arr, n);

    return 0;
}
