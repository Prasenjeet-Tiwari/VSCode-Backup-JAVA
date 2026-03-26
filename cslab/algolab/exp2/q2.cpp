#include <bits/stdc++.h>
using namespace std;

void merge(int arr[], int start, int mid, int end) {
    int n1 = mid - start + 1;
    int n2 = end - mid;

    int L[n1], R[n2];

    for (int i = 0; i < n1; i++)
        L[i] = arr[start + i];
    for (int j = 0; j < n2; j++)
        R[j] = arr[mid + 1 + j];

    int i = 0, j = 0, k = start;

    while (i < n1 && j < n2) {
        if (L[i] <= R[j]) {
            arr[k] = L[i];
            i++;
        } else {
            arr[k] = R[j];
            j++;
        }
        k++;
    }

    while (i < n1) {
        arr[k] = L[i];
        i++;
        k++;
    }

    while (j < n2) {
        arr[k] = R[j];
        j++;
        k++;
    }
}

void mergesort(int arr[], int start, int end) {
    if (start >= end)
        return;

    int mid = start + (end - start) / 2;

    mergesort(arr, start, mid);
    mergesort(arr, mid + 1, end);
    merge(arr, start, mid, end);
}

void sorted_array(int arr[], int n) {
    for (int i = 0; i < n; i++)
        arr[i] = i + 1;
}

void reverse_sorted(int arr[], int n) {
    for (int i = 0; i < n; i++)
        arr[i] = n - i;
}

void random_arr(int arr[], int n) {
    for (int i = 0; i < n; i++)
        arr[i] = rand() % 1000;
}

void printit(int arr[], int n) {
    for (int i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;
}

int main() {
    int n = 1000;
    int arr[n];

    srand(time(0));

    // Sorted array
    random_arr(arr, n);
    clock_t start = clock();
    mergesort(arr, 0, n - 1);
    clock_t end = clock();
    cout << "random array time (ms): "
         << double(end - start) * 1000 / CLOCKS_PER_SEC << endl;
    
    // Sorted array
    sorted_array(arr, n);
    start = clock();
    mergesort(arr, 0, n - 1);
    end = clock();
    cout << "Sorted array time (ms): "
         << double(end - start) * 1000 / CLOCKS_PER_SEC << endl;

    // Reverse sorted array
    reverse_sorted(arr, n);
    start = clock();
    mergesort(arr, 0, n - 1);
    end = clock();
    cout << "Reverse sorted array time (ms): "
         << double(end - start) * 1000 / CLOCKS_PER_SEC << endl;


    return 0;
}
