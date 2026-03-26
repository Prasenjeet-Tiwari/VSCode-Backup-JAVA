#include <iostream>
using namespace std;

class ABC {
public:
    virtual void sort(void* arr, int n) = 0;
};

class ABCint : public ABC {
public:
    void sort(void* arr, int n) {
        int* a = (int*)arr;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    int temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }

        cout << "Sorted Integer Array: ";
        for (int i = 0; i < n; i++)
            cout << a[i] << " ";
        cout << endl;
    }
};

class ABCdouble : public ABC {
public:
    void sort(void* arr, int n) {
        double* a = (double*)arr;

        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (a[i] > a[j]) {
                    double temp = a[i];
                    a[i] = a[j];
                    a[j] = temp;
                }
            }
        }

        cout << "Sorted Double Array: ";
        for (int i = 0; i < n; i++)
            cout << a[i] << " ";
        cout << endl;
    }
};

int main() {
    int iarr[5] = {5, 2, 9, 1, 3};
    double darr[5] = {4.2, 1.1, 3.3, 2.2, 5.5};

    ABC* obj;

    ABCint intObj;
    ABCdouble doubleObj;

    obj = &intObj;
    obj->sort(iarr, 5);

    obj = &doubleObj;
    obj->sort(darr, 5);

    return 0;
}
