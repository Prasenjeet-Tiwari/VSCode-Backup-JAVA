#include <iostream>
#include <vector>
#include <stack>
using namespace std;

int main() {
    int n;
    cout << "Enter number of plants: ";
    cin >> n;

    vector<int> plants(n);
    cout << "Enter pesticide levels: ";
    for (int i = 0; i < n; i++) cin >> plants[i];

    vector<int> days(n, 0);

    stack<int> st;  
    int maxDays = 0;

    for (int i = 0; i < n; i++) {
        int d = 0;

        while (!st.empty() && plants[i] <= plants[st.top()]) {
            d = max(d, days[st.top()]);  
            st.pop();
        }

        if (!st.empty()) {
            days[i] = d + 1;          
            maxDays = max(maxDays, days[i]); 
        }

        st.push(i);  
    }

    cout << "Days until no plants die: " << maxDays << endl;
    return 0;
}

