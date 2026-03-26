#include <iostream>
using namespace std;

void merge(int a[], int n, int b[], int m, int result[]) {
    int i = 0, j = 0, k = 0;
    
    while(i < n && j < m) {
        if(a[i] < b[j])
            result[k++] = a[i++];
        else
            result[k++] = b[j++];
    }
    
    while(i < n)
        result[k++] = a[i++];
    
    while(j < m)
        result[k++] = b[j++];
}

int main() {
    int a[] = {1, 3, 5, 7};
    int b[] = {2, 4, 6, 8};
    int result[8];

    merge(a, 4, b, 4, result);

    cout << "Merged Sorted Array: ";
    for(int i = 0; i < 8; i++)
        cout << result[i] << " ";

    return 0;
}
