#include "avltree.h"

static __inline int max(int a, int b){

    return a > b ? a : b;
}

static __inline int getHeight(Node* node){

    if(node == NULL){
        return 0;
    }
    return node->height;
}

static __inline int getBalanceFactor(Node* node){

    if(node == NULL){
        return 0;
    }
    return getHeight(node->left) - getHeight(node->right);
}

static Node* newNode(int value){

    Node* node = (Node*)malloc(sizeof(Node));
    node->value = value;
    node->left = NULL;
    node->right = NULL;
    node->height = 1;

    return node;
}

static Node* rightRotate(Node* y){

    Node* x = y->left;
    Node* T3 = x->right;

    x->right = y;
    y->left = T3;

    y->height = max(getHeight(y->left),getHeight(y->right)) + 1;
    x->height = max(getHeight(x->left),getHeight(x->right)) + 1;

    return x;
}

static Node* leftRotate(Node* y){

    Node* x = y->right;
    Node* T2 = x->left;

    x->left = y;
    y->right = T2;

    y->height = max(getHeight(y->left),getHeight(y->right)) + 1;
    x->height = max(getHeight(x->left),getHeight(x->right)) + 1;

    return x;
}

static Node* add(Node* node, int value, int* size){

    if(node == NULL){
        ++(*size);
        return newNode(value);
    }
    if(value < node->value){
        node->left = add(node->left,value,size);
    }else if(value > node->value){
        node->right = add(node->right,value,size);
    }

    node->height = 1 + max(getHeight(node->left),getHeight(node->right));

    int balanceFactor = getBalanceFactor(node);

    if(balanceFactor > 1 && (value < node->left->value)){
        return rightRotate(node);
    }
    if(balanceFactor < -1 && (value > node->right->value)){
        return leftRotate(node);
    }
    if(balanceFactor > 1 && (value > node->left->value)){
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }
    if(balanceFactor < -1 && (value < node->right->value)){
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

void avl_insert(AVLTree* tree, int value){

    tree->root = add(tree->root,value,&(tree->size));
}

static Node* minValueNode(Node* node){

    Node* current = node;
    while(current->left != NULL){
        current = current->left;
    }

    return current;
}

static Node* removeNode(Node* root, int value, int* size){

    if(root == NULL){
        return root;
    }
    if(value < root->value){
        root->left = removeNode(root->left,value,size);
    }else if(value > root->value){
        root->right = removeNode(root->right,value,size);
    }else{
        (*size)--;
        if((root->left == NULL) || (root->right == NULL)){
            Node* temp = root->left ? root->left : root->right;
            if(temp == NULL){
                temp =  root;
                root = NULL;
            }else{
                *root = *temp;
            }
            free(temp);
        }else{
            Node* temp = minValueNode(root->right);
            root->value = temp->value;
            root->right = removeNode(root->right,temp->value,size);
        }
    }
    if(root == NULL){ 
        return root;
    }

    root->height = 1 + max(getHeight(root->left),getHeight(root->right));

    int balance = getBalanceFactor(root);

    if(balance > 1 && getBalanceFactor(root->left) >= 0){ 
        return rightRotate(root);
    }
    if(balance > 1 && getBalanceFactor(root->left) < 0){
        root->left = leftRotate(root->left);
        return rightRotate(root);
    }
    if(balance < -1 && getBalanceFactor(root->right) <= 0){
        return leftRotate(root);
    }
    if(balance < -1 && getBalanceFactor(root->right) > 0){
        root->right = rightRotate(root->right);
        return leftRotate(root);
    }

    return root;
}

void avl_remove(AVLTree* tree, int value){

    tree->root = removeNode(tree->root,value,&(tree->size));
}

static bool isBalanced(Node* node){

    if(node == NULL){
        return true;
    }
    if(abs(getBalanceFactor(node)) > 1){
        return false;
    }

    return isBalanced(node->left) &&  isBalanced(node->right);
}

bool avl_isBalanced(AVLTree* tree){

    return isBalanced(tree->root);
}

static bool isBST(Node* node, int max, int min){

    if(node == NULL){
        return true;
    }
    if(node->value < min || node->value > max){
        return false;
    }

    return isBST(node->left,node->value,min) && isBST(node->right,max,node->value);
}

bool avl_isBST(AVLTree* tree){

    return isBST(tree->root,INT_MAX,INT_MIN);
}

AVLTree* avl_createTree(){

    AVLTree* tree = (AVLTree*)malloc(sizeof(AVLTree));
    tree->root = NULL;
    tree->size = 0;
    return tree;
}
