#ifndef __BTREE_H_
#define __BTREE_H_

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>
#include <limits.h>

#define CREATE_TREE(name,left,right,value)      treeNode_p name=bt_createTree(left,right,value)
#define TREE_INSERT(node,value)                 bt_insert(node,value)
#define TREE_SIZE(node)                         bt_size(node)
#define TREE_DEPTH(node)                        bt_maxDepth(node)
#define TREE_MIN_VALUE(node)                    bt_minValueR(node)
#define TREE_MAX_VALUE(node)                    bt_maxValueR(node)
#define TREE_TRAVERSAL(node)                    bt_levelOrder(node); printf("\n")
#define TREE_ISBST(node)                        bt_isBST3(node)

typedef struct treeNode{
    int data;
    struct treeNode* left;
    struct treeNode* right;
}treeNode;
typedef treeNode* treeNode_p;

treeNode_p bt_createTree(treeNode_p left, treeNode_p right, int data);
treeNode_p bt_insert(treeNode_p node, int value);
int bt_size(treeNode_p node);
int bt_maxDepth(treeNode_p node);
int bt_minValue(treeNode_p node);
int bt_minValueR(treeNode_p node);
int bt_maxValue(treeNode_p node);
int bt_maxValueR(treeNode_p node);
void bt_preOrder(treeNode_p node);
void bt_inOrder(treeNode_p node);
void bt_postOrder(treeNode_p node);
void bt_levelOrder(treeNode_p node);
bool bt_hasPathSum(treeNode_p node, int sum);
void printPaths(treeNode_p node);
void bt_mirror(treeNode_p node);
void bt_doubleTree(treeNode_p node);
bool bt_sameTree(treeNode_p node1, treeNode_p node2);
int countTree(int numsKey);
bool bt_isBST1(treeNode_p node);
bool bt_isBST2(treeNode_p node);
bool bt_isBST3(treeNode_p node);

#endif /* __BTREE_H_ */