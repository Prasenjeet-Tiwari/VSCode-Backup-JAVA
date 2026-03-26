#include <bits/stdc++.h>
using namespace std;

struct Node {
    char ch;
    int freq;
    Node *left, *right;

    Node(char c, int f) {
        ch = c;
        freq = f;
        left = right = NULL;
    }
};

struct Compare {
    bool operator()(Node* a, Node* b) {
        return a->freq > b->freq;
    }
};

void printCodes(Node* root, string code) {
    if (!root) return;

    if (!root->left && !root->right)
        cout << root->ch << " : " << code << endl;

    printCodes(root->left, code + "0");
    printCodes(root->right, code + "1");
}

int main() {
    int k;
    cout << "Enter number of characters: ";
    cin >> k;

    vector<char> chars(k);
    vector<int> freq(k);

    cout << "Enter characters:\n";
    for(int i = 0; i < k; i++)
        cin >> chars[i];

    cout << "Enter frequencies:\n";
    for(int i = 0; i < k; i++)
        cin >> freq[i];

    priority_queue<Node*, vector<Node*>, Compare> pq;

    for(int i = 0; i < k; i++)
        pq.push(new Node(chars[i], freq[i]));

    while(pq.size() > 1) {
        Node* left = pq.top(); pq.pop();
        Node* right = pq.top(); pq.pop();

        Node* newNode = new Node('$', left->freq + right->freq);
        newNode->left = left;
        newNode->right = right;

        pq.push(newNode);
    }

    Node* root = pq.top();

    cout << "\nHuffman Codes:\n";
    printCodes(root, "");

    return 0;
}