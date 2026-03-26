#include <iostream>
#include <string>
using namespace std;

class BankAccount {
protected:
    int accNo;
    double balance;

public:
    BankAccount(int a = 0, double b = 0) {
        accNo = a;
        balance = b;
    }

    virtual void display() const {
        cout << "AccNo: " << accNo << " Balance: " << balance;
    }

    friend ostream& operator<<(ostream& out, const BankAccount& b) {
        b.display();
        return out;
    }
};

class CheckingAccount : public BankAccount {
public:
    CheckingAccount(int a = 0, double b = 0)
        : BankAccount(a, b) {}

    void display() const override {
        cout << "[Checking] ";
        BankAccount::display();
    }
};

class SavingsAccount : public BankAccount {
public:
    SavingsAccount(int a = 0, double b = 0)
        : BankAccount(a, b) {}

    void display() const override {
        cout << "[Savings] ";
        BankAccount::display();
    }
};

class CheckingAccountWithInterest : public CheckingAccount {
    double interest;

public:
    CheckingAccountWithInterest(int a = 0, double b = 0, double i = 0)
        : CheckingAccount(a, b), interest(i) {}

    void display() const override {
        cout << "[Checking+Interest] ";
        BankAccount::display();
        cout << " Interest: " << interest << "%";
    }
};

template <class T>
void produceReport(string title, T arr[], int n) {
    cout << "\n===== " << title << " =====\n";

    int dashCount = sizeof(T);

    for (int i = 0; i < n; i++) {
        cout << arr[i] << endl;
        for (int j = 0; j < dashCount; j++)
            cout << "-";
        cout << endl;
    }

    cout << "End of Report\n";
}

int main() {
    CheckingAccountWithInterest cwi[5] = {
        {101, 5000, 3.5}, {102, 6000, 4},
        {103, 4500, 3}, {104, 7000, 4.5},
        {105, 8000, 5}
    };

    CheckingAccount ca[5] = {
        {201, 3000}, {202, 4000},
        {203, 3500}, {204, 2500},
        {205, 4500}
    };

    SavingsAccount sa[5] = {
        {301, 8000}, {302, 9000},
        {303, 8500}, {304, 9500},
        {305, 10000}
    };

    int iarr[5] = {10, 20, 30, 40, 50};
    string sarr[5] = {"One", "Two", "Three", "Four", "Five"};

    produceReport("Checking With Interest", cwi, 5);
    produceReport("Checking Accounts", ca, 5);
    produceReport("Savings Accounts", sa, 5);
    produceReport("Integer Report", iarr, 5);
    produceReport("String Report", sarr, 5);

    return 0;
}
