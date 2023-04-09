#ifndef __LINKEDLIST_H_
#define __LINKEDLIST_H_

#include <stdio.h>
#include <stdlib.h>

#define MLL_DEBUG           1
#define MLL_LOG(fmt,arg...) fprintf(stdout, "[My LinkedList Log] "fmt"\n", ##arg)
#define MLL_ERR(fmt,arg...) fprintf(stderr, "[My LinkedList Error] "fmt"\n", ##arg)
#define MLL_DBG(fmt,arg...) do{                                                                 \
                                if(MLL_DEBUG){                                                  \
                                    fprintf(stdout, "[Debug Mode] [%s][%s][%d] "fmt"\n",        \
                                    __FILE__, __func__, __LINE__, ##arg);                       \
                                }                                                               \
                            }while(0);                                                          \

#define INSERT(pre, new)    do{                                                                 \
                                (new)->next = (pre)->next;                                      \
                                (pre)->next = new;                                              \
                            }while(0);
#define REMOVE(pre, curr)   do{                                                                 \
                                if(pre){                                                        \
                                    (pre)->next = (curr)->next;                                 \
                                }                                                               \
                            }while(0);
#define FREE_NODE(node)     do{                                                                 \
                                if(node){                                                       \
                                    (node)->next = NULL;                                        \
                                    free(node);                                                 \
                                }                                                               \
                            }while(0);
typedef struct node{
    int data;
    struct node* next;
}myNode;

/* helper functions */
myNode* mll_createNode(int val, myNode* next);
int length(myNode* node);
void mll_printList(myNode* node);
void mll_pushNode(myNode** headRef, int newDate);

void mll_pushNode(myNode** headRef, int newDate);
int mll_countNode(myNode* node, int val);
int mll_countNodeR(myNode* node, int val, int cnt);
int mll_getNth(myNode* node, int n);
int mll_getNthR(myNode* node, int n);
void mll_deleteList(myNode** headRef);
void mll_deleteListR(myNode** headRef);
int mll_popNode(myNode** headRef);
void mll_insertNth(myNode** headref, int index, int data);
void mll_insertNthR(myNode** headref, int index, int data);
void mll_sortedInsert(myNode** headRef, myNode* newNode);
void mll_sortedInsert2(myNode** headRef, myNode* newNode);
void sortedmll_insertR(myNode** headRef, myNode* newNode);
void mll_insertSortDummy(myNode** headRef);
void mll_mll_insertSort2(myNode** headRef);
void mll_append(myNode** aRef, myNode** bRef);
void mll_frontBackSplit(myNode* source, myNode** frontRef, myNode** backRef);
void mll_frontBackSplit2(myNode* source, myNode** frontRef, myNode** backRef);
void mll_frontBackSplit3(myNode* source, myNode** frontRef, myNode** backRef);
void mll_RemoveDuplicates(myNode* node);
void mll_RemoveDuplicatesR(myNode* node);
void mll_moveNode(myNode** destRef, myNode** sourceRef);
void mll_alternatingSplit(myNode* source, myNode** aRef, myNode** bRef);
myNode* mll_shuffleMerge(myNode* a, myNode* b);
myNode* mll_shuffleMerge2(myNode* a, myNode* b);
myNode* mll_shuffleMerge3(myNode* a, myNode* b);
myNode* mll_shuffleMergeR(myNode* a, myNode* b);
myNode* mll_sortedMerge(myNode* a, myNode* b);
myNode* mll_sortedMerge2(myNode* a, myNode* b);
myNode* mll_sortedMergeR(myNode* a, myNode* b);
void mll_mergeSort(myNode** headRef);
myNode* mll_SortedIntersect(myNode* a, myNode* b);
myNode* mll_SortedIntersect2(myNode* a, myNode* b);
myNode* mll_SortedIntersectR(myNode* a, myNode* b);
void mll_reverse(myNode** headRef);
void mll_reverseR(myNode** headRef);
void mll_reverseR2(myNode** headRef);

#endif /* __LINKEDLIST_H_ */