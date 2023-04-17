#ifndef __PRIORITY_QUEUE_H_
#define __PRIORITY_QUEUE_H_

#include "heap.h"

#define CREATE_PQ(name)                     myHeap_p name=hp_createHeap()
#define CREATE_PQ_WITH_ARRAY(name,arr,len)  myHeap_p name=hp_maxHeapify(arr,len)
#define PQ_PRINTER(queue)                   hp_heapPrinter(queue)
#define PQ_ENQUEUE(queue,value)             hp_insertMaxHeap(queue,value)
#define PQ_DEQUEUE(queue)                   hp_extractMaxHeap(queue)
#define PQ_GET_FRONT(queue)                 hp_findMax(queue)
#define PQ_GET_SIZE(queue)                  hp_heapGetSize(queue)
#define PQ_IS_EMPTY(queue)                  hp_heapIsEmpty(queue)
#define FREE_PQ(queue)                      hp_freeHeap(&name)

#endif /* __PRIORITY_QUEUE_H_ */