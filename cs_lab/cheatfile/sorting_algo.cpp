#include <iostream>
using namespace std;

#define MAX 100

class ArrayOps {
private:
    int arr[MAX];
    int size;

public:
    ArrayOps() { size = 0; }

    // Insert element
    void insert(int value) {
        if (size == MAX) {
            cout << "Array is full!\n";
            return;
        }
        arr[size++] = value;
        cout << "Inserted successfully.\n";
    }

    // Delete element
    void remove(int value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (arr[i] == value) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            cout << "Element not found!\n";
            return;
        }

        for (int i = index; i < size - 1; i++)
            arr[i] = arr[i + 1];

        size--;
        cout << "Deleted successfully.\n";
    }

    // Selection Sort
    void selectionSort() {
        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < size; j++)
                if (arr[j] < arr[minIndex])
                    minIndex = j;

            swap(arr[i], arr[minIndex]);
        }
        cout << "Array sorted using Selection Sort.\n";
    }

    // Insertion Sort
    void insertionSort() {
        for (int i = 1; i < size; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
        cout << "Array sorted using Insertion Sort.\n";
    }

    // Bubble Sort
    void bubbleSort() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (arr[j] > arr[j + 1])
                    swap(arr[j], arr[j + 1]);
            }
        }
        cout << "Array sorted using Bubble Sort.\n";
    }

    // Display array
    void display() {
        if (size == 0) {
            cout << "Array is empty!\n";
            return;
        }

        cout << "Array elements: ";
        for (int i = 0; i < size; i++)
            cout << arr[i] << " ";
        cout << endl;
    }
};

// -------------------- MAIN --------------------
int main() {
    ArrayOps obj;
    int choice, value;

    while (true) {
        cout << "\n====== ARRAY MENU ======";
        cout << "\n1. Insert Element";
        cout << "\n2. Delete Element";
        cout << "\n3. Selection Sort";
        cout << "\n4. Insertion Sort";
        cout << "\n5. Bubble Sort";
        cout << "\n6. Display Array";
        cout << "\n7. Exit";
        cout << "\nEnter choice: ";
        cin >> choice;

        switch (choice) {
            case 1:
                cout << "Enter value: ";
                cin >> value;
                obj.insert(value);
                break;
            case 2:
                cout << "Enter value to delete: ";
                cin >> value;
                obj.remove(value);
                break;
            case 3:
                obj.selectionSort();
                break;
            case 4:
                obj.insertionSort();
                break;
            case 5:
                obj.bubbleSort();
                break;
            case 6:
                obj.display();
                break;
            case 7:
                cout << "Exiting...\n";
                return 0;
            default:
                cout << "Invalid choice! Try again.\n";
        }
    }

    return 0;
}
