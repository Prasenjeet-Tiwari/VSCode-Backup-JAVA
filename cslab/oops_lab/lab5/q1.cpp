#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

class EmptyMessageException {
public:
    string what() {
        return "Empty message! Please enter a valid message.";
    }
};

class Chatbot {
private:
    string message;
    string bad[3] = {"stupid", "idiot", "hate"};
    string good[3] = {"silly", "foolish", "dislike"};

public:
    void setMessage(string msg) {
        message = msg;
    }

    string toLower(string s) {
        transform(s.begin(), s.end(), s.begin(), ::tolower);
        return s;
    }

    string processMessage() {
        if (message.empty()) {
            throw EmptyMessageException();
        }

        string result = message;
        string lower = toLower(result);

        // replace bad words
        for (int i = 0; i < 3; i++) {
            size_t pos;
            while ((pos = lower.find(bad[i])) != string::npos) {
                result.replace(pos, bad[i].length(), good[i]);
                lower.replace(pos, bad[i].length(), good[i]);
            }
        }

        return result;
    }
};

int main() {
    Chatbot bot;

    try {
        bot.setMessage("I hate waiting so long!");
        cout << "Chatbot: " << bot.processMessage() << endl;

        bot.setMessage("");  // empty case
        cout << bot.processMessage() << endl;

    } catch (EmptyMessageException &e) {
        cout << "Error: " << e.what() << endl;
    }

    return 0;
}