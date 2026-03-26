#include <bits/stdc++.h>
using namespace std;

void sorted_arr(int arr[], int n){
    // create sorted array
    arr[0] = rand() % 10;
    for(int i = 1; i < n; i++){
        arr[i] = arr[i-1] + (rand() % 5);
    }
}

// merge two sorted arrays and return worst-case comparisons
int mergesort_comp(int arr1[], int arr2[], int arrx[], int n1, int n2){
    int i = 0;
    int j = 0;
    int k = 0;
    
    while(i < n1 && j < n2){
        if(arr1[i] < arr2[j]){
            arrx[k++] = arr1[i++];
        } else {
            arrx[k++] = arr2[j++];
        }
    }

    while(i < n1){
        arrx[k++] = arr1[i++];
    }

    while(j < n2){
        arrx[k++] = arr2[j++];
    }

    
    return n1+n2-1;
}

int main(){
    srand(time(0));

    int n1 = 20;   int n2 = 24;   int n3 = 30;
    int n4 = 35;   int n5 = 50;

    int arr1[n1]; int arr2[n2]; int arr3[n3];
    int arr4[n4]; int arr5[n5];

    sorted_arr(arr1, n1);    sorted_arr(arr2, n2);    sorted_arr(arr3, n3);
    sorted_arr(arr4, n4);    sorted_arr(arr5, n5);

    int output = 0;
    //always tryiong to select the most minimum length of array
    //merge 20 & 24 -> 44
    int arrx[n1 + n2];
    output += mergesort_comp(arr1, arr2, arrx, n1, n2);
    cout << "comparisions till now: " << output << endl;

    //merge 30 & 35 -> 65
    int arry[n3 + n4];
    output += mergesort_comp(arr3, arr4, arry, n3, n4);
    cout << "comparisions till now: " << output << endl;

    //merge 44 & 50 -> 94
    int arrz[n1 + n2 + n5];
    output += mergesort_comp(arrx, arr5, arrz, n1 + n2, n5);
    cout << "comparisions till now: " << output << endl;

   //merge 65 & 94 -> final
    int arra[n1 + n2 + n3 + n4 + n5];
    output += mergesort_comp(arry, arrz, arra, n3 + n4, n1 + n2 + n5);
    cout << "comparisions till now: " << output << endl;

    return 0;
}
