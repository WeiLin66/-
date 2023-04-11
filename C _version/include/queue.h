#ifndef __QUEUE_H_
#define __QUEUE_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <assert.h>

#define __DYNAMIC_ARRAY		0
#define __LINKED_LIST		1
#define QUEUE_BASE			__LINKED_LIST

#if (QUEUE_BASE == __DYNAMIC_ARRAY)
#define MAX_QUEUE_SIZE				256
#define CREATE_QUEUE(name)			myQueue_p name=aq_createQueue()
#define QUEUE_PRINTER(queue)		aq_queuePrinter(queue)
#define ENQUEUE(queue,value)		aq_enqueue(queue,value)
#define DEQUEUE(queue)				aq_dequeue(queue)
#define QUEUE_GET_FRONT(queue)		aq_queueGetFront(queue)
#define QUEUE_GET_SIZE(queue)		aq_queueGetSize(queue)
#define QUEUE_IS_EMPTY(queue)		aq_queueIsEmpty(queue)
#define FREE_QUEUE(queue)			aq_freeQueue(&queue)

typedef struct{
	int* buffer;
	int front;
	int tail;
	int capacity;
}myQueue;
typedef myQueue* myQueue_p;

myQueue_p aq_createQueue(void);
void aq_queuePrinter(myQueue_p queue);
void aq_enqueue(myQueue_p queue, int value);
int aq_dequeue(myQueue_p queue);
int aq_queueGetFront(myQueue_p queue);
int aq_queueGetSize(myQueue_p queue);
bool aq_queueIsEmpty(myQueue_p queue);
void aq_freeQueue(myQueue_p* queue);

#else /* QUEUE_BASE == __LINKED_LIST */
#include "linkedlist.h"
#define CREATE_QUEUE(name)			myQueue_p name=ll_createQueue()
#define QUEUE_PRINTER(queue)		ll_queuePrinter(queue)
#define ENQUEUE(queue,value)		ll_enqueue(queue,value)
#define DEQUEUE(queue)				ll_dequeue(queue)
#define QUEUE_GET_FRONT(queue)		ll_queueGetFront(queue)
#define QUEUE_GET_SIZE(queue)		ll_queueGetSize(queue)
#define QUEUE_IS_EMPTY(queue)		ll_queueIsEmpty(queue)
#define FREE_QUEUE(queue)			ll_freeQueue(&queue);

typedef struct{
	myNode* root;
	myNode* tail;
	int size;
}myQueue;
typedef myQueue* myQueue_p;

myQueue_p ll_createQueue(void);
void ll_queuePrinter(myQueue_p queue);
void ll_enqueue(myQueue_p queue, int value);
int ll_dequeue(myQueue_p queue);
int ll_queueGetFront(myQueue_p queue);
int ll_queueGetSize(myQueue_p queue);
bool ll_queueIsEmpty(myQueue_p queue);
void ll_freeQueue(myQueue_p* queue);
#endif /* QUEUE_BASE == __DYNAMIC_ARRAY */

#endif /* __QUEUE_H_ */