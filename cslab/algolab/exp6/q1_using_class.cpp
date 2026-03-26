#include <iostream>
#include <queue>
#include <vector>
using namespace std;

class Node {
public:
    char ch;
    int freq;
    Node* left;
    Node* right;

    Node(char c, int f) {
        ch = c;
        freq = f;
        left = right = NULL;
    }
};

class Compare {
public:
    bool operator()(Node* a, Node* b) {
        return a->freq > b->freq;
    }
};

class Huffman {
private:
    Node* root;

    void printCodes(Node* node, string code) {
        if (node == NULL)
            return;

        if (!node->left && !node->right)
            cout << node->ch << " : " << code << endl;

        printCodes(node->left, code + "0");
        printCodes(node->right, code + "1");
    }

public:

    void buildTree(vector<char> chars, vector<int> freq) {

        priority_queue<Node*, vector<Node*>, Compare> pq;

        for (int i = 0; i < chars.size(); i++) {
            pq.push(new Node(chars[i], freq[i]));
        }

        while (pq.size() > 1) {

            Node* left = pq.top(); pq.pop();
            Node* right = pq.top(); pq.pop();

            Node* newNode = new Node('$', left->freq + right->freq);
            newNode->left = left;
            newNode->right = right;

            pq.push(newNode);
        }

        root = pq.top();
    }

    void showCodes() {
        cout << "\nHuffman Codes:\n";
        printCodes(root, "");
    }
};

int main() {

    int k;
    cout << "Enter number of characters: ";
    cin >> k;

    vector<char> chars(k);
    vector<int> freq(k);

    cout << "Enter characters:\n";
    for (int i = 0; i < k; i++)
        cin >> chars[i];

    cout << "Enter frequencies:\n";
    for (int i = 0; i < k; i++)
        cin >> freq[i];

    Huffman h;
    h.buildTree(chars, freq);
    h.showCodes();

    return 0;
}