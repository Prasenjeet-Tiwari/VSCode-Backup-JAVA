#include <iostream>
#include <cstdlib>
#include <ctime>
using namespace std;

// Inline counting sort
void countingSortInline(int A[], int n, int k) {

    int range = 2*k + 1;

    // Count array
    int C[range] = {0};

    // Step 1: Count frequency
    for(int i = 0; i < n; i++) {
        C[A[i] + k]++;
    }

    // Step 2: Rewrite A in sorted order (in-place)
    int index = 0;

    for(int value = -k; value <= k; value++) {

        while(C[value + k] > 0) {
            A[index++] = value;
            C[value + k]--;
        }
    }
}

int main() {

    int n, k;
    cout<<"Enter n and k: ";
    cin>>n>>k;

    int A[n];

    srand(time(0));

    // Generate random numbers in [-k, k]
    for(int i=0;i<n;i++)
        A[i] = rand()%(2*k+1) - k;

    cout<<"\nArray before sorting:\n";
    for(int i=0;i<n;i++)
        cout<<A[i]<<" ";

    countingSortInline(A, n, k);

    cout<<"\n\nArray after sorting:\n";
    for(int i=0;i<n;i++)
        cout<<A[i]<<" ";
}
