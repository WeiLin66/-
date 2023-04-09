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
myNode* createNode(int val, myNode* next); // 返回創建節點
int length(myNode* node); // 打印鏈表長度
void printList(myNode* node); // 打印鏈表信息
void pushNode(myNode** headRef, int newDate); // 創建新節點並插入頭部

myNode* createNode(int val, myNode* next);
void printList(myNode* node);
void pushNode(myNode** headRef, int newDate);
int countNode(myNode* node, int val);
int countNodeR(myNode* node, int val, int cnt);
int getNth(myNode* node, int n);
int getNthR(myNode* node, int n);
void deleteList(myNode** headRef);
void deleteListR(myNode** headRef);
int popNode(myNode** headRef);
void insertNth(myNode** headref, int index, int data);
void insertNthR(myNode** headref, int index, int data);
void sortedInsert(myNode** headRef, myNode* newNode);
void sortedInsert2(myNode** headRef, myNode* newNode);
void sortedInsertR(myNode** headRef, myNode* newNode);
void insertSort(myNode** headRef);
void insertSort2(myNode** headRef);
void append(myNode** aRef, myNode** bRef);
void frontBackSplit(myNode* source, myNode** frontRef, myNode** backRef);
void frontBackSplit2(myNode* source, myNode** frontRef, myNode** backRef);
void frontBackSplit3(myNode* source, myNode** frontRef, myNode** backRef);
void RemoveDuplicates(myNode* node);
void RemoveDuplicatesR(myNode* node);
void moveNode(myNode** destRef, myNode** sourceRef);
void alternatingSplit(myNode* source, myNode** aRef, myNode** bRef);
myNode* shuffleMerge(myNode* a, myNode* b);
myNode* shuffleMerge2(myNode* a, myNode* b);
myNode* shuffleMerge3(myNode* a, myNode* b);
myNode* shuffleMergeR(myNode* a, myNode* b);
myNode* sortedMerge(myNode* a, myNode* b);
myNode* sortedMerge2(myNode* a, myNode* b);
myNode* sortedMergeR(myNode* a, myNode* b);
void mergeSort(myNode** headRef);
myNode* SortedIntersect(myNode* a, myNode* b);
myNode* SortedIntersect2(myNode* a, myNode* b);
myNode* SortedIntersectR(myNode* a, myNode* b);
void reverse(myNode** headRef);
void reverseR(myNode** headRef);
void reverseR2(myNode** headRef);

#endif /* __LINKEDLIST_H_ */