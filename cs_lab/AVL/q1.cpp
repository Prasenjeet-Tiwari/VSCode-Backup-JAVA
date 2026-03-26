#include <bits/stdc++.h>
using namespace std;

class Node {
public:
    int key;
    Node* left;
    Node* right;
    int height;

    Node(int val) {
        key = val;
        left = right = nullptr;
        height = 1;
    }
};

int getHeight(Node* n) {
    return n ? n->height : 0;
}

int getBalanceFactor(Node* n) {
    return n ? getHeight(n->left) - getHeight(n->right) : 0;
}

Node* rightRotate(Node* y) {
    Node* x = y->left;
    Node* T2 = x->right;

    // Perform rotation
    x->right = y;
    y->left = T2;

    // Update heights
    y->height = 1 + max(getHeight(y->left), getHeight(y->right));
    x->height = 1 + max(getHeight(x->left), getHeight(x->right));

    return x; // new root
}

Node* leftRotate(Node* x) {
    Node* y = x->right;
    Node* T2 = y->left;

    // Perform rotation
    y->left = x;
    x->right = T2;

    // Update heights
    x->height = 1 + max(getHeight(x->left), getHeight(x->right));
    y->height = 1 + max(getHeight(y->left), getHeight(y->right));

    return y; // new root
}

Node* insert(Node* root, int key) {
    // 1. Perform normal BST insert
    if (!root)
        return new Node(key);

    if (key < root->key)
        root->left = insert(root->left, key);
    else if (key > root->key)
        root->right = insert(root->right, key);
    else
        return root; // duplicates not allowed

    // 2. Update height
    root->height = 1 + max(getHeight(root->left), getHeight(root->right));

    // 3. Get balance factor
    int balance = getBalanceFactor(root);

    // 4. Balance tree if needed
    // Left Left
    if (balance > 1 && key < root->left->key)
        return rightRotate(root);

    // Right Right
    if (balance < -1 && key > root->right->key)
        return leftRotate(root);

    // Left Right
    if (balance > 1 && key > root->left->key) {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }

    // Right Left
    if (balance < -1 && key < root->right->key) {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }

    return root; // unchanged
}

Node* minValueNode(Node* node) {
    Node* current = node;
    while (current->left)
        current = current->left;
    return current;
}

Node* deleteNode(Node* root, int key) {
    if (!root)
        return root;

    if (key < root->key)
        root->left = deleteNode(root->left, key);
    else if (key > root->key)
        root->right = deleteNode(root->right, key);
    else {
        // Node with 1 or no child
        if (!root->left || !root->right) {
            Node* temp = root->left ? root->left : root->right;
            delete root;
            return temp;
        } else {
            Node* temp = minValueNode(root->right);
            root->key = temp->key;
            root->right = deleteNode(root->right, temp->key);
        }
    }

    // Update height
    root->height = 1 + max(getHeight(root->left), getHeight(root->right));

    // Get balance
    int balance = getBalanceFactor(root);

    // Rebalance if needed
    if (balance > 1 && getBalanceFactor(root->left) >= 0)
        return rightRotate(root);

    if (balance > 1 && getBalanceFactor(root->left) < 0) {
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }

    if (balance < -1 && getBalanceFactor(root->right) <= 0)
        return leftRotate(root);

    if (balance < -1 && getBalanceFactor(root->right) > 0) {
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }

    return root;
}

bool search(Node* root, int key) {
    if (!root)
        return false;
    if (key == root->key)
        return true;
    else if (key < root->key)
        return search(root->left, key);
    else{
        return search(root->right, key);
    }
}

void inorder(Node* root) {
    if (!root) return;
    inorder(root->left);
    cout << root->key << "(" << getBalanceFactor(root) << ") ";
    inorder(root->right);
}

void preorder(Node* root) {
    if (!root) return;
    cout << root->key << "(" << getBalanceFactor(root) << ") ";
    preorder(root->left);
    preorder(root->right);
}

void postorder(Node* root) {
    if (!root) return;
    postorder(root->left);
    postorder(root->right);
    cout << root->key << "(" << getBalanceFactor(root) << ") ";
}

int main() {
    Node* root = nullptr;
    int choice, key;

    cout << "AVL Tree Operations:\n";
    cout << "1. Insert\n2. Delete\n3. Search\n4. Traversals\n5. Exit\n";

    do {
        cout << "\nEnter choice: ";
        cin >> choice;

        switch (choice) {
        case 1:
            cout << "Enter value to insert: ";
            cin >> key;
            root = insert(root, key);
            break;

        case 2:
            cout << "Enter value to delete: ";
            cin >> key;
            root = deleteNode(root, key);
            break;

        case 3:
            cout << "Enter value to search: ";
            cin >> key;
            cout << (search(root, key) ? "Found!" : "Not found!") << endl;
            break;

        case 4:
            cout << "\nInorder   : "; inorder(root);
            cout << "\nPreorder  : "; preorder(root);
            cout << "\nPostorder : "; postorder(root);
            cout << endl;
            break;

        case 5:
            cout << "Exiting...\n";
            break;

        default:
            cout << "Invalid choice!\n";
        }   

    } while (choice != 5);

    return 0;
}
