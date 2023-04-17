#include "btree.h"

int main(){

    CREATE_TREE(n1,NULL,NULL,7);
    CREATE_TREE(n2,NULL,NULL,5);
    CREATE_TREE(n3,NULL,NULL,10);
    CREATE_TREE(n4,NULL,NULL,4);
    CREATE_TREE(n5,NULL,NULL,8);

    n1->left = n2;
    n1->right = n3;

    n2->left = n4;
    n2->right = n5;

    printf("%d\n",TREE_ISBST(n1));

    return 0;
}