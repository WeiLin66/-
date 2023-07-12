#ifndef __AVLTREE_H_
#define __AVLTREE_H_

#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <limits.h>

typedef struct Node{
    int value;
    struct Node* left;
    struct Node* right;
    int height;
}Node;

typedef struct AVLTree{
    Node* root;
    int size;
}AVLTree;

AVLTree* avl_createTree(void);
void avl_insert(AVLTree* tree, int value);
void avl_remove(AVLTree* tree, int value);
bool avl_isBalanced(AVLTree* tree);
bool avl_isBST(AVLTree* tree);

#endif /* __AVLTREE_H_ */