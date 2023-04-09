#include "linkedlist.h"

myNode* createNode(int val, myNode* next){

    myNode* newNode = malloc(sizeof(myNode));
    newNode->next = next;
    newNode->data = val;

    return newNode;
}

static int length(myNode* node){

    int len=0;

    while(node){

        len++;
        node = node->next;
    }

    return len;
}

void printList(myNode* node){

    while(node){
        printf("node [%d] --> ", node->data);
        node = node->next;
    }

    printf("NULL\n");
}

void pushNode(myNode** headRef, int newDate){

    if(headRef == NULL){
        return;
    }

    myNode* newNode = createNode(newDate, (*headRef));
    *headRef = newNode;
}

int countNode(myNode* node, int val){

    int cnt=0;

    while(node){
        cnt = node->data == val ? cnt+1 : cnt;
        node = node->next;
    }

    return cnt;
}

int countNodeR(myNode* node, int val, int cnt){

    if(node == NULL){
        return cnt;
    }

    cnt = node->data == val ? cnt+1 : cnt;

    return countNodeR(node->next, val, cnt);
}

int getNth(myNode* node, int n){

    for(int i=0; i<n; i++){

        if(node == NULL){
            break;
        }

        node = node->next;
    }

    if(node == NULL){
        MLL_ERR("fatal error! index is not match.");
        return -1;
    }

    return node->data;
}

int getNthR(myNode* node, int n){

    n = n < 0 ? 0 : n;

    if(node == NULL){
        MLL_ERR("fatal error! index is not match.");
        return -1;
    }

    if(n == 0){
        return node->data;
    }

    return getNthR(node->next, --n);
}

void deleteList(myNode** headRef){
    
    if(headRef == NULL){
        return;
    }

    myNode* curr = *headRef;
    myNode* next = NULL;

    while(curr){

        next = curr->next;
        curr->next = NULL;
        free(curr);
        curr = next;
    }

    *headRef = NULL;
}

static void releaseNode(myNode* node){
    
    if(node == NULL){
        return;
    }

    releaseNode(node->next);
    node->next = NULL;
    free(node);
}

void deleteListR(myNode** headRef){
    
    if(headRef == NULL){
        return;
    }

    releaseNode(*headRef);
    *headRef = NULL;
}

int popNode(myNode** headRef){

    if(headRef == NULL){
        return -1;
    }

    if(*headRef == NULL){
        return -1;
    }

    int ret = (*headRef)->data;
    myNode* freeNode = *headRef;

    *headRef = (*headRef)->next;
    freeNode->next = NULL;
    free(freeNode);

    return ret;
}

void insertNth(myNode** headref, int index, int data){

    if(index < 0){
        return;
    }

    myNode* dummy = createNode(-1, *headref);
    myNode* newNode = createNode(data, NULL);
    myNode* ptr = dummy;

    for(int i=0; i<index; i++){
        ptr = ptr->next;
        
        if(ptr == NULL){
            MLL_ERR("Fatal Error, Wrong index.");
            return;
        }
    }

    newNode->next = ptr->next;
    ptr->next = newNode;
    *headref = dummy->next;

    FREE_NODE(dummy);
}

static myNode* insertR(myNode* node, int index, int data){

    if(index == 0){
        return createNode(data, node);
    }

    if(node == NULL){
        MLL_ERR("Fatal Error, Wrong index.");
        return NULL;
    }

    node->next = insertR(node->next, index-1, data);

    return node;
}

void insertNthR(myNode** headref, int index, int data){

    if(index < 0){
        return;
    }

    *headref = insertR(*headref, index, data);
}

void sortedInsert(myNode** headRef, myNode* newNode){

    if(headRef == NULL || newNode == NULL){
        return;
    }

    myNode* dummy = createNode(-1, *headRef);
    myNode* pre = dummy;
    myNode* curr = dummy->next;

    while(curr){

        if(curr->data >= newNode->data){
            INSERT(pre, newNode);
            *headRef = dummy->next;
            FREE_NODE(dummy);
            return;
        }

        pre = curr;
        curr = curr->next;
    }

    INSERT(pre, newNode);
    *headRef = dummy->next;
    FREE_NODE(dummy);
}

void sortedInsert2(myNode** headRef, myNode* newNode){

    if(headRef == NULL || newNode == NULL){
        return;
    }

    myNode dummy={-1, *headRef};
    myNode* curr = &dummy;

    while(curr->next != NULL && curr->next->data < newNode->data){
        curr = curr->next;
    }

    INSERT(curr,newNode);

    *headRef = dummy.next;
}

static myNode* sortedR(myNode* node, myNode* newNode){

    if(node == NULL){
        newNode->next = NULL;
        return newNode;
    }

    if(node->data >= newNode->data){
        newNode->next = node;
        return newNode;
    }

    node->next = sortedR(node->next, newNode);

    return node;
}

void sortedInsertR(myNode** headRef, myNode* newNode){

    if(headRef == NULL || newNode == NULL){
        return;
    }

    *headRef = sortedR(*headRef, newNode);
}

void insertSort(myNode** headRef){

    if(headRef == NULL){
        return;
    }

    myNode* dummy = createNode(-1, *headRef);
    myNode* pre = dummy;
    myNode* curr = *headRef;

    while(curr){

        if(curr->next && curr->data > curr->next->data){
            *headRef = curr == *headRef ? curr->next : *headRef;
            REMOVE(pre, curr);
            sortedInsert(headRef, curr);
            pre = dummy;
            curr = *headRef;
        }else{
            pre = curr;
            curr = curr->next;
        }
    }

    FREE_NODE(dummy);
}

void insertSort2(myNode** headRef){

    if(headRef == NULL){
        return;
    }

    myNode* result = NULL;
    myNode* curr = *headRef;
    myNode* next = NULL;

    while (curr){
        
        next = curr->next;
        sortedInsert2(&result, curr);
        curr = next;
    }

    *headRef = result;
}

void append(myNode** aRef, myNode** bRef){

    if(aRef == NULL || bRef == NULL){
        return;
    }

    if(*aRef == NULL){
        *aRef = *bRef;
    }else{
        myNode* ptr = *aRef;
        while(ptr->next != NULL){
            ptr = ptr->next;
        }
        ptr->next = *bRef;
    }

    *bRef = NULL;
}

void frontBackSplit(myNode* source, myNode** frontRef, myNode** backRef){

    if(source == NULL || frontRef == NULL || backRef == NULL){
        return;
    }

    int len=0;
    int firstHalf;

    myNode* ptr = source;
    *frontRef = source;

    while(ptr){
        len++;
        ptr = ptr->next;
    }

    firstHalf = len % 2 == 0 ? (len/2) : (len/2+1);
    ptr = source;

    for(int i=1; i<firstHalf; i++){
        ptr = ptr->next;
    }

    *backRef = ptr->next;
    ptr->next = NULL;
}

void frontBackSplit2(myNode* source, myNode** frontRef, myNode** backRef){

    if(source == NULL || frontRef == NULL || backRef == NULL){
        return;
    }

    int len = length(source);

    if(len < 2){
        *frontRef = source;
        *backRef = NULL;
    }else{
        *frontRef = source;
        int half = (len+1)/2;
        for(int i=1; i<half; i++){
            source = source->next;
        }
        *backRef = source->next;
        source->next = NULL;
    }
}

void frontBackSplit3(myNode* source, myNode** frontRef, myNode** backRef){

    if(source == NULL || frontRef == NULL || backRef == NULL){
        return;
    }

    int len = length(source);

    if(len < 2){
        *frontRef = source;
        *backRef = NULL;
    }else{
        myNode* fast=source->next, *slow=source;
        *frontRef = source;
        while(fast){
            fast = fast->next;
            if(fast){
               fast = fast->next;
               slow = slow->next;
            }
        }
        *backRef = slow->next;
        slow->next = NULL;
    }
}

void RemoveDuplicates(myNode* node){

    if(node == NULL){
        return;
    }

    myNode* curr = node;
    myNode* next = curr->next;

    while(next){

        if(curr->data == next->data){
            REMOVE(curr, next);
            FREE_NODE(next);
        }else{
            curr = curr->next;
        }
    }
}

static myNode* removeDuplicateNode(myNode* prenode, myNode* node){

    if(node == NULL){
        return NULL;
    }

    node->next = removeDuplicateNode(node, node->next);

    if(prenode->data == node->data){
        myNode* ret = node->next;
        FREE_NODE(node);
        return ret;
    }

    return node;
}

void RemoveDuplicatesR(myNode* node){

    if(node == NULL){
        return;
    }

    node->next = removeDuplicateNode(node, node->next);
}

void moveNode(myNode** destRef, myNode** sourceRef){

    if(destRef == NULL || sourceRef == NULL){
        return;
    }

    myNode dummy1 = {-1, *destRef}, dummy2 = {-1, *sourceRef};
    myNode* node = dummy2.next;

    REMOVE(&dummy2, node);
    INSERT(&dummy1, node);

    *destRef = dummy1.next;
    *sourceRef = dummy2.next;
}

void alternatingSplit(myNode* source, myNode** aRef, myNode** bRef){

    if(source == NULL || aRef == NULL || bRef == NULL){
        return;
    }

    myNode** list;

    while(source){

        list = (source->data & 0x1) ? bRef : aRef;
        moveNode(list, &source);
    }
}

myNode* shuffleMerge(myNode* a, myNode* b){

    myNode dummy = {-1, NULL};
    myNode* ptr = &dummy;

    while(a != NULL || b != NULL){

        if(a == NULL){
            ptr->next = b;
            break;
        }
        if(b == NULL){
            ptr->next = a;
            break;
        }

        ptr->next = a;
        ptr = a;
        a = a->next;

        ptr->next = b;
        ptr = b;
        b = b->next;
    }

    return dummy.next;
}

myNode* shuffleMerge2(myNode* a, myNode* b){

    myNode dummy = {-1, NULL};
    myNode* ptr = &dummy;

    while(a != NULL || b != NULL){

        if(a == NULL){
            ptr->next = b;
            break;
        }else if(b == NULL){
            ptr->next = a;
            break;
        }else{
            moveNode(&(ptr->next), &a);
            ptr = ptr->next;

            moveNode(&(ptr->next), &b);
            ptr = ptr->next;
        }
    }

    return dummy.next;
}

myNode* shuffleMerge3(myNode* a, myNode* b){

    myNode* result = NULL;
    myNode** ptr = &result;

    while(a != NULL || b!= NULL){

        if(a == NULL){
            *ptr = b;
            break;
        }else if(b == NULL){
            *ptr = a;
            break;
        }else{
            moveNode(ptr, &a);
            ptr = &((*ptr)->next);

            moveNode(ptr, &b);
            ptr = &((*ptr)->next);
        }
    }

    return result;
}

myNode* shuffleMergeR(myNode* a, myNode* b){

    if(a == NULL){
        return b;
    }

    if(b == NULL){
        return a;
    }

    myNode* ret = shuffleMergeR(a->next, b->next);
    a->next = b;
    b->next = ret;

    return a;
}

myNode* sortedMerge(myNode* a, myNode* b){

    myNode dummy = {-1, NULL};
    myNode* ptr = &dummy;
    myNode** target;

    while(a != NULL || b != NULL){

        if(a == NULL){
            ptr->next = b;
            break;
        }else if(b == NULL){
            ptr->next = a;
            break;
        }else{
            target = a->data < b->data ? &a : &b;
            moveNode(&(ptr->next), target);
            ptr = ptr->next;
        }
    }

    return dummy.next;
}

myNode* sortedMerge2(myNode* a, myNode* b){

    myNode* result = NULL;
    myNode** target;
    myNode** ptr = &result;

    while(a != NULL || b != NULL){

        if(a == NULL){
            *ptr = b;
            break;
        }else if(b == NULL){
            *ptr = a;
            break;
        }else{
            target = a->data < b->data ? &a : &b;
            moveNode(ptr, target);
            ptr = &((*ptr)->next);
        }
    }

    return result;
}

myNode* sortedMergeR(myNode* a, myNode* b){

    if(a == NULL){
        return b;
    }

    if(b == NULL){
        return a;
    }

    if(a->data < b->data){
        a->next = sortedMergeR(a->next, b);
        return a;
    }

    b->next = sortedMergeR(a, b->next);
    return b;
}

void mergeSort(myNode** headRef){

    if(headRef == NULL || *headRef == NULL){
        return;
    }

    if((*headRef)->next == NULL){
        return;
    }

    myNode* left, *right;

    frontBackSplit(*headRef, &left, &right);
    
    mergeSort(&left);
    mergeSort(&right);
    
    *headRef = sortedMerge(left, right);
}

myNode* SortedIntersect(myNode* a, myNode* b){

    myNode dummy = {-1, NULL};
    myNode* ptr = &dummy;
    
    while(a != NULL && b != NULL){

        if(a->data > b->data){
            b = b->next;
        }else if(a->data < b->data){
            a = a->next;
        }else{
            ptr->next = createNode(a->data, NULL);
            ptr = ptr->next;

            a = a->next;
            b = b->next;
        }
    }

    return dummy.next;
}

myNode* SortedIntersect2(myNode* a, myNode* b){

    myNode* ret=NULL;
    myNode** ptr = &ret;

    while(a != NULL && b != NULL){

        if(a->data > b->data){
            b = b->next;
        }else if(a->data < b->data){
            a = a->next;
        }else{
            pushNode(ptr, a->data);
            ptr = &((*ptr)->next);

            a = a->next;
            b = b->next;
        }
    }

    return ret;
}

myNode* SortedIntersectR(myNode* a, myNode* b){

    if(a == NULL || b == NULL){
        return NULL;
    }

    myNode* ret;

    if(a->data > b->data){
        ret = SortedIntersectR(a, b->next);
    }else if(a->data < b->data){
        ret = SortedIntersectR(a->next, b);
    }else{
        ret = createNode(a->data, NULL);
        ret->next = SortedIntersectR(a->next, b->next);
    }

    return ret;
}

void reverse(myNode** headRef){

    if(headRef == NULL || *headRef == NULL){
        return;
    }

    myNode* pre = NULL;
    myNode* curr = *headRef;
    myNode* next = curr->next;

    while(next){
        curr->next = pre;
        pre = curr;
        curr = next;
        next = next->next;
    }

    curr->next = pre;
    *headRef = curr;
}

void reverseR(myNode** headRef){

    if(headRef == NULL || (*headRef) == NULL){
        return;
    }

    if((*headRef)->next == NULL){
        return;
    }

    myNode* curr = *headRef;
    myNode* next = curr->next;
    *headRef = next;

    reverseR(headRef);

    next->next = curr;
    curr->next = NULL;
}

void reverseR2(myNode** headRef){

    if(headRef == NULL || (*headRef) == NULL){
        return;
    }

    myNode* first = *headRef;
    myNode* rest = first->next;

    if(rest == NULL){
        return;
    }

    reverseR2(&rest);

    first->next->next = first;
    first->next = NULL;
    *headRef = rest; 
}