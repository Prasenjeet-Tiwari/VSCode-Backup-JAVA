#include <bits/stdc++.h>
using namespace std;

//Utility Functions 

void random_array_build(int arr[], int n){
    for(int i = 0; i < n; i++){
        arr[i] = rand() % 100000;
    }
}

void printit(int arr[], int n){
    for(int i = 0; i < n; i++){
        cout << arr[i] << " ";
    }
    cout << endl;
}

void swap(int &a, int &b){
    int t = a;
    a = b;
    b = t;
}

// QuickSort using LAST element as pivot 

int partition_last(int arr[], int low, int high){
    int pivot = arr[high];
    int i = low - 1;

    for(int j = low; j < high; j++){
        if(arr[j] < pivot){
            i++;
            swap(arr[i], arr[j]);
        }
    }

    swap(arr[i + 1], arr[high]);
    return i + 1;
}

void quickSort_last(int arr[], int low, int high){
    if(low < high){
        int pi = partition_last(arr, low, high);
        quickSort_last(arr, low, pi - 1);
        quickSort_last(arr, pi + 1, high);
    }
}

//Randomized QuickSort 

int partition_random(int arr[], int low, int high){
    int random_index = low + rand() % (high - low + 1);
    swap(arr[random_index], arr[high]);
    return partition_last(arr, low, high);
}

void quickSort_random(int arr[], int low, int high){
    if(low < high){
        int pi = partition_random(arr, low, high);
        quickSort_random(arr, low, pi - 1);
        quickSort_random(arr, pi + 1, high);
    }
}

//Main Function 

int main(){
    srand(time(0));

    int n = 100000;   // time
    int arr1[n], arr2[n];

    random_array_build(arr1, n);

    
    for(int i = 0; i < n; i++){
        arr2[i] = arr1[i];
    }

    // QuickSort with last element pivot
    clock_t start = clock();
    quickSort_last(arr1, 0, n - 1);
    clock_t end = clock();
    cout << "Time taken by QuickSort (Last Pivot): "
         << double(end - start) *1000/ CLOCKS_PER_SEC << " seconds" << endl;

    // Randomized QuickSort
    start = clock();
    quickSort_random(arr2, 0, n - 1);
    end = clock();
    cout << "Time taken by Randomized QuickSort: "
         << double(end - start) *1000/ CLOCKS_PER_SEC << " seconds" << endl;

    return 0;
}
