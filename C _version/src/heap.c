#include "heap.h"

static __inline int hp_parent(int index){

    assert(index);

    return (index-1) >> 1;
}

static __inline int hp_leftChild(int index){

    return (index << 1) + 1;
}

static __inline int hp_rightChild(int index){

    return (index << 1) + 2;
}

static void hp_swap(int* arr, int i, int j){

    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
}

static void hp_maxHeapSiftUp(myHeap_p heap, int index){

    assert(heap);

    while(index > 0 && heap->buffer[index] > heap->buffer[hp_parent(index)]){
        hp_swap(heap->buffer,index,hp_parent(index));
        index = hp_parent(index);
    }
}

static void hp_maxHeapSiftDown(myHeap_p heap, int index){

    assert(heap);

    while(hp_leftChild(index) < hp_heapGetSize(heap)){

        int target = hp_leftChild(index);

        if(target + 1 < hp_heapGetSize(heap) && 
           heap->buffer[target+1] > heap->buffer[target]){
            target = hp_rightChild(index);
        }
        
        if(heap->buffer[index] >= heap->buffer[target]){
            break;
        }

        hp_swap(heap->buffer,index,target);
        index = target;
    }
}

static void hp_minHeapSiftUp(myHeap_p heap, int index){

    assert(heap);

    while(index > 0 && heap->buffer[index] < heap->buffer[hp_parent(index)]){
        hp_swap(heap->buffer,index,hp_parent(index));
        index = hp_parent(index);
    }    
}

static void hp_minHeapSiftDown(myHeap_p heap, int index){

    assert(heap);

    while(hp_leftChild(index) < hp_heapGetSize(heap)){

        int target = hp_leftChild(index);

        if(target + 1 < hp_heapGetSize(heap) && 
           heap->buffer[target+1] < heap->buffer[target]){
            target = hp_rightChild(index);
        }
        
        if(heap->buffer[index] <= heap->buffer[target]){
            break;
        }

        hp_swap(heap->buffer,index,target);
        index = target;
    }    
}

myHeap_p hp_createHeap(){

    myHeap_p ret = malloc(sizeof(myHeap));
    ret->buffer = malloc(sizeof(int)*MAX_HEAP_SIZE);
    ret->size = 0;

    return ret;
}

void hp_heapPrinter(myHeap_p heap){

    assert(heap);
    assert(heap->buffer);

    int size = hp_heapGetSize(heap);

    printf("[");
    for(int i=0; i<size; ++i){
        printf("%d", heap->buffer[i]);
        if(i != size-1){
            printf(", ");
        }
    }
    printf("]\n");
}

void hp_insertMaxHeap(myHeap_p heap, int value){

    assert(heap);
    assert(heap->buffer);

    int size = hp_heapGetSize(heap);

    heap->buffer[size] = value;
    hp_maxHeapSiftUp(heap,size);
    ++(heap->size);
}

void hp_insertMinHeap(myHeap_p heap, int value){

    assert(heap);
    assert(heap->buffer);

    int size = hp_heapGetSize(heap);

    heap->buffer[size] = value;
    hp_minHeapSiftUp(heap,size);
    ++(heap->size);
}

int hp_findMax(myHeap_p heap){

    assert(heap);
    assert(heap->buffer);

    if(hp_heapIsEmpty(heap)){
        printf("Heap is Empty!\n");
        return -1;
    }

    return heap->buffer[0];
}

int hp_findMin(myHeap_p heap){

    assert(heap);
    assert(heap->buffer);

    if(hp_heapIsEmpty(heap)){
        printf("Heap is Empty!\n");
        return -1;
    }

    return heap->buffer[0];
}

int hp_extractMaxHeap(myHeap_p heap){

    assert(heap);
    assert(heap->buffer);

    if(hp_heapIsEmpty(heap)){
        printf("Heap is Empty!\n");
        return -1;
    }

    int ret = hp_findMax(heap);

    hp_swap(heap->buffer,0,heap->size-1);
    --(heap->size);
    hp_maxHeapSiftDown(heap,0);

    return ret;
}

int hp_extractMinHeap(myHeap_p heap){

    assert(heap);
    assert(heap->buffer);

    if(hp_heapIsEmpty(heap)){
        printf("Heap is Empty!\n");
        return -1;
    }

    int ret = hp_findMin(heap);

    hp_swap(heap->buffer,0,heap->size-1);
    --(heap->size);
    hp_minHeapSiftDown(heap,0);

    return ret;
}

int hp_repaceMaxHeap(myHeap_p heap, int value){

    assert(heap);

    int ret = hp_findMax(heap);
    heap->buffer[0] = value;
    hp_maxHeapSiftDown(heap,0);

    return ret;
}

int hp_replaceMinHeap(myHeap_p heap, int value){

    assert(heap);

    int ret = hp_findMin(heap);
    heap->buffer[0] = value;
    hp_minHeapSiftDown(heap,0);

    return ret;
}

int hp_heapGetSize(myHeap_p heap){

    assert(heap);

    return heap->size;
}

bool hp_heapIsEmpty(myHeap_p heap){

    assert(heap);

    return heap->size == 0;
}

void hp_freeHeap(myHeap_p* heap){

    assert(heap);

    free((*heap)->buffer);
    free(*(heap));
    *heap = NULL;
}