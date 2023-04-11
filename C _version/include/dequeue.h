#ifndef __DEQUEUE_H_
#define __DEQUEUE_H_

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <assert.h>

#define __DYNAMIC_ARRAY		0
#define __LINKED_LIST		1
#define DEQUEUE_BASE		__LINKED_LIST

#if (DEQUEUE_BASE == __DYNAMIC_ARRAY)
#define MAX_DEQUEUE_SIZE			256
#define CREATE_QUEUE(name)			myQueue_p name=adq_createQueue()
#define QUEUE_PRINTER(queue)		adq_queuePrinter(queue)
#define ADD_FRONT(queue,value)		adq_addFront(queue,value)
#define ADD_LAST(queue,value)		adq_addLast(queue,value)
#define REMOVE_FRONT(queue)			adq_removeFront(queue)
#define REMOVE_LAST(queue)			adq_removeLast(queue)
#define QUEUE_GET_FRONT(queue)		adq_queueGetFront(queue)
#define QUEUE_GET_LAST(queue)		adq_queueGetLast(queue)
#define QUEUE_GET_SIZE(queue)		adq_queueGetSize(queue)
#define QUEUE_IS_EMPTY(queue)		adq_queueIsEmpty(queue)
#define FREE_QUEUE(queue)			adq_freeQueue(&queue)

typedef struct{
	int* buffer;
	int front;
	int tail;
	int size;
	int capacity;
}myQueue;
typedef myQueue* myQueue_p;

myQueue_p adq_createQueue(void);
void adq_queuePrinter(myQueue_p queue);
void adq_addFront(myQueue_p queue, int value);
void adq_addLast(myQueue_p queue, int value);
int adq_removeFront(myQueue_p queue);
int adq_removeLast(myQueue_p queue);
int adq_queueGetFront(myQueue_p queue);
int adq_queueGetLast(myQueue_p queue);
int adq_queueGetSize(myQueue_p queue);
bool adq_queueIsEmpty(myQueue_p queue);
void adq_freeQueue(myQueue_p* queue);

#else /* DEQUEUE_BASE == __LINKED_LIST */
#include "linkedlist.h"
#define CREATE_QUEUE(name)			myQueue_p name=ldq_createQueue()
#define QUEUE_PRINTER(queue)		ldq_queuePrinter(queue)
#define ADD_FRONT(queue,value)		ldq_addFront(queue,value)
#define ADD_LAST(queue,value)		ldq_addLast(queue,value)
#define REMOVE_FRONT(queue)			ldq_removeFront(queue)
#define REMOVE_LAST(queue)			ldq_removeLast(queue)
#define QUEUE_GET_FRONT(queue)		ldq_queueGetFront(queue)
#define QUEUE_GET_LAST(queue)		ldq_queueGetLast(queue)
#define QUEUE_GET_SIZE(queue)		ldq_queueGetSize(queue)
#define QUEUE_IS_EMPTY(queue)		ldq_queueIsEmpty(queue)
#define FREE_QUEUE(queue)			ldq_freeQueue(&queue)

typedef struct{
	myNode* root;
	myNode* tail;
	int size;
}myQueue;
typedef myQueue* myQueue_p;

myQueue_p ldq_createQueue(void);
void ldq_queuePrinter(myQueue_p queue);
void ldq_addFront(myQueue_p queue, int value);
void ldq_addLast(myQueue_p queue, int value);
int ldq_removeFront(myQueue_p queue);
int ldq_removeLast(myQueue_p queue);
int ldq_queueGetFront(myQueue_p queue);
int ldq_queueGetLast(myQueue_p queue);
int ldq_queueGetSize(myQueue_p queue);
bool ldq_queueIsEmpty(myQueue_p queue);
void ldq_freeQueue(myQueue_p* queue);

#endif /* DEQUEUE_BASE == __DYNAMIC_ARRAY */

#endif /* __DEQUEUE_H_ */