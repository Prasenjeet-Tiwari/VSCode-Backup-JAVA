#include <iostream>
#include <string>
#include <vector>
using namespace std;

class BankAccount {
private:
    string depositorName;
    long accountNumber;
    string accountType;
    double balance;

public:
    // Assign initial values
    void assignInitialValues(const string& name, long accNum, const string& type, double initialBalance) {
        depositorName = name;
        accountNumber = accNum;
        accountType = type;
        balance = initialBalance;
    }

    // Deposit amount
    void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            cout << "Deposited $" << amount 
                 << ". New balance: $" << balance << endl;
        } else {
            cout << "Invalid deposit amount." << endl;
        }
    }

    // Withdraw amount
    void withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            cout << "Withdrew $" << amount 
                 << ". New balance: $" << balance << endl;
        } else {
            cout << "Insufficient funds or invalid amount." << endl;
        }
    }

    // Display account details
    void display() const {
        cout << "Name: " << depositorName
             << ", Account No: " << accountNumber
             << ", Account Type: " << accountType
             << ", Balance: $" << balance << endl;
    }
};

int main() {

    // ----- Single customer -----
    BankAccount customer1;
    customer1.assignInitialValues("Alice", 12345, "Savings", 1000.0);
    customer1.deposit(500.0);
    customer1.withdraw(200.0);
    customer1.display();

    cout << endl;

    // ----- Multiple customers (10) -----
    vector<BankAccount> customers(10);

    for (int i = 0; i < 10; i++) {
        string name;
        cout << "Enter name of customer " << i + 1 << ": ";
        cin >> name;

        customers[i].assignInitialValues(
            name,
            10000 + i,        // unique account number
            "Current",
            2000.0
        );

        customers[i].display();
    }

    return 0;
}
