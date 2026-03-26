#include <iostream>
using namespace std;

class Salesperson;

class Sale {
private:
    int day;
    float amount;
    int salespersonID;

public:
    Sale(int d, float a, int id) {
        day = d;
        amount = a;
        salespersonID = id;
    }

    friend void display(const Sale&, const Salesperson&);
};

class Salesperson {
private:
    int id;
    string lastName;

public:
    Salesperson(int i, string name) {
        id = i;
        lastName = name;
    }

    friend void display(const Sale&, const Salesperson&);
};

void display(const Sale& s, const Salesperson& sp) {
    cout << "Date: " << s.day << endl;
    cout << "Amount: " << s.amount << endl;
    cout << "Salesperson ID: " << sp.id << endl;
    cout << "Salesperson Name: " << sp.lastName << endl;
}

int main() {
    Sale s(15, 2500.75, 101);
    Salesperson sp(101, "Sharma");

    display(s, sp);
    return 0;
}
