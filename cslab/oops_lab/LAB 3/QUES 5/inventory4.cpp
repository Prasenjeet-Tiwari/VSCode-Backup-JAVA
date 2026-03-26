#include <iostream>
using namespace std;

class Inventory {
    int stock;
    int quantity;
    float price;

public:
    Inventory() {
        stock = 0;
        quantity = 0;
        price = 0.0;
    }

    friend istream& operator>>(istream& in, Inventory& obj) {
        in >> obj.stock >> obj.quantity >> obj.price;

        if (obj.stock < 0 || obj.stock > 999)
            throw "Invalid Stock";

        if (obj.quantity < 0)
            throw obj.quantity;

        if (obj.price > 100)
            throw obj.price;

        return in;
    }

    friend ostream& operator<<(ostream& out, Inventory& obj) {
        out << "Stock: " << obj.stock
            << " Quantity: " << obj.quantity
            << " Price: " << obj.price << endl;
        return out;
    }
};

int main() {
    Inventory inv[5];
    int count = 0;

    for (int i = 0; i < 5; i++) {
        cout << "Enter stock, quantity, price: ";
        try {
            cin >> inv[i];
            count++;
        }
        catch (...) {
            cout << "Exception occurred → Stopping input\n";
            break;
        }
    }

    cout << "\nValid Inventory Data:\n";
    for (int i = 0; i < count; i++)
        cout << inv[i];

    return 0;
}
