#include "btree.h"

static bool lookup(treeNode_p node, int target){

    if(node == NULL){
        return false;
    }

    if(node->data == target){
        return true;
    }else if(node->data > target){
        return lookup(node->left,target);
    }

    return lookup(node->right,target);
}

treeNode_p bt_createTree(treeNode_p left, treeNode_p right, int data){

    treeNode_p ret = malloc(sizeof(treeNode));
    ret->left = left;
    ret->right = right;
    ret->data = data;

    return ret;
}

treeNode_p bt_insert(treeNode_p node, int value){

    if(node == NULL){
        return bt_createTree(NULL,NULL,value);
    }

    if(node->data <= value){
        node->right = bt_insert(node->right,value);
    }else{
        node->left = bt_insert(node->left,value);
    }

    return node;
}

int bt_size(treeNode_p node){

    if(node == NULL){
        return 0;
    }

    return bt_size(node->left) + bt_size(node->right) + 1;
}

int bt_maxDepth(treeNode_p node){

    if(node == NULL){
        return 0;
    }

    int left_hight = bt_maxDepth(node->left);
    int right_hight = bt_maxDepth(node->right);

    return (left_hight > right_hight ? left_hight : right_hight) + 1;
}

int bt_minValue(treeNode_p node){

    if(node == NULL){
        printf("node is NULL!\n");
        return -1;
    }

    while(node->left){
        node = node->left;
    }

    return node->data;
}

static int minValueR(treeNode_p node){

    if(node->left == NULL && node->right == NULL){
        return node->data;
    }else if(node->left == NULL){
        int rightValue = minValueR(node->right);
        return rightValue > node->data ? node->data : rightValue;
    }else if(node->right == NULL){
        int leftValue = minValueR(node->left);
        return leftValue > node->data ? node->data : leftValue;
    }else{
        int rightValue = minValueR(node->right);
        int leftValue = minValueR(node->left);
        leftValue = leftValue > node->data ? node->data : leftValue;
        return rightValue > leftValue ? leftValue : rightValue;
    }
}

int bt_minValueR(treeNode_p node){

    assert(node);

    return minValueR(node);
}

int bt_maxValue(treeNode_p node){

    if(node == NULL){
        printf("node is NULL!\n");
        return -1;        
    }

    while(node->right){
        node = node->right;
    }

    return node->data;    
}

static int maxValueR(treeNode_p node){

    if(node->left == NULL && node->right == NULL){
        return node->data;
    }else if(node->left == NULL){
        int rightValue = maxValueR(node->right);
        return rightValue < node->data ? node->data : rightValue;
    }else if(node->right == NULL){
        int leftValue = maxValueR(node->left);
        return leftValue < node->data ? node->data : leftValue;
    }else{
        int rightValue = maxValueR(node->right);
        int leftValue = maxValueR(node->left);
        leftValue = leftValue < node->data ? node->data : leftValue;
        return rightValue < leftValue ? leftValue : rightValue;
    }
}

int bt_maxValueR(treeNode_p node){

    assert(node);

    return maxValueR(node);
}

void bt_preOrder(treeNode_p node){

    if(node == NULL){
        return;
    }

    bt_preOrder(node->left);
    printf("%d ", node->data);
    bt_preOrder(node->right);
}

void bt_inOrder(treeNode_p node){

    if(node == NULL){
        return;
    }

    printf("%d ", node->data);
    bt_inOrder(node->left);
    bt_inOrder(node->right);
}

void bt_postOrder(treeNode_p node){

    if(node == NULL){
        return;
    }

    bt_postOrder(node->left);
    bt_postOrder(node->right);
    printf("%d ", node->data);
}

static void levelOrder(treeNode_p node, int level){

    if(node == NULL){
        return;
    }

    if(level == 0){
        printf("%d ", node->data);
    }else{
        levelOrder(node->left, level-1);
        levelOrder(node->right, level-1);
    }
}

void bt_levelOrder(treeNode_p node){

    if(node == NULL){
        return;
    }

    int hight = bt_maxDepth(node);

    for(int i=0; i<hight; ++i){
        levelOrder(node,i);
        printf("\n");
    }
}

bool bt_hasPathSum(treeNode_p node, int sum){

    if(node == NULL){
        return false;
    }

    sum -= node->data;

    if(sum == 0){
        return true;
    }

    return bt_hasPathSum(node->left,sum) || bt_hasPathSum(node->right,sum);
}

static void printPathRecur(treeNode_p node, int* path, int pathLen){

    if(node == NULL){
        return;
    }

    path[pathLen++] = node->data;

    if(node->left == NULL && node->right == NULL){
        path[0] += 1;
        printf("Path %d: ",path[0]);
        for(int i=1; i<pathLen; ++i){
            printf("%d ", path[i]);
        }
        printf("\n");
        return;
    }

    printPathRecur(node->left,path,pathLen);
    printPathRecur(node->right,path,pathLen);
}

void printPaths(treeNode_p node){

    int hight = bt_maxDepth(node);
    int path[hight+1];
    path[0] = 0;
    printPathRecur(node,path,1);
}

void bt_mirror(treeNode_p node){

    if(node == NULL){
        return;
    }

    treeNode_p tmp = node->left;
    node->left = node->right;
    node->right = tmp;

    bt_mirror(node->left);
    bt_mirror(node->right);
}

void bt_doubleTree(treeNode_p node){

    if(node == NULL){
        return;
    }

    treeNode_p leftNode = node->left;
    treeNode_p newNode = bt_createTree(leftNode,NULL,node->data);
    node->left = newNode;

    bt_doubleTree(leftNode);
    bt_doubleTree(node->right);
}

bool bt_sameTree(treeNode_p node1, treeNode_p node2){

    if(node1 == NULL && node2 == NULL){
        return true;
    }

    if(node1 == NULL){
        return false;
    }

    if(node2 == NULL){
        return false;
    }

    if(node1->data != node2->data){
        return false;
    }

    return bt_sameTree(node1->left,node2->left) && bt_sameTree(node1->right,node2->right);
}

int countTree(int numsKey){

    if(numsKey <= 1){
        return 1;
    }

    int memo[numsKey+1];
    memo[0] = 1;
    memo[1] = 1;

    for(int i=2; i<=numsKey; ++i){
        memo[i] = 0;
        for(int j=1; j<=i; ++j){
            memo[i] += memo[j-1]*memo[i-j];
        }
    }

    return memo[numsKey];
}

static void bt_isBSTHelper1(treeNode_p node, int* arr){

    if(node == NULL){
        return;
    }

    bt_isBSTHelper1(node->left,arr);
    arr[arr[0]] = node->data;
    arr[0] += 1;
    bt_isBSTHelper1(node->right,arr);
}

bool bt_isBST1(treeNode_p node){

    if(node == NULL){
        return true;
    }

    int size = bt_size(node);
    int array[size+1];
    array[0] = 1;
    bt_isBSTHelper1(node,array);

    for(int i=1; i<size; ++i){
        if(array[i] > array[i+1]){
            return false;
        }
    }

    return true;
}

bool bt_isBST2(treeNode_p node){

    if(node == NULL){
        return true;
    }

    int leftValue = bt_maxValueR(node->left);
    int rightValue = bt_minValueR(node->right);

    if(node->data >= leftValue && node->data < rightValue){
        return true;
    }

    return false;
}

static bool bt_isBSTHelper2(treeNode_p node, int max, int min){

    if(node == NULL){
        return true;
    }

    if(node->data >= max || node->data < min){
        return false;
    }

    return bt_isBSTHelper2(node->left,node->data,min) && bt_isBSTHelper2(node->right,max,node->data);
}

bool bt_isBST3(treeNode_p node){

    return bt_isBSTHelper2(node,INT_MAX,INT_MIN);
}