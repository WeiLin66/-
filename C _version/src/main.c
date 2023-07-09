<<<<<<< HEAD
#include "avltree.h"

int main(){

    AVLTree* tree = avl_createTree();
    for(int i=0; i<10; ++i){
        avl_insert(tree,i);
    }
    printf("is balanced: %d, is binary tree: %d\n",avl_isBalanced(tree),avl_isBST(tree));
=======
#include "sort.h"

CREATE_ARRAY(arr);
CREATE_ARRAY(arr2);

int main(){

    GENERATE_ARRAY(arr);
    COPY_ARRAY(arr2,arr);

    SORTING_TEST(shellSort4,arr);
    SORTING_TEST(hp_heapSort2,arr2);
>>>>>>> refs/remotes/origin/main

    return 0;
}