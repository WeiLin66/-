#include "avltree.h"

int main(){

    AVLTree* tree = avl_createTree();
    for(int i=0; i<10; ++i){
        avl_insert(tree,i);
    }
    printf("is balanced: %d, is binary tree: %d\n",avl_isBalanced(tree),avl_isBST(tree));

    return 0;
}