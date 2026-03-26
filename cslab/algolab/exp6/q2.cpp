#include <iostream>
#include <queue>
#include <map>
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

void generateCodes(Node* root, string code, map<char,string>& huffman) {
    if(!root) return;

    if(!root->left && !root->right)
        huffman[root->ch] = code;

    generateCodes(root->left, code + "0", huffman);
    generateCodes(root->right, code + "1", huffman);
}

string encode(string s, map<char,string>& huffman) {
    string encoded = "";
    for(char c : s)
        encoded += huffman[c];
    return encoded;
}

string decode(string encoded, Node* root) {
    string decoded = "";
    Node* curr = root;

    for(char bit : encoded) {
        if(bit == '0')
            curr = curr->left;
        else
            curr = curr->right;

        if(!curr->left && !curr->right) {
            decoded += curr->ch;
            curr = root;
        }
    }

    return decoded;
}

int main() {

    string genetic;
    cout << "Enter genetic code: ";
    cin >> genetic;

    map<char,int> freq;

    for(char c : genetic)
        freq[c]++;

    priority_queue<Node*, vector<Node*>, Compare> pq;

    for(auto it : freq)
        pq.push(new Node(it.first, it.second));

    while(pq.size() > 1) {
        Node* left = pq.top(); pq.pop();
        Node* right = pq.top(); pq.pop();

        Node* newNode = new Node('$', left->freq + right->freq);
        newNode->left = left;
        newNode->right = right;

        pq.push(newNode);
    }

    Node* root = pq.top();

    map<char,string> huffman;
    generateCodes(root, "", huffman);

    string encoded = encode(genetic, huffman);

    cout << "Encoded Huffman Code:\n";
    cout << encoded << endl;

    cout << "Decoded String:\n";
    cout << decode(encoded, root) << endl;

    return 0;
}