#include <iostream>
#include <algorithm>
using namespace std;

class ArrayOps {
private:
    int *iarr;
    float *farr;
    int n;
    bool isInt;

public:
    ArrayOps(int arr[], int size) {
        n = size;
        isInt = true;
        iarr = new int[n];
        for (int i = 0; i < n; i++) iarr[i] = arr[i];
    }

    ArrayOps(float arr[], int size) {
        n = size;
        isInt = false;
        farr = new float[n];
        for (int i = 0; i < n; i++) farr[i] = arr[i];
    }

    void operations() {
        if (isInt) {
            int sum = 0;
            for (int i = 0; i < n; i++) sum += iarr[i];
            sort(iarr, iarr + n);
            cout << "Sum: " << sum << endl;
            cout << "Average: " << (float)sum / n << endl;
            cout << "Median: " << iarr[n/2] << endl;
        } else {
            float sum = 0;
            for (int i = 0; i < n; i++) sum += farr[i];
            sort(farr, farr + n);
            cout << "Sum: " << sum << endl;
            cout << "Average: " << sum / n << endl;
            cout << "Median: " << farr[n/2] << endl;
        }
    }
};

int main() {
    int a[] = {1, 2, 3, 4, 5};
    float b[] = {1.5, 2.5, 3.5};

    ArrayOps obj1(a, 5);
    obj1.operations();

    ArrayOps obj2(b, 3);    
    obj2.operations();

    return 0;
}
