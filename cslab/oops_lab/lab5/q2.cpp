#include <iostream>
#include <string>
#include <fstream>
#include <algorithm>
using namespace std;

class InvalidMessageException {
public:
    string what() {
        return "Invalid message: empty or contains restricted words.";
    }
};

class SecureMessage {
private:
    string message;
    string restricted[3] = {"hack", "attack", "breach"};

public:
    void setMessage(string msg) {
        message = msg;
    }

    string encrypt() {
        if (message.empty()) {
            throw InvalidMessageException();
        }

        // convert to lowercase for checking
        string lower = message;
        transform(lower.begin(), lower.end(), lower.begin(), ::tolower);

        // check restricted words
        for (int i = 0; i < 3; i++) {
            if (lower.find(restricted[i]) != string::npos) {
                throw InvalidMessageException();
            }
        }

        // Caesar cipher (+3)
        string result = "";
        for (char c : message) {
            if (isalpha(c)) {
                if (islower(c))
                    result += (c - 'a' + 3) % 26 + 'a';
                else
                    result += (c - 'A' + 3) % 26 + 'A';
            } else {
                result += c;
            }
        }

        return result;
    }

    void saveToFile(string text) {
        ofstream file("messages.txt", ios::app);
        file << text << endl;
    }
};

int main() {
    SecureMessage sm;

    try {
        sm.setMessage("Send secure data now.");
        string enc = sm.encrypt();

        cout << "Encrypted: " << enc << endl;
        sm.saveToFile(enc);

        sm.setMessage("");  // test invalid
        sm.encrypt();

    } catch (InvalidMessageException &e) {
        cout << "Error: " << e.what() << endl;
    }

    return 0;
}