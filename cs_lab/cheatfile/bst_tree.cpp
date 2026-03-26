#include <bits/stdc++.h>
using namespace std;

class Node {
public:
    int data;
    Node* left;
    Node* right;
    Node(int data){
        this->data = data;
        left = right = nullptr;
    }
};

//---------------------------------------
// BST Logical Core
//---------------------------------------
class BST {
public:

    Node* insert(Node* root, int value){
        if(root == nullptr) return new Node(value);

        if(value < root->data) 
            root->left = insert(root->left, value);

        else if(value > root->data) 
            root->right = insert(root->right, value);

        return root;
    }

    bool search(Node* root, int value){
        if(root == nullptr) return false;
        if(root->data == value) return true;

        return (value < root->data) ? 
                search(root->left, value) : search(root->right, value);
    }

    Node* findMin(Node* root){
        while(root && root->left != nullptr)
            root = root->left;
        return root;
    }

    Node* deleteNode(Node* root, int value){
        if(root == nullptr) return nullptr;

        if(value < root->data)
            root->left = deleteNode(root->left, value);

        else if(value > root->data)
            root->right = deleteNode(root->right, value);

        else { 
            if(root->left == nullptr){
                Node* temp = root->right;
                delete root;
                return temp;
            }
            else if(root->right == nullptr){
                Node* temp = root->left;
                delete root;
                return temp;
            }

            Node* temp = findMin(root->right);
            root->data = temp->data;
            root->right = deleteNode(root->right, temp->data);
        }

        return root;
    }

    bool isBST(Node* root, long minVal, long maxVal){
        if(root == nullptr) return true;

        if(root->data <= minVal || root->data >= maxVal)
            return false;

        return isBST(root->left, minVal, root->data)
            && isBST(root->right, root->data, maxVal);
    }
};

//---------------------------------------
// Tree → User-Level Interface
//---------------------------------------
class Tree {
public:
    Node* root;
    BST bst;

    Tree(){ root = nullptr; }

    void insert(int val){
        root = bst.insert(root, val);
        cout << "Inserted successfully." << endl;
    }

    void search(int val){
        if(bst.search(root, val))
            cout << "Element FOUND.\n";
        else
            cout << "Element NOT found.\n";
    }

    void deleteVal(int val){
        root = bst.deleteNode(root, val);
        cout << "Deleted (if existed).\n";
    }

    void inorder(Node* root){
        if(root == nullptr) return;
        inorder(root->left);
        cout << root->data << " ";
        inorder(root->right);
    }

    void preorder(Node* root){
        if(root == nullptr) return;
        cout << root->data << " ";
        preorder(root->left);
        preorder(root->right);
    }

    void postorder(Node* root){
        if(root == nullptr) return;
        postorder(root->left);
        postorder(root->right);
        cout << root->data << " ";
    }

    void checkBST(){
        if(bst.isBST(root, LONG_MIN, LONG_MAX))
            cout << "YES, this is a valid BST.\n";
        else
            cout << "NO, this does NOT satisfy BST properties.\n";
    }
};

//---------------------------------------
// MAIN PROGRAM (Menu Driven)
//---------------------------------------
int main(){

    Tree t;
    int choice, val;

    while(true){
        cout << "\n===== BST MENU =====\n";
        cout << "1. Insert\n";
        cout << "2. Search\n";
        cout << "3. Delete\n";
        cout << "4. Traversals\n";
        cout << "5. Check BST Validity\n";
        cout << "6. Exit\n";
        cout << "Enter choice: ";
        cin >> choice;

        switch(choice){
            case 1:
                cout << "Enter value: ";
                cin >> val;
                t.insert(val);
                break;

            case 2:
                cout << "Enter value: ";
                cin >> val;
                t.search(val);
                break;

            case 3:
                cout << "Enter value: ";
                cin >> val;
                t.deleteVal(val);
                break;

            case 4:
                cout << "\nInorder: ";
                t.inorder(t.root);
                cout << "\nPreorder: ";
                t.preorder(t.root);
                cout << "\nPostorder: ";
                t.postorder(t.root);
                cout << endl;
                break;

            case 5:
                t.checkBST();
                break;

            case 6:
                cout << "Exiting program...\n";
                return 0;

            default:
                cout << "Invalid choice, try again.\n";
        }
    }
}
