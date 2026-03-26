#include <iostream>
using namespace std;

class Compare {
private:
    int iVal;
    float fVal;
    bool isInt;

public:
    Compare(int x) {
        iVal = x;
        isInt = true;
    }

    Compare(float x) {
        fVal = x;
        isInt = false;
    }

    void compare(int x) {
        if (isInt)
            cout << (x == iVal ? "Equal\n" : "Not Equal\n");
    }

    void compare(float x) {
        if (!isInt)
            cout << (x == fVal ? "Equal\n" : "Not Equal\n");
    }
};

int main() {
    Compare c1(10);
    c1.compare(10);

    Compare c2(5.5f);
    c2.compare(4.5f);

    return 0;
}
