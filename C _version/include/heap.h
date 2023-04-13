#ifndef __HEAP_H_
#define __HEAP_H_

#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <assert.h>

#define MAX_HEAP_SIZE               256
#define CREATE_HEAP(name)           myHeap_p name=hp_createHeap()
#define HEAP_PRINTER(heap)          hp_heapPrinter(heap)
#define HEAP_SIZE(heap)             hp_heapGetSize(heap)
#define HEAP_IS_EMPTY(heap)         hp_heapIsEmpty(heap)
#define FREE_HEAP(name)             hp_freeHeap(&name)

/* Max Heap */
#define MAXHEAP_INSERT(heap,value)  hp_insertMaxHeap(heap,value)
#define MAXHEAP_REMOVE(heap)        hp_extractMaxHeap(heap)
#define MAXHEAP_REPLACE(heap,value) hp_replaceMaxHeap(heap,value)
/* Min Heap */
#define MINHEAP_INSERT(heap,value)  hp_insertMinHeap(heap,value)
#define MINHEAP_REMOVE(heap)        hp_extractMinHeap(heap)
#define MINHEAP_REPLACE(heap,value) hp_replaceMinHeap(heap,value)

typedef struct {
    int* buffer;
    int size;
}myHeap;
typedef myHeap* myHeap_p;

myHeap_p hp_createHeap(void);
void hp_heapPrinter(myHeap_p heap);
void hp_insertMaxHeap(myHeap_p heap, int value);
void hp_insertMinHeap(myHeap_p heap, int value);
int hp_findMax(myHeap_p heap);
int hp_findMin(myHeap_p heap);
int hp_extractMaxHeap(myHeap_p heap);
int hp_extractMinHeap(myHeap_p heap);
int hp_replaceMaxHeap(myHeap_p heap, int value);
int hp_replaceMinHeap(myHeap_p heap, int value);
int hp_heapGetSize(myHeap_p heap);
bool hp_heapIsEmpty(myHeap_p heap);
void hp_freeHeap(myHeap_p* heap);

#endif /* __HEAP_H_ */