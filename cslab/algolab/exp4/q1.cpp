#include <iostream>
using namespace std;

// structure to keep value + identity
struct Item {
    int value;
    char id;   // to track original order
};

/* ================= NORMAL HEAPSORT ================= */

// heapify for integer array
void heapifyInt(int arr[], int n, int i) {

    int largest = i;
    int left = 2*i + 1;
    int right = 2*i + 2;

    if(left < n && arr[left] > arr[largest])
        largest = left;

    if(right < n && arr[right] > arr[largest])
        largest = right;

    if(largest != i) {
        swap(arr[i], arr[largest]);
        heapifyInt(arr, n, largest);
    }
}

void heapSortInt(int arr[], int n) {

    for(int i = n/2 - 1; i >= 0; i--)
        heapifyInt(arr, n, i);

    for(int i = n-1; i > 0; i--) {
        swap(arr[0], arr[i]);
        heapifyInt(arr, i, 0);
    }
}

/* ================= UNSTABLE DEMO ================= */

// heapify for Item array (compare ONLY value)
void heapifyItem(Item arr[], int n, int i) {

    int largest = i;
    int left = 2*i + 1;
    int right = 2*i + 2;

    if(left < n && arr[left].value > arr[largest].value)
        largest = left;

    if(right < n && arr[right].value > arr[largest].value)
        largest = right;

    if(largest != i) {
        swap(arr[i], arr[largest]);
        heapifyItem(arr, n, largest);
    }
}

void heapSortItem(Item arr[], int n) {

    for(int i = n/2 - 1; i >= 0; i--)
        heapifyItem(arr, n, i);

    for(int i = n-1; i > 0; i--) {
        swap(arr[0], arr[i]);
        heapifyItem(arr, i, 0);
    }
}

/* ================= MAIN ================= */

int main() {

    // -------- Normal HeapSort --------
    int arr1[] = {5,3,8,4,1};
    int n1 = 5;

    heapSortInt(arr1, n1);

    cout<<"Sorted integer array:\n";
    for(int i=0;i<n1;i++)
        cout<<arr1[i]<<" ";

    // -------- Unstable Example --------
    Item arr2[] = {
        {5,'A'},
        {3,'X'},
        {5,'B'},
        {2,'Y'}
    };

    int n2 = 4;

    cout<<"\n\nBefore sorting:\n";
    for(int i=0;i<n2;i++)
        cout<<"("<<arr2[i].value<<","<<arr2[i].id<<") ";

    heapSortItem(arr2,n2);

    cout<<"\n\nAfter HeapSort:\n";
    for(int i=0;i<n2;i++)
        cout<<"("<<arr2[i].value<<","<<arr2[i].id<<") ";
}
