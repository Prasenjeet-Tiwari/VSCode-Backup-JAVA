#include <iostream>
#include <queue>
#include <map>
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
    map<char,string> codes;

    void generateCodes(Node* node, string code) {

        if(node == NULL)
            return;

        if(node->left == NULL && node->right == NULL)
            codes[node->ch] = code;

        generateCodes(node->left, code + "0");
        generateCodes(node->right, code + "1");
    }

public:

    void buildTree(string dna) {

        map<char,int> freq;

        for(char c : dna)
            freq[c]++;

        priority_queue<Node*, vector<Node*>, Compare> pq;

        for(auto x : freq)
            pq.push(new Node(x.first, x.second));

        while(pq.size() > 1) {

            Node* left = pq.top(); pq.pop();
            Node* right = pq.top(); pq.pop();

            Node* newNode = new Node('$', left->freq + right->freq);

            newNode->left = left;
            newNode->right = right;

            pq.push(newNode);
        }

        root = pq.top();

        generateCodes(root, "");
    }

    string encode(string dna) {

        string encoded = "";

        for(char c : dna)
            encoded += codes[c];

        return encoded;
    }

    string decode(string encoded) {

        string decoded = "";
        Node* current = root;

        for(char bit : encoded) {

            if(bit == '0')
                current = current->left;
            else
                current = current->right;

            if(current->left == NULL && current->right == NULL) {
                decoded += current->ch;
                current = root;
            }
        }

        return decoded;
    }

};

int main() {

    string dna;

    cout << "Enter DNA sequence (A C G T): ";
    cin >> dna;

    Huffman h;

    h.buildTree(dna);

    string encoded = h.encode(dna);

    cout << "\nEncoded DNA:\n" << encoded << endl;

    string decoded = h.decode(encoded);

    cout << "\nDecoded DNA:\n" << decoded << endl;

}