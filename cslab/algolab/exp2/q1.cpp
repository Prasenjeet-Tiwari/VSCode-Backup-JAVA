#include <bits/stdc++.h>
using namespace std;

void random_arr(int arr[], int n) {
    for (int i = 0; i < n; i++)
        arr[i] = rand();
}

void sorted_arr(int arr[], int n) {
    for (int i = 0; i < n; i++)
        arr[i] = i + 1;
}

void reverse_sorted_arr(int arr[], int n) {
    for (int i = 0; i < n; i++)
        arr[i] = n - i;
}

// Optimal 3n/2 - 2 comparison algorithm
void findMinMax(int arr[], int n) {
    int minVal, maxVal;
    int a, b;

    if (n % 2 == 0) {
        if (arr[0] < arr[1]) {
            minVal = arr[0];
            maxVal = arr[1];
        } else {
            minVal = arr[1];
            maxVal = arr[0];
        }
        a = 2;
        b = n - 1;
    } else {
        minVal = maxVal = arr[0];
        a = 1;
        b = n - 1;
    }

    while (a < b) {
        int temp_min, temp_max;

        if (arr[a] < arr[b]) {
            temp_min = arr[a];
            temp_max = arr[b];
        } else {
            temp_min = arr[b];
            temp_max = arr[a];
        }

        if (temp_min < minVal)
            minVal = temp_min;

        if (temp_max > maxVal)
            maxVal = temp_max;

        a++;
        b--;
    }
}

int main() {
    srand(time(0));
    int n = 10000;
    int arr[n];

    // Random array
    random_arr(arr, n);
    clock_t start = clock();
    findMinMax(arr, n);
    clock_t end = clock();
    cout << "Random array time (ms): "
         << double(end - start) * 1000 / CLOCKS_PER_SEC << endl;

    // Sorted array
    sorted_arr(arr, n);
    start = clock();
    findMinMax(arr, n);
    end = clock();
    cout << "Sorted array time (ms): "
         << double(end - start) * 1000 / CLOCKS_PER_SEC << endl;

    // Reverse sorted array
    reverse_sorted_arr(arr, n);
    start = clock();
    findMinMax(arr, n);
    end = clock();
    cout << "Reverse sorted array time (ms): "
         << double(end - start) * 1000 / CLOCKS_PER_SEC << endl;

    return 0;
}
