#include <iostream>
using namespace std;

// ---------------- NODE CLASS ----------------
class Node {
public:
    int data;
    Node* left;
    Node* right;

    Node(int value) {
        data = value;
        left = right = nullptr;
    }
};

// ---------------- TREE (BST + Traversals) ----------------
class Tree {
public:
    Node* root;

    Tree() {
        root = nullptr;
    }

    // INSERT
    Node* insert(Node* root, int value) {
        if (root == nullptr)
            return new Node(value);

        if (value < root->data)
            root->left = insert(root->left, value);
        else if (value > root->data)
            root->right = insert(root->right, value);

        return root;
    }

    void insert(int value) {
        root = insert(root, value);
    }

    // SEARCH
    bool search(Node* root, int key) {
        if (!root) return false;
        if (root->data == key) return true;

        return (key < root->data) ? search(root->left, key) : search(root->right, key);
    }

    bool search(int key) {
        return search(root, key);
    }

    // FIND MIN (HELPER FOR DELETE)
    Node* findMin(Node* root) {
        while (root && root->left != nullptr)
            root = root->left;
        return root;
    }

    // DELETE
    Node* deleteNode(Node* root, int key) {
        if (!root) return root;

        if (key < root->data)
            root->left = deleteNode(root->left, key);
        else if (key > root->data)
            root->right = deleteNode(root->right, key);
        else {

            // Case 1: No child
            if (!root->left && !root->right) {
                delete root;
                return nullptr;
            }

            // Case 2: One child
            if (!root->left) {
                Node* temp = root->right;
                delete root;
                return temp;
            }
            if (!root->right) {
                Node* temp = root->left;
                delete root;
                return temp;
            }

            // Case 3: Two children
            Node* temp = findMin(root->right);
            root->data = temp->data;
            root->right = deleteNode(root->right, temp->data);
        }
        return root;
    }

    void remove(int key) {
        root = deleteNode(root, key);
    }

    // TRAVERSALS
    void inorder(Node* root) {
        if (!root) return;
        inorder(root->left);
        cout << root->data << " ";
        inorder(root->right);
    }

    void preorder(Node* root) {
        if (!root) return;
        cout << root->data << " ";
        preorder(root->left);
        preorder(root->right);
    }

    void postorder(Node* root) {
        if (!root) return;
        postorder(root->left);
        postorder(root->right);
        cout << root->data << " ";
    }
};

// ---------------- MAIN ----------------
int main() {

    Tree t;
    int arr[] = {4, 2, 3, 1, 6, 5};
    int len = sizeof(arr) / sizeof(arr[0]);

    cout << "Inserting elements...\n";
    for (int i = 0; i < len; i++)
        t.insert(arr[i]);

    cout << "\nInorder Traversal: ";
    t.inorder(t.root);

    cout << "\nPreorder Traversal: ";
    t.preorder(t.root);

    cout << "\nPostorder Traversal: ";
    t.postorder(t.root);

    cout << "\n\nSearching 6 → "
         << (t.search(6) ? "Found" : "Not Found");

    cout << "\nDeleting 2...\n";
    t.remove(2);

    cout << "Inorder After Deletion: ";
    t.inorder(t.root);

    cout << endl;
    return 0;
}
