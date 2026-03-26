#include <iostream>
#include <stack>
#include <cctype>
using namespace std;

int prec(char c) {
    if(c == '^') return 3;
    if(c == '*' || c == '/') return 2;
    if(c == '+' || c == '-') return 1;
    return -1;
}

string infixToPostfix(string s) {
    stack<char> st;
    string result = "";

    for(char c: s) {
        if(isalnum(c)) result += c;
        else if(c == '(') st.push(c);
        else if(c == ')') {
            while(!st.empty() && st.top() != '(') {
                result += st.top();
                st.pop();
            }
            st.pop();
        } else {
            while(!st.empty() && prec(c) <= prec(st.top())) {
                result += st.top();
                st.pop();
            }
            st.push(c);
        }
    }

    while(!st.empty()) {
        result += st.top();
        st.pop();
    }
    return result;
}

int main() {
    string exp;
    cout << "Enter Infix Expression: ";
    cin >> exp;

    cout << "Postfix Expression: " << infixToPostfix(exp) << endl;
    return 0;
}
