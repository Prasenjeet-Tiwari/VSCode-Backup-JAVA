//Insertion sort
#include<iostream>
using namespace std;

void Insertion_sort(int arr[], int n){
    int swaps = 0, comparisons = 0;
    
    for (int i = 1; i <n; i++){  // start from 1, not 0
        int num = arr[i];
        int j = i - 1;
        
        while (j >= 0 && arr[j] > num){
            arr[j + 1] = arr[j];
            j--;
            swaps++;
            comparisons++;
        }
        arr[j + 1] = num; // place num at correct position
        if (j >= 0) comparisons++; // last failed comparison
    }

    cout << "After: ";
    for(int k = 0; k < n; k++){
        cout << arr[k] << " ";
    }
    cout << endl;
    cout<<"Comparisions: "<<comparisons<<endl<<"Swaps : "<<swaps;
}

int main(){
    int n;
    cout << "Enter the length of the array: ";
    cin >> n;
    int arr[n];
    cout << "Enter the elements: " << endl;
    for(int i = 0; i < n; i++){
        cout << "Element at " << i << ": ";
        cin >> arr[i];
    }

    cout << "Before: ";
    for(int i = 0; i < n; i++){
        cout << arr[i] << " ";
    }
    cout << endl;

    Insertion_sort(arr, n);
}
