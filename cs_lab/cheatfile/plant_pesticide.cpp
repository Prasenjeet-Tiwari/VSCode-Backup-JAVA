#include <iostream>
#include <vector>
#include <stack>
using namespace std;

int main() {
    int n;
    cout << "Enter number of plants: ";
    cin >> n;

    vector<int> arr(n);
    cout << "Enter pesticide levels: ";
    for(int i = 0; i < n; i++) cin >> arr[i];

    vector<int> days(n, 0);
    stack<int> st;
    st.push(0);

    int maxDays = 0;

    for(int i = 1; i < n; i++) {
        int day = 0;
        while(!st.empty() && arr[i] <= arr[st.top()])
            st.pop();

        if(!st.empty())
            day = days[st.top()] + 1;

        days[i] = day;
        maxDays = max(maxDays, day);
        st.push(i);
    }

    cout << "Days until no plant dies: " << maxDays << endl;
    return 0;
}
