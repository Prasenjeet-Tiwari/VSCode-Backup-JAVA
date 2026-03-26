#include <iostream>
#include <iomanip>
using namespace std;

class Time {
private:
    int hours;
    int minutes;

public:
    // Constructor
    Time(int h, int m = 0) {
        if (h < 0 || h > 23) hours = 23;
        else hours = h;

        if (m < 0 || m > 59) minutes = 59;
        else minutes = m;
    }

    // Function to display time
    void display() {
        cout << setw(2) << setfill('0') << hours << ":"
             << setw(2) << setfill('0') << minutes << endl;
    }

    // Add minutes
    void addMinutes(int m) {
        int total = hours * 60 + minutes + m;
        total = (total + 1440) % 1440; // handle rollover
        hours = total / 60;
        minutes = total % 60;
    }

    // Destructor
    ~Time() {
        // Destructor defined as required
    }
};

int main() {
    Time t[4] = {
        Time(12, 30),
        Time(14, 50),
        Time(23, 59),
        Time(3)
    };

    cout << "Before adding minutes:\n";
    for (int i = 0; i < 4; i++)
        t[i].display();

    t[0].addMinutes(15);
    t[1].addMinutes(20);
    t[2].addMinutes(2);
    t[3].addMinutes(60);

    cout << "\nAfter adding minutes:\n";
    for (int i = 0; i < 4; i++)
        t[i].display();

    return 0;
}
